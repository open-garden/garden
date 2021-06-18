package com.zipc.garden.job.bp;

import java.util.List;

import com.zipc.garden.model.bp.BPSpec;
import com.zipc.garden.model.bp.BPStateMachine;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;

/**
 * <pre>
 * Of the concrete paths of Behavior Pattern, the path for one FSM is generated.
 * 
 * BehaviorPatternの具体的なパスのうち，FSM一つ分のパスを生成する．
 * </pre>
 */
public interface IPathGenerator {

    /**
     * The specified generation target and the name of FinateStateMachine are set.
     * @param target Generation target (finite state machine). <br>
     *            生成対象の Finite State Machine
     * @param name name of target
     */
    void set(BPSInstanceStateMachine target, String name);

    /**
     * Gets the BPStateMachine referenced by the generated path.
     * @return The BPStateMachine referenced by the generated path. <br>
     *         生成したパスが参照しているBPStateMachine
     */
    List<BPStateMachine> getStateMachines();

    /**
     * Generates and retrieves the path.
     * @return Generated path. <br>
     *         生成したパス. BPSpecは以下の情報を持つ.
     * 
     *         <pre>
     *         BPState#name = FSMのID
     *         BPState#value = ステート名称
     *         BPEvent#name = FSMのID
     *         BPEvent#value = イベント名称
     *         </pre>
     */
    List<BPSpec> generate();

    /**
     * It returns how many scripts to split and generate.
     * @param numOfMaxSpecParScript Maximum number of inspection formulas to be included in one script <br>
     *            ひとつのスクリプトに含める検査式の最大件数
     * @return It returns how many scripts to split and generate. If it does not support split generation, it returns 1. <br>
     *         いくつのスクリプトに分割して生成するか．分割生成に対応していない場合，1を返す．
     */
    public int calculateNumberOfScripts(int numOfMaxSpecParScript);
}
