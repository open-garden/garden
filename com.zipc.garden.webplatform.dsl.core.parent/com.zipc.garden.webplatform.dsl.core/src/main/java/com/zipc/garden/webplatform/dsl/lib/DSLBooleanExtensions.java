package com.zipc.garden.webplatform.dsl.lib;

import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

import com.google.common.annotations.GwtCompatible;

/**
 * This is an extension library for {@link Boolean booleans}.
 */
@GwtCompatible
public class DSLBooleanExtensions {

    /**
     * A logical <code>or</code> (disjunction). This is the equivalent to the java <code>||</code> operator.
     * @param a a boolean value.
     * @param b another boolean value.
     * @return <code>a || b</code>
     */
    @Pure
    @Inline(value = "($1 || $2)", constantExpression = true)
    public static boolean operator_or(boolean a, boolean b) {
        return a || b;
    }

    /**
     * The logical <code>and</code> (conjunction). This is the equivalent to the java <code>&&</code> operator.
     * @param a a boolean value.
     * @param b another boolean value.
     * @return <code>a && b</code>
     */
    @Pure
    @Inline(value = "($1 && $2)", constantExpression = true)
    public static boolean operator_and(boolean a, boolean b) {
        return a && b;
    }

    /**
     * The logical <code>not</code> (negation). This is the equivalent to the java <code>!</code> operator.
     * @param b a boolean value.
     * @return <code>!b</code>
     */
    @Pure
    @Inline(value = "(!$1)", constantExpression = true)
    public static boolean operator_not(boolean b) {
        return !b;
    }
}
