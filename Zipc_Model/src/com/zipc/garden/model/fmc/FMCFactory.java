/**
 */
package com.zipc.garden.model.fmc;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.fmc.FMCPackage
 * @generated
 */
public interface FMCFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    FMCFactory eINSTANCE = com.zipc.garden.model.fmc.impl.FMCFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    FMCRoot createFMCRoot();

    /**
     * Returns a new object of class '<em>Constraint</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Constraint</em>'.
     * @generated
     */
    FMCConstraint createFMCConstraint();

    /**
     * Returns a new object of class '<em>Node Path</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Node Path</em>'.
     * @generated
     */
    FMCNodePath createFMCNodePath();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    FMCPackage getFMCPackage();

} // FMCFactory
