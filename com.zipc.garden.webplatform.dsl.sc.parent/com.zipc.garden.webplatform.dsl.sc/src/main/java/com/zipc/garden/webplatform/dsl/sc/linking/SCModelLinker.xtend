package com.zipc.garden.webplatform.dsl.sc.linking

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.linking.ILinkingService
import org.eclipse.xtext.linking.lazy.LazyLinker
import org.eclipse.xtext.nodemodel.INode

class SCModelLinker extends LazyLinker {

	@Inject extension ILinkingService linkingService

	override protected createProxy(EObject obj, INode node, EReference eRef) {
		val EObject result = getLinkedObject(obj, node, eRef);
		return result;
	}

	def private EObject getLinkedObject(EObject obj, INode node, EReference eRef) {
		val linkedObjects = linkingService.getLinkedObjects(obj, eRef, node);
		if (linkedObjects.size == 1) {
			val linkedObject = linkedObjects.get(0);
			if (linkedObject.eIsProxy) {
				return EcoreUtil.resolve(linkedObject, obj.eResource);
			} else {
				return linkedObject;
			}
		}
		return null;
	}

}
