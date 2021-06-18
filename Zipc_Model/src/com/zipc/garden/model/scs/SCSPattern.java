/**
 */
package com.zipc.garden.model.scs;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Pattern</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.scs.SCSPattern#getLsc <em>Lsc</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.SCSPattern#getPatternreferences <em>Patternreferences</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.SCSPattern#getId <em>Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.SCSPattern#getPatternValue <em>Pattern Value</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.SCSPattern#getCscUuid <em>Csc Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.SCSPattern#getCscFileName <em>Csc File Name</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.scs.SCSPackage#getSCSPattern()
 * @model
 * @generated
 */
public interface SCSPattern extends EObject {
    /**
     * Returns the value of the '<em><b>Lsc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Lsc</em>' attribute.
     * @see #setLsc(String)
     * @see com.zipc.garden.model.scs.SCSPackage#getSCSPattern_Lsc()
     * @model
     * @generated
     */
    String getLsc();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scs.SCSPattern#getLsc <em>Lsc</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Lsc</em>' attribute.
     * @see #getLsc()
     * @generated
     */
    void setLsc(String value);

    /**
     * Returns the value of the '<em><b>Patternreferences</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.scs.SCSPatternReference}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Patternreferences</em>' containment reference list.
     * @see com.zipc.garden.model.scs.SCSPackage#getSCSPattern_Patternreferences()
     * @model containment="true"
     * @generated
     */
    EList<SCSPatternReference> getPatternreferences();

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see com.zipc.garden.model.scs.SCSPackage#getSCSPattern_Id()
     * @model
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scs.SCSPattern#getId <em>Id</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Pattern Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Value</em>' attribute.
     * @see #setPatternValue(String)
     * @see com.zipc.garden.model.scs.SCSPackage#getSCSPattern_PatternValue()
     * @model
     * @generated
     */
    String getPatternValue();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scs.SCSPattern#getPatternValue <em>Pattern Value</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern Value</em>' attribute.
     * @see #getPatternValue()
     * @generated
     */
    void setPatternValue(String value);

    /**
     * Returns the value of the '<em><b>Csc Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Csc Uuid</em>' attribute.
     * @see #setCscUuid(String)
     * @see com.zipc.garden.model.scs.SCSPackage#getSCSPattern_CscUuid()
     * @model
     * @generated
     */
    String getCscUuid();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scs.SCSPattern#getCscUuid <em>Csc Uuid</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Csc Uuid</em>' attribute.
     * @see #getCscUuid()
     * @generated
     */
    void setCscUuid(String value);

    /**
     * Returns the value of the '<em><b>Csc File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Csc File Name</em>' attribute.
     * @see #setCscFileName(String)
     * @see com.zipc.garden.model.scs.SCSPackage#getSCSPattern_CscFileName()
     * @model
     * @generated
     */
    String getCscFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scs.SCSPattern#getCscFileName <em>Csc File Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Csc File Name</em>' attribute.
     * @see #getCscFileName()
     * @generated
     */
    void setCscFileName(String value);

} // SCSPattern
