/**
 */
package com.zipc.garden.model.arc;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Line</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.arc.ARCLine#getVariableName <em>Variable Name</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCLine#getVariableType <em>Variable Type</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCLine#getX <em>X</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCLine#getTarget <em>Target</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCLine#getSource <em>Source</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCLine#getY <em>Y</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.arc.ARCPackage#getARCLine()
 * @model
 * @generated
 */
public interface ARCLine extends ARCReferenceable, ARCAbstractLine {
    /**
     * Returns the value of the '<em><b>Variable Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Variable Name</em>' attribute.
     * @see #setVariableName(String)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCLine_VariableName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getVariableName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCLine#getVariableName <em>Variable Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Variable Name</em>' attribute.
     * @see #getVariableName()
     * @generated
     */
    void setVariableName(String value);

    /**
     * Returns the value of the '<em><b>Variable Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Variable Type</em>' attribute.
     * @see #setVariableType(String)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCLine_VariableType()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getVariableType();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCLine#getVariableType <em>Variable Type</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Variable Type</em>' attribute.
     * @see #getVariableType()
     * @generated
     */
    void setVariableType(String value);

    /**
     * Returns the value of the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(int)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCLine_X()
     * @model
     * @generated
     */
    int getX();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCLine#getX <em>X</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
    void setX(int value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.arc.ARCState#getIncomingLine <em>Incoming Line</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(ARCState)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCLine_Target()
     * @see com.zipc.garden.model.arc.ARCState#getIncomingLine
     * @model opposite="incomingLine"
     * @generated
     */
    ARCState getTarget();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCLine#getTarget <em>Target</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(ARCState value);

    /**
     * Returns the value of the '<em><b>Source</b></em>' reference. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.arc.ARCState#getOutgoingLine <em>Outgoing Line</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(ARCState)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCLine_Source()
     * @see com.zipc.garden.model.arc.ARCState#getOutgoingLine
     * @model opposite="outgoingLine"
     * @generated
     */
    ARCState getSource();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCLine#getSource <em>Source</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(ARCState value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(int)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCLine_Y()
     * @model
     * @generated
     */
    int getY();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCLine#getY <em>Y</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(int value);

} // ARCLine
