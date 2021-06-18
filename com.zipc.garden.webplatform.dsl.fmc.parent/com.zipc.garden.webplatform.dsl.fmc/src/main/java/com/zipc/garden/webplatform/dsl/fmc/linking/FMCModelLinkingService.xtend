package com.zipc.garden.webplatform.dsl.fmc.linking

import com.google.inject.Inject
import com.zipc.garden.webplatform.dsl.core.AbstractOperation
import com.zipc.garden.webplatform.dsl.core.DSLCOREPackage
import com.zipc.garden.webplatform.dsl.util.DSLCoreUtil
import java.util.Collections
import org.apache.log4j.Logger
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.linking.impl.DefaultLinkingService
import org.eclipse.xtext.linking.impl.IllegalNodeException
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.nodemodel.INode

class FMCModelLinkingService extends DefaultLinkingService {

	static final Logger logger = Logger.getLogger(FMCModelLinkingService);

	@Inject IQualifiedNameConverter qualifiedNameConverter;

	override getLinkedObjects(EObject context, EReference ref, INode node) throws IllegalNodeException {
		val requiredType = ref.getEReferenceType();
		if (requiredType === null) {
			return Collections.<EObject>emptyList();
		}
		val crossRefString = getCrossRefNodeAsString(node);
		if (crossRefString === null || crossRefString.equals("")) {
			return Collections.<EObject>emptyList();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("before getLinkedObjects: node: '" + crossRefString + "'");
		}

		if (context instanceof AbstractOperation && ref === DSLCOREPackage.Literals.ABSTRACT_OPERATION__OPERATOR) {
			if ("removes".equals(crossRefString)) {
				return Collections.singletonList(DSLCoreUtil.REMOVES_IDENTIFIABLEELEMENT);
			} else if ("->".equals(crossRefString)) {
				return Collections.singletonList(DSLCoreUtil.IMPLIES_IDENTIFIABLEELEMENT);
			} else if ("||".equals(crossRefString)) {
				return Collections.singletonList(DSLCoreUtil.OR_IDENTIFIABLEELEMENT);
			} else if ("&&".equals(crossRefString)) {
				return Collections.singletonList(DSLCoreUtil.AND_IDENTIFIABLEELEMENT);
			} else if ("!".equals(crossRefString)) {
				return Collections.singletonList(DSLCoreUtil.NOT_IDENTIFIABLEELEMENT);
			} else {
				return Collections.singletonList(DSLCoreUtil.UNDEFINED_IDENTIFIABLEELEMENT);
			}
		}

		val scope = getScope(context, ref);
		if (scope === null) {
			throw new AssertionError(
				"Scope provider " + scopeProvider.getClass().getName() + " must not return null for context " +
					context + ", reference " + ref + "! Consider to return IScope.NULLSCOPE instead.");
		}
		val qualifiedLinkName = qualifiedNameConverter.toQualifiedName(crossRefString);
		val eObjectDescription = scope.getSingleElement(qualifiedLinkName);
		if (logger.isDebugEnabled()) {
			logger.debug("after getLinkedObjects: node: '" + crossRefString + "' result: " + eObjectDescription);
		}
		if (eObjectDescription === null) {
			return Collections.emptyList();
		}
		val result = eObjectDescription.getEObjectOrProxy();
		return Collections.singletonList(result);
	}

}
