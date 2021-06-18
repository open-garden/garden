package com.zipc.garden.turtle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

public class Common {

    /** プロパティファイルの配置先のパス。 */
    protected static final String PROPERTIES_DIR = "./";

    /** prefixの情報を持つプロパティファイルの名前 */
    protected static final String PREFIX_PROP_FILE = "prefix.properties";

    /**
     * setAttributeのKeyをまとめたenumです。次の様に使用します。<br>
     * - getServletContext().setAttribute(Attribute.PREFIX , xxxx);
     */
    public static enum Attribute {
        PREFIX("Previx"), TTL_DAO_LIST("TurtleDaoList"), DIGRAPH("Digraph"), UPLOAD_MESSAGE("UploadMessage"), ANON_ID_MAP("AnonIdMap");

        private String key;

        private Attribute(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    /**
     * 指定したnameSpacesとprefixsを持ったプロパティファイルを作成します。
     * @param context
     * @param nameSpaces
     * @param prefixs
     * @throws IOException
     */
    protected static void outputPrefixPropertiesFile(ServletContext context, String[] nameSpaces, String[] prefixs) throws IOException {
        Properties settings = new Properties();
        for (int i = 0; i < prefixs.length; i++) {
            settings.setProperty(nameSpaces[i], prefixs[i]);
        }
        String pathStr = context.getRealPath(PROPERTIES_DIR);
        Path path = Paths.get(pathStr);
        String parentPathStr = path.getParent().toString();
        Path parentPath = Paths.get(parentPathStr + "/" + PREFIX_PROP_FILE);
        try (FileOutputStream out = new FileOutputStream(parentPath.toFile())) {
            settings.store(out, "Prefix Properties");
        }
    }

    /**
     * ディレクトリの下にあるファイルを再帰的に検索する
     * @param absolutePath ディレクトリの絶対パス
     * @return ファイルの一覧
     */
    protected static List<File> findAllFile(String absolutePath) {
        List<File> files = new ArrayList<>();
        Stack<File> stack = new Stack<>();
        stack.add(new File(absolutePath));
        while (!stack.isEmpty()) {
            File item = stack.pop();
            if (item.isFile()) {
                files.add(item);
            }
            if (item.isDirectory()) {
                for (File child : item.listFiles())
                    stack.push(child);
            }
        }
        return files;
    }

    /**
     * ファイル内容を文字列化する
     * @param file
     * @return ファイルの中身
     * @throws IOException
     */
    protected static String fileToString(File file) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), Charset.forName("UTF-8"))) {
            String string = reader.readLine();
            while (string != null) {
                builder.append(string + System.getProperty("line.separator"));
                string = reader.readLine();
            }
        }
        return builder.toString();
    }

    /**
     * 引数で指定したプロパティファイルを読み込みし、Mapで返却する
     * @param context
     * @param fileName
     * @return
     */
    protected static Map<String, String> readPropertiesFile(ServletContext context, String fileName) {
        Properties prop = new Properties();
        String pathStr = context.getRealPath(PROPERTIES_DIR);
        Path path = Paths.get(pathStr);
        String parentPathStr = path.getParent().toString();
        Path parentPath = Paths.get(parentPathStr + "/" + fileName);
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(parentPath.toFile());
            prop.load(inputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        Map<String, String> propertiesMap = new HashMap<>();
        for (Entry<Object, Object> e : prop.entrySet()) {
            propertiesMap.put(e.getKey().toString(), e.getValue().toString());
        }
        return propertiesMap;
    }

    /**
     * JavaのString配列を、Javascript形式のString配列に変換する
     * @param arr
     * @return
     */
    public static String toJavascriptArray(String[] arr) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append("\"").append(arr[i]).append("\"");
            if (i + 1 < arr.length) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Value値を元にMapを検索し、Keyを取得する
     * @param <T>
     * @param <E>
     * @param map
     * @param value
     * @return
     */
    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        return map.entrySet().stream().filter(entry -> {
            return Objects.equals(entry.getValue(), value);
        }).map(Map.Entry::getKey).collect(Collectors.toSet());
    }
}
