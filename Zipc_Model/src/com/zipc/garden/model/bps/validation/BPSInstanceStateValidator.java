/**
 *
 * $Id$
 */
package com.zipc.garden.model.bps.validation;

import com.zipc.garden.model.bps.BPSInstancePseudoStateType;
import com.zipc.garden.model.bps.BPSInstanceTransition;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link com.zipc.garden.model.bps.BPSInstanceState}. This doesn't really do anything, and
 * it's not a real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how
 * EMF's code generator can be extended. This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface BPSInstanceStateValidator {
    boolean validate();

    boolean validateOriginalName(String value);

    boolean validateType(BPSInstancePseudoStateType value);

    boolean validateTransitions(EList<BPSInstanceTransition> value);
}
