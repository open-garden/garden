package com.zipc.garden.job.cscs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;

import com.zipc.garden.job.JobExecutorThread;
import com.zipc.garden.job.tp.TCUtil;
import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.cscs.CSCSRoot;
import com.zipc.garden.webplatform.dao.CSCSPatternDAO;
import com.zipc.garden.webplatform.dao.SCSPatternDAO;
import com.zipc.garden.webplatform.dsl.sc.lib.RangeValueExtensions;
import com.zipc.garden.webplatform.dsl.sc.sCModel.Goal;
import com.zipc.garden.webplatform.dsl.sc.sCModel.Range;
import com.zipc.garden.webplatform.dsl.sc.sCModel.RoutesEntity;
import com.zipc.garden.webplatform.dsl.sc.sCModel.SCRoot;
import com.zipc.garden.webplatform.dsl.sc.sCModel.Scenario;
import com.zipc.garden.webplatform.dsl.sc.sCModel.Start;
import com.zipc.garden.webplatform.dsl.sc.web.util.SCModelUtils;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * A class that creates a Concrete Scenario Set.
 */
public class CSCSPatternGenerator extends JobExecutorThread {

    /** Model of cscs file */
    private CSCSRoot cscsRoot;

    /** Model converted from LSC. */
    private SCRoot scRoot;

    /** Parameter information set in "Range" of LSC. */
    List<List<RangeParam>> cscsPatterns;

    /** SCS pattern with the LSC string to convert */
    private SCSPatternDAO scsPatternDAO;

    /** CSCS pattern to be registered in DB. */
    private List<CSCSPatternDAO> cscsPtnDaoList;

    /** Error message when {@link #readCscs()} fails. */
    private String READ_CSCS_FAIL = "read .cscs fail";

    /** Error message when {@link #readScs()} fails. */
    private String READ_SCS_FAIL = "read .scs fail";

    /**
     * constructor
     * @param jobId job id
     * @param projectId project id
     * @param outputFileId cscs file id
     */
    public CSCSPatternGenerator(long jobId, long projectId, long outputFileId) {
        super(jobId, projectId, outputFileId);
    }

    /**
     * <pre>
     * Executes the CSCS pattern generation process.
     * 
     * {@inheritDoc}
     * </pre>
     */
    @Override
    protected boolean execute(long jobId) {
        return generate();
    }

    /**
     * <pre>
     * Generate a CSCS pattern.
     * 
     * CSCSPatternを生成する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    public boolean generate() {
        // Read CSCS/SCS
        if (!readFiles()) {
            return false;
        }

        // Read LSC
        if (!readLSC()) {
            return false;
        }

        // Create CSCSPattern
        if (!createCSCSPattern()) {
            return false;
        }

        // Create CSCSPatternDao
        if (!createCSCSPtnDaoList()) {
            return false;
        }

        // register
        if (!createPattern()) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Convert the LSC string to the SCRoot model and set it to {@link #scRoot}.
     * 
     * LSCの文字列をSCRootモデルに変換する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean readLSC() {
        String lsc = scsPatternDAO.getLsc();
        scRoot = SCModelUtils.getInstance().load(lsc, scsPatternDAO.getFileUuid());
        if (scRoot == null) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Create a CSCS pattern based on the value set in "Range" of LSC.
     * 
     * LSCのRangeに設定されたPatternを生成する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean createCSCSPattern() {
        cscsPtnDaoList = new ArrayList<CSCSPatternDAO>();
        cscsPatterns = createCSCSPattern(scRoot);
        if (cscsPatterns == null || cscsPatterns.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Create a combination pattern for the concrete scenario set.
     * @param root {@link #scRoot}
     * @return Combination of concrete scenario set patterns
     */
    private List<List<RangeParam>> createCSCSPattern(SCRoot root) {
        List<List<RangeParam>> results = new ArrayList<List<RangeParam>>();
        for (Scenario s : root.getScenarios()) {
            for (Range r : s.getRange()) {
                List<RangeParam> tmplList = new ArrayList<RangeParam>();
                if (r.getAverage() != null) {
                    tmplList.add(new RangeParam(r.getId(), Name.AVERAGE, r.getAverage()));
                }
                if (r.getMax() != null) {
                    tmplList.add(new RangeParam(r.getId(), Name.MAX, r.getMax()));
                }
                if (r.getMin() != null) {
                    tmplList.add(new RangeParam(r.getId(), Name.MIN, r.getMin()));
                }
                if (r.getMedian() != null) {
                    tmplList.add(new RangeParam(r.getId(), Name.MEDIAN, r.getMedian()));
                }
                if (r.getMode() != null) {
                    tmplList.add(new RangeParam(r.getId(), Name.MODE, r.getMode()));
                }
                if (!r.getValue().isEmpty()) {
                    r.getValue().forEach(v -> {
                        tmplList.add(new RangeParam(r.getId(), Name.VALUE, v));
                    });
                }
                results = addAllCombinationPattern(results, tmplList);
            }

        }
        return results;
    }

