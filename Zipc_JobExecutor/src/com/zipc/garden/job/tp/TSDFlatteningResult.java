package com.zipc.garden.job.tp;

import java.util.HashMap;
import java.util.Map;

import com.zipc.garden.model.tc.TCNode;

/**
 * A class that manages the results of flattening processing.
 */
public class TSDFlatteningResult {

    /**
     * <pre>
     * A string indicating the beginning of the parameter part.
     * パラメータ部分の始まりを示す文字列
     * </pre>
     */
    private final String ActsKeywordOfStartParameterSection = "[Parameter]";

    /**
     * <pre>
     * A string indicating the beginning of the constraint expression part.
     * 制約式部分の始まりを示す文字列
     * </pre>
     */
    private final String ActsKeywordOfStartConstraintSection = "[Constraint]";

    /** Parameter part of {@link #actsinfo} */
    private String parameters;

    /** Constraint expression part of {@link #actsinfo} */
    private String constraints;

    /** ACTS information (including parameters and constraint expressions) */
    public String actsinfo;

    /**
     * <pre>
     * ID map.
     *  key: ID of the node created by the flattening process
     *  value: TCNode
     * </pre>
     */
    public Map<String, TCNode> idMap = new HashMap<String, TCNode>();

    /**
     * Get ACTS information.
     * @return {@link #actsinfo}
     */
    public String getActsinfo() {
        return actsinfo;
    }

    /**
     * <pre>
     * Sets the specified ACTS to {@link #actsinfo}.
     * It also splits ACTS into a parameter part and a constraint expression part and sets them to {@ link # parameters} and {@ link # constraints}.
     * 
     * 指定されたACTSを{@link #actsinfo}に設定します。
     * また、ACTSをパラメーター部分と制約式部分に分割し、それらを{@link #parameters}と{@link #constraints}に設定します。
     * </pre>
     * 
     * @param actsinfo ACTS information
     */
    public void setActsinfo(String actsinfo) {
        this.actsinfo = actsinfo;
        if (actsinfo != null) {
            parameters = null;
            constraints = null;
            int startOfParam = actsinfo.indexOf(ActsKeywordOfStartParameterSection);
            int startOfConst = actsinfo.indexOf(ActsKeywordOfStartConstraintSection);
            if (startOfParam != -1) {
                int endOfParam = (startOfConst != -1) ? startOfConst : actsinfo.length();
                String parametersWithLabel = actsinfo.substring(startOfParam, endOfParam);
                parameters = parametersWithLabel.substring(parametersWithLabel.indexOf('\n') + 1);
            }
            if (startOfConst != -1) {
                String constraintsWithLabel = actsinfo.substring(startOfConst);
                constraints = constraintsWithLabel.substring(constraintsWithLabel.indexOf('\n') + 1);
            }
        }
    }

    /**
     * Get the ID map.
     * @return {@link #idMap}
     */
    public Map<String, TCNode> getIdMap() {
        return idMap;
    }

    /**
     * Set the ID map.
     * @param idMap The map to set to {@link #idMap}.
     */
    public void setIdMap(Map<String, TCNode> idMap) {
        this.idMap = idMap;
    }

    /**
     * <pre>
     * Get the parameter part of {@link #getActsinfo()}.
     * 
     * {@link #getActsinfo()}のパラメータ部分を取得します。
     * </pre>
     * 
     * @return Parameter part of {@link #getActsinfo()}
     */
    public String getParametersSection() {
        return parameters;
    }

    /**
     * <pre>
     * Gets the constraint expression part of {@link #getActsinfo()}.
     * 
     * {@link #getActsinfo()}の制約式の部分を取得します。
     * </pre>
     * 
     * @return Constraint expression part of {@link #getActsinfo()}
     */
    public String getConstraintsSection() {
        return constraints;
    }

}
