/**
 */
package com.zipc.garden.model.fsm;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>DVertex</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.FSMDVertex#getTop <em>Top</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDVertex#getLeft <em>Left</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDVertex#getHeight <em>Height</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDVertex#getWidth <em>Width</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDVertex#getProperties <em>Properties</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDVertex#getBackground <em>Background</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDVertex#getForeground <em>Foreground</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDVertex#getIncomingLine <em>Incoming Line</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDVertex#getOutgoingLine <em>Outgoing Line</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDVertex()
 * @model abstract="true"
 * @generated
 */
public interface FSMDVertex extends FSMDReferenceable {
    /**
     * Returns the value of the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Top</em>' attribute.
     * @see #setTop(int)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDVertex_Top()
     * @model
     * @generated
     */
    int getTop();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDVertex#getTop <em>Top</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Top</em>' attribute.
     * @see #getTop()
     * @generated
     */
    void setTop(int value);

    /**
     * Returns the value of the '<em><b>Left</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Left</em>' attribute.
     * @see #setLeft(int)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDVertex_Left()
     * @model
     * @generated
     */
    int getLeft();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDVertex#getLeft <em>Left</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Left</em>' attribute.
     * @see #getLeft()
     * @generated
     */
    void setLeft(int value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(int)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDVertex_Height()
     * @model
     * @generated
     */
    int getHeight();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDVertex#getHeight <em>Height</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(int value);

    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(int)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDVertex_Width()
     * @model
     * @generated
     */
    int getWidth();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDVertex#getWidth <em>Width</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(int value);

    /**
     * Returns the value of the '<em><b>Properties</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDProperty}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Properties</em>' containment reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDVertex_Properties()
     * @model containment="true"
     * @generated
     */
    EList<FSMDProperty> getProperties();

    /**
     * Returns the value of the '<em><b>Background</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Background</em>' attribute.
     * @see #setBackground(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDVertex_Background()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getBackground();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDVertex#getBackground <em>Background</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Background</em>' attribute.
     * @see #getBackground()
     * @generated
     */
    void setBackground(String value);

    /**
     * Returns the value of the '<em><b>Foreground</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Foreground</em>' attribute.
     * @see #setForeground(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDVertex_Foreground()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getForeground();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDVertex#getForeground <em>Foreground</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Foreground</em>' attribute.
     * @see #getForeground()
     * @generated
     */
    void setForeground(String value);

    /**
     * Returns the value of the '<em><b>Incoming Line</b></em>' reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDLine}. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.fsm.FSMDLine#getTarget <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Incoming Line</em>' reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDVertex_IncomingLine()
     * @see com.zipc.garden.model.fsm.FSMDLine#getTarget
     * @model opposite="target"
     * @generated
     */
    EList<FSMDLine> getIncomingLine();

    /**
     * Returns the value of the '<em><b>Outgoing Line</b></em>' reference. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.fsm.FSMDLine#getSource <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Outgoing Line</em>' reference.
     * @see #setOutgoingLine(FSMDLine)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDVertex_OutgoingLine()
     * @see com.zipc.garden.model.fsm.FSMDLine#getSource
     * @model opposite="source"
     * @generated
     */
    FSMDLine getOutgoingLine();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDVertex#getOutgoingLine <em>Outgoing Line</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Outgoing Line</em>' reference.
     * @see #getOutgoingLine()
     * @generated
     */
    void setOutgoingLine(FSMDLine value);

} // FSMDVertex
