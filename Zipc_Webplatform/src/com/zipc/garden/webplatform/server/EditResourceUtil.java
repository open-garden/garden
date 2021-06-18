package com.zipc.garden.webplatform.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.vocabulary.XSD;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.arc.ARCFactory;
import com.zipc.garden.model.bp.BPFactory;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.bps.BPSFactory;
import com.zipc.garden.model.bps.BPSRoot;
import com.zipc.garden.model.cb.CBFactory;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.core.COREFactory;
import com.zipc.garden.model.core.Reference;
import com.zipc.garden.model.cscs.CSCSFactory;
import com.zipc.garden.model.cscs.CSCSPattern;
import com.zipc.garden.model.cscs.CSCSRoot;
import com.zipc.garden.model.fm.FMFactory;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMRoot;
import com.zipc.garden.model.fmc.FMCFactory;
import com.zipc.garden.model.fsm.FSMDRegion;
import com.zipc.garden.model.fsm.FSMDStateMachine;
import com.zipc.garden.model.fsm.FSMFactory;
import com.zipc.garden.model.scd.SCDFactory;
import com.zipc.garden.model.scs.SCSFactory;
import com.zipc.garden.model.scs.SCSPattern;
import com.zipc.garden.model.scs.SCSPatternReference;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.model.scs.SCSSetting;
import com.zipc.garden.model.spql.SPQLFactory;
import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCRoot;
import com.zipc.garden.model.tp.TPFactory;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tps.TPSFactory;
import com.zipc.garden.model.tps.TPSPatternElement;
import com.zipc.garden.model.tps.TPSRoot;
import com.zipc.garden.webplatform.dao.CSCSPatternDAO;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.Directory;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.FileHistory;
import com.zipc.garden.webplatform.dao.FileReferences;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.dao.JobStatus;
import com.zipc.garden.webplatform.dao.Project;
import com.zipc.garden.webplatform.dao.ProjectUsers;
import com.zipc.garden.webplatform.dao.ProjectUsers.ProjectUsersPK;
import com.zipc.garden.webplatform.dao.SCSPatternDAO;
import com.zipc.garden.webplatform.dao.UserMaster;
import com.zipc.garden.webplatform.dao.rdf.RDFUtil;
import com.zipc.garden.webplatform.dao.rdf.ontology.BPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.FPO;
import com.zipc.garden.webplatform.dao.rdf.ontology.GBO;
import com.zipc.garden.webplatform.dao.rdf.ontology.SCSO;
import com.zipc.garden.webplatform.server.dao.accessor.BehaviorPatternAccessor;
import com.zipc.garden.webplatform.server.dao.accessor.FeaturePatternAccessor;
import com.zipc.garden.webplatform.server.dao.rdf.QueryBuilder;
import com.zipc.garden.webplatform.server.logic.ProjectServiceLogic;
import com.zipc.garden.webplatform.shared.CharaCode;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.PatternElementInfo;
import com.zipc.garden.webplatform.shared.SearchResult;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.resource.VMDirectory;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * A class that summarizes server-side processing related to model view
 */
public class EditResourceUtil {

    /** File update history is managed up to this number */
    private static final int HISTORY_COUNT = 30;

    /** An instance of this class */
    public static EditResourceUtil INSTANCE = new EditResourceUtil();

    /**
     * Searches the file table based on the specified file id.<br>
     * Store the search result in VMFile class and get it.
     * @param fileId specified file id
     * @return VMFile
     * @throws IllegalArgumentException
     */
    public VMFile getVMFile(long fileId) throws IllegalArgumentException {
        VMFile result = null;

        try (Session session = DAOUtils.getSessionFactory().openSession()) {

            List<VMFile> files;
            CriteriaQuery<VMFile> criteria = session.getCriteriaBuilder().createQuery(VMFile.class);
            Root<File> fileTbl = criteria.from(File.class);
            criteria.select(session.getCriteriaBuilder().construct(VMFile.class, fileTbl.get("id"), fileTbl.get("name"), fileTbl.get("extension"), fileTbl.get("fullPath"),
                    fileTbl.get("uuid"), fileTbl.get("deleteFlg"), fileTbl.get("hash")));
            criteria.where(session.getCriteriaBuilder().equal(fileTbl.get("id"), fileId), session.getCriteriaBuilder().equal(fileTbl.get("deleteFlg"), false));
            files = session.createQuery(criteria).getResultList();

            if (files.size() > 0) {
                result = files.get(0);
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }

        return result;
    }

    /**
     * Searches the file table based on the specified file UUID or file id, project ID.<br>
     * Store the search result in VMFile class and get it.
     * @param fileId specified file UUID or file id
     * @param projectId specified project Id
     * @return VMFile
     * @throws IllegalArgumentException
     */
    public VMFile getVMFile(String fileId, long projectId) throws IllegalArgumentException {
        return getVMFile(fileId, projectId, false);
    }

    /**
     * Searches the file table based on the specified file UUID or file id, project ID, and deletion flag.<br>
     * Store the search result in VMFile class and get it.
     * @param fileId specified file UUID or file id
     * @param projectId specified project Id
     * @param deleteFlg If true, you will get deleted files.<br>
     *            If the delete flag is null, the search condition of the delete flag is not set
     * @return VMFile
     * @throws IllegalArgumentException
     */
    public VMFile getVMFile(String fileId, long projectId, Boolean deleteFlg) throws IllegalArgumentException {
        VMFile result = null;
        if (isDigit(fileId)) {
            result = getVMFile(Long.parseLong(fileId));
        } else {
            try (Session session = DAOUtils.getSessionFactory().openSession()) {
                CriteriaQuery<VMFile> criteria = session.getCriteriaBuilder().createQuery(VMFile.class);
                Root<File> fileTbl = criteria.from(File.class);
                criteria.select(session.getCriteriaBuilder().construct(VMFile.class, fileTbl.get("id"), fileTbl.get("name"), fileTbl.get("extension"), fileTbl.get("fullPath"),
                        fileTbl.get("uuid"), fileTbl.get("deleteFlg"), fileTbl.get("hash")));
                List<Predicate> preList = new ArrayList<Predicate>();
                preList.add(session.getCriteriaBuilder().equal(fileTbl.get("uuid"), fileId));
                preList.add(session.getCriteriaBuilder().equal(fileTbl.get("projectid"), projectId));
                if (deleteFlg != null) {
                    preList.add(session.getCriteriaBuilder().equal(fileTbl.get("deleteFlg"), deleteFlg));
                }
                criteria.where(preList.toArray(new Predicate[preList.size()]));
                criteria.orderBy(session.getCriteriaBuilder().desc(fileTbl.get("updateTime")));

                List<VMFile> files = session.createQuery(criteria).getResultList();

                if (files.size() > 0) {
                    result = files.get(0);
                }
            } catch (Throwable e) {
                throw new IllegalArgumentException(e);
            }
        }
        return result;
    }

    /**
     * The file table is searched based on the specified file ID, and records are acquired.
     * @param fileId specified file id
     * @return File or null.
     */
    public File getFile(long fileId) {
        return getFile(fileId, false);
    }

    /**
     * The file table is searched based on the specified file ID and deletion flag, and records are acquired.
     * @param fileId specified file id
     * @param deleteFlag File deletion flag. If true, you will get deleted files.
     * @return File or null.
     */
    public File getFile(long fileId, boolean deleteFlag) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            CriteriaQuery<File> criteria = session.getCriteriaBuilder().createQuery(File.class);
            Root<File> fileTbl = criteria.from(File.class);
            criteria.select(fileTbl);
            criteria.where(session.getCriteriaBuilder().equal(fileTbl.get("id"), fileId), session.getCriteriaBuilder().equal(fileTbl.get("deleteFlg"), deleteFlag));
            List<File> files = session.createQuery(criteria).getResultList();

            if (!files.isEmpty()) {
                return files.get(0);
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        return null;
    }

    /**
     * The file table is searched based on the specified file UUID and project ID, and records are retrieved.
     * @param fileUuid specified file uuid
     * @param projectId specified project id
     * @return File or null.
     */
    public File getFile(String fileUuid, long projectId) {
        return getFile(fileUuid, projectId, false);
    }

    /**
     * Searches the file table based on the specified file UUID, project ID and delete flag and retrieves a record.
     * @param fileUuid specified file uuid
     * @param projectId specified project id
     * @param deleteFlag File deletion flag. If true, you will get deleted files.
     * @return File or null.
     */
    public File getFile(String fileUuid, long projectId, Boolean deleteFlag) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            CriteriaQuery<File> criteria = session.getCriteriaBuilder().createQuery(File.class);
            Root<File> fileTbl = criteria.from(File.class);
            criteria.select(fileTbl);
            List<Predicate> preList = new ArrayList<Predicate>();
            preList.add(session.getCriteriaBuilder().equal(fileTbl.get("uuid"), fileUuid));
            preList.add(session.getCriteriaBuilder().equal(fileTbl.get("projectid"), projectId));
            if (deleteFlag != null) {
                preList.add(session.getCriteriaBuilder().equal(fileTbl.get("deleteFlg"), deleteFlag));
            }
            criteria.where(preList.toArray(new Predicate[preList.size()]));
            criteria.orderBy(session.getCriteriaBuilder().desc(fileTbl.get("updateTime")));
            List<File> files = session.createQuery(criteria).getResultList();

            if (!files.isEmpty()) {
                return files.get(0);
            }
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        return null;
    }

