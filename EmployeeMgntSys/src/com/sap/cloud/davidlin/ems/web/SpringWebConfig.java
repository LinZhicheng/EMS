package com.sap.cloud.davidlin.ems.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.sap.cloud.davidlin.ems.SpringConfig;

@Configuration
@EnableWebMvc
public class SpringWebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	public void onStartup(javax.servlet.ServletContext servletContext) throws javax.servlet.ServletException {
		servletContext.addListener(RequestContextListener.class);
		super.onStartup(servletContext);
	};

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { SpringConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { SpringConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/api/*" };
	}

}
