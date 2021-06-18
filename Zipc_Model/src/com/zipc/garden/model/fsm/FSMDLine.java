/**
 */
package com.zipc.garden.model.fsm;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>DLine</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fsm.FSMDLine#getSource <em>Source</em>}</li>
 * <li>{@link com.zipc.garden.model.fsm.FSMDLine#getTarget <em>Target</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDLine()
 * @model
 * @generated
 */
public interface FSMDLine extends FSMDAbstractLine {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.fsm.FSMDVertex#getOutgoingLine <em>Outgoing Line</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(FSMDVertex)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDLine_Source()
     * @see com.zipc.garden.model.fsm.FSMDVertex#getOutgoingLine
     * @model opposite="outgoingLine"
     * @generated
     */
    FSMDVertex getSource();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDLine#getSource <em>Source</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(FSMDVertex value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.fsm.FSMDVertex#getIncomingLine <em>Incoming Line</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(FSMDVertex)
     * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDLine_Target()
     * @see com.zipc.garden.model.fsm.FSMDVertex#getIncomingLine
     * @model opposite="incomingLine"
     * @generated
     */
    FSMDVertex getTarget();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.fsm.FSMDLine#getTarget <em>Target</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(FSMDVertex value);

} // FSMDLine
