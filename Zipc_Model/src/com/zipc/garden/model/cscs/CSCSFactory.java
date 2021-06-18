/**
 */
package com.zipc.garden.model.cscs;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.cscs.CSCSPackage
 * @generated
 */
public interface CSCSFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    CSCSFactory eINSTANCE = com.zipc.garden.model.cscs.impl.CSCSFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    CSCSRoot createCSCSRoot();

    /**
     * Returns a new object of class '<em>Pattern</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Pattern</em>'.
     * @generated
     */
    CSCSPattern createCSCSPattern();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    CSCSPackage getCSCSPackage();

} // CSCSFactory
