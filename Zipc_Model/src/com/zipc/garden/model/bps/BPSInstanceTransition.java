/**
 */
package com.zipc.garden.model.bps;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Instance Transition</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceTransition#getPriority <em>Priority</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceTransition#getTrigger <em>Trigger</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceTransition#getEvent <em>Event</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceTransition#getCondition <em>Condition</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceTransition#getAction <em>Action</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceTransition#getSource <em>Source</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSInstanceTransition#getTarget <em>Target</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceTransition()
 * @model
 * @generated
 */
public interface BPSInstanceTransition extends BPSInstanceElement, BPSEnablement {
    /**
     * Returns the value of the '<em><b>Priority</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Priority</em>' attribute.
     * @see #setPriority(int)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceTransition_Priority()
     * @model
     * @generated
     */
    int getPriority();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getPriority <em>Priority</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Priority</em>' attribute.
     * @see #getPriority()
     * @generated
     */
    void setPriority(int value);

    /**
     * Returns the value of the '<em><b>Trigger</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Trigger</em>' attribute.
     * @see #setTrigger(String)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceTransition_Trigger()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getTrigger();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getTrigger <em>Trigger</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Trigger</em>' attribute.
     * @see #getTrigger()
     * @generated
     */
    void setTrigger(String value);

    /**
     * Returns the value of the '<em><b>Event</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Event</em>' attribute.
     * @see #setEvent(String)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceTransition_Event()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getEvent();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getEvent <em>Event</em>}' attribute. <!--
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
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceTransition_Condition()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getCondition();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getCondition <em>Condition</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition</em>' attribute.
     * @see #getCondition()
     * @generated
     */
    void setCondition(String value);

    /**
     * Returns the value of the '<em><b>Action</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Action</em>' attribute.
     * @see #setAction(String)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceTransition_Action()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getAction();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getAction <em>Action</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Action</em>' attribute.
     * @see #getAction()
     * @generated
     */
    void setAction(String value);

    /**
     * Returns the value of the '<em><b>Source</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(BPSInstanceState)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceTransition_Source()
     * @model
     * @generated
     */
    BPSInstanceState getSource();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getSource <em>Source</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(BPSInstanceState value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(BPSInstanceState)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstanceTransition_Target()
     * @model
     * @generated
     */
    BPSInstanceState getTarget();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSInstanceTransition#getTarget <em>Target</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(BPSInstanceState value);

} // BPSInstanceTransition
