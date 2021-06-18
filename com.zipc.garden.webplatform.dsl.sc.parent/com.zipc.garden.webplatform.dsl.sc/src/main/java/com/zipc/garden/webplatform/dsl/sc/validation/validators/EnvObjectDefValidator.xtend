package com.zipc.garden.webplatform.dsl.sc.validation.validators

import com.zipc.garden.webplatform.dsl.sc.sCModel.EnvObjectDef
import com.zipc.garden.webplatform.dsl.sc.sCModel.SCModelPackage
import com.zipc.garden.webplatform.dsl.sc.validation.AbstractSCModelValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class EnvObjectDefValidator extends AbstractSCModelValidator {

	public static val Type_UNDEFINED = 'Type is undefined!'

	override register(EValidatorRegistrar registrar) {
		// not needed for classes used as ComposedCheck
	}

	@Check(NORMAL)
	def checkLane(EnvObjectDef envObjDef) {
		if(envObjDef.defTypes.isEmpty) {
			warning(Type_UNDEFINED, SCModelPackage.Literals.ENV_OBJECT_DEF__DEF_TYPES);
		}
	}
}
