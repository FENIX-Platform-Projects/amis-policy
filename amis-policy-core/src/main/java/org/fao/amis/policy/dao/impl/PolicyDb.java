package org.fao.amis.policy.dao.impl;

import org.fao.amis.policy.dao.Dao;
import org.fao.amis.policy.dto.full.Policy;
import org.fao.amis.policy.dto.search.PolicySearch;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PolicyDb extends Dao {

    Connection connection;

    public PolicyDb() {
    }

    // Retrieve Subnational_Code
    public Policy retrieve(int id) {
        Policy result = null;
        try {
            connection = super.getJNDIConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM policytable WHERE policy_id =" + id);
            if (resultSet.next()) {
                result = new Policy(
                        resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getDate(6),
                        resultSet.getDate(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                        resultSet.getString(11), resultSet.getString(12), resultSet.getString(13), resultSet.getString(14),
                        resultSet.getString(15), resultSet.getString(16), resultSet.getString(17), resultSet.getString(18),
                        resultSet.getDate(19), resultSet.getString(20), resultSet.getString(21), resultSet.getString(22), resultSet.getString(23),
                        resultSet.getString(24), resultSet.getString(25), resultSet.getString(26), resultSet.getString(27),
                        resultSet.getDouble(29), resultSet.getString(30), resultSet.getString(31), resultSet.getString(32), resultSet.getString(33),
                        resultSet.getString(34), resultSet.getString(35), resultSet.getString(36), resultSet.getString(37));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
        return result;
    }

    // Retrieve Subnational_Code
    public List<Policy> retrieveAll() {
        List policies = null;
        try {
            connection = super.getJNDIConnection();
            policies = new LinkedList<Policy>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM policytable");
            while (resultSet.next()) {

                Policy result = new Policy(
                        resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getDate(6),
                        resultSet.getDate(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                        resultSet.getString(11), resultSet.getString(12), resultSet.getString(13), resultSet.getString(14),
                        resultSet.getString(15), resultSet.getString(16), resultSet.getString(17), resultSet.getString(18),
                        resultSet.getDate(19), resultSet.getString(20), resultSet.getString(21), resultSet.getString(22), resultSet.getString(23),
                        resultSet.getString(24), resultSet.getString(25), resultSet.getString(26), resultSet.getString(27),
                        resultSet.getDouble(29), resultSet.getString(30), resultSet.getString(31), resultSet.getString(32), resultSet.getString(33),
                        resultSet.getString(34), resultSet.getString(35), resultSet.getString(36), resultSet.getString(37));
                policies.add(result);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
        return policies;
    }


    public Policy insert(Policy policy) throws SQLException {
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO policytable"
                + "(metadata_id, policy_id, cpl_id, commodity_id,policy_element,start_date,end_date," +
                "units,value,value_text,value_type,exemptions,location_condition,notes,link,source," +
                "title_of_notice,legal_basis_name,date_of_publication,imposed_end_date,second_generation_specific," +
                "benchmark_tax,benchmark_product,tax_rate_biofuel,tax_rate_benchmark,start_date_tax,benchmark_link," +
                "original_dataset,type_of_change_code,type_of_change_name,measure_descr,product_original_hs,product_original_name," +
                "implementationprocedure,xs_yeartype,link_pdf,benchmark_link_pdf) VALUES"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            connection = super.getJNDIConnection();
            preparedStatement = connection.prepareStatement(insertTableSQL);
            int nextID = getNextID(connection);

            preparedStatement.setInt(1, policy.getMetadataId());
            preparedStatement.setInt(2, nextID);
            preparedStatement.setInt(3, policy.getCplId());
            preparedStatement.setDouble(4, policy.getCommodityId());
            preparedStatement.setString(5, policy.getPolicyElement());
            preparedStatement.setDate(6, (Date) policy.getStartDate());
            preparedStatement.setDate(7, (Date) policy.getEndDate());
            preparedStatement.setString(8, policy.getUnits());
            preparedStatement.setString(9, policy.getValue());
            preparedStatement.setString(10, policy.getValueText());
            preparedStatement.setString(11, policy.getValueType());
            preparedStatement.setString(12, policy.getExemptions());
            preparedStatement.setString(13, policy.getLocationCondition());
            preparedStatement.setString(14, policy.getNotes());
            preparedStatement.setString(15, policy.getLink());
            preparedStatement.setString(16, policy.getSource());
            preparedStatement.setString(17, policy.getTitleOfNotice());
            preparedStatement.setString(18, policy.getLegalBasisName());
            preparedStatement.setDate(19, (Date) policy.getDateOfPublication());
            preparedStatement.setString(20, policy.getImposedEndDate());
            preparedStatement.setString(21, policy.getSecondGenerationSpecific());
            preparedStatement.setString(22, policy.getBenchmarkTax());
            preparedStatement.setString(23, policy.getBenchmarkProduct());
            preparedStatement.setString(24, policy.getTaxRateBiofuel());
            preparedStatement.setString(25, policy.getTaxRateBenchmark());
            preparedStatement.setString(26, policy.getStartDateTax());
            preparedStatement.setString(27, policy.getBenchmarkLink());
            preparedStatement.setString(28, policy.getOriginalDataset());
            preparedStatement.setDouble(29, policy.getTypeOfChangeCode());
            preparedStatement.setString(30, policy.getTypeOfChangeName());
            preparedStatement.setString(31, policy.getMeasureDescr());
            preparedStatement.setString(32, policy.getProductOriginalHs());
            preparedStatement.setString(33, policy.getProductOriginalName());
            preparedStatement.setString(34, policy.getImplementationProcedure());
            preparedStatement.setString(35, policy.getXsYeartype());
            preparedStatement.setString(36, policy.getLinkPdf());
            preparedStatement.setString(37, policy.getBenchmarkLink());
            preparedStatement.executeUpdate();
            System.out.println("Record is inserted into policytable table!");

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
        return policy;
    }

    private int getNextID(Connection conn) {
        int autoIncKeyFromFunc = -1;
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX( policy_id ) FROM policytable;");
            if (rs.next()) {
                autoIncKeyFromFunc = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autoIncKeyFromFunc;
    }

    public Policy updateRecordToTable(Policy policy) throws SQLException {
        PreparedStatement preparedStatement = null;

        String updateTableSQL = "UPDATE policytable SET metadata_id =?, cpl_id=?,commodity_id=?,policy_element=?,start_date=?,end_date=?," +
                "units=?,value=?,value_text=?,value_type=?,exemptions=?,location_condition=?,notes=?,link=?,source=?," +
                "title_of_notice=?,legal_basis_name=?,date_of_publication=?,imposed_end_date=?,second_generation_specific=?," +
                "benchmark_tax=?,benchmark_product=?,tax_rate_biofuel=?,tax_rate_benchmark=?,start_date_tax=?,benchmark_link=?," +
                "original_dataset=?,type_of_change_code=?,type_of_change_name=?,measure_descr=?,product_original_hs=?,product_original_name=?," +
                "implementationprocedure=?,xs_yeartype=?,link_pdf=?,benchmark_link_pdf=?"
                + " WHERE policy_id=?";


        try {
            connection = getJNDIConnection();
            preparedStatement = connection.prepareStatement(updateTableSQL);
            preparedStatement.setInt(1, policy.getMetadataId());
            preparedStatement.setInt(2, policy.getCplId());
            preparedStatement.setDouble(3, policy.getCommodityId());
            preparedStatement.setString(4, policy.getPolicyElement());
            preparedStatement.setDate(5, (Date) policy.getStartDate());
            preparedStatement.setDate(6, (Date) policy.getEndDate());
            preparedStatement.setString(7, policy.getUnits());
            preparedStatement.setString(8, policy.getValue());
            preparedStatement.setString(9, policy.getValueText());
            preparedStatement.setString(10, policy.getValueType());
            preparedStatement.setString(11, policy.getExemptions());
            preparedStatement.setString(12, policy.getLocationCondition());
            preparedStatement.setString(13, policy.getNotes());
            preparedStatement.setString(14, policy.getLink());
            preparedStatement.setString(15, policy.getSource());
            preparedStatement.setString(16, policy.getTitleOfNotice());
            preparedStatement.setString(17, policy.getLegalBasisName());
            preparedStatement.setDate(18, (Date) policy.getDateOfPublication());
            preparedStatement.setString(19, policy.getImposedEndDate());
            preparedStatement.setString(20, policy.getSecondGenerationSpecific());
            preparedStatement.setString(21, policy.getBenchmarkTax());
            preparedStatement.setString(22, policy.getBenchmarkProduct());
            preparedStatement.setString(23, policy.getTaxRateBiofuel());
            preparedStatement.setString(24, policy.getTaxRateBenchmark());
            preparedStatement.setString(25, policy.getStartDateTax());
            preparedStatement.setString(26, policy.getBenchmarkLink());
            preparedStatement.setString(27, policy.getOriginalDataset());
            preparedStatement.setDouble(28, policy.getTypeOfChangeCode());
            preparedStatement.setString(29, policy.getTypeOfChangeName());
            preparedStatement.setString(30, policy.getMeasureDescr());
            preparedStatement.setString(31, policy.getProductOriginalHs());
            preparedStatement.setString(32, policy.getProductOriginalName());
            preparedStatement.setString(33, policy.getImplementationProcedure());
            preparedStatement.setString(34, policy.getXsYeartype());
            preparedStatement.setString(35, policy.getLinkPdf());
            preparedStatement.setString(36, policy.getBenchmarkLink());
            preparedStatement.setInt(37, policy.getPolicyId());
            // execute update SQL stetement
            preparedStatement.executeUpdate();
            System.out.println("Record is updated to policytable table!");

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
        return policy;
    }

    public Policy delete(int id) {
        Statement statement = null;
        Policy policy = null;
        try {
            policy = retrieve(id);
            connection = super.getJNDIConnection();
            statement = connection.createStatement();
            statement.execute("DELETE FROM policytable WHERE policy_id = " + id);
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
        return policy;
    }

    public List<Policy> search(PolicySearch searchBean) {

        List policies = null;
        try {
            connection = super.getJNDIConnection();
            policies = new LinkedList<Policy>();
            Statement statement = connection.createStatement();
            String query = findQueryAssociated(searchBean);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                Policy result = new Policy(
                        resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getDouble(4), resultSet.getString(5), resultSet.getDate(6),
                        resultSet.getDate(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10),
                        resultSet.getString(11), resultSet.getString(12), resultSet.getString(13), resultSet.getString(14),
                        resultSet.getString(15), resultSet.getString(16), resultSet.getString(17), resultSet.getString(18),
                        resultSet.getDate(19), resultSet.getString(20), resultSet.getString(21), resultSet.getString(22), resultSet.getString(23),
                        resultSet.getString(24), resultSet.getString(25), resultSet.getString(26), resultSet.getString(27),
                        resultSet.getDouble(29), resultSet.getString(30), resultSet.getString(31), resultSet.getString(32), resultSet.getString(33),
                        resultSet.getString(34), resultSet.getString(35), resultSet.getString(36), resultSet.getString(37));
                policies.add(result);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
        return policies;
    }
    /*
        4) every parameters
        3) source, policy element and commodity_id
        2) cpl_id
        1) commodity_id
     */

    private String findQueryAssociated(PolicySearch searchBean) {
        String policyFields = "metadata_id ," +
                "  policy_id, " +
                "   cpl_id, " +
                "  commodity_id, " +
                "  policy_element, " +
                "  start_date,  end_date, " +
                "  units, " +
                "  value, " +
                "  value_text, " +
                "  value_type, " +
                "  exemptions, " +
                "  location_condition, " +
                "  notes, " +
                "  link, " +
                "  source, " +
                "  title_of_notice, " +
                "  legal_basis_name, " +
                "  date_of_publication, " +
                "  imposed_end_date, " +
                "  second_generation_specific, " +
                "  benchmark_tax, " +
                "  benchmark_product, " +
                "  tax_rate_biofuel, " +
                "  tax_rate_benchmark, " +
                "  start_date_tax, " +
                "  benchmark_link, " +
                "  original_dataset, " +
                "  type_of_change_code, " +
                "  type_of_change_name, " +
                "  measure_descr, " +
                "  product_original_hs, " +
                "  product_original_name, " +
                "  implementationprocedure, " +
                "  xs_yeartype, " +
                "  link_pdf, " +
                "  benchmark_link_pdf ";


        String onlyCpl = "select " + policyFields + " from commwithmaster where ( " +
                "cpl_id = " + searchBean.getCplId() + " )";


        String onlyCommId = "select " + policyFields + " from commwithmaster where ( " +
                "commodity_id = " + searchBean.getCommodityId() + " )";

        String dateParameters = "select " + policyFields + " from commwithmaster where ( " +
                "commodityclass_code = " + searchBean.getCommodityClassCode() +
                " and start_date >= '" + searchBean.getStartDate() + "' and " +
                "end_date <= '" + searchBean.getEndDate() + "') ";

        String dateParametersWithoutCC = "select " + policyFields + " from commwithmaster where ( " +
                "  start_date >= '" + searchBean.getStartDate() + "' and " +
                "end_date <= '" + searchBean.getEndDate() + "') ";


        String dateParametersWithCommodity = "select " + policyFields + " from commwithmaster where ( " +
                "commodityclass_code = " + searchBean.getCommodityClassCode() + " and " +
                "commodity_id = " + searchBean.getCommodityId() +
                " and start_date >= '" + searchBean.getStartDate() + "' and " +
                "end_date <= '" + searchBean.getEndDate() + "' )";

        String dateParametersWithCpl = "select " + policyFields + " from commwithmaster where ( " +
                "commodityclass_code = " + searchBean.getCommodityClassCode() + " and" +
                "cpl_id = " + searchBean.getCommodityId() +
                " and start_date >= '" + searchBean.getStartDate() + "' and " +
                "end_date <= '" + searchBean.getEndDate() + "' )";


        int findQuery = searchBean.getQuery();
        String resultQuery = null;
        switch (findQuery) {
            case 1:
                resultQuery = onlyCpl;
                break;
            case 2:
                resultQuery = onlyCommId;
                break;
            case 3:
                resultQuery = dateParametersWithoutCC;
                break;
            case 4:
                resultQuery = dateParameters;
                break;
            case 5:
                resultQuery = dateParametersWithCommodity;
                break;
            case 6:
                resultQuery = dateParametersWithCpl;
                break;
        }
        return resultQuery;
    }
}