/**
 */
package com.zipc.garden.model.fm;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.fm.FMPackage
 * @generated
 */
public interface FMFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    FMFactory eINSTANCE = com.zipc.garden.model.fm.impl.FMFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    FMRoot createFMRoot();

    /**
     * Returns a new object of class '<em>Node</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Node</em>'.
     * @generated
     */
    FMNode createFMNode();

    /**
     * Returns a new object of class '<em>Property</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Property</em>'.
     * @generated
     */
    FMProperty createFMProperty();

    /**
     * Returns a new object of class '<em>Constraint</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Constraint</em>'.
     * @generated
     */
    FMConstraint createFMConstraint();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    FMPackage getFMPackage();

} // FMFactory
