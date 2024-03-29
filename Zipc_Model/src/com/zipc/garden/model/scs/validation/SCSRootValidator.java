/**
 *
 * $Id$
 */
package com.zipc.garden.model.scs.validation;

import com.zipc.garden.model.scs.SCSPattern;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link com.zipc.garden.model.scs.SCSRoot}. This doesn't really do anything, and it's not a
 * real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code
 * generator can be extended. This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface SCSRootValidator {
    boolean validate();

    boolean validateAll(double value);

    boolean validateBegin(int value);

    boolean validateEnd(int value);

    boolean validateStatus(int value);

    boolean validateMessage(String value);

    boolean validatePatterns(EList<SCSPattern> value);

    boolean validateMax(int value);

    boolean validateRowIds(String value);
}