    /**
     * Creates all combination patterns of the specified list1 and list2.
     * @param list1 Combination pattern information being generated.
     * @param list2 Pattern information to be combined with list1.
     * @return Combination of concrete scenario set patterns
     */
    private List<List<RangeParam>> addAllCombinationPattern(List<List<RangeParam>> list1, List<RangeParam> list2) {
        if (list1 == null || list1.isEmpty()) {
            return list2.stream().map(l2 -> new ArrayList<RangeParam>(Arrays.asList(l2))).collect(Collectors.toList());
        }
        return list1.stream().flatMap(l1 -> list2.stream().map(l2 -> {
            ArrayList<RangeParam> tmp = new ArrayList<RangeParam>();
            tmp.addAll(l1);
            tmp.add(l2);
            return tmp;
        })).collect(Collectors.toList());
    }

    /**
     * <pre>
     * Create a CSCS pattern to be registered in the DB.
     * 
     * DBに登録する一覧を生成する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean createCSCSPtnDaoList() {
        cscsPtnDaoList = new ArrayList<CSCSPatternDAO>();
        for (int i = 0; i < cscsPatterns.size(); i++) {
            String pattern = createPatternString(cscsPatterns.get(i));
            String csc = createCSCText(cscsPatterns.get(i));

            CSCSPatternDAO dao = new CSCSPatternDAO();
            dao.setCsc(scsPatternDAO.getLsc());
            dao.setProjectId(projectId);
            dao.setCSCSPatternId(i + 1L);
            dao.setSCSPatternId(scsPatternDAO.getSCSPatternId());
            dao.setFileUuid(cscsRoot.getId());
            dao.setPattern(pattern);
            dao.setCsc(csc);
            cscsPtnDaoList.add(dao);
        }
        if (cscsPtnDaoList.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Create a string to set to "pattern" of CSCSPatternDAO.
     * 
     * パターンの文字列を生成する
     * </pre>
     * 
     * @param rangeParams
     * @return The character string to be set in "Pattern".
     */
    private String createPatternString(List<RangeParam> rangeParams) {
        String pattern = "";
        for (RangeParam param : rangeParams) {
            if (!"".equals(pattern)) {
                pattern += " , ";
            }
            pattern += param.getId();
            pattern += " ";
            if (param.getKey() == Name.VALUE) {
                pattern += removeZeros(param.getValue());
            } else {
                pattern += param.getKey().getValue();
            }
        }
        return pattern;
    }

    /**
     * <pre>
     * Generate a concrete scenario from a logical scenario
     * 
     * ロジカルシナリオからコンクリートシナリオを生成する
     * </pre>
     * 
     * @param rangeParams Range parameter of logical scenario
     * @return concrete scenario
     */
    private String createCSCText(List<RangeParam> rangeParams) {
        SCRoot tmp = EcoreUtil.copy(scRoot);
        List<RoutesEntity> RoutesEntityList = TCUtil.getAllContentsOfType(tmp, RoutesEntity.class);

        RoutesEntityList.forEach(r -> {
            rangeParams.forEach(p -> {
                Range range = RangeValueExtensions.INSTANCE.createRange(p.getValue());
                if (r.getVelocity().getId().equals(p.getId())) {
                    r.setVelocity(range);
                    return;
                }
                if (r instanceof Start) {
                    if (((Start) r).getShift().getId().equals(p.getId())) {
                        ((Start) r).setShift(range);
                        return;
                    }
                } else if (r instanceof Goal) {
                    if (((Goal) r).getShift().getId().equals(p.getId())) {
                        ((Goal) r).setShift(range);
                        return;
                    }
                }

            });
        });

        return SCModelUtils.getInstance().toText(tmp);
    }

