/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UIPanel } from '../../../vendors/js/libs/ui.js';

import { MenubarAdd } from './Menubar.Add.js';
import { MenubarEdit } from './Menubar.Edit.js';
import { MenubarFile } from './Menubar.File.js';
import { MenubarExamples } from './Menubar.Examples.js';
import { MenubarSetting } from './Menubar.Setting.js';

var Menubar = function (editor) {

	var container = new UIPanel();
	container.setId('menubar');

	container.add(new MenubarFile(editor));
	container.add(new MenubarEdit(editor));
	container.add(new MenubarAdd(editor));
	container.add(new MenubarExamples(editor));
	container.add(new MenubarSetting(editor));

	return container;

};

export { Menubar };
