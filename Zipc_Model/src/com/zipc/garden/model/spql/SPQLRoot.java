/**
 */
package com.zipc.garden.model.spql;

import com.zipc.garden.model.core.AbstractRootElement;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.spql.SPQLRoot#getUuid <em>Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.spql.SPQLRoot#getSettings <em>Settings</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.spql.SPQLPackage#getSPQLRoot()
 * @model
 * @generated
 */
public interface SPQLRoot extends AbstractRootElement {
    /**
     * Returns the value of the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Uuid</em>' attribute.
     * @see #setUuid(String)
     * @see com.zipc.garden.model.spql.SPQLPackage#getSPQLRoot_Uuid()
     * @model
     * @generated
     */
    String getUuid();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.spql.SPQLRoot#getUuid <em>Uuid</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Uuid</em>' attribute.
     * @see #getUuid()
     * @generated
     */
    void setUuid(String value);

    /**
     * Returns the value of the '<em><b>Settings</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.spql.SPQLSetting}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Settings</em>' containment reference list.
     * @see com.zipc.garden.model.spql.SPQLPackage#getSPQLRoot_Settings()
     * @model containment="true"
     * @generated
     */
    EList<SPQLSetting> getSettings();

} // SPQLRoot
