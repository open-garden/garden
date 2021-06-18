/**
 */
package com.zipc.garden.model.spql;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Setting</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.spql.SPQLSetting#getUuid <em>Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.spql.SPQLSetting#getQuery <em>Query</em>}</li>
 * <li>{@link com.zipc.garden.model.spql.SPQLSetting#getResult <em>Result</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.spql.SPQLPackage#getSPQLSetting()
 * @model
 * @generated
 */
public interface SPQLSetting extends EObject {
    /**
     * Returns the value of the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Uuid</em>' attribute.
     * @see #setUuid(String)
     * @see com.zipc.garden.model.spql.SPQLPackage#getSPQLSetting_Uuid()
     * @model
     * @generated
     */
    String getUuid();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.spql.SPQLSetting#getUuid <em>Uuid</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Uuid</em>' attribute.
     * @see #getUuid()
     * @generated
     */
    void setUuid(String value);

    /**
     * Returns the value of the '<em><b>Query</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Query</em>' attribute.
     * @see #setQuery(String)
     * @see com.zipc.garden.model.spql.SPQLPackage#getSPQLSetting_Query()
     * @model
     * @generated
     */
    String getQuery();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.spql.SPQLSetting#getQuery <em>Query</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Query</em>' attribute.
     * @see #getQuery()
     * @generated
     */
    void setQuery(String value);

    /**
     * Returns the value of the '<em><b>Result</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Result</em>' attribute.
     * @see #setResult(String)
     * @see com.zipc.garden.model.spql.SPQLPackage#getSPQLSetting_Result()
     * @model
     * @generated
     */
    String getResult();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.spql.SPQLSetting#getResult <em>Result</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Result</em>' attribute.
     * @see #getResult()
     * @generated
     */
    void setResult(String value);

} // SPQLSetting
