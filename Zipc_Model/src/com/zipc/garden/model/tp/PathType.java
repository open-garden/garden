/**
 */
package com.zipc.garden.model.tp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Path Type</b></em>', and utility methods
 * for working with them. <!-- end-user-doc -->
 * @see com.zipc.garden.model.tp.TPPackage#getPathType()
 * @model
 * @generated
 */
public enum PathType implements Enumerator {
    /**
     * The '<em><b>Simple Path</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #SIMPLE_PATH_VALUE
     * @generated
     * @ordered
     */
    SIMPLE_PATH(1, "simplePath", "SIMPLEPATH"),

    /**
     * The '<em><b>Full Path</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #FULL_PATH_VALUE
     * @generated
     * @ordered
     */
    FULL_PATH(0, "fullPath", "FULLPATH");

    /**
     * The '<em><b>Simple Path</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #SIMPLE_PATH
     * @model name="simplePath" literal="SIMPLEPATH"
     * @generated
     * @ordered
     */
    public static final int SIMPLE_PATH_VALUE = 1;

    /**
     * The '<em><b>Full Path</b></em>' literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #FULL_PATH
     * @model name="fullPath" literal="FULLPATH"
     * @generated
     * @ordered
     */
    public static final int FULL_PATH_VALUE = 0;

    /**
     * An array of all the '<em><b>Path Type</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static final PathType[] VALUES_ARRAY = new PathType[] { SIMPLE_PATH, FULL_PATH, };

    /**
     * A public read-only list of all the '<em><b>Path Type</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public static final List<PathType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Path Type</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static PathType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            PathType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Path Type</b></em>' literal with the specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static PathType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            PathType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Path Type</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static PathType get(int value) {
        switch (value) {
        case SIMPLE_PATH_VALUE:
            return SIMPLE_PATH;
        case FULL_PATH_VALUE:
            return FULL_PATH;
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
    private PathType(int value, String name, String literal) {
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

} // PathType
