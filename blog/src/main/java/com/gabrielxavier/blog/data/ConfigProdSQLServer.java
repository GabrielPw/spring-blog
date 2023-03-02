package com.gabrielxavier.blog.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.net.URISyntaxException;

@Configuration
@Profile("prod")
public class ConfigProdSQLServer {

    @Bean
    DataSource dataSource() throws URISyntaxException {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        String dbUri = System.getenv("DATABASE_URL");

        System.out.println("dbUri: -" + dbUri + "\n");

        if(dbUri==null){
            System.out.println("Erro. Checar variavel de ambiente pra conexao do banco.");
        }

        String username = System.getenv("user_id");
        String password = System.getenv("pwd");
        String catalog = System.getenv("catalog");

        String dbUrl = "jdbc:sqlserver://" + dbUri + ";packet size=4096;user=" + username + ";password=" + password + ";data source =" + dbUri + ";persist security info=False;initial catalog=" + catalog + ";encrypt=true;trustServerCertificate=true;";

        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return dataSource;
    }

    @Bean
    JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setDatabase(Database.SQL_SERVER);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.SQLServer2012Dialect");
        adapter.setPrepareConnection(true);

        return adapter;
    }
}
