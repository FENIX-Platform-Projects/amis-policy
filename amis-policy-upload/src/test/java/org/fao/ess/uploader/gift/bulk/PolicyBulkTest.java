package org.fao.ess.uploader.gift.bulk;

import org.fao.ess.uploader.core.init.UploaderConfig;
import org.fao.oecd.policy.dto.HostProperties;
import org.fao.oecd.policy.dto.PolicyConfig;
import org.fao.oecd.policy.upload.bulk.PolicyBulk;
import org.fao.oecd.policy.upload.bulk.attachments.impl.FileManager;
import org.fao.oecd.policy.utils.DataSource;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class PolicyBulkTest {
    PolicyConfig config;
    UploaderConfig uploaderConfig;
    FileManager fileManager;
    DataSource dataSource;
    PolicyBulk policyBulk;

    @Before
    public void setUp() throws Exception {
        config = CDISupport.getInstance(PolicyConfig.class);
        uploaderConfig = CDISupport.getInstance(UploaderConfig.class);
        fileManager = CDISupport.getInstance(FileManager.class);
        dataSource = CDISupport.getInstance(DataSource.class);
        policyBulk = CDISupport.getInstance(PolicyBulk.class);

        config.init(this.getClass().getResourceAsStream("/org/fao/amis/policy/config/main.properties"));
        uploaderConfig.init(this.getClass().getResourceAsStream("/org/fao/amis/policy/config/upload.properties"));
        fileManager.init(getAttachmentsRemoteFolderHostProperties(uploaderConfig));
        dataSource.init(config.get("policy.db.url"),config.get("policy.db.usr"),config.get("policy.db.psw"));
    }

    @Test
    public void mainLogic1() throws Exception {
        policyBulk.mainLogic("WTO", this.getClass().getResourceAsStream("/test/test.zip"));
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