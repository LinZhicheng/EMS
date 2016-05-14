package com.sap.cloud.davidlin.ems.document;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/api/document/download")
public class DownloadServlet extends DocumentServlet {
	private static final long serialVersionUID = 1L;
	private Session openCmisSession;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileId = request.getParameter("imageId");
		if (this.openCmisSession == null) {
			this.openCmisSession = getOpenCmisSession();
		}
		Document document = (Document) openCmisSession.getObject(fileId);
		ContentStream cStream = document.getContentStream();
		InputStream iStream = cStream.getStream();
		try {
			response.setContentType(cStream.getMimeType());
			IOUtils.copy(iStream, response.getOutputStream());
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
