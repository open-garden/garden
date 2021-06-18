package com.zipc.garden.webplatform.dsl.fmc.validation.validators

import com.zipc.garden.webplatform.dsl.fmc.fMCModel.FMCRoot
import com.zipc.garden.webplatform.dsl.fmc.validation.AbstractFMCModelValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class FMCRootValidator extends AbstractFMCModelValidator {

	override register(EValidatorRegistrar registrar) {
		// not needed for classes used as ComposedCheck
	}

	@Check(NORMAL)
	def checkLane(FMCRoot fmcRoot) {
	}
}
