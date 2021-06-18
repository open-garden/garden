package com.zipc.garden.job.tp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.Directory;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.FileReferences;
import com.zipc.garden.webplatform.dao.Project;
import com.zipc.garden.webplatform.server.EditResourceUtil;

public class TSDPatternGeneratorTest {
    private TSDPatternGenerator sut;

    private static long projectId;

    private long jobId = 1L;

    private long outputFileId = 1L;

    private Set<String> uuidList = new HashSet<String>();

    // TODO EditResourceUtil#setReferencesをプロダクトコードとして呼び出すように変えるべきだ
    // 元のメソッドから部分的に切り出す必要があり、他処理へ影響が出るのが怖いのでこのままにした
    private void setReferences(Long projectId, String fileUuid, String refUuid, String refExt) {
        Transaction tx = null;
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<File> criteria = builder.createQuery(File.class);
            Root<File> fileTbl = criteria.from(File.class);
            List<Predicate> preList = new ArrayList<Predicate>();
            preList.add(builder.equal(fileTbl.get("uuid"), refUuid));
            preList.add(builder.isFalse(fileTbl.get("deleteFlg")));
            criteria.select(fileTbl).where(preList.toArray(new Predicate[preList.size()]));
            List<File> refFiles = session.createQuery(criteria).getResultList();
            FileReferences reference = new FileReferences();
            reference.setFileuuid(fileUuid);
            reference.setProject(new Project(projectId));
            if (!refFiles.isEmpty()) {
                reference.setRefFile(refFiles.get(0));
                reference.setRefuuid(refFiles.get(0).getUuid());
                reference.setRefextension(refFiles.get(0).getExtension());
                reference.setRefproject(new Project(refFiles.get(0).getProjectid()));
            } else {
                reference.setRefuuid(refUuid);
                reference.setRefextension(refExt);
                reference.setRefproject(new Project(projectId));
            }
            session.persist(reference);
            session.save(reference);
            tx.commit();
        }
    }

    private static void deleteProject(Long projectId) {
        Transaction tx = null;

        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaDelete<Project> delQuery = builder.createCriteriaDelete(Project.class);
            Root<Project> root = delQuery.from(Project.class);
            delQuery.where(builder.equal(root.get("id"), projectId));
            session.createQuery(delQuery).executeUpdate();

            String delDirSql = "update Directory dir set dir.deleteFlg = true where dir.projectid in :projectid";
            session.createQuery(delDirSql).setParameter("projectid", projectId).executeUpdate();

            tx.commit();
        }
    }

    @BeforeClass
    public static void initialize() {
        Directory dir = new Directory();
        dir = EditResourceUtil.INSTANCE.createProject("testProject", null);
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            projectId = EditResourceUtil.INSTANCE.getProjectId(dir, session);
        }

    }

    @Test
    public void Test_2TCFile() {
        String tc1FileUuid = "tc1";
        String tpsFileUuid = "tps";
        uuidList.add(tpsFileUuid);
        setReferences(projectId, tpsFileUuid, tc1FileUuid, "tc");

        // create tc ref
        String tc2FileUuid = "tc2";
        uuidList.add(tc1FileUuid);
        setReferences(projectId, tc1FileUuid, tc2FileUuid, "tc");
        String fmFileUuid = "FM";
        uuidList.add(tc2FileUuid);
        setReferences(projectId, tc2FileUuid, fmFileUuid, "fm");

        sut = new TSDPatternGenerator(jobId, projectId, outputFileId);
        String uuid = sut.findFile(tpsFileUuid, "fm");
        assertThat(uuid, is(fmFileUuid));
    }

    @Test
    public void Test_NoTCFile() {
        String fmFileUuid = "FM";
        String tpsFileUuid = "tps";
        uuidList.add(tpsFileUuid);
        setReferences(projectId, tpsFileUuid, fmFileUuid, "fm");

        sut = new TSDPatternGenerator(jobId, projectId, outputFileId);
        String uuid = sut.findFile(tpsFileUuid, "fm");
        assertThat(uuid, is(fmFileUuid));
    }

    @Test
    public void Test_4TCFile() {
        String tc1FileUuid = "tc1";
        String tpsFileUuid = "tps";
        uuidList.add(tpsFileUuid);
        setReferences(projectId, tpsFileUuid, tc1FileUuid, "tc");

        // create tc ref
        String tc2FileUuid = "tc2";
        uuidList.add(tc1FileUuid);
        setReferences(projectId, tc1FileUuid, tc2FileUuid, "tc");
        String tc3FileUuid = "tc3";
        uuidList.add(tc2FileUuid);
        setReferences(projectId, tc2FileUuid, tc3FileUuid, "tc");
        String tc4FileUuid = "tc4";
        uuidList.add(tc3FileUuid);
        setReferences(projectId, tc3FileUuid, tc4FileUuid, "tc");
        String fmFileUuid = "FM";
        uuidList.add(tc4FileUuid);
        setReferences(projectId, tc4FileUuid, fmFileUuid, "fm");

        sut = new TSDPatternGenerator(jobId, projectId, outputFileId);
        String uuid = sut.findFile(tpsFileUuid, "fm");
        assertThat(uuid, is(fmFileUuid));
    }

    @Test
    public void Test_ManyReferenceFile() {
        String tpsRef1Uuid = "tpsRef1Uuid";
        String tc1FileUuid = "tc1FileUuid";
        String tc2FileUuid = "tc2FileUuid";
        String tpsFileUuid = "tpsFileUuid";
        uuidList.add(tpsFileUuid);

        setReferences(projectId, tpsFileUuid, tpsRef1Uuid, "fmc");
        setReferences(projectId, tpsFileUuid, tc1FileUuid, "tc");
        setReferences(projectId, tpsFileUuid, tc2FileUuid, "tc");

        // create tc ref
        String tc1Uuid1 = "tc1Uuid1";
        String fmFileUuid = "fmFileUuid";
        uuidList.add(tc1FileUuid);
        setReferences(projectId, tc1FileUuid, tc1Uuid1, "tc");
        setReferences(projectId, tc1FileUuid, fmFileUuid, "fm");

        String tc2Uuid = "tc2Uuid";
        uuidList.add(tc2FileUuid);
        setReferences(projectId, tc2FileUuid, tc2Uuid, null);

        sut = new TSDPatternGenerator(jobId, projectId, outputFileId);
        String uuid = sut.findFile(tpsFileUuid, "fm");
        assertThat(uuid, is(fmFileUuid));
    }

    @After
    public void deleteReference() {
        Transaction tx = null;
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            for (String fileUuid : uuidList) {
                EditResourceUtil.INSTANCE.deleteReference(projectId, fileUuid, session);
            }

            tx.commit();
        }

        uuidList.clear();
    }

    @AfterClass
    public static void deleteProject() {
        deleteProject(projectId);
    }

}
