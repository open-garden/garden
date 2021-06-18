/**
 */
package com.zipc.garden.model.fmcs;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.fmcs.FMCSPackage
 * @generated
 */
public interface FMCSFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    FMCSFactory eINSTANCE = com.zipc.garden.model.fmcs.impl.FMCSFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Root</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Root</em>'.
     * @generated
     */
    FMCSRoot createFMCSRoot();

    /**
     * Returns a new object of class '<em>Constraint</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Constraint</em>'.
     * @generated
     */
    FMCSConstraint createFMCSConstraint();

    /**
     * Returns a new object of class '<em>Implies Expression</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Implies Expression</em>'.
     * @generated
     */
    FMCSImpliesExpression createFMCSImpliesExpression();

    /**
     * Returns a new object of class '<em>Or Expression</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Or Expression</em>'.
     * @generated
     */
    FMCSOrExpression createFMCSOrExpression();

    /**
     * Returns a new object of class '<em>And Expression</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>And Expression</em>'.
     * @generated
     */
    FMCSAndExpression createFMCSAndExpression();

    /**
     * Returns a new object of class '<em>Not Expression</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Not Expression</em>'.
     * @generated
     */
    FMCSNotExpression createFMCSNotExpression();

    /**
     * Returns a new object of class '<em>Select Expression</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Select Expression</em>'.
     * @generated
     */
    FMCSSelectExpression createFMCSSelectExpression();

    /**
     * Returns a new object of class '<em>OD Element</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>OD Element</em>'.
     * @generated
     */
    FMCSODElement createFMCSODElement();

    /**
     * Returns a new object of class '<em>Mutex Expression</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Mutex Expression</em>'.
     * @generated
     */
    FMCSMutexExpression createFMCSMutexExpression();

    /**
     * Returns a new object of class '<em>Removes Expression</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Removes Expression</em>'.
     * @generated
     */
    FMCSRemovesExpression createFMCSRemovesExpression();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    FMCSPackage getFMCSPackage();

} // FMCSFactory
