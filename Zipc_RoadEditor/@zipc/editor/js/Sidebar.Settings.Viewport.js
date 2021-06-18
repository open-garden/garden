/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UIDiv, UIBreak, UIText } from '../../../vendors/js/libs/ui.js';
import { UIBoolean } from '../../../vendors/js/libs/ui.three.js';


var SidebarSettingsViewport = function (editor) {

	var signals = editor.signals;
	var strings = editor.strings;

	var container = new UIDiv();
	container.add(new UIBreak());

	container.add(new UIText(strings.getKey('sidebar/settings/viewport/grid')).setWidth('90px'));

	var show = new UIBoolean(true).onChange(update);
	container.add(show);

	function update() {

		signals.showGridChanged.dispatch(show.getValue());

	}

	return container;

};

export { SidebarSettingsViewport };
