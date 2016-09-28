package org.fao.oecd.policy.download.bulk.impl;

import org.fao.fenix.commons.msd.dto.full.Code;
import org.fao.fenix.commons.utils.FileUtils;
import org.fao.fenix.commons.utils.Language;
import org.fao.oecd.policy.download.bulk.BulkDownload;
import org.fao.oecd.policy.download.bulk.BulkExcel;
import org.fao.oecd.policy.download.bulk.BulkName;
import org.fao.oecd.policy.download.dao.BiofuelDao;

import javax.inject.Inject;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@BulkName("biofuel")
public class Biofuel implements BulkDownload {
    @Inject BiofuelDao dao;
    @Inject BulkExcel excelbuilder;
    @Inject FileUtils fileUtils;

    @Override
    public File createFile(File tmpFolder, Map<String, Object> parameters) throws Exception {
        //Validate parameters
        Collection<Code> countries = (Collection<Code>) parameters.get("countries");
        Collection<Code> policyTypes = (Collection<Code>) parameters.get("policyTypes");
        if (countries==null || policyTypes==null)
            throw new Exception("Countries or policy types codelists not found");

        //Prepare codes maps
        Map<String, String> countriesMap = toMap(countries,Language.english);
        Map<String, String> policyTypesMap = toMap(policyTypes,Language.english);

        //Retrieve data
        Collection<Object[]> ethanolData = dao.countriesCountByMonthPolicyType("5", countriesMap, policyTypesMap);
        Collection<Object[]> biodieselData = dao.countriesCountByMonthPolicyType("6", countriesMap, policyTypesMap);
        Collection<Object[]> biofuelData = dao.countriesCountByMonthPolicyType("7", countriesMap, policyTypesMap);
        Collection<Object[]> allData = dao.countriesCountByMonthPolicyType(null, countriesMap, policyTypesMap);

        //Create file structure
        Properties biofuelBulkProperties = loadBiofuelProperties();
        File folder = new File(tmpFolder, biofuelBulkProperties.getProperty("bulk.folder.name"));
        folder.mkdir();
        File bulkFile = new File(tmpFolder, biofuelBulkProperties.getProperty("bulk.file.name"));

        //Create excel files
        excelbuilder.createExcel(
                ethanolData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.5")),
                biofuelBulkProperties.getProperty("commodity.class.title.5")
        );
        excelbuilder.createExcel(
                biodieselData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.6")),
                biofuelBulkProperties.getProperty("commodity.class.title.6")
        );
        excelbuilder.createExcel(
                biofuelData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.7")),
                biofuelBulkProperties.getProperty("commodity.class.title.7")
        );
        excelbuilder.createExcel(
                allData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.all")),
                biofuelBulkProperties.getProperty("commodity.class.title.all")
        );

        //create bulk file
        fileUtils.zip(folder, bulkFile, true);

        //Return bulk file
        return bulkFile;
    }





    //Utils
    private Properties loadBiofuelProperties() throws Exception {
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/org/fao/amis/policy/config/biofuelCountriesCount.properties"));
        return properties;
    }

    private Map<String, String> toMap (Collection<Code> codes, Language language) {
        Map<String, String> codesMap = new HashMap<>();
        addCodes(codesMap, codes, language);
        return codesMap;
    }
    private void addCodes(Map<String, String> codesMap, Collection<Code> codes, Language language) {
        if (codes!=null)
            for (Code code : codes) {
                codesMap.put(code.getCode(), code.getTitle().get(language.getCode()));
                addCodes(codesMap, code.getChildren(), language);
            }
    }



}
