/**
 */
package com.zipc.garden.model.scenario.impl;

import com.zipc.garden.model.scenario.EgoVehicle;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ego Vehicle</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class EgoVehicleImpl extends VehicleImpl implements EgoVehicle {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EgoVehicleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.EGO_VEHICLE;
    }

} //EgoVehicleImpl
