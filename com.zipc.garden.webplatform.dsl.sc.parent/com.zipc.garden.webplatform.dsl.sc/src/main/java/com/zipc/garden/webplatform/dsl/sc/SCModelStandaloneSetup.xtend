/*
 * generated by Xtext 2.17.0
 */
package com.zipc.garden.webplatform.dsl.sc


/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
class SCModelStandaloneSetup extends SCModelStandaloneSetupGenerated {

	def static void doSetup() {
		new SCModelStandaloneSetup().createInjectorAndDoEMFRegistration()
	}
}
