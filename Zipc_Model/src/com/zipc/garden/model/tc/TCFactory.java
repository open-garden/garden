/**
 */
package com.zipc.garden.model.tc;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.tc.TCPackage
 * @generated
 */
public interface TCFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    TCFactory eINSTANCE = com.zipc.garden.model.tc.impl.TCFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    TCRoot createTCRoot();

    /**
     * Returns a new object of class '<em>Node</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Node</em>'.
     * @generated
     */
    TCNode createTCNode();

    /**
     * Returns a new object of class '<em>Property</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Property</em>'.
     * @generated
     */
    TCProperty createTCProperty();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    TCPackage getTCPackage();

} // TCFactory
