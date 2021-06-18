/**
 */
package com.zipc.garden.model.fm.impl;

import com.google.gwt.user.client.rpc.GwtTransient;

import com.zipc.garden.model.fm.FMConstraint;
import com.zipc.garden.model.fm.FMNode;
import com.zipc.garden.model.fm.FMPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Constraint</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fm.impl.FMConstraintImpl#getConstraint <em>Constraint</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMConstraintImpl#getComment <em>Comment</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMConstraintImpl#isEnabled <em>Enabled</em>}</li>
 * <li>{@link com.zipc.garden.model.fm.impl.FMConstraintImpl#getRelatedNodes <em>Related Nodes</em>}</li>
 * </ul>
 * @generated
 */
public class FMConstraintImpl extends MinimalEObjectImpl.Container implements FMConstraint {
    /**
     * The default value of the '{@link #getConstraint() <em>Constraint</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getConstraint()
     * @generated
     * @ordered
     */
    protected static final String CONSTRAINT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getConstraint()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String constraint = CONSTRAINT_EDEFAULT;

    /**
     * The default value of the '{@link #getComment() <em>Comment</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getComment()
     * @generated
     * @ordered
     */
    protected static final String COMMENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getComment()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String comment = COMMENT_EDEFAULT;

    /**
     * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
    protected static final boolean ENABLED_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #isEnabled()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected boolean enabled = ENABLED_EDEFAULT;

    /**
     * The cached value of the '{@link #getRelatedNodes() <em>Related Nodes</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getRelatedNodes()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected EList<FMNode> relatedNodes;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected FMConstraintImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FMPackage.Literals.FM_CONSTRAINT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getConstraint() {
        return constraint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setConstraint(String newConstraint) {
        String oldConstraint = constraint;
        constraint = newConstraint;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_CONSTRAINT__CONSTRAINT, oldConstraint, constraint));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getComment() {
        return comment;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setComment(String newComment) {
        String oldComment = comment;
        comment = newComment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_CONSTRAINT__COMMENT, oldComment, comment));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setEnabled(boolean newEnabled) {
        boolean oldEnabled = enabled;
        enabled = newEnabled;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, FMPackage.FM_CONSTRAINT__ENABLED, oldEnabled, enabled));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FMNode> getRelatedNodes() {
        if (relatedNodes == null) {
            relatedNodes = new EObjectResolvingEList<FMNode>(FMNode.class, this, FMPackage.FM_CONSTRAINT__RELATED_NODES);
        }
        return relatedNodes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case FMPackage.FM_CONSTRAINT__CONSTRAINT:
            return getConstraint();
        case FMPackage.FM_CONSTRAINT__COMMENT:
            return getComment();
        case FMPackage.FM_CONSTRAINT__ENABLED:
            return isEnabled();
        case FMPackage.FM_CONSTRAINT__RELATED_NODES:
            return getRelatedNodes();
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
        case FMPackage.FM_CONSTRAINT__CONSTRAINT:
            setConstraint((String) newValue);
            return;
        case FMPackage.FM_CONSTRAINT__COMMENT:
            setComment((String) newValue);
            return;
        case FMPackage.FM_CONSTRAINT__ENABLED:
            setEnabled((Boolean) newValue);
            return;
        case FMPackage.FM_CONSTRAINT__RELATED_NODES:
            getRelatedNodes().clear();
            getRelatedNodes().addAll((Collection<? extends FMNode>) newValue);
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
        case FMPackage.FM_CONSTRAINT__CONSTRAINT:
            setConstraint(CONSTRAINT_EDEFAULT);
            return;
        case FMPackage.FM_CONSTRAINT__COMMENT:
            setComment(COMMENT_EDEFAULT);
            return;
        case FMPackage.FM_CONSTRAINT__ENABLED:
            setEnabled(ENABLED_EDEFAULT);
            return;
        case FMPackage.FM_CONSTRAINT__RELATED_NODES:
            getRelatedNodes().clear();
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
        case FMPackage.FM_CONSTRAINT__CONSTRAINT:
            return CONSTRAINT_EDEFAULT == null ? constraint != null : !CONSTRAINT_EDEFAULT.equals(constraint);
        case FMPackage.FM_CONSTRAINT__COMMENT:
            return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
        case FMPackage.FM_CONSTRAINT__ENABLED:
            return enabled != ENABLED_EDEFAULT;
        case FMPackage.FM_CONSTRAINT__RELATED_NODES:
            return relatedNodes != null && !relatedNodes.isEmpty();
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
        result.append(" (constraint: ");
        result.append(constraint);
        result.append(", comment: ");
        result.append(comment);
        result.append(", enabled: ");
        result.append(enabled);
        result.append(')');
        return result.toString();
    }

} // FMConstraintImpl
