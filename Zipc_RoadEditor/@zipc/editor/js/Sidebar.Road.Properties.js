/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UITabbedPanel } from '../../../vendors/js/libs/ui.js';

import { SidebarRoadObject } from './Sidebar.RoadObject.js';

var SidebarRoadProperties = function (editor) {

	var strings = editor.strings;

	var container = new UITabbedPanel();
	container.setId('properties');

	container.addTab('metaldata', strings.getKey('sidebar/properties/scenario'), new SidebarRoadObject(editor));
	container.select('metaldata');

	return container;

};

export { SidebarRoadProperties };
