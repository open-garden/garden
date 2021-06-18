package com.zipc.garden.webplatform.dsl.sc.linking

import com.google.inject.Inject
import com.zipc.garden.webplatform.dsl.sc.lib.RangeValueExtensions
import com.zipc.garden.webplatform.dsl.sc.sCModel.Routes
import com.zipc.garden.webplatform.dsl.sc.sCModel.RoutesEntity
import com.zipc.garden.webplatform.dsl.sc.sCModel.SCModelPackage
import com.zipc.garden.webplatform.dsl.sc.sCModel.WayPoint
import java.util.Collections
import org.apache.log4j.Logger
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.linking.impl.DefaultLinkingService
import org.eclipse.xtext.linking.impl.IllegalNodeException
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.nodemodel.INode

class SCModelLinkingService extends DefaultLinkingService {

	static final Logger logger = Logger.getLogger(SCModelLinkingService);

	@Inject IQualifiedNameConverter qualifiedNameConverter;

	override getLinkedObjects(EObject context, EReference ref, INode node) throws IllegalNodeException {
		val requiredType = ref.getEReferenceType();
		if(requiredType === null) {
			return Collections.<EObject>emptyList();
		}
		val crossRefString = getCrossRefNodeAsString(node);
		if(crossRefString === null || crossRefString.equals("")) {
			return Collections.<EObject>emptyList();
		}
		if(logger.isDebugEnabled()) {
			logger.debug("before getLinkedObjects: node: '" + crossRefString + "'");
		}

		if(context instanceof RoutesEntity &&
			(ref === SCModelPackage.Literals.ROUTES_ENTITY__VELOCITY || ref === SCModelPackage.Literals.START__SHIFT || ref === SCModelPackage.Literals.GOAL__SHIFT)) {
			if(isDouble(crossRefString)) {
				return RangeValueExtensions.INSTANCE.eObject(crossRefString);
			}
		} else if((context instanceof Routes || context instanceof WayPoint) && ref === SCModelPackage.Literals.ABSTRACT_ROUTES__ACCEL) {
			if(isDouble(crossRefString)) {
				return RangeValueExtensions.INSTANCE.eObject(crossRefString);
			}
		}

		val scope = getScope(context, ref);
		if(scope === null) {
			throw new AssertionError("Scope provider " + scopeProvider.getClass().getName() + " must not return null for context " + context + ", reference " + ref +
				"! Consider to return IScope.NULLSCOPE instead.");
		}
		val qualifiedLinkName = qualifiedNameConverter.toQualifiedName(crossRefString);
		val eObjectDescription = scope.getSingleElement(qualifiedLinkName);
		if(logger.isDebugEnabled()) {
			logger.debug("after getLinkedObjects: node: '" + crossRefString + "' result: " + eObjectDescription);
		}
		if(eObjectDescription === null) {
			return Collections.emptyList();
		}
		val result = eObjectDescription.getEObjectOrProxy();
		return Collections.singletonList(result);
	}

	def private isDouble(String string) {
		try {
			Double.parseDouble(string);
		} catch(Exception exception) {
			return false;
		}
		return true;
	}

}
