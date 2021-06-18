package com.zipc.garden.webplatform.dsl.sc.validation.validators

import com.zipc.garden.webplatform.dsl.sc.sCModel.RoutesEntity
import com.zipc.garden.webplatform.dsl.sc.sCModel.SCModelPackage
import com.zipc.garden.webplatform.dsl.sc.validation.AbstractSCModelValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class RoutesEntityValidator extends AbstractSCModelValidator {

	public static val LANE_UNDEFINED = 'Lane is undefined!'

	override register(EValidatorRegistrar registrar) {
		// not needed for classes used as ComposedCheck
	}

	@Check(NORMAL)
	def checkLane(RoutesEntity entity) {
		if (entity.lane === null) {
			error(LANE_UNDEFINED, SCModelPackage.Literals.ROUTES_ENTITY__LANE);
		}
	}
}
