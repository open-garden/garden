/**
 */
package com.zipc.garden.model.scd;

import com.zipc.garden.model.core.AbstractRootElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getODFileId <em>OD File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getODcFileId <em>ODc File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getODDFileId <em>ODD File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getBAFileId <em>BA File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getTSDcFileId <em>TS Dc File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getTSDgsFileId <em>TS Dgs File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getTSDpFileId <em>TS Dp File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getBgsFileId <em>Bgs File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getBpFileId <em>Bp File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getLSGSFileId <em>LSGS File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getLSFileId <em>LS File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getCSGSFileId <em>CSGS File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getCSFileId <em>CS File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getOD <em>OD</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getODC <em>ODC</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getBA <em>BA</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getTSDC <em>TSDC</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getTSDGS <em>TSDGS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getTSDP <em>TSDP</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getBGS <em>BGS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getBP <em>BP</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getLSGS <em>LSGS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getLS <em>LS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getCSGS <em>CSGS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getCS <em>CS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getODD <em>ODD</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getCSFileName <em>CS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getTSDCFileName <em>TSDC File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getBPFileName <em>BP File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getTSDPFileName <em>TSDP File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getCSGSFileName <em>CSGS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getBGSFileName <em>BGS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getTSDGSFileName <em>TSDGS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getODCFileName <em>ODC File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getODFileName <em>OD File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getODDFileName <em>ODD File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getBAFileName <em>BA File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getLSFileName <em>LS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getLSGSFileName <em>LSGS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getCBFileId <em>CB File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getSSFileId <em>SS File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getCB <em>CB</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getSS <em>SS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getCBFileName <em>CB File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.SCDRoot#getSSFileName <em>SS File Name</em>}</li>
 * </ul>
 * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot()
 * @model
 * @generated
 */
public interface SCDRoot extends AbstractRootElement {

    /**
     * Returns the value of the '<em><b>OD File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>OD File Id</em>' attribute.
     * @see #setODFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_ODFileId()
     * @model default="-1"
     * @generated
     */
    long getODFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getODFileId <em>OD File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>OD File Id</em>' attribute.
     * @see #getODFileId()
     * @generated
     */
    void setODFileId(long value);

    /**
     * Returns the value of the '<em><b>ODc File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>ODc File Id</em>' attribute.
     * @see #setODcFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_ODcFileId()
     * @model default="-1"
     * @generated
     */
    long getODcFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getODcFileId <em>ODc File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>ODc File Id</em>' attribute.
     * @see #getODcFileId()
     * @generated
     */
    void setODcFileId(long value);

    /**
     * Returns the value of the '<em><b>ODD File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>ODD File Id</em>' attribute.
     * @see #setODDFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_ODDFileId()
     * @model default="-1"
     * @generated
     */
    long getODDFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getODDFileId <em>ODD File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>ODD File Id</em>' attribute.
     * @see #getODDFileId()
     * @generated
     */
    void setODDFileId(long value);

    /**
     * Returns the value of the '<em><b>BA File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>BA File Id</em>' attribute.
     * @see #setBAFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_BAFileId()
     * @model default="-1"
     * @generated
     */
    long getBAFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getBAFileId <em>BA File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>BA File Id</em>' attribute.
     * @see #getBAFileId()
     * @generated
     */
    void setBAFileId(long value);

    /**
     * Returns the value of the '<em><b>TS Dc File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>TS Dc File Id</em>' attribute.
     * @see #setTSDcFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_TSDcFileId()
     * @model default="-1"
     * @generated
     */
    long getTSDcFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getTSDcFileId <em>TS Dc File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>TS Dc File Id</em>' attribute.
     * @see #getTSDcFileId()
     * @generated
     */
    void setTSDcFileId(long value);

    /**
     * Returns the value of the '<em><b>TS Dgs File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>TS Dgs File Id</em>' attribute.
     * @see #setTSDgsFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_TSDgsFileId()
     * @model default="-1"
     * @generated
     */
    long getTSDgsFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getTSDgsFileId <em>TS Dgs File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>TS Dgs File Id</em>' attribute.
     * @see #getTSDgsFileId()
     * @generated
     */
    void setTSDgsFileId(long value);

