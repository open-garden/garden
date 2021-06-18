/**
 */
package com.zipc.garden.model.bp;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Behavior Pattern</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.BPBehaviorPattern#getBehaviors <em>Behaviors</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPBehaviorPattern#getSpecification <em>Specification</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPBehaviorPattern#getId <em>Id</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bp.BPPackage#getBPBehaviorPattern()
 * @model
 * @generated
 */
public interface BPBehaviorPattern extends EObject {
    /**
     * Returns the value of the '<em><b>Behaviors</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.bp.BPBehavior}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Behaviors</em>' containment reference list.
     * @see com.zipc.garden.model.bp.BPPackage#getBPBehaviorPattern_Behaviors()
     * @model containment="true"
     * @generated
     */
    EList<BPBehavior> getBehaviors();

    /**
     * Returns the value of the '<em><b>Specification</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Specification</em>' reference.
     * @see #setSpecification(BPSpec)
     * @see com.zipc.garden.model.bp.BPPackage#getBPBehaviorPattern_Specification()
     * @model required="true"
     * @generated
     */
    BPSpec getSpecification();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPBehaviorPattern#getSpecification <em>Specification</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Specification</em>' reference.
     * @see #getSpecification()
     * @generated
     */
    void setSpecification(BPSpec value);

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see com.zipc.garden.model.bp.BPPackage#getBPBehaviorPattern_Id()
     * @model required="true"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPBehaviorPattern#getId <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // BPBehaviorPattern
