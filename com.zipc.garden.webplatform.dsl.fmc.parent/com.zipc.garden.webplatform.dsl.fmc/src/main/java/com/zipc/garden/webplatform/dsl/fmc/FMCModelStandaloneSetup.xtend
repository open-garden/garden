/*
 * generated by Xtext 2.17.0
 */
package com.zipc.garden.webplatform.dsl.fmc


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class FMCModelStandaloneSetup extends FMCModelStandaloneSetupGenerated {

	def static void doSetup() {
		new FMCModelStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
