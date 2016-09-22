package org.fao.amis.policy.dao.impl;

import org.fao.amis.policy.dto.full.Cpl;
import org.fao.amis.policy.dao.Dao;
import org.fao.amis.policy.dto.search.CplSearch;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CplDb extends Dao {

    Connection connection;

    public CplDb() {
    }

    public Cpl retrieve(int id) {
        Cpl result = null;
        try {

            connection = super.getJNDIConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mastertable WHERE cpl_id =" + id);
            if (resultSet.next()) {
                result = new Cpl(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6),
                        resultSet.getString(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getInt(10),
                        resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13), resultSet.getInt(14),
                        resultSet.getString(15), resultSet.getInt(16), resultSet.getString(17), resultSet.getInt(18),
                        resultSet.getString(19), resultSet.getInt(20), resultSet.getString(21));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
        return result;
    }

    public List<Cpl> retrieveAll() {
        List cpls = null;
        try {
            cpls = new LinkedList<Cpl>();
            connection = super.getJNDIConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mastertable");
            while (resultSet.next()) {

                Cpl result = new Cpl(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6),
                        resultSet.getString(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getInt(10),
                        resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13), resultSet.getInt(14),
                        resultSet.getString(15), resultSet.getInt(16), resultSet.getString(17), resultSet.getInt(18),
                        resultSet.getString(19), resultSet.getInt(20), resultSet.getString(21));
                cpls.add(result);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
        return cpls;
    }

    public Cpl insert(Cpl cpl) throws SQLException {

        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO mastertable"
                + "(cpl_id, cpl_code, commodity_id,country_code,country_name,subnational_code," +
                "subnational_name,commoditydomain_code,commoditydomain_name,commodityclass_code,commodityclass_name,policydomain_code," +
                "policydomain_name,policytype_code,policytype_name,policymeasure_code,policymeasure_name," +
                "condition_code,condition,individualpolicy_code,individualpolicy_name) VALUES"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            connection = super.getJNDIConnection();
            preparedStatement = connection.prepareStatement(insertTableSQL);
            int nextId = getNextID(connection);
            preparedStatement.setInt(1, nextId);
            preparedStatement.setString(2, cpl.getCplCode());
            preparedStatement.setInt(3, cpl.getCommodityId());
            preparedStatement.setInt(4, cpl.getCountryCode());
            preparedStatement.setString(5, cpl.getCountryName());
            preparedStatement.setInt(6, cpl.getSubnationalCode());
            preparedStatement.setString(7, cpl.getSubnationalName());
            preparedStatement.setInt(8, cpl.getCommodityDomainCode());
            preparedStatement.setString(9, cpl.getCommodityDomainName());
            preparedStatement.setInt(10, cpl.getCommodityClassCode());
            preparedStatement.setString(11, cpl.getCommodityClassName());
            preparedStatement.setInt(12, cpl.getPolicyDomainCode());
            preparedStatement.setString(13, cpl.getPolicyDomainName());
            preparedStatement.setInt(14, cpl.getPolicyTypeCode());
            preparedStatement.setString(15, cpl.getPolicyTypeName());
            preparedStatement.setInt(16, cpl.getPolicyMeasureCode());
            preparedStatement.setString(17, cpl.getPolicyMeasureName());
            preparedStatement.setInt(18, cpl.getConditionCode());
            preparedStatement.setString(19, cpl.getCondition());
            preparedStatement.setInt(20, cpl.getIndividualPolicyCode());
            preparedStatement.setString(21, cpl.getIndividualPolicyName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return cpl;
    }

    private int getNextID(Connection conn) {
        int autoIncKeyFromFunc = -1;
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX( cpl_id ) FROM mastertable;");
            if (rs.next()) {
                autoIncKeyFromFunc = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autoIncKeyFromFunc;
    }

    public Cpl updateRecordToTable(Cpl cpl) throws SQLException {
        PreparedStatement preparedStatement = null;
        String updateTableSQL = "UPDATE mastertable SET  cpl_code=?, commodity_id=?,country_code=?,country_name=?,subnational_code=?," +
                "subnational_name=?,commoditydomain_code=?,commoditydomain_name=?,commodityclass_code=?,commodityclass_name=?,policydomain_code=?," +
                "policydomain_name=?,policytype_code=?,policytype_name=?,policymeasure_code=?,policymeasure_name=?," +
                "condition_code=?,condition=?,individualpolicy_code=?,individualpolicy_name=?"
                + "WHERE cpl_id=?";
        try {
            connection = super.getJNDIConnection();
            preparedStatement = connection.prepareStatement(updateTableSQL);
            preparedStatement.setString(1, cpl.getCplCode());
            preparedStatement.setInt(2, cpl.getCommodityId());
            preparedStatement.setInt(3, cpl.getCountryCode());
            preparedStatement.setString(4, cpl.getCountryName());
            preparedStatement.setInt(5, cpl.getSubnationalCode());
            preparedStatement.setString(6, cpl.getSubnationalName());
            preparedStatement.setInt(7, cpl.getCommodityDomainCode());
            preparedStatement.setString(8, cpl.getCommodityDomainName());
            preparedStatement.setInt(9, cpl.getCommodityClassCode());
            preparedStatement.setString(10, cpl.getCommodityClassName());
            preparedStatement.setInt(11, cpl.getPolicyDomainCode());
            preparedStatement.setString(12, cpl.getPolicyDomainName());
            preparedStatement.setInt(13, cpl.getPolicyTypeCode());
            preparedStatement.setString(14, cpl.getPolicyTypeName());
            preparedStatement.setInt(15, cpl.getPolicyMeasureCode());
            preparedStatement.setString(16, cpl.getPolicyMeasureName());
            preparedStatement.setInt(17, cpl.getConditionCode());
            preparedStatement.setString(18, cpl.getCondition());
            preparedStatement.setInt(19, cpl.getIndividualPolicyCode());
            preparedStatement.setString(20, cpl.getIndividualPolicyName());
            preparedStatement.setInt(21, cpl.getCplId());
            preparedStatement.executeUpdate();
            System.out.println("Record is updated to mastertable table!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }

        }
        return cpl;
    }

    public Cpl delete(int id) {
        Statement statement = null;
        Cpl cpl = null;
        try {
            cpl = retrieve(id);
            connection = super.getJNDIConnection();
            statement = connection.createStatement();
            statement.execute("DELETE FROM mastertable WHERE cpl_id = " + id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cpl;
    }

    public List<Cpl> search(CplSearch searchBean) {

        List cpls = null;
        try {
            cpls = new LinkedList<Cpl>();
            connection = super.getJNDIConnection();
            Statement statement = connection.createStatement();
            String query = findQueryAssociated(searchBean);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                Cpl result = new Cpl(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6),
                        resultSet.getString(7), resultSet.getInt(8), resultSet.getString(9), resultSet.getInt(10),
                        resultSet.getString(11), resultSet.getInt(12), resultSet.getString(13), resultSet.getInt(14),
                        resultSet.getString(15), resultSet.getInt(16), resultSet.getString(17), resultSet.getInt(18),
                        resultSet.getString(19), resultSet.getInt(20), resultSet.getString(21));
                cpls.add(result);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpls;

    }

    private String findQueryAssociated(CplSearch searchBean) {

        String noParameters = "select * from mastertable";

        String onlyCommodityClass = "select * from mastertable where ( " +
                "commodityclass_code = " + searchBean.getCommodityClassCode() + " )";


        String onlyCountry = "select * from mastertable where ( " +
                "country_code = " + searchBean.getCountryCode() + " )";


        String everyParameters = "select * from mastertable where ( " +
                "commodityclass_code = " + searchBean.getCommodityClassCode() + " and " +
                "country_code = " + searchBean.getCountryCode() + " and " +
                "policydomain_code = " + searchBean.getPolicyDomainCode() + " and " +
                "policymeasure_code = " + searchBean.getPolicyMeasureCode() + " )";

        String twoParameters = "select * from mastertable where( " +
                "commodityclass_code = " + searchBean.getCommodityClassCode() + " and " +
                "country_code = " + searchBean.getCountryCode() + " )";

        String measureParameters = "select * from mastertable where( " +
                "commodityclass_name = " + searchBean.getCommodityClassCode() + " and " +
                "country_code = " + searchBean.getCountryCode() + " and " +
                "policydomain_name = " + searchBean.getPolicyDomainCode() + "  )";

        String domainParameters = "select * from mastertable where( " +
                "commodityclass_name = " + searchBean.getCommodityClassCode() + " and " +
                "country_code = " + searchBean.getCountryCode() + " and " +
                "policymeasure_name = " + searchBean.getPolicyMeasureCode() + " )";


        int findQuery = searchBean.getQuery();
        String resultQuery = null;
        switch (findQuery) {
            case 1:
                resultQuery = twoParameters;
                break;
            case 2:
                resultQuery = domainParameters;
                break;
            case 3:
                resultQuery = measureParameters;
                break;
            case 4:
                resultQuery = everyParameters;
                break;
            case 5:
                resultQuery = noParameters;
                break;
            case 6:
                resultQuery = onlyCommodityClass;
                break;
            case 7:
                resultQuery = onlyCountry;
                break;
        }
        return resultQuery;
    }
}