    /**
     * Searches the file table based on the specified file ID to get the "content" data.
     * @param fileId specified file Id
     * @return file's content.
     * @throws IllegalArgumentException
     */
    public byte[] getFileContent(long fileId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            byte[] ret = null;
            File targetFile = session.byId(File.class).load(fileId);
            ret = targetFile.getContent();
            // XtextのコンテントはBinaryではありませんので、エラー起きる
            // convertToRootElement(ret);
            return ret;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Searches the file table based on the specified file UUID and project ID to get the "content" data.
     * @param uuid specified file uuid
     * @param projectId The specified project Id
     * @return file's content. Null if the record does not exist
     * @throws IllegalArgumentException
     */
    public byte[] getFileContent(String uuid, long projectId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {

            CriteriaQuery<File> criteria = session.getCriteriaBuilder().createQuery(File.class);
            Root<File> fileTbl = criteria.from(File.class);
            criteria.select(fileTbl);
            criteria.where(session.getCriteriaBuilder().equal(fileTbl.get("uuid"), uuid), session.getCriteriaBuilder().equal(fileTbl.get("projectid"), projectId),
                    session.getCriteriaBuilder().isFalse(fileTbl.get("deleteFlg")));

            List<File> files = session.createQuery(criteria).getResultList();
            if (!files.isEmpty()) {
                return files.get(0).getContent();
            }
            return null;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Search the file table based on the specified file Id and get the ID of the directory where the target file is located.
     * @param fileId file id
     * @return ID of the directory where the file is located
     * @throws IllegalArgumentException
     */
    public long getDirId(long fileId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            long ret;
            File targetFile = session.byId(File.class).load(fileId);
            ret = targetFile.getDirectory().getId();
            return ret;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Adds a record to the project table based on the specified arguments.
     * @param projectName Name of project to create
     * @param userId ID of the user who created the project
     * @return Root directory of created project
     */
    public Directory createProject(String projectName, Long userId) {
        return createProject(projectName, userId, null, CharaCode.UTF8.getName(), null, null, 0, 0);
    }

    /**
     * Adds a record to the project table based on the specified arguments.
     * @param projectName Name of project to create
     * @param userId ID of the user who created the project
     * @param description Description of the project to be created
     * @param encodingtype Encoding type of project to be created
     * @param image Image of project to be created (byte array)
     * @param imageName Image name of the project to be created
     * @param imageHeight Image Height
     * @param imageWidth Image Width
     * @return Root directory of created project
     */
    public Directory createProject(String projectName, Long userId, String description, String encodingtype, Byte[] image, String imageName, int imageHeight, int imageWidth) {
        Transaction tx = null;

        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Directory dir = new Directory();
            dir.setName("root");
            session.persist(dir);

            Project project = new Project();
            project.setName(projectName);
            project.setDirectory(dir);
            project.setDescription(description);
            project.setEncodingType(encodingtype);
            project.setImage(image);
            project.setImageName(imageName);
            project.setImageHeight(imageHeight);
            project.setImageWidth(imageWidth);
            session.persist(project);

            dir.setProjectid(project.getId());
            session.save(dir);

            if (userId != null) {
                ProjectUsers pUsers = new ProjectUsers();
                pUsers.setPk(new ProjectUsersPK(new UserMaster(userId), project));
                session.persist(pUsers);
            }

            tx.commit();
            return dir;
        } catch (Throwable e) {
            if (tx != null)
                tx.rollback();
            throw e;
        }
    }

    /**
     * Adds a new record to the file table based on the given arguments.
     * @param parentId The directory id where the created file will be placed
     * @param file Information about the file to be created
     * @param refIdList ID of the reference file of the file to be created
     * @param userInfo Information of user who is logged in
     * @return ID of created file
     * @throws IllegalArgumentException
     */
    public Long createFile(long parentId, VMFile file, List<Long> refIdList, UserInfo userInfo) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                File targetFile = new File();
                Directory targetDir = session.byId(Directory.class).load(parentId);
                List<File> refFileList = null;
                Map<String, File> refFileMap = null;
                if (refIdList != null && refIdList.size() > 0) {
                    CriteriaQuery<File> criteria = session.getCriteriaBuilder().createQuery(File.class);
                    Root<File> fileTbl = criteria.from(File.class);
                    criteria.select(fileTbl);
                    criteria.where(fileTbl.get("id").in(refIdList), session.getCriteriaBuilder().isFalse(fileTbl.get("deleteFlg")));
                    refFileList = session.createQuery(criteria).getResultList();
                    refFileMap = refFileList.stream().collect(Collectors.toMap(s -> s.getUuid(), s -> s));
                }

                // create file
                targetFile.setName(file.getName());
                targetFile.setExtension(file.getExtensionStr());

                if (targetDir != null) {
                    targetFile.setDirectory(targetDir);
                    targetFile.setProjectid(getProjectId(targetDir, session));
                }
                String uuid = generateUUID();
                targetFile.setUuid(uuid);
                session.persist(targetFile);

                AbstractRootElement root = makeFile(targetFile, refFileList);
                if (root != null) {
                    root.setId(uuid);
                }
                setContentData(targetFile, root, refFileMap, session);

                targetFile = setFullPathName(file.getId(), targetFile.getName(), targetFile, session);
                targetFile.setCreateUser(userInfo.getUserId());
                targetFile.setCreateTime(new Timestamp(System.currentTimeMillis()));
                targetFile.setUpdateUser(userInfo.getUserId());
                targetFile.setUpdateTime(targetFile.getCreateTime());
                String hashCode = returnHashCodeFromFile(targetFile);
                targetFile.setHash(hashCode);
                session.save(targetFile);
                tx.commit();
                return targetFile.getId();
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Generate and get the file UUID.
     * @return UUID
     */
    private String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     * The argument "data" is reflected in the "Content" of the record of the file table that matches the specified file ID.<br>
     * Also, the file history table will be updated.
     * @param fileId specified file ID
     * @param data EMF root model binary information
     * @param userInfo Information of user who is logged in
     * @throws IllegalArgumentException
     */
    public void saveFile(long fileId, byte[] data, UserInfo userInfo) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                // 対象のファイルを検索する
                File targetFile = session.byId(File.class).load(fileId);

                // save File data
                targetFile.setContent(data);
                saveFile(targetFile, session, userInfo);
                tx.commit();
            } catch (Throwable e) {
                e.printStackTrace();
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * The file table is searched based on the specified project ID and exetensions.<br>
     * A fuzzy search is performed for the FM node name (or FSMState name) of the searched file and the result is obtained.
     * @param projectId Specified project ID
     * @param extensions Specified file extensions
     * @param keyWord Keywords used for fuzzy search of FM node name (or FSMState name)
     * @return search results
     * @throws IllegalArgumentException
     */
    public List<SearchResult> searchModel(long projectId, List<String> extensions, String keyWord) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                List<SearchResult> ret = new ArrayList<SearchResult>();
                if (extensions.isEmpty()) {
                    return ret;
                }
                tx = session.beginTransaction();
                CriteriaQuery<File> criteria = session.getCriteriaBuilder().createQuery(File.class);
                Root<File> fileTbl = criteria.from(File.class);
                CriteriaBuilder builder = session.getCriteriaBuilder();
                criteria.select(fileTbl);
                List<Predicate> preList = new ArrayList<Predicate>();
                preList.add(fileTbl.get("extension").in(extensions));
                preList.add(builder.isFalse(fileTbl.get("deleteFlg")));
                preList.add(builder.equal(fileTbl.get("projectid"), projectId));
                criteria.where(preList.toArray(new Predicate[preList.size()]));
                List<File> retData = session.createQuery(criteria).getResultList();
                SearchModelUtil searchModelUtil = new SearchModelUtil();
                ret = searchModelUtil.createResultListToSearchViewData(retData, keyWord);
                tx.commit();
                return ret;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * The file table is searched based on the specified project ID and keyword.<br>
     * The searched file information is acquired.
     * @param projectId Specified project ID
     * @param keyWord Specified keyword (used in fuzzy search for file name)
     * @return VMFile list
     * @throws IllegalArgumentException
     */
    public List<VMFile> searchFile(long projectId, String keyWord) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                List<VMFile> ret = new ArrayList<VMFile>();
                if (keyWord.isEmpty()) {
                    return ret;
                }
                tx = session.beginTransaction();
                CriteriaQuery<VMFile> criteria = session.getCriteriaBuilder().createQuery(VMFile.class);
                Root<File> fileTbl = criteria.from(File.class);
                CriteriaBuilder builder = session.getCriteriaBuilder();
                criteria.select(builder.construct(VMFile.class, fileTbl.get("id"), fileTbl.get("name"), fileTbl.get("extension"), fileTbl.get("fullPath"), fileTbl.get("uuid"),
                        fileTbl.get("deleteFlg"), fileTbl.get("hash")));
                List<Predicate> preList = new ArrayList<Predicate>();
                preList.add(builder.isFalse(fileTbl.get("deleteFlg")));
                preList.add(builder.like(fileTbl.get("name"), "%" + keyWord.replace("*", "%").replace("?", "_") + "%"));
                preList.add(builder.equal(fileTbl.get("projectid"), projectId));
                criteria.where(preList.toArray(new Predicate[preList.size()]));
                ret = session.createQuery(criteria).getResultList();
                tx.commit();
                return ret;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Recursively searches the upper directory from the directory in which the specified file is stored, and obtains each
     * directory ID.
     * @param targetId file id
     * @return Directory ID
     */
    public List<Long> getTargetToRootDirIds(long targetId) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                File target = session.byId(File.class).load(targetId);
                List<Long> resources = new ArrayList<Long>();
                StringBuilder queryStr = new StringBuilder();
                queryStr.append("WITH RECURSIVE r AS ( SELECT * FROM directory WHERE id = ? ");
                queryStr.append(" UNION ALL SELECT directory.* FROM directory, r WHERE directory.id = r.parentdirid ) SELECT id FROM r ORDER BY id;");
                @SuppressWarnings("unchecked")
                Query<Object[]> q = session.createNativeQuery(queryStr.toString());
                q.setParameter(1, target.getDirectory().getId());
                List<Object[]> targets = q.getResultList();
                for (Object columns : targets) {
                    resources.add(((BigInteger) columns).longValue());
                }
                tx.commit();
                return resources;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Record is added to the File table based on the contents specified in the argument.
     * @param parentId ID of the directory where the file will be placed
     * @param file Information such as the name and extension of the uploaded file
     * @param content Binary information of uploaded file
     * @param userInfo Information of user who is logged in
     * @return file id
     */
    synchronized public Long uploadFile(long parentId, VMFile file, byte[] content, UserInfo userInfo) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                File targetFile = new File();
                EnumSet<Extension> extensionSet = EnumSet.allOf(Extension.class);
                Extension ext = file.getExtension();
                AbstractRootElement root = null;

                Directory targetDir = session.byId(Directory.class).load(parentId);
                long projectId = getProjectId(targetDir, session);
                String extension = file.getExtensionStr();

                if (extensionSet.contains(ext) && ext != Extension.TXT && ext != Extension.CSC) {
                    root = convertToRootElement(content);
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<File> criteria = builder.createQuery(File.class);
                    Root<File> fileTbl = criteria.from(File.class);

                    List<Predicate> preList = new ArrayList<Predicate>();
                    preList.add(builder.equal(fileTbl.get("uuid"), root.getId()));
                    preList.add(builder.isFalse(fileTbl.get("deleteFlg")));
                    preList.add(builder.equal(fileTbl.get("projectid"), projectId));
                    criteria.select(fileTbl).where(preList.toArray(new Predicate[preList.size()]));
                    List<File> files = session.createQuery(criteria).getResultList();
                    if (!files.isEmpty()) {
                        tx.rollback();
                        return -1L;
                    }
                    targetFile.setUuid(root.getId());
                }

                targetFile.setDirectory(targetDir);
                targetFile.setProjectid(projectId);
                targetFile.setExtension(extension);
                targetFile.setContent(content);
                targetFile.setName(file.getName());
                targetFile = setFullPathName(file.getId(), targetFile.getName(), targetFile, session);
                targetFile.setCreateUser(userInfo.getUserId());
                targetFile.setCreateTime(new Timestamp(System.currentTimeMillis()));
                targetFile.setUpdateUser(userInfo.getUserId());
                targetFile.setUpdateTime(targetFile.getCreateTime());
                String hashCode = returnHashCodeFromFile(targetFile);
                targetFile.setHash(hashCode);
                session.persist(targetFile);
                if (extensionSet.contains(ext) && ext != Extension.TXT && ext != Extension.CSC) {
                    setReferences(targetFile, root.getRefs(), session);
                }

                tx.commit();
                return targetFile.getId();
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Get all records in the job table.
     * @return All records in the job table
     */
    public List<Job> getAllJobInfo() {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
            Root<Job> jobTbl = criteria.from(Job.class);
            criteria.select(jobTbl);
            List<Job> jobs = session.createQuery(criteria).getResultList();
            return jobs;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Gets the record that matches the specified input fileID and job type from the Job table.
     * @param inputFileId specified input File Id
     * @param jobType specified job Type
     * @return Record of Job table.
     */
    public Job getJobInfoByInputFileIdAndType(long inputFileId, int jobType) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
            Root<Job> jobTbl = criteria.from(Job.class);
            criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("inputFileId"), inputFileId), session.getCriteriaBuilder().equal(jobTbl.get("type"), jobType));
            List<Job> jobs = session.createQuery(criteria).getResultList();
            if (jobs.isEmpty()) {
                return null;
            }
            return jobs.get(0);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Gets the record that matches the specified output fileID from the Job table.
     * @param outputFileId specified output File Id
     * @return Record of Job table.
     */
    public Job getJobInfoByOutputFileId(long outputFileId) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
            Root<Job> jobTbl = criteria.from(Job.class);
            criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("outputFileId"), outputFileId));
            List<Job> jobs = session.createQuery(criteria).getResultList();
            if (jobs.isEmpty()) {
                return null;
            }
            return jobs.get(0);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Gets the record that matches the specified job ID from the Job table.
     * @param jobId specified job id
     * @return Record of Job table. Null if not present
     */
    public Job getJobInfo(long jobId) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
            Root<Job> jobTbl = criteria.from(Job.class);
            criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("id"), jobId));
            List<Job> jobs = session.createQuery(criteria).getResultList();
            if (jobs.isEmpty()) {
                return null;
            }
            return jobs.get(0);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * The Job table is searched for a record that matches the specified job ID, and the input file id is obtained.
     * @param jobId specified job id
     * @return input file id
     */
    public long getJobInputFileId(long jobId) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
            Root<Job> jobTbl = criteria.from(Job.class);
            criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("id"), jobId));
            List<Job> jobs = session.createQuery(criteria).getResultList();
            return jobs.get(0).getInputFileId();
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Get the record that matches the specified file ID and hash value from the JobStatus table.
     * @param fileId specified file ID
     * @param hash specified hash value.
     * @return Record of JobStatus table
     */
    public JobStatus getJobStatusByInputFileId(long fileId, String hash) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();

                CriteriaQuery<JobStatus> criteria = builder.createQuery(JobStatus.class);
                Root<JobStatus> jobStatusTbl = criteria.from(JobStatus.class);
                ArrayList<Predicate> preList = new ArrayList<Predicate>();
                preList.add(builder.equal(jobStatusTbl.get("file"), fileId));
                preList.add(builder.equal(jobStatusTbl.get("hash"), hash));
                criteria.where(preList.toArray(new Predicate[preList.size()]));
                List<JobStatus> jobStatusList = session.createQuery(criteria).getResultList();
                if (!jobStatusList.isEmpty()) {
                    return jobStatusList.get(0);
                }
                return null;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Updates the status of the record in the job status table that matches the specified job ID.
     * @param jobId job id
     * @param status Job status update contents
     * @return Update successful: True / No target data: False
     */
    public boolean updateJobStatus(long jobId, int status) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
                Root<Job> jobTbl = criteria.from(Job.class);
                criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("id"), jobId));
                List<Job> jobs = session.createQuery(criteria).getResultList();
                if (jobs.isEmpty()) {
                    return false;
                }
                // memo: ↑と↓って，同じことしてない？
                // memo: もともと，outputFileId で取得して，idを特定して全体を取り直していたようだが，今はjobIdが渡されるので，↓だけでいいと思われる．
                // TODO 修正する(updateJobStateus(long, int, String) も)
                Long id = jobs.get(0).getId();
                Job job = session.byId(Job.class).load(id);
                job.setStatus(status);
                session.save(job);
                tx.commit();
                return true;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Updates the status and information of the record in the job status table that matches the specified file ID.
     * @param fileId specified file ID.
     * @param status Job status update contents
     * @param infomation Job information update contents
     * @return Update successful: True
     */
    public boolean updateJobStatusByInputFileId(long fileId, int status, String infomation) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                File file = getFile(fileId);
                tx = session.beginTransaction();
                CriteriaQuery<JobStatus> criteria = session.getCriteriaBuilder().createQuery(JobStatus.class);
                Root<JobStatus> jobTbl = criteria.from(JobStatus.class);
                criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("file"), fileId), session.getCriteriaBuilder().equal(jobTbl.get("hash"), file.getHash()));
                List<JobStatus> jobStatusList = session.createQuery(criteria).getResultList();

                JobStatus jobStatus;
                if (jobStatusList == null || jobStatusList.isEmpty()) {
                    jobStatus = new JobStatus();
                    jobStatus.setFile(file);
                } else {
                    jobStatus = jobStatusList.get(0);
                }
                jobStatus.setHash(file.getHash());
                if (status != -1) {
                    jobStatus.setStatus(status);
                }

                jobStatus.setInfomation(infomation);
                session.save(jobStatus);
                tx.commit();
                return true;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Updates the message for the record in the JOB table that matches the specified job ID.
     * @param jobId The specified job ID
     * @param message Job message update contents
     * @return Update successful: True / No target data: False
     */
    public boolean updateJobProgress(long jobId, String message) {
        Transaction tx = null;
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
            Root<Job> jobTbl = criteria.from(Job.class);
            criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("id"), jobId));
            List<Job> jobs = session.createQuery(criteria).getResultList();
            if (jobs.isEmpty()) {
                return false;
            }
            Long id = jobs.get(0).getId();
            Job job = session.byId(Job.class).load(id);
            job.setStepProgressMessage(message);
            session.save(job);
            tx.commit();
            return true;
        } catch (Throwable e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Updates the JOB status and message of the record in the JOB table that matches the specified job Id.
     * @param jobId The specified job ID
     * @param status Job status update contents
     * @param message Job message update contents
     * @return Update successful: True / No target data: False
     */
    public boolean updateJobStatus(long jobId, int status, String message) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
                Root<Job> jobTbl = criteria.from(Job.class);
                criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("id"), jobId));
                List<Job> jobs = session.createQuery(criteria).getResultList();
                if (jobs.isEmpty()) {
                    return false;
                }
                Long id = jobs.get(0).getId();
                Job job = session.byId(Job.class).load(id);
                job.setStatus(status);
                job.setMessage(message);
                session.save(job);
                tx.commit();
                return true;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Adds a record to the Job table with the specified argument contents.
     * @param inputFileId ID of the file for which the job was executed
     * @param inputFile A record in the table of the file in which the job was executed
     * @param outputFileId File ID created when Job is executed
     * @param type Type of Job to be executed. See the constants for the {@link Job} class.
     * @param projectId project id
     * @return Id of the created Job
     */
    public String addJob(long inputFileId, File inputFile, long outputFileId, int type, long projectId) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Job job = new Job();
                job.setInputFileId(inputFileId);
                job.setInputFile(inputFile);
                job.setOutputFileId(outputFileId);
                job.setType(type);
                job.setStatus(Job.STATUS_NOEXEC);
                job.setProjectId(projectId);
                session.save(job);
                tx.commit();
                return String.valueOf(job.getId());
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Deletes the record that matches the specified job ID from the Job table.
     * @param jobId job id
     * @return Deletion success: True / No target data: False
     */
    public boolean removeJob(long jobId) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
                Root<Job> jobTbl = criteria.from(Job.class);
                criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("id"), jobId));
                List<Job> jobs = session.createQuery(criteria).getResultList();
                if (jobs.isEmpty()) {
                    return false;
                }
                Long id = jobs.get(0).getId();
                Job job = session.byId(Job.class).load(id);
                session.remove(job);
                tx.commit();
                return true;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Delete the records in the JOB table that match the specified input file ID and Job type
     * @param fileId ID of the file that created the job
     * @param type Type of Job to be executed. See the constants for the {@link Job} class.
     */
    public boolean removeJobByInputFileIdAndType(long fileId, int type) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaQuery<Job> criteria = session.getCriteriaBuilder().createQuery(Job.class);
                Root<Job> jobTbl = criteria.from(Job.class);
                criteria.where(session.getCriteriaBuilder().equal(jobTbl.get("inputFileId"), fileId), session.getCriteriaBuilder().equal(jobTbl.get("type"), type));
                List<Job> jobs = session.createQuery(criteria).getResultList();
                if (jobs.isEmpty()) {
                    return false;
                }
                Long id = jobs.get(0).getId();
                Job job = session.byId(Job.class).load(id);
                session.remove(job);
                tx.commit();
                return true;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Obtain the pattern number from RDF and set it in the argument setting.
     * @param projectId project id
     * @param uuid fps or bps file uuid
     * @param setting Setting destination of pattern number
     * @param patternNums Scenario set setting Pattern number entered in the editor
     * @return False if the number of pattern numbers entered in the scenario set settings editor and the number of
     *         patternscreated in RDF are different.<br>
     *         True otherwise
     */
    public boolean setPattern(long projectId, String uuid, AbstractSetting setting, String[] patternNums) {
        if (setting instanceof TPSetting) {
            return setTPTSDPattern(projectId, uuid, (TPSetting) setting, patternNums);
        } else if (setting instanceof BPSetting) {
            return setBPBehaviorPattern(projectId, uuid, (BPSetting) setting, patternNums);
        }
        return false;
    }

    /**
     * Obtain the pattern info from RDF and set it in the argument tpSetting.
     * @param projectId project id
     * @param fileUuid fps file uuid
     * @param tpSetting Setting destination of pattern number
     * @param patternNums Scenario set setting Pattern number entered in the editor
     * @return False if the number of pattern numbers entered in the scenario set settings editor and the number of patterns
     *         created in RDF are different.<br>
     *         True otherwise
     */
    public boolean setTPTSDPattern(long projectId, String fileUuid, TPSetting tpSetting, String[] patternNums) {
        File file = getFile(fileUuid, projectId);
        FeaturePatternAccessor.getAllFeaturePattern(projectId, file.getId(), file.getHash(), patternNums, tpSetting);
        if (patternNums != null && patternNums.length != 0) {
            return tpSetting.getPatterns().size() == patternNums.length;
        }
        return true;
    }

    /**
     * Obtain the pattern number from RDF and set it in the argument bpSetting.
     * @param projectId project id
     * @param fileUuid bps file uuid
     * @param bpSetting Setting destination of pattern number
     * @param patternNums Scenario set setting Pattern number entered in the editor
     * @return False if the number of pattern numbers entered in the scenario set settings editor and the number of patterns
     *         created in RDF are different.<br>
     *         True otherwise
     */
    public boolean setBPBehaviorPattern(long projectId, String fileUuid, BPSetting bpSetting, String[] patternNums) {
        File file = getFile(fileUuid, projectId);
        BehaviorPatternAccessor.getAllBehaviorPattern(projectId, file.getId(), file.getHash(), bpSetting, patternNums);
        if (patternNums != null && patternNums.length != 0) {
            return bpSetting.getPattern().size() == patternNums.length;
        }
        return true;
    }

    /**
     * Get the record to be displayed in the SCS editor from RDF and set it in the argument: scsSetting.
     * @param projectId project id
     * @param startRecordOffset Reading start position
     * @param recordCount Number of reads
     * @param scsSetting Storage location of search results
     * @return True if successful
     */
    public boolean getSCSFileByRecord(long projectId, long startRecordOffset, long recordCount, SCSSetting scsSetting) {
        File scssFile = getFile(scsSetting.getUuid(), projectId);

        AbstractRootElement scssRoot = convertToRootElement(scssFile.getContent());

        // Long:fps or bps File Id, File:fp or bp File
        // TODO: If you can get the name from RDF, fix the SQL
        Map<Long, File> patternMap = new HashMap<>();
        scssRoot.getRefs().forEach(scssRef -> {
            File tpsOrBpsFile = getFile(scssRef.getRefid(), projectId);
            if (tpsOrBpsFile == null) {
                tpsOrBpsFile = getFile(scssRef.getRefid(), projectId, true);
            }
            patternMap.put(tpsOrBpsFile.getId(), tpsOrBpsFile);
        });

        try (RDFConnection conn = RDFConnectionFactory.connect(RDFUtil.fuseki_url)) {
            CBRoot cbRoot = (CBRoot) convertToRootElement(scssFile.getContent());
            int combinationCount = cbRoot.getLogic().getFile().size();

            ParameterizedSparqlString parameterizedSparql = getSCSQuery(scsSetting, scssFile, startRecordOffset, recordCount, combinationCount, false);

            org.apache.jena.query.Query query = QueryFactory.create(parameterizedSparql.asQuery());
            try (QueryExecution qe = conn.query(query)) {
                ResultSet rs = qe.execSelect();

                scsSetting.getPatterns().clear();

                List<String> patternValList = new ArrayList<String>();
                List<SCSPatternReference> ptnRefList = new ArrayList<SCSPatternReference>();
                String scsScenarioNo = String.valueOf(startRecordOffset + 1);
                String beforeNo = null;

                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();
                    scsScenarioNo = String.valueOf(qs.getLiteral("scsScenarioNo").getInt());
                    if ((beforeNo != null && !scsScenarioNo.equals(beforeNo)) || !rs.hasNext()) {
                        SCSPatternDAO ptnDAO = getSCSPatternDAO(scssFile.getId(), beforeNo);
                        SCSPattern ptn = SCSFactory.eINSTANCE.createSCSPattern();
                        ptn.setId(beforeNo);
                        if (ptnDAO != null) {
                            ptn.setLsc(ptnDAO.getLsc());
                            ptn.setCscUuid(ptnDAO.getCscUuid());
                            ptn.setCscFileName(ptnDAO.getCscFileName());
                        } else {
                            ptn.setLsc("");
                            ptn.setCscUuid("");
                            ptn.setCscFileName("");
                        }
                        if (!rs.hasNext()) {
                            Optional<Long> optRefFileId = Optional.ofNullable(qs.getLiteral("refFileId") != null ? qs.getLiteral("refFileId").getLong() : null);
                            Optional<Long> optPatternNo = Optional.ofNullable(qs.getLiteral("ptnNo") != null ? qs.getLiteral("ptnNo").getLong() : null);
                            patternValList.add(patternMap.get(optRefFileId.get()).getName() + "-" + optPatternNo.orElse(9999l));
                            SCSPatternReference ptnRef = SCSFactory.eINSTANCE.createSCSPatternReference();
                            ptnRef.setFileId(patternMap.get(optRefFileId.get()).getId());
                            ptnRef.setPatternId(optPatternNo.orElse(Long.valueOf(scsScenarioNo)));
                            ptnRefList.add(ptnRef);
                        }
                        ptn.setPatternValue(String.join(",", patternValList));
                        ptn.getPatternreferences().addAll(ptnRefList);
                        scsSetting.getPatterns().add(ptn);
                        ptnRefList.clear();
                        patternValList.clear();
                    }
                    Optional<Long> optRefFileId = Optional.ofNullable(qs.getLiteral("refFileId") != null ? qs.getLiteral("refFileId").getLong() : null);
                    Optional<Long> optPatternNo = Optional.ofNullable(qs.getLiteral("ptnNo") != null ? qs.getLiteral("ptnNo").getLong() : null);
                    patternValList.add(patternMap.get(optRefFileId.get()).getName() + "-" + optPatternNo.orElse(9999l));
                    SCSPatternReference ptnRef = SCSFactory.eINSTANCE.createSCSPatternReference();
                    ptnRef.setFileId(patternMap.get(optRefFileId.get()).getId());
                    ptnRef.setPatternId(optPatternNo.orElse(Long.valueOf(scsScenarioNo)));
                    ptnRefList.add(ptnRef);
                    beforeNo = scsScenarioNo;
                }

                scsSetting.setBegin((int) startRecordOffset);// 開始位置のOffsetを指定(0から始まる)
                scsSetting.setEnd((int) startRecordOffset + scsSetting.getPatterns().size());
            }

            parameterizedSparql = getSCSQuery(scsSetting, scssFile, recordCount, recordCount, combinationCount, true);

            query = QueryFactory.create(parameterizedSparql.asQuery());
            try (QueryExecution qe = conn.query(query)) {
                ResultSet rs = qe.execSelect();
                if (rs.hasNext()) {
                    QuerySolution qs = rs.next();
                    scsSetting.setCount(qs.getLiteral("COUNT").getInt());
                }
            }
            return true;
        } catch (Throwable e) {
            SCSRoot root = (SCSRoot) scsSetting.eContainer();
            root.setMessage(e.getMessage());
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Acquires the SPARQL syntax for scenario set data acquisition.
     * @param scsSetting A class that has the hash value when creating RDF and the pattern number to be extracted
     * @param scssFile SCSS file information
     * @param startRecordOffset Reading start position
     * @param recordCount Number of reads
     * @param combinationCount Number of Combination files
     * @param isCount True to get the number of scenario sets
     * @return SPARQL syntax
     */
    private ParameterizedSparqlString getSCSQuery(SCSSetting scsSetting, File scssFile, long startRecordOffset, long recordCount, int combinationCount, boolean isCount) {
        QueryBuilder builder;
        if (isCount) {
            builder = new QueryBuilder("SELECT (COUNT(?scsScenario) AS ?COUNT) WHERE { GRAPH ?graph { ");
        } else {
            builder = new QueryBuilder("SELECT * WHERE { GRAPH ?graph { ");
        }
        builder.append("scsFile", SCSO.refSettingFile, "scssFile");
        builder.append("?scssFile <" + GBO.hasFileId.getURI() + "> \"" + String.valueOf(scssFile.getId()) + "\"^^<" + XSD.xlong.getURI() + ">. ");
        builder.append("?scssFile <" + GBO.hasHash.getURI() + "> \"" + scsSetting.getSettingHash() + "\". ");
        builder.append("scsFile", SCSO.hasScenario, "scsScenario");
        builder.append("scsScenario", SCSO.hasScenarioNo, "scsScenarioNo");
        if (!isCount) {
            builder.append("scsScenario", SCSO.refPattern, "scsRefPtn");
        }
        if (scsSetting.getPatternNos() != null && !scsSetting.getPatternNos().isEmpty()) {
            builder.append("FILTER (xsd:long(?scsScenarioNo) IN(" + scsSetting.getPatternNos() + "))");
        }
        if (!isCount) {
            builder.append("  OPTIONAL { ");
            builder.append("?scsRefPtn ?_bpHasPatternNo | ?_fpHasPatternNo ?ptnNo. ");
            builder.append("?refFile ?_bpHasPattern | ?_fpHasPattern ?scsRefPtn. ");
            builder.append("?refFile ?_bpRefSetFile | ?_fpRefSetFile ?refSettingFile. ");
            builder.append("refSettingFile", GBO.hasFileId, "refFileId");
            builder.append("refSettingFile", GBO.hasFileName, "fileName");
            builder.append("  } ");
        }
        builder.append(" } ");
        builder.append("} ");
        if (!isCount) {
            builder.append("order by xsd:long(?scsScenarioNo) ");
            builder.append(" LIMIT ?_start OFFSET ?_end ");
        }

        ParameterizedSparqlString parameterizedSparql = builder.build();
        parameterizedSparql.setNsPrefix("xsd", XSD.getURI());
        if (!isCount) {
            parameterizedSparql.setParam("_fpHasPatternNo", FPO.hasPatternNo);
            parameterizedSparql.setParam("_bpHasPatternNo", BPO.hasPatternNo);
            parameterizedSparql.setParam("_fpHasPattern", FPO.hasPattern);
            parameterizedSparql.setParam("_bpHasPattern", BPO.hasPattern);
            parameterizedSparql.setParam("_fpRefSetFile", FPO.refSettingFile);
            parameterizedSparql.setParam("_bpRefSetFile", BPO.refSettingFile);
            parameterizedSparql.setLiteral("_start", recordCount * combinationCount);
            parameterizedSparql.setLiteral("_end", startRecordOffset * combinationCount);
        }

        return parameterizedSparql;
    }

    /**
     * Search CSCSPatternDAO table and set the obtained record in argument: cscsRoot.
     * @param projectId project id
     * @param fileId cscs file id
     * @param startRecordOffset Acquisition start position
     * @param recordCount Number of records to retrieve
     * @param cscsRoot EMF model that sets the retrieved record
     * @return True: Processing succeeded / False: Processing failed
     */
    public boolean getCSCSFileByRecord(long projectId, long fileId, long startRecordOffset, long recordCount, CSCSRoot cscsRoot) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            File targetFile = session.byId(File.class).load(fileId);
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CSCSPatternDAO> criteriaRef = builder.createQuery(CSCSPatternDAO.class);
            Root<CSCSPatternDAO> patternTable = criteriaRef.from(CSCSPatternDAO.class);
            Root<File> fileTable = criteriaRef.from(File.class);
            criteriaRef.select(patternTable);
            criteriaRef.where(builder.equal(fileTable.get("uuid"), patternTable.get("fileUuid")), builder.equal(fileTable.get("projectid"), patternTable.get("projectId")),
                    builder.isFalse(fileTable.get("deleteFlg")), builder.equal(patternTable.get("fileUuid"), targetFile.getUuid()),
                    builder.equal(patternTable.get("projectId"), projectId));
            criteriaRef.orderBy(builder.asc(patternTable.get("CSCSPatternId")));
            Query<CSCSPatternDAO> query = session.createQuery(criteriaRef);
            long count = query.getResultStream().count();
            query.setFirstResult((int) startRecordOffset).setMaxResults((int) recordCount);
            List<CSCSPatternDAO> temp = query.getResultList();
            cscsRoot.getPatterns().clear();
            for (CSCSPatternDAO dao : temp) {
                CSCSPattern cscsPattern = CSCSFactory.eINSTANCE.createCSCSPattern();
                cscsPattern.setId(dao.getCSCSPatternId().toString());
                cscsPattern.setCsc(dao.getCsc());
                cscsPattern.setPatternValue(dao.getPattern());
                cscsRoot.getPatterns().add(cscsPattern);
            }
            cscsRoot.setAll(count); // 全CSCSPattern数を設定
            cscsRoot.setBegin((int) startRecordOffset); // 開始位置のOffsetを指定(0から始まる)
            cscsRoot.setEnd((int) startRecordOffset + cscsRoot.getPatterns().size());
            return true;
        } catch (Throwable e) {
            cscsRoot.setMessage(e.getMessage());
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Deletes the data that matches the file id of the specified DAO class.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param fileId file id
     * @param classList DAO class associated with the table that issues the delete process
     */
    public void removeDBElements(Session session, long fileId, Class<?>[] classList) {
        String fileIdStr = Long.toString(fileId);
        for (Class<?> c : classList) {
            CriteriaQuery<?> criteria = session.getCriteriaBuilder().createQuery(c);
            Root<?> table = criteria.from(c);
            criteria.where(session.getCriteriaBuilder().equal(table.get("fileId"), fileIdStr));
            List<?> resultList = session.createQuery(criteria).getResultList();
            resultList.forEach(result -> session.delete(result));
        }
    }

    /**
     * Deletes the data that matches the file uuid and project id of the specified DAO class.<br>
     * From EntityManager, the deletion process will be performed collectively.
     * @param em An object that manages an entity. It is used to perform the remove processing collectively.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param fileUuid file uuid
     * @param projectId project ID
     * @param classList DAO class associated with the table that issues the delete process
     */
    private void removeDBElementsWithUuid(EntityManager em, Session session, String fileUuid, long projectId, Class<?>[] classList) {
        for (Class<?> c : classList) {
            CriteriaQuery<?> criteria = session.getCriteriaBuilder().createQuery(c);
            Root<?> table = criteria.from(c);
            criteria.where(session.getCriteriaBuilder().equal(table.get("fileUuid"), fileUuid), session.getCriteriaBuilder().equal(table.get("projectId"), projectId));
            List<?> resultList = session.createQuery(criteria).getResultList();
            DAOUtils.deleteEntity(em, resultList);
        }
    }

    /**
     * Clears the contents of specific CSCS columns in the SCSPatternDAO table.
     * @param cscsUuid .cscs file uuid to be cleared
     * @param projectId project id
     */
    private void removeCscsFromScs(String cscsUuid, long projectId) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                CriteriaQuery<SCSPatternDAO> criteria = session.getCriteriaBuilder().createQuery(SCSPatternDAO.class);
                Root<SCSPatternDAO> scsPatternDaoTbl = criteria.from(SCSPatternDAO.class);
                Root<CSCSPatternDAO> cscsPatternDaoTbl = criteria.from(CSCSPatternDAO.class);
                criteria.select(scsPatternDaoTbl);
                criteria.where(session.getCriteriaBuilder().equal(scsPatternDaoTbl.get("projectId"), cscsPatternDaoTbl.get("projectId")),
                        session.getCriteriaBuilder().equal(scsPatternDaoTbl.get("cscUuid"), cscsPatternDaoTbl.get("fileUuid")),
                        session.getCriteriaBuilder().equal(cscsPatternDaoTbl.get("projectId"), projectId),
                        session.getCriteriaBuilder().equal(cscsPatternDaoTbl.get("fileUuid"), cscsUuid),
                        session.getCriteriaBuilder().equal(cscsPatternDaoTbl.get("CSCSPatternId"), 1));
                List<SCSPatternDAO> scsPatternDaoList = session.createQuery(criteria).getResultList();

                scsPatternDaoList.forEach(dao -> {
                    dao.setCscUuid(null);
                    dao.setCscFileName(null);
                    session.update(dao);
                    session.flush();
                    session.refresh(dao);
                });
                tx.commit();
            } catch (Throwable e) {
                e.printStackTrace();
                if (tx != null) {
                    tx.rollback();
                }
            }
        }
    }

    /**
     * The SCSPatternDAO is retrieved based on the ID of the .scs file and the ID of the .cscs file.
     * @param projectId project id
     * @param scsFileId .scs file id
     * @param cscsFileId .cscs file id
     * @return Specific pattern data in the SCSPatternDAO table
     */
    public SCSPatternDAO getSCSPatternDAO(long projectId, long scsFileId, long cscsFileId) {
        File scsFile = getFile(scsFileId);
        File cscsFile = getFile(cscsFileId);
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {

                tx = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<SCSPatternDAO> query = builder.createQuery(SCSPatternDAO.class);
                Root<SCSPatternDAO> scsPtnTbl = query.from(SCSPatternDAO.class);
                query.select(scsPtnTbl);
                query.where(builder.equal(scsPtnTbl.get("projectId"), projectId), builder.equal(scsPtnTbl.get("fileUuid"), scsFile.getUuid()),
                        builder.equal(scsPtnTbl.get("cscUuid"), cscsFile.getUuid()));

                List<SCSPatternDAO> scsPtnList = session.createQuery(query).getResultList();
                if (!scsPtnList.isEmpty()) {
                    return scsPtnList.get(0);
                }
            } catch (Throwable e) {
                tx.rollback();
            }
            return null;
        }
    }

    /**
     * Get the data of the specified pattern from the SCSPatternDAO table.
     * @param fileId .scss file id
     * @param scsPatternId Only records that match the scs pattern id specified here will be fetched.
     * @return Specific pattern data in the SCSPatternDAO table
     */
    public SCSPatternDAO getSCSPatternDAO(long fileId, String scsPatternId) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                File file = session.byId(File.class).load(fileId);
                long ptnId = Long.valueOf(scsPatternId);
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<SCSPatternDAO> query = session.getCriteriaBuilder().createQuery(SCSPatternDAO.class);
                Root<SCSPatternDAO> ptnTable = query.from(SCSPatternDAO.class);
                Root<File> fileTable = query.from(File.class);
                query.select(ptnTable);
                List<Predicate> preList = new ArrayList<Predicate>();
                preList.add(builder.equal(ptnTable.get("fileUuid"), file.getUuid()));
                preList.add(builder.equal(ptnTable.get("projectId"), file.getProjectid()));
                preList.add(builder.equal(ptnTable.get("SCSPatternId"), ptnId));
                preList.add(builder.equal(fileTable.get("uuid"), ptnTable.get("fileUuid")));
                preList.add(builder.equal(fileTable.get("projectid"), ptnTable.get("projectId")));
                preList.add(builder.isFalse(fileTable.get("deleteFlg")));
                query.where(preList.toArray(new Predicate[preList.size()]));
                Query<SCSPatternDAO> querySCSPattern = session.createQuery(query);
                if (querySCSPattern.getResultList().isEmpty()) {
                    return null;
                } else {
                    return querySCSPattern.getSingleResult();
                }
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            }
        }
    }

    /**
     * From the SCSPatternDAO table, get the records for which LSC has been set.
     * @param projectId project id
     * @param scssUuid .scss file uuid
     * @return SCSPatternDAO list where lsc is set
     */
    public List<SCSPatternDAO> getSCSPatternDaoWithLSCDefined(long projectId, String scssUuid) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            CriteriaQuery<SCSPatternDAO> criteria = session.getCriteriaBuilder().createQuery(SCSPatternDAO.class);
            Root<SCSPatternDAO> scsPatternDaoTbl = criteria.from(SCSPatternDAO.class);
            criteria.select(scsPatternDaoTbl);
            criteria.where(session.getCriteriaBuilder().equal(scsPatternDaoTbl.get("projectId"), projectId),
                    session.getCriteriaBuilder().equal(scsPatternDaoTbl.get("fileUuid"), scssUuid), session.getCriteriaBuilder().isNotNull(scsPatternDaoTbl.get("lsc")),
                    session.getCriteriaBuilder().notEqual(scsPatternDaoTbl.get("lsc"), ""));
            criteria.orderBy(session.getCriteriaBuilder().asc(scsPatternDaoTbl.get("SCSPatternId")));
            return session.createQuery(criteria).getResultList();
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * The argument information is reflected in the CSSPatternDAO table.
     * @param daoList Data to be registered in CSSPatternDAO table
     * @return True: Registration succeeded / False: Registration failed
     */
    public boolean registerCSCSPatterns(List<CSCSPatternDAO> daoList) {
        try {
            Transaction tx = null;
            try (Session session = DAOUtils.getSessionFactory().openSession()) {
                tx = session.beginTransaction();
                daoList.forEach(session::persist);
                tx.commit();
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                return false;
            }
        } catch (Throwable e) {
            return false;
        }
        return true;
    }

    /**
     * Delete the data from the CSCSPatternDao table.<br>
     * In addition, the deleted CSCSPatternDao information will be cleared from the SCSPatternDAO table.
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param uuid cscs file uuid
     * @param projectId Project ID of CSCS file
     * @throws Exception
     */
    public void removeCSCSPatterens(Session session, String uuid, long projectId) throws Exception {
        // CSCSの場合もDBの内容削除
        EntityManager em = null;
        EntityTransaction etx = null;
        try {
            em = session.getEntityManagerFactory().createEntityManager(DAOUtils.createProps());
            etx = em.getTransaction();
            etx.begin();
            Class<?>[] classList = { CSCSPatternDAO.class };
            // CSCSを削除
            removeCscsFromScs(uuid, projectId);
            removeDBElementsWithUuid(em, session, uuid, projectId, classList);
            etx.commit();
        } catch (Throwable e) {
            if (etx != null)
                etx.rollback();
            throw new Exception(e);
        } finally {
            if (em != null)
                em.close();
        }
    }

    /**
     * Argument: The information of "file" is reflected in the File table.<br>
     * Moreover, the information of the File table before reflection is saved to the FileHistory table.(Maximum 30. Old data
     * will be deleted)<br>
     * @param file File information after update
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @param userInfo Information of user who is logged in
     */
    public void saveFile(File file, Session session, UserInfo userInfo) {
        try {
            // Historyのレコード数がHISTORY_COUNTを超えたら古い方から一つ削除する
            CriteriaQuery<FileHistory> criteria = session.getCriteriaBuilder().createQuery(FileHistory.class);
            Root<FileHistory> root = criteria.from(FileHistory.class);
            criteria.select(root);
            criteria.where(session.getCriteriaBuilder().equal(root.get("file").get("id"), file.getId()));
            List<FileHistory> historyList = session.createQuery(criteria).getResultList();
            if (historyList.size() >= HISTORY_COUNT) {
                FileHistory oldHistory = historyList.stream().max((l, r) -> r.getUpdateTime().compareTo(l.getUpdateTime())).get();
                if (oldHistory != null) {
                    session.remove(oldHistory);
                }
            }

            // 対象ファイルの直前の情報を履歴に追加
            File target = session.byId(File.class).load(file.getId());
            FileHistory history = new FileHistory();
            history.setFile(target);
            history.setName(target.getName());
            history.setExtension(target.getExtension());
            history.setFullPath(target.getFullPath());
            history.setContent(target.getContent());
            history.setUpdateTime(target.getUpdateTime());
            history.setUpdateUser(target.getUpdateUser());
            String hashCode = returnHashCodeFromFile(target);
            history.setHash(hashCode);
            history.setProjectid(target.getProjectid());
            // 履歴を更新
            session.persist(history);

            EnumSet<Extension> extensionSet = EnumSet.allOf(Extension.class);
            Extension ext = Extension.getByCode(target.getExtension());
            if (extensionSet.contains(ext) && ext != Extension.TXT && ext != Extension.LSC && ext != Extension.CSC) {
                AbstractRootElement rootElement = convertToRootElement(target.getContent());
                rootElement.setId(target.getUuid());

                Map<String, File> refFileMap = getRefFileMap(rootElement, file.getProjectid(), session);
                setContentData(target, rootElement, refFileMap, session);
            }

            // ファイルを更新する
            target.setName(file.getName());
            if (file.getExtension() != null && !file.getExtension().isEmpty()) {
                target.setExtension(file.getExtension());
            }
            target.setFullPath(file.getFullPath());
            target.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            if (userInfo != null) {
                target.setUpdateUser(userInfo.getUserId());
            }
            String newHash = returnHashCodeFromFile(target);
            target.setHash(newHash);
            session.save(target);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Get the file information of the dependent files set in the EMF model
     * @param root EMF model
     * @param projectId project ID
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @return File information of dependent files.<br>
     *         key: uuid<br>
     *         value: File information
     */
    public Map<String, File> getRefFileMap(AbstractRootElement root, long projectId, Session session) {
        Map<String, File> refFileMap = null;
        List<String> refIdList = root.getRefs().stream().map(ref -> ref.getRefid()).collect(Collectors.toList());
        if (refIdList != null && !refIdList.isEmpty()) {
            CriteriaQuery<File> criteria = session.getCriteriaBuilder().createQuery(File.class);
            Root<File> fileTbl = criteria.from(File.class);
            criteria.select(fileTbl);
            criteria.where(fileTbl.get("uuid").in(refIdList), session.getCriteriaBuilder().equal(fileTbl.get("projectid"), projectId),
                    session.getCriteriaBuilder().isFalse(fileTbl.get("deleteFlg")));
            refFileMap = session.createQuery(criteria).getResultList().stream().collect(Collectors.toMap(File::getUuid, res -> res, (a, b) -> a));
        }
        return refFileMap;
    }

    /**
     * Gets the ID of the project that manages the specified file.
     * @param fileId ID of {@link File} or {@link VMFile}
     * @return project ID
     */
    public long getProjectId(long fileId) {
        File file = getFile(fileId);
        if (file == null) {
            file = getFile(fileId, true);
        }
        if (file == null) {
            throw new IllegalArgumentException(fileId + " is not exist in File table or has been deleted.");
        }
        return file.getProjectid();
    }

    /**
     * Gets the ID of the project that manages the specified directory.
     * @param dir Directory to get the project ID
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @return project ID
     */
    public long getProjectId(Directory dir, Session session) {
        Directory parentDir = dir.getDirectory();
        if (parentDir != null) {
            Directory nextDir = session.byId(Directory.class).load(parentDir.getId());
            return getProjectId(nextDir, session);
        } else {
            return dir.getProjectid();
        }
    }

    /**
     * Creates an EMF model associated with the specified file.<br>
     * Set the initial value and file dependency in the EMF model,
     * @param file File to create EMF model
     * @param refFileList Dependent files
     * @return EMF model
     * @throws Exception
     */
    private AbstractRootElement makeFile(File file, List<File> refFileList) throws Exception {
        AbstractRootElement root = null;
        Extension ext = Extension.getByCode(file.getExtension());
        if (ext == null) {
            makeOtherFile(file);
            return root;
        } else
            switch (ext) {
            case FSM:
                FSMDStateMachine machine = FSMFactory.eINSTANCE.createFSMDStateMachine();
                FSMDRegion region = FSMFactory.eINSTANCE.createFSMDRegion();
                machine.getRegions().add(region);
                root = machine;
                break;
            case FM:
                FMRoot fmRoot = FMFactory.eINSTANCE.createFMRoot();
                FMNode rootNode = FMFactory.eINSTANCE.createFMNode();
                FMNode childNode = FMFactory.eINSTANCE.createFMNode();
                rootNode.setName("RootNode");
                rootNode.setTop(10);
                rootNode.setLeft(10);
                rootNode.setHeight(40);
                rootNode.setWidth(80);
                childNode.setName("Node1");
                childNode.setHeight(40);
                childNode.setWidth(80);
                childNode.setTop(10);
                childNode.setLeft(200);

                rootNode.getChildren().add(childNode);
                fmRoot.setNode(rootNode);
                root = fmRoot;
                break;
            case SCD:
                root = SCDFactory.eINSTANCE.createSCDRoot();
                break;
            case SPQL:
                root = SPQLFactory.eINSTANCE.createSPQLRoot();
                break;
            case TC:
                String extension = "";
                if (refFileList != null && !refFileList.isEmpty()) {
                    BinaryResourceImpl r = new BinaryResourceImpl();
                    ByteArrayInputStream bi = new ByteArrayInputStream(refFileList.get(0).getContent());
                    extension = refFileList.get(0).getExtension();
                    try {
                        r.load(bi, EditOptions.getDefaultLoadOptions());
                        root = (AbstractRootElement) r.getContents().get(0);
                    } catch (IOException e) {
                        throw e;
                    }
                    if (extension.equals(Extension.FM.getValue())) {
                        root = new TCModelParserServer().parseFMModel((FMRoot) root, file.getProjectid());
                        break;
                    } else if (extension.equals(Extension.TC.getValue())) {
                        root = new TCModelParserServer().parseTCModel((TCRoot) root);
                        break;
                    } else {
                        root = TCFactory.eINSTANCE.createTCRoot();
                        break;
                    }
                } else {
                    root = TCFactory.eINSTANCE.createTCRoot();
                    break;
                }
            case FMC:
                root = FMCFactory.eINSTANCE.createFMCRoot();
                break;
            case ARC:
                root = ARCFactory.eINSTANCE.createARCRoot();
                break;
            case FPS:
                root = TPSFactory.eINSTANCE.createTPSRoot();
                List<PatternElementInfo> infos = ProjectServiceLogic.getPatternElementInfoList(file.getProjectid());
                for (PatternElementInfo info : infos) {
                    TPSPatternElement el = TPSFactory.eINSTANCE.createTPSPatternElement();
                    el.setName(info.getName());
                    el.setValue(info.getValue());
                    ((TPSRoot) root).getPatternElements().add(el);
                }
                break;
            case FP:
                root = TPFactory.eINSTANCE.createTPRoot();
                break;
            case BPS:
                root = BPSFactory.eINSTANCE.createBPSRoot();
                ((BPSRoot) root).getOptions().add(BPSFactory.eINSTANCE.createBPSPathCombinationOption());
                break;
            case BP:
                root = BPFactory.eINSTANCE.createBPRoot();
                break;
            case CSC:
                makeCSCFile(file);
                break;
            case SCSS:
                root = CBFactory.eINSTANCE.createCBRoot();
                break;
            case SCS:
                root = SCSFactory.eINSTANCE.createSCSRoot();
                break;
            case CSCS:
                root = CSCSFactory.eINSTANCE.createCSCSRoot();
                break;
            default:
                makeOtherFile(file);
                break;
            }
        if (refFileList != null) {
            for (File refFile : refFileList) {
                Reference ref = COREFactory.eINSTANCE.createReference();
                ref.setRefid(refFile.getUuid());
                root.getRefs().add(ref);
            }
        }
        return root;
    }

    /**
     * Set file dependencies and content.
     * @param file File to be set
     * @param root EMF model associated with the file to set
     * @param refFileMap MAP with file information of reference file.<br>
     *            key: File UUID<br>
     *            value: File class
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @throws IOException
     */
    private void setContentData(File file, AbstractRootElement root, Map<String, File> refFileMap, Session session) throws IOException {
        Extension ext = Extension.getByCode(file.getExtension());
        if (ext == Extension.CSC || ext == Extension.LSC) {
            return;
        }

        if (refFileMap != null && root != null) {
            for (Reference ref : root.getRefs()) {
                File refFile = refFileMap.get(ref.getRefid());
                if (refFile != null) {
                    ref.setRefExtension(refFile.getExtension());
                    ref.setRefName(refFile.getName());
                    ref.setHash(refFile.getHash());
                }
            }
        }
        if (ext != Extension.TXT && root != null) {
            setReferences(file, root.getRefs(), session);
        }
        if (root != null) {
            BinaryResourceImpl r = new BinaryResourceImpl();
            r.getContents().add(root);
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] output = null;
            r.save(outputStream, EditOptions.getDefaultSaveOptions());
            output = outputStream.toByteArray();
            file.setContent(output);
        } else {
            file.setContent(createDummyData());
        }

    }

    /**
     * Set the absolute path of the file based on the argument.
     * @param targetId ID of renamed directory
     * @param newName New name for the directory
     * @param targetFile File to change full path
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @return File after changing the full path
     */
    public File setFullPathName(Long targetId, String newName, File targetFile, Session session) {
        String fullPath = targetFile.getName() + "." + targetFile.getExtension();
        for (Directory parent = session.byId(Directory.class).load(targetFile.getDirectory().getId()); parent.getDirectory() != null; parent = session.byId(Directory.class)
                .load(parent.getDirectory().getId())) {
            if (parent.getId().equals(targetId)) {
                fullPath = newName + "/" + fullPath;
            } else {
                fullPath = parent.getName() + "/" + fullPath;
            }
        }
        targetFile.setFullPath("/" + fullPath);
        return targetFile;
    }

    /**
     * Set the dummy data of the CSC file to the contents of the file.
     * @param file File to set the value
     * @throws Exception
     */
    private void makeCSCFile(File file) throws Exception {
        file.setContent(DummyData.CSC_FILE_DUMMY_DATA);
    }

    /**
     * Set the empty string byte array to the contents of the file.
     * @param file File to set the value
     * @throws Exception
     */
    private void makeOtherFile(File file) throws Exception {
        file.setContent(createDummyData());
    }

    /**
     * Create a byte array of empty characters and return it.
     * @return Empty character byte array
     */
    private byte[] createDummyData() {
        return new String("").getBytes();
    }

    /**
     * Returns a hash code from the specified file.
     * @param targetFile File to get the hash code
     * @return hash File hash code
     * @throws IOException
     * @throws FileNotFoundException
     */
    private String returnHashCodeFromFile(File targetFile) throws IOException, FileNotFoundException {
        MessageDigest md = null;
        StringBuilder sb = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(targetFile.getContent());
        sb = new StringBuilder();
        for (byte b : md.digest()) {
            String hex = String.format("%02x", b);
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * The current file dependency will be removed.
     * @param projectId Project Id of the file to remove the dependency
     * @param fileUuid UUID of the file to remove the dependency
     * @param session It is a class that holds the object obtained from DB and manages the state
     */
    public void deleteReference(long projectId, String fileUuid, Session session) {
        // 以前の依存関係を削除する
        CriteriaDelete<FileReferences> fileRefDelete = session.getCriteriaBuilder().createCriteriaDelete(FileReferences.class);
        Root<FileReferences> rootPath = fileRefDelete.from(FileReferences.class);
        fileRefDelete.where(session.getCriteriaBuilder().equal(rootPath.get("fileuuid"), fileUuid), session.getCriteriaBuilder().equal(rootPath.get("project"), projectId));
        session.createQuery(fileRefDelete).executeUpdate();
    }

    /**
     * The current file dependency will be deleted and a new file dependency will be registered.
     * @param root File to recreate the dependency
     * @param refs Dependent files
     * @param session It is a class that holds the object obtained from DB and manages the state
     * @throws IllegalArgumentException
     */
    private void setReferences(File root, List<Reference> refs, Session session) throws IllegalArgumentException {
        deleteReference(root.getProjectid(), root.getUuid(), session);
        if (!root.isDeleteFlg()) {
            CriteriaQuery<FileReferences> criteriaRef = session.getCriteriaBuilder().createQuery(FileReferences.class);
            Root<FileReferences> refFileTbl = criteriaRef.from(FileReferences.class);
            criteriaRef.where(session.getCriteriaBuilder().equal(refFileTbl.get("refuuid"), root.getUuid()),
                    session.getCriteriaBuilder().equal(refFileTbl.get("refproject"), root.getProjectid()));
            List<FileReferences> fieleReferences = session.createQuery(criteriaRef).getResultList();
            for (FileReferences fieleReference : fieleReferences) {
                fieleReference.setRefFile(root);
                session.save(fieleReference);
            }

            if (refs != null) {
                // 依存関係を登録する
                for (Reference refTarget : refs) {
                    CriteriaBuilder builder = session.getCriteriaBuilder();
                    CriteriaQuery<File> criteria = builder.createQuery(File.class);
                    Root<File> fileTbl = criteria.from(File.class);
                    List<Predicate> preList = new ArrayList<Predicate>();
                    preList.add(builder.equal(fileTbl.get("uuid"), refTarget.getRefid()));
                    preList.add(builder.isFalse(fileTbl.get("deleteFlg")));
                    preList.add(builder.equal(fileTbl.get("projectid"), root.getProjectid()));
                    criteria.select(fileTbl).where(preList.toArray(new Predicate[preList.size()]));
                    List<File> refFiles = session.createQuery(criteria).getResultList();
                    FileReferences reference = new FileReferences();
                    reference.setFile(root);
                    reference.setFileuuid(root.getUuid());
                    reference.setExtension(root.getExtension());
                    reference.setProject(new Project(root.getProjectid()));
                    if (!refFiles.isEmpty()) {
                        reference.setHash(refFiles.get(0).getHash());
                        reference.setRefFile(refFiles.get(0));
                        reference.setRefuuid(refFiles.get(0).getUuid());
                        reference.setRefextension(refFiles.get(0).getExtension());
                        reference.setRefproject(new Project(refFiles.get(0).getProjectid()));
                    } else {
                        reference.setHash(refTarget.getHash());
                        reference.setRefuuid(refTarget.getRefid());
                        reference.setRefextension(refTarget.getRefExtension());
                        reference.setRefproject(new Project(root.getProjectid()));
                    }

                    session.persist(reference);
                }
            }
        }

    }

    /**
     * Gets the reference file of the specified file. <br>
     * Syntax: "Select * from FileReferences where fileuuid = arg:fileuuId"
     * @param projectId project Id of the specified file
     * @param fileuuid UUID of the specified file
     * @return All FileReferences that match the conditions
     */
    public List<FileReferences> getReferences(long projectId, String fileuuid) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {

            CriteriaQuery<FileReferences> criteria = session.getCriteriaBuilder().createQuery(FileReferences.class);
            Root<FileReferences> refTbl = criteria.from(FileReferences.class);
            criteria.select(refTbl);
            criteria.where(session.getCriteriaBuilder().equal(refTbl.get("fileuuid"), fileuuid), session.getCriteriaBuilder().equal(refTbl.get("project"), projectId));

            List<FileReferences> references = session.createQuery(criteria).getResultList();

            return references;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Gets the file that references the specified file.<br>
     * Syntax: "Select * from FileReferences where refuuid = arg:refuuid"
     * @param projectId project Id of the specified file
     * @param refuuid UUID of the specified file
     * @return All FileReferences that match the conditions
     */
    public List<FileReferences> getReferencesByRefuuid(long projectId, String refuuid) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {

            CriteriaQuery<FileReferences> criteria = session.getCriteriaBuilder().createQuery(FileReferences.class);
            Root<FileReferences> refTbl = criteria.from(FileReferences.class);
            criteria.select(refTbl);
            criteria.where(session.getCriteriaBuilder().equal(refTbl.get("refuuid"), refuuid), session.getCriteriaBuilder().equal(refTbl.get("project"), projectId));

            List<FileReferences> references = session.createQuery(criteria).getResultList();

            return references;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Get all files that reference the specified file.
     * @param uuid UUID of the specified file
     * @param projectId project Id of the specified file
     * @return All files that match the conditions
     */
    public List<File> getChildFile(String uuid, long projectId) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            CriteriaQuery<File> criteria = session.getCriteriaBuilder().createQuery(File.class);
            Root<FileReferences> fileRefTbl = criteria.from(FileReferences.class);
            Root<File> fileTbl = criteria.from(File.class);
            criteria.select(fileTbl);
            criteria.where(session.getCriteriaBuilder().equal(fileRefTbl.get("refuuid"), uuid),
                    session.getCriteriaBuilder().equal(fileRefTbl.get("refproject").get("id"), projectId),
                    session.getCriteriaBuilder().equal(fileTbl.get("id"), fileRefTbl.get("file").get("id")),
                    session.getCriteriaBuilder().equal(fileTbl.get("projectid"), fileRefTbl.get("project").get("id")),
                    session.getCriteriaBuilder().isFalse(fileTbl.get("deleteFlg")));
            List<File> files = session.createQuery(criteria).getResultList();
            return files;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * File dependencies are obtained from the FileReferences table and File table.
     * @param projectId project id
     * @return List<{@link VMFile}[0] is source, {@link VMFile}[1] is target>
     */
    public List<VMFile[]> getFileDependencies(long projectId) {
        List<VMFile[]> retList = new ArrayList<VMFile[]>();
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                StringBuilder sbQuery = new StringBuilder();
                sbQuery.append(" select s.uuid as suuid ").append("\r\n");
                sbQuery.append("      ,s.name as sname ").append("\r\n");
                sbQuery.append("      ,s.extension as sext ").append("\r\n");
                sbQuery.append("      ,s.id as sid ").append("\r\n");
                sbQuery.append("      ,s.fullPath as sfullPath ").append("\r\n");
                sbQuery.append("      ,t.uuid as tuuid ").append("\r\n");
                sbQuery.append("      ,t.name as tname ").append("\r\n");
                sbQuery.append("      ,t.extension as text ").append("\r\n");
                sbQuery.append("      ,t.id as tid ").append("\r\n");
                sbQuery.append("      ,t.fullPath as tfullPath ").append("\r\n");
                sbQuery.append(" from  filereferences r ").append("\r\n");
                sbQuery.append("      ,file s ").append("\r\n");
                sbQuery.append("      ,file t ").append("\r\n");
                sbQuery.append("where r.fileuuid = t.uuid ").append("\r\n");
                sbQuery.append("  and r.projectid = t.projectid ").append("\r\n");
                sbQuery.append("  and r.refuuid = s.uuid ").append("\r\n");
                sbQuery.append("  and r.refprojectid = s.projectid ").append("\r\n");
                sbQuery.append("  and r.projectid = ? ").append("\r\n");
                @SuppressWarnings("unchecked")
                Query<Object[]> query = session.createNativeQuery(sbQuery.toString());
                query.setParameter(1, projectId);
                List<Object[]> results = query.getResultList();
                if (results != null && !results.isEmpty()) {
                    results.forEach(r -> {
                        VMFile tmpSource = new VMFile();
                        tmpSource.setUuid((String) r[0]);
                        tmpSource.setName((String) r[1]);
                        tmpSource.setExtensionStr((String) r[2]);
                        tmpSource.setId(((BigInteger) r[3]).longValue());
                        tmpSource.setFullPath((String) r[4]);
                        VMFile tmpTarget = new VMFile();
                        tmpTarget.setUuid((String) r[5]);
                        tmpTarget.setName((String) r[6]);
                        tmpTarget.setExtensionStr((String) r[7]);
                        tmpTarget.setId(((BigInteger) r[8]).longValue());
                        tmpTarget.setFullPath((String) r[9]);
                        VMFile[] tmpFiles = { tmpSource, tmpTarget };
                        retList.add(tmpFiles);
                    });
                }
                tx.commit();
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
        return retList;
    }

    /**
     * All files belonging to the specified project will be retrieved.
     * @param projectId project Id
     * @return Acquired file information
     */
    public List<VMFile> getVMFiles(Long projectId) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaQuery<VMFile> criteria = session.getCriteriaBuilder().createQuery(VMFile.class);
                Root<File> fileTbl = criteria.from(File.class);
                CriteriaBuilder builder = session.getCriteriaBuilder();
                criteria.select(builder.construct(VMFile.class, fileTbl.get("id"), fileTbl.get("name"), fileTbl.get("extension"), fileTbl.get("fullPath"), fileTbl.get("uuid"),
                        fileTbl.get("deleteFlg"), fileTbl.get("hash")));

                List<Predicate> preList = new ArrayList<Predicate>();
                preList.add(builder.isFalse(fileTbl.get("deleteFlg")));
                preList.add(builder.equal(fileTbl.get("projectid"), projectId));
                criteria.where(preList.toArray(new Predicate[preList.size()]));
                List<VMFile> retData = session.createQuery(criteria).getResultList();
                tx.commit();
                return retData;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * The directory or file is deleted.<br>
     * When the FPS, BPS and SCSS files are deleted, the job to delete RDF is registered.<br>
     * If the CSCS file is deleted, the corresponding data will be deleted from the DB.<br>
     * @param resources List of directories and files to delete
     * @param userInfo Information of logged-in user
     * @throws IllegalArgumentException
     */
    public void removeResources(List<VMResource> resources, UserInfo userInfo) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                for (VMResource resource : resources) {
                    if (resource instanceof VMDirectory) {
                        Directory dir = session.byId(Directory.class).load(resource.getId());
                        dir.setDeleteFlg(true);
                        session.save(dir);
                    } else if (resource instanceof VMFile) {
                        File file = session.byId(File.class).load(resource.getId());
                        file.setDeleteFlg(true);
                        EditResourceUtil.INSTANCE.saveFile(file, session, userInfo);
                        long fileId = file.getId();
                        long projectId = file.getProjectid();

                        // FP、BPの場合DBの内容も削除するJOBを登録
                        if (Extension.getByCode(file.getExtension()) == Extension.FPS) {
                            addJob(fileId, file, fileId, Job.TYPE_REMOVE_FPS, projectId);
                        } else if (Extension.getByCode(file.getExtension()) == Extension.BPS) {
                            addJob(fileId, file, fileId, Job.TYPE_REMOVE_BPS, projectId);
                        } else if (Extension.getByCode(file.getExtension()) == Extension.SCSS) {
                            addJob(fileId, file, fileId, Job.TYPE_REMOVE_SCSS, projectId);
                        } else if (Extension.getByCode(file.getExtension()) == Extension.CSCS) {
                            // CSCSの場合もDBの内容削除
                            EditResourceUtil.INSTANCE.removeCSCSPatterens(session, file.getUuid(), file.getProjectid());
                        }
                    } else {
                        throw new Exception("class cast exception.");
                    }
                }
                tx.commit();
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Get the free number of the file name.
     * @param dirId Directory where the file is located
     * @param file File to get the free number
     * @return free number
     * @throws IllegalArgumentException
     */
    public long getFileNumber(long dirId, VMFile file) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                StringBuilder queryStr = new StringBuilder();
                queryStr.append("WITH FILE_NUM AS(");
                queryStr.append("  SELECT CAST(CASE WHEN IDX = '' THEN '0' ELSE IDX END AS BIGINT) AS IDX");
                queryStr.append("    FROM (");
                queryStr.append("      SELECT REPLACE(REPLACE(Name, ?, ''), '_', '') AS IDX");
                queryStr.append("        FROM File");
                queryStr.append("       WHERE ParentDirId = ?");
                queryStr.append("         AND Extension = ?");
                queryStr.append("         AND DeleteFlg = false");
                queryStr.append("         AND Name SIMILAR TO ?"); // '[FileName](_)?[0-9]*'
                queryStr.append("      ) AS F");
                queryStr.append(")");
                queryStr.append("SELECT MIN(IDX + 1) AS IDX");
                queryStr.append("  FROM FILE_NUM");
                queryStr.append(" WHERE (IDX + 1) NOT IN (SELECT IDX FROM FILE_NUM)");

                @SuppressWarnings("unchecked")
                Query<Object> q = session.createNativeQuery(queryStr.toString());
                q.setParameter(1, file.getName());
                q.setParameter(2, dirId);
                q.setParameter(3, file.getExtension().getValue());
                q.setParameter(4, file.getName() + "(_)?[0-9]*");

                List<Object> targets = q.getResultList();
                long ret = 0;
                if (targets.get(0) != null) {
                    ret = ((BigInteger) targets.get(0)).longValue();
                }

                tx.commit();
                return ret;
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * Reflect the contents of the argument in the SCSPatternDAO table.
     * @param fileId SCSS file id
     * @param scsPattern Information of this class is reflected in the SCSPatternDAO table.
     */
    public void saveSCSPattern(long fileId, SCSPattern scsPattern) {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                File file = session.byId(File.class).load(fileId);
                long ptnId = Long.valueOf(scsPattern.getId());
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<SCSPatternDAO> query = session.getCriteriaBuilder().createQuery(SCSPatternDAO.class);
                Root<SCSPatternDAO> ptnTable = query.from(SCSPatternDAO.class);
                Root<File> fileTable = query.from(File.class);
                query.select(ptnTable);
                List<Predicate> preList = new ArrayList<Predicate>();
                preList.add(builder.equal(ptnTable.get("fileUuid"), file.getUuid()));
                preList.add(builder.equal(ptnTable.get("projectId"), file.getProjectid()));
                preList.add(builder.equal(ptnTable.get("SCSPatternId"), ptnId));
                preList.add(builder.equal(fileTable.get("uuid"), ptnTable.get("fileUuid")));
                preList.add(builder.equal(fileTable.get("projectid"), ptnTable.get("projectId")));
                preList.add(builder.isFalse(fileTable.get("deleteFlg")));
                query.where(preList.toArray(new Predicate[preList.size()]));
                Query<SCSPatternDAO> querySCSPattern = session.createQuery(query);
                SCSPatternDAO patternDAO;
                if (querySCSPattern.getResultList().isEmpty()) {
                    patternDAO = new SCSPatternDAO();
                    patternDAO.setSCSPatternId(Long.valueOf(scsPattern.getId()));
                    patternDAO.setFileUuid(file.getUuid());
                    patternDAO.setProjectId(file.getProjectid());
                } else {
                    patternDAO = querySCSPattern.getSingleResult();
                }
                patternDAO.setLsc(scsPattern.getLsc());
                patternDAO.setCscUuid(scsPattern.getCscUuid());
                patternDAO.setCscFileName(scsPattern.getCscFileName());
                session.save(patternDAO);
                tx.commit();
            } catch (Exception e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw e;
            }
        }
    }

    /**
     * Convert the binary to the root class of the EMF model.
     * @param data Binary root class
     * @return EMF model root class
     */
    public AbstractRootElement convertToRootElement(byte[] data) {
        BinaryResourceImpl r = new BinaryResourceImpl();
        ByteArrayInputStream bi = new ByteArrayInputStream(data);
        try {
            r.load(bi, EditOptions.getDefaultLoadOptions());
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
        return (AbstractRootElement) r.getContents().get(0);
    }

    /**
     * Convert the root class of the EMF model to binary.
     * @param root EMF model root class
     * @return Root class binary
     */
    public byte[] convertToBinary(AbstractRootElement root) {
        BinaryResourceImpl r = new BinaryResourceImpl();
        r.getContents().add(root);
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        try {
            r.save(bo, EditOptions.getDefaultSaveOptions());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return bo.toByteArray();
    }

    /**
     * Checks if the argument string is a number.
     * @param str String to check
     * @return If true, a number
     */
    private boolean isDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
