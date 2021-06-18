package com.zipc.garden.job.tp;

import static org.junit.Assert.assertNotNull;

import java.io.Reader;
import java.io.StringReader;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import com.zipc.garden.model.fmcs.FMCSAndExpression;
import com.zipc.garden.model.fmcs.FMCSConstraint;
import com.zipc.garden.model.fmcs.FMCSImpliesExpression;
import com.zipc.garden.model.fmcs.FMCSMutexExpression;
import com.zipc.garden.model.fmcs.FMCSNotExpression;
import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSOrExpression;
import com.zipc.garden.model.fmcs.FMCSRemovesExpression;
import com.zipc.garden.model.fmcs.FMCSSelectExpression;
import com.zipc.garden.parser.fmc.constraint.ASTRoot;
import com.zipc.garden.parser.fmc.constraint.FMConstraintParser;
import com.zipc.garden.parser.fmc.constraint.ParseException;

@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ASTRoot2FmcsConvertVistorTest {

    @Before
    public void printBefore() {
        System.out.println("------");
    }

    @After
    public void printAfter() {
        System.out.println("------");

    }

    @Test
    public void Test01() {
        System.out.println("Test01");
        Reader in = new StringReader("(A.a1 || 参照B:b.v1) && (!X || mutex(x, y, z)) || C removes Z");
        FMConstraintParser parser = new FMConstraintParser(in);
        try {
            ASTRoot root = parser.Root();
            FMCSConstraint constraint = (FMCSConstraint) root.jjtAccept(new ASTRoot2FmcsConvertVisitor(), root);
            assertNotNull(constraint);
            printFMCS(constraint);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    @Test
    public void Test02() {
        System.out.println("Test02");
        Reader in = new StringReader("(A.a1 || 参照B:\"b.v1\") && (!X || mutex(x, y, z)) || C -> S");
        FMConstraintParser parser = new FMConstraintParser(in);
        try {
            ASTRoot root = parser.Root();
            FMCSConstraint constraint = (FMCSConstraint) root.jjtAccept(new ASTRoot2FmcsConvertVisitor(), root);
            assertNotNull(constraint);
            printFMCS(constraint);
        } catch (ParseException e) {
            System.out.println(e);
        }
    }

    private void printFMCS(Object object) {
        printFMCS(object, 0);
    }

    private void printFMCS(Object object, int hierarchy) {
        StringBuilder indentBuilder = new StringBuilder();
        IntStream.range(0, hierarchy).forEach(i -> indentBuilder.append("  "));
        String indent = indentBuilder.toString();

        if (object instanceof FMCSConstraint) {
            FMCSConstraint constraint = (FMCSConstraint) object;
            System.out.println(indent + "Constraint");
            printFMCS(constraint.getExpression(), hierarchy + 1);
        } else if (object instanceof FMCSImpliesExpression) {
            FMCSImpliesExpression exp = (FMCSImpliesExpression) object;
            System.out.println(indent + "Implies");
            printFMCS(exp.getLeftExpression(), hierarchy + 1);
            printFMCS(exp.getRightExpression(), hierarchy + 1);
        } else if (object instanceof FMCSRemovesExpression) {
            FMCSRemovesExpression exp = (FMCSRemovesExpression) object;
            System.out.println(indent + "Removes");
            printFMCS(exp.getExpression(), hierarchy + 1);
            printFMCS(exp.getOdElement(), hierarchy + 1);
        } else if (object instanceof FMCSMutexExpression) {
            FMCSMutexExpression exp = (FMCSMutexExpression) object;
            System.out.println(indent + "Mutex");
            exp.getOdElements().forEach(element -> printFMCS(element, hierarchy + 1));
        } else if (object instanceof FMCSSelectExpression) {
            FMCSSelectExpression exp = (FMCSSelectExpression) object;
            System.out.println(indent + "Select");
            printFMCS(exp.getOdElement(), hierarchy + 1);
        } else if (object instanceof FMCSAndExpression) {
            FMCSAndExpression exp = (FMCSAndExpression) object;
            System.out.println(indent + "And");
            exp.getExpressions().forEach(expression -> printFMCS(expression, hierarchy + 1));
        } else if (object instanceof FMCSOrExpression) {
            FMCSOrExpression exp = (FMCSOrExpression) object;
            System.out.println(indent + "Or");
            exp.getExpressions().forEach(expression -> printFMCS(expression, hierarchy + 1));
        } else if (object instanceof FMCSNotExpression) {
            FMCSNotExpression exp = (FMCSNotExpression) object;
            System.out.println(indent + "Not");
            printFMCS(exp.getExpression(), hierarchy + 1);
        } else if (object instanceof FMCSODElement) {
            FMCSODElement ode = (FMCSODElement) object;
            System.out.println(indent + "ODElement[" + ode.getFullName() + "]");
        }
    }
}
