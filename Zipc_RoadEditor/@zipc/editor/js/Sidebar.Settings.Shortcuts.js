/**
 * @author TyLindberg / https://github.com/TyLindberg
 */

import { UIDiv, UIBreak, UIText, UIRow, UIInput } from '../../../vendors/js/libs/ui.js';
import { RemoveObjectCommand } from './commands/RemoveObjectCommand.js';
import { RemoveLaneObjectCommand } from './commands/RemoveLaneObjectCommand.js';
import { RemoveLaneCommand } from './commands/RemoveLaneCommand.js';
import { AddLaneCommand } from './commands/AddLaneCommand.js';
import { AddGOLaneCommand } from './commands/AddGOLaneCommand.js';
import { RemoveGOLaneCommand } from './commands/RemoveGOLaneCommand.js';
import { CloneAndConnectRoadObjectCommand } from './commands/CloneAndConnectRoadObjectCommand.js';
import { RemoveLineSegmentObjectCommand } from './commands/RemoveLineSegmentObjectCommand.js';



var SidebarSettingsShortcuts = function (editor) {

	var strings = editor.strings;

	var IS_MAC = navigator.platform.toUpperCase().indexOf('MAC') >= 0;

	function isValidKeyBinding(key) {

		return key.match(/^[A-Za-z0-9]$/i); // Can't use z currently due to undo/redo

	}

	var config = editor.config;
	var signals = editor.signals;

	var container = new UIDiv();
	container.add(new UIBreak());

	var shortcuts = ['translate', 'rotate', 'scale', 'undo', 'focus'];

	function createShortcutInput(name) {

		var configName = 'settings/shortcuts/' + name;
		var shortcutRow = new UIRow();

		var shortcutInput = new UIInput().setWidth('150px').setFontSize('12px');
		shortcutInput.setTextTransform('lowercase');
		shortcutInput.onChange(function () {

			var value = shortcutInput.getValue().toLowerCase();

			if (isValidKeyBinding(value)) {

				config.setKey(configName, value);

			}

		});

		// Automatically highlight when selecting an input field
		shortcutInput.dom.addEventListener('click', function () {

			shortcutInput.dom.select();

		});

		// If the value of the input field is invalid, revert the input field
		// to contain the key binding stored in config
		shortcutInput.dom.addEventListener('blur', function () {

			if (!isValidKeyBinding(shortcutInput.getValue())) {

				shortcutInput.setValue(config.getKey(configName));

			}

		});

		// If a valid key binding character is entered, blur the input field
		shortcutInput.dom.addEventListener('keyup', function (event) {

			if (isValidKeyBinding(event.key)) {

				shortcutInput.dom.blur();

			}

		});

		if (config.getKey(configName) !== undefined) {

			shortcutInput.setValue(config.getKey(configName));

		}

		shortcutInput.dom.maxLength = 1;
		shortcutRow.add(new UIText(strings.getKey('sidebar/settings/shortcuts/' + name)).setTextTransform('capitalize').setWidth('90px'));
		shortcutRow.add(shortcutInput);

		container.add(shortcutRow);

	}

	for (var i = 0; i < shortcuts.length; i++) {

		createShortcutInput(shortcuts[i]);

	}

	document.addEventListener('keydown', function (event) {

		switch (event.key.toLowerCase()) {

			case 'backspace':

				event.preventDefault(); // prevent browser back

			// fall-through

			case 'delete':

				var object = editor.selected;

				if (object === null) return;

				var parent = object.parent;

				if (parent !== null) {

					if (object.type === 'Lane') {

						if (object.lanePosition === 'center') {
							alert(`Cannot delete lane '${object.name}' of CENTER`);
						} else {
							editor.execute(new RemoveLaneCommand(editor, object));
						}

					} else if (object.type === 'JunctionLane') {

						editor.execute(new RemoveLaneObjectCommand(editor, object));

					} else if (object.goType === 'GOLane') {

						editor.execute(new RemoveGOLaneCommand(editor, object));

					} else if (object.type === 'RoadMark.LineSegment') {

						editor.execute(new RemoveLineSegmentObjectCommand(editor, object));

					} else {

						editor.execute(new RemoveObjectCommand(editor, object));

					}

				}

				break;

			case config.getKey('settings/shortcuts/translate'):

				signals.transformModeChanged.dispatch('translate');

				break;

			case config.getKey('settings/shortcuts/rotate'):

				signals.transformModeChanged.dispatch('rotate');

				break;

			case config.getKey('settings/shortcuts/scale'):

				signals.transformModeChanged.dispatch('scale');

				break;

			case config.getKey('settings/shortcuts/undo'):

				if (IS_MAC ? event.metaKey : event.ctrlKey) {

					event.preventDefault(); // Prevent browser specific hotkeys

					if (event.shiftKey) {

						editor.redo();

					} else {

						editor.undo();

					}

				}

				break;

			case config.getKey('settings/shortcuts/focus'):

				if (editor.selected !== null) {

					editor.focus(editor.selected);

				}

				break;

			case config.getKey('settings/shortcuts/add_lane'):

				if (IS_MAC ? event.metaKey : event.ctrlKey) {

					event.preventDefault(); // Prevent browser specific hotkeys

					var object = editor.selected;

					if (object === null) return;

					if (object.type === 'Road') {

						editor.execute(new AddLaneCommand(editor, object));

					} else if (object.goType === 'GORoad') {

						editor.execute(new AddGOLaneCommand(editor, object));
					}

				}

				break;

			case config.getKey('settings/shortcuts/clone'):

				if (IS_MAC ? event.metaKey : event.ctrlKey) {

					event.preventDefault(); // Prevent browser specific hotkeys

					var object = editor.selected;

					if (object === null) return;

					if (object.type === 'Road') {

						if (event.shiftKey) {

							editor.execute(new CloneAndConnectRoadObjectCommand(editor, object));

						} else {

							// editor.execute(new CloneAndConnectRoadObjectCommand(editor, object));

						}

					}

				}

				break;

		}

	}, false);

	return container;

};

export { SidebarSettingsShortcuts };
