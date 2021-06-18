/**
 */
package com.zipc.garden.model.cb;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.cb.CBPackage
 * @generated
 */
public interface CBFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    CBFactory eINSTANCE = com.zipc.garden.model.cb.impl.CBFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    CBRoot createCBRoot();

    /**
     * Returns a new object of class '<em>Logic</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Logic</em>'.
     * @generated
     */
    CBLogic createCBLogic();

    /**
     * Returns a new object of class '<em>File</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>File</em>'.
     * @generated
     */
    CBFile createCBFile();

    /**
     * Returns a new object of class '<em>All Combination</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>All Combination</em>'.
     * @generated
     */
    CBAllCombination createCBAllCombination();

    /**
     * Returns a new object of class '<em>Pair Wise</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Pair Wise</em>'.
     * @generated
     */
    CBPairWise createCBPairWise();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    CBPackage getCBPackage();

} // CBFactory
