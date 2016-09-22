package org.fao.oecd.policy.upload.bulk.attachments.impl;

import com.jcraft.jsch.*;
import org.fao.ess.uploader.core.init.UploaderConfig;
import org.fao.oecd.policy.upload.bulk.attachments.dto.AttachmentProperties;
import org.fao.oecd.policy.dto.HostProperties;
import org.fao.fenix.commons.utils.FileUtils;
import org.fao.oecd.policy.utils.SFTP;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.io.*;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
public class FileManager {
    @Inject private UploaderConfig config;
    @Inject private FileUtils fileUtils;
    private File tmpFolder;
    @Inject SFTP sftpUtils;

    private HostProperties properties;

    public void init(HostProperties properties) throws Exception {
        this.properties = properties;
        sftpUtils.init(properties);
    }


    //SFTP operations management
    public String restoreAttachments(String source) throws Exception {
        ChannelSftp channel = sftpUtils.getConnection();
        try {
            String destinationPath = properties.getPath() + '/' + source;
            channel.rm(destinationPath);
            String backupSourcePath = properties.getPath() + "/backup/" + source;
            String lastBackupFolder = sftpUtils.getLastBackupPath(backupSourcePath, channel);
            if (lastBackupFolder!=null) {
                sftpUtils.rmDir(backupSourcePath + '/' + lastBackupFolder, channel);
                lastBackupFolder = sftpUtils.getLastBackupPath(backupSourcePath, channel);
                if (lastBackupFolder!=null) {
                    channel.symlink(backupSourcePath + '/' + lastBackupFolder, destinationPath);
                    //return the name of the restored backup
                    return lastBackupFolder.substring(lastBackupFolder.lastIndexOf('/')+1);
                }
            }
            return null;
        } finally {
            sftpUtils.close(channel);
        }
    }
    public Collection<AttachmentProperties> uploadAttachments(String source, Integer[] policyIDs) throws Exception {
        Collection<AttachmentProperties> attachments = new LinkedList<>();
        File attachmentsFolder = getAttachmentsFolder(source);
        if (attachmentsFolder.exists() && attachmentsFolder.isDirectory()) {
            ChannelSftp channel = sftpUtils.getConnection();
            try {
                //Create path to needed folders
                String backupPath = properties.getPath() + "/backup/" + source + '/' + sftpUtils.getTimeSuffix();
                String destinationPath = properties.getPath() + '/' + source;
                String backupSourcePath = properties.getPath() + "/backup/" + source;
                sftpUtils.mkDir(backupSourcePath, channel);
                String lastBackupFolder = sftpUtils.getLastBackupPath(backupSourcePath, channel);
                //Create backup folder
                sftpUtils.mkDir(backupPath, channel);
                try {
                    //Copy new files into the new backup folder
                    for (File sourceFolder : attachmentsFolder.listFiles())
                        if (sourceFolder.isDirectory()) {
                            Integer policyID = null;
                            try {
                                policyID = policyIDs[Integer.parseInt(sourceFolder.getName()) - 1];
                            } catch (Exception ex) { }
                            if (policyID != null) {
                                String fileDestinationPath = backupPath + '/' + policyID;
                                sftpUtils.mkDir(fileDestinationPath, channel);
                                channel.cd(fileDestinationPath);

                                for (File sourceFile : sourceFolder.listFiles())
                                    if (sourceFile.isFile()) {
                                        String digest = uploadAttachment(channel, sourceFile);
                                        attachments.add(new AttachmentProperties(policyID, sourceFile.getName(), digest, sourceFile.length()));
                                    }
                            } else
                                throw new BadRequestException("Policy ID not found for attachments into folder: "+sourceFolder.getName());
                        }
                    //Refresh link to the new backup folder
                    sftpUtils.rmFile(destinationPath, channel);
                    try {
                        channel.symlink(backupPath, destinationPath);
                    } catch (Exception ex) {
                        if (lastBackupFolder!=null) //try to restore the link to the previous backup folder
                            channel.symlink(backupSourcePath + '/' + lastBackupFolder, destinationPath);
                        throw ex;
                    }
                } catch (Exception ex) {
                    sftpUtils.rmDir(backupPath, channel); //try to roll back backup folder creation
                    throw ex;
                }
            } finally {
                sftpUtils.close(channel);
            }
        }
        return attachments;
    }

    private String uploadAttachment(ChannelSftp channel, File sourceFile) throws Exception {
        DigestInputStream input = new DigestInputStream(new FileInputStream(sourceFile), MessageDigest.getInstance("MD5"));
        channel.put(input, sourceFile.getName(), null, ChannelSftp.OVERWRITE);
        return new BigInteger(1, input.getMessageDigest().digest()).toString(16);
    }


    //Temporary folder management
    public File createTmpFolder(InputStream zipPackageStream, String source) throws IOException {
        File destinationFolder = getFolder(source);
        fileUtils.unzip(zipPackageStream, destinationFolder, true);

        File[] content = destinationFolder.listFiles();
        if (content.length==1 && content[0].isDirectory() && !content[0].getName().equalsIgnoreCase("attachments")) {
            for (File f : content[0].listFiles())
                fileUtils.copy(f, destinationFolder);
            fileUtils.delete(content[0]);
        }

        return destinationFolder;
    }

    public void removeTmpFolder (String source) {
        fileUtils.delete(getFolder(source));
    }

    public InputStream getMetadataFileStream(String source) throws FileNotFoundException {
        return getFile(source,"metadata.xlsx");
    }
    public InputStream getDataFileStream(String source) throws FileNotFoundException {
        return getFile(source,"data.csv");
    }
    public File getAttachmentsFolder(String source) throws FileNotFoundException {
        return getFolder(source,"attachments");
    }



    //File utils

    private File getFolder(String source) {
        if (tmpFolder==null) {
            String tmpFilePath = config.get("policy.local.folder.tmp");
            tmpFolder = new File(tmpFilePath != null ? tmpFilePath : "/tmp");
        }

        File folder = new File(tmpFolder, source);
        if (!folder.exists())
            folder.mkdirs();
        return folder;
    }
    private File getFolder(String source, String path) throws FileNotFoundException {
        File folder = new File(getFolder(source), path);
        return folder.exists() && folder.isDirectory() ? folder : null;
    }
    private InputStream getFile(String source, String path) throws FileNotFoundException {
        File file = new File(getFolder(source), path);
        return file.exists() && file.isFile() ? new FileInputStream(file) : null;
    }


}
