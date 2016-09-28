package org.fao.oecd.policy.download.dao;

import org.fao.fenix.commons.utils.database.DatabaseUtils;
import org.fao.oecd.policy.utils.DataSource;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class BiofuelDao {
    @Inject DataSource dataSource;
    @Inject DatabaseUtils databaseUtils;

    public Collection<Object[]> countriesCountByMonthPolicyType(String commodityClass, Map<String, String> countriesMap, Map<String, String> policyTypesMap) throws Exception {
        Collection<Object[]> data = new LinkedList<>();
        //manage countries
        Map<String,String> countriesMapByLabel = new TreeMap<>();
        for (Map.Entry<String,String> countryEntry : countriesMap.entrySet())
            countriesMapByLabel.put(countryEntry.getValue(), countryEntry.getKey());
        //create header row
        Collection<Object> header = new LinkedList<>();
        header.add("month");
        header.add("policyType");
        header.add("all");
        for (String countryLabel : countriesMapByLabel.keySet())
            header.add(countryLabel);
        data.add(header.toArray());
        //get and return data
        return getData(data, commodityClass, countriesMapByLabel.values(), countriesMap, policyTypesMap);
    }


    private Collection<Object[]> getData(Collection<Object[]> data, String commodityClass, Collection<String> countriesCode, final Map<String, String> countriesMap, final Map<String, String> policyTypesMap) throws Exception {
        Map<String, Integer> countriesIndex = new HashMap<>();
        int countryIndex=0;
        for (String country : countriesCode)
            countriesIndex.put(country, countryIndex++);
        Map<String, Map<Integer,Integer[]>> byPolicyType = new TreeMap<>();
        //Retrieve data
        Connection connection = dataSource.getConnection();
        try {
            //prepare query
            Collection<Object> parameters = new LinkedList<>();
            String query = Query.Timeseries_Biofuel_download.getQuery();
            if (commodityClass==null) {
                query = query.replace("<<commodityClass>>", "?,?,?");
                parameters.add("5");
                parameters.add("6");
                parameters.add("7");
            } else {
                query = query.replace("<<commodityClass>>", "?");
                parameters.add(commodityClass);
            }
            PreparedStatement statement = connection.prepareStatement(query);
            databaseUtils.fillStatement(statement, null, parameters.toArray());
            //fill raw data
            for (ResultSet resultSet = statement.executeQuery(); resultSet.next();) {
                String policyTypeLabel = policyTypesMap.get(resultSet.getString(1));
                Map<Integer,Integer[]> byMonth = byPolicyType.get(policyTypeLabel);
                if (byMonth==null)
                    byPolicyType.put(policyTypeLabel, byMonth = new TreeMap<>());
                Integer[] countries = byMonth.get(resultSet.getInt(2));
                if (countries==null) {
                    byMonth.put(resultSet.getInt(2), countries = new Integer[countriesCode.size()]);
                    Arrays.fill(countries,0);
                }
                countries[countriesIndex.get(resultSet.getString(3))] = 1;
            }
        } finally {
            connection.close();
        }

        //fill data
        for (Map.Entry<String, Map<Integer,Integer[]>> byPolicyTypeEntry : byPolicyType.entrySet()) {
            for (Map.Entry<Integer,Integer[]> byMonthEntry : byPolicyTypeEntry.getValue().entrySet()) {
                Integer[] countries = byMonthEntry.getValue();
                Object[] row = new Object[countries.length+3];
                int total = 0;
                for (int i=0; i<countries.length; i++) {
                    row[i+3] = countries[i];
                    total += countries[i];
                }
                row[0] = byMonthEntry.getKey();
                row[1] = byPolicyTypeEntry.getKey();
                row[2] = total;
                data.add(row);
            }
        }

        //return data itself
        return data;
    }

}

