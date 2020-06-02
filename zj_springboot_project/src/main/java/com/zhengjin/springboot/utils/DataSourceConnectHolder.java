package com.zhengjin.springboot.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

@Component
public class DataSourceConnectHolder {

	@Autowired
	DataSource dataSource;

	// 线程绑定对象
	ThreadLocal<Connection> resources = new NamedThreadLocal<>("Transactional resources");

	public Connection getConnection() {
		Connection conn = this.resources.get();
		if (conn != null) {
			return conn;
		}

		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.resources.set(conn);
		return conn;
	}

	public void cleanHolder() {
		Connection conn = this.resources.get();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		this.resources.remove();
	}

}
