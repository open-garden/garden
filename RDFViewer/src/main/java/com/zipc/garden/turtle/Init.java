package com.zipc.garden.turtle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.zipc.garden.turtle.Common.Attribute;
import com.zipc.garden.turtle.dao.TurtleDao;
import com.zipc.garden.turtle.rdf.RDFUtil;

/**
 * Servlet implementation class Init
 */
@WebServlet("/Init")
public class Init extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
        getServletContext().setResponseCharacterEncoding("UTF-8");
        getServletContext().setRequestCharacterEncoding("UTF-8");

        Map<String, String> prefixDatas = Common.readPropertiesFile(getServletContext(), Common.PREFIX_PROP_FILE);

        List<TurtleDao> turtleDaoList = RDFUtil.selectAll();
        List<TurtleDao> digraphList = new ArrayList<>();
        digraphList.addAll(RDFUtil.getIndividualList(turtleDaoList));
        digraphList.addAll(RDFUtil.getSpecificPredicate(turtleDaoList));

        Map<String, String> anonIdMap = RDFUtil.getAnonymousIdMap(turtleDaoList);
        String digraph = RDFUtil.getDigraph(digraphList, prefixDatas, anonIdMap, "Tag");
        getServletContext().setAttribute(Attribute.PREFIX.getKey(), prefixDatas);
        getServletContext().setAttribute(Attribute.TTL_DAO_LIST.getKey(), turtleDaoList);
        getServletContext().setAttribute(Attribute.DIGRAPH.getKey(), digraph);
        getServletContext().setAttribute(Attribute.UPLOAD_MESSAGE.getKey(), "");
        getServletContext().setAttribute(Attribute.ANON_ID_MAP.getKey(), anonIdMap);
    }
}
