/**
 */
package com.zipc.garden.model.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Setting</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.core.AbstractSetting#getUuid <em>Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractSetting#getSettingHash <em>Setting Hash</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractSetting#getPatternNos <em>Pattern Nos</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractSetting#getBegin <em>Begin</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractSetting#getEnd <em>End</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractSetting#getCount <em>Count</em>}</li>
 * <li>{@link com.zipc.garden.model.core.AbstractSetting#getAbstractRoot <em>Abstract Root</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.core.COREPackage#getAbstractSetting()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface AbstractSetting extends EObject {
    /**
     * Returns the value of the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Uuid</em>' attribute.
     * @see #setUuid(String)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractSetting_Uuid()
     * @model
     * @generated
     */
    String getUuid();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractSetting#getUuid <em>Uuid</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Uuid</em>' attribute.
     * @see #getUuid()
     * @generated
     */
    void setUuid(String value);

    /**
     * Returns the value of the '<em><b>Setting Hash</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Setting Hash</em>' attribute.
     * @see #setSettingHash(String)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractSetting_SettingHash()
     * @model
     * @generated
     */
    String getSettingHash();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractSetting#getSettingHash <em>Setting Hash</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Setting Hash</em>' attribute.
     * @see #getSettingHash()
     * @generated
     */
    void setSettingHash(String value);

    /**
     * Returns the value of the '<em><b>Pattern Nos</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Nos</em>' attribute.
     * @see #setPatternNos(String)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractSetting_PatternNos()
     * @model required="true"
     * @generated
     */
    String getPatternNos();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractSetting#getPatternNos <em>Pattern Nos</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern Nos</em>' attribute.
     * @see #getPatternNos()
     * @generated
     */
    void setPatternNos(String value);

    /**
     * Returns the value of the '<em><b>Begin</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Begin</em>' attribute.
     * @see #setBegin(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractSetting_Begin()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getBegin();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractSetting#getBegin <em>Begin</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Begin</em>' attribute.
     * @see #getBegin()
     * @generated
     */
    void setBegin(int value);

    /**
     * Returns the value of the '<em><b>End</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>End</em>' attribute.
     * @see #setEnd(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractSetting_End()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getEnd();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractSetting#getEnd <em>End</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>End</em>' attribute.
     * @see #getEnd()
     * @generated
     */
    void setEnd(int value);

    /**
     * Returns the value of the '<em><b>Count</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Count</em>' attribute.
     * @see #setCount(int)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractSetting_Count()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getCount();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractSetting#getCount <em>Count</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Count</em>' attribute.
     * @see #getCount()
     * @generated
     */
    void setCount(int value);

    /**
     * Returns the value of the '<em><b>Abstract Root</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Abstract Root</em>' containment reference.
     * @see #setAbstractRoot(AbstractRootElement)
     * @see com.zipc.garden.model.core.COREPackage#getAbstractSetting_AbstractRoot()
     * @model containment="true" required="true"
     * @generated
     */
    AbstractRootElement getAbstractRoot();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.core.AbstractSetting#getAbstractRoot <em>Abstract Root</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Abstract Root</em>' containment reference.
     * @see #getAbstractRoot()
     * @generated
     */
    void setAbstractRoot(AbstractRootElement value);

} // AbstractSetting
