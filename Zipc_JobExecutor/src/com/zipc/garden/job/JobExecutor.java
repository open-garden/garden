package com.zipc.garden.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;

import com.zipc.garden.core.ZGResourceFactory;
import com.zipc.garden.job.core.Z3Loader;
import com.zipc.garden.job.ontology.RDFGarbageCollector;
import com.zipc.garden.model.arc.ARCPackage;
import com.zipc.garden.model.bp.BPPackage;
import com.zipc.garden.model.bps.BPSPackage;
import com.zipc.garden.model.cb.CBPackage;
import com.zipc.garden.model.cgen.CGENPackage;
import com.zipc.garden.model.core.COREPackage;
import com.zipc.garden.model.csc.CSCPackage;
import com.zipc.garden.model.fm.FMPackage;
import com.zipc.garden.model.fmc.FMCPackage;
import com.zipc.garden.model.fmcs.FMCSPackage;
import com.zipc.garden.model.fsm.FSMPackage;
import com.zipc.garden.model.lgen.LGENPackage;
import com.zipc.garden.model.lsc.LSCPackage;
import com.zipc.garden.model.scd.SCDPackage;
import com.zipc.garden.model.scs.SCSPackage;
import com.zipc.garden.model.tc.TCPackage;
import com.zipc.garden.model.tp.TPPackage;
import com.zipc.garden.model.tps.TPSPackage;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.dao.Job;
import com.zipc.garden.webplatform.server.EditResourceUtil;

/**
 * <pre>
 * A class with an entry point.
 * The process of {@link #execute} is executed repeatedly.
 * </pre>
 */
public class JobExecutor {

    /**
     * The entrance to the JobExecutor process.
     * @param args Argument specified when the program starts.
     */
    public static void main(String args[]) {
        JobExecutor executor = new JobExecutor();
        executor.execute();
    }

    /** Job Id list that are executing. */
    private List<Long> executingList = new ArrayList<>();

    /** The interval at which the job runs. */
    private final int jobIntervalTimer = 5000;

    /** The interval at which RDF Garbage Collection is executed. */
    private final int garbageCollectionTimer = jobIntervalTimer * 12 * 5;

    /**
     * It counts until RDF garbage collection is performed.<br>
     * If the value of {@link #garbageCollectionTimer} is exceeded, <br>
     * RDF garbage collection is performed and this count returns to 0.
     */
    private int currentGarbageCollectionTime = 0;

