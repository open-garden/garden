/**
 */
package com.zipc.garden.model.cscs;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Pattern</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.cscs.CSCSPattern#getId <em>Id</em>}</li>
 * <li>{@link com.zipc.garden.model.cscs.CSCSPattern#getPatternValue <em>Pattern Value</em>}</li>
 * <li>{@link com.zipc.garden.model.cscs.CSCSPattern#getCsc <em>Csc</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.cscs.CSCSPackage#getCSCSPattern()
 * @model
 * @generated
 */
public interface CSCSPattern extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see com.zipc.garden.model.cscs.CSCSPackage#getCSCSPattern_Id()
     * @model
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.cscs.CSCSPattern#getId <em>Id</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Pattern Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Value</em>' attribute.
     * @see #setPatternValue(String)
     * @see com.zipc.garden.model.cscs.CSCSPackage#getCSCSPattern_PatternValue()
     * @model
     * @generated
     */
    String getPatternValue();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.cscs.CSCSPattern#getPatternValue <em>Pattern Value</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern Value</em>' attribute.
     * @see #getPatternValue()
     * @generated
     */
    void setPatternValue(String value);

    /**
     * Returns the value of the '<em><b>Csc</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Csc</em>' attribute.
     * @see #setCsc(String)
     * @see com.zipc.garden.model.cscs.CSCSPackage#getCSCSPattern_Csc()
     * @model
     * @generated
     */
    String getCsc();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.cscs.CSCSPattern#getCsc <em>Csc</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Csc</em>' attribute.
     * @see #getCsc()
     * @generated
     */
    void setCsc(String value);

} // CSCSPattern
