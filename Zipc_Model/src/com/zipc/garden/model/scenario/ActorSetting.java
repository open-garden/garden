/**
 */
package com.zipc.garden.model.scenario;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Actor Setting</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.ActorSetting#getEgo <em>Ego</em>}</li>
 *   <li>{@link com.zipc.garden.model.scenario.ActorSetting#getOthers <em>Others</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getActorSetting()
 * @model
 * @generated
 */
public interface ActorSetting extends EObject {
    /**
     * Returns the value of the '<em><b>Ego</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ego</em>' containment reference.
     * @see #setEgo(EgoVehicle)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getActorSetting_Ego()
     * @model containment="true" required="true"
     * @generated
     */
    EgoVehicle getEgo();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.ActorSetting#getEgo <em>Ego</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ego</em>' containment reference.
     * @see #getEgo()
     * @generated
     */
    void setEgo(EgoVehicle value);

    /**
     * Returns the value of the '<em><b>Others</b></em>' containment reference list.
     * The list contents are of type {@link com.zipc.garden.model.scenario.Actor}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Others</em>' containment reference list.
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getActorSetting_Others()
     * @model containment="true"
     * @generated
     */
    EList<Actor> getOthers();

} // ActorSetting
