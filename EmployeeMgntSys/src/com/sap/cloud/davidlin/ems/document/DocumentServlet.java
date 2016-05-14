package com.sap.cloud.davidlin.ems.document;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;

import com.sap.ecm.api.EcmService;
import com.sap.ecm.api.RepositoryOptions;
import com.sap.ecm.api.RepositoryOptions.Visibility;

/**
 * Servlet implementation class DocumentServlet
 */
@WebServlet("/api/document")
public class DocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Session openCmisSession = null;
	private static final String uniqueName = "com.sap.cloud.davidlin.ems.document";
	private static final String repoKey = "my_repository_key";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DocumentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		initCmisSession();
	}

	public Session getOpenCmisSession() {
		if (openCmisSession == null) {
			initCmisSession();
		}
		return openCmisSession;
	}

	private void initCmisSession() {
		EcmService ecmSvc = null;
		try {
			InitialContext ctx = new InitialContext();
			ecmSvc = (EcmService) ctx.lookup("java:comp/env/EcmService");
			openCmisSession = ecmSvc.connect(uniqueName, repoKey);
		} catch (CmisObjectNotFoundException e) {
			RepositoryOptions repoOpt = new RepositoryOptions();
			repoOpt.setUniqueName(uniqueName);
			repoOpt.setRepositoryKey(repoKey);
			repoOpt.setVisibility(Visibility.PROTECTED);
			ecmSvc.createRepository(repoOpt);
			openCmisSession = ecmSvc.connect(uniqueName, repoKey);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
