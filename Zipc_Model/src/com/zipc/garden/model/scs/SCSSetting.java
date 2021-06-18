/**
 */
package com.zipc.garden.model.scs;

import com.zipc.garden.model.core.AbstractSetting;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Setting</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.scs.SCSSetting#getPatterns <em>Patterns</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.scs.SCSPackage#getSCSSetting()
 * @model
 * @generated
 */
public interface SCSSetting extends AbstractSetting {
    /**
     * Returns the value of the '<em><b>Patterns</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.scs.SCSPattern}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Patterns</em>' containment reference list.
     * @see com.zipc.garden.model.scs.SCSPackage#getSCSSetting_Patterns()
     * @model containment="true"
     * @generated
     */
    EList<SCSPattern> getPatterns();

} // SCSSetting
