package com.meiduimall.payment.config;

import java.sql.SQLException;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 数据源配置
 * @author xuji
 *
 */
@Configuration
public class DataSourceConfig {
	
	private HikariDataSource pool;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	@Value("${spring.datasource.connectionTimeout}")
	private String connectionTimeout;
	
	@Value("${spring.datasource.maximumPoolSize}")
	private String maximumPoolSize;
	
	@Value("${spring.datasource.minimumIdle}")
	private String minimumIdle;
	
	@Value("${spring.datasource.maxLifetime}")
	private String maxLifetime;
	
	@Value("${spring.datasource.idleTimeout}")
	private String idleTimeout;
	
	@Value("${spring.datasource.autoCommit}")
	private String autoCommit;
	
	@Value("${spring.datasource.connectionTestQuery}")
	private String connectionTestQuery;
	

	@Bean(destroyMethod = "close")
	public DataSource getHikariDataSource() throws SQLException {
		HikariConfig config = new HikariConfig();

		config.setDriverClassName(driverClassName);
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password); 
		config.setConnectionTimeout(Long.valueOf(connectionTimeout));
		config.setMaximumPoolSize(Integer.valueOf(maximumPoolSize));
		config.setMinimumIdle(Integer.valueOf(minimumIdle));
		config.setMaxLifetime(Long.valueOf(maxLifetime));
		config.setIdleTimeout(Long.valueOf(idleTimeout));
		config.setAutoCommit(Boolean.valueOf(autoCommit));
		config.setConnectionTestQuery(connectionTestQuery);
		return pool=new HikariDataSource(config);
	}
	
	@PreDestroy
	public void close() {
		if (this.pool != null) {
			this.pool.close();
		}
	}
}