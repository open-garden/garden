/**
 */
package com.zipc.garden.model.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Reference</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.Reference#getRefid <em>Refid</em>}</li>
 * <li>{@link com.zipc.garden.model.core.Reference#getHash <em>Hash</em>}</li>
 * <li>{@link com.zipc.garden.model.core.Reference#getRefName <em>Ref Name</em>}</li>
 * <li>{@link com.zipc.garden.model.core.Reference#getRefExtension <em>Ref Extension</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.core.COREPackage#getReference()
 * @model
 * @generated
 */
public interface Reference extends EObject {
    /**
     * Returns the value of the '<em><b>Refid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Refid</em>' attribute.
     * @see #setRefid(String)
     * @see com.zipc.garden.model.core.COREPackage#getReference_Refid()
     * @model
     * @generated
     */
    String getRefid();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.Reference#getRefid <em>Refid</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Refid</em>' attribute.
     * @see #getRefid()
     * @generated
     */
    void setRefid(String value);

    /**
     * Returns the value of the '<em><b>Hash</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Hash</em>' attribute.
     * @see #setHash(String)
     * @see com.zipc.garden.model.core.COREPackage#getReference_Hash()
     * @model
     * @generated
     */
    String getHash();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.Reference#getHash <em>Hash</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Hash</em>' attribute.
     * @see #getHash()
     * @generated
     */
    void setHash(String value);

    /**
     * Returns the value of the '<em><b>Ref Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Ref Name</em>' attribute.
     * @see #setRefName(String)
     * @see com.zipc.garden.model.core.COREPackage#getReference_RefName()
     * @model
     * @generated
     */
    String getRefName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.Reference#getRefName <em>Ref Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Ref Name</em>' attribute.
     * @see #getRefName()
     * @generated
     */
    void setRefName(String value);

    /**
     * Returns the value of the '<em><b>Ref Extension</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Ref Extension</em>' attribute.
     * @see #setRefExtension(String)
     * @see com.zipc.garden.model.core.COREPackage#getReference_RefExtension()
     * @model
     * @generated
     */
    String getRefExtension();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.Reference#getRefExtension <em>Ref Extension</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Ref Extension</em>' attribute.
     * @see #getRefExtension()
     * @generated
     */
    void setRefExtension(String value);

} // Reference
