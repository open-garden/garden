/**
 */
package com.zipc.garden.model.lsc;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.lsc.LSCPackage
 * @generated
 */
public interface LSCFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    LSCFactory eINSTANCE = com.zipc.garden.model.lsc.impl.LSCFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    LSCRoot createLSCRoot();

    /**
     * Returns a new object of class '<em>Scenario</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Scenario</em>'.
     * @generated
     */
    LSCScenario createLSCScenario();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    LSCPackage getLSCPackage();

} // LSCFactory
