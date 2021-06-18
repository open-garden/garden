package com.zipc.garden.webplatform.dsl.sc.validation.validators

import com.zipc.garden.webplatform.dsl.sc.sCModel.Range
import com.zipc.garden.webplatform.dsl.sc.sCModel.SCModelPackage
import com.zipc.garden.webplatform.dsl.sc.validation.AbstractSCModelValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class RangeValidator extends AbstractSCModelValidator {

	public static val RANGE_PROPERTIES_UNDEFINED = 'Range properties is undefined!'

	override register(EValidatorRegistrar registrar) {
		// not needed for classes used as ComposedCheck
	}

	@Check(NORMAL)
	def checkLane(Range range) {
		if(range.min === null && range.max === null && range.average === null && range.mode === null && range.median === null && range.value.isEmpty) {
			warning(RANGE_PROPERTIES_UNDEFINED, SCModelPackage.Literals.RANGE__ID);
		}
	}

}
