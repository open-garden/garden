/**
 */
package com.zipc.garden.model.tc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Node State</b></em>', and utility methods
 * for working with them. <!-- end-user-doc -->
 * @see com.zipc.garden.model.tc.TCPackage#getTCNodeState()
 * @model
 * @generated
 */
public enum TCNodeState implements Enumerator {
    /**
     * The '<em><b>NEW</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #NEW_VALUE
     * @generated
     * @ordered
     */
    NEW(0, "NEW", "NEW"),

    /**
     * The '<em><b>DELETED</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #DELETED_VALUE
     * @generated
     * @ordered
     */
    DELETED(0, "DELETED", "DELETED"),

    /**
     * The '<em><b>UNCHANGED</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #UNCHANGED_VALUE
     * @generated
     * @ordered
     */
    UNCHANGED(0, "UNCHANGED", "UNCHANGED");

    /**
     * The '<em><b>NEW</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #NEW
     * @model
     * @generated
     * @ordered
     */
    public static final int NEW_VALUE = 0;

    /**
     * The '<em><b>DELETED</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #DELETED
     * @model
     * @generated
     * @ordered
     */
    public static final int DELETED_VALUE = 0;

    /**
     * The '<em><b>UNCHANGED</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #UNCHANGED
     * @model
     * @generated
     * @ordered
     */
    public static final int UNCHANGED_VALUE = 0;

    /**
     * An array of all the '<em><b>Node State</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static final TCNodeState[] VALUES_ARRAY = new TCNodeState[] { NEW, DELETED, UNCHANGED, };

    /**
     * A public read-only list of all the '<em><b>Node State</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * @generated
     */
    public static final List<TCNodeState> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Node State</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TCNodeState get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TCNodeState result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Node State</b></em>' literal with the specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TCNodeState getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TCNodeState result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Node State</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TCNodeState get(int value) {
        switch (value) {
        case NEW_VALUE:
            return NEW;
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
    private TCNodeState(int value, String name, String literal) {
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

} // TCNodeState
