/**
 */
package com.zipc.garden.model.bps;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Instance State Machine</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getOriginalName <em>Original Name</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getOriginalUuid <em>Original Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getEvalPriority <em>Eval Priority</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getInitialState <em>Initial State</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getStates <em>States</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getVariables <em>Variables</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceStateMachine()
 * @model
 * @generated
 */
public interface BPSInstanceStateMachine extends BPSInstanceElement, BPSEnablement {
    /**
     * Returns the value of the '<em><b>Original Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Original Name</em>' attribute.
     * @see #setOriginalName(String)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceStateMachine_OriginalName()
     * @model
     * @generated
     */
    String getOriginalName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getOriginalName <em>Original Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Original Name</em>' attribute.
     * @see #getOriginalName()
     * @generated
     */
    void setOriginalName(String value);

    /**
     * Returns the value of the '<em><b>Original Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Original Uuid</em>' attribute.
     * @see #setOriginalUuid(String)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceStateMachine_OriginalUuid()
     * @model
     * @generated
     */
    String getOriginalUuid();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getOriginalUuid <em>Original Uuid</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Original Uuid</em>' attribute.
     * @see #getOriginalUuid()
     * @generated
     */
    void setOriginalUuid(String value);

    /**
     * Returns the value of the '<em><b>Eval Priority</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> ステートマシンの評価順．小さいほど，評価順が早い． <!-- end-model-doc -->
     * @return the value of the '<em>Eval Priority</em>' attribute.
     * @see #setEvalPriority(int)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceStateMachine_EvalPriority()
     * @model required="true"
     * @generated
     */
    int getEvalPriority();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getEvalPriority <em>Eval Priority</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Eval Priority</em>' attribute.
     * @see #getEvalPriority()
     * @generated
     */
    void setEvalPriority(int value);

    /**
     * Returns the value of the '<em><b>Initial State</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Initial State</em>' containment reference.
     * @see #setInitialState(BPSInstanceState)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceStateMachine_InitialState()
     * @model containment="true"
     * @generated
     */
    BPSInstanceState getInitialState();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine#getInitialState <em>Initial State</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Initial State</em>' reference.
     * @see #getInitialState()
     * @generated
     */
    void setInitialState(BPSInstanceState value);

    /**
     * Returns the value of the '<em><b>States</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bps.BPSInstanceState}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>States</em>' containment reference list.
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceStateMachine_States()
     * @model containment="true"
     * @generated
     */
    EList<BPSInstanceState> getStates();

    /**
     * Returns the value of the '<em><b>Variables</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bps.BPSVariable}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Variables</em>' containment reference list.
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceStateMachine_Variables()
     * @model containment="true"
     * @generated
     */
    EList<BPSVariable> getVariables();

} // BPSInstanceStateMachine
