/**
 *
 * $Id$
 */
package com.zipc.garden.model.bp.validation;

import com.zipc.garden.model.bp.BPBehaviorPattern;
import com.zipc.garden.model.bp.BPStateMachine;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link com.zipc.garden.model.bp.BPSetting}. This doesn't really do anything, and it's not a
 * real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code
 * generator can be extended. This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface BPSettingValidator {
    boolean validate();

    boolean validateStateMachines(EList<BPStateMachine> value);

    boolean validatePattern(EList<BPBehaviorPattern> value);
}
