package com.zipc.garden.turtle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zipc.garden.turtle.Common.Attribute;
import com.zipc.garden.turtle.dao.TurtleDao;
import com.zipc.garden.turtle.rdf.RDFUtil;

/**
 * Servlet implementation class RDFFilterServlet
 */
@WebServlet("/RDFFilterServlet")
public class RDFFilterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RDFFilterServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String[] prefixChecks = request.getParameterValues("prefixCheck[]");
        String[] prefixNameSpaces = request.getParameterValues("prefixNameSpace[]");
        String[] prefixs = request.getParameterValues("prefix[]");
        String type = request.getParameter("selectType");
        Common.outputPrefixPropertiesFile(getServletContext(), prefixNameSpaces, prefixs);
        Map<String, String> prefixMap = new HashMap<>();
        if (prefixChecks != null) {
            for (int i = 0; i < prefixChecks.length; i++) {
                if ("true".equals(prefixChecks[i])) {
                    prefixMap.put(prefixNameSpaces[i], prefixs[i]);
                }
            }
        }
        getServletContext().setAttribute(Attribute.PREFIX.getKey(), prefixMap);

        String[] classSearchTarget = request.getParameterValues("classSearchTarget[]");
        String[] objectPropertySearchTarget = request.getParameterValues("objectPropertySearchTarget[]");

        List<TurtleDao> turtleDaoList = (List<TurtleDao>) getServletContext().getAttribute(Attribute.TTL_DAO_LIST.getKey());
        Map<String, String> anonIdMap = (Map<String, String>) getServletContext().getAttribute(Attribute.ANON_ID_MAP.getKey());
        List<TurtleDao> individualList = RDFUtil.getIndividualList(turtleDaoList, Arrays.asList(objectPropertySearchTarget));
        List<TurtleDao> specPreList = RDFUtil.getSpecificPredicate(turtleDaoList);
        List<TurtleDao> digraphList = new ArrayList<>();
        digraphList.addAll(individualList);
        digraphList.addAll(specPreList);

        List<TurtleDao> relatedNodeList = new ArrayList<>();

        List<String> nodeNames = new ArrayList<String>();
        if (classSearchTarget != null) {
            List<String> selClassList = Arrays.asList(classSearchTarget);
            nodeNames.addAll(digraphList.stream().filter(dao -> {
                return Arrays.stream(dao.getSubjectClass()).filter(sClass -> selClassList.contains(sClass)).findFirst().isPresent();
            }).map(dao -> dao.getSubject()).collect(Collectors.toList()));
            nodeNames.addAll(digraphList.stream().filter(dao -> {
                return Arrays.stream(dao.getObjectClass()).filter(oClass -> selClassList.contains(oClass)).findFirst().isPresent();
            }).map(dao -> dao.getObject()).collect(Collectors.toList()));
        }
        for (String nodeName : nodeNames) {
            RDFUtil.searchBySubject(relatedNodeList, digraphList, nodeName);
            RDFUtil.searchByObject(relatedNodeList, digraphList, nodeName);
        }

        String digraph = RDFUtil.getDigraph(relatedNodeList, prefixMap, anonIdMap, type);
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(digraph);

        getServletContext().setAttribute(Attribute.DIGRAPH.getKey(), digraph);
    }
}
