package com.sap.cloud.davidlin.ems.document;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisNameConstraintViolationException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/api/document/upload")
public class UploadServlet extends DocumentServlet {
	private static final long serialVersionUID = 1L;
	private static final String TEMP_PATH = "C:\\TEMP";
	private Session openCmisSession;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
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
		String contentType = request.getContentType();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(TEMP_PATH));
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> iterator = fileItems.iterator();
			while (iterator.hasNext()) {
				FileItem fileItem = iterator.next();
				if (!fileItem.isFormField()) {
					String fileName = fileItem.getName();
					if (this.openCmisSession == null) {
						this.openCmisSession = getOpenCmisSession();
					}
					Folder root = this.openCmisSession.getRootFolder();

					// Try to create work folder
					Map<String, String> newFolderProps = new HashMap<>();
					newFolderProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
					newFolderProps.put(PropertyIds.NAME, "emsFolder");
					Folder emsFolder = null;
					try {
						emsFolder = root.createFolder(newFolderProps);
					} catch (CmisNameConstraintViolationException cncve) {
						ItemIterable<CmisObject> children = root.getChildren();
						for (CmisObject co : children) {
							if (co instanceof Folder && co.getName().equals("emsFolder")) {
								emsFolder = (Folder) co;
							}
						}
					}
					// Try to create new file
					Map<String, String> newFileProps = new HashMap<>();
					newFileProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
					newFileProps.put(PropertyIds.NAME, fileName);
					try {
						ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
						IOUtils.copy(fileItem.getInputStream(), byteArrayOutputStream);
						byte[] data = byteArrayOutputStream.toByteArray();
						InputStream iStream = new ByteArrayInputStream(data);
						ContentStream cStream = openCmisSession.getObjectFactory().createContentStream(fileName,
								data.length, contentType, iStream);
						Document document = emsFolder.createDocument(newFileProps, cStream, VersioningState.NONE);
						Cookie cookie = new Cookie("imageId", document.getId());
						cookie.setPath("/EmployeeMgntSys/");
						response.addCookie(cookie);
					} catch (CmisNameConstraintViolationException e) {
						// File exist
						System.out.println("File has already exist!");
					} catch (IOException e) {
						// TODO: handle exception
						throw e;
					}
				}
			}
		} catch (Exception e) {
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
