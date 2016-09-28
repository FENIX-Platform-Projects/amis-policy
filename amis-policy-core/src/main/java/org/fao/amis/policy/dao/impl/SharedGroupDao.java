package org.fao.amis.policy.dao.impl;

import org.fao.amis.policy.dao.Dao;
import org.fao.amis.policy.dto.search.SharedGroupSearch;
import org.fao.amis.policy.dto.full.SharedGroup;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SharedGroupDao extends Dao {

    Connection connection;

    public SharedGroupDao() {
    }

    public SharedGroup retrieve(int id) {
        SharedGroup result = null;
        try {
            connection = super.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sharedgroups WHERE id_single =" + id);
            if (resultSet.next()) {
                result = new SharedGroup(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
        return result;
    }

    public List<SharedGroup> retrieveAll() {
        List sharedGroups = null;
        try {
            connection = super.getConnection();
            sharedGroups = new LinkedList<SharedGroup>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sharedgroups");
            while (resultSet.next()) {

                SharedGroup result = new SharedGroup(
                        resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                sharedGroups.add(result);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
        return sharedGroups;
    }

    public SharedGroup insert(SharedGroup sharedGroup) throws SQLException {
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO sharedgroups"
                + "(commodity_id, id_single, original_hs_version, original_hs_code,original_hs_suffix) VALUES"
                + "(?,?,?,?,?)";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, sharedGroup.getCommodityId());
            preparedStatement.setInt(2, sharedGroup.getIdSingle());
            preparedStatement.setString(3, sharedGroup.getOriginalHsVersion());
            preparedStatement.setString(4, sharedGroup.getOriginalHsCode());
            preparedStatement.setString(5, sharedGroup.getOriginalHsSuffix());
            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into sharedgroups table!");

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
        return sharedGroup;
    }

    public SharedGroup updateRecordToTable(SharedGroup sharedGroup) throws SQLException {
        PreparedStatement preparedStatement = null;
        String updateTableSQL = "UPDATE sharedgroups SET commodity_id = ?" +
                ",original_hs_version= ?, original_hs_code =?,original_hs_suffix = ? "
                + " WHERE id_single = ?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(updateTableSQL);

            preparedStatement.setInt(1, sharedGroup.getCommodityId());
            preparedStatement.setString(2, sharedGroup.getOriginalHsVersion());
            preparedStatement.setString(3, sharedGroup.getOriginalHsCode());
            preparedStatement.setString(4, sharedGroup.getOriginalHsSuffix());
            preparedStatement.setInt(5, sharedGroup.getIdSingle());
            preparedStatement.executeUpdate();
            System.out.println("Record is updated to DBUSER table!");

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
        return sharedGroup;

    }

    public SharedGroup delete(int id) {
        Statement statement = null;
        SharedGroup shGroup = null;
        try {
            shGroup = retrieve(id);
            connection = getConnection();
            statement = connection.createStatement();
            statement.execute("DELETE FROM sharedgroups WHERE id_single = " + id);
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
        return shGroup;
    }

    public List<SharedGroup> search(SharedGroupSearch searchBean) {
        List sharedGroups = null;
        try {
            connection = super.getConnection();
            sharedGroups = new LinkedList<SharedGroup>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sharedgroups " +
                    "where commodity_id = " + searchBean.getCommodityId());
            while (resultSet.next()) {

                SharedGroup result = new SharedGroup(
                        resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
                sharedGroups.add(result);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
        return sharedGroups;
    }

}


