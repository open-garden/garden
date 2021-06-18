package com.zipc.garden.job.scs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.cb.CBFile;
import com.zipc.garden.model.cb.CBLogic;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.scs.SCSFactory;
import com.zipc.garden.model.scs.SCSPatternReference;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tp.TPTSDPattern;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that creates the FPS and BPS patterns used for the combination.
 */
public class SCSPtnRefGenerator {

    /**
     * <pre>
     * An SCS pattern reference containing the pattern ID information entered in the SCSS editor is created and retrieved.
     * If the pattern ID is not entered, all pattern information will be created / acquired.
     * Patterns that do not exist in RDF are not created.
     * </pre>
     * 
     * @param cbRoot SCSS editor information
     * @param patternSettingMap Pattern setting information acquired from RDF
     * @param projectId project id
     * @return key: Pattern information existing on RDF / value: uuid (fps or bps)
     */
    public Map<SCSPatternReference, String> execute(CBRoot cbRoot, Map<String, AbstractSetting> patternSettingMap, long projectId) {
        Map<SCSPatternReference, String> scsPtnRefMap = new LinkedHashMap<SCSPatternReference, String>();
        List<CBFile> allCBFile = getAllCBFile(cbRoot);
        for (String uuid : patternSettingMap.keySet()) {
            List<String> patternIdList = null;
            CBFile cbFile = allCBFile.stream().filter(f -> uuid.equals(f.getUuid())).findFirst().orElse(null);
            if (cbFile != null && cbFile.getPattern() != null && !cbFile.getPattern().isEmpty()) {
                patternIdList = Arrays.asList(cbFile.getPattern().split(","));
            }
            AbstractSetting setting = patternSettingMap.get(uuid);
            long fileId = EditResourceUtil.INSTANCE.getVMFile(uuid, projectId).getId();
            if (setting instanceof TPSetting) {
                TPSetting tpSetting = (TPSetting) setting;
                for (TPTSDPattern ptn : tpSetting.getPatterns()) {
                    if (patternIdList != null && !patternIdList.contains(ptn.getId())) {
                        continue;
                    }
                    SCSPatternReference scsRefPtn = SCSFactory.eINSTANCE.createSCSPatternReference();
                    scsRefPtn.setFileId(fileId);
                    scsRefPtn.setPatternId(Long.parseLong(ptn.getId()));
                    scsPtnRefMap.put(scsRefPtn, uuid);
                }
            } else if (setting instanceof BPSetting) {
                BPSetting bpSetting = (BPSetting) setting;
                for (BPBehaviorPattern ptn : bpSetting.getPattern()) {
                    if (patternIdList != null && !patternIdList.contains(ptn.getId())) {
                        continue;
                    }
                    SCSPatternReference scsRefPtn = SCSFactory.eINSTANCE.createSCSPatternReference();
                    scsRefPtn.setFileId(fileId);
                    scsRefPtn.setPatternId(Long.parseLong(ptn.getId()));
                    scsPtnRefMap.put(scsRefPtn, uuid);
                }
            } else {
                return null;
            }
        }
        return scsPtnRefMap;
    }

    /**
     * Gets all the files set in the specified CBRoot.
     * @param cbRoot specified CBRoot.
     * @return All files set to the specified CBRoot.
     */
    private List<CBFile> getAllCBFile(CBRoot cbRoot) {
        List<CBFile> ret = new ArrayList<CBFile>();
        return getCBFile(ret, Arrays.asList(cbRoot.getLogic()));
    }

    /**
     * Recursively searches the specified CBLogic and retrieves the file.
     * @param cbFiles Storage destination of the acquired file.
     * @param cbLogics specified CBLogic
     * @return The file set in the specified CBLogic.
     */
    private List<CBFile> getCBFile(List<CBFile> cbFiles, List<CBLogic> cbLogics) {
        for (CBLogic cbLogic : cbLogics) {
            if (cbLogic.getFile() != null && !cbLogic.getFile().isEmpty()) {
                cbFiles.addAll(cbLogic.getFile());
            }
            if (cbLogic.getChildren() != null && !cbLogic.getChildren().isEmpty()) {
                cbFiles.addAll(getCBFile(cbFiles, cbLogic.getChildren()));
            }
        }
        return cbFiles;
    }
}
