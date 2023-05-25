package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@MapperScan(value = "com.example.demo.mapper", sqlSessionFactoryRef = "mariadbSqlSessionFactory")
@EnableTransactionManagement
@Configuration
public class MariadbConfig {

    @Bean(name = "mariadbDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.mariadb.datasource")
    public DataSource mariadbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mariadbSqlSessionFactory")
    @Primary
    public SqlSessionFactory mariadbSqlSessionFactory(@Qualifier("mariadbDataSource") DataSource mariadbDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mariadbDataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mariadbSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mariadbSqlSessionTemplate(SqlSessionFactory mariadbSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(mariadbSqlSessionFactory);
    }

}
