/**
 */
package com.zipc.garden.model.scenario.impl;

import com.zipc.garden.model.scenario.LaneCondition;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lane Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class LaneConditionImpl extends VehicleDetailConditionImpl implements LaneCondition {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LaneConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.LANE_CONDITION;
    }

} //LaneConditionImpl
