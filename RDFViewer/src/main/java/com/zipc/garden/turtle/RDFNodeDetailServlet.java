package com.zipc.garden.turtle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zipc.garden.turtle.Common.Attribute;
import com.zipc.garden.turtle.dao.TurtleDao;
import com.zipc.garden.turtle.rdf.Predicate;
import com.zipc.garden.turtle.rdf.RDFUtil;

/**
 * Servlet implementation class RDFNodeDetailServlet
 */
@WebServlet("/RDFNodeDetailServlet")
public class RDFNodeDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RDFNodeDetailServlet() {
        super();
    }

    /**
     * RDFグラフの矩形をクリックした際に表示される詳細情報を作成します。
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");

        List<TurtleDao> ttlDaoList = (List<TurtleDao>) getServletContext().getAttribute(Attribute.TTL_DAO_LIST.getKey());
        Map<String, String> prefixMap = (Map<String, String>) getServletContext().getAttribute(Attribute.PREFIX.getKey());
        Map<String, String> anonIdMap = (Map<String, String>) getServletContext().getAttribute(Attribute.ANON_ID_MAP.getKey());

        Optional<String> orgNodeName;
        String nodeName = request.getParameter("nodeName");

        // ClickされたNodeが、anonIdMapに存在する場合は取得される。
        Optional<String> optAnonKey = Common.getKeysByValue(anonIdMap, nodeName).stream().findFirst();

        // anonMapに存在する場合、ブランクノードがClickされている
        if (optAnonKey.isPresent()) {
            orgNodeName = optAnonKey;
        } else {
            // nodeNameを短縮前の値へ戻す
            String[] nodeNames = nodeName.split(":");
            if (nodeNames.length == 2) {
                Optional<String> nameSpace = Common.getKeysByValue(prefixMap, nodeNames[0]).stream().findFirst();
                if (nameSpace.isPresent()) {
                    orgNodeName = Optional.of(nameSpace.get() + "#" + nodeNames[1]);
                } else {
                    orgNodeName = Optional.of(nodeName);
                }
            } else {
                orgNodeName = Optional.of(nodeName);
            }
        }
        // 表示対象外の?pの要素を取得
        HashSet<String> untargetedSet = new HashSet<String>();
        untargetedSet.addAll(RDFUtil.getOwlObjectProperty(ttlDaoList).stream().map(dao -> dao.getSubject()).distinct().collect(Collectors.toList()));
        untargetedSet.addAll(Predicate.getPredicateList());

        // uriが短縮されていないノード名で検索。ObjectProperty以外のもので絞り込み
        List<TurtleDao> propList = ttlDaoList.stream().filter(dao -> {
            return dao.getSubject().equals(orgNodeName.get());
        }).filter(dao -> {
            return !untargetedSet.contains(dao.getPredicate());
        }).sorted(Comparator.comparing(TurtleDao::getPredicate).thenComparing(TurtleDao::getObject)).collect(Collectors.toList());

        // 出力する文字を作成
        StringBuilder output = new StringBuilder();
        output.append(nodeName);
        propList.forEach(dao -> {
            String pPrefix = prefixMap.get(dao.getPredicateNameSpace());
            String oPrefix = prefixMap.get(dao.getObjectNameSpace());
            String predicate;
            String object;
            if (dao.isPredicateAnon()) {
                predicate = anonIdMap.get(dao.getPredicate());
            } else if (pPrefix == null) {
                predicate = dao.getPredicate();
            } else {
                predicate = pPrefix + ":" + dao.getPredicateLocalName();
            }
            if (dao.isObjectAnon()) {
                object = anonIdMap.get(dao.getObject());
            } else if (oPrefix == null) {
                object = dao.getObject();
            } else {
                object = oPrefix + ":" + dao.getObjectLocalName();
            }
            output.append("<br />");
            output.append(predicate);
            output.append(" : ");
            output.append(object);
        });
        PrintWriter out = response.getWriter();
        out.write(output.toString());
    }
}