    /**
     * <pre>
     * If the argument is an integer, zeros after the decimal point are excluded.
     * If it is not an integer, it will be returned without conversion.
     * </pre>
     * 
     * @param d Value to convert
     * @return Result of conversion
     */
    private String removeZeros(double d) {
        if (d == (int) d)
            return String.format("%d", (int) d);
        else
            return String.format("%s", d);
    }

    /**
     * <pre>
     * Register {@link #cscsPtnDaoList CSCSPattern} in the DB.
     * 
     * CSCSPatternをDBに登録する
     * </pre>
     * 
     * @return true: success / false: failure
     */
    public boolean createPattern() {
        return EditResourceUtil.INSTANCE.registerCSCSPatterns(cscsPtnDaoList);
    }

    /**
     * <pre>
     * Read the various files needed for generation from the DB.
     * 
     * generationに必要な各種ファイルをDBから読み込む
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean readFiles() {
        if (!readCscs()) {
            setErrorMessage(READ_CSCS_FAIL);
            return false;
        }
        if (!readScs()) {
            setErrorMessage(READ_SCS_FAIL);
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Read the CSCS file based on {@link JobExecutorThread#outputFileId} and set it to {@link #cscsRoot}.
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean readCscs() {
        long id = outputFileId;
        byte[] byteContent = EditResourceUtil.INSTANCE.getFileContent(id);
        cscsRoot = convertToCSCSRoot(byteContent);
        if (null == cscsRoot) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Get a specific SCS pattern from DB based on {@link JobExecutorThread#inputFileId scsFileId} and {@link JobExecutorThread#outputFileId cscsFileId}.
     * </pre>
     * 
     * @return true: success / false: failure
     */
    private boolean readScs() {
        scsPatternDAO = EditResourceUtil.INSTANCE.getSCSPatternDAO(projectId, inputFileId, outputFileId);
        if (null == scsPatternDAO) {
            return false;
        }

        return true;
    }

    /**
     * <pre>
     * Convert byte data read from DB to CSCSRoot.
     * 
     * DBから読み込んだbyteデータをCSCSRootに変換する
     * </pre>
     * 
     * @param data Byte data read from DB
     * @return CSCSRoot
     */
    private CSCSRoot convertToCSCSRoot(byte[] data) {
        AbstractRootElement root = EditResourceUtil.INSTANCE.convertToRootElement(data);
        if (root instanceof CSCSRoot) {
            return (CSCSRoot) root;
        }
        return null;
    }

    /**
     * Range An ENUM that manages the types of parameters.
     */
    protected enum Name {
        AVERAGE("average"), MAX("max"), MIN("min"), MEDIAN("median"), MODE("mode"), VALUE("value");

        /** value of enum */
        private String name;

        /**
         * constructor
         * @param name Value to set to {@link #name}
         */
        private Name(String name) {
            this.name = name;
        }

        /**
         * Get enum value.
         * @return {@link #name}
         */
        public String getValue() {
            return this.name;
        }
    }

    /**
     * A class that manages range parameter information.
     */
    protected class RangeParam {

        /** Range name */
        private String id;

        /** The type of range parameter. */
        private Name key;

        /** The setting value of the parameter. */
        private Double value;

        /**
         * constructor
         * @param id Value to set to {@link #id}
         * @param key Value to set to {@link #key}
         * @param value Value to set to {@link #value}
         */
        public RangeParam(String id, Name key, Double value) {
            this.id = id;
            this.key = key;
            this.value = value;
        }

        /**
         * Get the range name.
         * @return {#link {@link #id}
         */
        public String getId() {
            return id;
        }

        /**
         * Set the Range name.
         * @param id Range name.
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * Gets the type of range parameter.
         * @return {#link {@link #key}
         */
        public Name getKey() {
            return key;
        }

        /**
         * Sets the type of range parameter.
         * @param key
         */
        public void setKey(Name key) {
            this.key = key;
        }

        /**
         * Gets the settings value of parameter.
         * @return {@link #value}
         */
        public Double getValue() {
            return value;
        }

        /**
         * Sets the settings value of parameter.
         * @param value
         */
        public void setValue(Double value) {
            this.value = value;
        }

        /**
         * Get the character string that summarizes the Range parameter information.
         * @return {@link #id} + ":" + {@link #key} + ":" + {@link #value}
         */
        @Override
        public String toString() {
            return this.id + ":" + this.key.getValue() + ":" + this.getValue();
        }
    }

}
