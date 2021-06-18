/**
 */
package com.zipc.garden.model.scenario;

import com.zipc.garden.model.core.AbstractRootElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.ScenarioRoot#getRoadSetting <em>Road Setting</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.ScenarioRoot#getActorSetting <em>Actor Setting</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.ScenarioRoot#getPhaseSetting <em>Phase Setting</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getScenarioRoot()
 * @model
 * @generated
 */
public interface ScenarioRoot extends AbstractRootElement {
    /**
     * Returns the value of the '<em><b>Road Setting</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Road Setting</em>' containment reference.
     * @see #setRoadSetting(RoadSetting)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getScenarioRoot_RoadSetting()
     * @model containment="true" required="true"
     * @generated
     */
    RoadSetting getRoadSetting();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.ScenarioRoot#getRoadSetting <em>Road Setting</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Road Setting</em>' containment reference.
     * @see #getRoadSetting()
     * @generated
     */
    void setRoadSetting(RoadSetting value);

    /**
     * Returns the value of the '<em><b>Actor Setting</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Actor Setting</em>' containment reference.
     * @see #setActorSetting(ActorSetting)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getScenarioRoot_ActorSetting()
     * @model containment="true" required="true"
     * @generated
     */
    ActorSetting getActorSetting();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.ScenarioRoot#getActorSetting <em>Actor Setting</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Actor Setting</em>' containment reference.
     * @see #getActorSetting()
     * @generated
     */
    void setActorSetting(ActorSetting value);

    /**
     * Returns the value of the '<em><b>Phase Setting</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Phase Setting</em>' containment reference.
     * @see #setPhaseSetting(PhaseSetting)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getScenarioRoot_PhaseSetting()
     * @model containment="true" required="true"
     * @generated
     */
    PhaseSetting getPhaseSetting();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.ScenarioRoot#getPhaseSetting <em>Phase Setting</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Phase Setting</em>' containment reference.
     * @see #getPhaseSetting()
     * @generated
     */
    void setPhaseSetting(PhaseSetting value);

} // ScenarioRoot
