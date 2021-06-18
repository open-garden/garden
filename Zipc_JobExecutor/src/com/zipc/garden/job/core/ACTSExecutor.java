package com.zipc.garden.job.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * ACTS execution class
 */
public class ACTSExecutor {

    /** Working directory name */
    private static final String dirName = "garden";

    /** The name used when filing the ACTS input value for {@link #execute(String, long)}. */
    private static final String inFileName = "actsin";

    /**
     * Acceptable warning message. <br>
     * If a warning message other than the one specified here is output, the execution result will be null.
     */
    private static final String warningMsg = "";

    /**
     * ACTS is executed and a combination using the N-wise is created.
     * @param inputData ACTS input value
     * @param nWise N-Wise value
     * @return ACTS execution result
     */
    public String execute(String inputData, long nWise) {

        String os = System.getProperty("os.name").toLowerCase();
        String errorMsg = "";
        String workPath;

        // 作業ディレクトリを作成する
        if (os.startsWith("linux")) {
            workPath = System.getProperty("user.home") + "/" + dirName + "/";
        } else {
            workPath = "c:\\" + dirName + "\\";
        }

        File dir = new File(workPath);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                return null;
            }
        }

        String inputPath = workPath + inFileName + "_" + UUID.randomUUID().toString() + ".txt";
        String outputPath = workPath + "output.txt";

        File input = new File(inputPath);

        try {
            // 入力データをファイル化する
            BufferedWriter bw = new BufferedWriter(new FileWriter(input));
            bw.write(inputData);
            bw.close();

            ProcessBuilder pb;

            // ACTSを実行する
            // -Ddoi : 強度の指定、-Doutput : 出力ファイル形式の指定
            if (os.equals("linux")) {
                pb = new ProcessBuilder("java", "-Ddoi=" + nWise, "-Doutput=csv", "-jar", "./acts_cmd_2.92.jar", "cmd", inputPath, outputPath);
            } else {
                pb = new ProcessBuilder("java", "-Ddoi=" + nWise, "-Doutput=csv", "-jar", "./acts_cmd_2.92.jar", "cmd", inputPath, outputPath);
            }

            Process process = pb.start();

            // 標準出力を読み出すスレッドを起動
            StdOutThread stdOutThread = new StdOutThread(process);

            // 標準エラー出力を取得
            errorMsg = stdOutThread.getErrorMsg();

            // InputDataを削除する
            input.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (!errorMsg.equals("") && !errorMsg.contains(warningMsg)) {
            return null;
        }

        // Header情報および「"」記号を取り除き、Actsの出力ファイルを読みこむ
        String actsData = "";

        try {
            File output = new File(outputPath);
            BufferedReader br = new BufferedReader(new FileReader(output));

            // headerをskip
            while (true) {
                if (br.readLine().equals("# ****************************************************")) {
                    break;
                }
            }

            String str = "";
            while ((str = br.readLine()) != null) {
                // 1列目の" "を削除する
                actsData += str.replaceAll("\"", "") + System.lineSeparator();
            }
            br.close();
            // OutputDataを削除する
            output.delete();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return actsData;
    }
}
