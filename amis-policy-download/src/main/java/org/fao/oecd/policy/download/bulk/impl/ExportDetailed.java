package org.fao.oecd.policy.download.bulk.impl;

import org.fao.fenix.commons.msd.dto.full.Code;
import org.fao.fenix.commons.utils.FileUtils;
import org.fao.fenix.commons.utils.Language;
import org.fao.oecd.policy.download.bulk.BulkDownload;
import org.fao.oecd.policy.download.bulk.BulkExcel;
import org.fao.oecd.policy.download.bulk.BulkName;
import org.fao.oecd.policy.download.dao.ExportDao;

import javax.inject.Inject;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@BulkName("exportDetailed")
public class ExportDetailed implements BulkDownload {
    @Inject ExportDao dao;
    @Inject BulkExcel excelbuilder;
    @Inject FileUtils fileUtils;

    @Override
    public File createFile(File tmpFolder, Map<String, Object> parameters) throws Exception {
        //Validate parameters
        Collection<Code> countries = (Collection<Code>) parameters.get("countries");
        Collection<Code> policyTypes = (Collection<Code>) parameters.get("policyTypes");
        Collection<Code> policyMeasures = (Collection<Code>) parameters.get("policyMeasures");
        if (countries==null || policyTypes==null)
            throw new Exception("Countries, policy types or policy measures codelists not found");

        //Prepare codes maps
        Map<String, String> countriesMap = toMap(countries,Language.english);
        Map<String, String> policyTypesMap = toMap(policyTypes,Language.english);
        Map<String, String> policyMeasuresMap = toMap(policyMeasures,Language.english);

        //Retrieve data
        Collection<Object[]> wheatData = dao.countriesCountByMonthPolicyTypePolicyMeasure("1", countriesMap, policyTypesMap, policyMeasuresMap);
        Collection<Object[]> riceData = dao.countriesCountByMonthPolicyTypePolicyMeasure("2", countriesMap, policyTypesMap, policyMeasuresMap);
        Collection<Object[]> maizeData = dao.countriesCountByMonthPolicyTypePolicyMeasure("3", countriesMap, policyTypesMap, policyMeasuresMap);
        Collection<Object[]> soybeansData = dao.countriesCountByMonthPolicyTypePolicyMeasure("4", countriesMap, policyTypesMap, policyMeasuresMap);
        Collection<Object[]> allData = dao.countriesCountByMonthPolicyTypePolicyMeasure(null, countriesMap, policyTypesMap, policyMeasuresMap);

        //Create file structure
        Properties biofuelBulkProperties = loadProperties();
        File folder = new File(tmpFolder, biofuelBulkProperties.getProperty("bulk.folder.name.export.detailed"));
        folder.mkdir();
        File bulkFile = new File(tmpFolder, biofuelBulkProperties.getProperty("bulk.file.name.export.detailed"));

        //Create excel files
        excelbuilder.createExcel(
                wheatData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.1")),
                biofuelBulkProperties.getProperty("commodity.class.title.export.detailed.1"),
                4
        );
        excelbuilder.createExcel(
                riceData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.2")),
                biofuelBulkProperties.getProperty("commodity.class.title.export.detailed.2"),
                4
        );
        excelbuilder.createExcel(
                maizeData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.3")),
                biofuelBulkProperties.getProperty("commodity.class.title.export.detailed.3"),
                4
        );
        excelbuilder.createExcel(
                soybeansData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.4")),
                biofuelBulkProperties.getProperty("commodity.class.title.export.detailed.4"),
                4
        );
        excelbuilder.createExcel(
                allData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.all")),
                biofuelBulkProperties.getProperty("commodity.class.title.export.detailed.all"),
                4
        );

        //create bulk file
        fileUtils.zip(folder, bulkFile, true);

        //Return bulk file
        return bulkFile;
    }





    //Utils
    private Properties loadProperties() throws Exception {
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/org/fao/amis/policy/config/bulkDownload.properties"));
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
