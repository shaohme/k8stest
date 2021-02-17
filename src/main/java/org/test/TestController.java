package org.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {

    @RequestMapping("/status")
    public String status() {
        return "ok";
    }

    @RequestMapping("/")
    public String hello() throws Exception {
        String dbHostname = System.getProperty("test.pgsql.hostname");
        if (StringUtils.isEmpty(dbHostname) || dbHostname.equals("null")) {
            throw new IllegalArgumentException(String.format("invalid http hostname '%s'", dbHostname));
        }

        String url = String.format("jdbc:postgresql://%s/postgres", dbHostname);
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "postgres");
        props.setProperty("ssl", "false");
        Connection conn = DriverManager.getConnection(url, props);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT user;");
        rs.next();
        String s = rs.getString(1);
        rs.close();
        st.close();
        conn.close();
        return s;
    }
}
