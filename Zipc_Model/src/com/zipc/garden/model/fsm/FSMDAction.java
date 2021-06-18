/**
 */
package com.zipc.garden.model.fsm;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>DAction</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.FSMDAction#getText <em>Text</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDAction()
 * @model
 * @generated
 */
public interface FSMDAction extends EObject {
    /**
     * Returns the value of the '<em><b>Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Text</em>' attribute.
     * @see #setText(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDAction_Text()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getText();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDAction#getText <em>Text</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Text</em>' attribute.
     * @see #getText()
     * @generated
     */
    void setText(String value);

} // FSMDAction
