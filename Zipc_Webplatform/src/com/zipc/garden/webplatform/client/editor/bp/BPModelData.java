package com.zipc.garden.webplatform.client.editor.bp;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.zipc.garden.model.bp.BPBehavior;
import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPSetting;
import com.zipc.garden.model.bp.BPStep;

/**
 * Class that creates the data to be displayed in behavior-pattern
 */
public class BPModelData {

    /** Step column title */
    public static final String STEP = "step";

    /**
     * Get behavior-pattern data in JSON format [{}]
     * @param allNum First record number of page break
     * @param fileNo Pattern number
     * @param bpSetting Setting information of BPEditor
     * @return behavior-pattern data in JSON format
     */
    public static JSONObject getRecordsAsJsonString(AtomicInteger allNum, int fileNo, BPSetting bpSetting) {
        int stepCount = 0;
        // root json -> { 'stepCount': 数値, 'content': [ 配列 ] }
        JSONObject jsonRoot = new JSONObject();
        JsArray<JavaScriptObject> data = JavaScriptObject.createArray().cast();
        // insert object in item
        for (int patternIndex = 0; patternIndex < Math.min(2000, bpSetting.getPattern().size()); patternIndex++) {
            // PatternID
            BPBehaviorPattern bpBehaviorPattern = bpSetting.getPattern().get(patternIndex);
            JSONObject bpItem = new JSONObject();
            // insert patternid in item object
            if (allNum != null) {
                bpItem.put("no", new JSONNumber(allNum.incrementAndGet()));
                bpItem.put("patternid", new JSONString(fileNo + "_" + bpSetting.getPattern().get(patternIndex).getId()));
            } else {
                bpItem.put("no", new JSONString(bpSetting.getPattern().get(patternIndex).getId()));
            }

            // insert statemachine Array in item
            JsArray<JavaScriptObject> stateArray = JavaScriptObject.createArray().cast();
            bpItem.put("stateMachine", new JSONArray(stateArray));
            for (int behaviorIndex = 0; behaviorIndex < Math.min(100, bpBehaviorPattern.getBehaviors().size()); behaviorIndex++) {
                BPBehavior bpBehavior = bpBehaviorPattern.getBehaviors().get(behaviorIndex);
                String stmName = bpBehavior.getStateMachineRef().getName();
                // build stateMachine Object
                JSONObject stateMachineObject = new JSONObject();
                stateMachineObject.put("initial", new JSONString(stmName));
                String rowSpanAttr = "";
                if (behaviorIndex == 0) {
                    rowSpanAttr = "true";
                } else {
                    rowSpanAttr = "false";
                }
                String stepAttr = "";
                String stepType = "";
                int stepSize = Math.min(200, bpBehavior.getSteps().size());
                stepCount = 0;
                for (int stepIndex = 0; stepIndex < stepSize; stepIndex++) {
                    String stepName = "";
                    BPStep bpStep = bpBehavior.getSteps().get(stepIndex);
                    if (stepIndex == 0 && bpStep.getState() != null) {
                        // First step should be initial state.
                        stepAttr = bpStep.getState().getValue();
                        stepType = "state";
                        stepName = "Initial";
                    } else {
                        if (bpStep.getEvent() != null) {
                            stepAttr = bpStep.getEvent().getValue() + " " + bpStep.getState().getValue();
                        }
                        stepType = "event";
                        stepName = STEP + stepIndex;
                    }

                    // Create step object
                    JSONObject stepObject = new JSONObject();
                    stepObject.put("attr", new JSONString(stepAttr));
                    stepObject.put("type", new JSONString(stepType));
                    stateMachineObject.put(stepName, stepObject);
                    stepAttr = "";
                    stepType = "";
                    stepCount++;
                }
                stateMachineObject.put("rowspan", new JSONString(rowSpanAttr));
                stateArray.push(stateMachineObject.getJavaScriptObject());
                // insert rowcount in item object
                bpItem.put("colcount", new JSONNumber(Math.min(100, bpBehaviorPattern.getBehaviors().size())));
                // json content entity -> { 'patternID': 数値, 'stateMachine': '文字列', 'step(1..stepCount)': '文字列' }
            }
            data.push(bpItem.getJavaScriptObject());
        }
        // insert columns in json data
        JSONArray columns = new JSONArray();
        columns.set(1, new JSONString("Initial"));
        IntStream.range(2, stepCount + 1).forEach(n -> columns.set(n, new JSONString("step" + (n - 1))));
        jsonRoot.put("columns", columns);
        // insert items key in stateMachine
        jsonRoot.put("items", new JSONArray(data));
        return jsonRoot;
    }
}
