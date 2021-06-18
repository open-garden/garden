/**
 */
package com.zipc.garden.model.arc;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.arc.ARCState#getFileId <em>File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCState#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCState#getIncomingLine <em>Incoming Line</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCState#getOutgoingLine <em>Outgoing Line</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCState#getEvalPriority <em>Eval Priority</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.arc.ARCPackage#getARCState()
 * @model
 * @generated
 */
public interface ARCState extends ARCVertex {
    /**
     * Returns the value of the '<em><b>File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>File Id</em>' attribute.
     * @see #setFileId(String)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCState_FileId()
     * @model
     * @generated
     */
    String getFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCState#getFileId <em>File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>File Id</em>' attribute.
     * @see #getFileId()
     * @generated
     */
    void setFileId(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCState_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCState#getName <em>Name</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Incoming Line</b></em>' reference list. The list contents are of type
     * {@link com.zipc.garden.model.arc.ARCLine}. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.arc.ARCLine#getTarget <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Incoming Line</em>' reference list.
     * @see com.zipc.garden.model.arc.ARCPackage#getARCState_IncomingLine()
     * @see com.zipc.garden.model.arc.ARCLine#getTarget
     * @model opposite="target"
     * @generated
     */
    EList<ARCLine> getIncomingLine();

    /**
     * Returns the value of the '<em><b>Outgoing Line</b></em>' reference list. The list contents are of type
     * {@link com.zipc.garden.model.arc.ARCLine}. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.arc.ARCLine#getSource <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Outgoing Line</em>' reference list.
     * @see com.zipc.garden.model.arc.ARCPackage#getARCState_OutgoingLine()
     * @see com.zipc.garden.model.arc.ARCLine#getSource
     * @model opposite="source"
     * @generated
     */
    EList<ARCLine> getOutgoingLine();

    /**
     * Returns the value of the '<em><b>Eval Priority</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> ステートマシンの評価順．小さいほど，評価順が早い． <!-- end-model-doc -->
     * @return the value of the '<em>Eval Priority</em>' attribute.
     * @see #setEvalPriority(int)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCState_EvalPriority()
     * @model required="true"
     * @generated
     */
    int getEvalPriority();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCState#getEvalPriority <em>Eval Priority</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Eval Priority</em>' attribute.
     * @see #getEvalPriority()
     * @generated
     */
    void setEvalPriority(int value);

} // ARCState
