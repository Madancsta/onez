package com.onez.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
	
    // Railway database configuration
    private static final String DB_NAME = "railway"; // use correct capitalization
    private static final String HOST = "caboose.proxy.rlwy.net";
    private static final String PORT = "44888";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "orPqLiECGrLhkOYDtrCfPApDYNwHEQmZ";

    private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME
            + "?useSSL=true&requireSSL=false&serverTimezone=UTC";

    public static Connection getDbConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
