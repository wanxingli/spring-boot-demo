package com.sb.demo.druidConfig;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Configuration
public class DruidConfig {

    @Autowired
    private DruidDataSourceProperty druidDataSourceProperty;

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(druidDataSourceProperty.getUrl());
        druidDataSource.setUsername(druidDataSourceProperty.getUsername());
        druidDataSource.setPassword(druidDataSourceProperty.getPassword());
        druidDataSource.setDriverClassName(druidDataSourceProperty.getDriverClassName());

        druidDataSource.setInitialSize(druidDataSourceProperty.getInitialSize());
        druidDataSource.setMinIdle(druidDataSourceProperty.getMinIdle());
        druidDataSource.setMaxActive(druidDataSourceProperty.getMaxActive());
        druidDataSource.setMaxWait(druidDataSourceProperty.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(druidDataSourceProperty.getTimeBetweenEvicationRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(druidDataSourceProperty.getMinEvicatableIdleTimeMillis());
        druidDataSource.setValidationQuery(druidDataSourceProperty.getValidationQuery());
        druidDataSource.setTestWhileIdle(druidDataSourceProperty.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(druidDataSourceProperty.isTestOnBorrow());
        druidDataSource.setTestOnReturn(druidDataSourceProperty.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(druidDataSourceProperty.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(druidDataSourceProperty.getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setUseGlobalDataSourceStat(druidDataSourceProperty.isUseGlobalDataSourceStat());
        try {
            druidDataSource.setFilters(druidDataSourceProperty.getFilters());
        } catch (SQLException e) {
            log.error("druid configuration initialization filter", e);
        }
        druidDataSource.setConnectProperties(druidDataSourceProperty.getConnectionProperties());

        return druidDataSource;
    }
}
