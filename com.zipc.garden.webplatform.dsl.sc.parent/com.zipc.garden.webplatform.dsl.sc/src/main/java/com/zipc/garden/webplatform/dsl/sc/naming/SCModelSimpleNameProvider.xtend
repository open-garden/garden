package com.zipc.garden.webplatform.dsl.sc.naming

import com.google.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.util.SimpleAttributeResolver

class SCModelSimpleNameProvider extends IQualifiedNameProvider.AbstractImpl {

	public final static SimpleAttributeResolver<EObject, String> ID_RESOLVER = SimpleAttributeResolver.
		newResolver(String, "id");

	@Inject IQualifiedNameConverter qualifiedNameConverter

	override getFullyQualifiedName(EObject obj) {
		val String id = ID_RESOLVER.apply(obj);
		if (id === null || id.length() == 0)
			return null;
		return qualifiedNameConverter.toQualifiedName(id);
	}

}
