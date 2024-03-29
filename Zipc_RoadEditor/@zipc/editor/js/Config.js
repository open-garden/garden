/**
 * @author mrdoob / http://mrdoob.com/
 */

var Config = function () {

	var name = 'threejs-editor';

	var storage = {
		'language': 'en',
		'exportPrecision': 6,

		'autosave': false,

		'wireframe': false,
		'referenceLine': false,
		'wayPointEditMode': false,

		'project/title': '',
		'project/editable': false,
		'project/vr': false,

		'project/renderer/antialias': true,
		'project/renderer/shadows': true,
		'project/renderer/shadowType': 1, // PCF
		'project/renderer/physicallyCorrectLights': false,
		'project/renderer/toneMapping': 1, // linear
		'project/renderer/toneMappingExposure': 1,
		'project/renderer/toneMappingWhitePoint': 1,

		'project/vr': false,

		'settings/history': false,

		'settings/shortcuts/translate': 'w',
		'settings/shortcuts/rotate': 'e',
		'settings/shortcuts/scale': 'r',
		'settings/shortcuts/undo': 'z',
		'settings/shortcuts/focus': 'f',
		'settings/shortcuts/add_lane': 'a',
		'settings/shortcuts/clone': 'c'
	};

	if (window.localStorage[name] === undefined) {

		window.localStorage[name] = JSON.stringify(storage);

	} else {

		var data = JSON.parse(window.localStorage[name]);

		for (var key in data) {

			storage[key] = data[key];

		}

	}

	return {

		getKey: function (key) {

			return storage[key];

		},

		setKey: function () { // key, value, key, value ...

			for (var i = 0, l = arguments.length; i < l; i += 2) {

				storage[arguments[i]] = arguments[i + 1];

			}

			window.localStorage[name] = JSON.stringify(storage);

			console.log('[' + /\d\d\:\d\d\:\d\d/.exec(new Date())[0] + ']', 'Saved config to LocalStorage.');

		},

		clear: function () {

			delete window.localStorage[name];

		}

	};

};

export { Config };
