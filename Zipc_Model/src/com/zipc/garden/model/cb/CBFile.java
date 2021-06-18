/**
 */
package com.zipc.garden.model.cb;

import com.zipc.garden.model.core.AbstractNode;
import com.zipc.garden.model.core.AbstractRootElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>File</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.cb.CBFile#getUuid <em>Uuid</em>}</li>
 * <li>{@link com.zipc.garden.model.cb.CBFile#getPattern <em>Pattern</em>}</li>
 * <li>{@link com.zipc.garden.model.cb.CBFile#getAbstractRoot <em>Abstract Root</em>}</li>
 * <li>{@link com.zipc.garden.model.cb.CBFile#getHash <em>Hash</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.cb.CBPackage#getCBFile()
 * @model
 * @generated
 */
public interface CBFile extends AbstractNode {
    /**
     * Returns the value of the '<em><b>Uuid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Uuid</em>' attribute.
     * @see #setUuid(String)
     * @see com.zipc.garden.model.cb.CBPackage#getCBFile_Uuid()
     * @model
     * @generated
     */
    String getUuid();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.cb.CBFile#getUuid <em>Uuid</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Uuid</em>' attribute.
     * @see #getUuid()
     * @generated
     */
    void setUuid(String value);

    /**
     * Returns the value of the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Pattern</em>' attribute.
     * @see #setPattern(String)
     * @see com.zipc.garden.model.cb.CBPackage#getCBFile_Pattern()
     * @model required="true"
     * @generated
     */
    String getPattern();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.cb.CBFile#getPattern <em>Pattern</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Pattern</em>' attribute.
     * @see #getPattern()
     * @generated
     */
    void setPattern(String value);

    /**
     * Returns the value of the '<em><b>Abstract Root</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Abstract Root</em>' containment reference.
     * @see #setAbstractRoot(AbstractRootElement)
     * @see com.zipc.garden.model.cb.CBPackage#getCBFile_AbstractRoot()
     * @model containment="true" required="true"
     * @generated
     */
    AbstractRootElement getAbstractRoot();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.cb.CBFile#getAbstractRoot <em>Abstract Root</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Abstract Root</em>' containment reference.
     * @see #getAbstractRoot()
     * @generated
     */
    void setAbstractRoot(AbstractRootElement value);

    /**
     * Returns the value of the '<em><b>Hash</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Hash</em>' attribute.
     * @see #setHash(String)
     * @see com.zipc.garden.model.cb.CBPackage#getCBFile_Hash()
     * @model
     * @generated
     */
    String getHash();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.cb.CBFile#getHash <em>Hash</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Hash</em>' attribute.
     * @see #getHash()
     * @generated
     */
    void setHash(String value);

} // CBFile
