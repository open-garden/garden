/**
 */
package com.zipc.garden.model.bps;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Path Combination Option</b></em>'. <!-- end-user-doc -->
 * <!-- begin-model-doc --> ステートマシンの初期状態からの遷移で構成されるパスを，ARCに含まれるすべてのステートマシンに対して求め，それらの組み合わせからBPを生成するオプション <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.BPSPathCombinationOption#getPathLength <em>Path Length</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSPathCombinationOption()
 * @model
 * @generated
 */
public interface BPSPathCombinationOption extends BPSOption {
    /**
     * Returns the value of the '<em><b>Path Length</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> 生成するパスの長さ．遷移するか，イベントが発生せずに今の状態にとどまるかを{@code pathLength - 1}回発生させる．1は初期状態のみのパスを意味する． <!--
     * end-model-doc -->
     * @return the value of the '<em>Path Length</em>' attribute.
     * @see #setPathLength(int)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSPathCombinationOption_PathLength()
     * @model required="true"
     * @generated
     */
    int getPathLength();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSPathCombinationOption#getPathLength <em>Path Length</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Path Length</em>' attribute.
     * @see #getPathLength()
     * @generated
     */
    void setPathLength(int value);

} // BPSPathCombinationOption
