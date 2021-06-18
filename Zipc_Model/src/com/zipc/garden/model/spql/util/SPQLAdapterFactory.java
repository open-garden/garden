/**
 */
package com.zipc.garden.model.spql.util;

import com.zipc.garden.model.core.AbstractRootElement;

import com.zipc.garden.model.spql.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code> method for
 * each class of the model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.spql.SPQLPackage
 * @generated
 */
public class SPQLAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static SPQLPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public SPQLAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = SPQLPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This implementation
     * returns <code>true</code> if the object is either the model's package or is an instance object of the model. <!--
     * end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected SPQLSwitch<Adapter> modelSwitch = new SPQLSwitch<Adapter>() {
        @Override
        public Adapter caseSPQLRoot(SPQLRoot object) {
            return createSPQLRootAdapter();
        }

        @Override
        public Adapter caseSPQLSetting(SPQLSetting object) {
            return createSPQLSettingAdapter();
        }

        @Override
        public Adapter caseAbstractRootElement(AbstractRootElement object) {
            return createAbstractRootElementAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.spql.SPQLRoot <em>Root</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.spql.SPQLRoot
     * @generated
     */
    public Adapter createSPQLRootAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.spql.SPQLSetting <em>Setting</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.spql.SPQLSetting
     * @generated
     */
    public Adapter createSPQLSettingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.core.AbstractRootElement <em>Abstract Root
     * Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.core.AbstractRootElement
     * @generated
     */
    public Adapter createAbstractRootElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null. <!--
     * end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // SPQLAdapterFactory
