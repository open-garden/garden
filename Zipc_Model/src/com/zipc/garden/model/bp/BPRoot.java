/**
 */
package com.zipc.garden.model.bp;

import com.zipc.garden.model.core.AbstractRootElement;
import com.zipc.garden.model.core.AbstractSearchCondition;
import com.zipc.garden.model.core.SettingInterface;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bp.BPRoot#getAll <em>All</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPRoot#getBegin <em>Begin</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPRoot#getEnd <em>End</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPRoot#getStatus <em>Status</em>}</li>
 * <li>{@link com.zipc.garden.model.bp.BPRoot#getMessage <em>Message</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bp.BPPackage#getBPRoot()
 * @model
 * @generated
 */
public interface BPRoot extends AbstractRootElement, AbstractSearchCondition, SettingInterface {

    /**
     * Returns the value of the '<em><b>All</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>All</em>' attribute.
     * @see #setAll(int)
     * @see com.zipc.garden.model.bp.BPPackage#getBPRoot_All()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getAll();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPRoot#getAll <em>All</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>All</em>' attribute.
     * @see #getAll()
     * @generated
     */
    void setAll(int value);

    /**
     * Returns the value of the '<em><b>Begin</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Begin</em>' attribute.
     * @see #setBegin(int)
     * @see com.zipc.garden.model.bp.BPPackage#getBPRoot_Begin()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getBegin();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPRoot#getBegin <em>Begin</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Begin</em>' attribute.
     * @see #getBegin()
     * @generated
     */
    void setBegin(int value);

    /**
     * Returns the value of the '<em><b>End</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>End</em>' attribute.
     * @see #setEnd(int)
     * @see com.zipc.garden.model.bp.BPPackage#getBPRoot_End()
     * @model dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getEnd();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPRoot#getEnd <em>End</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>End</em>' attribute.
     * @see #getEnd()
     * @generated
     */
    void setEnd(int value);

    /**
     * Returns the value of the '<em><b>Status</b></em>' attribute. The default value is <code>"-1"</code>. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @return the value of the '<em>Status</em>' attribute.
     * @see #setStatus(int)
     * @see com.zipc.garden.model.bp.BPPackage#getBPRoot_Status()
     * @model default="-1" dataType="org.eclipse.emf.ecore.xml.type.Int"
     * @generated
     */
    int getStatus();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPRoot#getStatus <em>Status</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Status</em>' attribute.
     * @see #getStatus()
     * @generated
     */
    void setStatus(int value);

    /**
     * Returns the value of the '<em><b>Message</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Message</em>' attribute.
     * @see #setMessage(String)
     * @see com.zipc.garden.model.bp.BPPackage#getBPRoot_Message()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getMessage();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bp.BPRoot#getMessage <em>Message</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Message</em>' attribute.
     * @see #getMessage()
     * @generated
     */
    void setMessage(String value);
} // BPRoot
