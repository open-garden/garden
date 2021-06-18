/**
 */
package com.zipc.garden.model.fmcs;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>And Expression</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> 論理積 <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.fmcs.FMCSAndExpression#getExpressions <em>Expressions</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSAndExpression()
 * @model
 * @generated
 */
public interface FMCSAndExpression extends FMCSExpression {
    /**
     * Returns the value of the '<em><b>Expressions</b></em>' containment reference list. The list contents are of type
     * {@link com.zipc.garden.model.fmcs.FMCSExpression}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Expressions</em>' containment reference list.
     * @see com.zipc.garden.model.fmcs.FMCSPackage#getFMCSAndExpression_Expressions()
     * @model containment="true" lower="2"
     * @generated
     */
    EList<FMCSExpression> getExpressions();

} // FMCSAndExpression
