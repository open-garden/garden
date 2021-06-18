/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UITabbedPanel } from '../../../vendors/js/libs/ui.js';

import { SidebarObject } from './Sidebar.Object.js';

import { SidebarMetaldata } from './Sidebar.Metaldata.js';

var SidebarProperties = function (editor) {

	var strings = editor.strings;

	var container = new UITabbedPanel();
	container.setId('properties');

	container.addTab('metaldata', strings.getKey('sidebar/properties/scenario'), new SidebarMetaldata(editor));
	container.addTab('object', strings.getKey('sidebar/properties/object'), new SidebarObject(editor));
	//container.addTab('geometry', strings.getKey('sidebar/properties/geometry'), new SidebarGeometry(editor));
	//container.addTab('material', strings.getKey('sidebar/properties/material'), new SidebarMaterial(editor));
	container.select('metaldata');

	return container;

};

export { SidebarProperties };