    /**
     * Returns the value of the '<em><b>TS Dp File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>TS Dp File Id</em>' attribute.
     * @see #setTSDpFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_TSDpFileId()
     * @model default="-1"
     * @generated
     */
    long getTSDpFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getTSDpFileId <em>TS Dp File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>TS Dp File Id</em>' attribute.
     * @see #getTSDpFileId()
     * @generated
     */
    void setTSDpFileId(long value);

    /**
     * Returns the value of the '<em><b>Bgs File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Bgs File Id</em>' attribute.
     * @see #setBgsFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_BgsFileId()
     * @model default="-1"
     * @generated
     */
    long getBgsFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getBgsFileId <em>Bgs File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Bgs File Id</em>' attribute.
     * @see #getBgsFileId()
     * @generated
     */
    void setBgsFileId(long value);

    /**
     * Returns the value of the '<em><b>Bp File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>Bp File Id</em>' attribute.
     * @see #setBpFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_BpFileId()
     * @model default="-1"
     * @generated
     */
    long getBpFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getBpFileId <em>Bp File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Bp File Id</em>' attribute.
     * @see #getBpFileId()
     * @generated
     */
    void setBpFileId(long value);

    /**
     * Returns the value of the '<em><b>LSGS File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>LSGS File Id</em>' attribute.
     * @see #setLSGSFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_LSGSFileId()
     * @model default="-1"
     * @generated
     */
    long getLSGSFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getLSGSFileId <em>LSGS File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>LSGS File Id</em>' attribute.
     * @see #getLSGSFileId()
     * @generated
     */
    void setLSGSFileId(long value);

    /**
     * Returns the value of the '<em><b>LS File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>LS File Id</em>' attribute.
     * @see #setLSFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_LSFileId()
     * @model default="-1"
     * @generated
     */
    long getLSFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getLSFileId <em>LS File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>LS File Id</em>' attribute.
     * @see #getLSFileId()
     * @generated
     */
    void setLSFileId(long value);

    /**
     * Returns the value of the '<em><b>CSGS File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>CSGS File Id</em>' attribute.
     * @see #setCSGSFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_CSGSFileId()
     * @model default="-1"
     * @generated
     */
    long getCSGSFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getCSGSFileId <em>CSGS File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>CSGS File Id</em>' attribute.
     * @see #getCSGSFileId()
     * @generated
     */
    void setCSGSFileId(long value);

    /**
     * Returns the value of the '<em><b>CS File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>CS File Id</em>' attribute.
     * @see #setCSFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_CSFileId()
     * @model default="-1"
     * @generated
     */
    long getCSFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getCSFileId <em>CS File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>CS File Id</em>' attribute.
     * @see #getCSFileId()
     * @generated
     */
    void setCSFileId(long value);

    /**
     * Returns the value of the '<em><b>OD</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>OD</em>' attribute.
     * @see #setOD(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_OD()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getOD();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getOD <em>OD</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>OD</em>' attribute.
     * @see #getOD()
     * @generated
     */
    void setOD(String value);

    /**
     * Returns the value of the '<em><b>ODC</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>ODC</em>' attribute.
     * @see #setODC(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_ODC()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getODC();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getODC <em>ODC</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>ODC</em>' attribute.
     * @see #getODC()
     * @generated
     */
    void setODC(String value);

    /**
     * Returns the value of the '<em><b>BA</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BA</em>' attribute.
     * @see #setBA(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_BA()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getBA();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getBA <em>BA</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BA</em>' attribute.
     * @see #getBA()
     * @generated
     */
    void setBA(String value);

    /**
     * Returns the value of the '<em><b>TSDC</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>TSDC</em>' attribute.
     * @see #setTSDC(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_TSDC()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getTSDC();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getTSDC <em>TSDC</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>TSDC</em>' attribute.
     * @see #getTSDC()
     * @generated
     */
    void setTSDC(String value);

    /**
     * Returns the value of the '<em><b>TSDGS</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>TSDGS</em>' attribute.
     * @see #setTSDGS(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_TSDGS()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getTSDGS();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getTSDGS <em>TSDGS</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>TSDGS</em>' attribute.
     * @see #getTSDGS()
     * @generated
     */
    void setTSDGS(String value);

    /**
     * Returns the value of the '<em><b>TSDP</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>TSDP</em>' attribute.
     * @see #setTSDP(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_TSDP()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getTSDP();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getTSDP <em>TSDP</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>TSDP</em>' attribute.
     * @see #getTSDP()
     * @generated
     */
    void setTSDP(String value);

