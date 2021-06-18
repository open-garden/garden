package com.zipc.garden.webplatform.dsl.terminals;

import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;

import com.zipc.garden.webplatform.dsl.terminals.ID.Type;

public class DSLIDValueConverter implements IValueConverter<ID> {

    @Override
    public ID toValue(String string, INode node) throws ValueConverterException {
        if (string == null) {
            return null;
        }
        if (node == null) {
            return ID.fromString(string);
        }

        for (ILeafNode leaf : node.getLeafNodes()) {
            Object grammarElement = leaf.getGrammarElement();
            if (grammarElement instanceof RuleCall) {
                RuleCall lexerRuleCall = (RuleCall) grammarElement;
                AbstractRule nestedLexerRule = lexerRuleCall.getRule();
                String nestedLexerRuleName = nestedLexerRule.getName();
                if ("COMPASS_PT".equals(nestedLexerRuleName)) {
                    nestedLexerRuleName = "STRING";
                }
                return ID.fromString(string, Type.valueOf(nestedLexerRuleName));
            }
        }
        throw new IllegalArgumentException("Invalid ID string " + string);
    }

    @Override
    public String toString(ID value) throws ValueConverterException {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

}
