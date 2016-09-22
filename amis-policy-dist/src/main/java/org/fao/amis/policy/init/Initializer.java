package org.fao.amis.policy.init;

import org.fao.ess.uploader.core.init.UploaderConfig;
import org.fao.oecd.policy.dto.HostProperties;
import org.fao.oecd.policy.dto.PolicyConfig;
import org.fao.oecd.policy.upload.bulk.attachments.impl.FileManager;
import org.fao.oecd.policy.utils.DataSource;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.ws.rs.WebApplicationException;
import java.util.Map;

@WebListener
public class Initializer implements ServletContextListener {
    @Inject PolicyConfig config;
    @Inject UploaderConfig uploaderConfig;
    @Inject FileManager fileManager;
    @Inject DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            config.init(this.getClass().getResourceAsStream("/org/fao/amis/policy/config/main.properties"));
            uploaderConfig.init(this.getClass().getResourceAsStream("/org/fao/amis/policy/config/upload.properties"));
            fileManager.init(getAttachmentsRemoteFolderHostProperties(uploaderConfig));
            dataSource.init(config.get("policy.db.url"),config.get("policy.db.usr"),config.get("policy.db.psw"));
        } catch (Exception e) {
            throw new WebApplicationException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }



    //Utils
    private HostProperties getAttachmentsRemoteFolderHostProperties(Map<String,String> config) {
        return new HostProperties(
                null,
                config.get("policy.remote.host"),
                new Integer(config.get("policy.remote.port")),
                config.get("policy.remote.usr"),
                config.get("policy.remote.psw"),
                config.get("policy.remote.path.attachments")
        );
    }
}