    /**
     * Returns the value of the '<em><b>BGS</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BGS</em>' attribute.
     * @see #setBGS(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_BGS()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getBGS();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getBGS <em>BGS</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BGS</em>' attribute.
     * @see #getBGS()
     * @generated
     */
    void setBGS(String value);

    /**
     * Returns the value of the '<em><b>BP</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>BP</em>' attribute.
     * @see #setBP(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_BP()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getBP();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getBP <em>BP</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>BP</em>' attribute.
     * @see #getBP()
     * @generated
     */
    void setBP(String value);

    /**
     * Returns the value of the '<em><b>LSGS</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>LSGS</em>' attribute.
     * @see #setLSGS(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_LSGS()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getLSGS();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getLSGS <em>LSGS</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>LSGS</em>' attribute.
     * @see #getLSGS()
     * @generated
     */
    void setLSGS(String value);

    /**
     * Returns the value of the '<em><b>LS</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>LS</em>' attribute.
     * @see #setLS(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_LS()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getLS();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getLS <em>LS</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>LS</em>' attribute.
     * @see #getLS()
     * @generated
     */
    void setLS(String value);

    /**
     * Returns the value of the '<em><b>CSGS</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>CSGS</em>' attribute.
     * @see #setCSGS(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_CSGS()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getCSGS();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getCSGS <em>CSGS</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the new value of the '<em>CSGS</em>' attribute.
     * @see #getCSGS()
     * @generated
     */
    void setCSGS(String value);

    /**
     * Returns the value of the '<em><b>CS</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>CS</em>' attribute.
     * @see #setCS(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_CS()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getCS();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getCS <em>CS</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>CS</em>' attribute.
     * @see #getCS()
     * @generated
     */
    void setCS(String value);

    /**
     * Returns the value of the '<em><b>ODD</b></em>' attribute. The default value is <code>""</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>ODD</em>' attribute.
     * @see #setODD(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_ODD()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getODD();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getODD <em>ODD</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>ODD</em>' attribute.
     * @see #getODD()
     * @generated
     */
    void setODD(String value);

    /**
     * Returns the value of the '<em><b>CS File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>CS File Name</em>' attribute.
     * @see #setCSFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_CSFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getCSFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getCSFileName <em>CS File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>CS File Name</em>' attribute.
     * @see #getCSFileName()
     * @generated
     */
    void setCSFileName(String value);

    /**
     * Returns the value of the '<em><b>TSDC File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>TSDC File Name</em>' attribute.
     * @see #setTSDCFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_TSDCFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getTSDCFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getTSDCFileName <em>TSDC File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>TSDC File Name</em>' attribute.
     * @see #getTSDCFileName()
     * @generated
     */
    void setTSDCFileName(String value);

    /**
     * Returns the value of the '<em><b>BP File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>BP File Name</em>' attribute.
     * @see #setBPFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_BPFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getBPFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getBPFileName <em>BP File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>BP File Name</em>' attribute.
     * @see #getBPFileName()
     * @generated
     */
    void setBPFileName(String value);

    /**
     * Returns the value of the '<em><b>TSDP File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>TSDP File Name</em>' attribute.
     * @see #setTSDPFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_TSDPFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getTSDPFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getTSDPFileName <em>TSDP File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>TSDP File Name</em>' attribute.
     * @see #getTSDPFileName()
     * @generated
     */
    void setTSDPFileName(String value);

    /**
     * Returns the value of the '<em><b>CSGS File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>CSGS File Name</em>' attribute.
     * @see #setCSGSFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_CSGSFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getCSGSFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getCSGSFileName <em>CSGS File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>CSGS File Name</em>' attribute.
     * @see #getCSGSFileName()
     * @generated
     */
    void setCSGSFileName(String value);

    /**
     * Returns the value of the '<em><b>BGS File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>BGS File Name</em>' attribute.
     * @see #setBGSFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_BGSFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getBGSFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getBGSFileName <em>BGS File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>BGS File Name</em>' attribute.
     * @see #getBGSFileName()
     * @generated
     */
    void setBGSFileName(String value);

    /**
     * Returns the value of the '<em><b>TSDGS File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>TSDGS File Name</em>' attribute.
     * @see #setTSDGSFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_TSDGSFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getTSDGSFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getTSDGSFileName <em>TSDGS File Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>TSDGS File Name</em>' attribute.
     * @see #getTSDGSFileName()
     * @generated
     */
    void setTSDGSFileName(String value);

