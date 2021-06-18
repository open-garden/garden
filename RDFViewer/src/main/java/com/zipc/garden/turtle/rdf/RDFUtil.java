package com.zipc.garden.turtle.rdf;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFReader;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.system.Txn;

import com.zipc.garden.turtle.dao.TurtleDao;
import com.zipc.garden.turtle.graph.GraphContext;

public class RDFUtil {

    private static final String FUSEKI_URL = "http://localhost:3030/garden_rdf";

    private static final String GRAPH = "http://www.zipc.com/model/scenario";

    public static final String RDF_TYPE = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";

    public static final String OWL_CLASS = "http://www.w3.org/2002/07/owl#Class";

    public static final String OWL_OBJECT_PROPERTY = "http://www.w3.org/2002/07/owl#ObjectProperty";

    private static final String RDFS_LABEL = "http://www.w3.org/2000/01/rdf-schema#label";

    /**
     * ttlTextをttlファイルへ変換し、TDB2へ登録する
     * @param ttlText
     * @throws UnsupportedEncodingException
     */
    public static void registerToRDF(String ttlText) throws UnsupportedEncodingException {
        try (RDFConnection conn = RDFConnectionFactory.connect(FUSEKI_URL)) {
            Model model = ModelFactory.createOntologyModel();
            InputStream bais = new ByteArrayInputStream(ttlText.getBytes("UTF-8"));

            RDFReader reader = model.getReader("TTL");
            reader.read(model, bais, null);

            Txn.executeWrite(conn, () -> {
                conn.load(GRAPH, model);
                conn.commit();
                conn.end();
            });
        }
    }

