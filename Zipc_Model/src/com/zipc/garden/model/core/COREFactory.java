/**
 */
package com.zipc.garden.model.core;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.core.COREPackage
 * @generated
 */
public interface COREFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    COREFactory eINSTANCE = com.zipc.garden.model.core.impl.COREFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Reference</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Reference</em>'.
     * @generated
     */
    Reference createReference();

    /**
     * Returns a new object of class '<em>Memo</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return a new object of class '<em>Memo</em>'.
     * @generated
     */
    Memo createMemo();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    COREPackage getCOREPackage();

} // COREFactory
