package com.zipc.garden.webplatform.dsl.sc.lib

import com.zipc.garden.webplatform.dsl.sc.sCModel.Range
import java.util.Collections
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.common.types.access.TypeResource;
import com.zipc.garden.webplatform.dsl.sc.sCModel.SCModelFactory

class RangeValueExtensions {

	static final StringBuilder uriBuilder = new StringBuilder(128);

	static final Resource resource = new TypeResource();

	public static RangeValueExtensions INSTANCE = new RangeValueExtensions();

	private new() {
		uriBuilder.append("java").append(":").append("/Objects/").append("com.zipc.garden.webplatform.dsl.sc.lib.RangeValueExtensions");
		resource.setURI(URI.createURI(uriBuilder.toString()));
	}

	def List<EObject> eObject(String string) {
		try {
			val value = Double.parseDouble(string);
			val range = resource.contents.filter(Range).findFirst[r|r.id.equals(value.toString)];
			return Collections.singletonList(range ?: createRange(value));
		} catch(Exception e) {
			Collections.emptyList;
		}
	}

	def Range createRange(Double value) {
		val newRange = SCModelFactory.eINSTANCE.createRange();
		newRange.id = value.toString;
		newRange.value.add(value);
		resource.getContents().add(newRange);
		return newRange;
	}

}
