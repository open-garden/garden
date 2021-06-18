/**
 */
package com.zipc.garden.model.tp;

import com.zipc.garden.model.core.AbstractSetting;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Setting</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tp.TPSetting#getHeader <em>Header</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.TPSetting#getTitle <em>Title</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.TPSetting#getElements <em>Elements</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.TPSetting#getPatterns <em>Patterns</em>}</li>
 * <li>{@link com.zipc.garden.model.tp.TPSetting#getPatternElements <em>Pattern Elements</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.tp.TPPackage#getTPSetting()
 * @model
 * @generated
 */
public interface TPSetting extends AbstractSetting {
    /**
     * Returns the value of the '<em><b>Header</b></em>' attribute list. The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Header</em>' attribute list.
     * @see com.zipc.garden.model.tp.TPPackage#getTPSetting_Header()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    EList<String> getHeader();

    /**
     * Returns the value of the '<em><b>Title</b></em>' attribute. The literals are from the enumeration
     * {@link com.zipc.garden.model.tp.PathType}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Title</em>' attribute.
     * @see com.zipc.garden.model.tp.PathType
     * @see #setTitle(PathType)
     * @see com.zipc.garden.model.tp.TPPackage#getTPSetting_Title()
     * @model
     * @generated
     */
    PathType getTitle();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.tp.TPSetting#getTitle <em>Title</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Title</em>' attribute.
     * @see com.zipc.garden.model.tp.PathType
     * @see #getTitle()
     * @generated
     */
    void setTitle(PathType value);

    /**
     * Returns the value of the '<em><b>Elements</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.tp.TPElement}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Elements</em>' containment reference list.
     * @see com.zipc.garden.model.tp.TPPackage#getTPSetting_Elements()
     * @model containment="true"
     * @generated
     */
    EList<TPElement> getElements();

    /**
     * Returns the value of the '<em><b>Patterns</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.tp.TPTSDPattern}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Patterns</em>' containment reference list.
     * @see com.zipc.garden.model.tp.TPPackage#getTPSetting_Patterns()
     * @model containment="true"
     * @generated
     */
    EList<TPTSDPattern> getPatterns();

    /**
     * Returns the value of the '<em><b>Pattern Elements</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.tp.TPPatternElement}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Pattern Elements</em>' containment reference list.
     * @see com.zipc.garden.model.tp.TPPackage#getTPSetting_PatternElements()
     * @model containment="true"
     * @generated
     */
    EList<TPPatternElement> getPatternElements();

} // TPSetting
