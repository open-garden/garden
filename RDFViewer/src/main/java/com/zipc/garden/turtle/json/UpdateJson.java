package com.zipc.garden.turtle.json;

public class UpdateJson {

	private String graph;

	private String prefix;

	private String classFilter;

	private String objectPropertyfilter;

	public String getGraph() {
		return graph;
	}

	public void setGraph(String graph) {
		this.graph = graph;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getClassFilter() {
		return classFilter;
	}

	public void setClassFilter(String classFilter) {
		this.classFilter = classFilter;
	}

	public String getObjectPropertyFilter() {
		return objectPropertyfilter;
	}

	public void setObjectPropertyeFilter(String filter) {
		this.objectPropertyfilter = filter;
	}
}
