/**
 */
package com.zipc.garden.model.arc;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.arc.ARCPackage
 * @generated
 */
public interface ARCFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    ARCFactory eINSTANCE = com.zipc.garden.model.arc.impl.ARCFactoryImpl.init();

    /**
     * Returns a new object of class '<em>State</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>State</em>'.
     * @generated
     */
    ARCState createARCState();

    /**
     * Returns a new object of class '<em>Point</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Point</em>'.
     * @generated
     */
    ARCPoint createARCPoint();

    /**
     * Returns a new object of class '<em>Line</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Line</em>'.
     * @generated
     */
    ARCLine createARCLine();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    ARCRoot createARCRoot();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ARCPackage getARCPackage();

} // ARCFactory
