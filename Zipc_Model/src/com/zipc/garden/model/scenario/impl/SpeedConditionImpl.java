/**
 */
package com.zipc.garden.model.scenario.impl;

import com.zipc.garden.model.scenario.ScenarioPackage;
import com.zipc.garden.model.scenario.SpeedCondition;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Speed Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class SpeedConditionImpl extends VehicleDetailConditionImpl implements SpeedCondition {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SpeedConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.SPEED_CONDITION;
    }

} //SpeedConditionImpl
