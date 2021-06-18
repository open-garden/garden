/**
 */
package com.zipc.garden.model.fm;

import com.zipc.garden.model.core.AbstractStyle;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getChildren <em>Children</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getTop <em>Top</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getLeft <em>Left</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getHeight <em>Height</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getWidth <em>Width</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getRef <em>Ref</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getRefName <em>Ref Name</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getRefInfo <em>Ref Info</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getChildType <em>Child Type</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getProperties <em>Properties</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getMin <em>Min</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getMax <em>Max</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getX <em>X</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getY <em>Y</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#getRefuuid <em>Refuuid</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.FMNode#isOptional <em>Optional</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fm.FMPackage#getFMNode()
 * @model
 * @generated
 */
public interface FMNode extends AbstractStyle {
    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fm.FMNode}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Children()
     * @model containment="true"
     * @generated
     */
    EList<FMNode> getChildren();

    /**
     * Returns the value of the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Top</em>' attribute.
     * @see #setTop(int)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Top()
     * @model
     * @generated
     */
    int getTop();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getTop <em>Top</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Top</em>' attribute.
     * @see #getTop()
     * @generated
     */
    void setTop(int value);

    /**
     * Returns the value of the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Left</em>' attribute.
     * @see #setLeft(int)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Left()
     * @model
     * @generated
     */
    int getLeft();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getLeft <em>Left</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Left</em>' attribute.
     * @see #getLeft()
     * @generated
     */
    void setLeft(int value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(int)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Height()
     * @model
     * @generated
     */
    int getHeight();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getHeight <em>Height</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(int value);

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(int)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Width()
     * @model
     * @generated
     */
    int getWidth();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getWidth <em>Width</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(int value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getName <em>Name</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Ref</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Ref</em>' attribute.
     * @see #setRef(long)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Ref()
     * @model
     * @generated
     */
    long getRef();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getRef <em>Ref</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ref</em>' attribute.
     * @see #getRef()
     * @generated
     */
    void setRef(long value);

    /**
     * Returns the value of the '<em><b>Ref Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Ref Name</em>' attribute.
     * @see #setRefName(String)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_RefName()
     * @model
     * @generated
     */
    String getRefName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getRefName <em>Ref Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Ref Name</em>' attribute.
     * @see #getRefName()
     * @generated
     */
    void setRefName(String value);

    /**
     * Returns the value of the '<em><b>Ref Info</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Ref Info</em>' attribute.
     * @see #setRefInfo(String)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_RefInfo()
     * @model
     * @generated
     */
    String getRefInfo();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getRefInfo <em>Ref Info</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Ref Info</em>' attribute.
     * @see #getRefInfo()
     * @generated
     */
    void setRefInfo(String value);

    /**
     * Returns the value of the '<em><b>Child Type</b></em>' attribute. The literals are from the enumeration
     * {@link com.zipc.garden.model.fm.ChildType}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Child Type</em>' attribute.
     * @see com.zipc.garden.model.fm.ChildType
     * @see #setChildType(ChildType)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_ChildType()
     * @model
     * @generated
     */
    ChildType getChildType();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getChildType <em>Child Type</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Child Type</em>' attribute.
     * @see com.zipc.garden.model.fm.ChildType
     * @see #getChildType()
     * @generated
     */
    void setChildType(ChildType value);

    /**
     * Returns the value of the '<em><b>Properties</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fm.FMProperty}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' containment reference list.
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Properties()
     * @model containment="true"
     * @generated
     */
    EList<FMProperty> getProperties();

    /**
     * Returns the value of the '<em><b>Min</b></em>' attribute. The default value is <code>"-1"</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Min</em>' attribute.
     * @see #setMin(int)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Min()
     * @model default="-1"
     * @generated
     */
    int getMin();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getMin <em>Min</em>}' attribute. <!-- begin-user-doc -->
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
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Max()
     * @model default="-1"
     * @generated
     */
    int getMax();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getMax <em>Max</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Max</em>' attribute.
     * @see #getMax()
     * @generated
     */
    void setMax(int value);

    /**
     * Returns the value of the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(int)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_X()
     * @model
     * @generated
     */
    int getX();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getX <em>X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
    void setX(int value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(int)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Y()
     * @model
     * @generated
     */
    int getY();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getY <em>Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(int value);

    /**
     * Returns the value of the '<em><b>Refuuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Refuuid</em>' attribute.
     * @see #setRefuuid(String)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Refuuid()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getRefuuid();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#getRefuuid <em>Refuuid</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Refuuid</em>' attribute.
     * @see #getRefuuid()
     * @generated
     */
    void setRefuuid(String value);

    /**
     * Returns the value of the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Optional</em>' attribute.
     * @see #setOptional(boolean)
     * @see com.zipc.garden.model.fm.FMPackage#getFMNode_Optional()
     * @model
     * @generated
     */
    boolean isOptional();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fm.FMNode#isOptional <em>Optional</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Optional</em>' attribute.
     * @see #isOptional()
     * @generated
     */
    void setOptional(boolean value);

} // FMNode
