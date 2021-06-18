/**
 *
 * $Id$
 */
package com.zipc.garden.model.fmcs.validation;

import com.zipc.garden.model.fmcs.FMCSExpression;

/**
 * A sample validator interface for {@link com.zipc.garden.model.fmcs.FMCSNotExpression}. This doesn't really do anything, and
 * it's not a real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how
 * EMF's code generator can be extended. This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface FMCSNotExpressionValidator {
    boolean validate();

    boolean validateExpression(FMCSExpression value);
}