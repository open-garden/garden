package com.zipc.garden.webplatform.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.Project;
import com.zipc.garden.webplatform.shared.CharaCode;

/**
 * A Servlet class that runs when project edits are applied.
 */
@SuppressWarnings("serial")
@MultipartConfig(maxFileSize = 5242880, maxRequestSize = 27262976, fileSizeThreshold = 0)
public class FileUpload extends HttpServlet {

    /**
     * Called when project edits in the following classes are applied.<br>
     * {@link com.zipc.garden.webplatform.client.view.part.ProjectSettingViewPart}<br>
     * Apply the edited contents to the Project table.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(final HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getContentType() != null && request.getContentType().indexOf("x-gwt-rpc") != -1) {
            super.service(request, response);
            return;
        }
        request.setCharacterEncoding("UTF-8");
        Part part = request.getPart("file");
        InputStream iStream = part.getInputStream();
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        int c;
        try {
            while ((c = iStream.read()) != -1) {
                bStream.write(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bStream != null) {
                try {
                    bStream.flush();
                    bStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        byte[] byteFile = bStream.toByteArray();
        Byte[] binaryFile = new Byte[byteFile.length];
        for (int i = 0; i < byteFile.length; i++) {
            binaryFile[i] = Byte.valueOf(byteFile[i]);
        }

        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Project project = session.byId(Project.class).load(Long.parseLong(request.getParameter("projectId")));
                if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
                    project.setName(request.getParameter("name"));
                }
                project.setDescription(request.getParameter("description").replaceAll(System.lineSeparator(), "<br />"));
                project.setEncodingType(CharaCode.getNameByKey(request.getParameter("encode")));
                if (request.getParameter("clear").equals("clear")) {
                    project.setImage(null);
                    project.setImageName(null);
                } else {
                    if (binaryFile != null && !(binaryFile.length == 0)) {
                        project.setImage(binaryFile);
                    }
                    if (request.getParameter("imageName") != null && !request.getParameter("imageName").isEmpty()) {
                        project.setImageName(request.getParameter("imageName"));
                    }
                }
                if (request.getParameter("imageWidth") != null && !request.getParameter("imageWidth").isEmpty()) {
                    project.setImageWidth(Integer.parseInt(request.getParameter("imageWidth")));
                }
                if (request.getParameter("imageHeight") != null && !request.getParameter("imageHeight").isEmpty()) {
                    project.setImageHeight(Integer.parseInt(request.getParameter("imageHeight")));
                }

                session.persist(project);

                tx.commit();

            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw e;
            }
        }
    }
}
