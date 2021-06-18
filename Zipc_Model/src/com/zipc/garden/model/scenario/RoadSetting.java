/**
 */
package com.zipc.garden.model.scenario;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Road Setting</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.zipc.garden.model.scenario.RoadSetting#getLaneCount <em>Lane Count</em>}</li>
 * </ul>
 *
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getRoadSetting()
 * @model
 * @generated
 */
public interface RoadSetting extends EObject {
    /**
     * Returns the value of the '<em><b>Lane Count</b></em>' attribute.
     * The default value is <code>"3"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Lane Count</em>' attribute.
     * @see #setLaneCount(int)
     * @see com.zipc.garden.model.scenario.ScenarioPackage#getRoadSetting_LaneCount()
     * @model default="3" required="true"
     * @generated
     */
    int getLaneCount();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scenario.RoadSetting#getLaneCount <em>Lane Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Lane Count</em>' attribute.
     * @see #getLaneCount()
     * @generated
     */
    void setLaneCount(int value);

} // RoadSetting
