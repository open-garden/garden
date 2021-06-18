package com.zipc.garden.job.scs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.zipc.garden.model.cb.CBAllCombination;
import com.zipc.garden.model.cb.CBFile;
import com.zipc.garden.model.cb.CBLogic;
import com.zipc.garden.model.cb.CBPairWise;
import com.zipc.garden.model.cb.CBRoot;
import com.zipc.garden.model.scs.SCSPatternReference;

/**
 * A class that creates combination pattern information for a scenario set.
 */
public class SCSCombinationLogic {

    /**
     * The process of creating a combination of scenario sets is executed.
     * @param cbRoot cb root
     * @param scsPtnRefMap key: Pattern information existing on RDF / value: uuid (fps or bps)
     * @return Combination of scenario set patterns
     */
    public List<List<SCSPatternReference>> execute(CBRoot cbRoot, Map<SCSPatternReference, String> scsPtnRefMap) {
        return createCombination(Arrays.asList(cbRoot.getLogic()), scsPtnRefMap);
    }

    /**
     * Create a combination of patterns for the scenario set.
     * @param cbLogicList Information on the files used for the combination
     * @param scsPtnRefMap key: Pattern information existing on RDF / value: uuid (fps or bps)
     * @return Combination of scenario set patterns
     */
    private List<List<SCSPatternReference>> createCombination(List<CBLogic> cbLogicList, Map<SCSPatternReference, String> scsPtnRefMap) {
        List<List<SCSPatternReference>> results = new ArrayList<List<SCSPatternReference>>();
        for (CBLogic cbLogic : cbLogicList) {
            if (cbLogic.getChildren() != null && !cbLogic.getChildren().isEmpty()) {
                List<List<SCSPatternReference>> tmpResults = createCombination(cbLogic.getChildren(), scsPtnRefMap);
                results.addAll(tmpResults);
            }
            if (cbLogic.getType() instanceof CBAllCombination) {
                for (CBFile cbFile : cbLogic.getFile()) {
                    List<SCSPatternReference> scsList = scsPtnRefMap.entrySet().stream().filter(e -> cbFile.getUuid().equals(e.getValue())).map(e -> e.getKey())
                            .collect(Collectors.toList());
                    results = addAllCombinationPattern(results, scsList);
                }
            } else if (cbLogic.getType() instanceof CBPairWise) {
                // TODO: いずれ実装
                results = addPairWisePattern(results);
            }
        }
        return results;
    }

    /**
     * Combination creation process when the type of CBLogic is CBAllCombination.
     * @param list1 Combination pattern information being generated.
     * @param list2 Pattern information to be combined with list1.
     * @return Combination of scenario set patterns
     */
    private List<List<SCSPatternReference>> addAllCombinationPattern(List<List<SCSPatternReference>> list1, List<SCSPatternReference> list2) {
        if (list1 == null || list1.isEmpty()) {
            return list2.stream().map(l2 -> new ArrayList<SCSPatternReference>(Arrays.asList(l2))).collect(Collectors.toList());
        }
        return list1.stream().flatMap(l1 -> list2.stream().map(l2 -> {
            ArrayList<SCSPatternReference> tmp = new ArrayList<SCSPatternReference>();
            tmp.addAll(l1);
            tmp.add(l2);
            return tmp;
        })).collect(Collectors.toList());
    }

    /**
     * <pre>
     * Unimplemented
     * 未実装
     * </pre>
     * 
     * @param list1
     * @return
     */
    private List<List<SCSPatternReference>> addPairWisePattern(List<List<SCSPatternReference>> list1) {
        // TODO: いずれ実装
        return list1;
    }
}
