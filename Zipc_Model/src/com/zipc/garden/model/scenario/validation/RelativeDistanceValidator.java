/**
 *
 * $Id$
 */
package com.zipc.garden.model.scenario.validation;

import com.zipc.garden.model.scenario.DistanceDirection;
import com.zipc.garden.model.scenario.Vehicle;

/**
 * A sample validator interface for {@link com.zipc.garden.model.scenario.RelativeDistance}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface RelativeDistanceValidator {
    boolean validate();

    boolean validateVehicle(Vehicle value);
    boolean validateDirection(DistanceDirection value);
    boolean validateMeter(double value);

    boolean validateDistance(double value);
}
