package com.zipc.garden.webplatform.dsl.sc.validation.validators

import com.zipc.garden.webplatform.dsl.sc.sCModel.EnvObject
import com.zipc.garden.webplatform.dsl.sc.validation.AbstractSCModelValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class EnvObjectValidator extends AbstractSCModelValidator {

	public static val RADIUS_UNDEFINED = 'Radius is undefined!'

	public static val CONNECTION_UNDEFINED = 'When Reverse is TRUE, need to define the Connection!'

	override register(EValidatorRegistrar registrar) {
		// not needed for classes used as ComposedCheck
	}

	@Check(NORMAL)
	def checkLane(EnvObject envObj) {
		switch (envObj.type) {
			// Geometry
			case 'triangle',
			case 'cube',
			case 'round': {
			}
			// Road markings
			case 'crosswalk',
			case 'stopline',
			case 'arrow_straight',
			case 'arrow_right',
			case 'arrow_left': {
			}
			// Traffic Objects
			case 'traffic_light',
			case 'no_parking',
			case 'speed_limit_40': {
			}
			// Others
			default: {
			}
		}
	}
}
