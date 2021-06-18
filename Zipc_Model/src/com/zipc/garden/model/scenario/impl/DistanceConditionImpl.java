/**
 */
package com.zipc.garden.model.scenario.impl;

import com.zipc.garden.model.scenario.DistanceCondition;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Distance Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class DistanceConditionImpl extends VehicleDetailConditionImpl implements DistanceCondition {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DistanceConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.DISTANCE_CONDITION;
    }

} //DistanceConditionImpl
