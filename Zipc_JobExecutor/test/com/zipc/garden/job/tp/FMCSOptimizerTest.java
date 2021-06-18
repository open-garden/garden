package com.zipc.garden.job.tp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.zipc.garden.model.fm.ChildType;
import com.zipc.garden.model.fmcs.FMCSAndExpression;
import com.zipc.garden.model.fmcs.FMCSConstraint;
import com.zipc.garden.model.fmcs.FMCSExpression;
import com.zipc.garden.model.fmcs.FMCSFactory;
import com.zipc.garden.model.fmcs.FMCSImpliesExpression;
import com.zipc.garden.model.fmcs.FMCSNotExpression;
import com.zipc.garden.model.fmcs.FMCSODElement;
import com.zipc.garden.model.fmcs.FMCSRoot;
import com.zipc.garden.model.fmcs.FMCSSelectExpression;
import com.zipc.garden.model.tc.TCFactory;
import com.zipc.garden.model.tc.TCNode;
import com.zipc.garden.model.tc.TCRoot;

public class FMCSOptimizerTest {
    private TCRoot tcRoot;

    private FMCSRoot fmcsRoot;

    private void createTC1() {
        tcRoot = TCFactory.eINSTANCE.createTCRoot();
        TCNode tcNodeRoot = TCFactory.eINSTANCE.createTCNode();
        tcNodeRoot.setName("OD");
        tcNodeRoot.setChecked(true);
        TCNode tcNode1 = TCFactory.eINSTANCE.createTCNode();
        tcNode1.setName("気象");
        tcNode1.setChecked(true);
        TCNode tcNode11 = TCFactory.eINSTANCE.createTCNode();
        tcNode11.setName("天気");
        tcNode11.setChecked(true);
        TCNode tcNode111 = TCFactory.eINSTANCE.createTCNode();
        tcNode111.setName("晴れ");
        tcNode111.setChecked(true);
        // TCNode tcNode112 = TCFactory.eINSTANCE.createTCNode();
        // tcNode112.setName("雨");
        // tcNode112.setChecked(true);
        // TCNode tcNode113 = TCFactory.eINSTANCE.createTCNode();
        // tcNode113.setName("曇");
        // tcNode113.setChecked(true);
        TCNode tcNode12 = TCFactory.eINSTANCE.createTCNode();
        tcNode12.setName("気温");
        tcNode12.setChecked(true);
        TCNode tcNode121 = TCFactory.eINSTANCE.createTCNode();
        tcNode121.setName("灼熱");
        tcNode121.setChecked(true);
        TCNode tcNode122 = TCFactory.eINSTANCE.createTCNode();
        tcNode122.setName("極寒");
        tcNode122.setChecked(true);
        TCNode tcNode2 = TCFactory.eINSTANCE.createTCNode();
        tcNode2.setName("土地");
        tcNode2.setChecked(true);
        TCNode tcNode21 = TCFactory.eINSTANCE.createTCNode();
        tcNode21.setName("草原");
        tcNode21.setChecked(true);
        // TCNode tcNode22 = TCFactory.eINSTANCE.createTCNode();
        // tcNode22.setName("砂漠");
        // tcNode22.setChecked(false);

        tcRoot.getRootNodes().add(tcNodeRoot);
        tcNodeRoot.setChildType(ChildType.AND);
        tcNodeRoot.getChildren().add(tcNode1);
        tcNodeRoot.getChildren().add(tcNode2);
        tcNode1.setChildType(ChildType.AND);
        tcNode1.getChildren().add(tcNode11);
        tcNode1.getChildren().add(tcNode12);
        tcNode11.setChildType(ChildType.XOR);
        tcNode11.getChildren().add(tcNode111);
        // tcNode11.getChildren().add(tcNode112);
        // tcNode11.getChildren().add(tcNode113);
        tcNode12.setChildType(ChildType.XOR);
        tcNode12.getChildren().add(tcNode121);
        tcNode12.getChildren().add(tcNode122);
        tcNode2.setChildType(ChildType.XOR);
        tcNode2.getChildren().add(tcNode21);
        // tcNode2.getChildren().add(tcNode22);

    }

