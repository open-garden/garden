/**
 *
 * $Id$
 */
package com.zipc.garden.model.scs.validation;

/**
 * A sample validator interface for {@link com.zipc.garden.model.scs.SCSPatternReference}. This doesn't really do anything, and
 * it's not a real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how
 * EMF's code generator can be extended. This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface SCSPatternReferenceValidator {
    boolean validate();

    boolean validateFileId(long value);

    boolean validatePatternId(long value);
}