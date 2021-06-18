/**
 */
package com.zipc.garden.model.fsm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>DLine Type</b></em>', and utility methods
 * for working with them. <!-- end-user-doc -->
 * @see com.zipc.garden.model.fsm.FSMPackage#getFSMDLineType()
 * @model
 * @generated
 */
public enum FSMDLineType implements Enumerator {
    /**
     * The '<em><b>Simple</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #SIMPLE_VALUE
     * @generated
     * @ordered
     */
    SIMPLE(0, "simple", "SIMPLE"),

    /**
     * The '<em><b>Manhattan</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #MANHATTAN_VALUE
     * @generated
     * @ordered
     */
    MANHATTAN(0, "manhattan", "MANHATTAN");

    /**
     * The '<em><b>Simple</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #SIMPLE
     * @model name="simple" literal="SIMPLE"
     * @generated
     * @ordered
     */
    public static final int SIMPLE_VALUE = 0;

    /**
     * The '<em><b>Manhattan</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #MANHATTAN
     * @model name="manhattan" literal="MANHATTAN"
     * @generated
     * @ordered
     */
    public static final int MANHATTAN_VALUE = 0;

    /**
     * An array of all the '<em><b>DLine Type</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static final FSMDLineType[] VALUES_ARRAY = new FSMDLineType[] { SIMPLE, MANHATTAN, };

    /**
     * A public read-only list of all the '<em><b>DLine Type</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     */
    public static final List<FSMDLineType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>DLine Type</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FSMDLineType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            FSMDLineType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>DLine Type</b></em>' literal with the specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FSMDLineType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            FSMDLineType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>DLine Type</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FSMDLineType get(int value) {
        switch (value) {
        case SIMPLE_VALUE:
            return SIMPLE;
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
    private FSMDLineType(int value, String name, String literal) {
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

} // FSMDLineType
