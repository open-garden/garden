/**
 */
package com.zipc.garden.model.scenario.impl;

import com.zipc.garden.model.scenario.AccelCondition;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Accel Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class AccelConditionImpl extends VehicleDetailConditionImpl implements AccelCondition {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AccelConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.ACCEL_CONDITION;
    }

} //AccelConditionImpl
