/**
 */
package com.zipc.garden.model.bps;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Enablement</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.BPSEnablement#isEnabled <em>Enabled</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSEnablement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface BPSEnablement extends EObject {
    /**
     * Returns the value of the '<em><b>Enabled</b></em>' attribute. The default value is <code>"true"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Enabled</em>' attribute.
     * @see #setEnabled(boolean)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSEnablement_Enabled()
     * @model default="true"
     * @generated
     */
    boolean isEnabled();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSEnablement#isEnabled <em>Enabled</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Enabled</em>' attribute.
     * @see #isEnabled()
     * @generated
     */
    void setEnabled(boolean value);

} // BPSEnablement
