package org.fao.amis.policy.dao.impl;

import org.fao.amis.policy.dao.Dao;
import org.fao.amis.policy.dto.full.Commodity;
import org.fao.amis.policy.dto.search.CommoditySearch;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CommodityDao extends Dao {

    Connection connection;

    public CommodityDao() {
    }

    public Commodity retrieve(int id) {
        Commodity result = null;
        try {
            connection = super.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM commlistwithid WHERE commodity_id =" + id);

            if (resultSet.next()) {
                result = new Commodity(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getInt(10),
                        resultSet.getString(11));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }

        return result;
    }

    public List<Commodity> retrieveAll() {
        List commodities = null;
        try {
            connection = super.getConnection();
            commodities = new LinkedList<Commodity>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM commlistwithid");

            while (resultSet.next()) {

                Commodity result = new Commodity(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getInt(10),
                        resultSet.getString(11));

                commodities.add(result);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
        return commodities;
    }

    public Commodity insert(Commodity commodity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO commlistwithid"
                + "(commodity_id, country_name, hs_code, hs_suffix,hs_version,target_code,description," +
                "short_description,commodityclass_name,commodityclass_code,shared_group_code) VALUES"
                + "(?,?,?,?,?,?,?,?,?,?,?)";

        try {
            connection = super.getConnection();
            int id = getNextID(connection);

            preparedStatement = connection.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, commodity.getCountryName());
            preparedStatement.setString(3, commodity.getHsCode());
            preparedStatement.setString(4, commodity.getHsSuffix());
            preparedStatement.setString(5, commodity.getHsVersion());
            preparedStatement.setString(6, commodity.getTargetCode());
            preparedStatement.setString(7, commodity.getDescription());
            preparedStatement.setString(8, commodity.getShortDescription());
            preparedStatement.setString(9, commodity.getCommodityClassName());
            preparedStatement.setInt(10, commodity.getCommodityClassCode());
            preparedStatement.setString(11, commodity.getSharedGroupCode());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into commlistwithid table!");

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
        return commodity;
    }

    private int getNextID(Connection conn) {
        int autoIncKeyFromFunc = -1;
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX( commodity_id ) FROM commlistwithid;");
            if (rs.next()) {
                autoIncKeyFromFunc = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autoIncKeyFromFunc;
    }

    public Commodity updateRecordToTable(Commodity commodity) throws SQLException {
        PreparedStatement preparedStatement = null;
        String updateTableSQL = "UPDATE commlistwithid SET  country_name=?, hs_code=?, hs_suffix=?,hs_version=?,target_code=?, description=?," +
                "short_description=?,commodityclass_name=?,commodityclass_code=?,shared_group_code=?   "
                + " WHERE commodity_id  = ?";

        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(updateTableSQL);

            preparedStatement.setString(1, commodity.getCountryName());
            preparedStatement.setString(2, commodity.getHsCode());
            preparedStatement.setString(3, commodity.getHsSuffix());
            preparedStatement.setString(4, commodity.getHsVersion());
            preparedStatement.setString(5, commodity.getTargetCode());
            preparedStatement.setString(6, commodity.getDescription());
            preparedStatement.setString(7, commodity.getShortDescription());
            preparedStatement.setString(8, commodity.getCommodityClassName());
            preparedStatement.setInt(9, commodity.getCommodityClassCode());
            preparedStatement.setString(10, commodity.getSharedGroupCode());
            preparedStatement.setInt(11, commodity.getCommodityId());


            // execute update SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is updated to commlistwithid table!");

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
        return commodity;
    }

    public Commodity delete(int id) {
        Statement statement = null;
        Commodity comm = null;
        try {
            comm = retrieve(id);
            connection = super.getConnection();
            statement = connection.createStatement();
            statement.execute("DELETE FROM commlistwithid WHERE commodity_id = " + id);
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
        return comm;
    }

    public List<Commodity> search(CommoditySearch searchBean) {
        List commodities = null;
        try {
            connection = super.getConnection();
            commodities = new LinkedList<Commodity>();
            Statement statement = connection.createStatement();
            String query = findQueryAssociated(searchBean);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                Commodity result = new Commodity(
                        resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getInt(10),
                        resultSet.getString(11));

                commodities.add(result);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
        return commodities;
    }

    private String findQueryAssociated(CommoditySearch searchBean) {
        String everyParameters = "select * from commlistwithid where ( " +
                "hs_code = '" + searchBean.getHsCode() + "' and " +
                "commodityclass_code = '" + searchBean.getCommodityClassCode() + "' )";

        String commClassParameter = "select * from commlistwithid where ( " +
                "commodityclass_code = '" + searchBean.getCommodityClassCode() + "' )";

        String hsClassParameter = "select * from commlistwithid where ( " +
                "hs_code = '" + searchBean.getHsCode() + "' )";

        int findQuery = searchBean.getQuery();
        String resultQuery = null;
        switch (findQuery) {
            case 1:
                resultQuery = everyParameters;
                break;
            case 2:
                resultQuery = commClassParameter;
                break;
            case 3:
                resultQuery = hsClassParameter;
                break;
        }
        return resultQuery;
    }

}