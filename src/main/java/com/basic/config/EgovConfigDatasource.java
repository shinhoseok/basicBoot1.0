package com.basic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class EgovConfigDatasource {

	@Value("${basic.datasource.url}")
	private String url;

	@Value("${basic.datasource.username}")
	private String username;

	@Value("${basic.datasource.password}")
	private String password;

	@Value("${basic.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.profiles.active}")
	private String profile;

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		if(profile.equals("local")) {
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			return builder.setType(EmbeddedDatabaseType.HSQL).addScript("classpath:/db/sampledb.sql").build();
		} else {
//			DriverManagerDataSource dataSource = new DriverManagerDataSource();
//			dataSource.setUrl(url);
//			dataSource.setUsername(username);
//			dataSource.setPassword(password);
//			dataSource.setDriverClassName(driverClassName);
//			return dataSource;
			JndiDataSourceLookup jndiDataSourceLookup = new JndiDataSourceLookup();
			jndiDataSourceLookup.setResourceRef(true);
			return jndiDataSourceLookup.getDataSource("basic");
		}
	}

}
