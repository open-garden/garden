/**
 *
 * $Id$
 */
package com.zipc.garden.model.core.validation;

import com.zipc.garden.model.core.AbstractNode;
import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link com.zipc.garden.model.core.AbstractDiagram}. This doesn't really do anything, and
 * it's not a real EMF artifact. It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how
 * EMF's code generator can be extended. This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface AbstractDiagramValidator {
    boolean validate();

    boolean validateScrollX(int value);

    boolean validateScrollY(int value);

    boolean validatePositionX(int value);

    boolean validatePositionY(int value);

    boolean validateGridSize(int value);

    boolean validateNodes(EList<AbstractNode> value);

    boolean validateLineMode(int value);
}