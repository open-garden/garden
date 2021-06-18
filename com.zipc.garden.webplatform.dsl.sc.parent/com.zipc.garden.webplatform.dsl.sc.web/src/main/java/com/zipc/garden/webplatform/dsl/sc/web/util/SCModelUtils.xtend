package com.zipc.garden.webplatform.dsl.sc.web.util

import com.google.inject.Injector
import com.zipc.garden.webplatform.dsl.sc.generator.CSCFileContentGenerator
import com.zipc.garden.webplatform.dsl.sc.sCModel.SCRoot
import com.zipc.garden.webplatform.dsl.sc.sCModel.Scenario
import com.zipc.garden.webplatform.dsl.sc.web.SCModelWebSetup
import java.io.ByteArrayInputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Collections
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.resource.IResourceFactory
import org.eclipse.xtext.util.StringInputStream

class SCModelUtils {
	
	def static void main(String[] args) {
		
	}

	static SCModelUtils INSTANCE = new SCModelUtils();

	static Injector injector;
	static IResourceFactory resourceFactory;
	static CSCFileContentGenerator cscFileContentGenerator;

	private new() {
		injector = new SCModelWebSetup().createInjectorAndDoEMFRegistration();
		resourceFactory = injector.getInstance(IResourceFactory);
		cscFileContentGenerator = injector.getInstance(CSCFileContentGenerator);
	}

	def static SCModelUtils getInstance() {
		return INSTANCE;
	}

	/**
	 * Loads the resource from the specified file path.
	 * 
	 * <p>Example:
	 * <pre>
	 *     SCModelLoader.instance.load("c:\\temp\\a01.lsc");
	 * </pre>
	 * 
	 * @param filePath 絶対ファイルパス
	 * @return EMFモデル の Resource
	 */
	def SCRoot load(String filePath) {
		if(filePath === null)
			return null;

		val path = Paths.get(filePath);
		if(Files.exists(path)) {
			val bytes = Files.readAllBytes(path);
			val uri = URI.createFileURI(path.toFile.toString);
			try {
				val resource = resourceFactory.createResource(uri);
				resource.load(new ByteArrayInputStream(bytes), Collections.emptyMap());
				val rootModel = resource.allContents.filter(SCRoot).toList;
				return if(rootModel.isNullOrEmpty) null else rootModel.get(0);
			} catch(Exception caught) {
				throw new RuntimeException("Failed to load resource for " + uri.toString, caught);
			}
		}
		return null;
	}

	/**
	 * Loads the resource from the given text.
	 * 
	 * <p>Example:
	 * <pre>
	 *     SCModelLoader.instance.load("構文の文字列", "sample01.sc");
	 * </pre>
	 * 
	 * @param fullText fullText
	 * @param resourceId resourceId
	 * @return Resource
	 */
	def SCRoot load(String fullText, String resourceId) {
		if(resourceId === null || resourceId.isEmpty) {
			return null;
		}
		val uri = URI.createURI(resourceId);
		try {
			val resource = resourceFactory.createResource(uri);
			resource.load(new StringInputStream(fullText), Collections.emptyMap());
			val rootModel = resource.allContents.filter(SCRoot).toList;
			return if(rootModel.isNullOrEmpty) null else rootModel.get(0);
		} catch(Exception caught) {
			throw new RuntimeException("Failed to load resource for " + uri.toString, caught);
		}
	}

	/**
	 * Creates a string representation of the given object
	 * 
	 * <p>Example:
	 * <pre>
	 *     SCModelLoader.instance.toText(rootObject);
	 * </pre>
	 * 
	 * @param eObject eObject
	 * @return String
	 */
	def String toText(EObject eObject) {
		val builder = new StringBuilder();
		if(eObject === null || eObject.eIsProxy) {
			return builder.toString;
		}

		if(eObject instanceof SCRoot) {
			builder.append(cscFileContentGenerator.compile(eObject));
		} else if(eObject instanceof Scenario) {
			builder.append(cscFileContentGenerator.compile(eObject));
		} else {
			throw new RuntimeException("Not supported type class '" + eObject.class.name + "'");
		}

		return builder.toString;
	}
}
