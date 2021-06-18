/**
 */
package com.zipc.garden.model.arc;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Vertex</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.arc.ARCVertex#getTop <em>Top</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCVertex#getLeft <em>Left</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCVertex#getHeight <em>Height</em>}</li>
 * <li>{@link com.zipc.garden.model.arc.ARCVertex#getWidth <em>Width</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.arc.ARCPackage#getARCVertex()
 * @model abstract="true"
 * @generated
 */
public interface ARCVertex extends ARCReferenceable {
    /**
     * Returns the value of the '<em><b>Top</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Top</em>' attribute.
     * @see #setTop(int)
     * @see com.zipc.garden.model.arc.ARCPackage#getARCVertex_Top()
     * @model
     * @generated
     */
    int getTop();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCVertex#getTop <em>Top</em>}' attribute. <!-- begin-user-doc
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
     * @see com.zipc.garden.model.arc.ARCPackage#getARCVertex_Left()
     * @model
     * @generated
     */
    int getLeft();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCVertex#getLeft <em>Left</em>}' attribute. <!-- begin-user-doc
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
     * @see com.zipc.garden.model.arc.ARCPackage#getARCVertex_Height()
     * @model
     * @generated
     */
    int getHeight();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCVertex#getHeight <em>Height</em>}' attribute. <!--
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
     * @see com.zipc.garden.model.arc.ARCPackage#getARCVertex_Width()
     * @model
     * @generated
     */
    int getWidth();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.arc.ARCVertex#getWidth <em>Width</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(int value);

} // ARCVertex
