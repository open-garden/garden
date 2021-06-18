/**
 */
package com.zipc.garden.model.arc.util;

import com.zipc.garden.model.arc.*;

import com.zipc.garden.model.core.AbstractDiagram;
import com.zipc.garden.model.core.AbstractLine;
import com.zipc.garden.model.core.AbstractPoint;
import com.zipc.garden.model.core.AbstractRootElement;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code> method for
 * each class of the model. <!-- end-user-doc -->
 * @see com.zipc.garden.model.arc.ARCPackage
 * @generated
 */
public class ARCAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static ARCPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ARCAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ARCPackage.eINSTANCE;
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
    protected ARCSwitch<Adapter> modelSwitch = new ARCSwitch<Adapter>() {
        @Override
        public Adapter caseARCState(ARCState object) {
            return createARCStateAdapter();
        }

        @Override
        public Adapter caseARCVertex(ARCVertex object) {
            return createARCVertexAdapter();
        }

        @Override
        public Adapter caseARCPoint(ARCPoint object) {
            return createARCPointAdapter();
        }

        @Override
        public Adapter caseARCLine(ARCLine object) {
            return createARCLineAdapter();
        }

        @Override
        public Adapter caseARCAbstractLine(ARCAbstractLine object) {
            return createARCAbstractLineAdapter();
        }

        @Override
        public Adapter caseARCRoot(ARCRoot object) {
            return createARCRootAdapter();
        }

        @Override
        public Adapter caseARCReferenceable(ARCReferenceable object) {
            return createARCReferenceableAdapter();
        }

        @Override
        public Adapter caseAbstractPoint(AbstractPoint object) {
            return createAbstractPointAdapter();
        }

        @Override
        public Adapter caseAbstractLine(AbstractLine object) {
            return createAbstractLineAdapter();
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
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.arc.ARCState <em>State</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.arc.ARCState
     * @generated
     */
    public Adapter createARCStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.arc.ARCVertex <em>Vertex</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.arc.ARCVertex
     * @generated
     */
    public Adapter createARCVertexAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.arc.ARCPoint <em>Point</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.arc.ARCPoint
     * @generated
     */
    public Adapter createARCPointAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.arc.ARCLine <em>Line</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.arc.ARCLine
     * @generated
     */
    public Adapter createARCLineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.arc.ARCAbstractLine <em>Abstract Line</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.arc.ARCAbstractLine
     * @generated
     */
    public Adapter createARCAbstractLineAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.arc.ARCRoot <em>Root</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.arc.ARCRoot
     * @generated
     */
    public Adapter createARCRootAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link com.zipc.garden.model.arc.ARCReferenceable <em>Referenceable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * @return the new adapter.
     * @see com.zipc.garden.model.arc.ARCReferenceable
     * @generated
     */
    public Adapter createARCReferenceableAdapter() {
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
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null. <!--
     * end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // ARCAdapterFactory
