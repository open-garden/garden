/**
 */
package com.zipc.garden.model.bp.util;

import com.zipc.garden.model.bp.*;

import com.zipc.garden.model.core.AbstractRootElement;

import com.zipc.garden.model.core.AbstractSearchCondition;
import com.zipc.garden.model.core.AbstractSetting;
import com.zipc.garden.model.core.SettingInterface;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code> method for
 * each class of the model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.bp.BPPackage
 * @generated
 */
public class BPAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static BPPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public BPAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = BPPackage.eINSTANCE;
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
    protected BPSwitch<Adapter> modelSwitch = new BPSwitch<Adapter>() {
        @Override
        public Adapter caseBPRoot(BPRoot object) {
            return createBPRootAdapter();
        }

        @Override
        public Adapter caseBPStateMachine(BPStateMachine object) {
            return createBPStateMachineAdapter();
        }

        @Override
        public Adapter caseBPBehaviorPattern(BPBehaviorPattern object) {
            return createBPBehaviorPatternAdapter();
        }

        @Override
        public Adapter caseBPBehavior(BPBehavior object) {
            return createBPBehaviorAdapter();
        }

        @Override
        public Adapter caseBPStep(BPStep object) {
            return createBPStepAdapter();
        }

        @Override
        public Adapter caseBPState(BPState object) {
            return createBPStateAdapter();
        }

        @Override
        public Adapter caseBPEvent(BPEvent object) {
            return createBPEventAdapter();
        }

        @Override
        public Adapter caseBPSpec(BPSpec object) {
            return createBPSpecAdapter();
        }

        @Override
        public Adapter caseBPSpecElement(BPSpecElement object) {
            return createBPSpecElementAdapter();
        }

        @Override
        public Adapter caseBPSetting(BPSetting object) {
            return createBPSettingAdapter();
        }

        @Override
        public Adapter caseAbstractRootElement(AbstractRootElement object) {
            return createAbstractRootElementAdapter();
        }

        @Override
        public Adapter caseAbstractSearchCondition(AbstractSearchCondition object) {
            return createAbstractSearchConditionAdapter();
        }

        @Override
        public Adapter caseSettingInterface(SettingInterface object) {
            return createSettingInterfaceAdapter();
        }

        @Override
        public Adapter caseAbstractSetting(AbstractSetting object) {
            return createAbstractSettingAdapter();
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
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bp.BPRoot <em>Root</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bp.BPRoot
     * @generated
     */
    public Adapter createBPRootAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bp.BPStateMachine <em>State Machine</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bp.BPStateMachine
     * @generated
     */
    public Adapter createBPStateMachineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bp.BPBehaviorPattern <em>Behavior
     * Pattern</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bp.BPBehaviorPattern
     * @generated
     */
    public Adapter createBPBehaviorPatternAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bp.BPBehavior <em>Behavior</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bp.BPBehavior
     * @generated
     */
    public Adapter createBPBehaviorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bp.BPStep <em>Step</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bp.BPStep
     * @generated
     */
    public Adapter createBPStepAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bp.BPState <em>State</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bp.BPState
     * @generated
     */
    public Adapter createBPStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bp.BPEvent <em>Event</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bp.BPEvent
     * @generated
     */
    public Adapter createBPEventAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bp.BPSpec <em>Spec</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bp.BPSpec
     * @generated
     */
    public Adapter createBPSpecAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bp.BPSpecElement <em>Spec Element</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bp.BPSpecElement
     * @generated
     */
    public Adapter createBPSpecElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.bp.BPSetting <em>Setting</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.bp.BPSetting
     * @generated
     */
    public Adapter createBPSettingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.core.AbstractSetting <em>Abstract
     * Setting</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.core.AbstractSetting
     * @generated
     */
    public Adapter createAbstractSettingAdapter() {
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
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.core.AbstractSearchCondition <em>Abstract
     * Search Condition</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.core.AbstractSearchCondition
     * @generated
     */
    public Adapter createAbstractSearchConditionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.core.SettingInterface <em>Setting
     * Interface</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.core.SettingInterface
     * @generated
     */
    public Adapter createSettingInterfaceAdapter() {
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

} // BPAdapterFactory
