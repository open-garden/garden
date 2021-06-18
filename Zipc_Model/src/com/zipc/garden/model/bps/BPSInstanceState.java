/**
 */
package com.zipc.garden.model.bps;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Instance State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceState#getOriginalName <em>Original Name</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceState#getType <em>Type</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceState#getTransitions <em>Transitions</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceState()
 * @model
 * @generated
 */
public interface BPSInstanceState extends BPSInstanceElement, BPSEnablement {
    /**
     * Returns the value of the '<em><b>Original Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Original Name</em>' attribute.
     * @see #setOriginalName(String)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceState_OriginalName()
     * @model
     * @generated
     */
    String getOriginalName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceState#getOriginalName <em>Original Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Original Name</em>' attribute.
     * @see #getOriginalName()
     * @generated
     */
    void setOriginalName(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. The default value is <code>"NONE"</code>. The literals are
     * from the enumeration {@link com.zipc.garden.model.bps.BPSInstancePseudoStateType}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see com.zipc.garden.model.bps.BPSInstancePseudoStateType
     * @see #setType(BPSInstancePseudoStateType)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceState_Type()
     * @model default="NONE"
     * @generated
     */
    BPSInstancePseudoStateType getType();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceState#getType <em>Type</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see com.zipc.garden.model.bps.BPSInstancePseudoStateType
     * @see #getType()
     * @generated
     */
    void setType(BPSInstancePseudoStateType value);

    /**
     * Returns the value of the '<em><b>Transitions</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bps.BPSInstanceTransition}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Transitions</em>' containment reference list.
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceState_Transitions()
     * @model containment="true"
     * @generated
     */
    EList<BPSInstanceTransition> getTransitions();

} // BPSInstanceState
