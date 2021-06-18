/**
 */
package com.zipc.garden.model.tps;

import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.fmc.FMCRoot;
import com.zipc.garden.model.tc.TCNode;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tps.TPSRoot#getNWiseCombination <em>NWise Combination</em>}</li>
 * <li>{@link com.zipc.garden.model.tps.TPSRoot#getRootNodes <em>Root Nodes</em>}</li>
 * <li>{@link com.zipc.garden.model.tps.TPSRoot#getFmcRoot <em>Fmc Root</em>}</li>
 * <li>{@link com.zipc.garden.model.tps.TPSRoot#getPatternElements <em>Pattern Elements</em>}</li>
 * <li>{@link com.zipc.garden.model.tps.TPSRoot#getNodeVariables <em>Node Variables</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.tps.TPSPackage#getTPSRoot()
 * @model
 * @generated
 */
public interface TPSRoot extends AbstractRootElement {
    /**
     * Returns the value of the '<em><b>NWise Combination</b></em>' attribute. The default value is <code>"1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>NWise Combination</em>' attribute.
     * @see #setNWiseCombination(int)
     * @see com.zipc.garden.model.tps.TPSPackage#getTPSRoot_NWiseCombination()
     * @model default="1"
     * @generated
     */
    int getNWiseCombination();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tps.TPSRoot#getNWiseCombination <em>NWise Combination</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>NWise Combination</em>' attribute.
     * @see #getNWiseCombination()
     * @generated
     */
    void setNWiseCombination(int value);

    /**
     * Returns the value of the '<em><b>Root Nodes</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.tc.TCNode}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Root Nodes</em>' containment reference list.
     * @see com.zipc.garden.model.tps.TPSPackage#getTPSRoot_RootNodes()
     * @model containment="true"
     * @generated
     */
    EList<TCNode> getRootNodes();

    /**
     * Returns the value of the '<em><b>Fmc Root</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Fmc Root</em>' containment reference.
     * @see #setFmcRoot(FMCRoot)
     * @see com.zipc.garden.model.tps.TPSPackage#getTPSRoot_FmcRoot()
     * @model containment="true"
     * @generated
     */
    FMCRoot getFmcRoot();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tps.TPSRoot#getFmcRoot <em>Fmc Root</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Fmc Root</em>' containment reference.
     * @see #getFmcRoot()
     * @generated
     */
    void setFmcRoot(FMCRoot value);

    /**
     * Returns the value of the '<em><b>Pattern Elements</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.tps.TPSPatternElement}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Elements</em>' containment reference list.
     * @see com.zipc.garden.model.tps.TPSPackage#getTPSRoot_PatternElements()
     * @model containment="true"
     * @generated
     */
    EList<TPSPatternElement> getPatternElements();

    /**
     * Returns the value of the '<em><b>Node Variables</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.tps.NodeVariable}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Node Variables</em>' containment reference list.
     * @see com.zipc.garden.model.tps.TPSPackage#getTPSRoot_NodeVariables()
     * @model containment="true"
     * @generated
     */
    EList<NodeVariable> getNodeVariables();

} // TPSRoot
