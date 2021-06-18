/**
 */
package com.zipc.garden.model.fsm;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>DTransition</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.FSMDTransition#getTrigger <em>Trigger</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDTransition#getEvent <em>Event</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDTransition#getCondition <em>Condition</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDTransition#getAction <em>Action</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDTransition#getX <em>X</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDTransition#getY <em>Y</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDTransition#getTarget <em>Target</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDTransition#getSource <em>Source</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDTransition#getPriority <em>Priority</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDTransition()
 * @model
 * @generated
 */
public interface FSMDTransition extends FSMDReferenceable, FSMDAbstractLine {
    /**
     * Returns the value of the '<em><b>Trigger</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Trigger</em>' attribute.
     * @see #setTrigger(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDTransition_Trigger()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getTrigger();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDTransition#getTrigger <em>Trigger</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Trigger</em>' attribute.
     * @see #getTrigger()
     * @generated
     */
    void setTrigger(String value);

    /**
     * Returns the value of the '<em><b>Event</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Event</em>' attribute.
     * @see #setEvent(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDTransition_Event()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getEvent();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDTransition#getEvent <em>Event</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Event</em>' attribute.
     * @see #getEvent()
     * @generated
     */
    void setEvent(String value);

    /**
     * Returns the value of the '<em><b>Condition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Condition</em>' attribute.
     * @see #setCondition(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDTransition_Condition()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getCondition();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDTransition#getCondition <em>Condition</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition</em>' attribute.
     * @see #getCondition()
     * @generated
     */
    void setCondition(String value);

    /**
     * Returns the value of the '<em><b>Action</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Action</em>' attribute.
     * @see #setAction(String)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDTransition_Action()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getAction();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDTransition#getAction <em>Action</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Action</em>' attribute.
     * @see #getAction()
     * @generated
     */
    void setAction(String value);

    /**
     * Returns the value of the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(int)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDTransition_X()
     * @model
     * @generated
     */
    int getX();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDTransition#getX <em>X</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
    void setX(int value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(int)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDTransition_Y()
     * @model
     * @generated
     */
    int getY();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDTransition#getY <em>Y</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(int value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.fsm.FSMDState#getIncomingTransition <em>Incoming Transition</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(FSMDState)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDTransition_Target()
     * @see com.zipc.garden.model.fsm.FSMDState#getIncomingTransition
     * @model opposite="incomingTransition"
     * @generated
     */
    FSMDState getTarget();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDTransition#getTarget <em>Target</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(FSMDState value);

    /**
     * Returns the value of the '<em><b>Source</b></em>' reference. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.fsm.FSMDState#getOutgoingTransition <em>Outgoing Transition</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(FSMDState)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDTransition_Source()
     * @see com.zipc.garden.model.fsm.FSMDState#getOutgoingTransition
     * @model opposite="outgoingTransition"
     * @generated
     */
    FSMDState getSource();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDTransition#getSource <em>Source</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(FSMDState value);

    /**
     * Returns the value of the '<em><b>Priority</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Priority</em>' attribute.
     * @see #setPriority(int)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDTransition_Priority()
     * @model
     * @generated
     */
    int getPriority();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDTransition#getPriority <em>Priority</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Priority</em>' attribute.
     * @see #getPriority()
     * @generated
     */
    void setPriority(int value);

} // FSMDTransition
