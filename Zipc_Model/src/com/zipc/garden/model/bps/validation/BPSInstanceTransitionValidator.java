/**
 *
 * $Id$
 */
package com.zipc.garden.model.bps.validation;

import com.zipc.garden.model.bps.BPSInstanceState;

/**
 * A sample validator interface for {@link com.zipc.garden.model.bps.BPSInstanceTransition}. This doesn't really do anything,
 * and it's not a real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate
 * how EMF's code generator can be extended. This can be disabled with -vmargs
 * -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface BPSInstanceTransitionValidator {
    boolean validate();

    boolean validatePriority(int value);

    boolean validateTrigger(String value);

    boolean validateEvent(String value);

    boolean validateCondition(String value);

    boolean validateAction(String value);

    boolean validateSource(BPSInstanceState value);

    boolean validateTarget(BPSInstanceState value);
}