    /**
     * Returns the value of the '<em><b>ODC File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>ODC File Name</em>' attribute.
     * @see #setODCFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_ODCFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getODCFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getODCFileName <em>ODC File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>ODC File Name</em>' attribute.
     * @see #getODCFileName()
     * @generated
     */
    void setODCFileName(String value);

    /**
     * Returns the value of the '<em><b>OD File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>OD File Name</em>' attribute.
     * @see #setODFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_ODFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getODFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getODFileName <em>OD File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>OD File Name</em>' attribute.
     * @see #getODFileName()
     * @generated
     */
    void setODFileName(String value);

    /**
     * Returns the value of the '<em><b>ODD File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>ODD File Name</em>' attribute.
     * @see #setODDFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_ODDFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getODDFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getODDFileName <em>ODD File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>ODD File Name</em>' attribute.
     * @see #getODDFileName()
     * @generated
     */
    void setODDFileName(String value);

    /**
     * Returns the value of the '<em><b>BA File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>BA File Name</em>' attribute.
     * @see #setBAFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_BAFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getBAFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getBAFileName <em>BA File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>BA File Name</em>' attribute.
     * @see #getBAFileName()
     * @generated
     */
    void setBAFileName(String value);

    /**
     * Returns the value of the '<em><b>LS File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>LS File Name</em>' attribute.
     * @see #setLSFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_LSFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getLSFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getLSFileName <em>LS File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>LS File Name</em>' attribute.
     * @see #getLSFileName()
     * @generated
     */
    void setLSFileName(String value);

    /**
     * Returns the value of the '<em><b>LSGS File Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>LSGS File Name</em>' attribute.
     * @see #setLSGSFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_LSGSFileName()
     * @model default="" dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getLSGSFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getLSGSFileName <em>LSGS File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>LSGS File Name</em>' attribute.
     * @see #getLSGSFileName()
     * @generated
     */
    void setLSGSFileName(String value);

    /**
     * Returns the value of the '<em><b>CB File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>CB File Id</em>' attribute.
     * @see #setCBFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_CBFileId()
     * @model default="-1"
     * @generated
     */
    long getCBFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getCBFileId <em>CB File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>CB File Id</em>' attribute.
     * @see #getCBFileId()
     * @generated
     */
    void setCBFileId(long value);

    /**
     * Returns the value of the '<em><b>SS File Id</b></em>' attribute. The default value is <code>"-1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>SS File Id</em>' attribute.
     * @see #setSSFileId(long)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_SSFileId()
     * @model default="-1"
     * @generated
     */
    long getSSFileId();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getSSFileId <em>SS File Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>SS File Id</em>' attribute.
     * @see #getSSFileId()
     * @generated
     */
    void setSSFileId(long value);

    /**
     * Returns the value of the '<em><b>CB</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>CB</em>' attribute.
     * @see #setCB(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_CB()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getCB();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getCB <em>CB</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>CB</em>' attribute.
     * @see #getCB()
     * @generated
     */
    void setCB(String value);

    /**
     * Returns the value of the '<em><b>SS</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>SS</em>' attribute.
     * @see #setSS(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_SS()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getSS();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getSS <em>SS</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>SS</em>' attribute.
     * @see #getSS()
     * @generated
     */
    void setSS(String value);

    /**
     * Returns the value of the '<em><b>CB File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>CB File Name</em>' attribute.
     * @see #setCBFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_CBFileName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getCBFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getCBFileName <em>CB File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>CB File Name</em>' attribute.
     * @see #getCBFileName()
     * @generated
     */
    void setCBFileName(String value);

    /**
     * Returns the value of the '<em><b>SS File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the value of the '<em>SS File Name</em>' attribute.
     * @see #setSSFileName(String)
     * @see com.zipc.garden.model.scd.SCDPackage#getSCDRoot_SSFileName()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
     * @generated
     */
    String getSSFileName();

    /**
     * Sets the value of the '{@link com.zipc.garden.model.scd.SCDRoot#getSSFileName <em>SS File Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>SS File Name</em>' attribute.
     * @see #getSSFileName()
     * @generated
     */
    void setSSFileName(String value);
} // SCDRoot
