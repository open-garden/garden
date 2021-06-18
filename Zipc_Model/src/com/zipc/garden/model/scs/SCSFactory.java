/**
 */
package com.zipc.garden.model.scs;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.scs.SCSPackage
 * @generated
 */
public interface SCSFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    SCSFactory eINSTANCE = com.zipc.garden.model.scs.impl.SCSFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    SCSRoot createSCSRoot();

    /**
     * Returns a new object of class '<em>Pattern</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Pattern</em>'.
     * @generated
     */
    SCSPattern createSCSPattern();

    /**
     * Returns a new object of class '<em>Pattern Reference</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Pattern Reference</em>'.
     * @generated
     */
    SCSPatternReference createSCSPatternReference();

    /**
     * Returns a new object of class '<em>Setting</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Setting</em>'.
     * @generated
     */
    SCSSetting createSCSSetting();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    SCSPackage getSCSPackage();

} // SCSFactory
