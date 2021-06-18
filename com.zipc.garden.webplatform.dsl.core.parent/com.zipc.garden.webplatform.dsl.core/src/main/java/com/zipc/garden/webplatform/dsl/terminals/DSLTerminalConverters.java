package com.zipc.garden.webplatform.dsl.terminals;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.impl.AbstractDeclarativeValueConverterService;

import com.google.inject.Inject;

public class DSLTerminalConverters extends AbstractDeclarativeValueConverterService {

    @Inject
    private DSLIDValueConverter idValueConverter;

    /**
     * A value converter for the ID data type rule.
     * @return An {@link DotIDValueConverter}.
     */
    @ValueConverter(rule = "ValidID")
    public IValueConverter<ID> ID() {
        return idValueConverter;
    }
}
