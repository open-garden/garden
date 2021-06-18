/**
 */
package com.zipc.garden.model.bps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Instance Pseudo State Type</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 * @see com.zipc.garden.model.bps.BPSPackage#getBPSInstancePseudoStateType()
 * @model
 * @generated
 */
public enum BPSInstancePseudoStateType implements Enumerator {
    /**
     * The '<em><b>Join</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #JOIN_VALUE
     * @generated
     * @ordered
     */
    JOIN(0, "join", "JOIN"),

    /**
     * The '<em><b>Fork</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #FORK_VALUE
     * @generated
     * @ordered
     */
    FORK(1, "fork", "FORK"),

    /**
     * The '<em><b>Junction</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #JUNCTION_VALUE
     * @generated
     * @ordered
     */
    JUNCTION(1, "junction", "JUNCTION"),

    /**
     * The '<em><b>Choice</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #CHOICE_VALUE
     * @generated
     * @ordered
     */
    CHOICE(1, "choice", "CHOICE"),

    /**
     * The '<em><b>Entry Point</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #ENTRY_POINT_VALUE
     * @generated
     * @ordered
     */
    ENTRY_POINT(1, "entryPoint", "ENTORY_POINT"),

    /**
     * The '<em><b>Exit Point</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #EXIT_POINT_VALUE
     * @generated
     * @ordered
     */
    EXIT_POINT(1, "exitPoint", "EXIT_POINT"),

    /**
     * The '<em><b>Terminate</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #TERMINATE_VALUE
     * @generated
     * @ordered
     */
    TERMINATE(1, "terminate", "TERMINATE"),

    /**
     * The '<em><b>Initial</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #INITIAL_VALUE
     * @generated
     * @ordered
     */
    INITIAL(0, "initial", "INITIAL"),

    /**
     * The '<em><b>Final</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #FINAL_VALUE
     * @generated
     * @ordered
     */
    FINAL(0, "final", "FINAL"),

    /**
     * The '<em><b>Deep History</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #DEEP_HISTORY_VALUE
     * @generated
     * @ordered
     */
    DEEP_HISTORY(0, "deepHistory", "DEEP_HISTORY"),

    /**
     * The '<em><b>Shallow History</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #SHALLOW_HISTORY_VALUE
     * @generated
     * @ordered
     */
    SHALLOW_HISTORY(0, "shallowHistory", "SHALLOW_HISTORY"),

    /**
     * The '<em><b>None</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #NONE_VALUE
     * @generated
     * @ordered
     */
    NONE(0, "none", "NONE");

    /**
     * The '<em><b>Join</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #JOIN
     * @model name="join" literal="JOIN"
     * @generated
     * @ordered
     */
    public static final int JOIN_VALUE = 0;

    /**
     * The '<em><b>Fork</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #FORK
     * @model name="fork" literal="FORK"
     * @generated
     * @ordered
     */
    public static final int FORK_VALUE = 1;

    /**
     * The '<em><b>Junction</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #JUNCTION
     * @model name="junction" literal="JUNCTION"
     * @generated
     * @ordered
     */
    public static final int JUNCTION_VALUE = 1;

    /**
     * The '<em><b>Choice</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #CHOICE
     * @model name="choice" literal="CHOICE"
     * @generated
     * @ordered
     */
    public static final int CHOICE_VALUE = 1;

    /**
     * The '<em><b>Entry Point</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #ENTRY_POINT
     * @model name="entryPoint" literal="ENTORY_POINT"
     * @generated
     * @ordered
     */
    public static final int ENTRY_POINT_VALUE = 1;

    /**
     * The '<em><b>Exit Point</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #EXIT_POINT
     * @model name="exitPoint" literal="EXIT_POINT"
     * @generated
     * @ordered
     */
    public static final int EXIT_POINT_VALUE = 1;

    /**
     * The '<em><b>Terminate</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #TERMINATE
     * @model name="terminate" literal="TERMINATE"
     * @generated
     * @ordered
     */
    public static final int TERMINATE_VALUE = 1;

    /**
     * The '<em><b>Initial</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #INITIAL
     * @model name="initial" literal="INITIAL"
     * @generated
     * @ordered
     */
    public static final int INITIAL_VALUE = 0;

    /**
     * The '<em><b>Final</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #FINAL
     * @model name="final" literal="FINAL"
     * @generated
     * @ordered
     */
    public static final int FINAL_VALUE = 0;

    /**
     * The '<em><b>Deep History</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #DEEP_HISTORY
     * @model name="deepHistory" literal="DEEP_HISTORY"
     * @generated
     * @ordered
     */
    public static final int DEEP_HISTORY_VALUE = 0;

    /**
     * The '<em><b>Shallow History</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #SHALLOW_HISTORY
     * @model name="shallowHistory" literal="SHALLOW_HISTORY"
     * @generated
     * @ordered
     */
    public static final int SHALLOW_HISTORY_VALUE = 0;

    /**
     * The '<em><b>None</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #NONE
     * @model name="none" literal="NONE"
     * @generated
     * @ordered
     */
    public static final int NONE_VALUE = 0;

    /**
     * An array of all the '<em><b>Instance Pseudo State Type</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     */
    private static final BPSInstancePseudoStateType[] VALUES_ARRAY = new BPSInstancePseudoStateType[] { JOIN, FORK, JUNCTION, CHOICE, ENTRY_POINT, EXIT_POINT, TERMINATE, INITIAL,
            FINAL, DEEP_HISTORY, SHALLOW_HISTORY, NONE, };

    /**
     * A public read-only list of all the '<em><b>Instance Pseudo State Type</b></em>' enumerators. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    public static final List<BPSInstancePseudoStateType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Instance Pseudo State Type</b></em>' literal with the specified literal value. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static BPSInstancePseudoStateType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            BPSInstancePseudoStateType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Instance Pseudo State Type</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static BPSInstancePseudoStateType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            BPSInstancePseudoStateType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Instance Pseudo State Type</b></em>' literal with the specified integer value. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static BPSInstancePseudoStateType get(int value) {
        switch (value) {
        case JOIN_VALUE:
            return JOIN;
        case FORK_VALUE:
            return FORK;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private BPSInstancePseudoStateType(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getValue() {
        return value;
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
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} // BPSInstancePseudoStateType
