package com.sap.cloud.davidlin.ems.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sap.cloud.davidlin.ems.persistence.context.DataSourceContext;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories
@EnableTransactionManagement
public class SpringPersistenceConfig {

	public static final String PERSISTENCE_UNIT_NAME = "EmployeeMgntSys";

	@Autowired
	protected DataSourceContext dataSourceContext;

	protected DataSource dataSource;

	protected EntityManagerFactory entityManagerFactory;

	protected EntityManager entityManager;

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	public synchronized DataSource initDataSource() {
		if (this.dataSource != null || this.dataSourceContext == null) {
			return this.dataSource;
		}
		try {
			final DataSource dataSource = this.dataSourceContext.getDataSource();
			try (final Connection connection = dataSource.getConnection()) {
				return this.dataSource = dataSource;
			}
		} catch (SQLException sqle) {
			throw new IllegalStateException(sqle);
		}
	}

	@Bean
	public synchronized EntityManagerFactory entityManagerFactory() {
		if (this.entityManagerFactory == null) {
			try {
				HashMap<String, Object> properties = new HashMap<>();
				properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, this.initDataSource());
				this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		return this.entityManagerFactory;
	}

	@Bean
	@Primary
	public synchronized EntityManager entityManager() {
		if (this.entityManager == null) {
			this.entityManager = SharedEntityManagerCreator.createSharedEntityManager(this.entityManagerFactory);
		}
		return this.entityManager;
	}

	@Bean(autowire = Autowire.BY_TYPE)
	@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
	@Lazy(true)
	public synchronized PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(this.entityManagerFactory);
	}
}
