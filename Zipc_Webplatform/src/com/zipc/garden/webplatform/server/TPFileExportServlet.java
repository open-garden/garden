package com.zipc.garden.webplatform.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.hibernate.Session;

import com.opencsv.CSVWriter;
import com.zipc.garden.core.EditOptions;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.tp.PathType;
import com.zipc.garden.model.tp.TPElement;
import com.zipc.garden.model.tp.TPPatternElement;
import com.zipc.garden.model.tp.TPRoot;
import com.zipc.garden.model.tp.TPSetting;
import com.zipc.garden.model.tp.TPTSDPattern;
import com.zipc.garden.webplatform.dao.DAOUtils;
import com.zipc.garden.webplatform.dao.File;
import com.zipc.garden.webplatform.server.dao.accessor.FeaturePatternAccessor;
import com.zipc.garden.webplatform.server.generator.HeavyTaskContentGetter;
import com.zipc.garden.webplatform.shared.ExportUtil;
import com.zipc.garden.webplatform.shared.NodeUtil;

/**
 * A class that manages the export process of FP files.
 */
public class TPFileExportServlet extends HttpServlet {

    /** Unique identifier for this class */
    private static final long serialVersionUID = 1L;

    /**
     * Called by the server to force the Servlet to process the GET request. <br>
     * The export process is executed based on the request parameters.
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // リクエストパラメータから値を取得する。
        String name = req.getParameter("name");
        String fileIds = req.getParameter("ids");
        String projectId = req.getParameter("projectid");
        String fileType = req.getParameter("fileType");
        String title = req.getParameter("title");
        String pattern = req.getParameter("pattern");
        String path = req.getParameter("path");
        String show = req.getParameter("show");

        // ファイルIdリストを取得する。
        List<String> idList = new ArrayList<String>(Arrays.asList(fileIds.split(ExportUtil.COMMA_SIGN)));
        try (Session session = DAOUtils.getSessionFactory().openSession()) {
            CriteriaQuery<File> criteria = session.getCriteriaBuilder().createQuery(File.class);
            Root<File> fileTbl = criteria.from(File.class);
            criteria.select(fileTbl);
            criteria.where(fileTbl.get("id").in(idList));
            List<File> targetFileList = session.createQuery(criteria).getResultList();
            // エクスポートしたいファイルは1つ以上の時、ZIP拡張子でエクスポートする。
            if (targetFileList.size() > 1) {
                name += ".zip";
                zipFiles(targetFileList, resp, name, fileType, title, pattern, path, show, Long.valueOf(projectId));
            } else {
                // エクスポートしたいファイルは1つの時、CSVまたはTSV拡張子でエクスポートする。
                BinaryResourceImpl r = new BinaryResourceImpl();
                ByteArrayInputStream is = new ByteArrayInputStream(targetFileList.get(0).getContent());
                r.load(is, EditOptions.getDefaultLoadOptions());
                if (r.getContents().get(0) instanceof TPRoot) {
                    TPRoot tpRoot = (TPRoot) r.getContents().get(0);
                    HeavyTaskContentGetter<TPRoot> getter = new HeavyTaskContentGetter<TPRoot>();
                    getter.setSettings(tpRoot, Long.valueOf(projectId));
                    for (AbstractSetting setting : tpRoot.getSettings()) {
                        TPSetting tpSetting = (TPSetting) setting;
                        String[] patternNos = null;
                        if (ExportUtil.Pattern.DEPENDS_ON_PATTERN_FILE.getValue().equals(pattern)) {
                            if (tpSetting.getPatternNos() != null) {
                                patternNos = tpSetting.getPatternNos().split(ExportUtil.COMMA_SIGN);
                            }
                        }
                        File fpsFile = EditResourceUtil.INSTANCE.getFile(tpSetting.getUuid(), Long.valueOf(projectId));
                        FeaturePatternAccessor.getAllFeaturePattern(fpsFile.getProjectid(), fpsFile.getId(), fpsFile.getHash(), patternNos, tpSetting);
                    }

                    List<String[]> outputData = getFileContent(tpRoot, fileType, title, pattern, path, show);

                    Writer writer = new OutputStreamWriter(resp.getOutputStream(), "Shift_JIS");
                    CSVWriter csvWriter = null;

                    // エクスポートしたいファイルの拡張子はTSVの時
                    if (ExportUtil.FileType.TSV.getValue().equals(fileType)) {
                        name += ".tsv";
                        resp.setContentType("text/tab-separated-values");
                        csvWriter = new CSVWriter(writer, ExportUtil.TAB_SIGN, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
                    }
                    // エクスポートしたいファイルの拡張子はCSVの時
                    else if (ExportUtil.FileType.CSV.getValue().equals(fileType)) {
                        name += ".csv";
                        resp.setContentType("text/csv");
                        csvWriter = new CSVWriter(writer);
                    }
                    resp.setCharacterEncoding("Shift_JIS");
                    resp.setHeader("Content-Disposition", "attachment; filename='" + name + "'; filename*=Shift_JIS''" + URLEncoder.encode(name, "Shift_JIS") + ";");
                    csvWriter.writeAll(outputData);
                    csvWriter.flush();
                    csvWriter.close();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * Export multiple FP files in ZIP format.
     * @param fileList List of files to export
     * @param resp HttpServletResponse
     * @param name Zip file name
     * @param fileType Export conditions (CSV / TSV)
     * @param title Export conditions (Show / Hide title)
     * @param pattern Export conditions (export only patterns that depend on the configuration file / export all)
     * @param path Export conditions（Depends on configuration file / Output node path as simple path / Output node path as full
     *            path）
     * @param show Export conditions (Output risk importance depending on the configuration file / Output all risk importance /
     *            Do not output risk importance)
     * @param projectId ID of the project in which the FP file to be exported is managed
     * @throws IOException
     */
    private void zipFiles(List<File> fileList, HttpServletResponse resp, String name, String fileType, String title, String pattern, String path, String show,
            long projectId) throws IOException {
        try (OutputStream out = resp.getOutputStream(); ZipOutputStream zos = new ZipOutputStream(out)) {
            Writer writer = new OutputStreamWriter(zos, "Shift_JIS");
            CSVWriter csvWriter = null;
            if (ExportUtil.FileType.TSV.getValue().equals(fileType)) {
                csvWriter = new CSVWriter(writer, ExportUtil.TAB_SIGN, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
            } else if (ExportUtil.FileType.CSV.getValue().equals(fileType)) {
                csvWriter = new CSVWriter(writer);
            }
            resp.setCharacterEncoding("Shift_JIS");
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment; filename='" + name + "'; filename*=Shift_JIS''" + URLEncoder.encode(name, "Shift_JIS") + ";");
            for (File file : fileList) {
                BinaryResourceImpl r = new BinaryResourceImpl();
                List<String[]> outputData = new ArrayList<String[]>();
                ByteArrayInputStream is = new ByteArrayInputStream(file.getContent());
                r.load(is, EditOptions.getDefaultLoadOptions());
                TPRoot tpRoot = null;
                if (r.getContents().get(0) instanceof TPRoot) {
                    tpRoot = (TPRoot) r.getContents().get(0);
                    HeavyTaskContentGetter<TPRoot> getter = new HeavyTaskContentGetter<TPRoot>();
                    getter.setSettings(tpRoot, Long.valueOf(projectId));
                    for (AbstractSetting setting : tpRoot.getSettings()) {
                        TPSetting tpSetting = (TPSetting) setting;
                        String[] patternNos = null;
                        if (ExportUtil.Pattern.DEPENDS_ON_PATTERN_FILE.getValue().equals(pattern)) {
                            if (tpSetting.getPatternNos() != null) {
                                patternNos = tpSetting.getPatternNos().split(ExportUtil.COMMA_SIGN);
                            }
                        }
                        File fpsFile = EditResourceUtil.INSTANCE.getFile(tpSetting.getUuid(), projectId);
                        FeaturePatternAccessor.getAllFeaturePattern(fpsFile.getProjectid(), fpsFile.getId(), fpsFile.getHash(), patternNos, tpSetting);
                    }
                    outputData.addAll(getFileContent(tpRoot, fileType, title, pattern, path, show));
                }
                zos.putNextEntry(new ZipEntry(file.getName() + "." + fileType.toLowerCase()));
                csvWriter.writeAll(outputData);
                csvWriter.flush();
                zos.flush();
                out.flush();
            }
            csvWriter.close();
            zos.close();
            out.close();
        }
    }

    /**
     * Creates and retrieves the export contents of a single FP file.
     * @param tpRoot Contents of FP file
     * @param fileType Export conditions (CSV / TSV)
     * @param title Export conditions (Show / Hide title)
     * @param pattern Export conditions (export only patterns that depend on the configuration file / export all)
     * @param path Export conditions（Depends on configuration file / Output node path as simple path / Output node path as full
     *            path）
     * @param show Export conditions (Output risk importance depending on the configuration file / Output all risk importance /
     *            Do not output risk importance)
     * @return List of strings to export
     * @throws IOException
     */
    private List<String[]> getFileContent(TPRoot tpRoot, String fileType, String title, String pattern, String path, String show) throws IOException {
        int no = 0;
        int detailNo = 0;
        List<String[]> outputDataByte = new ArrayList<String[]>();
        for (AbstractSetting setting : tpRoot.getSettings()) {
            TPSetting tpSetting = (TPSetting) setting;
            if (tpSetting.getPatterns().isEmpty()) {
                continue;
            }

            boolean fullPathFlag = false;
            // 選択したPathが"Depends on setting file"の場合
            if (ExportUtil.Path.DEPENDS_ON_SETTING_FILE.getValue().equals(path)) {
                // FPで選択したPathが"FullPath"か
                fullPathFlag = PathType.FULL_PATH.equals(tpSetting.getTitle());
            } else {
                // Exportで選択したPathが"FullPath"か
                fullPathFlag = ExportUtil.Path.FULL_PATH.getValue().equals(path);
            }

            detailNo++;

            // パターンを取得するために、マップを宣言する。
            Map<String, Map<String, String>> patternParameterMap = new LinkedHashMap<>();

            // Headerの重複しない最短パスを取得する
            List<String> shortestPaths = NodeUtil.getInstance().getUniqueShortestPaths(tpSetting.getHeader());

            for (TPTSDPattern tptsdPattern : tpSetting.getPatterns()) {
                Map<String, String> parameterValueMap = new LinkedHashMap<>();
                for (TPPatternElement patternElement : tptsdPattern.getPatternElements()) {
                    parameterValueMap.put(patternElement.getName(), patternElement.getValue());
                }

                for (TPElement element : tptsdPattern.getElements()) {
                    // すべてのシンプルパスを取得するために、文字列配列を宣言する。
                    List<String> simplePathList = NodeUtil.getInstance().splitNode(element.getSimplePath());
                    // すべてのフルパスを取得するために、文字列配列を宣言する。
                    List<String> fullPathList = NodeUtil.getInstance().splitNode(element.getFullPath());
                    String parameter = String.join(".", fullPathList.subList(0, fullPathList.size() - 1));
                    String value = simplePathList.get(simplePathList.size() - 1);

                    // FullPathで出力する場合
                    if (fullPathFlag) {
                        if (ExportUtil.Title.HIDE.getValue().equals(title)) {
                            value = NodeUtil.getInstance().removeEscape(element.getFullPath());
                        }
                    } else {
                        int indexOf = tpSetting.getHeader().indexOf(parameter);
                        if (indexOf != -1) {
                            parameter = NodeUtil.getInstance().removeEscape(shortestPaths.get(indexOf));
                        }
                        if (ExportUtil.Title.HIDE.getValue().equals(title)) {
                            value = NodeUtil.getInstance().removeEscape(element.getSimplePath());
                        }
                    }
                    if (parameterValueMap.containsKey(parameter)) {
                        // シンプルパスはカーディナリティの場合
                        StringBuilder val = new StringBuilder();
                        if (ExportUtil.FileType.TSV.getValue().equals(fileType)) {
                            val.append(parameterValueMap.get(parameter));
                            val.append(",");
                            val.append(value);
                            parameterValueMap.put(parameter, val.toString());
                        } else {
                            // カンマを含む値をCSVファイルに書き込む。
                            val.append("\"");
                            val.append(parameterValueMap.get(parameter));
                            val.append(",");
                            val.append(value);
                            val.append("\"");
                            parameterValueMap.put(parameter, val.toString());
                        }
                    } else {
                        parameterValueMap.put(parameter, value);
                    }
                }
                patternParameterMap.put(tptsdPattern.getId(), parameterValueMap);
            }

            // タイトルを取得するために、リストを宣言する。
            List<String> titleList = new ArrayList<String>();
            AtomicInteger ai = new AtomicInteger(0);
            for (TPPatternElement pe : tpSetting.getPatternElements()) {
                if (ExportUtil.Show.DEPENDS_ON_SETTING_FILE.getValue().equals(show)) {
                    if (pe.isChecked()) {
                        titleList.add(ai.getAndIncrement(), pe.getName());
                    }
                } else if (ExportUtil.Show.ALL_ON.getValue().equals(show)) {
                    titleList.add(ai.getAndIncrement(), pe.getName());
                }
            }
            if (fullPathFlag) {
                tpSetting.getHeader().forEach(fullPath -> {
                    titleList.add(NodeUtil.getInstance().removeEscape(fullPath));
                });
            } else {
                shortestPaths.forEach(simplePath -> {
                    titleList.add(NodeUtil.getInstance().removeEscape(simplePath));
                });
            }

            // ファイルにタイトルを書き込む。
            if (ExportUtil.Title.SHOW.getValue().equals(title)) {
                List<String> header = new ArrayList<>(titleList);
                header.add(0, "No");
                header.add(1, "Detail No");
                outputDataByte.add(header.toArray(new String[header.size()]));
            }

            // パターン値を読み取りするために、マップを宣言する。
            Iterator<Entry<String, Map<String, String>>> itr = patternParameterMap.entrySet().iterator();
            while (itr.hasNext()) {
                Entry<String, Map<String, String>> patternEntry = itr.next();
                String id = patternEntry.getKey();
                Map<String, String> entryValue = patternEntry.getValue();
                List<String> dataList = new ArrayList<>();
                dataList.add(String.valueOf(++no));
                dataList.add(detailNo + "-" + id);

                for (int i = 0; i < titleList.size(); i++) {
                    if (entryValue.containsKey(titleList.get(i))) {
                        for (Entry<String, String> parameterEntry : entryValue.entrySet()) {
                            String parameter = parameterEntry.getKey();
                            String value = parameterEntry.getValue();
                            if (titleList.get(i).equals(parameter)) {
                                dataList.add(value);
                                break;
                            }
                        }
                    } else {
                        // シンプルパスはオプショナルの場合
                        dataList.add("-");
                    }
                }
                outputDataByte.add(dataList.toArray(new String[dataList.size()]));
            }
        }
        return outputDataByte;
    }
}
