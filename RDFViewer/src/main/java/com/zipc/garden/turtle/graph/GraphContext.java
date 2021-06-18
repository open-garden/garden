package com.zipc.garden.turtle.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.zipc.garden.turtle.dao.TurtleDao;
import com.zipc.garden.turtle.rdf.ScenarioSchema;

public class GraphContext {

    private final Map<String, String> prefixMap;

    private final Map<String, String> anonymousIdMap;

    private final String name;

    private List<TurtleDao> daos = new ArrayList<TurtleDao>();

    private Set<String> subjects = new TreeSet<String>();

    private Set<String> objects = new TreeSet<String>();

    private List<String> rootScenes = new ArrayList<String>();

    private final Map<String, String> nodes = new HashMap<String, String>();

    private final Map<String, String> nexts = new HashMap<String, String>();

    private final Map<String, String> predicates = new HashMap<String, String>();

    private final Set<String> times = new TreeSet<String>();

    public GraphContext(String name, Map<String, String> prefixMap, Map<String, String> anonymousIdMap) {
        this.name = name;
        this.prefixMap = prefixMap;
        this.anonymousIdMap = anonymousIdMap;
    }

    public String getName() {
        return name;
    }

    public List<TurtleDao> getTurtleDaos() {
        return daos;
    }

    public Set<String> getSubjects() {
        return subjects;
    }

    public Set<String> getObjects() {
        return objects;
    }

    public String getNode(String name) {
        return nodes.get(name);
    }

    public String getPredicate(String name) {
        return predicates.get(name);
    }

    public Set<String> getTimes() {
        return times;
    }

    public void add(TurtleDao dao) {
        daos.add(dao);
        subjects.add(dao.getSubject());
        objects.add(dao.getObject());

        if (ScenarioSchema.TIME.equals(dao.getObjectClassLocalName()[0])) {
            times.add(dao.getObject());
        } else if (ScenarioSchema.PROPERTY_NEXT.equals(dao.getPredicateLocalName())) {
            nexts.put(dao.getSubject(), dao.getObject());
        }

        createNode(dao);
    }

    public void addRootScene(String scene) {
        rootScenes.add(scene);
    }

    public List<String> getRootScenes() {
        return rootScenes;
    }

    public String getNext(String subject) {
        if (nexts.containsKey(subject)) {
            return nexts.get(subject);
        }
        return null;
    }

    private void createNode(TurtleDao dao) {
        String[] subjectClass = getClasses(dao.getSubjectClass(), dao.getSubjectClassNameSpace(), dao.getSubjectClassLocalName());
        String[] objectClass = getClasses(dao.getObjectClass(), dao.getObjectClassNameSpace(), dao.getObjectClassLocalName());

        String subject = getShortURI(prefixMap.get(dao.getSubjectNameSpace()), dao.getSubject(), dao.getSubjectLocalName(), dao.isSubjectAnon());
        String predicate = getShortURI(prefixMap.get(dao.getPredicateNameSpace()), dao.getPredicate(), dao.getPredicateLocalName(), dao.isPredicateAnon());
        String object = getShortURI(prefixMap.get(dao.getObjectNameSpace()), dao.getObject(), dao.getObjectLocalName(), dao.isObjectAnon());
        // 主語のノードに表示する文字列
        String nodeName_subject = getNodeName(subjectClass, dao.getSubjectLabelName(), subject);

        // 目的語のノードに表示する文字列
        String nodeName_object = getNodeName(objectClass, dao.getObjectLabelName(), object);

        nodes.put(dao.getSubject(), nodeName_subject);
        nodes.put(dao.getObject(), nodeName_object);
        predicates.put(dao.getPredicate(), predicate);
    }

    private String[] getClasses(String[] classes, String[] classNameSpaces, String[] classLocalNames) {
        String[] scPrefix = new String[classes.length];
        String[] results = new String[classes.length];
        for (int i = 0; i < classes.length; i++) {
            scPrefix[i] = prefixMap.get(classNameSpaces[i]);
            results[i] = scPrefix[i] == null ? classes[i] : scPrefix[i] + ":" + classLocalNames[i];
        }
        return results;
    }

    /**
     * <pre>
     * URIが短縮されます。
     * 短縮できなかった場合は、そのまま返されます。
     *　
     * 例
     * ・ http://www.w3.org/2002/07/owl#Class → owl:Class
     * ・ アノニマスID → _:bn
     * </pre>
     * 
     * @param prefix
     * @param uri
     * @param localName
     * @param isAnonymous
     * @param anonymousIdMap
     * @return
     */
    private String getShortURI(String prefix, String uri, String localName, boolean isAnonymous) {
        String result = "";
        if (isAnonymous) {
            result = anonymousIdMap.get(uri);
        } else if (prefix == null) {
            result = uri;
        } else {
            result = prefix + ":" + localName;
        }
        return result;
    }

    /**
     * クラス情報とラベル情報を含むノード名を取得します。
     * @param classNames
     * @param labelName
     * @param nodeName
     * @return
     */
    private String getNodeName(String[] classNames, String labelName, String nodeName) {
        StringBuilder result = new StringBuilder();
        result.append("\"");
        if (classNames.length > 0) {
            result.append("(");
            result.append(String.join(",\\n", classNames));
            result.append(")\\n");
        }
        if (labelName != null && !"".equals(labelName)) {
            result.append(labelName);
            result.append("\\n");
        }
        result.append(nodeName).append("\"");
        return result.toString();
    }
}
