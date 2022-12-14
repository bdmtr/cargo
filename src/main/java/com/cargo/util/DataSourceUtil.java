package com.cargo.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSourceUtil {
    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("application");
            config.setDriverClassName(rb.getString("driver"));
            config.setJdbcUrl(rb.getString("url"));
            config.setUsername(rb.getString("username"));
            config.setPassword(rb.getString("password"));
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private DataSourceUtil() {
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}