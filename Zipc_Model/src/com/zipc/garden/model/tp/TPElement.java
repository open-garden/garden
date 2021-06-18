/**
 */
package com.zipc.garden.model.tp;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Element</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> A Test Scenario Domain element <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tp.TPElement#getFullPath <em>Full Path</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.TPElement#getSimplePath <em>Simple Path</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.tp.TPPackage#getTPElement()
 * @model
 * @generated
 */
public interface TPElement extends EObject {
    /**
     * Returns the value of the '<em><b>Full Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> ルートのfmファイルのルート要素から辿って得られるパス <!-- end-model-doc -->
     * @return the value of the '<em>Full Path</em>' attribute.
     * @see #setFullPath(String)
     * @see com.zipc.garden.model.tp.TPPackage#getTPElement_FullPath()
     * @model required="true"
     * @generated
     */
    String getFullPath();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tp.TPElement#getFullPath <em>Full Path</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Full Path</em>' attribute.
     * @see #getFullPath()
     * @generated
     */
    void setFullPath(String value);

    /**
     * Returns the value of the '<em><b>Simple Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> 全体で重複しないように得られる最小の親要素と連結して得られるパス <!-- end-model-doc -->
     * @return the value of the '<em>Simple Path</em>' attribute.
     * @see #setSimplePath(String)
     * @see com.zipc.garden.model.tp.TPPackage#getTPElement_SimplePath()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getSimplePath();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tp.TPElement#getSimplePath <em>Simple Path</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Simple Path</em>' attribute.
     * @see #getSimplePath()
     * @generated
     */
    void setSimplePath(String value);

} // TPElement
