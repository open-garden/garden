/**
 */
package com.zipc.garden.model.bps.util;

import com.zipc.garden.model.bps.*;

import com.zipc.garden.model.core.AbstractRootElement;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code> method for
 * each class of the model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.bps.BPSPackage
 * @generated
 */
public class BPSAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static BPSPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPSAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = BPSPackage.eINSTANCE;
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
    protected BPSSwitch<Adapter> modelSwitch = new BPSSwitch<Adapter>() {
        @Override
        public Adapter caseBPSRoot(BPSRoot object) {
            return createBPSRootAdapter();
        }

        @Override
        public Adapter caseBPSOption(BPSOption object) {
            return createBPSOptionAdapter();
        }

        @Override
        public Adapter caseBPSStateCombinationOption(BPSStateCombinationOption object) {
            return createBPSStateCombinationOptionAdapter();
        }

        @Override
        public Adapter caseBPSNSwitchOption(BPSNSwitchOption object) {
            return createBPSNSwitchOptionAdapter();
        }

        @Override
        public Adapter caseBPSPathCombinationOption(BPSPathCombinationOption object) {
            return createBPSPathCombinationOptionAdapter();
        }

        @Override
        public Adapter caseBPSEnablement(BPSEnablement object) {
            return createBPSEnablementAdapter();
        }

        @Override
        public Adapter caseBPSVariable(BPSVariable object) {
            return createBPSVariableAdapter();
        }

        @Override
        public Adapter caseBPSDataflow(BPSDataflow object) {
            return createBPSDataflowAdapter();
        }

        @Override
        public Adapter caseBPSInstanceElement(BPSInstanceElement object) {
            return createBPSInstanceElementAdapter();
        }

        @Override
        public Adapter caseBPSInstanceArc(BPSInstanceArc object) {
            return createBPSInstanceArcAdapter();
        }

        @Override
        public Adapter caseBPSInstanceStateMachine(BPSInstanceStateMachine object) {
            return createBPSInstanceStateMachineAdapter();
        }

        @Override
        public Adapter caseBPSInstanceState(BPSInstanceState object) {
            return createBPSInstanceStateAdapter();
        }

        @Override
        public Adapter caseBPSInstanceTransition(BPSInstanceTransition object) {
            return createBPSInstanceTransitionAdapter();
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
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSRoot <em>Root</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSRoot
     * @generated
     */
    public Adapter createBPSRootAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSOption <em>Option</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSOption
     * @generated
     */
    public Adapter createBPSOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSStateCombinationOption <em>State
     * Combination Option</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSStateCombinationOption
     * @generated
     */
    public Adapter createBPSStateCombinationOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSNSwitchOption <em>NSwitch
     * Option</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSNSwitchOption
     * @generated
     */
    public Adapter createBPSNSwitchOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSPathCombinationOption <em>Path
     * Combination Option</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSPathCombinationOption
     * @generated
     */
    public Adapter createBPSPathCombinationOptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSEnablement <em>Enablement</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSEnablement
     * @generated
     */
    public Adapter createBPSEnablementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSVariable <em>Variable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSVariable
     * @generated
     */
    public Adapter createBPSVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSDataflow <em>Dataflow</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSDataflow
     * @generated
     */
    public Adapter createBPSDataflowAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSInstanceElement <em>Instance
     * Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSInstanceElement
     * @generated
     */
    public Adapter createBPSInstanceElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSInstanceArc <em>Instance Arc</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSInstanceArc
     * @generated
     */
    public Adapter createBPSInstanceArcAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSInstanceStateMachine <em>Instance State
     * Machine</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSInstanceStateMachine
     * @generated
     */
    public Adapter createBPSInstanceStateMachineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSInstanceState <em>Instance
     * State</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSInstanceState
     * @generated
     */
    public Adapter createBPSInstanceStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bps.BPSInstanceTransition <em>Instance
     * Transition</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bps.BPSInstanceTransition
     * @generated
     */
    public Adapter createBPSInstanceTransitionAdapter() {
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

} // BPSAdapterFactory
