package com.jsearch.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public final class MySQLConnector {

	private static final Logger LOG = Logger.getLogger(MySQLConnector.class.getName());
	private static Connection conn = null;

	private MySQLConnector() {
	}

	public static void init() {
		if (conn == null) {
			try {
				Context context = new InitialContext();
				DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/jsearch");
				conn = dataSource.getConnection();
				if (conn != null) {
					LOG.info("Connected!...");
				}

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Connection instance() {
		return conn;
	}

	public static void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			LOG.info("Error: " + e);
		}
	}
}