    private void createFMCS1() {
        fmcsRoot = FMCSFactory.eINSTANCE.createFMCSRoot();
        FMCSConstraint fmcsConstraint1 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        FMCSConstraint fmcsConstraint2 = FMCSFactory.eINSTANCE.createFMCSConstraint();
        FMCSExpression expression1 = FMCSFactory.eINSTANCE.createFMCSImpliesExpression();
        FMCSExpression expNot = FMCSFactory.eINSTANCE.createFMCSNotExpression();
        FMCSExpression expression11 = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        FMCSExpression expression12 = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        FMCSODElement odelement1 = FMCSFactory.eINSTANCE.createFMCSODElement();
        FMCSODElement odelement2 = FMCSFactory.eINSTANCE.createFMCSODElement();

        odelement1.setFullName("OD.土地.砂漠");
        odelement2.setFullName("OD.気象.気温.灼熱");
        ((FMCSSelectExpression) expression11).setOdElement(odelement1);
        ((FMCSSelectExpression) expression12).setOdElement(odelement2);
        ((FMCSNotExpression) expNot).setExpression(expression11);
        ((FMCSImpliesExpression) expression1).setLeftExpression(expNot);
        ((FMCSImpliesExpression) expression1).setRightExpression(expression12);

        // FMCSExpression expression2 = FMCSFactory.eINSTANCE.createFMCSRemovesExpression();
        FMCSExpression expression2 = FMCSFactory.eINSTANCE.createFMCSImpliesExpression();
        FMCSExpression expression2le = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        FMCSExpression expression21 = FMCSFactory.eINSTANCE.createFMCSAndExpression();
        FMCSExpression expression211 = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        FMCSExpression expression212 = FMCSFactory.eINSTANCE.createFMCSSelectExpression();
        FMCSExpression expression213 = FMCSFactory.eINSTANCE.createFMCSSelectExpression();

        FMCSODElement odelement3 = FMCSFactory.eINSTANCE.createFMCSODElement();
        FMCSODElement odelement4 = FMCSFactory.eINSTANCE.createFMCSODElement();
        FMCSODElement odelement5 = FMCSFactory.eINSTANCE.createFMCSODElement();
        FMCSODElement odelement6 = FMCSFactory.eINSTANCE.createFMCSODElement();
        odelement3.setFullName("OD.気象.天気.晴れ");
        odelement4.setFullName("OD.気象.天気.雨");
        odelement5.setFullName("OD.気象.天気.曇");
        odelement6.setFullName("OD.土地.草原");
        ((FMCSSelectExpression) expression2le).setOdElement(odelement6);
        ((FMCSImpliesExpression) expression2).setLeftExpression(expression2le);
        ((FMCSImpliesExpression) expression2).setRightExpression(expression21);

        // ((FMCSMutexExpression) expression21).getOdElements().add(odelement3);
        // ((FMCSMutexExpression) expression21).getOdElements().add(odelement4);
        // ((FMCSMutexExpression) expression21).getOdElements().add(odelement5);
        ((FMCSSelectExpression) expression211).setOdElement(odelement3);
        ((FMCSSelectExpression) expression212).setOdElement(odelement4);
        ((FMCSSelectExpression) expression213).setOdElement(odelement5);
        ((FMCSAndExpression) expression21).getExpressions().add(expression211);
        ((FMCSAndExpression) expression21).getExpressions().add(expression212);
        ((FMCSAndExpression) expression21).getExpressions().add(expression213);

        fmcsConstraint1.setExpression(expression1);
        fmcsConstraint2.setExpression(expression2);

        fmcsRoot.getConstraints().add(fmcsConstraint1);
        fmcsRoot.getConstraints().add(fmcsConstraint2);
    }

    @Test
    public void Test01() {
        createTC1();
        createFMCS1();
        FMCSOptimizer opt = new FMCSOptimizer(tcRoot, fmcsRoot);
        FMCSRoot root = opt.optimize();
        FMCSExpression exp1 = root.getConstraints().get(0).getExpression();
        // FMCSExpression exp2 = root.getConstraints().get(1).getExpression();

        // FMCSExpression left = ((FMCSImpliesExpression) exp1).getLeftExpression();
        // FMCSExpression right = ((FMCSImpliesExpression) exp1).getRightExpression();
        // String namel = ((FMCSSelectExpression) left).getOdElement().getFullName();
        // String namer = ((FMCSSelectExpression) right).getOdElement().getFullName();

        // String name1 = ((FMCSRemovesExpression) exp1).getOdElement().getFullName();
        FMCSExpression left = ((FMCSImpliesExpression) exp1).getLeftExpression();
        FMCSExpression right = ((FMCSImpliesExpression) exp1).getRightExpression();

        String e1 = ((FMCSSelectExpression) left).getOdElement().getFullName();
        String e2 = ((FMCSSelectExpression) right).getOdElement().getFullName();

        assertThat(root.getConstraints().size(), is(1));
        // assertThat(namel, is("OD.土地.砂漠"));
        // assertThat(namer, is("OD.気象.気温.灼熱"));

        assertThat(e1, is("OD.土地.草原"));
        assertThat(e2, is("OD.気象.天気.晴れ"));
        // assertThat(((FMCSSelectExpression) e1).getOdElement().getFullName(), is("OD.気象.天気.晴れ"));
        // assertThat(((FMCSAndExpression) and).getExpressions().size(), is(2));
        // assertThat(od.get(0).getFullName(), is("OD.気象.天気.晴れ"));
        // assertThat(od.get(1).getFullName(), is("OD.気象.天気.雨"));
        // assertThat(od.get(2).getFullName(), is("OD.気象.天気.曇"));

        //
        // FMCSExpression orExp = ((FMCSRemovesExpression) exp).getExpression();
        // String name2 = ((FMCSSelectExpression) ((FMCSAndExpression)
        // orExp).getExpressions().get(0)).getOdElement().getFullName();
        // String name3 = ((FMCSSelectExpression) ((FMCSAndExpression)
        // orExp).getExpressions().get(1)).getOdElement().getFullName();
        // // String name4 = ((FMCSSelectExpression) ((FMCSOrExpression)
        // // orExp).getExpressions().get(2)).getOdElement().getFullName();
        // assertThat(name1, is("OD.土地.草原"));
        // assertThat(name2, is("OD.気象.天気.晴れ"));
        // assertThat(name3, is("OD.気象.天気.雨"));
        // // assertThat(name4, is("OD.気象.天気.曇"));
        // assertThat(((FMCSAndExpression) orExp).getExpressions().size(), is(2));
        //
        // // FMCSExpression name = ((FMCSRemovesExpression) exp).getExpression();
        // // assertThat(name1, is("OD.土地.草原"));
        // // assertThat(((FMCSSelectExpression) name).getOdElement().getFullName(), is("OD.気象.天気.晴れ"));
        //
        // assertThat(root.getConstraints().size(), is(1));

    }
}
