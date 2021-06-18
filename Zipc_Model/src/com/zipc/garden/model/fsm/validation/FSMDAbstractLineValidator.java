/**
 *
 * $Id$
 */
package com.zipc.garden.model.fsm.validation;

import com.zipc.garden.model.fsm.FSMDLineType;
import com.zipc.garden.model.fsm.FSMDPoint;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link com.zipc.garden.model.fsm.FSMDAbstractLine}. This doesn't really do anything, and
 * it's not a real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how
 * EMF's code generator can be extended. This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface FSMDAbstractLineValidator {
    boolean validate();

    boolean validateSourceAnchorX(double value);

    boolean validateSourceAnchorY(double value);

    boolean validateTargetAnchorX(double value);

    boolean validateTargetAnchorY(double value);

    boolean validateConnectionPoint(EList<FSMDPoint> value);

    boolean validateType(FSMDLineType value);
}
