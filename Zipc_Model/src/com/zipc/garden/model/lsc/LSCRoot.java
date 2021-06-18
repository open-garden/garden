/**
 */
package com.zipc.garden.model.lsc;

import com.zipc.garden.model.core.AbstractRootElement;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.lsc.LSCRoot#getScenarios <em>Scenarios</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.lsc.LSCPackage#getLSCRoot()
 * @model
 * @generated
 */
public interface LSCRoot extends AbstractRootElement {

    /**
     * Returns the value of the '<em><b>Scenarios</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.lsc.LSCScenario}. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * ロジカルシナリオのリスト <!-- end-model-doc -->
     * @return the value of the '<em>Scenarios</em>' containment reference list.
     * @see com.zipc.garden.model.lsc.LSCPackage#getLSCRoot_Scenarios()
     * @model containment="true"
     * @generated
     */
    EList<LSCScenario> getScenarios();
} // LSCRoot
