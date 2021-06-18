/**
 */
package com.zipc.garden.model.fsm.util;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractLine;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.AbstractRootElement;

import com.zipc.garden.model.fsm.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code> method for
 * each class of the model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.fsm.FSMPackage
 * @generated
 */
public class FSMAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static FSMPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public FSMAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = FSMPackage.eINSTANCE;
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
    protected FSMSwitch<Adapter> modelSwitch = new FSMSwitch<Adapter>() {
        @Override
        public Adapter caseFSMDStateMachine(FSMDStateMachine object) {
            return createFSMDStateMachineAdapter();
        }

        @Override
        public Adapter caseFSMDVertex(FSMDVertex object) {
            return createFSMDVertexAdapter();
        }

        @Override
        public Adapter caseFSMDProperty(FSMDProperty object) {
            return createFSMDPropertyAdapter();
        }

        @Override
        public Adapter caseFSMDState(FSMDState object) {
            return createFSMDStateAdapter();
        }

        @Override
        public Adapter caseFSMDTransition(FSMDTransition object) {
            return createFSMDTransitionAdapter();
        }

        @Override
        public Adapter caseFSMDReferenceable(FSMDReferenceable object) {
            return createFSMDReferenceableAdapter();
        }

        @Override
        public Adapter caseFSMDMemo(FSMDMemo object) {
            return createFSMDMemoAdapter();
        }

        @Override
        public Adapter caseFSMDRegion(FSMDRegion object) {
            return createFSMDRegionAdapter();
        }

        @Override
        public Adapter caseFSMDLine(FSMDLine object) {
            return createFSMDLineAdapter();
        }

        @Override
        public Adapter caseFSMDEvent(FSMDEvent object) {
            return createFSMDEventAdapter();
        }

        @Override
        public Adapter caseFSMDAction(FSMDAction object) {
            return createFSMDActionAdapter();
        }

        @Override
        public Adapter caseFSMDAbstractLine(FSMDAbstractLine object) {
            return createFSMDAbstractLineAdapter();
        }

        @Override
        public Adapter caseFSMDPoint(FSMDPoint object) {
            return createFSMDPointAdapter();
        }

        @Override
        public Adapter caseFSMDVariable(FSMDVariable object) {
            return createFSMDVariableAdapter();
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
        public Adapter caseAbstractLine(AbstractLine object) {
            return createAbstractLineAdapter();
        }

        @Override
        public Adapter caseAbstractPoint(AbstractPoint object) {
            return createAbstractPointAdapter();
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
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDStateMachine <em>DState
     * Machine</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDStateMachine
     * @generated
     */
    public Adapter createFSMDStateMachineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDVertex <em>DVertex</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDVertex
     * @generated
     */
    public Adapter createFSMDVertexAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDProperty <em>DProperty</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDProperty
     * @generated
     */
    public Adapter createFSMDPropertyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDState <em>DState</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDState
     * @generated
     */
    public Adapter createFSMDStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDTransition <em>DTransition</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDTransition
     * @generated
     */
    public Adapter createFSMDTransitionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDReferenceable
     * <em>DReferenceable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDReferenceable
     * @generated
     */
    public Adapter createFSMDReferenceableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDMemo <em>DMemo</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDMemo
     * @generated
     */
    public Adapter createFSMDMemoAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDRegion <em>DRegion</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDRegion
     * @generated
     */
    public Adapter createFSMDRegionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDLine <em>DLine</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDLine
     * @generated
     */
    public Adapter createFSMDLineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDEvent <em>DEvent</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDEvent
     * @generated
     */
    public Adapter createFSMDEventAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDAction <em>DAction</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDAction
     * @generated
     */
    public Adapter createFSMDActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDAbstractLine <em>DAbstract
     * Line</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDAbstractLine
     * @generated
     */
    public Adapter createFSMDAbstractLineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDPoint <em>DPoint</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDPoint
     * @generated
     */
    public Adapter createFSMDPointAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.fsm.FSMDVariable <em>DVariable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.fsm.FSMDVariable
     * @generated
     */
    public Adapter createFSMDVariableAdapter() {
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
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.core.AbstractLine <em>Abstract Line</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.core.AbstractLine
     * @generated
     */
    public Adapter createAbstractLineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.core.AbstractPoint <em>Abstract Point</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.core.AbstractPoint
     * @generated
     */
    public Adapter createAbstractPointAdapter() {
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

} // FSMAdapterFactory
