/**
 */
package com.zipc.garden.model.spql;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.spql.SPQLPackage
 * @generated
 */
public interface SPQLFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    SPQLFactory eINSTANCE = com.zipc.garden.model.spql.impl.SPQLFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    SPQLRoot createSPQLRoot();

    /**
     * Returns a new object of class '<em>Setting</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Setting</em>'.
     * @generated
     */
    SPQLSetting createSPQLSetting();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    SPQLPackage getSPQLPackage();

} // SPQLFactory
