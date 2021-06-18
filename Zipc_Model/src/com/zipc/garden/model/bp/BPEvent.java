/**
 */
package com.zipc.garden.model.bp;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Event</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.BPEvent#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPEvent#getValue <em>Value</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bp.BPPackage#getBPEvent()
 * @model
 * @generated
 */
public interface BPEvent extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see com.zipc.garden.model.bp.BPPackage#getBPEvent_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPEvent#getName <em>Name</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see com.zipc.garden.model.bp.BPPackage#getBPEvent_Value()
     * @model required="true"
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPEvent#getValue <em>Value</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

} // BPEvent
