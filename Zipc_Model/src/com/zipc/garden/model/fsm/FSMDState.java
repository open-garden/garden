/**
 */
package com.zipc.garden.model.fsm;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>DState</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.FSMDState#getRef <em>Ref</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDState#getRefName <em>Ref Name</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDState#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDState#getEntry <em>Entry</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDState#getExit <em>Exit</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDState#getDo <em>Do</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDState#getRegions <em>Regions</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDState#getType <em>Type</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDState#getIncomingTransition <em>Incoming Transition</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDState#getOutgoingTransition <em>Outgoing Transition</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDState#getRefuuid <em>Refuuid</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState()
 * @model
 * @generated
 */
public interface FSMDState extends FSMDVertex {
    /**
     * Returns the value of the '<em><b>Ref</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Ref</em>' attribute.
     * @see #setRef(long)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState_Ref()
     * @model
     * @generated
     */
    long getRef();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDState#getRef <em>Ref</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Ref</em>' attribute.
     * @see #getRef()
     * @generated
     */
    void setRef(long value);

    /**
     * Returns the value of the '<em><b>Ref Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Ref Name</em>' attribute.
     * @see #setRefName(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState_RefName()
     * @model
     * @generated
     */
    String getRefName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDState#getRefName <em>Ref Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Ref Name</em>' attribute.
     * @see #getRefName()
     * @generated
     */
    void setRefName(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDState#getName <em>Name</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Entry</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Entry</em>' attribute.
     * @see #setEntry(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState_Entry()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getEntry();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDState#getEntry <em>Entry</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Entry</em>' attribute.
     * @see #getEntry()
     * @generated
     */
    void setEntry(String value);

    /**
     * Returns the value of the '<em><b>Exit</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Exit</em>' attribute.
     * @see #setExit(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState_Exit()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getExit();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDState#getExit <em>Exit</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Exit</em>' attribute.
     * @see #getExit()
     * @generated
     */
    void setExit(String value);

    /**
     * Returns the value of the '<em><b>Do</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Do</em>' attribute.
     * @see #setDo(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState_Do()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getDo();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDState#getDo <em>Do</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Do</em>' attribute.
     * @see #getDo()
     * @generated
     */
    void setDo(String value);

    /**
     * Returns the value of the '<em><b>Regions</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDRegion}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Regions</em>' containment reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState_Regions()
     * @model containment="true"
     * @generated
     */
    EList<FSMDRegion> getRegions();

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute. The default value is <code>"NONE"</code>. The literals are
     * from the enumeration {@link com.zipc.garden.model.fsm.FSMDPseudoStateType}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see com.zipc.garden.model.fsm.FSMDPseudoStateType
     * @see #setType(FSMDPseudoStateType)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState_Type()
     * @model default="NONE"
     * @generated
     */
    FSMDPseudoStateType getType();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDState#getType <em>Type</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see com.zipc.garden.model.fsm.FSMDPseudoStateType
     * @see #getType()
     * @generated
     */
    void setType(FSMDPseudoStateType value);

    /**
     * Returns the value of the '<em><b>Incoming Transition</b></em>' reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDTransition}. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.fsm.FSMDTransition#getTarget <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Incoming Transition</em>' reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState_IncomingTransition()
     * @see com.zipc.garden.model.fsm.FSMDTransition#getTarget
     * @model opposite="target"
     * @generated
     */
    EList<FSMDTransition> getIncomingTransition();

    /**
     * Returns the value of the '<em><b>Outgoing Transition</b></em>' reference list. The list contents are of type
     * {@link com.zipc.garden.model.fsm.FSMDTransition}. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.fsm.FSMDTransition#getSource <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Outgoing Transition</em>' reference list.
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState_OutgoingTransition()
     * @see com.zipc.garden.model.fsm.FSMDTransition#getSource
     * @model opposite="source"
     * @generated
     */
    EList<FSMDTransition> getOutgoingTransition();

    /**
     * Returns the value of the '<em><b>Refuuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Refuuid</em>' attribute.
     * @see #setRefuuid(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDState_Refuuid()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getRefuuid();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDState#getRefuuid <em>Refuuid</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Refuuid</em>' attribute.
     * @see #getRefuuid()
     * @generated
     */
    void setRefuuid(String value);

} // FSMDState
