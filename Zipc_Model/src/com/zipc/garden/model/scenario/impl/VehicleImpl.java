/**
 */
package com.zipc.garden.model.scenario.impl;

import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.Vehicle;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Vehicle</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class VehicleImpl extends ActorImpl implements Vehicle {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VehicleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.VEHICLE;
    }

} //VehicleImpl
