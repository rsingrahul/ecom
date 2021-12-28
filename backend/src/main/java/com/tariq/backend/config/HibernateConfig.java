package com.tariq.backend.config;

import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages="com.tariq.backend.dto")
@EnableTransactionManagement
public class HibernateConfig {
	
	Logger logger = Logger.getLogger(HibernateConfig.class.getName());
	
	private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/onlineshopping?useSSL=false";
	private final static String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String DATABASE_DIALECT = "org.hibernate.dialect.MySQLDialect";
	private final static String DATABASE_USERNAME = "root";
	private final static String DATABASE_PASSWORD = "root";
	
	@Bean("dataSource")
	public DataSource getDataSource(){
		
		BasicDataSource dataSource = new BasicDataSource();
		logger.info("Basic Data source :: "+dataSource);
		dataSource.setUrl(DATABASE_URL);
		logger.info("Data base url set :: "+DATABASE_URL);
		dataSource.setDriverClassName(DATABASE_DRIVER);
		logger.info("Data base driver set :: "+DATABASE_DRIVER);
		dataSource.setUsername(DATABASE_USERNAME);
		logger.info("Database user name set :: "+DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		logger.info("Database password set :: "+DATABASE_PASSWORD);
		
		return dataSource;
	}
	
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource){
		
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.tariq.backend.dto");
		logger.info("Session factory class build ");
		
		return builder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		
		Properties pro = new Properties();
		pro.put("hibernate.dialect", DATABASE_DIALECT);
		pro.put("hibernate.show_sql", "true");
		pro.put("hibernate.format_sql", "true");
		pro.put("hibernate.hbm2ddl.auto", "update");
		logger.info("hibernate properties are set!!");
		
		return pro;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

}
