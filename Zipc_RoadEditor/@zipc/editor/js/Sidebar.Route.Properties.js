/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UITabbedPanel } from '../../../vendors/js/libs/ui.js';

import { SidebarRouteObject } from './Sidebar.RouteObject.js';

var SidebarRouteProperties = function (editor) {

	var strings = editor.strings;

	var container = new UITabbedPanel();
	container.setId('properties');

	container.addTab('route', strings.getKey('sidebar/properties/route'), new SidebarRouteObject(editor));
	container.select('route');

	return container;

};

export { SidebarRouteProperties };
