package com.zipc.garden.job.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * Class to read standard output and standard error output
 * 
 * 標準出力および標準エラー出力を読み出すクラス
 * </pre>
 */
public class StdOutThread {

    /** The process to perform. */
    private Process process = null;

    /** A thread that reads standard output. */
    OutputStreamThread outputStreamThread = new OutputStreamThread();

    /** A thread that reads standard error output. */
    ErrorStreamThread errorStreamThread = new ErrorStreamThread();

    /**
     * constructor.
     * @param process The process to perform.
     * @throws Exception
     */
    public StdOutThread(Process process) throws Exception {

        this.process = process;

        // 標準出力を読み出すスレッドを起動
        outputStreamThread.start();

        // 標準エラー出力を読み出すスレッドを起動
        errorStreamThread.start();

        process.waitFor(30, TimeUnit.SECONDS);
    }

    /**
     * Gets the character string read from standard output.
     * @return String read from standard output. <br>
     *         標準出力から読み出した文字列
     * @throws Exception
     */
    public String getOutputData() throws Exception {
        outputStreamThread.join();
        return outputStreamThread.getOutput();
    }

    /**
     * Gets the string read from standard error output.
     * @return String read from standard error output.<br>
     *         標準エラー出力から読み出した文字列
     * @throws Exception
     */
    public String getErrorMsg() throws Exception {
        errorStreamThread.join();
        return errorStreamThread.getError();
    }

    /**
     * <pre>
     * A class of threads that reads standard output.
     * 標準出力を読み出すスレッド
     * </pre>
     */
    private class OutputStreamThread extends Thread {

        /** Output contents */
        private StringBuilder outputData = new StringBuilder();

        /**
         * Gets the contents of standard output.
         * @return Output contents
         */
        public String getOutput() {
            return outputData.toString();
        }

        /**
         * Gets the message from the input stream connected to the normal output of {@link #process}.
         */
        public void run() {
            try (BufferedReader outbr = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String s = outbr.readLine();
                while (s != null) {
                    outputData.append(s).append(System.lineSeparator());
                    s = outbr.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <pre>
     * A class of threads that reads standard error output.
     * 標準エラー出力を読み出すスレッド
     * </pre>
     */
    private class ErrorStreamThread extends Thread {

        /** Error output contents */
        private StringBuilder errorMsg = new StringBuilder();

        /**
         * Get the standard error output contents.
         * @return error message
         */
        public String getError() {
            return errorMsg.toString();
        }

        /**
         * Gets the error message from the input stream connected to the error output of {@link #process}.
         */
        public void run() {
            try (BufferedReader errbr = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String s = errbr.readLine();
                while (s != null) {
                    errorMsg.append(s).append(System.lineSeparator());
                    s = errbr.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
