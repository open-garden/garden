/**
 */
package com.zipc.garden.model.scd.impl;

import com.google.gwt.user.client.rpc.GwtTransient;
import com.zipc.garden.model.core.impl.AbstractRootElementImpl;

import com.zipc.garden.model.scd.SCDPackage;
import com.zipc.garden.model.scd.SCDRoot;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getODFileId <em>OD File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getODcFileId <em>ODc File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getODDFileId <em>ODD File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getBAFileId <em>BA File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getTSDcFileId <em>TS Dc File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getTSDgsFileId <em>TS Dgs File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getTSDpFileId <em>TS Dp File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getBgsFileId <em>Bgs File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getBpFileId <em>Bp File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getLSGSFileId <em>LSGS File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getLSFileId <em>LS File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getCSGSFileId <em>CSGS File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getCSFileId <em>CS File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getOD <em>OD</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getODC <em>ODC</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getBA <em>BA</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getTSDC <em>TSDC</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getTSDGS <em>TSDGS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getTSDP <em>TSDP</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getBGS <em>BGS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getBP <em>BP</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getLSGS <em>LSGS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getLS <em>LS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getCSGS <em>CSGS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getCS <em>CS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getODD <em>ODD</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getCSFileName <em>CS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getTSDCFileName <em>TSDC File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getBPFileName <em>BP File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getTSDPFileName <em>TSDP File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getCSGSFileName <em>CSGS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getBGSFileName <em>BGS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getTSDGSFileName <em>TSDGS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getODCFileName <em>ODC File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getODFileName <em>OD File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getODDFileName <em>ODD File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getBAFileName <em>BA File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getLSFileName <em>LS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getLSGSFileName <em>LSGS File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getCBFileId <em>CB File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getSSFileId <em>SS File Id</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getCB <em>CB</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getSS <em>SS</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getCBFileName <em>CB File Name</em>}</li>
 * <li>{@link com.zipc.garden.model.scd.impl.SCDRootImpl#getSSFileName <em>SS File Name</em>}</li>
 * </ul>
 * @generated
 */
public class SCDRootImpl extends AbstractRootElementImpl implements SCDRoot {
    /**
     * The default value of the '{@link #getODFileId() <em>OD File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getODFileId()
     * @generated
     * @ordered
     */
    protected static final long OD_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getODFileId() <em>OD File Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getODFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long odFileId = OD_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getODcFileId() <em>ODc File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getODcFileId()
     * @generated
     * @ordered
     */
    protected static final long ODC_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getODcFileId() <em>ODc File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getODcFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long oDcFileId = ODC_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getODDFileId() <em>ODD File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getODDFileId()
     * @generated
     * @ordered
     */
    protected static final long ODD_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getODDFileId() <em>ODD File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getODDFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long oddFileId = ODD_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getBAFileId() <em>BA File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBAFileId()
     * @generated
     * @ordered
     */
    protected static final long BA_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getBAFileId() <em>BA File Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getBAFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long baFileId = BA_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getTSDcFileId() <em>TS Dc File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDcFileId()
     * @generated
     * @ordered
     */
    protected static final long TS_DC_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getTSDcFileId() <em>TS Dc File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDcFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long tsDcFileId = TS_DC_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getTSDgsFileId() <em>TS Dgs File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDgsFileId()
     * @generated
     * @ordered
     */
    protected static final long TS_DGS_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getTSDgsFileId() <em>TS Dgs File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDgsFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long tsDgsFileId = TS_DGS_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getTSDpFileId() <em>TS Dp File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDpFileId()
     * @generated
     * @ordered
     */
    protected static final long TS_DP_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getTSDpFileId() <em>TS Dp File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDpFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long tsDpFileId = TS_DP_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getBgsFileId() <em>Bgs File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBgsFileId()
     * @generated
     * @ordered
     */
    protected static final long BGS_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getBgsFileId() <em>Bgs File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBgsFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long bgsFileId = BGS_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getBpFileId() <em>Bp File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBpFileId()
     * @generated
     * @ordered
     */
    protected static final long BP_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getBpFileId() <em>Bp File Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getBpFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long bpFileId = BP_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getLSGSFileId() <em>LSGS File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLSGSFileId()
     * @generated
     * @ordered
     */
    protected static final long LSGS_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getLSGSFileId() <em>LSGS File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLSGSFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long lsgsFileId = LSGS_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getLSFileId() <em>LS File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLSFileId()
     * @generated
     * @ordered
     */
    protected static final long LS_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getLSFileId() <em>LS File Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getLSFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long lsFileId = LS_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getCSGSFileId() <em>CSGS File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCSGSFileId()
     * @generated
     * @ordered
     */
    protected static final long CSGS_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getCSGSFileId() <em>CSGS File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCSGSFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long csgsFileId = CSGS_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getCSFileId() <em>CS File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCSFileId()
     * @generated
     * @ordered
     */
    protected static final long CS_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getCSFileId() <em>CS File Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getCSFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long csFileId = CS_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getOD() <em>OD</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getOD()
     * @generated
     * @ordered
     */
    protected static final String OD_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getOD() <em>OD</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getOD()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String od = OD_EDEFAULT;

    /**
     * The default value of the '{@link #getODC() <em>ODC</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getODC()
     * @generated
     * @ordered
     */
    protected static final String ODC_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getODC() <em>ODC</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getODC()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String odc = ODC_EDEFAULT;

    /**
     * The default value of the '{@link #getBA() <em>BA</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getBA()
     * @generated
     * @ordered
     */
    protected static final String BA_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getBA() <em>BA</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getBA()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String ba = BA_EDEFAULT;

    /**
     * The default value of the '{@link #getTSDC() <em>TSDC</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTSDC()
     * @generated
     * @ordered
     */
    protected static final String TSDC_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getTSDC() <em>TSDC</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTSDC()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String tsdc = TSDC_EDEFAULT;

    /**
     * The default value of the '{@link #getTSDGS() <em>TSDGS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTSDGS()
     * @generated
     * @ordered
     */
    protected static final String TSDGS_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getTSDGS() <em>TSDGS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTSDGS()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String tsdgs = TSDGS_EDEFAULT;

    /**
     * The default value of the '{@link #getTSDP() <em>TSDP</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTSDP()
     * @generated
     * @ordered
     */
    protected static final String TSDP_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getTSDP() <em>TSDP</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTSDP()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String tsdp = TSDP_EDEFAULT;

    /**
     * The default value of the '{@link #getBGS() <em>BGS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getBGS()
     * @generated
     * @ordered
     */
    protected static final String BGS_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getBGS() <em>BGS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getBGS()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String bgs = BGS_EDEFAULT;

    /**
     * The default value of the '{@link #getBP() <em>BP</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getBP()
     * @generated
     * @ordered
     */
    protected static final String BP_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getBP() <em>BP</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getBP()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String bp = BP_EDEFAULT;

    /**
     * The default value of the '{@link #getLSGS() <em>LSGS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLSGS()
     * @generated
     * @ordered
     */
    protected static final String LSGS_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getLSGS() <em>LSGS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLSGS()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String lsgs = LSGS_EDEFAULT;

    /**
     * The default value of the '{@link #getLS() <em>LS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLS()
     * @generated
     * @ordered
     */
    protected static final String LS_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getLS() <em>LS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getLS()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String ls = LS_EDEFAULT;

    /**
     * The default value of the '{@link #getCSGS() <em>CSGS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCSGS()
     * @generated
     * @ordered
     */
    protected static final String CSGS_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getCSGS() <em>CSGS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCSGS()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String csgs = CSGS_EDEFAULT;

    /**
     * The default value of the '{@link #getCS() <em>CS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCS()
     * @generated
     * @ordered
     */
    protected static final String CS_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getCS() <em>CS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCS()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String cs = CS_EDEFAULT;

    /**
     * The default value of the '{@link #getODD() <em>ODD</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getODD()
     * @generated
     * @ordered
     */
    protected static final String ODD_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getODD() <em>ODD</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getODD()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String odd = ODD_EDEFAULT;

    /**
     * The default value of the '{@link #getCSFileName() <em>CS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCSFileName()
     * @generated
     * @ordered
     */
    protected static final String CS_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getCSFileName() <em>CS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCSFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String csFileName = CS_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getTSDCFileName() <em>TSDC File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDCFileName()
     * @generated
     * @ordered
     */
    protected static final String TSDC_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getTSDCFileName() <em>TSDC File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDCFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String tsdcFileName = TSDC_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getBPFileName() <em>BP File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBPFileName()
     * @generated
     * @ordered
     */
    protected static final String BP_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getBPFileName() <em>BP File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBPFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String bpFileName = BP_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getTSDPFileName() <em>TSDP File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDPFileName()
     * @generated
     * @ordered
     */
    protected static final String TSDP_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getTSDPFileName() <em>TSDP File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDPFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String tsdpFileName = TSDP_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getCSGSFileName() <em>CSGS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCSGSFileName()
     * @generated
     * @ordered
     */
    protected static final String CSGS_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getCSGSFileName() <em>CSGS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCSGSFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String csgsFileName = CSGS_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getBGSFileName() <em>BGS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBGSFileName()
     * @generated
     * @ordered
     */
    protected static final String BGS_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getBGSFileName() <em>BGS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBGSFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String bgsFileName = BGS_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getTSDGSFileName() <em>TSDGS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDGSFileName()
     * @generated
     * @ordered
     */
    protected static final String TSDGS_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getTSDGSFileName() <em>TSDGS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getTSDGSFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String tsdgsFileName = TSDGS_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getODCFileName() <em>ODC File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getODCFileName()
     * @generated
     * @ordered
     */
    protected static final String ODC_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getODCFileName() <em>ODC File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getODCFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String odcFileName = ODC_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getODFileName() <em>OD File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getODFileName()
     * @generated
     * @ordered
     */
    protected static final String OD_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getODFileName() <em>OD File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getODFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String odFileName = OD_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getODDFileName() <em>ODD File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getODDFileName()
     * @generated
     * @ordered
     */
    protected static final String ODD_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getODDFileName() <em>ODD File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getODDFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String oddFileName = ODD_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getBAFileName() <em>BA File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBAFileName()
     * @generated
     * @ordered
     */
    protected static final String BA_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getBAFileName() <em>BA File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getBAFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String baFileName = BA_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getLSFileName() <em>LS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLSFileName()
     * @generated
     * @ordered
     */
    protected static final String LS_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getLSFileName() <em>LS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLSFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String lsFileName = LS_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getLSGSFileName() <em>LSGS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLSGSFileName()
     * @generated
     * @ordered
     */
    protected static final String LSGS_FILE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getLSGSFileName() <em>LSGS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getLSGSFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String lsgsFileName = LSGS_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getCBFileId() <em>CB File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCBFileId()
     * @generated
     * @ordered
     */
    protected static final long CB_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getCBFileId() <em>CB File Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getCBFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long cbFileId = CB_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getSSFileId() <em>SS File Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSSFileId()
     * @generated
     * @ordered
     */
    protected static final long SS_FILE_ID_EDEFAULT = -1L;

    /**
     * The cached value of the '{@link #getSSFileId() <em>SS File Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @see #getSSFileId()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected long ssFileId = SS_FILE_ID_EDEFAULT;

    /**
     * The default value of the '{@link #getCB() <em>CB</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCB()
     * @generated
     * @ordered
     */
    protected static final String CB_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCB() <em>CB</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getCB()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String cb = CB_EDEFAULT;

    /**
     * The default value of the '{@link #getSS() <em>SS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getSS()
     * @generated
     * @ordered
     */
    protected static final String SS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSS() <em>SS</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getSS()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String ss = SS_EDEFAULT;

    /**
     * The default value of the '{@link #getCBFileName() <em>CB File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCBFileName()
     * @generated
     * @ordered
     */
    protected static final String CB_FILE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCBFileName() <em>CB File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getCBFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String cbFileName = CB_FILE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getSSFileName() <em>SS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSSFileName()
     * @generated
     * @ordered
     */
    protected static final String SS_FILE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSSFileName() <em>SS File Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getSSFileName()
     * @generated
     * @ordered
     */
    @GwtTransient
    protected String ssFileName = SS_FILE_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected SCDRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return SCDPackage.Literals.SCD_ROOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getODFileId() {
        return odFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setODFileId(long newODFileId) {
        long oldODFileId = odFileId;
        odFileId = newODFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__OD_FILE_ID, oldODFileId, odFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getODcFileId() {
        return oDcFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setODcFileId(long newODcFileId) {
        long oldODcFileId = oDcFileId;
        oDcFileId = newODcFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__ODC_FILE_ID, oldODcFileId, oDcFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getODDFileId() {
        return oddFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setODDFileId(long newODDFileId) {
        long oldODDFileId = oddFileId;
        oddFileId = newODDFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__ODD_FILE_ID, oldODDFileId, oddFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getBAFileId() {
        return baFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBAFileId(long newBAFileId) {
        long oldBAFileId = baFileId;
        baFileId = newBAFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__BA_FILE_ID, oldBAFileId, baFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getTSDcFileId() {
        return tsDcFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTSDcFileId(long newTSDcFileId) {
        long oldTSDcFileId = tsDcFileId;
        tsDcFileId = newTSDcFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__TS_DC_FILE_ID, oldTSDcFileId, tsDcFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getTSDgsFileId() {
        return tsDgsFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTSDgsFileId(long newTSDgsFileId) {
        long oldTSDgsFileId = tsDgsFileId;
        tsDgsFileId = newTSDgsFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__TS_DGS_FILE_ID, oldTSDgsFileId, tsDgsFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getTSDpFileId() {
        return tsDpFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTSDpFileId(long newTSDpFileId) {
        long oldTSDpFileId = tsDpFileId;
        tsDpFileId = newTSDpFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__TS_DP_FILE_ID, oldTSDpFileId, tsDpFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getBgsFileId() {
        return bgsFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBgsFileId(long newBgsFileId) {
        long oldBgsFileId = bgsFileId;
        bgsFileId = newBgsFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__BGS_FILE_ID, oldBgsFileId, bgsFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getBpFileId() {
        return bpFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBpFileId(long newBpFileId) {
        long oldBpFileId = bpFileId;
        bpFileId = newBpFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__BP_FILE_ID, oldBpFileId, bpFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getLSGSFileId() {
        return lsgsFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLSGSFileId(long newLSGSFileId) {
        long oldLSGSFileId = lsgsFileId;
        lsgsFileId = newLSGSFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__LSGS_FILE_ID, oldLSGSFileId, lsgsFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getLSFileId() {
        return lsFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLSFileId(long newLSFileId) {
        long oldLSFileId = lsFileId;
        lsFileId = newLSFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__LS_FILE_ID, oldLSFileId, lsFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getCSGSFileId() {
        return csgsFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCSGSFileId(long newCSGSFileId) {
        long oldCSGSFileId = csgsFileId;
        csgsFileId = newCSGSFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__CSGS_FILE_ID, oldCSGSFileId, csgsFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getCSFileId() {
        return csFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCSFileId(long newCSFileId) {
        long oldCSFileId = csFileId;
        csFileId = newCSFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__CS_FILE_ID, oldCSFileId, csFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getOD() {
        return od;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setOD(String newOD) {
        String oldOD = od;
        od = newOD;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__OD, oldOD, od));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getODC() {
        return odc;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setODC(String newODC) {
        String oldODC = odc;
        odc = newODC;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__ODC, oldODC, odc));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBA() {
        return ba;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBA(String newBA) {
        String oldBA = ba;
        ba = newBA;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__BA, oldBA, ba));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTSDC() {
        return tsdc;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTSDC(String newTSDC) {
        String oldTSDC = tsdc;
        tsdc = newTSDC;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__TSDC, oldTSDC, tsdc));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTSDGS() {
        return tsdgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTSDGS(String newTSDGS) {
        String oldTSDGS = tsdgs;
        tsdgs = newTSDGS;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__TSDGS, oldTSDGS, tsdgs));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTSDP() {
        return tsdp;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTSDP(String newTSDP) {
        String oldTSDP = tsdp;
        tsdp = newTSDP;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__TSDP, oldTSDP, tsdp));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBGS() {
        return bgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBGS(String newBGS) {
        String oldBGS = bgs;
        bgs = newBGS;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__BGS, oldBGS, bgs));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBP() {
        return bp;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBP(String newBP) {
        String oldBP = bp;
        bp = newBP;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__BP, oldBP, bp));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getLSGS() {
        return lsgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLSGS(String newLSGS) {
        String oldLSGS = lsgs;
        lsgs = newLSGS;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__LSGS, oldLSGS, lsgs));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getLS() {
        return ls;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLS(String newLS) {
        String oldLS = ls;
        ls = newLS;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__LS, oldLS, ls));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCSGS() {
        return csgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCSGS(String newCSGS) {
        String oldCSGS = csgs;
        csgs = newCSGS;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__CSGS, oldCSGS, csgs));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCS() {
        return cs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCS(String newCS) {
        String oldCS = cs;
        cs = newCS;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__CS, oldCS, cs));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getODD() {
        return odd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setODD(String newODD) {
        String oldODD = odd;
        odd = newODD;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__ODD, oldODD, odd));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCSFileName() {
        return csFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCSFileName(String newCSFileName) {
        String oldCSFileName = csFileName;
        csFileName = newCSFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__CS_FILE_NAME, oldCSFileName, csFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTSDCFileName() {
        return tsdcFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTSDCFileName(String newTSDCFileName) {
        String oldTSDCFileName = tsdcFileName;
        tsdcFileName = newTSDCFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__TSDC_FILE_NAME, oldTSDCFileName, tsdcFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBPFileName() {
        return bpFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBPFileName(String newBPFileName) {
        String oldBPFileName = bpFileName;
        bpFileName = newBPFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__BP_FILE_NAME, oldBPFileName, bpFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTSDPFileName() {
        return tsdpFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTSDPFileName(String newTSDPFileName) {
        String oldTSDPFileName = tsdpFileName;
        tsdpFileName = newTSDPFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__TSDP_FILE_NAME, oldTSDPFileName, tsdpFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCSGSFileName() {
        return csgsFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCSGSFileName(String newCSGSFileName) {
        String oldCSGSFileName = csgsFileName;
        csgsFileName = newCSGSFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__CSGS_FILE_NAME, oldCSGSFileName, csgsFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBGSFileName() {
        return bgsFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBGSFileName(String newBGSFileName) {
        String oldBGSFileName = bgsFileName;
        bgsFileName = newBGSFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__BGS_FILE_NAME, oldBGSFileName, bgsFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTSDGSFileName() {
        return tsdgsFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTSDGSFileName(String newTSDGSFileName) {
        String oldTSDGSFileName = tsdgsFileName;
        tsdgsFileName = newTSDGSFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__TSDGS_FILE_NAME, oldTSDGSFileName, tsdgsFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getODCFileName() {
        return odcFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setODCFileName(String newODCFileName) {
        String oldODCFileName = odcFileName;
        odcFileName = newODCFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__ODC_FILE_NAME, oldODCFileName, odcFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getODFileName() {
        return odFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setODFileName(String newODFileName) {
        String oldODFileName = odFileName;
        odFileName = newODFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__OD_FILE_NAME, oldODFileName, odFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getODDFileName() {
        return oddFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setODDFileName(String newODDFileName) {
        String oldODDFileName = oddFileName;
        oddFileName = newODDFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__ODD_FILE_NAME, oldODDFileName, oddFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getBAFileName() {
        return baFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setBAFileName(String newBAFileName) {
        String oldBAFileName = baFileName;
        baFileName = newBAFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__BA_FILE_NAME, oldBAFileName, baFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getLSFileName() {
        return lsFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLSFileName(String newLSFileName) {
        String oldLSFileName = lsFileName;
        lsFileName = newLSFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__LS_FILE_NAME, oldLSFileName, lsFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getLSGSFileName() {
        return lsgsFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setLSGSFileName(String newLSGSFileName) {
        String oldLSGSFileName = lsgsFileName;
        lsgsFileName = newLSGSFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__LSGS_FILE_NAME, oldLSGSFileName, lsgsFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getCBFileId() {
        return cbFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCBFileId(long newCBFileId) {
        long oldCBFileId = cbFileId;
        cbFileId = newCBFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__CB_FILE_ID, oldCBFileId, cbFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public long getSSFileId() {
        return ssFileId;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSSFileId(long newSSFileId) {
        long oldSSFileId = ssFileId;
        ssFileId = newSSFileId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__SS_FILE_ID, oldSSFileId, ssFileId));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCB() {
        return cb;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCB(String newCB) {
        String oldCB = cb;
        cb = newCB;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__CB, oldCB, cb));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getSS() {
        return ss;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSS(String newSS) {
        String oldSS = ss;
        ss = newSS;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__SS, oldSS, ss));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getCBFileName() {
        return cbFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCBFileName(String newCBFileName) {
        String oldCBFileName = cbFileName;
        cbFileName = newCBFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__CB_FILE_NAME, oldCBFileName, cbFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getSSFileName() {
        return ssFileName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSSFileName(String newSSFileName) {
        String oldSSFileName = ssFileName;
        ssFileName = newSSFileName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, SCDPackage.SCD_ROOT__SS_FILE_NAME, oldSSFileName, ssFileName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case SCDPackage.SCD_ROOT__OD_FILE_ID:
            return getODFileId();
        case SCDPackage.SCD_ROOT__ODC_FILE_ID:
            return getODcFileId();
        case SCDPackage.SCD_ROOT__ODD_FILE_ID:
            return getODDFileId();
        case SCDPackage.SCD_ROOT__BA_FILE_ID:
            return getBAFileId();
        case SCDPackage.SCD_ROOT__TS_DC_FILE_ID:
            return getTSDcFileId();
        case SCDPackage.SCD_ROOT__TS_DGS_FILE_ID:
            return getTSDgsFileId();
        case SCDPackage.SCD_ROOT__TS_DP_FILE_ID:
            return getTSDpFileId();
        case SCDPackage.SCD_ROOT__BGS_FILE_ID:
            return getBgsFileId();
        case SCDPackage.SCD_ROOT__BP_FILE_ID:
            return getBpFileId();
        case SCDPackage.SCD_ROOT__LSGS_FILE_ID:
            return getLSGSFileId();
        case SCDPackage.SCD_ROOT__LS_FILE_ID:
            return getLSFileId();
        case SCDPackage.SCD_ROOT__CSGS_FILE_ID:
            return getCSGSFileId();
        case SCDPackage.SCD_ROOT__CS_FILE_ID:
            return getCSFileId();
        case SCDPackage.SCD_ROOT__OD:
            return getOD();
        case SCDPackage.SCD_ROOT__ODC:
            return getODC();
        case SCDPackage.SCD_ROOT__BA:
            return getBA();
        case SCDPackage.SCD_ROOT__TSDC:
            return getTSDC();
        case SCDPackage.SCD_ROOT__TSDGS:
            return getTSDGS();
        case SCDPackage.SCD_ROOT__TSDP:
            return getTSDP();
        case SCDPackage.SCD_ROOT__BGS:
            return getBGS();
        case SCDPackage.SCD_ROOT__BP:
            return getBP();
        case SCDPackage.SCD_ROOT__LSGS:
            return getLSGS();
        case SCDPackage.SCD_ROOT__LS:
            return getLS();
        case SCDPackage.SCD_ROOT__CSGS:
            return getCSGS();
        case SCDPackage.SCD_ROOT__CS:
            return getCS();
        case SCDPackage.SCD_ROOT__ODD:
            return getODD();
        case SCDPackage.SCD_ROOT__CS_FILE_NAME:
            return getCSFileName();
        case SCDPackage.SCD_ROOT__TSDC_FILE_NAME:
            return getTSDCFileName();
        case SCDPackage.SCD_ROOT__BP_FILE_NAME:
            return getBPFileName();
        case SCDPackage.SCD_ROOT__TSDP_FILE_NAME:
            return getTSDPFileName();
        case SCDPackage.SCD_ROOT__CSGS_FILE_NAME:
            return getCSGSFileName();
        case SCDPackage.SCD_ROOT__BGS_FILE_NAME:
            return getBGSFileName();
        case SCDPackage.SCD_ROOT__TSDGS_FILE_NAME:
            return getTSDGSFileName();
        case SCDPackage.SCD_ROOT__ODC_FILE_NAME:
            return getODCFileName();
        case SCDPackage.SCD_ROOT__OD_FILE_NAME:
            return getODFileName();
        case SCDPackage.SCD_ROOT__ODD_FILE_NAME:
            return getODDFileName();
        case SCDPackage.SCD_ROOT__BA_FILE_NAME:
            return getBAFileName();
        case SCDPackage.SCD_ROOT__LS_FILE_NAME:
            return getLSFileName();
        case SCDPackage.SCD_ROOT__LSGS_FILE_NAME:
            return getLSGSFileName();
        case SCDPackage.SCD_ROOT__CB_FILE_ID:
            return getCBFileId();
        case SCDPackage.SCD_ROOT__SS_FILE_ID:
            return getSSFileId();
        case SCDPackage.SCD_ROOT__CB:
            return getCB();
        case SCDPackage.SCD_ROOT__SS:
            return getSS();
        case SCDPackage.SCD_ROOT__CB_FILE_NAME:
            return getCBFileName();
        case SCDPackage.SCD_ROOT__SS_FILE_NAME:
            return getSSFileName();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case SCDPackage.SCD_ROOT__OD_FILE_ID:
            setODFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__ODC_FILE_ID:
            setODcFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__ODD_FILE_ID:
            setODDFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__BA_FILE_ID:
            setBAFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__TS_DC_FILE_ID:
            setTSDcFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__TS_DGS_FILE_ID:
            setTSDgsFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__TS_DP_FILE_ID:
            setTSDpFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__BGS_FILE_ID:
            setBgsFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__BP_FILE_ID:
            setBpFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__LSGS_FILE_ID:
            setLSGSFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__LS_FILE_ID:
            setLSFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__CSGS_FILE_ID:
            setCSGSFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__CS_FILE_ID:
            setCSFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__OD:
            setOD((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__ODC:
            setODC((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__BA:
            setBA((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__TSDC:
            setTSDC((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__TSDGS:
            setTSDGS((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__TSDP:
            setTSDP((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__BGS:
            setBGS((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__BP:
            setBP((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__LSGS:
            setLSGS((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__LS:
            setLS((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__CSGS:
            setCSGS((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__CS:
            setCS((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__ODD:
            setODD((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__CS_FILE_NAME:
            setCSFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__TSDC_FILE_NAME:
            setTSDCFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__BP_FILE_NAME:
            setBPFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__TSDP_FILE_NAME:
            setTSDPFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__CSGS_FILE_NAME:
            setCSGSFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__BGS_FILE_NAME:
            setBGSFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__TSDGS_FILE_NAME:
            setTSDGSFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__ODC_FILE_NAME:
            setODCFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__OD_FILE_NAME:
            setODFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__ODD_FILE_NAME:
            setODDFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__BA_FILE_NAME:
            setBAFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__LS_FILE_NAME:
            setLSFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__LSGS_FILE_NAME:
            setLSGSFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__CB_FILE_ID:
            setCBFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__SS_FILE_ID:
            setSSFileId((Long) newValue);
            return;
        case SCDPackage.SCD_ROOT__CB:
            setCB((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__SS:
            setSS((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__CB_FILE_NAME:
            setCBFileName((String) newValue);
            return;
        case SCDPackage.SCD_ROOT__SS_FILE_NAME:
            setSSFileName((String) newValue);
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
        case SCDPackage.SCD_ROOT__OD_FILE_ID:
            setODFileId(OD_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__ODC_FILE_ID:
            setODcFileId(ODC_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__ODD_FILE_ID:
            setODDFileId(ODD_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__BA_FILE_ID:
            setBAFileId(BA_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__TS_DC_FILE_ID:
            setTSDcFileId(TS_DC_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__TS_DGS_FILE_ID:
            setTSDgsFileId(TS_DGS_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__TS_DP_FILE_ID:
            setTSDpFileId(TS_DP_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__BGS_FILE_ID:
            setBgsFileId(BGS_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__BP_FILE_ID:
            setBpFileId(BP_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__LSGS_FILE_ID:
            setLSGSFileId(LSGS_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__LS_FILE_ID:
            setLSFileId(LS_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__CSGS_FILE_ID:
            setCSGSFileId(CSGS_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__CS_FILE_ID:
            setCSFileId(CS_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__OD:
            setOD(OD_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__ODC:
            setODC(ODC_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__BA:
            setBA(BA_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__TSDC:
            setTSDC(TSDC_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__TSDGS:
            setTSDGS(TSDGS_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__TSDP:
            setTSDP(TSDP_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__BGS:
            setBGS(BGS_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__BP:
            setBP(BP_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__LSGS:
            setLSGS(LSGS_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__LS:
            setLS(LS_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__CSGS:
            setCSGS(CSGS_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__CS:
            setCS(CS_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__ODD:
            setODD(ODD_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__CS_FILE_NAME:
            setCSFileName(CS_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__TSDC_FILE_NAME:
            setTSDCFileName(TSDC_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__BP_FILE_NAME:
            setBPFileName(BP_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__TSDP_FILE_NAME:
            setTSDPFileName(TSDP_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__CSGS_FILE_NAME:
            setCSGSFileName(CSGS_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__BGS_FILE_NAME:
            setBGSFileName(BGS_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__TSDGS_FILE_NAME:
            setTSDGSFileName(TSDGS_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__ODC_FILE_NAME:
            setODCFileName(ODC_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__OD_FILE_NAME:
            setODFileName(OD_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__ODD_FILE_NAME:
            setODDFileName(ODD_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__BA_FILE_NAME:
            setBAFileName(BA_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__LS_FILE_NAME:
            setLSFileName(LS_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__LSGS_FILE_NAME:
            setLSGSFileName(LSGS_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__CB_FILE_ID:
            setCBFileId(CB_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__SS_FILE_ID:
            setSSFileId(SS_FILE_ID_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__CB:
            setCB(CB_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__SS:
            setSS(SS_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__CB_FILE_NAME:
            setCBFileName(CB_FILE_NAME_EDEFAULT);
            return;
        case SCDPackage.SCD_ROOT__SS_FILE_NAME:
            setSSFileName(SS_FILE_NAME_EDEFAULT);
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
        case SCDPackage.SCD_ROOT__OD_FILE_ID:
            return odFileId != OD_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__ODC_FILE_ID:
            return oDcFileId != ODC_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__ODD_FILE_ID:
            return oddFileId != ODD_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__BA_FILE_ID:
            return baFileId != BA_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__TS_DC_FILE_ID:
            return tsDcFileId != TS_DC_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__TS_DGS_FILE_ID:
            return tsDgsFileId != TS_DGS_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__TS_DP_FILE_ID:
            return tsDpFileId != TS_DP_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__BGS_FILE_ID:
            return bgsFileId != BGS_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__BP_FILE_ID:
            return bpFileId != BP_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__LSGS_FILE_ID:
            return lsgsFileId != LSGS_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__LS_FILE_ID:
            return lsFileId != LS_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__CSGS_FILE_ID:
            return csgsFileId != CSGS_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__CS_FILE_ID:
            return csFileId != CS_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__OD:
            return OD_EDEFAULT == null ? od != null : !OD_EDEFAULT.equals(od);
        case SCDPackage.SCD_ROOT__ODC:
            return ODC_EDEFAULT == null ? odc != null : !ODC_EDEFAULT.equals(odc);
        case SCDPackage.SCD_ROOT__BA:
            return BA_EDEFAULT == null ? ba != null : !BA_EDEFAULT.equals(ba);
        case SCDPackage.SCD_ROOT__TSDC:
            return TSDC_EDEFAULT == null ? tsdc != null : !TSDC_EDEFAULT.equals(tsdc);
        case SCDPackage.SCD_ROOT__TSDGS:
            return TSDGS_EDEFAULT == null ? tsdgs != null : !TSDGS_EDEFAULT.equals(tsdgs);
        case SCDPackage.SCD_ROOT__TSDP:
            return TSDP_EDEFAULT == null ? tsdp != null : !TSDP_EDEFAULT.equals(tsdp);
        case SCDPackage.SCD_ROOT__BGS:
            return BGS_EDEFAULT == null ? bgs != null : !BGS_EDEFAULT.equals(bgs);
        case SCDPackage.SCD_ROOT__BP:
            return BP_EDEFAULT == null ? bp != null : !BP_EDEFAULT.equals(bp);
        case SCDPackage.SCD_ROOT__LSGS:
            return LSGS_EDEFAULT == null ? lsgs != null : !LSGS_EDEFAULT.equals(lsgs);
        case SCDPackage.SCD_ROOT__LS:
            return LS_EDEFAULT == null ? ls != null : !LS_EDEFAULT.equals(ls);
        case SCDPackage.SCD_ROOT__CSGS:
            return CSGS_EDEFAULT == null ? csgs != null : !CSGS_EDEFAULT.equals(csgs);
        case SCDPackage.SCD_ROOT__CS:
            return CS_EDEFAULT == null ? cs != null : !CS_EDEFAULT.equals(cs);
        case SCDPackage.SCD_ROOT__ODD:
            return ODD_EDEFAULT == null ? odd != null : !ODD_EDEFAULT.equals(odd);
        case SCDPackage.SCD_ROOT__CS_FILE_NAME:
            return CS_FILE_NAME_EDEFAULT == null ? csFileName != null : !CS_FILE_NAME_EDEFAULT.equals(csFileName);
        case SCDPackage.SCD_ROOT__TSDC_FILE_NAME:
            return TSDC_FILE_NAME_EDEFAULT == null ? tsdcFileName != null : !TSDC_FILE_NAME_EDEFAULT.equals(tsdcFileName);
        case SCDPackage.SCD_ROOT__BP_FILE_NAME:
            return BP_FILE_NAME_EDEFAULT == null ? bpFileName != null : !BP_FILE_NAME_EDEFAULT.equals(bpFileName);
        case SCDPackage.SCD_ROOT__TSDP_FILE_NAME:
            return TSDP_FILE_NAME_EDEFAULT == null ? tsdpFileName != null : !TSDP_FILE_NAME_EDEFAULT.equals(tsdpFileName);
        case SCDPackage.SCD_ROOT__CSGS_FILE_NAME:
            return CSGS_FILE_NAME_EDEFAULT == null ? csgsFileName != null : !CSGS_FILE_NAME_EDEFAULT.equals(csgsFileName);
        case SCDPackage.SCD_ROOT__BGS_FILE_NAME:
            return BGS_FILE_NAME_EDEFAULT == null ? bgsFileName != null : !BGS_FILE_NAME_EDEFAULT.equals(bgsFileName);
        case SCDPackage.SCD_ROOT__TSDGS_FILE_NAME:
            return TSDGS_FILE_NAME_EDEFAULT == null ? tsdgsFileName != null : !TSDGS_FILE_NAME_EDEFAULT.equals(tsdgsFileName);
        case SCDPackage.SCD_ROOT__ODC_FILE_NAME:
            return ODC_FILE_NAME_EDEFAULT == null ? odcFileName != null : !ODC_FILE_NAME_EDEFAULT.equals(odcFileName);
        case SCDPackage.SCD_ROOT__OD_FILE_NAME:
            return OD_FILE_NAME_EDEFAULT == null ? odFileName != null : !OD_FILE_NAME_EDEFAULT.equals(odFileName);
        case SCDPackage.SCD_ROOT__ODD_FILE_NAME:
            return ODD_FILE_NAME_EDEFAULT == null ? oddFileName != null : !ODD_FILE_NAME_EDEFAULT.equals(oddFileName);
        case SCDPackage.SCD_ROOT__BA_FILE_NAME:
            return BA_FILE_NAME_EDEFAULT == null ? baFileName != null : !BA_FILE_NAME_EDEFAULT.equals(baFileName);
        case SCDPackage.SCD_ROOT__LS_FILE_NAME:
            return LS_FILE_NAME_EDEFAULT == null ? lsFileName != null : !LS_FILE_NAME_EDEFAULT.equals(lsFileName);
        case SCDPackage.SCD_ROOT__LSGS_FILE_NAME:
            return LSGS_FILE_NAME_EDEFAULT == null ? lsgsFileName != null : !LSGS_FILE_NAME_EDEFAULT.equals(lsgsFileName);
        case SCDPackage.SCD_ROOT__CB_FILE_ID:
            return cbFileId != CB_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__SS_FILE_ID:
            return ssFileId != SS_FILE_ID_EDEFAULT;
        case SCDPackage.SCD_ROOT__CB:
            return CB_EDEFAULT == null ? cb != null : !CB_EDEFAULT.equals(cb);
        case SCDPackage.SCD_ROOT__SS:
            return SS_EDEFAULT == null ? ss != null : !SS_EDEFAULT.equals(ss);
        case SCDPackage.SCD_ROOT__CB_FILE_NAME:
            return CB_FILE_NAME_EDEFAULT == null ? cbFileName != null : !CB_FILE_NAME_EDEFAULT.equals(cbFileName);
        case SCDPackage.SCD_ROOT__SS_FILE_NAME:
            return SS_FILE_NAME_EDEFAULT == null ? ssFileName != null : !SS_FILE_NAME_EDEFAULT.equals(ssFileName);
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
        result.append(" (ODFileId: ");
        result.append(odFileId);
        result.append(", ODcFileId: ");
        result.append(oDcFileId);
        result.append(", ODDFileId: ");
        result.append(oddFileId);
        result.append(", BAFileId: ");
        result.append(baFileId);
        result.append(", TSDcFileId: ");
        result.append(tsDcFileId);
        result.append(", TSDgsFileId: ");
        result.append(tsDgsFileId);
        result.append(", TSDpFileId: ");
        result.append(tsDpFileId);
        result.append(", BgsFileId: ");
        result.append(bgsFileId);
        result.append(", BpFileId: ");
        result.append(bpFileId);
        result.append(", LSGSFileId: ");
        result.append(lsgsFileId);
        result.append(", LSFileId: ");
        result.append(lsFileId);
        result.append(", CSGSFileId: ");
        result.append(csgsFileId);
        result.append(", CSFileId: ");
        result.append(csFileId);
        result.append(", OD: ");
        result.append(od);
        result.append(", ODC: ");
        result.append(odc);
        result.append(", BA: ");
        result.append(ba);
        result.append(", TSDC: ");
        result.append(tsdc);
        result.append(", TSDGS: ");
        result.append(tsdgs);
        result.append(", TSDP: ");
        result.append(tsdp);
        result.append(", BGS: ");
        result.append(bgs);
        result.append(", BP: ");
        result.append(bp);
        result.append(", LSGS: ");
        result.append(lsgs);
        result.append(", LS: ");
        result.append(ls);
        result.append(", CSGS: ");
        result.append(csgs);
        result.append(", CS: ");
        result.append(cs);
        result.append(", ODD: ");
        result.append(odd);
        result.append(", CSFileName: ");
        result.append(csFileName);
        result.append(", TSDCFileName: ");
        result.append(tsdcFileName);
        result.append(", BPFileName: ");
        result.append(bpFileName);
        result.append(", TSDPFileName: ");
        result.append(tsdpFileName);
        result.append(", CSGSFileName: ");
        result.append(csgsFileName);
        result.append(", BGSFileName: ");
        result.append(bgsFileName);
        result.append(", TSDGSFileName: ");
        result.append(tsdgsFileName);
        result.append(", ODCFileName: ");
        result.append(odcFileName);
        result.append(", ODFileName: ");
        result.append(odFileName);
        result.append(", ODDFileName: ");
        result.append(oddFileName);
        result.append(", BAFileName: ");
        result.append(baFileName);
        result.append(", LSFileName: ");
        result.append(lsFileName);
        result.append(", LSGSFileName: ");
        result.append(lsgsFileName);
        result.append(", CBFileId: ");
        result.append(cbFileId);
        result.append(", SSFileId: ");
        result.append(ssFileId);
        result.append(", CB: ");
        result.append(cb);
        result.append(", SS: ");
        result.append(ss);
        result.append(", CBFileName: ");
        result.append(cbFileName);
        result.append(", SSFileName: ");
        result.append(ssFileName);
        result.append(')');
        return result.toString();
    }

} // SCDRootImpl
