/**
 *
 * $Id$
 */
package com.zipc.garden.model.bp.validation;

import com.zipc.garden.model.bp.BPSpecElement;
import com.zipc.garden.model.bp.BPState;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link com.zipc.garden.model.bp.BPSpec}. This doesn't really do anything, and it's not a
 * real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code
 * generator can be extended. This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface BPSpecValidator {
    boolean validate();

    boolean validatePaths(EList<BPSpecElement> value);

    boolean validateStates(EList<BPState> value);
}