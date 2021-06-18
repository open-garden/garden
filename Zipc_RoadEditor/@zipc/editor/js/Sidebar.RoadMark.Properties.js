/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UITabbedPanel } from '../../../vendors/js/libs/ui.js';

import { SidebarRoadMarkObject } from './Sidebar.RoadMarkObject.js';

var SidebarRoadMarkProperties = function (editor) {

	var strings = editor.strings;

	var container = new UITabbedPanel();
	container.setId('properties');

	container.addTab('roadmark', strings.getKey('sidebar/properties/roadmark'), new SidebarRoadMarkObject(editor));
	container.select('roadmark');

	return container;

};

export { SidebarRoadMarkProperties };
