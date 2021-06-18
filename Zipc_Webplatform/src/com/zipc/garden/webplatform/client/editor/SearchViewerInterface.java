package com.zipc.garden.webplatform.client.editor;

import java.util.List;

/**
 * It is an interface to reflect the SPARQL search result to the Viewer.<br>
 * Please implement it in the Viewer.
 */
public interface SearchViewerInterface {

    /**
     * Numeric values ​​(comma separated) are reflected on the setting screen of FP, BP and SCS files. <br>
     * Used in applying SPARQL search results.
     * @param patternNos Pattern number (comma separated)
     * @param uuids UUID for FPS, BPS, SCSS files
     */
    public void setRowIds(List<String> patternNos, List<String> uuids);

    /**
     * Apply the contents entered on the screen and reload the page.
     */
    public void doApply();
}
