/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UIPanel, UIDiv } from '../../../vendors/js/libs/ui.js';

var LoadingScreen = function (editor) {

	var strings = editor.strings;
	var signals = editor.signals;

	// container
	var container = new UIPanel();
	container.setId('loadingScreen');

	var animationDiv = new UIDiv();
	animationDiv.setClass('lds-default');
	for (let i = 0; i < 12; i++) {
		animationDiv.add(new UIDiv());
	}
	container.add(animationDiv);

	//
	signals.loadingScreenShow.add(function () {
		container.setDisplay('block');
	});

	signals.loadingScreenHide.add(function () {
		container.setDisplay('none');
	});

	return container;

};

export { LoadingScreen };
