/**
 */
package com.zipc.garden.model.scd;

import com.zipc.garden.model.core.COREPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.zipc.garden.model.scd.SCDFactory
 * @model kind="package"
 * @generated
 */
public interface SCDPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "scd";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://com.zipc.garden.scd";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "SCD";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    SCDPackage eINSTANCE = com.zipc.garden.model.scd.impl.SCDPackageImpl.init();

    /**
     * The meta object id for the '{@link com.zipc.garden.model.scd.impl.SCDRootImpl <em>Root</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @see com.zipc.garden.model.scd.impl.SCDRootImpl
     * @see com.zipc.garden.model.scd.impl.SCDPackageImpl#getSCDRoot()
     * @generated
     */
    int SCD_ROOT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__ID = COREPackage.ABSTRACT_ROOT_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Refs</b></em>' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__REFS = COREPackage.ABSTRACT_ROOT_ELEMENT__REFS;

    /**
     * The feature id for the '<em><b>OD File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__OD_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>ODc File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__ODC_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>ODD File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__ODD_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>BA File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__BA_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>TS Dc File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__TS_DC_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>TS Dgs File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__TS_DGS_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>TS Dp File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__TS_DP_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Bgs File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__BGS_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Bp File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__BP_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>LSGS File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__LSGS_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>LS File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__LS_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>CSGS File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__CSGS_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>CS File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__CS_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>OD</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__OD = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>ODC</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__ODC = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>BA</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__BA = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 15;

    /**
     * The feature id for the '<em><b>TSDC</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__TSDC = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 16;

    /**
     * The feature id for the '<em><b>TSDGS</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__TSDGS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 17;

    /**
     * The feature id for the '<em><b>TSDP</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__TSDP = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 18;

    /**
     * The feature id for the '<em><b>BGS</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__BGS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 19;

    /**
     * The feature id for the '<em><b>BP</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__BP = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 20;

    /**
     * The feature id for the '<em><b>LSGS</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__LSGS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 21;

    /**
     * The feature id for the '<em><b>LS</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__LS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 22;

    /**
     * The feature id for the '<em><b>CSGS</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__CSGS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 23;

    /**
     * The feature id for the '<em><b>CS</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__CS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 24;

    /**
     * The feature id for the '<em><b>ODD</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__ODD = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 25;

    /**
     * The feature id for the '<em><b>CS File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__CS_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 26;

    /**
     * The feature id for the '<em><b>TSDC File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__TSDC_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 27;

    /**
     * The feature id for the '<em><b>BP File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__BP_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 28;

    /**
     * The feature id for the '<em><b>TSDP File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__TSDP_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 29;

    /**
     * The feature id for the '<em><b>CSGS File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__CSGS_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 30;

    /**
     * The feature id for the '<em><b>BGS File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__BGS_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 31;

    /**
     * The feature id for the '<em><b>TSDGS File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__TSDGS_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 32;

    /**
     * The feature id for the '<em><b>ODC File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__ODC_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 33;

    /**
     * The feature id for the '<em><b>OD File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__OD_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 34;

    /**
     * The feature id for the '<em><b>ODD File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__ODD_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 35;

    /**
     * The feature id for the '<em><b>BA File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__BA_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 36;

    /**
     * The feature id for the '<em><b>LS File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__LS_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 37;

    /**
     * The feature id for the '<em><b>LSGS File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__LSGS_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 38;

    /**
     * The feature id for the '<em><b>CB File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__CB_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 39;

    /**
     * The feature id for the '<em><b>SS File Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__SS_FILE_ID = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 40;

    /**
     * The feature id for the '<em><b>CB</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__CB = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 41;

    /**
     * The feature id for the '<em><b>SS</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__SS = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 42;

    /**
     * The feature id for the '<em><b>CB File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__CB_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 43;

    /**
     * The feature id for the '<em><b>SS File Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT__SS_FILE_NAME = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 44;

    /**
     * The number of structural features of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT_FEATURE_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_FEATURE_COUNT + 45;

    /**
     * The number of operations of the '<em>Root</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCD_ROOT_OPERATION_COUNT = COREPackage.ABSTRACT_ROOT_ELEMENT_OPERATION_COUNT + 0;

    /**
     * Returns the meta object for class '{@link com.zipc.garden.model.scd.SCDRoot <em>Root</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @return the meta object for class '<em>Root</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot
     * @generated
     */
    EClass getSCDRoot();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getODFileId <em>OD File Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>OD File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getODFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_ODFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getODcFileId <em>ODc File Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>ODc File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getODcFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_ODcFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getODDFileId <em>ODD File Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>ODD File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getODDFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_ODDFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getBAFileId <em>BA File Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>BA File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getBAFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_BAFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getTSDcFileId <em>TS Dc File
     * Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>TS Dc File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getTSDcFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_TSDcFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getTSDgsFileId <em>TS Dgs File
     * Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>TS Dgs File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getTSDgsFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_TSDgsFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getTSDpFileId <em>TS Dp File
     * Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>TS Dp File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getTSDpFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_TSDpFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getBgsFileId <em>Bgs File Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bgs File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getBgsFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_BgsFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getBpFileId <em>Bp File Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Bp File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getBpFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_BpFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getLSGSFileId <em>LSGS File
     * Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>LSGS File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getLSGSFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_LSGSFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getLSFileId <em>LS File Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>LS File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getLSFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_LSFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getCSGSFileId <em>CSGS File
     * Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>CSGS File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getCSGSFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_CSGSFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getCSFileId <em>CS File Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>CS File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getCSFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_CSFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getOD <em>OD</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>OD</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getOD()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_OD();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getODC <em>ODC</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>ODC</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getODC()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_ODC();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getBA <em>BA</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>BA</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getBA()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_BA();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getTSDC <em>TSDC</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>TSDC</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getTSDC()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_TSDC();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getTSDGS <em>TSDGS</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>TSDGS</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getTSDGS()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_TSDGS();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getTSDP <em>TSDP</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>TSDP</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getTSDP()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_TSDP();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getBGS <em>BGS</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>BGS</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getBGS()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_BGS();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getBP <em>BP</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>BP</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getBP()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_BP();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getLSGS <em>LSGS</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>LSGS</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getLSGS()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_LSGS();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getLS <em>LS</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>LS</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getLS()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_LS();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getCSGS <em>CSGS</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>CSGS</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getCSGS()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_CSGS();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getCS <em>CS</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>CS</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getCS()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_CS();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getODD <em>ODD</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>ODD</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getODD()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_ODD();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getCSFileName <em>CS File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>CS File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getCSFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_CSFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getTSDCFileName <em>TSDC File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>TSDC File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getTSDCFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_TSDCFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getBPFileName <em>BP File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>BP File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getBPFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_BPFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getTSDPFileName <em>TSDP File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>TSDP File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getTSDPFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_TSDPFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getCSGSFileName <em>CSGS File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>CSGS File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getCSGSFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_CSGSFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getBGSFileName <em>BGS File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>BGS File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getBGSFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_BGSFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getTSDGSFileName <em>TSDGS File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>TSDGS File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getTSDGSFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_TSDGSFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getODCFileName <em>ODC File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>ODC File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getODCFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_ODCFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getODFileName <em>OD File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>OD File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getODFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_ODFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getODDFileName <em>ODD File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>ODD File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getODDFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_ODDFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getBAFileName <em>BA File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>BA File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getBAFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_BAFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getLSFileName <em>LS File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>LS File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getLSFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_LSFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getLSGSFileName <em>LSGS File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>LSGS File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getLSGSFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_LSGSFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getCBFileId <em>CB File Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>CB File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getCBFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_CBFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getSSFileId <em>SS File Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>SS File Id</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getSSFileId()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_SSFileId();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getCB <em>CB</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>CB</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getCB()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_CB();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getSS <em>SS</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>SS</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getSS()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_SS();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getCBFileName <em>CB File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>CB File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getCBFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_CBFileName();

    /**
     * Returns the meta object for the attribute '{@link com.zipc.garden.model.scd.SCDRoot#getSSFileName <em>SS File
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>SS File Name</em>'.
     * @see com.zipc.garden.model.scd.SCDRoot#getSSFileName()
     * @see #getSCDRoot()
     * @generated
     */
    EAttribute getSCDRoot_SSFileName();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    SCDFactory getSCDFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link com.zipc.garden.model.scd.impl.SCDRootImpl <em>Root</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * @see com.zipc.garden.model.scd.impl.SCDRootImpl
         * @see com.zipc.garden.model.scd.impl.SCDPackageImpl#getSCDRoot()
         * @generated
         */
        EClass SCD_ROOT = eINSTANCE.getSCDRoot();

        /**
         * The meta object literal for the '<em><b>OD File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__OD_FILE_ID = eINSTANCE.getSCDRoot_ODFileId();

        /**
         * The meta object literal for the '<em><b>ODc File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__ODC_FILE_ID = eINSTANCE.getSCDRoot_ODcFileId();

        /**
         * The meta object literal for the '<em><b>ODD File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__ODD_FILE_ID = eINSTANCE.getSCDRoot_ODDFileId();

        /**
         * The meta object literal for the '<em><b>BA File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__BA_FILE_ID = eINSTANCE.getSCDRoot_BAFileId();

        /**
         * The meta object literal for the '<em><b>TS Dc File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__TS_DC_FILE_ID = eINSTANCE.getSCDRoot_TSDcFileId();

        /**
         * The meta object literal for the '<em><b>TS Dgs File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__TS_DGS_FILE_ID = eINSTANCE.getSCDRoot_TSDgsFileId();

        /**
         * The meta object literal for the '<em><b>TS Dp File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__TS_DP_FILE_ID = eINSTANCE.getSCDRoot_TSDpFileId();

        /**
         * The meta object literal for the '<em><b>Bgs File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__BGS_FILE_ID = eINSTANCE.getSCDRoot_BgsFileId();

        /**
         * The meta object literal for the '<em><b>Bp File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__BP_FILE_ID = eINSTANCE.getSCDRoot_BpFileId();

        /**
         * The meta object literal for the '<em><b>LSGS File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__LSGS_FILE_ID = eINSTANCE.getSCDRoot_LSGSFileId();

        /**
         * The meta object literal for the '<em><b>LS File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__LS_FILE_ID = eINSTANCE.getSCDRoot_LSFileId();

        /**
         * The meta object literal for the '<em><b>CSGS File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__CSGS_FILE_ID = eINSTANCE.getSCDRoot_CSGSFileId();

        /**
         * The meta object literal for the '<em><b>CS File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__CS_FILE_ID = eINSTANCE.getSCDRoot_CSFileId();

        /**
         * The meta object literal for the '<em><b>OD</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__OD = eINSTANCE.getSCDRoot_OD();

        /**
         * The meta object literal for the '<em><b>ODC</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCD_ROOT__ODC = eINSTANCE.getSCDRoot_ODC();

        /**
         * The meta object literal for the '<em><b>BA</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__BA = eINSTANCE.getSCDRoot_BA();

        /**
         * The meta object literal for the '<em><b>TSDC</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCD_ROOT__TSDC = eINSTANCE.getSCDRoot_TSDC();

        /**
         * The meta object literal for the '<em><b>TSDGS</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCD_ROOT__TSDGS = eINSTANCE.getSCDRoot_TSDGS();

        /**
         * The meta object literal for the '<em><b>TSDP</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCD_ROOT__TSDP = eINSTANCE.getSCDRoot_TSDP();

        /**
         * The meta object literal for the '<em><b>BGS</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCD_ROOT__BGS = eINSTANCE.getSCDRoot_BGS();

        /**
         * The meta object literal for the '<em><b>BP</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__BP = eINSTANCE.getSCDRoot_BP();

        /**
         * The meta object literal for the '<em><b>LSGS</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCD_ROOT__LSGS = eINSTANCE.getSCDRoot_LSGS();

        /**
         * The meta object literal for the '<em><b>LS</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__LS = eINSTANCE.getSCDRoot_LS();

        /**
         * The meta object literal for the '<em><b>CSGS</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCD_ROOT__CSGS = eINSTANCE.getSCDRoot_CSGS();

        /**
         * The meta object literal for the '<em><b>CS</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__CS = eINSTANCE.getSCDRoot_CS();

        /**
         * The meta object literal for the '<em><b>ODD</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * @generated
         */
        EAttribute SCD_ROOT__ODD = eINSTANCE.getSCDRoot_ODD();

        /**
         * The meta object literal for the '<em><b>CS File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__CS_FILE_NAME = eINSTANCE.getSCDRoot_CSFileName();

        /**
         * The meta object literal for the '<em><b>TSDC File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__TSDC_FILE_NAME = eINSTANCE.getSCDRoot_TSDCFileName();

        /**
         * The meta object literal for the '<em><b>BP File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__BP_FILE_NAME = eINSTANCE.getSCDRoot_BPFileName();

        /**
         * The meta object literal for the '<em><b>TSDP File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__TSDP_FILE_NAME = eINSTANCE.getSCDRoot_TSDPFileName();

        /**
         * The meta object literal for the '<em><b>CSGS File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__CSGS_FILE_NAME = eINSTANCE.getSCDRoot_CSGSFileName();

        /**
         * The meta object literal for the '<em><b>BGS File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__BGS_FILE_NAME = eINSTANCE.getSCDRoot_BGSFileName();

        /**
         * The meta object literal for the '<em><b>TSDGS File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__TSDGS_FILE_NAME = eINSTANCE.getSCDRoot_TSDGSFileName();

        /**
         * The meta object literal for the '<em><b>ODC File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__ODC_FILE_NAME = eINSTANCE.getSCDRoot_ODCFileName();

        /**
         * The meta object literal for the '<em><b>OD File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__OD_FILE_NAME = eINSTANCE.getSCDRoot_ODFileName();

        /**
         * The meta object literal for the '<em><b>ODD File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__ODD_FILE_NAME = eINSTANCE.getSCDRoot_ODDFileName();

        /**
         * The meta object literal for the '<em><b>BA File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__BA_FILE_NAME = eINSTANCE.getSCDRoot_BAFileName();

        /**
         * The meta object literal for the '<em><b>LS File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__LS_FILE_NAME = eINSTANCE.getSCDRoot_LSFileName();

        /**
         * The meta object literal for the '<em><b>LSGS File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__LSGS_FILE_NAME = eINSTANCE.getSCDRoot_LSGSFileName();

        /**
         * The meta object literal for the '<em><b>CB File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__CB_FILE_ID = eINSTANCE.getSCDRoot_CBFileId();

        /**
         * The meta object literal for the '<em><b>SS File Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__SS_FILE_ID = eINSTANCE.getSCDRoot_SSFileId();

        /**
         * The meta object literal for the '<em><b>CB</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__CB = eINSTANCE.getSCDRoot_CB();

        /**
         * The meta object literal for the '<em><b>SS</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__SS = eINSTANCE.getSCDRoot_SS();

        /**
         * The meta object literal for the '<em><b>CB File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__CB_FILE_NAME = eINSTANCE.getSCDRoot_CBFileName();

        /**
         * The meta object literal for the '<em><b>SS File Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * @generated
         */
        EAttribute SCD_ROOT__SS_FILE_NAME = eINSTANCE.getSCDRoot_SSFileName();

    }

} // SCDPackage
