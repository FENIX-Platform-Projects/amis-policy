package org.fao.oecd.policy.download.bulk.impl;

import org.fao.fenix.commons.msd.dto.data.Resource;
import org.fao.fenix.commons.msd.dto.full.DSDCodelist;
import org.fao.fenix.commons.utils.FileUtils;
import org.fao.oecd.policy.dto.PolicyConfig;
import org.fao.oecd.policy.utils.D3SClient;
import org.fao.oecd.policy.utils.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BiofuelTest {
    PolicyConfig config;
    DataSource dataSource;
    FileUtils fileUtils;
    D3SClient d3sClient;

    File folder;
    File zipFile;

    @Before
    public void setUp() throws Exception {
        config = CDISupport.getInstance(PolicyConfig.class);
        dataSource = CDISupport.getInstance(DataSource.class);
        fileUtils = CDISupport.getInstance(FileUtils.class);
        d3sClient = CDISupport.getInstance(D3SClient.class);
        folder = new File("/tmp/policy/biofuel");

        config.init(this.getClass().getResourceAsStream("/org/fao/amis/policy/config/main.properties"));
        dataSource.init(config.get("policy.db.url"),config.get("policy.db.usr"),config.get("policy.db.psw"));
        folder.mkdirs();
    }

    @After
    public void tearDown() throws Exception {
        fileUtils.copy(zipFile, new File("/tmp/policy/"+zipFile.getName()));
        fileUtils.delete(folder);
    }

    @Test
    public void createFile() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("countries", d3sClient.filterCodelist(getD3sBaseUrl(), "OECD_Country", "1.0", null, 1));
        parameters.put("policyTypes", d3sClient.filterCodelist(getD3sBaseUrl(), "OECD_PolicyType", "1.0", null));

        Biofuel biofuel = CDISupport.getInstance(Biofuel.class);
        zipFile = biofuel.createFile(folder, parameters);
    }


    //Utils
    private String getD3sBaseUrl() {
        String baseUrl = config.get("policy.d3s.url");
        return baseUrl + (baseUrl.charAt(baseUrl.length() - 1) != '/' ? "/" : "");
    }


}