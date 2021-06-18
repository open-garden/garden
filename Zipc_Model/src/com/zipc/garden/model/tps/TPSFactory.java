/**
 */
package com.zipc.garden.model.tps;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.tps.TPSPackage
 * @generated
 */
public interface TPSFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    TPSFactory eINSTANCE = com.zipc.garden.model.tps.impl.TPSFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    TPSRoot createTPSRoot();

    /**
     * Returns a new object of class '<em>Pattern Element</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Pattern Element</em>'.
     * @generated
     */
    TPSPatternElement createTPSPatternElement();

    /**
     * Returns a new object of class '<em>Node Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Node Variable</em>'.
     * @generated
     */
    NodeVariable createNodeVariable();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    TPSPackage getTPSPackage();

} // TPSFactory
