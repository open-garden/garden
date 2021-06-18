package com.zipc.garden.webplatform.server.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.SettingInterface;
import com.zipc.garden.model.scs.SCSRoot;
import com.zipc.garden.model.scs.SCSSetting;
import com.zipc.garden.webplatform.client.service.EditResourceService;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.Directory;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.JobStatus;
import com.zipc.garden.webplatform.dao.Project;
import com.zipc.garden.webplatform.dao.SCSPatternDAO;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.server.generator.HeavyTaskContentGetter;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.JobStatusInfo;
import com.zipc.garden.webplatform.shared.SearchResult;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.resource.VMDirectory;
import com.zipc.garden.webplatform.shared.resource.VMFile;
import com.zipc.garden.webplatform.shared.resource.VMResource;

/**
 * A class that implements server-side code that extends RemoteServiceServlet and implements the EditResourceService interface.
 */
@SuppressWarnings("serial")
public class EditResourceServiceImpl extends RemoteServiceServlet implements EditResourceService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VMResource> getResources(long parentDirId) throws IllegalArgumentException {
        List<VMResource> resources = new ArrayList<VMResource>();
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            // select
            List<VMFile> files;
            CriteriaQuery<VMFile> criteria = session.getCriteriaBuilder().createQuery(VMFile.class);
            Root<File> fileTbl = criteria.from(File.class);
            criteria.select(session.getCriteriaBuilder().construct(VMFile.class, fileTbl.get("id"), fileTbl.get("name"), fileTbl.get("extension"), fileTbl.get("fullPath"),
                    fileTbl.get("uuid"), fileTbl.get("deleteFlg"), fileTbl.get("hash")));
            criteria.where(session.getCriteriaBuilder().equal(fileTbl.get("directory").get("id"), parentDirId),
                    session.getCriteriaBuilder().equal(fileTbl.get("deleteFlg"), false));
            files = session.createQuery(criteria).getResultList();
            resources.addAll(files);

