package com.zipc.garden.webplatform.dsl.sc.validation.validators

import com.zipc.garden.webplatform.dsl.sc.sCModel.ConnectionType
import com.zipc.garden.webplatform.dsl.sc.sCModel.Road
import com.zipc.garden.webplatform.dsl.sc.sCModel.RoadType
import com.zipc.garden.webplatform.dsl.sc.sCModel.SCModelPackage
import com.zipc.garden.webplatform.dsl.sc.validation.AbstractSCModelValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class RoadValidator extends AbstractSCModelValidator {

	public static val RADIUS_UNDEFINED = 'Radius is undefined!'

	public static val START_RADIUS_UNDEFINED = 'Start radius is undefined!'

	public static val END_RADIUS_UNDEFINED = 'End radius is undefined!'

	public static val CONNECTION_UNDEFINED = 'When Reverse is TRUE, need to define the Connection!'

	override register(EValidatorRegistrar registrar) {
		// not needed for classes used as ComposedCheck
	}

	@Check(NORMAL)
	def checkLane(Road road) {
		if(road.type === RoadType.CIRCULAR && road.radius === null) {
			error(RADIUS_UNDEFINED, SCModelPackage.Literals.ROAD__RADIUS);
		}
		if(road.type === RoadType.CLOTHOID) {
			if(road.start_radius === null) {
				error(START_RADIUS_UNDEFINED, SCModelPackage.Literals.ROAD__START_RADIUS);
			} else if(road.end_radius === null) {
				error(END_RADIUS_UNDEFINED, SCModelPackage.Literals.ROAD__END_RADIUS);
			}
		}
		if(road.connectionType === ConnectionType.PREDECESSOR && road.connection === null) {
			error(CONNECTION_UNDEFINED, SCModelPackage.Literals.ROAD__CONNECTION);
		}
	}
}
