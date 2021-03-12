package com.company.jdbctemplate.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;

import javax.sql.DataSource;

@RequiredArgsConstructor
public class MyDataSource {
    private final String dsn;
    private final String user;
    private final String pas;

    private DataSource ds = null;

    public DataSource createDataSource() {

        if (ds == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dsn);
            config.setUsername(user);
            config.setPassword(pas);
            config.setMaximumPoolSize(8);
            config.setMinimumIdle(4);
            return new HikariDataSource(config);
        }
        return ds;
    }
}