            // directories
            List<VMDirectory> dirs;
            CriteriaQuery<VMDirectory> dirCriteria = session.getCriteriaBuilder().createQuery(VMDirectory.class);
            Root<Directory> dirTbl = dirCriteria.from(Directory.class);
            dirCriteria.select(session.getCriteriaBuilder().construct(VMDirectory.class, dirTbl.get("id"), dirTbl.get("name")));
            dirCriteria.where(session.getCriteriaBuilder().equal(dirTbl.get("directory").get("id"), parentDirId),
                    session.getCriteriaBuilder().equal(dirTbl.get("deleteFlg"), false));
            dirs = session.createQuery(dirCriteria).getResultList();
            resources.addAll(dirs);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        return resources;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renameResource(long resourceId, String newName) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");

        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Directory dir = session.byId(Directory.class).load(resourceId);
                File file = session.byId(File.class).load(resourceId);
                if (dir != null) {
                    // 対象ディレクトリが存在した場合
                    // 該当のディレクトリ直下のファイルパスを変更する
                    searchDirForRename(resourceId, newName, dir.getId(), session);
                    dir.setName(newName);
                    Directory target = session.byId(Directory.class).load(dir.getId());
                    target.setName(dir.getName());
                    target.setDirectory(dir.getDirectory());
                    session.flush();
                } else if (file != null) {
                    // 対象ファイルが存在した場合
                    int index = newName.lastIndexOf(".");
                    if (!newName.substring(index + 1).isEmpty() && index != -1) {
                        String newBaseName = newName.substring(0, index);
                        String newExtension = newName.substring(index + 1);
                        file.setName(newBaseName);
                        file.setExtension(newExtension);
                        file.setFullPath(file.getFullPath().substring(0, file.getFullPath().lastIndexOf("/")) + "/" + newBaseName + "." + newExtension);
                        EditResourceUtil.INSTANCE.saveFile(file, session, userInfo);
                    } else {
                        System.out.println("Extension is lost!");
                        return;
                    }
                } else {
                    throw new Exception("class cast exception.");
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
     * {@inheritDoc}
     */
    @Override
    public void saveFile(long fileId, byte[] data) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        EditResourceUtil.INSTANCE.saveFile(fileId, data, userInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTextFile(long fileId, String data, long projectId) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");

        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                // 対象のファイルを検索する
                File targetFile = session.byId(File.class).load(fileId);

                Project project = session.byId(Project.class).load(projectId);

                // save File data
                targetFile.setContent(data.getBytes(project.getEncodingType()));
                EditResourceUtil.INSTANCE.saveFile(targetFile, session, userInfo);
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
     * {@inheritDoc}
     */
    @Override
    public Long createDir(long parentId, String childDirName) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Directory target = new Directory();
                Directory childDirectory = new Directory();

                // find parent directory
                target = session.byId(Directory.class).load(parentId);
                // create child directory
                childDirectory.setName(childDirName);
                childDirectory.setDirectory(target);
                childDirectory.setProjectid(EditResourceUtil.INSTANCE.getProjectId(target, session));
                childDirectory = setFullPathName(childDirectory.getId(), childDirName, childDirectory, session);
                session.persist(childDirectory);
                tx.commit();
                return childDirectory.getId();
            } catch (Throwable e) {
                if (tx != null) {
                    tx.rollback();
                }
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long createFile(long parentId, VMFile file, List<Long> refIdList) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        Long result = EditResourceUtil.INSTANCE.createFile(parentId, file, refIdList, userInfo);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long uploadFile(long parentId, VMFile file, byte[] data) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        return EditResourceUtil.INSTANCE.uploadFile(parentId, file, data, userInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getFileContent(long fileId) throws IllegalArgumentException {
        return EditResourceUtil.INSTANCE.getFileContent(fileId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] getFileContent(String fileUuid, long projectId) throws IllegalArgumentException {
        return EditResourceUtil.INSTANCE.getFileContent(fileUuid, projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTextFileContent(long fileId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            byte[] bytes = null;
            File targetFile = session.byId(File.class).load(fileId);
            bytes = targetFile.getContent();
            String charaCode = charaCodeJudge(bytes);
            return new String(bytes, charaCode);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeResources(List<VMResource> resources) throws IllegalArgumentException {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");
        EditResourceUtil.INSTANCE.removeResources(resources, userInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHash(String uuid, long projectId) throws IllegalArgumentException {
        VMFile vmfile = EditResourceUtil.INSTANCE.getVMFile(uuid, projectId);
        return EditResourceUtil.INSTANCE.getFile(vmfile.getId()).getHash();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VMFile getVMFile(long fileId) throws IllegalArgumentException {
        return EditResourceUtil.INSTANCE.getVMFile(fileId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VMFile getVMFile(String fileId, long projectId) throws IllegalArgumentException {
        return EditResourceUtil.INSTANCE.getVMFile(fileId, projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VMFile getVMFile(String fileId, long projectId, Boolean deleteFlg) throws IllegalArgumentException {
        return EditResourceUtil.INSTANCE.getVMFile(fileId, projectId, deleteFlg);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getFileMap(long projectId, String extension) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<File> query = builder.createQuery(File.class);
                Root<File> root = query.from(File.class);
                query.select(root);
                List<Predicate> preList = new ArrayList<Predicate>();
                preList.add(builder.equal(root.get("projectid"), projectId));
                preList.add(builder.equal(root.get("extension"), extension));
                preList.add(builder.isFalse(root.get("deleteFlg")));
                query.where(preList.toArray(new Predicate[preList.size()]));
                query.orderBy(builder.asc(root.get("name")), builder.asc(root.get("extension")));
                Map<String, String> map = new LinkedHashMap<String, String>();
                Map<String, String> tmpMap = session.createQuery(query).getResultList().stream()
                        .collect(Collectors.toMap(result -> result.getUuid(), result -> result.getName() + "." + extension, (res1, res2) -> res1, LinkedHashMap::new));
                map.putAll(tmpMap);
                map.put("", "");
                tx.commit();
                return map;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getFileMap(long projectId, List<Long> refId, String extension) throws IllegalArgumentException {
        List<Long> refIdList = new ArrayList<>();
        switch (Extension.getByCode(extension)) {
        case FP:
        case BP:
        case SCS:
            // If related to multiple settings file, add the settings file ID to refIdList.
            File settingFile = EditResourceUtil.INSTANCE.getFile(refId.get(0));
            List<File> files = EditResourceUtil.INSTANCE.getChildFile(settingFile.getUuid(), projectId);
            Optional<File> optPtnFile = files.stream().filter(file -> file.getExtension().equals(extension)).findFirst();
            if (optPtnFile.isPresent()) {
                AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(optPtnFile.get().getContent());
                root.getRefs().forEach(ref -> {
                    refIdList.add(EditResourceUtil.INSTANCE.getFile(ref.getRefid(), projectId).getId());
                });
            }
            break;
        default:
            refIdList.addAll(refId);
        }

        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                Map<String, String> map = new LinkedHashMap<String, String>();
                if (refIdList != null) {
                    refIdList.removeIf(id -> id == null || id.equals(-1L));
                }
                if (refIdList == null || refIdList.isEmpty()) {
                    map.put("", "");
                    return map;
                }
                tx = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<File> query = builder.createQuery(File.class);

                Root<File> root = query.from(File.class);
                List<Predicate> preList = new ArrayList<Predicate>();
                preList.add(root.get("id").in(refIdList));
                preList.add(builder.isFalse(root.get("deleteFlg")));
                preList.add(builder.equal(root.get("projectid"), projectId));
                query.select(root).where(preList.toArray(new Predicate[preList.size()]));
                List<File> resultList = session.createQuery(query).getResultList();

                // Even if the refIdList is duplicated, the results will not be combined
                List<File> refFileList = new ArrayList<>();
                refIdList.forEach(id -> {
                    Optional<File> optFile = resultList.stream().filter(file -> file.getId().equals(id)).findFirst();
                    if (optFile.isPresent()) {
                        refFileList.add(optFile.get());
                    }
                });

                ArrayList<Object> paramList = new ArrayList<Object>();
                StringBuilder queryStr = new StringBuilder();
                queryStr.append("SELECT");
                queryStr.append("   A.UUID ");
                queryStr.append("  ,A.NAME ");
                queryStr.append("  ,A.EXTENSION ");
                queryStr.append("FROM ");
                queryStr.append("  FILE A ");
                queryStr.append("  , ( ");
                queryStr.append("    SELECT ");
                queryStr.append("       B.FILEUUID ");
                queryStr.append("      ,B.PROJECTID ");
                queryStr.append("      ,COUNT(B.FILEUUID) AS CNT ");
                queryStr.append("    FROM ");
                queryStr.append("      FILEREFERENCES B ");
                queryStr.append("    GROUP BY ");
                queryStr.append("      B.FILEUUID ");
                queryStr.append("     ,B.PROJECTID ");
                queryStr.append("  ) AS C ");
                queryStr.append("  INNER JOIN ( ");
                queryStr.append("    SELECT ");
                queryStr.append("      D.FILEUUID ");
                queryStr.append("     ,D.PROJECTID ");
                queryStr.append("     ,COUNT(D.FILEUUID) AS CNT ");
                queryStr.append("    FROM ");
                queryStr.append("      FILEREFERENCES D ");
                if (refFileList != null && !refFileList.isEmpty()) {
                    Iterator<File> refFileItr = refFileList.iterator();
                    queryStr.append("    WHERE ");
                    while (refFileItr.hasNext()) {
                        File refFile = refFileItr.next();
                        queryStr.append("      (D.REFID = ? AND D.REFPROJECTID = ?) ");
                        paramList.add(refFile.getId());
                        paramList.add(refFile.getProjectid());
                        if (refFileItr.hasNext()) {
                            queryStr.append(" OR ");
                        }
                    }
                }
                queryStr.append("    GROUP BY ");
                queryStr.append("      D.FILEUUID ");
                queryStr.append("     ,D.PROJECTID ");
                queryStr.append("    HAVING ");
                queryStr.append("      COUNT(D.FILEUUID) = ? ");
                paramList.add(refFileList.size());
                queryStr.append("  ) AS E ");
                queryStr.append("     ON C.FILEUUID = E.FILEUUID ");
                queryStr.append("    AND C.PROJECTID = E.PROJECTID ");
                queryStr.append("    AND C.CNT = E.CNT ");
                queryStr.append("WHERE ");
                queryStr.append("      A.UUID = C.FILEUUID ");
                queryStr.append("  AND A.PROJECTID = C.PROJECTID ");
                queryStr.append("  AND A.DELETEFLG = FALSE ");
                queryStr.append("  AND A.EXTENSION = ? ");
                paramList.add(extension);
                queryStr.append(" ORDER BY ");
                queryStr.append("      A.NAME ");
                queryStr.append("     ,A.EXTENSION ");
                @SuppressWarnings("unchecked")
                Query<Object[]> q = session.createNativeQuery(queryStr.toString());
                for (int i = 0; i < paramList.size(); i++) {
                    q.setParameter(i + 1, paramList.get(i));
                }
                List<Object[]> targets = q.getResultList();
                Map<String, String> tmpMap = targets.stream()
                        .collect(Collectors.toMap(result -> (String) result[0], result -> result[1] + "." + result[2], (res1, res2) -> res1, LinkedHashMap::new));

                tx.commit();
                map.putAll(tmpMap);
                map.put("", "");
                return map;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Long> getFileUuidMap(long projectId) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<File> query = builder.createQuery(File.class);

                Root<File> root = query.from(File.class);
                List<Predicate> preList = new ArrayList<Predicate>();
                preList.add(builder.isFalse(root.get("deleteFlg")));
                preList.add(builder.equal(root.get("projectid"), projectId));
                query.select(root).where(preList.toArray(new Predicate[preList.size()]));
                Map<String, Long> map = session.createQuery(query).getResultList().stream().collect(Collectors.toMap(File::getUuid, File::getId, (a, b) -> a));
                tx.commit();
                map.put("", -1L);
                return map;
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveParents(List<Map<Long, VMResource>> targets) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                for (Map<Long, VMResource> target : targets) {
                    for (Entry<Long, VMResource> entry : target.entrySet()) {
                        File targetFile = session.byId(File.class).load(entry.getValue().getId());
                        Directory targetDir = null;
                        Directory parent = null;
                        if (targetFile == null) {
                            targetDir = session.byId(Directory.class).load(entry.getValue().getId());
                            parent = session.byId(Directory.class).load(entry.getKey());
                            targetDir.setDirectory(parent);
                            session.save(targetDir);
                            searchDirForRename(targetDir.getId(), targetDir.getName(), targetDir.getId(), session);
                        } else {
                            parent = session.byId(Directory.class).load(entry.getKey());
                            targetFile.setDirectory(parent);
                            session.save(targetFile);
                            EditResourceUtil.INSTANCE.setFullPathName(targetFile.getId(), targetFile.getName(), targetFile, session);
                        }
                    }
                }
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeDeleteFlgs(List<Long> targetIds, boolean flg) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                for (Long targetId : targetIds) {
                    File targetFile = session.byId(File.class).load(targetId);
                    Directory targetDir = null;
                    if (targetFile == null) {
                        targetDir = session.byId(Directory.class).load(targetId);
                        targetDir.setDeleteFlg(flg);
                        session.save(targetDir);
                    } else {
                        targetFile.setDeleteFlg(flg);
                        session.save(targetFile);
                    }
                }
                tx.commit();
            } catch (Throwable e) {
                if (tx != null)
                    tx.rollback();
                throw new IllegalArgumentException(e);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VMFile> getRefTargetFiles(long projectId, List<String> extensions) throws IllegalArgumentException {
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
                preList.add(fileTbl.get("extension").in(extensions));
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
     * Get all the directories that belong to the specified directory ID.
     * @param parentDirId
     * @return List of directories
     */
    private List<Directory> getTargetDirs(long parentDirId) {
        List<Directory> targets = null;
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            // select dirs
            CriteriaQuery<Directory> criteria = session.getCriteriaBuilder().createQuery(Directory.class);
            Root<Directory> root = criteria.from(Directory.class);
            criteria.select(root);
            criteria.where(session.getCriteriaBuilder().equal(root.get("directory"), parentDirId), session.getCriteriaBuilder().equal(root.get("deleteFlg"), false));
            targets = session.createQuery(criteria).getResultList();
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        return targets;
    }

    /**
     * Recursively search directories belonging to the renamed directory, Change the full path of the file.
     * @param targetId ID of renamed directory
     * @param newName New name for the directory
     * @param id Directory ID belonging to the renamed directory
     * @param session Holds the objects acquired from the DB and manages the state
     */
    private void searchDirForRename(long targetId, String newName, Long id, Session session) {
        renameFileFullPath(targetId, newName, id, session);
        for (Directory parent : getTargetDirs(id)) {
            searchDirForRename(targetId, newName, parent.getId(), session);
        }
    }

    /**
     * The full path of the file is renamed.
     * @param targetId ID of renamed directory
     * @param newName New name for the directory
     * @param id Directory ID belonging to the renamed directory
     * @param session Holds the objects acquired from the DB and manages the state
     */
    private void renameFileFullPath(long targetId, String newName, Long id, Session session) {
        UserInfo userInfo = (UserInfo) getThreadLocalRequest().getSession().getAttribute("userInfo");

        // select
        CriteriaQuery<File> criteria = session.getCriteriaBuilder().createQuery(File.class);
        Root<File> root = criteria.from(File.class);
        criteria.select(root);
        criteria.where(session.getCriteriaBuilder().equal(root.get("directory"), id), session.getCriteriaBuilder().equal(root.get("deleteFlg"), false));
        for (File targetFile : session.createQuery(criteria).getResultList()) {
            EditResourceUtil.INSTANCE.saveFile(EditResourceUtil.INSTANCE.setFullPathName(targetId, newName, targetFile, session), session, userInfo);
        }
    }

    /**
     * Set the absolute path of the directory based on the argument.
     * @param targetId ID of renamed directory
     * @param newName New name for the directory
     * @param targetDir directory to change full path
     * @param session Holds the objects acquired from the DB and manages the state
     * @return directory after changing the full path
     */
    private Directory setFullPathName(Long targetId, String newName, Directory targetDir, Session session) {
        String fullPath = targetDir.getName();
        for (Directory parent = session.byId(Directory.class).load(targetDir.getDirectory().getId()); parent.getDirectory() != null; parent = session.byId(Directory.class)
                .load(parent.getDirectory().getId())) {
            if (parent.getId() == targetId) {
                fullPath = newName + "/" + fullPath;
            } else {
                fullPath = parent.getName() + "/" + fullPath;
            }
        }
        targetDir.setFullpath("/" + fullPath);
        return targetDir;
    }

    /**
     * The character code of the file (binary) is judged and returned.
     * @param bytes file
     * @return Character code of the file. If the target character code is not found, UTF8 is returned
     */
    private String charaCodeJudge(byte[] bytes) {
        for (CharaCode chara : CharaCode.values()) {
            try {
                byte[] tmp = new String(bytes, chara.getName()).getBytes(chara.getName());
                if (Arrays.equals(tmp, bytes))
                    return chara.getName();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return CharaCode.UTF8.getName();
    }

    /**
     * ENUM that manages the character code
     */
    enum CharaCode {
        UTF8("UTF8"), Shift_JIS("SJIS"), UTF16("UTF-16"), EUC_JP("EUC_JP");
        private final String name;

        CharaCode(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getDirId(long fileId) throws IllegalArgumentException {
        return EditResourceUtil.INSTANCE.getDirId(fileId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<Long, VMResource>> getActiveDirOrFileMap(long projectId, List<Long> selectedIds) throws IllegalArgumentException {
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List<Map<Long, VMResource>> resources = new ArrayList<Map<Long, VMResource>>();
                StringBuilder queryStr = new StringBuilder();
                queryStr.append("WITH RECURSIVE r AS ( SELECT * FROM directory WHERE id in(");
                for (int i = 0; i < selectedIds.size(); i++) {
                    if (i != 0) {
                        queryStr.append(",");
                    }
                    queryStr.append("?");
                }
                queryStr.append(") UNION ALL SELECT directory.* FROM directory, r WHERE directory.parentdirid = r.id ) SELECT id, parentdirid, name FROM r ORDER BY id;");
                @SuppressWarnings("unchecked")
                Query<Object[]> q = session.createNativeQuery(queryStr.toString());
                for (int i = 0; i < selectedIds.size(); i++) {
                    q.setParameter(i + 1, selectedIds.get(i));
                }
                List<Object[]> targets = q.getResultList();
                List<Directory> deleteDirList = new ArrayList<Directory>();
                for (Object[] columns : targets) {
                    Map<Long, VMResource> mapTemp = new HashMap<Long, VMResource>();
                    VMDirectory temp = new VMDirectory(((BigInteger) columns[0]).longValue(), (String) columns[2]);
                    mapTemp.put(((BigInteger) columns[1]).longValue(), temp);
                    resources.add(mapTemp);
                    deleteDirList.add(new Directory(((BigInteger) columns[0]).longValue()));
                }
                // select files
                List<File> files;
                CriteriaQuery<File> criteria = session.getCriteriaBuilder().createQuery(File.class);
                Root<File> fileTbl = criteria.from(File.class);
                criteria.select(fileTbl);
                List<Predicate> preList = new ArrayList<Predicate>();
                if (deleteDirList.isEmpty()) {
                    preList.add(fileTbl.get("id").in(selectedIds));
                } else {
                    preList.add(session.getCriteriaBuilder().or(fileTbl.get("directory").in(deleteDirList), fileTbl.get("id").in(selectedIds)));
                }
                preList.add(session.getCriteriaBuilder().equal(fileTbl.get("projectid"), projectId));
                preList.add(session.getCriteriaBuilder().isFalse(fileTbl.get("deleteFlg")));
                criteria.where(preList.toArray(new Predicate[preList.size()]));
                files = session.createQuery(criteria).getResultList();
                for (File file : files) {
                    Map<Long, VMResource> mapTemp = new HashMap<Long, VMResource>();
                    mapTemp.put(file.getDirectory().getId(),
                            new VMFile(file.getId(), file.getName(), file.getExtension(), file.getFullPath(), file.getUuid(), file.isDeleteFlg(), file.getHash()));
                    resources.add(mapTemp);
                }

                // Get a CSCS file associated with an SCS file.
                List<String> scsUuids = files.stream().filter(file -> file.getExtension().equals(Extension.SCS.getValue())).map(File::getUuid).collect(Collectors.toList());
                if (!scsUuids.isEmpty()) {
                    CriteriaQuery<File> criteria2 = session.getCriteriaBuilder().createQuery(File.class);
                    Root<SCSPatternDAO> scsPatternDaoTbl = criteria2.from(SCSPatternDAO.class);
                    Root<File> fileTbl2 = criteria2.from(File.class);
                    criteria2.select(fileTbl2);
                    criteria2.where(scsPatternDaoTbl.get("fileUuid").in(scsUuids), session.getCriteriaBuilder().equal(fileTbl2.get("projectid"), projectId),
                            session.getCriteriaBuilder().equal(fileTbl2.get("projectid"), scsPatternDaoTbl.get("projectId")),
                            session.getCriteriaBuilder().equal(fileTbl2.get("uuid"), scsPatternDaoTbl.get("cscUuid")),
                            session.getCriteriaBuilder().isFalse(fileTbl2.get("deleteFlg")));
                    List<File> files2 = session.createQuery(criteria2).getResultList();
                    for (File file : files2) {
                        Map<Long, VMResource> mapTemp = new HashMap<Long, VMResource>();
                        mapTemp.put(file.getDirectory().getId(),
                                new VMFile(file.getId(), file.getName(), file.getExtension(), file.getFullPath(), file.getUuid(), file.isDeleteFlg(), file.getHash()));
                        resources.add(mapTemp);
                    }
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
     * {@inheritDoc}
     */
    @Override
    public List<SearchResult> searchModel(long projectId, List<String> extensions, String keyWord) throws IllegalArgumentException {
        return EditResourceUtil.INSTANCE.searchModel(projectId, extensions, keyWord);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VMFile> searchFile(long projectId, String keyWord) throws IllegalArgumentException {
        return EditResourceUtil.INSTANCE.searchFile(projectId, keyWord);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Long> getTargetToRootDirIds(long targetId) throws IllegalArgumentException {
        return EditResourceUtil.INSTANCE.getTargetToRootDirIds(targetId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VMFile[]> getFileDependencies(long projectId) {
        return EditResourceUtil.INSTANCE.getFileDependencies(projectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getSCSRootWithLSCDefined(long projectId, String scsUuid, String scssUuid) {
        File scsFile = EditResourceUtil.INSTANCE.getFile(scsUuid, projectId);
        Map<String, String> fileMap = new HashMap<>();
        if (scsFile != null) {
            List<SCSPatternDAO> scsPatternDaoList = EditResourceUtil.INSTANCE.getSCSPatternDaoWithLSCDefined(projectId, scssUuid);
            SCSRoot scsRoot = (SCSRoot) EditResourceUtil.INSTANCE.convertToRootElement(scsFile.getContent());
            HeavyTaskContentGetter<SCSRoot> getter = new HeavyTaskContentGetter<SCSRoot>();
            getter.setSettings(scsRoot, projectId);
            SCSSetting scsSetting = (SCSSetting) scsRoot.getSettings().stream().filter(setting -> setting.getUuid().equals(scssUuid)).findFirst().get();
            List<String> patternIds = new ArrayList<>();
            scsPatternDaoList.forEach(dao -> patternIds.add(dao.getSCSPatternId().toString()));
            if (patternIds.size() > 0) {
                scsSetting.setPatternNos(String.join(",", patternIds));
                if (EditResourceUtil.INSTANCE.getSCSFileByRecord(projectId, 0, patternIds.size(), scsSetting)) {
                    scsSetting.getPatterns().forEach(pattern -> fileMap.put(pattern.getId(), pattern.getId() + ":" + pattern.getPatternValue()));
                }
            }
        }
        fileMap.put("", "");
        return fileMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getCSCSFileMap(long projectId, String scssUuid, long patternId) {
        List<SCSPatternDAO> scsPatternDaoList = EditResourceUtil.INSTANCE.getSCSPatternDaoWithLSCDefined(projectId, scssUuid);
        Map<String, String> fileMap = new HashMap<>();
        scsPatternDaoList.stream().filter(dao -> {
            return dao.getCscUuid() != null;
        }).filter(dao -> {
            return !dao.getCscUuid().isEmpty();
        }).filter(dao -> {
            return dao.getSCSPatternId().equals(patternId);
        }).forEach(dao -> {
            VMFile cscFile = EditResourceUtil.INSTANCE.getVMFile(dao.getCscUuid(), projectId);
            fileMap.put(dao.getCscUuid(), cscFile.getName() + "." + cscFile.getExtensionStr());
        });
        fileMap.put("", "");
        return fileMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobStatusInfo getJobStatusInfo(long fileId, String hash) {
        JobStatus jobStatus = EditResourceUtil.INSTANCE.getJobStatusByInputFileId(fileId, hash);
        if (jobStatus != null) {
            return new JobStatusInfo(jobStatus.getStatus(), jobStatus.getInfomation(), jobStatus.getHash());
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends AbstractRootElement & SettingInterface> byte[] getFileContentIsSetting(long fileId) {
        File settingFile = EditResourceUtil.INSTANCE.getFile(fileId);
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(settingFile.getContent());
        HeavyTaskContentGetter<T> getter = new HeavyTaskContentGetter<>();
        if (root instanceof SettingInterface) {
            getter.setSettings((T) root, settingFile.getProjectid().longValue());
        }
        return EditResourceUtil.INSTANCE.convertToBinary(root);
    }
}
