/**
 */
package com.zipc.garden.model.scenario;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Distance Direction</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getDistanceDirection()
 * @model
 * @generated
 */
public enum DistanceDirection implements Enumerator {
    /**
     * The '<em><b>FORWARD</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FORWARD_VALUE
     * @generated
     * @ordered
     */
    FORWARD(0, "FORWARD", "FORWARD"),

    /**
     * The '<em><b>BACKWARD</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BACKWARD_VALUE
     * @generated
     * @ordered
     */
    BACKWARD(1, "BACKWARD", "BACKWARD");

    /**
     * The '<em><b>FORWARD</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FORWARD
     * @model
     * @generated
     * @ordered
     */
    public static final int FORWARD_VALUE = 0;

    /**
     * The '<em><b>BACKWARD</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BACKWARD
     * @model
     * @generated
     * @ordered
     */
    public static final int BACKWARD_VALUE = 1;

    /**
     * An array of all the '<em><b>Distance Direction</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final DistanceDirection[] VALUES_ARRAY =
        new DistanceDirection[] {
            FORWARD,
            BACKWARD,
        };

    /**
     * A public read-only list of all the '<em><b>Distance Direction</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<DistanceDirection> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Distance Direction</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static DistanceDirection get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            DistanceDirection result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Distance Direction</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static DistanceDirection getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            DistanceDirection result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Distance Direction</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static DistanceDirection get(int value) {
        switch (value) {
            case FORWARD_VALUE: return FORWARD;
            case BACKWARD_VALUE: return BACKWARD;
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
    private DistanceDirection(int value, String name, String literal) {
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
    
} //DistanceDirection
