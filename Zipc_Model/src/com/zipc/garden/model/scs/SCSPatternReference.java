/**
 */
package com.zipc.garden.model.scs;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Pattern Reference</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.scs.SCSPatternReference#getFileId <em>File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scs.SCSPatternReference#getPatternId <em>Pattern Id</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.scs.SCSPackage#getSCSPatternReference()
 * @model
 * @generated
 */
public interface SCSPatternReference extends EObject {
    /**
     * Returns the value of the '<em><b>File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>File Id</em>' attribute.
     * @see #setFileId(long)
     * @see com.zipc.garden.model.scs.SCSPackage#getSCSPatternReference_FileId()
     * @model
     * @generated
     */
    long getFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scs.SCSPatternReference#getFileId <em>File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>File Id</em>' attribute.
     * @see #getFileId()
     * @generated
     */
    void setFileId(long value);

    /**
     * Returns the value of the '<em><b>Pattern Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Id</em>' attribute.
     * @see #setPatternId(long)
     * @see com.zipc.garden.model.scs.SCSPackage#getSCSPatternReference_PatternId()
     * @model
     * @generated
     */
    long getPatternId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scs.SCSPatternReference#getPatternId <em>Pattern Id</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern Id</em>' attribute.
     * @see #getPatternId()
     * @generated
     */
    void setPatternId(long value);

} // SCSPatternReference
