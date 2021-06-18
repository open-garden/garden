package com.zipc.garden.webplatform.dsl.sc.validation.validators

import com.zipc.garden.webplatform.dsl.sc.sCModel.Goal
import com.zipc.garden.webplatform.dsl.sc.sCModel.Routes
import com.zipc.garden.webplatform.dsl.sc.sCModel.SCModelPackage
import com.zipc.garden.webplatform.dsl.sc.sCModel.Start
import com.zipc.garden.webplatform.dsl.sc.validation.AbstractSCModelValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class RoutesValidator extends AbstractSCModelValidator {

	public static val ROUTES_ENTITIES_UNDEFINED = 'Route entities is undefined!'

	public static val ROUTES_START_UNDEFINED = 'Route Start is undefined!'

	public static val ROUTES_START_MULTIPLY_DEFINED = 'Route Start is multiply defined!'

	public static val ROUTES_GOAL_UNDEFINED = 'Route Goal is undefined!'

	public static val ROUTES_GOAL_MULTIPLY_DEFINED = 'Route Goal is multiply defined!'

	override register(EValidatorRegistrar registrar) {
		// not needed for classes used as ComposedCheck
	}

	@Check(NORMAL)
	def checkLane(Routes routes) {
		if (routes.entities.isEmpty) {
			warning(ROUTES_ENTITIES_UNDEFINED, SCModelPackage.Literals.ROUTES__ENTITIES);
		} else {
			val startCount = routes.entities.filter(Start).size;
			if (startCount === 0) {
				error(ROUTES_START_UNDEFINED, SCModelPackage.Literals.ROUTES__ENTITIES);
			} else if (startCount > 1) {
				error(ROUTES_START_MULTIPLY_DEFINED, SCModelPackage.Literals.ROUTES__ENTITIES);
			}
			val goalCount = routes.entities.filter(Goal).size;
			if (goalCount === 0) {
				error(ROUTES_GOAL_UNDEFINED, SCModelPackage.Literals.ROUTES__ENTITIES);
			} else if (goalCount > 1) {
				error(ROUTES_GOAL_MULTIPLY_DEFINED, SCModelPackage.Literals.ROUTES__ENTITIES);
			}
		}
	}
}