    /**
     * TDB2のレコードをすべて取得する。
     * @return TurtleDao List
     */
    public static List<TurtleDao> selectAll() {
        List<TurtleDao> result = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT ?s ?p ?o");
        sb.append(" WHERE {");
        sb.append("  GRAPH <").append(GRAPH).append("> {");
        sb.append("   ?s ?p ?o ");
        sb.append("  }");
        sb.append(" }");

        try (RDFConnection conn = RDFConnectionFactory.connect(FUSEKI_URL)) {
            try (QueryExecution qe = conn.query(sb.toString())) {
                ResultSet rs = qe.execSelect();
                while (rs.hasNext()) {
                    QuerySolution qs = rs.next();

                    TurtleDao dao = new TurtleDao();
                    dao.setSubjectAnon(isAnon(qs, "s"));
                    dao.setPredicateAnon(isAnon(qs, "p"));
                    dao.setObjectAnon(isAnon(qs, "o"));
                    dao.setSubject(getData(qs, "s"));
                    dao.setPredicate(getData(qs, "p"));
                    dao.setObject(getData(qs, "o"));

                    result.add(dao);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchAndSetClassName(result);
        searchAndSetLabelName(result);

        return result;
    }

    /**
     * ?s, ?o のClassを検索し、TurtleDaoに格納する
     * @param turtleDaoListAll
     */
    private static void searchAndSetClassName(List<TurtleDao> turtleDaoListAll) {
        // ?s rdf:type owl:class
        List<String> classList = RDFUtil.getOwlClass(turtleDaoListAll).stream().map(dao -> dao.getSubject()).collect(Collectors.toList());

        // ?s rdf:type owl:ObjectProperty
        List<String> objectPropertyList = RDFUtil.getOwlObjectProperty(turtleDaoListAll).stream().map(dao -> dao.getSubject()).collect(Collectors.toList());

        // blankNodeのリストのPredicateも取得対象
        objectPropertyList.addAll(Predicate.getPredicateList());

        for (TurtleDao dao : turtleDaoListAll) {

            // エッジがObjectProperty
            if (objectPropertyList.contains(dao.getPredicate())) {

                // Subjectのクラスを特定
                List<String> subjectClassList = turtleDaoListAll.stream().filter(daoAll -> {
                    return daoAll.getSubject().equals(dao.getSubject()); // ?s を Subjectで検索
                    // }).filter(daoAll -> {
                    // return RDF_TYPE.equals(daoAll.getPredicate()); // ?p が rdf:type
                }).filter(daoAll -> {
                    return classList.contains(daoAll.getObject()); // ?o が 何れかのClass
                }).map(daoAll -> daoAll.getObject()).collect(Collectors.toList());
                if (subjectClassList.size() > 0) {
                    dao.setSubjectClass(subjectClassList.toArray(new String[subjectClassList.size()]));
                }

                // Objectのクラスを特定
                List<String> objectClassList = turtleDaoListAll.stream().filter(daoAll -> {
                    return daoAll.getSubject().equals(dao.getObject()); // ?s を Objectで検索
                    // }).filter(daoAll -> {
                    // return RDF_TYPE.equals(daoAll.getPredicate()); // ?p が rdf:type
                }).filter(daoAll -> {
                    return classList.contains(daoAll.getObject()); // ?o が 何れかのClass
                }).map(daoAll -> daoAll.getObject()).collect(Collectors.toList());
                if (objectClassList.size() > 0) {
                    dao.setObjectClass(objectClassList.toArray(new String[objectClassList.size()]));
                }
            }
        }
    }

    /**
     * ?s, ?o の rdfs:label を検索し、TurtleDaoに格納する
     * @param turtleDaoListAll
     */
    private static void searchAndSetLabelName(List<TurtleDao> turtleDaoListAll) {
        for (TurtleDao dao : turtleDaoListAll) {

            // Subjectのラベルを特定
            Optional<String> subjectLabelName = turtleDaoListAll.stream().filter(daoAll -> {
                return daoAll.getSubject().equals(dao.getSubject()); // ?s を Subjectで検索
            }).filter(daoAll -> {
                return RDFS_LABEL.equals(daoAll.getPredicate()); // ?p が rdfs:label
            }).map(daoAll -> daoAll.getObject()).findFirst();

            // Subjectのラベルが存在する場合、Daoに格納する
            if (subjectLabelName.isPresent()) {
                dao.setSubjectLabelName(subjectLabelName.get());
            }

            // Objectのラベルを特定
            Optional<String> objectLabelName = turtleDaoListAll.stream().filter(daoAll -> {
                return daoAll.getSubject().equals(dao.getObject()); // ?s を Objectで検索
            }).filter(daoAll -> {
                return RDFS_LABEL.equals(daoAll.getPredicate()); // ?p が rdfs:label
            }).map(daoAll -> daoAll.getObject()).findFirst();

            // Objectのラベルが存在する場合、Daoに格納する
            if (objectLabelName.isPresent()) {
                dao.setObjectLabelName(objectLabelName.get());
            }
        }
    }

    /**
     * 引数の要素がアノニマス（ブランクノード）である場合はtrue
     * @param qs
     * @param option
     * @return
     */
    private static boolean isAnon(QuerySolution qs, String option) {
        return qs.get(option).isAnon();
    }

    /**
     * 引数の要素の値を取得する。
     * @param qs
     * @param option
     * @return
     */
    private static String getData(QuerySolution qs, String option) {
        RDFNode node = qs.get(option);
        if (node.isAnon()) {
            return node.asNode().getBlankNodeLabel();
        } else if (node.isLiteral()) {
            return node.asLiteral().getString();
        } else if (node.isResource()) {
            return node.asResource().getURI();
        } else {
            return "";
        }
    }

    /**
     * 引数を元に、アノニマスIDのMAPを作成する。<br>
     * key : アノニマスID / value : _:bn
     * @param turtleDaoList
     * @return
     */
    public static Map<String, String> getAnonymousIdMap(List<TurtleDao> turtleDaoList) {
        Map<String, String> anonymousIdList = new HashMap<>();
        AtomicInteger idx = new AtomicInteger(0);

        turtleDaoList.stream().forEach(dao -> {
            if (dao.isSubjectAnon() && !anonymousIdList.containsKey(dao.getSubject())) {
                anonymousIdList.put(dao.getSubject(), "_:b" + idx.getAndIncrement());
            }
            if (dao.isPredicateAnon() && !anonymousIdList.containsKey(dao.getPredicate())) {
                anonymousIdList.put(dao.getPredicate(), "_:b" + idx.getAndIncrement());
            }
            if (dao.isObjectAnon() && !anonymousIdList.containsKey(dao.getObject())) {
                anonymousIdList.put(dao.getObject(), "_:b" + idx.getAndIncrement());
            }
        });
        return anonymousIdList;
    }

    /**
     * 引数を元に、?p が rdf:rest , rdf:firstに一致するレコードを取得する
     * @param turtleDaoList
     * @return
     */
    public static List<TurtleDao> getSpecificPredicate(List<TurtleDao> turtleDaoList) {
        return turtleDaoList.stream().filter(dao -> Predicate.isSpecificPredicate(dao.getPredicate())).collect(Collectors.toList());
    }

    /**
     * turtleDaoListを元にグラフを作成する。<br>
     * uriは、prefixMapを元に短縮する。<br>
     * BrankNodeは、anonymousIdMapのValue値に置き換える
     * @param turtleDao
     * @param prefixMap
     * @param anonymousIdMap
     * @return
     */
    public static String getDigraph(List<TurtleDao> turtleDaoList, Map<String, String> prefixMap, Map<String, String> anonymousIdMap, String type) {

        compressionForBlankNodeList(turtleDaoList);

        // 走行データの種別を取得
        Map<String, GraphContext> graphs = new HashMap<String, GraphContext>();
        for (TurtleDao dao : turtleDaoList) {
            if (dao.getSubjectClassLocalName().length < 1) {
                continue;
            }
            if (ScenarioSchema.RAW_DATA.equals(dao.getSubjectClassLocalName()[0])) {
                GraphContext context = graphs.get(dao.getSubjectLabelName());
                if (context == null) {
                    graphs.put(dao.getSubject(), new GraphContext(dao.getSubjectLabelName(), prefixMap, anonymousIdMap));
                }
            }
        }

        // 走行データごとにGroup分けし、必要な情報のみにする
        for (GraphContext context : graphs.values()) {
            for (TurtleDao dao : turtleDaoList) {
                String subjectClassLocal = dao.getSubjectLocalName();
                if (!subjectClassLocal.startsWith("garden_ts_" + context.getName())) {
                    continue;
                }
                if (type.equals(dao.getSubjectClassLocalName()[0])) {
                    context.add(dao);
                }
                if (ScenarioSchema.SCENE_TYPE.equals(dao.getSubjectClassLocalName()[0])) {
                    if (ScenarioSchema.PROPERTY_INITIAL.equals(dao.getPredicateLocalName())) {
                        context.addRootScene(dao.getObject());
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("digraph scenario_graph {");
        result.append("    graph[fontname=\"Meiryo\" charset=\"UTF-8\" rankdir=TB]; ");
        result.append("    node[fontname=\"Meiryo\" fontsize=8];");
        result.append("    edge[fontname=\"Meiryo\" fontsize=8];");

        int cnt = 1;
        for (GraphContext context : graphs.values()) {
            List<TurtleDao> daos = context.getTurtleDaos();
            result.append(String.format("    subgraph cluster_%d {", cnt));
            result.append(String.format("        label=\"%s\";", context.getName()));

            /**
             * root subject subject -> node subject -> dao
             */
            for (TurtleDao dao : daos) {
                String subject = dao.getSubject();
                String object = dao.getObject();
                String predicate = dao.getPredicate();
                result.append(context.getNode(subject)).append("->").append(context.getNode(object));
                result.append("[label=\"" + context.getPredicate(predicate) + "\"];");
            }

            // create sub cluster
            if (ScenarioSchema.TAG.equals(type)) {
                result.append(String.format("        subgraph cluster_%d_tag {", cnt));
                result.append(String.format("            label=\"%s\";", type));
                result.append(String.format("            edge[style=invis];"));
                String prev_node = null;
                for (String subject : context.getSubjects()) {
                    String node = context.getNode(subject);
                    if (prev_node != null) {
                        result.append(prev_node).append("->").append(node);
                        result.append(";");
                    }
                    prev_node = node;
                }
                result.append(" }");
            } else if (ScenarioSchema.SCENE.equals(type)) {
                int scene_cnt = 0;
                for (String sub : context.getRootScenes()) {
                    result.append(String.format("        subgraph cluster_%d_scene {", scene_cnt));
                    result.append(String.format("            label=\"%s_%d\";", type, scene_cnt));
                    String next = sub;
                    while (next != null) {
                        String node = context.getNode(next);
                        if (node != null) {
                            result.append(node).append(";");
                        }
                        next = context.getNext(next);
                    }
                    result.append(" }");
                    scene_cnt++;
                }
            }

            // Time
            result.append(String.format("        subgraph cluster_%d_time {", cnt));
            result.append("            label=\"Time\";");
            result.append(String.format("            edge[style=invis];"));
            String prev_node = null;
            for (String time : context.getTimes()) {
                String node = context.getNode(time);
                if (prev_node != null) {
                    result.append(prev_node).append("->").append(node);
                    result.append(";");
                }
                prev_node = node;
            }
            result.append(" }");
            result.append("    }");
            cnt++;
        }
        result.append("}");

        return result.toString();

    }

    /**
     * turtleDaoListより、rdf:type、owl:Classのレコードを取得する
     * @param turtleDaoList
     * @return
     */
    public static List<TurtleDao> getOwlClass(List<TurtleDao> turtleDaoList) {
        return turtleDaoList.stream().filter(dao -> {
            return RDF_TYPE.equals(dao.getPredicate());
        }).filter(dao -> {
            return OWL_CLASS.equals(dao.getObject());
        }).collect(Collectors.toList());
    }

    /**
     * turtleDaoListより、rdf:type、owl:Classのレコードを取得する
     * @param turtleDaoList
     * @return
     */
    public static List<TurtleDao> getOwlClassFilter(List<TurtleDao> turtleDaoList) {
        List<String> filter = Arrays.asList(ScenarioSchema.TAG, ScenarioSchema.SCENE, ScenarioSchema.TIME);
        return getOwlClass(turtleDaoList).stream().filter(dao -> {
            return filter.contains(dao.getSubjectLocalName());
        }).collect(Collectors.toList());
    }

    /**
     * turtleDaoListより、rdf:type、owl:ObjectPropertyのレコードを取得する
     * @param turtleDaoList
     * @return
     */
    public static List<TurtleDao> getOwlObjectProperty(List<TurtleDao> turtleDaoList) {
        return turtleDaoList.stream().filter(dao -> {
            return RDF_TYPE.equals(dao.getPredicate());
        }).filter(dao -> {
            return OWL_OBJECT_PROPERTY.equals(dao.getObject());
        }).collect(Collectors.toList());
    }

    /**
     * turtleDaoListより、rdf:type、owl:ObjectPropertyのレコードを取得する
     * @param turtleDaoList
     * @return
     */
    public static List<TurtleDao> getOwlObjectPropertyFilter(List<TurtleDao> turtleDaoList) {
        List<String> filter = Arrays.asList(ScenarioSchema.PROPERTY_TIME, ScenarioSchema.PROPERTY_START, ScenarioSchema.PROPERTY_END, ScenarioSchema.PROPERTY_INITIAL, ScenarioSchema.PROPERTY_NEXT);
        return getOwlObjectProperty(turtleDaoList).stream().filter(dao -> {
            return filter.contains(dao.getSubjectLocalName());
        }).collect(Collectors.toList());
    }

    /**
     * Subjectの値に一致するレコードを再帰的に検索する。
     * @param results
     * @param turtleDaoList
     * @param subject
     */
    public static void searchBySubject(List<TurtleDao> results, List<TurtleDao> turtleDaoList, String subject) {
        List<TurtleDao> subjectFilter = turtleDaoList.stream().filter(dao -> subject.equals(dao.getSubject())).collect(Collectors.toList());
        for (TurtleDao dao : subjectFilter) {
            Optional<TurtleDao> optSame = results.stream().filter(ret -> {
                return ret.getSubject().equals(dao.getSubject());
            }).filter(ret -> {
                return ret.getPredicate().equals(dao.getPredicate());
            }).filter(ret -> {
                return ret.getObject().equals(dao.getObject());
            }).findFirst();
            if (!optSame.isPresent()) {
                results.add(dao);
                if (!dao.getSubject().equals(dao.getObject())) {
                    searchBySubject(results, turtleDaoList, dao.getObject());
                }
            }
        }
    }

    /**
     * Objectの値に一致するレコードを再帰的に検索する。
     * @param results
     * @param turtleDaoList
     * @param object
     */
    public static void searchByObject(List<TurtleDao> results, List<TurtleDao> turtleDaoList, String object) {
        List<TurtleDao> objectFilter = turtleDaoList.stream().filter(dao -> object.equals(dao.getObject())).collect(Collectors.toList());
        for (TurtleDao dao : objectFilter) {
            Optional<TurtleDao> optSame = results.stream().filter(ret -> {
                return ret.getSubject().equals(dao.getSubject());
            }).filter(ret -> {
                return ret.getPredicate().equals(dao.getPredicate());
            }).filter(ret -> {
                return ret.getObject().equals(dao.getObject());
            }).findFirst();
            if (!optSame.isPresent()) {
                results.add(dao);
                if (!dao.getSubject().equals(dao.getObject())) {
                    searchByObject(results, turtleDaoList, dao.getSubject());
                }
            }
        }
    }

    /**
     * プレフィックスの画面で使用する。<br>
     * URIのNameSpaceを取得し、リストで返す。
     * @param turtleDaoList
     * @return
     */
    public static List<String> getNameSpace(List<TurtleDao> turtleDaoList) {
        List<String> nameSpaceList = new ArrayList<>();
        turtleDaoList.stream().forEach(dao -> {
            String[] sSubject = dao.getSubject().split("#");
            String[] sPredicate = dao.getPredicate().split("#");
            String[] sObject = dao.getObject().split("#");
            if (sSubject.length > 1 && !nameSpaceList.contains(sSubject[0]))
                nameSpaceList.add(sSubject[0]);
            if (sPredicate.length > 1 && !nameSpaceList.contains(sPredicate[0]))
                nameSpaceList.add(sPredicate[0]);
            if (sObject.length > 1 && !nameSpaceList.contains(sObject[0]))
                nameSpaceList.add(sObject[0]);
        });
        return nameSpaceList;
    }

    /**
     * rdf:type owl:ObjectProperty に一致するすべてのレコードを取得する
     * @param turtleDaoListAll
     * @return
     */
    public static List<TurtleDao> getIndividualList(List<TurtleDao> turtleDaoListAll) {
        List<String> objectPropertyList = RDFUtil.getOwlObjectProperty(turtleDaoListAll).stream().map(dao -> dao.getSubject()).collect(Collectors.toList());
        return getIndividualList(turtleDaoListAll, objectPropertyList);
    }

    /**
     * ?p と objectPropertyList の一致するレコードを取得する
     * @param turtleDaoListAll
     * @param objectPropertyList
     * @return
     */
    public static List<TurtleDao> getIndividualList(List<TurtleDao> turtleDaoListAll, List<String> objectPropertyList) {
        return turtleDaoListAll.stream().filter(dao -> objectPropertyList.contains(dao.getPredicate())).collect(Collectors.toList());
    }

    /**
     * ?p が rdf:first , rdf:rest のノードの関連を短縮します。
     * @param turtleDaoList
     */
    private static void compressionForBlankNodeList(List<TurtleDao> turtleDaoList) {
        // ?s rdf:first ?o でフィルタ
        List<TurtleDao> firstList = turtleDaoList.stream().filter(dao -> Predicate.RDF_FIRST.uri.equals(dao.getPredicate())).collect(Collectors.toList());

        for (TurtleDao firstDao : firstList) {
            Optional<TurtleDao> optFindDao = Optional.of(firstDao);
            do {
                TurtleDao findDao = optFindDao.get();

                // rdf:restと関連づいているか？
                Optional<TurtleDao> optRestDao = turtleDaoList.stream().filter(dao -> {
                    return dao.getSubject().equals(findDao.getSubject());
                }).filter(dao -> {
                    return dao.getPredicate().equals(Predicate.RDF_REST.uri);
                }).findFirst();
                if (optRestDao.isPresent()) {
                    // rdf:rest の親 を次の検索対象とする
                    optFindDao = turtleDaoList.stream().filter(dao -> dao.getObject().equals(findDao.getSubject())).findFirst();
                } else {
                    // rus:first を持ったノードの繋ぎ直し
                    firstDao.setSubjectAnon(findDao.isSubjectAnon());
                    firstDao.setSubject(findDao.getSubject());
                    firstDao.setSubjectClass(findDao.getSubjectClass());
                    firstDao.setPredicateAnon(findDao.isPredicateAnon());
                    firstDao.setPredicate(findDao.getPredicate());

                    // 繋ぎ直したので、次の検索対象は無し
                    optFindDao = Optional.empty();
                }
            } while (optFindDao.isPresent());// restがある場合は処理継続
        }
        // rdf:rest の削除
        turtleDaoList.removeAll(turtleDaoList.stream().filter(dao -> {
            return Predicate.RDF_REST.uri.equals(dao.getPredicate());
        }).filter(restDao -> {
            return turtleDaoList.stream().filter(dao -> {
                return dao.getSubject().equals(restDao.getSubject());
            }).collect(Collectors.toList()).size() == 1;
        }).collect(Collectors.toList()));
    }

    /**
     * 主語と目的語のラベル名を取得する
     * @param turtleDaoListAll
     * @return
     */
    public static List<String> getLabelNameList(List<TurtleDao> turtleDaoListAll) {
        List<String> result = new ArrayList<String>();
        for (TurtleDao dao : turtleDaoListAll) {

            // Subjectのラベルを特定
            Optional<String> subjectLabelName = turtleDaoListAll.stream().filter(daoAll -> {
                return daoAll.getSubject().equals(dao.getSubject()); // ?s を Subjectで検索
            }).filter(daoAll -> {
                return RDFS_LABEL.equals(daoAll.getPredicate()); // ?p が rdfs:label
            }).map(daoAll -> daoAll.getObject()).findFirst();

            // Subjectのラベルが存在する場合
            if (subjectLabelName.isPresent()) {
                result.add(subjectLabelName.get());
            }

            // Objectのラベルを特定
            Optional<String> objectLabelName = turtleDaoListAll.stream().filter(daoAll -> {
                return daoAll.getSubject().equals(dao.getObject()); // ?s を Objectで検索
            }).filter(daoAll -> {
                return RDFS_LABEL.equals(daoAll.getPredicate()); // ?p が rdfs:label
            }).map(daoAll -> daoAll.getObject()).findFirst();

            // Objectのラベルが存在する場合
            if (objectLabelName.isPresent()) {
                result.add(objectLabelName.get());
            }
        }
        return new ArrayList<String>(new HashSet<>(result));
    }
}
