package org.fao.oecd.policy.download.dao;

import org.fao.fenix.commons.utils.database.DatabaseUtils;
import org.fao.oecd.policy.utils.DataSource;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ExportDao {
    @Inject DataSource dataSource;
    @Inject DatabaseUtils databaseUtils;

    //Export detailed
    public Collection<Object[]> countriesCountByMonthPolicyTypePolicyMeasure(String commodityClass, Map<String, String> countriesMap, Map<String, String> policyTypesMap, Map<String, String> policyMeasuresMap) throws Exception {
        Collection<Object[]> data = new LinkedList<>();
        //manage countries
        Map<String,String> countriesMapByLabel = new TreeMap<>();
        for (Map.Entry<String,String> countryEntry : countriesMap.entrySet())
            countriesMapByLabel.put(countryEntry.getValue(), countryEntry.getKey());
        //create header row
        Collection<Object> header = new LinkedList<>();
        header.add("Period");
        header.add("Policy Type");
        header.add("Policy Measure");
        header.add("Sum of AMIS countries");
        for (String countryLabel : countriesMapByLabel.keySet())
            header.add(countryLabel);
        data.add(header.toArray());
        //get and return data
        return getData(data, commodityClass, countriesMapByLabel.values(), policyTypesMap, policyMeasuresMap);
    }

    private Collection<Object[]> getData(Collection<Object[]> data, String commodityClass, Collection<String> countriesCode, final Map<String, String> policyTypesMap, Map<String, String> policyMeasuresMap) throws Exception {
        Map<String, Integer> countriesIndex = new HashMap<>();
        int countryIndex=0;
        for (String country : countriesCode)
            countriesIndex.put(country, countryIndex++);
        Map<String, Map<String, Map<Integer,Integer[]>>> byPolicyType = new TreeMap<>();
        //Retrieve data
        Connection connection = dataSource.getConnection();
        try {
            //prepare query
            Collection<Object> parameters = new LinkedList<>();
            String query = Query.Timeseries_Detailed_download.getQuery();
            //add period
            query = query.replace("<<from>>","2007-01-01").replace("<<to>>","2014-12-31");
            //add policy types
            query = query.replace("<<policyType>>","?");
            parameters.add("1");
            //add commodity class
            if (commodityClass==null) {
                query = query.replace("<<commodityClass>>", "?,?,?,?");
                parameters.add("1");
                parameters.add("2");
                parameters.add("3");
                parameters.add("4");
            } else {
                query = query.replace("<<commodityClass>>", "?");
                parameters.add(commodityClass);
            }
            PreparedStatement statement = connection.prepareStatement(query);
            databaseUtils.fillStatement(statement, null, parameters.toArray());

            //fill raw data
            for (ResultSet resultSet = statement.executeQuery(); resultSet.next();) {
                String policyTypeLabel = policyTypesMap.get(resultSet.getString(1));
                String policyMeasureLabel = policyMeasuresMap.get(resultSet.getString(2));
                int month = resultSet.getInt(3);

                Map<String, Map<Integer,Integer[]>> byPolicyMeasure = byPolicyType.get(policyTypeLabel);
                if (byPolicyMeasure==null)
                    byPolicyType.put(policyTypeLabel, byPolicyMeasure = new TreeMap<>());

                Map<Integer,Integer[]> byMonth = byPolicyMeasure.get(policyMeasureLabel);
                if (byMonth==null)
                    byPolicyMeasure.put(policyMeasureLabel, byMonth = new TreeMap<>());

                Integer[] countries = byMonth.get(month);
                if (countries==null) {
                    byMonth.put(month, countries = new Integer[countriesCode.size()]);
                    Arrays.fill(countries,0);
                }
                countries[countriesIndex.get(resultSet.getString(4))] = 1;
            }
        } finally {
            connection.close();
        }

        //fill data
        for (Map.Entry<String, Map<String, Map<Integer,Integer[]>>> byPolicyTypeEntry : byPolicyType.entrySet()) {
            for (Map.Entry<String, Map<Integer,Integer[]>> byPolicyMeasureEntry : byPolicyTypeEntry.getValue().entrySet()) {
                for (Map.Entry<Integer, Integer[]> byMonthEntry : byPolicyMeasureEntry.getValue().entrySet()) {
                    Integer[] countries = byMonthEntry.getValue();
                    Object[] row = new Object[countries.length + 4];
                    int total = 0;
                    for (int i = 0; i < countries.length; i++) {
                        row[i + 4] = countries[i];
                        total += countries[i];
                    }
                    row[0] = byMonthEntry.getKey();
                    row[1] = byPolicyTypeEntry.getKey();
                    row[2] = byPolicyMeasureEntry.getKey();
                    row[3] = total;
                    data.add(row);
                }
            }
        }

        //return data itself
        return data;
    }



}

