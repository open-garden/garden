/**
 *
 * $Id$
 */
package com.zipc.garden.model.fsm.validation;

import com.zipc.garden.model.fsm.FSMDAction;
import com.zipc.garden.model.fsm.FSMDEvent;
import com.zipc.garden.model.fsm.FSMDLine;
import com.zipc.garden.model.fsm.FSMDMemo;
import com.zipc.garden.model.fsm.FSMDProperty;
import com.zipc.garden.model.fsm.FSMDRegion;
import com.zipc.garden.model.fsm.FSMDTransition;

import com.zipc.garden.model.fsm.FSMDVariable;
import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link com.zipc.garden.model.fsm.FSMDStateMachine}. This doesn't really do anything, and
 * it's not a real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how
 * EMF's code generator can be extended. This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface FSMDStateMachineValidator {
    boolean validate();

    boolean validateProperties(EList<FSMDProperty> value);

    boolean validateTransitions(EList<FSMDTransition> value);

    boolean validateMemos(EList<FSMDMemo> value);

    boolean validateRegions(EList<FSMDRegion> value);

    boolean validateFmsdevent(EList<FSMDEvent> value);

    boolean validateActions(EList<FSMDAction> value);

    boolean validateLines(EList<FSMDLine> value);

    boolean validateVariables(EList<FSMDVariable> value);

    boolean validateManhattanMode(int value);
}