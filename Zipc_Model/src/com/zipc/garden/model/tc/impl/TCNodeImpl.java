/**
 */
package com.zipc.garden.model.tc.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fm.ChildType;

import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCNodeState;
import com.zipc.garden.model.tc.TCPackage;

import com.zipc.garden.model.tc.TCProperty;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#getName <em>Name</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#isChecked <em>Checked</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#getChildren <em>Children</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#getChildType <em>Child Type</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#getState <em>State</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#isInherited <em>Inherited</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#isOptional <em>Optional</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#getMin <em>Min</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#getMax <em>Max</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#getNWiseCombination <em>NWise Combination</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#isTemporary <em>Temporary</em>}</li>
 * <li>{@link com.zipc.garden.model.tc.impl.TCNodeImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 * @generated
 */
public class TCNodeImpl extends MinimalEObjectImpl.Container implements TCNode {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #isChecked() <em>Checked</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isChecked()
     * @generated
     * @ordered
     */
    protected static final boolean CHECKED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isChecked() <em>Checked</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isChecked()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected boolean checked = CHECKED_EDEFAULT;

    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<TCNode> children;

    /**
     * The default value of the '{@link #getChildType() <em>Child Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getChildType()
     * @generated
     * @ordered
     */
    protected static final ChildType CHILD_TYPE_EDEFAULT = ChildType.AND;

    /**
     * The cached value of the '{@link #getChildType() <em>Child Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getChildType()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected ChildType childType = CHILD_TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getState() <em>State</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getState()
     * @generated
     * @ordered
     */
    protected static final TCNodeState STATE_EDEFAULT = TCNodeState.UNCHANGED;

    /**
     * The cached value of the '{@link #getState() <em>State</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getState()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected TCNodeState state = STATE_EDEFAULT;

    /**
     * The default value of the '{@link #isInherited() <em>Inherited</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #isInherited()
     * @generated
     * @ordered
     */
    protected static final boolean INHERITED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInherited() <em>Inherited</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #isInherited()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected boolean inherited = INHERITED_EDEFAULT;

    /**
     * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected static final boolean OPTIONAL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #isOptional()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected boolean optional = OPTIONAL_EDEFAULT;

    /**
     * The default value of the '{@link #getMin() <em>Min</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMin()
     * @generated
     * @ordered
     */
    protected static final int MIN_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getMin() <em>Min</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMin()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int min = MIN_EDEFAULT;

    /**
     * The default value of the '{@link #getMax() <em>Max</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
    protected static final int MAX_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getMax() <em>Max</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getMax()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int max = MAX_EDEFAULT;

    /**
     * The default value of the '{@link #getNWiseCombination() <em>NWise Combination</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNWiseCombination()
     * @generated
     * @ordered
     */
    protected static final int NWISE_COMBINATION_EDEFAULT = -1;

    /**
     * The cached value of the '{@link #getNWiseCombination() <em>NWise Combination</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNWiseCombination()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected int nWiseCombination = NWISE_COMBINATION_EDEFAULT;

    /**
     * The default value of the '{@link #isTemporary() <em>Temporary</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #isTemporary()
     * @generated
     * @ordered
     */
    protected static final boolean TEMPORARY_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isTemporary() <em>Temporary</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #isTemporary()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected boolean temporary = TEMPORARY_EDEFAULT;

    /**
     * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see #getProperties()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<TCProperty> properties;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected TCNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TCPackage.Literals.TC_NODE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TCPackage.TC_NODE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isChecked() {
        return checked;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setChecked(boolean newChecked) {
        boolean oldChecked = checked;
        checked = newChecked;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TCPackage.TC_NODE__CHECKED, oldChecked, checked));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TCNode> getChildren() {
        if (children == null) {
            children = new EObjectContainmentEList<TCNode>(TCNode.class, this, TCPackage.TC_NODE__CHILDREN);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ChildType getChildType() {
        return childType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setChildType(ChildType newChildType) {
        ChildType oldChildType = childType;
        childType = newChildType == null ? CHILD_TYPE_EDEFAULT : newChildType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TCPackage.TC_NODE__CHILD_TYPE, oldChildType, childType));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TCNodeState getState() {
        return state;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setState(TCNodeState newState) {
        TCNodeState oldState = state;
        state = newState == null ? STATE_EDEFAULT : newState;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TCPackage.TC_NODE__STATE, oldState, state));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isInherited() {
        return inherited;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setInherited(boolean newInherited) {
        boolean oldInherited = inherited;
        inherited = newInherited;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TCPackage.TC_NODE__INHERITED, oldInherited, inherited));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isOptional() {
        return optional;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOptional(boolean newOptional) {
        boolean oldOptional = optional;
        optional = newOptional;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TCPackage.TC_NODE__OPTIONAL, oldOptional, optional));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getMin() {
        return min;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMin(int newMin) {
        int oldMin = min;
        min = newMin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TCPackage.TC_NODE__MIN, oldMin, min));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getMax() {
        return max;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMax(int newMax) {
        int oldMax = max;
        max = newMax;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TCPackage.TC_NODE__MAX, oldMax, max));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getNWiseCombination() {
        return nWiseCombination;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setNWiseCombination(int newNWiseCombination) {
        int oldNWiseCombination = nWiseCombination;
        nWiseCombination = newNWiseCombination;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TCPackage.TC_NODE__NWISE_COMBINATION, oldNWiseCombination, nWiseCombination));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isTemporary() {
        return temporary;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTemporary(boolean newTemporary) {
        boolean oldTemporary = temporary;
        temporary = newTemporary;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TCPackage.TC_NODE__TEMPORARY, oldTemporary, temporary));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TCProperty> getProperties() {
        if (properties == null) {
            properties = new EObjectContainmentEList<TCProperty>(TCProperty.class, this, TCPackage.TC_NODE__PROPERTIES);
        }
        return properties;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TCPackage.TC_NODE__CHILDREN:
            return ((InternalEList<?>) getChildren()).basicRemove(otherEnd, msgs);
        case TCPackage.TC_NODE__PROPERTIES:
            return ((InternalEList<?>) getProperties()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TCPackage.TC_NODE__NAME:
            return getName();
        case TCPackage.TC_NODE__CHECKED:
            return isChecked();
        case TCPackage.TC_NODE__CHILDREN:
            return getChildren();
        case TCPackage.TC_NODE__CHILD_TYPE:
            return getChildType();
        case TCPackage.TC_NODE__STATE:
            return getState();
        case TCPackage.TC_NODE__INHERITED:
            return isInherited();
        case TCPackage.TC_NODE__OPTIONAL:
            return isOptional();
        case TCPackage.TC_NODE__MIN:
            return getMin();
        case TCPackage.TC_NODE__MAX:
            return getMax();
        case TCPackage.TC_NODE__NWISE_COMBINATION:
            return getNWiseCombination();
        case TCPackage.TC_NODE__TEMPORARY:
            return isTemporary();
        case TCPackage.TC_NODE__PROPERTIES:
            return getProperties();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case TCPackage.TC_NODE__NAME:
            setName((String) newValue);
            return;
        case TCPackage.TC_NODE__CHECKED:
            setChecked((Boolean) newValue);
            return;
        case TCPackage.TC_NODE__CHILDREN:
            getChildren().clear();
            getChildren().addAll((Collection<? extends TCNode>) newValue);
            return;
        case TCPackage.TC_NODE__CHILD_TYPE:
            setChildType((ChildType) newValue);
            return;
        case TCPackage.TC_NODE__STATE:
            setState((TCNodeState) newValue);
            return;
        case TCPackage.TC_NODE__INHERITED:
            setInherited((Boolean) newValue);
            return;
        case TCPackage.TC_NODE__OPTIONAL:
            setOptional((Boolean) newValue);
            return;
        case TCPackage.TC_NODE__MIN:
            setMin((Integer) newValue);
            return;
        case TCPackage.TC_NODE__MAX:
            setMax((Integer) newValue);
            return;
        case TCPackage.TC_NODE__NWISE_COMBINATION:
            setNWiseCombination((Integer) newValue);
            return;
        case TCPackage.TC_NODE__TEMPORARY:
            setTemporary((Boolean) newValue);
            return;
        case TCPackage.TC_NODE__PROPERTIES:
            getProperties().clear();
            getProperties().addAll((Collection<? extends TCProperty>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case TCPackage.TC_NODE__NAME:
            setName(NAME_EDEFAULT);
            return;
        case TCPackage.TC_NODE__CHECKED:
            setChecked(CHECKED_EDEFAULT);
            return;
        case TCPackage.TC_NODE__CHILDREN:
            getChildren().clear();
            return;
        case TCPackage.TC_NODE__CHILD_TYPE:
            setChildType(CHILD_TYPE_EDEFAULT);
            return;
        case TCPackage.TC_NODE__STATE:
            setState(STATE_EDEFAULT);
            return;
        case TCPackage.TC_NODE__INHERITED:
            setInherited(INHERITED_EDEFAULT);
            return;
        case TCPackage.TC_NODE__OPTIONAL:
            setOptional(OPTIONAL_EDEFAULT);
            return;
        case TCPackage.TC_NODE__MIN:
            setMin(MIN_EDEFAULT);
            return;
        case TCPackage.TC_NODE__MAX:
            setMax(MAX_EDEFAULT);
            return;
        case TCPackage.TC_NODE__NWISE_COMBINATION:
            setNWiseCombination(NWISE_COMBINATION_EDEFAULT);
            return;
        case TCPackage.TC_NODE__TEMPORARY:
            setTemporary(TEMPORARY_EDEFAULT);
            return;
        case TCPackage.TC_NODE__PROPERTIES:
            getProperties().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case TCPackage.TC_NODE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case TCPackage.TC_NODE__CHECKED:
            return checked != CHECKED_EDEFAULT;
        case TCPackage.TC_NODE__CHILDREN:
            return children != null && !children.isEmpty();
        case TCPackage.TC_NODE__CHILD_TYPE:
            return childType != CHILD_TYPE_EDEFAULT;
        case TCPackage.TC_NODE__STATE:
            return state != STATE_EDEFAULT;
        case TCPackage.TC_NODE__INHERITED:
            return inherited != INHERITED_EDEFAULT;
        case TCPackage.TC_NODE__OPTIONAL:
            return optional != OPTIONAL_EDEFAULT;
        case TCPackage.TC_NODE__MIN:
            return min != MIN_EDEFAULT;
        case TCPackage.TC_NODE__MAX:
            return max != MAX_EDEFAULT;
        case TCPackage.TC_NODE__NWISE_COMBINATION:
            return nWiseCombination != NWISE_COMBINATION_EDEFAULT;
        case TCPackage.TC_NODE__TEMPORARY:
            return temporary != TEMPORARY_EDEFAULT;
        case TCPackage.TC_NODE__PROPERTIES:
            return properties != null && !properties.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (name: ");
        result.append(name);
        result.append(", checked: ");
        result.append(checked);
        result.append(", childType: ");
        result.append(childType);
        result.append(", state: ");
        result.append(state);
        result.append(", inherited: ");
        result.append(inherited);
        result.append(", optional: ");
        result.append(optional);
        result.append(", min: ");
        result.append(min);
        result.append(", max: ");
        result.append(max);
        result.append(", nWiseCombination: ");
        result.append(nWiseCombination);
        result.append(", temporary: ");
        result.append(temporary);
        result.append(')');
        return result.toString();
    }

} // TCNodeImpl
