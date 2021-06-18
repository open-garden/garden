/**
 */
package com.zipc.garden.model.tp;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.tp.TPPackage
 * @generated
 */
public interface TPFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    TPFactory eINSTANCE = com.zipc.garden.model.tp.impl.TPFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    TPRoot createTPRoot();

    /**
     * Returns a new object of class '<em>TSD Pattern</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>TSD Pattern</em>'.
     * @generated
     */
    TPTSDPattern createTPTSDPattern();

    /**
     * Returns a new object of class '<em>Element</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Element</em>'.
     * @generated
     */
    TPElement createTPElement();

    /**
     * Returns a new object of class '<em>Setting</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Setting</em>'.
     * @generated
     */
    TPSetting createTPSetting();

    /**
     * Returns a new object of class '<em>Pattern Element</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Pattern Element</em>'.
     * @generated
     */
    TPPatternElement createTPPatternElement();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    TPPackage getTPPackage();

} // TPFactory
