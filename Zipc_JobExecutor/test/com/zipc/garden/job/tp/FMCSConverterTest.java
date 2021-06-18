package com.zipc.garden.job.tp;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fmc.FMCConstraint;
import com.zipc.garden.model.fmc.FMCFactory;
import com.zipc.garden.model.fmc.FMCRoot;
import com.zipc.garden.model.fmcs.FMCSAndExpression;
import com.zipc.garden.model.fmcs.FMCSConstraint;
import com.zipc.garden.model.fmcs.FMCSImpliesExpression;
import com.zipc.garden.model.fmcs.FMCSMutexExpression;
import com.zipc.garden.model.fmcs.FMCSNotExpression;
import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSOrExpression;
import com.zipc.garden.model.fmcs.FMCSRemovesExpression;
import com.zipc.garden.model.fmcs.FMCSRoot;
import com.zipc.garden.model.fmcs.FMCSSelectExpression;
import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FMCSConverterTest {

    private TCRoot odRoot;

    private FMCRoot fmcRoot;

    @Before
    public void printBefore() {
        System.out.println("------");
    }

    @After
    public void printAfter() {
        System.out.println("------");

    }

    private void createTC() {
        // [File ID] in database
        // 0 : od.fm
        // 1 : weather.fm
        // 2 : temp.fm

        // TC OD.fm
        odRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode odNodeRoot = TCFactory.eINSTANCE.createTCNode();
        odNodeRoot.setName("OD");
        TCNode odNode1 = TCFactory.eINSTANCE.createTCNode();
        odNode1.setName("気象");
        TCNode odNode11 = TCFactory.eINSTANCE.createTCNode();
        odNode11.setName("天気");
        TCNode odNode111 = TCFactory.eINSTANCE.createTCNode();
        odNode111.setName("晴れ");
        TCNode odNode112 = TCFactory.eINSTANCE.createTCNode();
        odNode112.setName("雨");
        TCNode odNode12 = TCFactory.eINSTANCE.createTCNode();
        odNode12.setName("気温");
        TCNode odNode121 = TCFactory.eINSTANCE.createTCNode();
        odNode121.setName("灼\"熱");
        TCNode odNode122 = TCFactory.eINSTANCE.createTCNode();
        odNode122.setName("通常");
        TCNode odNode2 = TCFactory.eINSTANCE.createTCNode();
        odNode2.setName("土地");
        TCNode odNode21 = TCFactory.eINSTANCE.createTCNode();
        odNode21.setName("草原");
        TCNode odNode22 = TCFactory.eINSTANCE.createTCNode();
        odNode22.setName("砂. \\漠");

        // TC OD.fm Syntax Tree
        odRoot.getRootNodes().add(odNodeRoot);
        odNodeRoot.setChildType(ChildType.AND);
        odNodeRoot.getChildren().add(odNode1);
        odNodeRoot.getChildren().add(odNode2);
        odNode1.setChildType(ChildType.AND);
        odNode1.getChildren().add(odNode11);
        odNode1.getChildren().add(odNode12);
        odNode11.setChildType(ChildType.XOR);
        odNode11.getChildren().add(odNode111);
        odNode11.getChildren().add(odNode112);
        odNode12.setChildType(ChildType.XOR);
        odNode12.getChildren().add(odNode121);
        odNode12.getChildren().add(odNode122);
        odNode2.setChildType(ChildType.XOR);
        odNode2.getChildren().add(odNode21);
        odNode2.getChildren().add(odNode22);

        // FMC
        fmcRoot = FMCFactory.eINSTANCE.createFMCRoot();
        FMCConstraint fmConstraint = FMCFactory.eINSTANCE.createFMCConstraint();
        fmConstraint.setEnabled(true);
        fmConstraint.setComment("土地が砂漠だったら気温は灼熱である");
        fmConstraint.setConstraint("\"砂. \\\\漠\" -> 気温.\"灼\\\"熱\"");
        fmcRoot.getConstraints().add(fmConstraint);
    }

    @Test
    public void Test01_ReferenceOtherFile() {
        createTC();
        FMCSConverter converter = new FMCSConverter(odRoot, fmcRoot);
        FMCSRoot fmcsRoot = converter.convert();
        String actualText = createSyntaxText(fmcsRoot);
        System.out.println(actualText);
        String expectText = createExpectText();
        assertEquals(expectText, actualText);
    }

    private String createExpectText() {
        StringBuilder builder = new StringBuilder();
        addTextLine(builder, "Constraint");
        addTextLine(builder, "  Implies");
        addTextLine(builder, "    Select");
        addTextLine(builder, "      ODElement[OD.土地.\"砂. \\\\漠\"]");
        addTextLine(builder, "    Select");
        addTextLine(builder, "      ODElement[OD.気象.気温.\"灼\\\"熱\"]");
        return builder.toString();
    }

    private String createSyntaxText(Object object) {
        StringBuilder builder = new StringBuilder();
        printFMCS(object, 0, builder);
        return builder.toString();
    }

    private void printFMCS(Object object, int hierarchy, StringBuilder builder) {
        StringBuilder indentBuilder = new StringBuilder();
        IntStream.range(0, hierarchy).forEach(i -> indentBuilder.append("  "));
        String indent = indentBuilder.toString();

        if (object instanceof FMCSRoot) {
            FMCSRoot root = (FMCSRoot) object;
            root.getConstraints().forEach(constraint -> printFMCS(constraint, hierarchy, builder));
        } else if (object instanceof FMCSConstraint) {
            FMCSConstraint constraint = (FMCSConstraint) object;
            addTextLine(builder, indent + "Constraint");
            printFMCS(constraint.getExpression(), hierarchy + 1, builder);
        } else if (object instanceof FMCSImpliesExpression) {
            FMCSImpliesExpression exp = (FMCSImpliesExpression) object;
            addTextLine(builder, indent + "Implies");
            printFMCS(exp.getLeftExpression(), hierarchy + 1, builder);
            printFMCS(exp.getRightExpression(), hierarchy + 1, builder);
        } else if (object instanceof FMCSRemovesExpression) {
            FMCSRemovesExpression exp = (FMCSRemovesExpression) object;
            addTextLine(builder, indent + "Removes");
            printFMCS(exp.getExpression(), hierarchy + 1, builder);
            printFMCS(exp.getOdElement(), hierarchy + 1, builder);
        } else if (object instanceof FMCSMutexExpression) {
            FMCSMutexExpression exp = (FMCSMutexExpression) object;
            addTextLine(builder, indent + "Mutex");
            exp.getOdElements().forEach(element -> printFMCS(element, hierarchy + 1, builder));
        } else if (object instanceof FMCSSelectExpression) {
            FMCSSelectExpression exp = (FMCSSelectExpression) object;
            addTextLine(builder, indent + "Select");
            printFMCS(exp.getOdElement(), hierarchy + 1, builder);
        } else if (object instanceof FMCSAndExpression) {
            FMCSAndExpression exp = (FMCSAndExpression) object;
            addTextLine(builder, indent + "And");
            exp.getExpressions().forEach(expression -> printFMCS(expression, hierarchy + 1, builder));
        } else if (object instanceof FMCSOrExpression) {
            FMCSOrExpression exp = (FMCSOrExpression) object;
            addTextLine(builder, indent + "Or");
            exp.getExpressions().forEach(expression -> printFMCS(expression, hierarchy + 1, builder));
        } else if (object instanceof FMCSNotExpression) {
            FMCSNotExpression exp = (FMCSNotExpression) object;
            addTextLine(builder, indent + "Not");
            printFMCS(exp.getExpression(), hierarchy + 1, builder);
        } else if (object instanceof FMCSODElement) {
            FMCSODElement ode = (FMCSODElement) object;
            addTextLine(builder, indent + "ODElement[" + ode.getFullName() + "]");
        }
    }

    private void addTextLine(StringBuilder builder, String text) {
        builder.append(text + System.lineSeparator());
    }
}
