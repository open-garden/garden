/**
 */
package com.zipc.garden.model.project;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.project.GPModelPackage
 * @generated
 */
public interface GPModelFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    GPModelFactory eINSTANCE = com.zipc.garden.model.project.impl.GPModelFactoryImpl.init();

    /**
     * Returns a new object of class '<em>GP Resource</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>GP Resource</em>'.
     * @generated
     */
    GPResource createGPResource();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    GPModelPackage getGPModelPackage();

} // GPModelFactory
