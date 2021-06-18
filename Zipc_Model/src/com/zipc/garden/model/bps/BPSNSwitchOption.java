/**
 */
package com.zipc.garden.model.bps;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>NSwitch Option</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> 指定されたFSMのN-Switchを満たすパスを生成するオプション <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.bps.BPSNSwitchOption#getTSMFileId <em>TSM File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.bps.BPSNSwitchOption#getNSwitch <em>NSwitch</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSNSwitchOption()
 * @model
 * @generated
 */
public interface BPSNSwitchOption extends BPSOption {
    /**
     * Returns the value of the '<em><b>TSM File Id</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>TSM File Id</em>' attribute.
     * @see #setTSMFileId(String)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSNSwitchOption_TSMFileId()
     * @model default="" required="true"
     * @generated
     */
    String getTSMFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSNSwitchOption#getTSMFileId <em>TSM File Id</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>TSM File Id</em>' attribute.
     * @see #getTSMFileId()
     * @generated
     */
    void setTSMFileId(String value);

    /**
     * Returns the value of the '<em><b>NSwitch</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>NSwitch</em>' attribute.
     * @see #setNSwitch(int)
     * @see com.zipc.garden.model.bps.BPSPackage#getBPSNSwitchOption_NSwitch()
     * @model required="true"
     * @generated
     */
    int getNSwitch();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.bps.BPSNSwitchOption#getNSwitch <em>NSwitch</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>NSwitch</em>' attribute.
     * @see #getNSwitch()
     * @generated
     */
    void setNSwitch(int value);

} // BPSNSwitchOption
