package com.sap.cloud.davidlin.ems.persistence.context;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JndiDataSourceContext implements DataSourceContext {

	protected DataSource dataSource;

	@Autowired
	private InitialContext initialContext;

	@Override
	public synchronized DataSource getDataSource() {
		// TODO Auto-generated method stub
		if (dataSource == null) {
			try {
				dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/DefaultDB");
			} catch (NamingException ne) {
				throw new IllegalStateException(ne);
			}
		}
		return dataSource;
	}

}
