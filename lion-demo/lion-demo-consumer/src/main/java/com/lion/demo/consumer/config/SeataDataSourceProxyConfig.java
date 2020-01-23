package com.lion.demo.consumer.config;

/**
 * SeataDataSourceProxyConfig
 * 分布式事物 Seata 数据源代理配置（Mybatis & Jpa）
 *
 * @author Yanzheng
 * @date 2019/09/24
 * Copyright 2019 Yanzheng. All rights reserved.
 */
//@Configuration
public class SeataDataSourceProxyConfig {

    /**
     * 从配置文件获取属性构造datasource，注意前缀，这里用的是druid，根据自己情况配置,
     * 原生datasource前缀取"spring.datasource"
     */
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.druid")
//    public DataSource dataSource() {
//        return new DruidDataSource();
//    }

    /**
     * 构造datasource代理对象，替换原来的datasource
     */
//    @Primary
//    @Bean
//    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
//        return new DataSourceProxy(dataSource);
//    }

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

}
