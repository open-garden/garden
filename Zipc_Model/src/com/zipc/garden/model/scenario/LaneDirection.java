/**
 */
package com.zipc.garden.model.scenario;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Lane Direction</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getLaneDirection()
 * @model
 * @generated
 */
public enum LaneDirection implements Enumerator {
    /**
     * The '<em><b>EQUAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EQUAL_VALUE
     * @generated
     * @ordered
     */
    EQUAL(0, "EQUAL", "EQUAL"),

    /**
     * The '<em><b>LEFT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LEFT_VALUE
     * @generated
     * @ordered
     */
    LEFT(1, "LEFT", "LEFT"),

    /**
     * The '<em><b>RIGHT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RIGHT_VALUE
     * @generated
     * @ordered
     */
    RIGHT(2, "RIGHT", "RIGHT");

    /**
     * The '<em><b>EQUAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EQUAL
     * @model
     * @generated
     * @ordered
     */
    public static final int EQUAL_VALUE = 0;

    /**
     * The '<em><b>LEFT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LEFT
     * @model
     * @generated
     * @ordered
     */
    public static final int LEFT_VALUE = 1;

    /**
     * The '<em><b>RIGHT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RIGHT
     * @model
     * @generated
     * @ordered
     */
    public static final int RIGHT_VALUE = 2;

    /**
     * An array of all the '<em><b>Lane Direction</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final LaneDirection[] VALUES_ARRAY =
        new LaneDirection[] {
            EQUAL,
            LEFT,
            RIGHT,
        };

    /**
     * A public read-only list of all the '<em><b>Lane Direction</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<LaneDirection> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Lane Direction</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LaneDirection get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LaneDirection result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Lane Direction</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LaneDirection getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            LaneDirection result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Lane Direction</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LaneDirection get(int value) {
        switch (value) {
            case EQUAL_VALUE: return EQUAL;
            case LEFT_VALUE: return LEFT;
            case RIGHT_VALUE: return RIGHT;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private LaneDirection(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public int getValue() {
      return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getName() {
      return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getLiteral() {
      return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }
    
} //LaneDirection
