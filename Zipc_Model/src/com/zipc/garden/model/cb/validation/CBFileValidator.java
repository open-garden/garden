/**
 *
 * $Id$
 */
package com.zipc.garden.model.cb.validation;

import com.zipc.garden.model.core.AbstractRootElement;

/**
 * A sample validator interface for {@link com.zipc.garden.model.cb.CBFile}. This doesn't really do anything, and it's not a
 * real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code
 * generator can be extended. This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface CBFileValidator {
    boolean validate();

    boolean validateUuid(String value);

    boolean validatePattern(String value);

    boolean validateAbstractRoot(AbstractRootElement value);

    boolean validateHash(String value);
}
