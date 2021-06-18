package com.zipc.garden.webplatform.dsl.sc.parser

import com.zipc.garden.webplatform.dsl.sc.sCModel.Goal
import com.zipc.garden.webplatform.dsl.sc.sCModel.Mid
import com.zipc.garden.webplatform.dsl.sc.sCModel.Road
import com.zipc.garden.webplatform.dsl.sc.sCModel.Start
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.conversion.ValueConverterException
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.parser.DefaultEcoreElementFactory
import com.zipc.garden.webplatform.dsl.sc.sCModel.Trajectory
import com.zipc.garden.webplatform.dsl.sc.sCModel.SCModelFactory

class SCModelEcoreElementFactory extends DefaultEcoreElementFactory {

	override set(EObject object, String feature, Object value, String ruleName, INode node) throws ValueConverterException {
		if(object instanceof Road) {
			if(object.radius === null) object.radius = new Double(0.0);
		} else if(object instanceof Start) {
			if(object.lanechange_start === null) object.lanechange_start = new Integer(-1);
			if(object.lanechange_end === null) object.lanechange_end = new Integer(-1);
		} else if(object instanceof Mid) {
			if(object.lanechange_start === null) object.lanechange_start = new Integer(-1);
			if(object.lanechange_end === null) object.lanechange_end = new Integer(-1);
		} else if(object instanceof Goal) {
			if(object.lanechange_start === null) object.lanechange_start = new Integer(-1);
			if(object.lanechange_end === null) object.lanechange_end = new Integer(-1);
		} else if(object instanceof Trajectory) {
			if(object.mode === null) object.mode = SCModelFactory.eINSTANCE.createWayPoint();
			if(object.average === null) object.average = SCModelFactory.eINSTANCE.createWayPoint();
			if(object.edge === null) object.edge = SCModelFactory.eINSTANCE.createWayPoint();
		}
		super.set(object, feature, value, ruleName, node)
	}

}
