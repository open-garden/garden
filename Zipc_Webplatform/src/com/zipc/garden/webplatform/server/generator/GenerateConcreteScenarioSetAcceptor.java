package com.zipc.garden.webplatform.server.generator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

import com.zipc.garden.model.cscs.CSCSRoot;
import com.zipc.garden.model.scs.SCSPattern;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.server.EditResourceUtil;
import com.zipc.garden.webplatform.shared.Extension;
import com.zipc.garden.webplatform.shared.UserInfo;
import com.zipc.garden.webplatform.shared.resource.VMFile;

/**
 * JOB that generates concrete-scenarioset is added to JOB table.<br>
 * Also, we will create a concrete-scenarioset file.
 */
public class GenerateConcreteScenarioSetAcceptor {

    /** ID of the project that created the SCSS file */
    long projectId;

    /** scenarioset-setting File ID */
    long settingFileId;

    /** One combination pattern of scenarioset */
    SCSPattern scsPattern;

    /** User information of logged in */
    UserInfo userInfo;

    /** scenarioset file record */
    File scsFile;

    /** scenarioset file info */
    VMFile scsVMFile;

    /** concrete-scenarioset file info */
    VMFile cscsVMFile;

    /** EMF root model for concrete-scenarioset */
    CSCSRoot cscsRoot;

    /** concrete-scenarioset File ID */
    long cscsFileId;

    /**
     * concrete-scenarioset generation process is executed.<br>
     * Here, the concrete-scenarioset file is created and the JOB is registered.
     * @param settingFileId {@link #settingFileId}
     * @param pattern {@link #pattern}
     * @param userInfo {@link #userInfo}
     * @return The concrete-scenarioset file you created. If not created, null is returned.
     */
    public VMFile execute(long settingFileId, byte[] pattern, UserInfo userInfo) {
        System.out.println("settingFileId:" + settingFileId);
        this.settingFileId = settingFileId;
        this.userInfo = userInfo;
        this.scsFile = EditResourceUtil.INSTANCE.getFile(settingFileId);
        BinaryResourceImpl r = new BinaryResourceImpl();
        ByteArrayInputStream bi = new ByteArrayInputStream(pattern);
        try {
            r.load(bi, null);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        this.scsPattern = (SCSPattern) r.getContents().get(0);

        if (!createCSCSFile()) {
            return null;
        }
        return cscsVMFile;
    }

    /**
     * Create an empty CSCS file and register it in the DB. <br>
     * The job record is added in the waiting state.
     * @return True if successful
     */
    private boolean createCSCSFile() {
        if (!createDefaultCSCSFile()) {
            return false;
        }
        if (!saveCSCS()) {
            return false;
        }
        if (!addJob()) {
            return false;
        }
        return true;
    }

    /**
     * Create a default concrete-scenarioset file. <br>
     * Only the meta information is set here.
     * @return True if successful
     */
    private boolean createDefaultCSCSFile() {
        // read SCS VMFile
        scsVMFile = new VMFile();
        scsVMFile = EditResourceUtil.INSTANCE.getVMFile(settingFileId);

        // set File
        long dirId = EditResourceUtil.INSTANCE.getDirId(scsVMFile.getId());
        String cscsFileName = scsVMFile.getName();
        cscsVMFile = new VMFile();
        cscsVMFile.setName(cscsFileName + "_" + scsPattern.getId());
        cscsVMFile.setExtension(Extension.CSCS);
        long num = EditResourceUtil.INSTANCE.getFileNumber(dirId, cscsVMFile);
        cscsVMFile.setName(num == 0 ? cscsVMFile.getName() : cscsVMFile.getName() + "_" + num);

        // set FileReferences
        List<Long> refList = new ArrayList<>();
        refList.add(scsVMFile.getId());

        // create File and File References
        long fileId = EditResourceUtil.INSTANCE.createFile(dirId, cscsVMFile, refList, userInfo);
        cscsFileId = fileId;
        cscsVMFile.setId(fileId);
        System.out.println("outputFileId:" + cscsFileId);

        this.projectId = EditResourceUtil.INSTANCE.getProjectId(scsVMFile.getId());

        return true;
    }

    /**
     * Put the initial status in CSCSRoot and save it back in DB.
     * @return True if successful
     */
    private boolean saveCSCS() {
        // read CSCSFile from DB
        byte[] data = EditResourceUtil.INSTANCE.getFileContent(cscsFileId);
        cscsRoot = (CSCSRoot) EditResourceUtil.INSTANCE.convertToRootElement(data);

        // set executing status
        cscsRoot.setStatus(Job.STATUS_NOEXEC);

        // save CSCSRoot
        BinaryResourceImpl r = new BinaryResourceImpl();
        r.getContents().add(cscsRoot);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            r.save(out, null);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        EditResourceUtil.INSTANCE.saveFile(cscsFileId, out.toByteArray(), userInfo);

        // save SCS File
        VMFile cscsFile = EditResourceUtil.INSTANCE.getVMFile(cscsFileId);
        scsPattern.setCscUuid(cscsFile.getUuid());
        scsPattern.setCscFileName(cscsVMFile.getName() + "." + cscsVMFile.getExtensionStr());
        EditResourceUtil.INSTANCE.saveSCSPattern(scsVMFile.getId(), scsPattern);
        return true;
    }

    /**
     * JOB for concrete-scenarioset generation is added in the waiting state.
     * @return True if successful
     */
    private boolean addJob() {
        EditResourceUtil.INSTANCE.addJob(settingFileId, scsFile, cscsFileId, Job.TYPE_CSCS, projectId);
        return true;
    }
}
