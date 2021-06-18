/**
 */
package com.zipc.garden.model.cb.util;

import com.zipc.garden.model.cb.*;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractNode;
import com.zipc.garden.model.core.AbstractRootElement;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code> method for
 * each class of the model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.cb.CBPackage
 * @generated
 */
public class CBAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static CBPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public CBAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = CBPackage.eINSTANCE;
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
    protected CBSwitch<Adapter> modelSwitch = new CBSwitch<Adapter>() {
        @Override
        public Adapter caseCBRoot(CBRoot object) {
            return createCBRootAdapter();
        }

        @Override
        public Adapter caseCBLogic(CBLogic object) {
            return createCBLogicAdapter();
        }

        @Override
        public Adapter caseCBFile(CBFile object) {
            return createCBFileAdapter();
        }

        @Override
        public Adapter caseCBAllCombination(CBAllCombination object) {
            return createCBAllCombinationAdapter();
        }

        @Override
        public Adapter caseCBPairWise(CBPairWise object) {
            return createCBPairWiseAdapter();
        }

        @Override
        public Adapter caseCBLogicType(CBLogicType object) {
            return createCBLogicTypeAdapter();
        }

        @Override
        public Adapter caseAbstractRootElement(AbstractRootElement object) {
            return createAbstractRootElementAdapter();
        }

        @Override
        public Adapter caseAbstractDiagram(AbstractDiagram object) {
            return createAbstractDiagramAdapter();
        }

        @Override
        public Adapter caseAbstractNode(AbstractNode object) {
            return createAbstractNodeAdapter();
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
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.cb.CBRoot <em>Root</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.cb.CBRoot
     * @generated
     */
    public Adapter createCBRootAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.cb.CBLogic <em>Logic</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.cb.CBLogic
     * @generated
     */
    public Adapter createCBLogicAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.cb.CBFile <em>File</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.cb.CBFile
     * @generated
     */
    public Adapter createCBFileAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.cb.CBAllCombination <em>All
     * Combination</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.cb.CBAllCombination
     * @generated
     */
    public Adapter createCBAllCombinationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.cb.CBPairWise <em>Pair Wise</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.cb.CBPairWise
     * @generated
     */
    public Adapter createCBPairWiseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.cb.CBLogicType <em>Logic Type</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.cb.CBLogicType
     * @generated
     */
    public Adapter createCBLogicTypeAdapter() {
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
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.core.AbstractDiagram <em>Abstract
     * Diagram</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.core.AbstractDiagram
     * @generated
     */
    public Adapter createAbstractDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.core.AbstractNode <em>Abstract Node</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.core.AbstractNode
     * @generated
     */
    public Adapter createAbstractNodeAdapter() {
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

} // CBAdapterFactory
