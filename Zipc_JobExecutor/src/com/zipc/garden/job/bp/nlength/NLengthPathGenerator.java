package com.zipc.garden.job.bp.nlength;

import java.util.Optional;

import com.zipc.garden.job.bp.BPGenerationException;
import com.zipc.garden.job.bp.PathGenerator;
import com.zipc.garden.model.bps.BPSInstancePseudoStateType;
import com.zipc.garden.model.bps.BPSInstanceState;
import com.zipc.garden.model.bps.BPSInstanceStateMachine;
import com.zipc.garden.model.bps.BPSInstanceTransition;

/**
 * A class that performs generation processing with the specified path length.
 */
public class NLengthPathGenerator extends PathGenerator {

    /** The initial state of the State Machine. */
    private BPSInstanceState initialState;

    /** the length of the path to be generated. */
    private int pathLength;

    /**
     * <pre>
     * constructor.
     * Set {@link PathGenerator#allowToStayOwnState} to True.
     * </pre>
     */
    public NLengthPathGenerator() {
        setAllowToStayOwnState(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(BPSInstanceStateMachine target, String name) {
        super.set(target, name);
        // memo: generate内で初期ステートを算出させるために，処理対象のFSMが変わった場合は初期ステートを空にしている．
        this.initialState = null;
    }

    /**
     * <pre>
     * Get the initial state of {@link PathGenerator#target}.
     * If it does not exist, null is returned.
     * </pre>
     * 
     * @return initial state. Null if it does not exist.
     */
    public BPSInstanceState getDefaultInitialState() {
        BPSInstanceStateMachine target = get();
        if (target == null)
            return null;

        BPSInstanceState initialState = target.getInitialState();
        if (initialState == null || BPSInstancePseudoStateType.INITIAL != initialState.getType())
            return null;

        Optional<BPSInstanceState> initialNode = initialState.getTransitions().stream().map(BPSInstanceTransition::getTarget).findFirst();
        if (initialNode.isPresent()) {
            return initialNode.get();
        }

        return null;
    }

    /**
     * <pre>
     * Set the length of the path to be generated to {@link #pathLength}.
     * If the specified path length is less than or equal to 0, an IllegalArgumentException will be thrown.
     * </pre>
     * 
     * @param pathLength The length of the path to generate.<br>
     *            The length of the path is equal to the number of states contained in the path. <br>
     *            生成するパスの長さ．パスの長さは，パスに含まれる状態の数と等しい．
     */
    public void setLength(int pathLength) {
        if (pathLength <= 0) {
            throw new IllegalArgumentException("pathLength must greater than 0.");
        }
        this.pathLength = pathLength;
    }

    /**
     * <pre>
     * {@inheritDoc}
     * 
     * If {@link PathGenerator#target} does not exist, a BPGenerationException will be thrown.
     * </pre>
     */
    @Override
    protected void doGenerate() {
        if (get() == null) {
            throw new BPGenerationException("Call set(BPSInstanceStateMachine) before generate.");
        }
        if (initialState == null) {
            initialState = getDefaultInitialState();
        }
        generate(initialState, pathLength - 1);
    }
}