    /**
     * Runs the job at the specified intervals.
     */
    public void execute() {
        initialize();
        reRegistJob(); // JOBの中で実行中のままJobExecutorが落ちた場合は再登録
        removeGarbageJob(); // JOBの中でゴミ化したJOBを削除
        while (true) {
            try {
                Thread.sleep(jobIntervalTimer);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            registRDFGarbageCollector(); // GarbageCollectorを起動
            List<Job> jobs = getAllJobInfo();
            updateExecutingList(jobs);
            printExecutingList();
            printAllJob(jobs);
            removeDeletedFileJob(jobs); // JOBが完了した後にRDBにある結果確認用ファイルを削除したJOBを削除する
            for (Job job : jobs) {
                if (job.getStatus() == Job.STATUS_NOEXEC) {
                    if (executingList.size() < 100) {
                        executingList.add(job.getId());
                        JobExecutorThread executor = JobExecutorUtil.createThread(job);
                        Thread thread = new Thread(executor);
                        updateJobStatus(job.getId(), Job.STATUS_EXECUTING);
                        appendLog(String.format("Job[%d] Type[%d] inputFile[%s] outputFile[%s] start", job.getId(), job.getType(), job.getInputFileId(), job.getOutputFileId()));
                        print();
                        thread.start();
                    }
                }
            }
            appendLog("JobExecutor sleep");
            print();
        }
    }

    /**
     * <pre>
     * Updates the status of the running job to NO_EXEC (waiting for execution).
     * JOBの中で実行中になっているものを再度実行するためNO_EXECにする
     * </pre>
     */
    private void reRegistJob() {
        List<Job> jobs = getAllJobInfo();
        jobs.stream().filter(job -> {
            switch (job.getStatus()) { // JOBが実行中のままJobExecutorが終了していたら再登録
            case Job.STATUS_EXECUTING:
                return true;
            default:
                return false;
            }
        }).forEach(job -> {
            EditResourceUtil.INSTANCE.updateJobStatus(job.getId(), Job.STATUS_NOEXEC);
        });
    }

    /**
     * <pre>
     * Delete unnecessary Jobs from the table.
     * Jobの中でゴミ化したJobを削除する
     * </pre>
     */
    private void removeGarbageJob() {
        List<Job> jobs = getAllJobInfo();
        jobs.stream().filter(job -> {
            switch (job.getType()) { // JOB完了時に自動で消えるJOBを抽出
            case Job.TYPE_REMOVE_FPS:
            case Job.TYPE_REMOVE_BPS:
            case Job.TYPE_REMOVE_SCSS:
            case Job.TYPE_REMOVE_RDF:
                return true;
            default:
                return false;
            }
        }).filter(job -> {
            switch (job.getStatus()) { // JOBが未実行以外で残っていたら削除
            case Job.STATUS_NOEXEC:
                return false;
            default:
                return true;
            }
        }).forEach(job -> {
            EditResourceUtil.INSTANCE.removeJob(job.getId());
        });
    }

    /**
     * <pre>
     * Periodically perform RDF Garbage Collection in JOB registration format.
     * 定期的にRDFのGarbageCollectionをJOB登録形式で行う
     * </pre>
     */
    private void registRDFGarbageCollector() {
        currentGarbageCollectionTime += jobIntervalTimer;
        if (currentGarbageCollectionTime >= garbageCollectionTimer) { // 指定時間経過していたら実行
            currentGarbageCollectionTime = 0;
            RDFGarbageCollector gc = new RDFGarbageCollector();
            gc.execute();
        }
    }

    /**
     * <pre>
     * Register the created EMF class in "Resource.Factory" and "EPackage".
     * Load Z3 into the system.
     * </pre>
     */
    private void initialize() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("arc", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("bp", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("bps", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("cgen", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("core", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("csc", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fm", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fmc", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fmcs", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("fsm", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("lgen", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("lsc", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("scd", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("tc", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("tp", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("scs", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("tps", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("cb", new ZGResourceFactory());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("scs", new ZGResourceFactory());

        EPackage.Registry.INSTANCE.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(ARCPackage.eNS_URI, ARCPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(BPPackage.eNS_URI, BPPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(BPSPackage.eNS_URI, BPSPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(CGENPackage.eNS_URI, CGENPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(COREPackage.eNS_URI, COREPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(CSCPackage.eNS_URI, CSCPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(FMPackage.eNS_URI, FMPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(FMCPackage.eNS_URI, FMCPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(FMCSPackage.eNS_URI, FMCSPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(FSMPackage.eNS_URI, FSMPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(LGENPackage.eNS_URI, LGENPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(LSCPackage.eNS_URI, LSCPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(SCDPackage.eNS_URI, SCDPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(TCPackage.eNS_URI, TCPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(TPPackage.eNS_URI, TPPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(TPSPackage.eNS_URI, TPSPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(CBPackage.eNS_URI, CBPackage.eINSTANCE);
        EPackage.Registry.INSTANCE.put(SCSPackage.eNS_URI, SCSPackage.eINSTANCE);

        Z3Loader.loadZ3Lib();
    }

    /**
     * Get all records in the job table.
     * @return All records in the job table
     */
    private List<Job> getAllJobInfo() {
        return EditResourceUtil.INSTANCE.getAllJobInfo();
    }

    /**
     * Deleted jobs and terminated jobs are excluded from the running job list ({@link #executingList}).
     * @param jobs All records in the job table
     */
    private void updateExecutingList(List<Job> jobs) {
        Iterator<Long> itr = executingList.iterator();
        while (itr.hasNext()) {
            Long jobId = itr.next();
            Job matchJob = jobs.stream().filter(job -> job.getId().equals(jobId)).findFirst().orElse(null);
            if (null == matchJob) { // テーブルから削除済のJOBは実行中リストから除外
                itr.remove();
            } else {
                if ((matchJob.getStatus() == Job.STATUS_COMPLETE) || matchJob.getStatus() == Job.STATUS_ERROR) { // 処理終了JOBは実行中リストから除外
                    itr.remove();
                }
            }
        }
    }

    /**
     * <pre>
     * If the file that created the specified job has been deleted, it will be deleted from the Job table.
     * Jobが完了する前にRDB上のファイルを削除したJobを削除する
     * </pre>
     * 
     * @param jobs All records in the job table
     */
    private void removeDeletedFileJob(List<Job> jobs) {
        jobs.stream().filter(job -> {
            switch (job.getType()) { // RDBのファイルで進捗刈り取りをするJOBを抽出
            case Job.TYPE_FP:
            case Job.TYPE_BP:
            case Job.TYPE_SCS:
                return true;
            default:
                return false;
            }
        }).filter(job -> {
            switch (job.getStatus()) { // JOBが完了しているJOBを抽出
            case Job.STATUS_COMPLETE:
            case Job.STATUS_ERROR:
                return true;
            default:
                return false;
            }
        }).filter(job -> {
            File file = EditResourceUtil.INSTANCE.getFile(job.getInputFileId());
            if (null == file) { // InputFileが削除済のJOBを抽出
                return true;
            } else {
                return false;
            }
        }).forEach(job -> {
            EditResourceUtil.INSTANCE.removeJob(job.getId());
        });
    }

    /**
     * The list of executing job IDs is output to the log.
     */
    private void printExecutingList() {
        if (executingList.isEmpty()) {
            return;
        }
        appendLog("execute[");
        appendLog(executingList.stream().map(e -> e.toString()).collect(Collectors.joining(" ")));
        appendLog("]");
        print();
    }

    /**
     * Updates the status of job table records that match the specified ID.
     * @param jobId specified ID.
     * @param status It will be updated to this status.
     */
    private void updateJobStatus(long jobId, int status) {
        EditResourceUtil.INSTANCE.updateJobStatus(jobId, status);
    }

    /**
     * All the contents of the specified job list are output to the log.
     * @param jobs specified job list
     */
    private void printAllJob(List<Job> jobs) {
        jobs.forEach(job -> {
            appendLog("Job ID[" + job.getId() + "]");
            appendLog(" Type[" + job.getType() + "]");
            appendLog(" InputFileId[" + job.getInputFileId() + "]");
            appendLog(" OutputFileId[" + job.getOutputFileId() + "]");
            appendLog(" Status[" + job.getStatus() + "]");
            appendLog(" Message[" + job.getMessage() + "]");
            print();
        });
    }

    /** Date / time format ("yyyy/MM/dd HH:mm:ss") */
    private SimpleDateFormat datetime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /** Log contents of jobs that are executed at regular intervals. */
    private StringBuilder logCache = new StringBuilder();

    /**
     * <pre>
     * Outputs / clears the contents of {@link #logCache}.
     * It is output with the date and time in Japan.
     * </pre>
     */
    private void print() {
        System.out.println(datetime.format(Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo")).getTime()) + " " + logCache.toString());
        logCache.setLength(0);
    }

    /**
     * Appends the contents of the specified log to {@link #logCache}.
     * @param log Log to append.
     */
    private void appendLog(String log) {
        logCache.append(log);
    }
}
