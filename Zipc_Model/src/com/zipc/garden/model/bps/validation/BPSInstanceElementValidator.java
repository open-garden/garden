/**
 *
 * $Id$
 */
package com.zipc.garden.model.bps.validation;

/**
 * A sample validator interface for {@link com.zipc.garden.model.bps.BPSInstanceElement}. This doesn't really do anything, and
 * it's not a real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how
 * EMF's code generator can be extended. This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface BPSInstanceElementValidator {
    boolean validate();

    boolean validateOriginModelName(String value);
}
