/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UITabbedPanel, UISpan } from '../../../vendors/js/libs/ui.js';

import { SidebarRoad } from './Sidebar.Road.js';
import { SidebarRoadProperties } from './Sidebar.Road.Properties.js';

import { SidebarRoute } from './Sidebar.Route.js';
import { SidebarRouteProperties } from './Sidebar.Route.Properties.js';

import { SidebarRoadMark } from './Sidebar.RoadMark.js';
import { SidebarRoadMarkProperties } from './Sidebar.RoadMark.Properties.js';

import { SidebarProject } from './Sidebar.Project.js';
import { SidebarHistory } from './Sidebar.History.js';
import { SidebarSettings } from './Sidebar.Settings.js';

var Sidebar = function (editor) {

	var strings = editor.strings;

	var container = new UITabbedPanel();
	container.setId('sidebar');

	var scenario = new UISpan().add(
		new SidebarRoad(editor),
		new SidebarRoadProperties(editor)
	);

	var roadMarkObject = new UISpan().add(
		new SidebarRoadMark(editor),
		new SidebarRoadMarkProperties(editor)
	);

	var routeObject = new UISpan().add(
		new SidebarRoute(editor),
		new SidebarRouteProperties(editor)
	);

	var project = new SidebarProject(editor);

	var settings = new UISpan().add(
		new SidebarSettings(editor),
		new SidebarHistory(editor)
	);

	editor.signals.objectSelected.add(function (object) {
		if (object === undefined || object === null) {
			return;
		} else if (object.type === 'Road' || object.type === 'Lane' || object.type === 'Junction' || object.type === 'JunctionLane') {
			container.select('scenario');
		} else if (object.type === 'Route') {
			container.select('route');
		} else if (object.type === 'RoadMark.Line' || object.type === 'RoadMark.Sign' || object.type === 'RoadMark.Signal.TrafficLight' || object.type === 'RoadMark.Crosswalk') {
			container.select('roadmark');
		} else if (object.type === 'ControlSprite') {
			container.select('scenario');
		}
	});

	container.addTab('scenario', strings.getKey('sidebar/scenario'), scenario);
	container.addTab('roadmark', strings.getKey('sidebar/roadmark'), roadMarkObject);
	container.addTab('route', strings.getKey('sidebar/route'), routeObject);
	container.select('scenario');

	return container;

};

export { Sidebar };
