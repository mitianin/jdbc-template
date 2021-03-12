package com.company.jdbctemplate;

import com.company.jdbctemplate.db.JdbcTemplate;
import com.company.jdbctemplate.db.MyDataSource;
import com.company.jdbctemplate.models.SomeUser;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        LogData ld = new LogData();

        DataSource ds = new MyDataSource(ld.getDsn(), ld.getUser(), ld.getPas())
                .createDataSource();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

        String query = "select id, login, name, surname from some_users where id>?";
        SomeUser u = jdbcTemplate.selectOne(query, new Object[]{4}, rs -> {
            SomeUser su = new SomeUser();
            try {
                su.setId(rs.getInt("id"));
                su.setLogin(rs.getString("login"));
                su.setName(rs.getString("name"));
                su.setSurname(rs.getString("surname"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return su;
        });

        System.out.println(u);


    }
}
