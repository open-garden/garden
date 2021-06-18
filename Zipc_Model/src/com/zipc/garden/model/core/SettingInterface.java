/**
 */
package com.zipc.garden.model.core;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Setting Interface</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.SettingInterface#getSettings <em>Settings</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.core.COREPackage#getSettingInterface()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SettingInterface extends EObject {
    /**
     * Returns the value of the '<em><b>Settings</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.core.AbstractSetting}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Settings</em>' containment reference list.
     * @see com.zipc.garden.model.core.COREPackage#getSettingInterface_Settings()
     * @model containment="true"
     * @generated
     */
    EList<AbstractSetting> getSettings();

} // SettingInterface
