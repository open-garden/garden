/**
 */
package com.zipc.garden.model.project;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>GP Resource</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.project.GPResource#getParent <em>Parent</em>}</li>
 * <li>{@link com.zipc.garden.model.project.GPResource#getChildren <em>Children</em>}</li>
 * <li>{@link com.zipc.garden.model.project.GPResource#isDirectory <em>Directory</em>}</li>
 * <li>{@link com.zipc.garden.model.project.GPResource#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.project.GPResource#getId <em>Id</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.project.GPModelPackage#getGPResource()
 * @model
 * @generated
 */
public interface GPResource extends EObject {
    /**
     * Returns the value of the '<em><b>Parent</b></em>' reference. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.project.GPResource#getChildren <em>Children</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the value of the '<em>Parent</em>' reference.
     * @see #setParent(GPResource)
     * @see com.zipc.garden.model.project.GPModelPackage#getGPResource_Parent()
     * @see com.zipc.garden.model.project.GPResource#getChildren
     * @model opposite="children"
     * @generated
     */
    GPResource getParent();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.project.GPResource#getParent <em>Parent</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' reference.
     * @see #getParent()
     * @generated
     */
    void setParent(GPResource value);

    /**
     * Returns the value of the '<em><b>Children</b></em>' reference list. The list contents are of type
     * {@link com.zipc.garden.model.project.GPResource}. It is bidirectional and its opposite is
     * '{@link com.zipc.garden.model.project.GPResource#getParent <em>Parent</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @return the value of the '<em>Children</em>' reference list.
     * @see com.zipc.garden.model.project.GPModelPackage#getGPResource_Children()
     * @see com.zipc.garden.model.project.GPResource#getParent
     * @model opposite="parent"
     * @generated
     */
    EList<GPResource> getChildren();

    /**
     * Returns the value of the '<em><b>Directory</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Directory</em>' attribute.
     * @see #setDirectory(boolean)
     * @see com.zipc.garden.model.project.GPModelPackage#getGPResource_Directory()
     * @model
     * @generated
     */
    boolean isDirectory();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.project.GPResource#isDirectory <em>Directory</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Directory</em>' attribute.
     * @see #isDirectory()
     * @generated
     */
    void setDirectory(boolean value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see com.zipc.garden.model.project.GPModelPackage#getGPResource_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.project.GPResource#getName <em>Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(long)
     * @see com.zipc.garden.model.project.GPModelPackage#getGPResource_Id()
     * @model
     * @generated
     */
    long getId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.project.GPResource#getId <em>Id</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(long value);

} // GPResource
