/**
 */
package com.zipc.garden.model.scenario.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.scenario.AbsoluteLaneCondition;
import com.zipc.garden.model.scenario.ComparisonOperator;
import com.zipc.garden.model.scenario.ScenarioPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Absolute Lane Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.impl.AbsoluteLaneConditionImpl#getLaneId <em>Lane Id</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.impl.AbsoluteLaneConditionImpl#getOperator <em>Operator</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AbsoluteLaneConditionImpl extends LaneConditionImpl implements AbsoluteLaneCondition {
    /**
     * The default value of the '{@link #getLaneId() <em>Lane Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLaneId()
     * @generated
     * @ordered
     */
    protected static final int LANE_ID_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getLaneId() <em>Lane Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLaneId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int laneId = LANE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperator()
     * @generated
     * @ordered
     */
    protected static final ComparisonOperator OPERATOR_EDEFAULT = ComparisonOperator.EQUAL;

    /**
     * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOperator()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected ComparisonOperator operator = OPERATOR_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbsoluteLaneConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScenarioPackage.Literals.ABSOLUTE_LANE_CONDITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getLaneId() {
        return laneId;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLaneId(int newLaneId) {
        int oldLaneId = laneId;
        laneId = newLaneId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ABSOLUTE_LANE_CONDITION__LANE_ID, oldLaneId, laneId));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ComparisonOperator getOperator() {
        return operator;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOperator(ComparisonOperator newOperator) {
        ComparisonOperator oldOperator = operator;
        operator = newOperator == null ? OPERATOR_EDEFAULT : newOperator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ABSOLUTE_LANE_CONDITION__OPERATOR, oldOperator, operator));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ScenarioPackage.ABSOLUTE_LANE_CONDITION__LANE_ID:
                return getLaneId();
            case ScenarioPackage.ABSOLUTE_LANE_CONDITION__OPERATOR:
                return getOperator();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ScenarioPackage.ABSOLUTE_LANE_CONDITION__LANE_ID:
                setLaneId((Integer)newValue);
                return;
            case ScenarioPackage.ABSOLUTE_LANE_CONDITION__OPERATOR:
                setOperator((ComparisonOperator)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ScenarioPackage.ABSOLUTE_LANE_CONDITION__LANE_ID:
                setLaneId(LANE_ID_EDEFAULT);
                return;
            case ScenarioPackage.ABSOLUTE_LANE_CONDITION__OPERATOR:
                setOperator(OPERATOR_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ScenarioPackage.ABSOLUTE_LANE_CONDITION__LANE_ID:
                return laneId != LANE_ID_EDEFAULT;
            case ScenarioPackage.ABSOLUTE_LANE_CONDITION__OPERATOR:
                return operator != OPERATOR_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (laneId: ");
        result.append(laneId);
        result.append(", operator: ");
        result.append(operator);
        result.append(')');
        return result.toString();
    }

} //AbsoluteLaneConditionImpl
