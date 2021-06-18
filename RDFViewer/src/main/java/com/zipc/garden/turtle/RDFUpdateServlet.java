package com.zipc.garden.turtle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zipc.garden.turtle.Common.Attribute;
import com.zipc.garden.turtle.dao.TurtleDao;
import com.zipc.garden.turtle.json.UpdateJson;
import com.zipc.garden.turtle.rdf.RDFUtil;

/**
 * Servlet implementation class RDFUpdateServlet
 */
@WebServlet("/RDFUpdateServlet")
public class RDFUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RDFUpdateServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("selectType");
		Map<String, String> prefixDatas = (Map<String, String>) getServletContext()
				.getAttribute(Attribute.PREFIX.getKey());

		List<TurtleDao> turtleDaoList = RDFUtil.selectAll();
		List<TurtleDao> digraphList = new ArrayList<>();
		digraphList.addAll(RDFUtil.getIndividualList(turtleDaoList));
		digraphList.addAll(RDFUtil.getSpecificPredicate(turtleDaoList));

		Map<String, String> anonIdMap = RDFUtil.getAnonymousIdMap(turtleDaoList);
		String digraph = RDFUtil.getDigraph(digraphList, prefixDatas, anonIdMap, type);
		getServletContext().setAttribute(Attribute.TTL_DAO_LIST.getKey(), turtleDaoList);
		getServletContext().setAttribute(Attribute.DIGRAPH.getKey(), digraph);
		getServletContext().setAttribute(Attribute.ANON_ID_MAP.getKey(), anonIdMap);

		String prefix = createPrefix(turtleDaoList, prefixDatas);
		String clazz = createClass(turtleDaoList);
		String objectProperty = createObjectProperty(turtleDaoList);

		UpdateJson updateJson = new UpdateJson();
		updateJson.setGraph(digraph);
		updateJson.setPrefix(prefix);
		updateJson.setClassFilter(clazz);
		updateJson.setObjectPropertyeFilter(objectProperty);

		String updateJsonString = new Gson().toJson(updateJson);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(updateJsonString);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private String createPrefix(List<TurtleDao> turtleDaoList, Map<String, String> prefixDatas) {
		String body = "";
		List<String> nameSpaces = RDFUtil.getNameSpace(turtleDaoList);
		for (String nameSpace : nameSpaces) {
			String prefix = prefixDatas.get(nameSpace);
			if (prefix == null) {
				prefix = "";
			}
			body += "<tr>";
			body += "<td class=\"prefixCell-01\"><input type=\"checkbox\" name=\"prefixCheck\" checked></td>";
			body += String.format(
					"<td class=\"prefixCell-02\"><input type=\"text\" name=\"prefix\" onInput=\"checkForm(this)\" size=\"6\" maxlength=\"10\" value=\"%s\"></td>",
					prefix);
			body += String.format(
					"<td class=\"prefixCell-03\"><input type=\"hidden\" name=\"prefixNameSpace\" value=\"%s\">%s</td>",
					nameSpace, nameSpace);
			body += "</tr>";
		}
		return body;
	}

	private String createClass(List<TurtleDao> turtleDaoList) {
		String clazz = "";
		List<TurtleDao> classDatas = RDFUtil.getOwlClassFilter(turtleDaoList);
		for (TurtleDao dao : classDatas) {
			clazz += "<tr>";
			clazz += "<td class=\"filterCell-01\"><input type=\"checkbox\" name=\"classCheck\" checked></td>";
			clazz += String.format(
					"<td class=\"filterCell-02\"><input type=\"hidden\" name=\"className\" value=\"%s\">%s</td>",
					dao.getSubject(), dao.getSubject());
			clazz += "</tr>";
		}
		return clazz;
	}

	private String createObjectProperty(List<TurtleDao> turtleDaoList) {
		String filter = "";
		List<TurtleDao> objectPropertyDatas = RDFUtil.getOwlObjectPropertyFilter(turtleDaoList);
		for (TurtleDao dao : objectPropertyDatas) {
			filter += "<tr>";
			filter += "<td class=\"filterCell-01\"><input type=\"checkbox\" name=\"objectPropertyCheck\" checked></td>";
			filter += String.format(
					"<td class=\"filterCell-02\"><input type=\"hidden\" name=\"objectPropertyName\" value=\"%s\">%s</td>",
					dao.getSubject(), dao.getSubject());
			filter += "</tr>";
		}
		return filter;
	}
}
