package com.zipc.garden.webplatform.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;

import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.Directory;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.server.service.ProjectServiceImpl;
import com.zipc.garden.webplatform.shared.ProjectInfo;

/**
 * A class that downloads the entire project or files.
 */
public class FileDownLoadServlet extends HttpServlet {

    /**
     * It works when you download on the project list screen or download the folder / file on the project explorer. <br>
     * The download process will be performed according to the specified parameters.
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = (String) req.getParameter("name");
        String fileIds = req.getParameter("ids");
        String type = "";
        if (req.getParameterMap().containsKey("type")) {
            type = req.getParameter("type");
        }
        List<String> idlist = new ArrayList<String>(Arrays.asList(fileIds.split(",")));
        byte[] download = null;
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            if (type.isEmpty()) {
                CriteriaQuery<File> criteria = session.getCriteriaBuilder().createQuery(File.class);
                Root<File> fileTbl = criteria.from(File.class);
                criteria.select(fileTbl);
                criteria.where(fileTbl.get("id").in(idlist));
                List<File> targetFileList = session.createQuery(criteria).getResultList();
                if (targetFileList.size() > 1) {
                    download = zipFiles(targetFileList);
                    name += ".zip";
                } else {
                    download = targetFileList.get(0).getContent();
                    name += "." + targetFileList.get(0).getExtension();
                }
            } else if ("project".equals(type)) {
                ProjectServiceImpl projectService = new ProjectServiceImpl();
                List<ProjectInfo> projectList = new ArrayList<>();
                idlist.forEach(projectId -> projectList.add(projectService.getProject(Long.valueOf(projectId))));
                download = projectZipFiles(session, projectList, name);
                name += ".zip";
            } else {
                CriteriaQuery<Directory> dirCriteria = session.getCriteriaBuilder().createQuery(Directory.class);
                Root<Directory> dirTbl = dirCriteria.from(Directory.class);
                dirCriteria.select(dirTbl);
                dirCriteria.where(dirTbl.get("id").in(idlist));
                List<Directory> targetDirList = session.createQuery(dirCriteria).getResultList();

                List<File> targetFileList = new ArrayList<File>();
                List<Directory> emptyDirList = new ArrayList<Directory>();
                getFolderContent(session, targetDirList, targetFileList, emptyDirList);
                download = folderZipFiles(targetFileList, emptyDirList, name);
                name += ".zip";

            }
        } catch (Throwable e) {
            e.printStackTrace();
            return;
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename='" + name + "'; filename*=UTF-8''" + URLEncoder.encode(name, "UTF-8") + ";");

        try (java.io.OutputStream out = resp.getOutputStream()) {
            out.write(download);
            out.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Check the inside of the folder and set the files and folders to be zipped.
     * @param session hibernate Session information
     * @param targetDirList Folder to be compressed
     * @param targetFileList Storage destination of the file to be compressed
     * @param emptyDirList Storage destination of the empty folder to be compressed
     */
    private void getFolderContent(Session session, List<Directory> targetDirList, List<File> targetFileList, List<Directory> emptyDirList) {
        for (Directory directory : targetDirList) {
            // select files under directory
            List<File> files;
            CriteriaQuery<File> fileCriteria = session.getCriteriaBuilder().createQuery(File.class);
            Root<File> fileTbl = fileCriteria.from(File.class);
            fileCriteria.select(fileTbl);
            fileCriteria.where(session.getCriteriaBuilder().equal(fileTbl.get("directory").get("id"), directory.getId()),
                    session.getCriteriaBuilder().equal(fileTbl.get("deleteFlg"), false));
            files = session.createQuery(fileCriteria).getResultList();

            targetFileList.addAll(files);

            // directories
            List<Directory> dirs;
            CriteriaQuery<Directory> subDirCriteria = session.getCriteriaBuilder().createQuery(Directory.class);
            Root<Directory> subDirTbl = subDirCriteria.from(Directory.class);
            subDirCriteria.select(subDirTbl);
            subDirCriteria.where(session.getCriteriaBuilder().equal(subDirTbl.get("directory").get("id"), directory.getId()),
                    session.getCriteriaBuilder().equal(subDirTbl.get("deleteFlg"), false));
            dirs = session.createQuery(subDirCriteria).getResultList();

            if (!dirs.isEmpty()) {
                getFolderContent(session, dirs, targetFileList, emptyDirList);
            }

            if (files.isEmpty() && dirs.isEmpty()) {
                emptyDirList.add(directory);
            }
        }
    }

