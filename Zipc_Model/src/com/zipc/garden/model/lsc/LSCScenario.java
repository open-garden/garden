/**
 */
package com.zipc.garden.model.lsc;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Scenario</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> ロジカルシナリオ <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.lsc.LSCScenario#getTpId <em>Tp Id</em>}</li>
 * <li>{@link com.zipc.garden.model.lsc.LSCScenario#getBpId <em>Bp Id</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.lsc.LSCPackage#getLSCScenario()
 * @model
 * @generated
 */
public interface LSCScenario extends EObject {
    /**
     * Returns the value of the '<em><b>Tp Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> TSDTPPatternのID <!-- end-model-doc -->
     * @return the value of the '<em>Tp Id</em>' attribute.
     * @see #setTpId(String)
     * @see com.zipc.garden.model.lsc.LSCPackage#getLSCScenario_TpId()
     * @model required="true"
     * @generated
     */
    String getTpId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.lsc.LSCScenario#getTpId <em>Tp Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Tp Id</em>' attribute.
     * @see #getTpId()
     * @generated
     */
    void setTpId(String value);

    /**
     * Returns the value of the '<em><b>Bp Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> BPBehaviorPatternのID <!-- end-model-doc -->
     * @return the value of the '<em>Bp Id</em>' attribute.
     * @see #setBpId(String)
     * @see com.zipc.garden.model.lsc.LSCPackage#getLSCScenario_BpId()
     * @model required="true"
     * @generated
     */
    String getBpId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.lsc.LSCScenario#getBpId <em>Bp Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Bp Id</em>' attribute.
     * @see #getBpId()
     * @generated
     */
    void setBpId(String value);

} // LSCScenario
