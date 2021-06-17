package com.justech.mobile.mobileserver.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @description: sqlserver配置
 * @author: xing.peng
 * @create: 2020-12-15
 **/
@Configuration
@MapperScan(basePackages = "com.justech.mobile.mobileserver.mapper.sqlserver", sqlSessionFactoryRef = "otherSqlSessionFactory")
public class MyBatisDbOtherConfig {

    @Bean(name = "otherDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.other")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "otherTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("otherDataSource") DataSource datasource) {
        return new DataSourceTransactionManager(datasource);
    }


    @Bean(name = "otherSqlSessionFactory")
    public SqlSessionFactory basicSqlSessionFactory(@Qualifier("otherDataSource") DataSource dataSource ) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.justech.mobile.mobileserver.entity");
        factoryBean.setConfiguration(configuration());
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:sqlserver-mappers/*.xml")
        );


        return factoryBean.getObject();
    }

    @ConfigurationProperties(prefix = "mybatis.configuration")
    private org.apache.ibatis.session.Configuration configuration() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "otherSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("otherSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
