package com.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

//@Configuration
public class DataSourceConfiguration {
//	private DataSource dataSource;
//
//	public void JpaConfiguration(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
//
//	public DataSourceConfiguration(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
//
//	@Bean
//	public EntityManagerFactory entityManagerFactory() {
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(true);
//
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setPackagesToScan("com.cinema.repository.UserRepository");
//		factory.setDataSource(dataSource);
//		factory.afterPropertiesSet();
//
//		return factory.getObject();
//	}
//
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		JpaTransactionManager txManager = new JpaTransactionManager();
//		txManager.setEntityManagerFactory(entityManagerFactory());
//		return txManager;
//	}
//	@Bean(name="entityManagerFactory")
//	public LocalSessionFactoryBean sessionFactory() {
//		return new LocalSessionFactoryBean();
//	}
	
}
