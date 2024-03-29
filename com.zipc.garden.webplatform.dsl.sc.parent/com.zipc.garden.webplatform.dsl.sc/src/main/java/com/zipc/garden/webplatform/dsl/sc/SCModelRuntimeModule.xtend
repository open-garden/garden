/*
 * generated by Xtext 2.17.0
 */
package com.zipc.garden.webplatform.dsl.sc

import com.zipc.garden.webplatform.dsl.sc.generator.CSCFileContentGenerator
import com.zipc.garden.webplatform.dsl.sc.linking.SCModelLinker
import com.zipc.garden.webplatform.dsl.sc.linking.SCModelLinkingService
import com.zipc.garden.webplatform.dsl.sc.naming.SCModelSimpleNameProvider
import com.zipc.garden.webplatform.dsl.sc.parser.SCModelEcoreElementFactory

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
class SCModelRuntimeModule extends AbstractSCModelRuntimeModule {

	override bindILinker() {
		return SCModelLinker;
	}

	override bindILinkingService() {
		return SCModelLinkingService;
	}

	override bindIAstFactory() {
		return SCModelEcoreElementFactory;
	}

	override bindIQualifiedNameProvider() {
		return SCModelSimpleNameProvider;
	}

	def Class<? extends CSCFileContentGenerator> bindCSCFileContentGenerator() {
		return CSCFileContentGenerator;
	}

}
