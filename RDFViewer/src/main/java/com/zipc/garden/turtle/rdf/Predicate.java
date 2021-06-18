package com.zipc.garden.turtle.rdf;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Predicate {

    /**
     * BrankNodeのリストで追加される要素。<br>
     * 暗黙的に追加されるBlankNodeの述語に当たる部分
     */
    RDF_REST("http://www.w3.org/1999/02/22-rdf-syntax-ns#rest"),
    /**
     * BrankNodeのリストで追加される要素。<br>
     * 暗黙的に追加されるBlankNodeの述語に当たる部分
     */
    RDF_FIRST("http://www.w3.org/1999/02/22-rdf-syntax-ns#first");

    public String uri;

    Predicate(String uri) {
        this.uri = uri;
    }

    public static boolean isSpecificPredicate(String uri) {
        return Arrays.stream(values()).filter(p -> p.uri.equals(uri)).findFirst().isPresent();
    }

    public static List<String> getPredicateList() {
        return Arrays.stream(values()).map(p -> p.uri).collect(Collectors.toList());
    }

}
