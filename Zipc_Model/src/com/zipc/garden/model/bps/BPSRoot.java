/**
 */
package com.zipc.garden.model.bps;

import com.zipc.garden.model.core.AbstractRootElement;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.BPSRoot#getActiveOptionIndex <em>Active Option Index</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSRoot#getOptions <em>Options</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSRoot#getInstanceArc <em>Instance Arc</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSRoot()
 * @model
 * @generated
 */
public interface BPSRoot extends AbstractRootElement {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * @throws IndexOutOfBoundsException if set out of bounds index using {@link #setActiveOptionIndex} <!-- end-model-doc -->
     * @model kind="operation" required="true"
     * @generated
     */
    BPSOption getActiveOption();

    /**
     * Returns the value of the '<em><b>Active Option Index</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Active Option Index</em>' attribute.
     * @see #setActiveOptionIndex(int)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSRoot_ActiveOptionIndex()
     * @model required="true"
     * @generated
     */
    int getActiveOptionIndex();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSRoot#getActiveOptionIndex <em>Active Option Index</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Active Option Index</em>' attribute.
     * @see #getActiveOptionIndex()
     * @generated
     */
    void setActiveOptionIndex(int value);

    /**
     * Returns the value of the '<em><b>Options</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bps.BPSOption}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Options</em>' containment reference list.
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSRoot_Options()
     * @model containment="true" required="true"
     * @generated
     */
    EList<BPSOption> getOptions();

    /**
     * Returns the value of the '<em><b>Instance Arc</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Instance Arc</em>' containment reference.
     * @see #setInstanceArc(BPSInstanceArc)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSRoot_InstanceArc()
     * @model containment="true"
     * @generated
     */
    BPSInstanceArc getInstanceArc();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSRoot#getInstanceArc <em>Instance Arc</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Instance Arc</em>' containment reference.
     * @see #getInstanceArc()
     * @generated
     */
    void setInstanceArc(BPSInstanceArc value);
} // BPSRoot
