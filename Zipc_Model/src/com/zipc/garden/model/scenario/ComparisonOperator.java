/**
 */
package com.zipc.garden.model.scenario;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Comparison Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.zipc.garden.model.scenario.ScenarioPackage#getComparisonOperator()
 * @model
 * @generated
 */
public enum ComparisonOperator implements Enumerator {
    /**
     * The '<em><b>Equal</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EQUAL_VALUE
     * @generated
     * @ordered
     */
    EQUAL(0, "Equal", "Equal"),

    /**
     * The '<em><b>Not Equal</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NOT_EQUAL_VALUE
     * @generated
     * @ordered
     */
    NOT_EQUAL(1, "NotEqual", "NotEqual"),

    /**
     * The '<em><b>Greater Than</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #GREATER_THAN_VALUE
     * @generated
     * @ordered
     */
    GREATER_THAN(2, "GreaterThan", "GreaterThan"),

    /**
     * The '<em><b>Equal Greater Than</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EQUAL_GREATER_THAN_VALUE
     * @generated
     * @ordered
     */
    EQUAL_GREATER_THAN(3, "EqualGreaterThan", "EqualGreaterThan"),

    /**
     * The '<em><b>Equal Lesser Than</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EQUAL_LESSER_THAN_VALUE
     * @generated
     * @ordered
     */
    EQUAL_LESSER_THAN(4, "EqualLesserThan", "EqualLesserThan"),

    /**
     * The '<em><b>Lesser Than</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LESSER_THAN_VALUE
     * @generated
     * @ordered
     */
    LESSER_THAN(5, "LesserThan", "LesserThan"),

    /**
     * The '<em><b>RANGE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RANGE_VALUE
     * @generated
     * @ordered
     */
    RANGE(6, "RANGE", "RANGE");

    /**
     * The '<em><b>Equal</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EQUAL
     * @model name="Equal"
     * @generated
     * @ordered
     */
    public static final int EQUAL_VALUE = 0;

    /**
     * The '<em><b>Not Equal</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NOT_EQUAL
     * @model name="NotEqual"
     * @generated
     * @ordered
     */
    public static final int NOT_EQUAL_VALUE = 1;

    /**
     * The '<em><b>Greater Than</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #GREATER_THAN
     * @model name="GreaterThan"
     * @generated
     * @ordered
     */
    public static final int GREATER_THAN_VALUE = 2;

    /**
     * The '<em><b>Equal Greater Than</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EQUAL_GREATER_THAN
     * @model name="EqualGreaterThan"
     * @generated
     * @ordered
     */
    public static final int EQUAL_GREATER_THAN_VALUE = 3;

    /**
     * The '<em><b>Equal Lesser Than</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EQUAL_LESSER_THAN
     * @model name="EqualLesserThan"
     * @generated
     * @ordered
     */
    public static final int EQUAL_LESSER_THAN_VALUE = 4;

    /**
     * The '<em><b>Lesser Than</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LESSER_THAN
     * @model name="LesserThan"
     * @generated
     * @ordered
     */
    public static final int LESSER_THAN_VALUE = 5;

    /**
     * The '<em><b>RANGE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RANGE
     * @model
     * @generated
     * @ordered
     */
    public static final int RANGE_VALUE = 6;

    /**
     * An array of all the '<em><b>Comparison Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ComparisonOperator[] VALUES_ARRAY =
        new ComparisonOperator[] {
            EQUAL,
            NOT_EQUAL,
            GREATER_THAN,
            EQUAL_GREATER_THAN,
            EQUAL_LESSER_THAN,
            LESSER_THAN,
            RANGE,
        };

    /**
     * A public read-only list of all the '<em><b>Comparison Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<ComparisonOperator> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Comparison Operator</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ComparisonOperator get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ComparisonOperator result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Comparison Operator</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ComparisonOperator getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ComparisonOperator result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Comparison Operator</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ComparisonOperator get(int value) {
        switch (value) {
            case EQUAL_VALUE: return EQUAL;
            case NOT_EQUAL_VALUE: return NOT_EQUAL;
            case GREATER_THAN_VALUE: return GREATER_THAN;
            case EQUAL_GREATER_THAN_VALUE: return EQUAL_GREATER_THAN;
            case EQUAL_LESSER_THAN_VALUE: return EQUAL_LESSER_THAN;
            case LESSER_THAN_VALUE: return LESSER_THAN;
            case RANGE_VALUE: return RANGE;
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
    private ComparisonOperator(int value, String name, String literal) {
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
    
} //ComparisonOperator
