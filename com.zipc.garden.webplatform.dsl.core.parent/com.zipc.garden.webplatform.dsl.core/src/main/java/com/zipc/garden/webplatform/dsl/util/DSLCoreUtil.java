package com.zipc.garden.webplatform.dsl.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.access.TypeResource;

import com.zipc.garden.webplatform.dsl.core.DSLCOREFactory;
import com.zipc.garden.webplatform.dsl.core.IdentifiableElement;
import com.zipc.garden.webplatform.dsl.core.OperatorIdentifier;

public class DSLCoreUtil {

    private static final StringBuilder uriBuilder = new StringBuilder(48);

    private static final Resource resource = new TypeResource();

    public static final IdentifiableElement UNDEFINED_IDENTIFIABLEELEMENT = DSLCOREFactory.eINSTANCE.createIdentifiableElement();

    public static final IdentifiableElement REMOVES_IDENTIFIABLEELEMENT = DSLCOREFactory.eINSTANCE.createIdentifiableElement();

    public static final IdentifiableElement IMPLIES_IDENTIFIABLEELEMENT = DSLCOREFactory.eINSTANCE.createIdentifiableElement();

    public static final IdentifiableElement OR_IDENTIFIABLEELEMENT = DSLCOREFactory.eINSTANCE.createIdentifiableElement();

    public static final IdentifiableElement AND_IDENTIFIABLEELEMENT = DSLCOREFactory.eINSTANCE.createIdentifiableElement();

    public static final IdentifiableElement NOT_IDENTIFIABLEELEMENT = DSLCOREFactory.eINSTANCE.createIdentifiableElement();

    static {
        uriBuilder.append("java").append(":").append("/Objects/").append("com.zipc.garden.webplatform.dsl.lib.DSLBooleanExtensions");
        resource.setURI(URI.createURI(uriBuilder.toString()));
        UNDEFINED_IDENTIFIABLEELEMENT.setIdentifier(OperatorIdentifier.OPERATOR_UNDEFINED);
        REMOVES_IDENTIFIABLEELEMENT.setIdentifier(OperatorIdentifier.OPERATOR_REMOVES);
        IMPLIES_IDENTIFIABLEELEMENT.setIdentifier(OperatorIdentifier.OPERATOR_IMPLIES);
        OR_IDENTIFIABLEELEMENT.setIdentifier(OperatorIdentifier.OPERATOR_OR);
        AND_IDENTIFIABLEELEMENT.setIdentifier(OperatorIdentifier.OPERATOR_AND);
        NOT_IDENTIFIABLEELEMENT.setIdentifier(OperatorIdentifier.OPERATOR_NOT);
        resource.getContents().add(UNDEFINED_IDENTIFIABLEELEMENT);
        resource.getContents().add(REMOVES_IDENTIFIABLEELEMENT);
        resource.getContents().add(IMPLIES_IDENTIFIABLEELEMENT);
        resource.getContents().add(OR_IDENTIFIABLEELEMENT);
        resource.getContents().add(AND_IDENTIFIABLEELEMENT);
        resource.getContents().add(NOT_IDENTIFIABLEELEMENT);
    }
}
