package com.zipc.garden.webplatform.dsl.fmc.web.server.util

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.resource.ILocationInFileProvider
import org.eclipse.xtext.resource.XtextResource
import com.zipc.garden.webplatform.dsl.util.DSLCoreEObjectAtOffsetHelper

class FMCModelElementAtOffsetUtil {

	@Inject extension DSLCoreEObjectAtOffsetHelper
	@Inject extension ILocationInFileProvider

	def EObject getElementAt(XtextResource resource, int offset) {
		var crossLinkedEObject = resource.resolveCrossReferencedElementAt(offset)
		if (crossLinkedEObject !== null) {
			return crossLinkedEObject
		} else {
			var containedEObject = resource.resolveContainedElementAt(offset)
			if (containedEObject !== null) {
				val nameRegion = containedEObject.significantTextRegion
				if (nameRegion.contains(offset))
					return containedEObject
			}
		}
		return null
	}
}
