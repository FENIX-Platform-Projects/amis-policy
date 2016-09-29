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

@BulkName("biofuelDetailed")
public class BiofuelDetailed implements BulkDownload {
    @Inject BiofuelDao dao;
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
        Collection<Object[]> ethanolData = dao.countriesCountByMonthPolicyTypePolicyMeasure("5", countriesMap, policyTypesMap, policyMeasuresMap);
        Collection<Object[]> biodieselData = dao.countriesCountByMonthPolicyTypePolicyMeasure("6", countriesMap, policyTypesMap, policyMeasuresMap);
        Collection<Object[]> biofuelData = dao.countriesCountByMonthPolicyTypePolicyMeasure("7", countriesMap, policyTypesMap, policyMeasuresMap);
        Collection<Object[]> allData = dao.countriesCountByMonthPolicyTypePolicyMeasure(null, countriesMap, policyTypesMap, policyMeasuresMap);

        //Create file structure
        Properties biofuelBulkProperties = loadProperties();
        File folder = new File(tmpFolder, biofuelBulkProperties.getProperty("bulk.folder.name.biofuel.detailed"));
        folder.mkdir();
        File bulkFile = new File(tmpFolder, biofuelBulkProperties.getProperty("bulk.file.name.biofuel.detailed"));

        //Create excel files
        excelbuilder.createExcel(
                ethanolData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.5")),
                biofuelBulkProperties.getProperty("commodity.class.title.biofuel.detailed.5"),
                4
        );
        excelbuilder.createExcel(
                biodieselData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.6")),
                biofuelBulkProperties.getProperty("commodity.class.title.biofuel.detailed.6"),
                4
        );
        excelbuilder.createExcel(
                biofuelData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.7")),
                biofuelBulkProperties.getProperty("commodity.class.title.biofuel.detailed.7"),
                4
        );
        excelbuilder.createExcel(
                allData.iterator(),
                new File(folder, biofuelBulkProperties.getProperty("commodity.class.file.name.all")),
                biofuelBulkProperties.getProperty("commodity.class.title.biofuel.detailed.all"),
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
