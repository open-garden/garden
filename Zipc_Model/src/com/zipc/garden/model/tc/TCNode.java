/**
 */
package com.zipc.garden.model.tc;

import com.zipc.garden.model.fm.ChildType;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tc.TCNode#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCNode#isChecked <em>Checked</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCNode#getChildren <em>Children</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCNode#getChildType <em>Child Type</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCNode#getState <em>State</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCNode#isInherited <em>Inherited</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCNode#isOptional <em>Optional</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCNode#getMin <em>Min</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCNode#getMax <em>Max</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCNode#getNWiseCombination <em>NWise Combination</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCNode#isTemporary <em>Temporary</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.TCNode#getProperties <em>Properties</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.tc.TCPackage#getTCNode()
 * @model
 * @generated
 */
public interface TCNode extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_Name()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCNode#getName <em>Name</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Checked</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Checked</em>' attribute.
     * @see #setChecked(boolean)
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_Checked()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     * @generated
     */
    boolean isChecked();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCNode#isChecked <em>Checked</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Checked</em>' attribute.
     * @see #isChecked()
     * @generated
     */
    void setChecked(boolean value);

    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.tc.TCNode}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_Children()
     * @model containment="true"
     * @generated
     */
    EList<TCNode> getChildren();

    /**
     * Returns the value of the '<em><b>Child Type</b></em>' attribute. The literals are from the enumeration
     * {@link com.zipc.garden.model.fm.ChildType}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Child Type</em>' attribute.
     * @see com.zipc.garden.model.fm.ChildType
     * @see #setChildType(ChildType)
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_ChildType()
     * @model
     * @generated
     */
    ChildType getChildType();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCNode#getChildType <em>Child Type</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Child Type</em>' attribute.
     * @see com.zipc.garden.model.fm.ChildType
     * @see #getChildType()
     * @generated
     */
    void setChildType(ChildType value);

    /**
     * Returns the value of the '<em><b>State</b></em>' attribute. The default value is <code>"UNCHANGED"</code>. The literals
     * are from the enumeration {@link com.zipc.garden.model.tc.TCNodeState}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>State</em>' attribute.
     * @see com.zipc.garden.model.tc.TCNodeState
     * @see #setState(TCNodeState)
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_State()
     * @model default="UNCHANGED"
     * @generated
     */
    TCNodeState getState();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCNode#getState <em>State</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>State</em>' attribute.
     * @see com.zipc.garden.model.tc.TCNodeState
     * @see #getState()
     * @generated
     */
    void setState(TCNodeState value);

    /**
     * Returns the value of the '<em><b>Inherited</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Inherited</em>' attribute.
     * @see #setInherited(boolean)
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_Inherited()
     * @model
     * @generated
     */
    boolean isInherited();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCNode#isInherited <em>Inherited</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Inherited</em>' attribute.
     * @see #isInherited()
     * @generated
     */
    void setInherited(boolean value);

    /**
     * Returns the value of the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Optional</em>' attribute.
     * @see #setOptional(boolean)
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_Optional()
     * @model
     * @generated
     */
    boolean isOptional();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCNode#isOptional <em>Optional</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Optional</em>' attribute.
     * @see #isOptional()
     * @generated
     */
    void setOptional(boolean value);

    /**
     * Returns the value of the '<em><b>Min</b></em>' attribute. The default value is <code>"-1"</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Min</em>' attribute.
     * @see #setMin(int)
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_Min()
     * @model default="-1"
     * @generated
     */
    int getMin();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCNode#getMin <em>Min</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Min</em>' attribute.
     * @see #getMin()
     * @generated
     */
    void setMin(int value);

    /**
     * Returns the value of the '<em><b>Max</b></em>' attribute. The default value is <code>"-1"</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Max</em>' attribute.
     * @see #setMax(int)
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_Max()
     * @model default="-1"
     * @generated
     */
    int getMax();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCNode#getMax <em>Max</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max</em>' attribute.
     * @see #getMax()
     * @generated
     */
    void setMax(int value);

    /**
     * Returns the value of the '<em><b>NWise Combination</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>NWise Combination</em>' attribute.
     * @see #setNWiseCombination(int)
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_NWiseCombination()
     * @model default="-1"
     * @generated
     */
    int getNWiseCombination();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCNode#getNWiseCombination <em>NWise Combination</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>NWise Combination</em>' attribute.
     * @see #getNWiseCombination()
     * @generated
     */
    void setNWiseCombination(int value);

    /**
     * Returns the value of the '<em><b>Temporary</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Temporary</em>' attribute.
     * @see #setTemporary(boolean)
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_Temporary()
     * @model
     * @generated
     */
    boolean isTemporary();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tc.TCNode#isTemporary <em>Temporary</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Temporary</em>' attribute.
     * @see #isTemporary()
     * @generated
     */
    void setTemporary(boolean value);

    /**
     * Returns the value of the '<em><b>Properties</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.tc.TCProperty}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' containment reference list.
     * @see com.zipc.garden.model.tc.TCPackage#getTCNode_Properties()
     * @model containment="true"
     * @generated
     */
    EList<TCProperty> getProperties();

} // TCNode