    /**
     * The specified files are combined into a ZIP file.
     * @param fileList specified files
     * @return ZIP file converted to binary.
     * @throws IOException
     */
    private byte[] zipFiles(List<File> fileList) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ZipOutputStream zos = new ZipOutputStream(baos);) {
            byte bytes[] = new byte[2048];

            for (File file : fileList) {
                try (InputStream is = new ByteArrayInputStream(file.getContent()); BufferedInputStream bis = new BufferedInputStream(is);) {

                    zos.putNextEntry(new ZipEntry(file.getName() + "." + file.getExtension()));

                    int bytesRead;
                    while ((bytesRead = bis.read(bytes)) != -1) {
                        zos.write(bytes, 0, bytesRead);
                    }
                    zos.closeEntry();
                    bis.close();
                    is.close();
                }
            }
            zos.flush();
            baos.flush();
            // *.close method is needed for extracting the download file successfully.
            zos.close();
            baos.close();
            return baos.toByteArray();
        }
    }

    /**
     * The specified files and folders are combined into a Zip file.
     * @param fileList List of files to put in the zip file
     * @param emptyDirList List of empty folders to put in the zip file
     * @param folderName zip file name
     * @return ZIP file converted to binary.
     * @throws IOException
     */
    private byte[] folderZipFiles(List<File> fileList, List<Directory> emptyDirList, String folderName) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ZipOutputStream zos = new ZipOutputStream(baos);) {
            byte bytes[] = new byte[2048];

            for (File f : fileList) {
                try (InputStream is = new ByteArrayInputStream(f.getContent()); BufferedInputStream bis = new BufferedInputStream(is);) {

                    String fullPath = f.getFullPath();
                    String path = "";
                    if (fullPath.indexOf(folderName) == -1) {
                        path = fullPath.substring(fullPath.indexOf("/") + 1);
                    } else {
                        path = fullPath.substring(fullPath.indexOf(folderName));
                    }
                    zos.putNextEntry(new ZipEntry(path));
                    int bytesRead;
                    while ((bytesRead = bis.read(bytes)) != -1) {
                        zos.write(bytes, 0, bytesRead);
                    }
                    zos.closeEntry();
                    bis.close();
                    is.close();
                }
            }
            for (Directory d : emptyDirList) {

                String fullPath = d.getFullpath();
                String path = "";
                if (fullPath.indexOf(folderName) == -1) {
                    path = fullPath.substring(fullPath.indexOf("/") + 1);
                } else {
                    path = fullPath.substring(fullPath.indexOf(folderName));
                }
                zos.putNextEntry(new ZipEntry(path + "/"));
                zos.closeEntry();
            }

            zos.flush();
            baos.flush();
            // *.close method is needed for extracting the download file successfully.
            zos.close();
            baos.close();
            return baos.toByteArray();
        }
    }

    /**
     * Combines the specified project into a Zip file.
     * @param session hibernate Session information
     * @param projectList List of projects to zip into a zip file
     * @param folderName zip file name
     * @return ZIP file converted to binary.
     * @throws IOException
     */
    private byte[] projectZipFiles(Session session, List<ProjectInfo> projectList, String folderName) throws IOException {
        System.out.println(folderName);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ZipOutputStream zos = new ZipOutputStream(baos);) {
            byte bytes[] = new byte[2048];
            List<String> projectNames = new ArrayList<>();
            for (ProjectInfo project : projectList) {
                // If project names are duplicated, number them.
                AtomicInteger idx = new AtomicInteger(1);
                StringBuilder projectName = new StringBuilder();
                projectName.append(project.getName());
                while (projectNames.contains(projectName.toString())) {
                    projectName.setLength(0);
                    projectName.append(project.getName()).append(" (").append(idx.getAndIncrement()).append(")");
                }
                projectNames.add(projectName.toString());

                // create properties file
                String lineCd = System.getProperty("line.separator");
                StringBuilder sb = new StringBuilder();
                sb.append("description=").append(StringUtils.defaultString(project.getDescription())).append(lineCd);
                sb.append("encodingtype=").append(project.getEncodingType()).append(lineCd);
                sb.append("imageheight=").append(project.getImageHeight()).append(lineCd);
                sb.append("imagewidth=").append(project.getImageWidth()).append(lineCd);
                sb.append("name=").append(project.getName()).append(lineCd);
                try (InputStream is = new ByteArrayInputStream(sb.toString().getBytes()); BufferedInputStream bis = new BufferedInputStream(is);) {
                    zos.putNextEntry(new ZipEntry(projectName.toString() + "/Project.properties"));
                    int bytesRead;
                    while ((bytesRead = bis.read(bytes)) != -1) {
                        zos.write(bytes, 0, bytesRead);
                    }
                    zos.closeEntry();
                    bis.close();
                    is.close();
                }

                // create image file
                if (project.getImageName() != null && !project.getImageName().isEmpty()) {
                    byte[] b = ArrayUtils.toPrimitive(project.getImage());
                    try (InputStream is = new ByteArrayInputStream(b); BufferedInputStream bis = new BufferedInputStream(is);) {
                        zos.putNextEntry(new ZipEntry(projectName.toString() + "/" + project.getImageName()));
                        int bytesRead;
                        while ((bytesRead = bis.read(bytes)) != -1) {
                            zos.write(bytes, 0, bytesRead);
                        }
                        zos.closeEntry();
                        bis.close();
                        is.close();
                    }
                }

                // create directory and file
                CriteriaQuery<Directory> dirCriteria = session.getCriteriaBuilder().createQuery(Directory.class);
                Root<Directory> dirTbl = dirCriteria.from(Directory.class);
                dirCriteria.select(dirTbl);
                dirCriteria.where(session.getCriteriaBuilder().equal(dirTbl.get("id"), project.getRootDirId()), session.getCriteriaBuilder().equal(dirTbl.get("deleteFlg"), false));
                Directory targetDir = session.createQuery(dirCriteria).getSingleResult();

                List<File> targetFileList = new ArrayList<File>();
                List<Directory> emptyDirList = new ArrayList<Directory>();
                getFolderContent(session, new ArrayList<>(Arrays.asList(targetDir)), targetFileList, emptyDirList);

                for (File f : targetFileList) {
                    try (InputStream is = new ByteArrayInputStream(f.getContent()); BufferedInputStream bis = new BufferedInputStream(is);) {

                        String fullPath = f.getFullPath();
                        String path = projectName.toString() + "/Project/";
                        if (fullPath.indexOf(folderName) == -1) {
                            path += fullPath.substring(fullPath.indexOf("/") + 1);
                        } else {
                            path += fullPath.substring(fullPath.indexOf(folderName));
                        }
                        zos.putNextEntry(new ZipEntry(path));
                        int bytesRead;
                        while ((bytesRead = bis.read(bytes)) != -1) {
                            zos.write(bytes, 0, bytesRead);
                        }
                        zos.closeEntry();
                        bis.close();
                        is.close();
                    }
                }
                for (Directory d : emptyDirList) {
                    if (d.equals(targetDir)) {
                        continue;
                    }
                    String fullPath = d.getFullpath();
                    String path = projectName.toString() + "/Project/";
                    if (fullPath.indexOf(folderName) == -1) {
                        path += fullPath.substring(fullPath.indexOf("/") + 1);
                    } else {
                        path += fullPath.substring(fullPath.indexOf(folderName));
                    }
                    zos.putNextEntry(new ZipEntry(path + "/"));
                    zos.closeEntry();
                }
            }
            zos.flush();
            baos.flush();
            // *.close method is needed for extracting the download file successfully.
            zos.close();
            baos.close();
            return baos.toByteArray();
        }
    }
}
