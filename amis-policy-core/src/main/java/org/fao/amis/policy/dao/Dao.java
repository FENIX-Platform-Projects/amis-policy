package org.fao.amis.policy.dao;

import org.fao.oecd.policy.utils.DataSource;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.SQLException;

public class Dao {

    //@Resource(lookup = "java:jboss/postgresqllocal")
    //private DataSource datasource;
    @Inject DataSource dataSource;

    public Connection getJNDIConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
