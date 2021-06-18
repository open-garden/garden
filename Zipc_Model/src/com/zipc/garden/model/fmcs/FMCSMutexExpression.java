/**
 */
package com.zipc.garden.model.fmcs;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Mutex Expression</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> 相互排他 <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSMutexExpression#getOdElement <em>Od Element</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSMutexExpression()
 * @model
 * @generated
 */
public interface FMCSMutexExpression extends FMCSExpression {
    /**
     * Returns the value of the '<em><b>Od Elements</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fmcs.FMCSODElement}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Od Elements</em>' containment reference list.
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSMutexExpression_OdElements()
     * @model containment="true" required="true"
     * @generated
     */
    EList<FMCSODElement> getOdElements();

} // FMCSMutexExpression
