// CSS
import './css/main.css';

// Common JS
import 'codemirror/lib/codemirror.css';
import 'codemirror/theme/monokai.css';
import 'codemirror/lib/codemirror.js';
import 'codemirror/mode/javascript/javascript.js';

import '../vendors/js/libs/esprima.js';
import '../vendors/js/libs/jsonlint.js';
import '../vendors/js/libs/glslprep.min.js';

import 'codemirror/addon/dialog/dialog.css';
import 'codemirror/addon/hint/show-hint.css';
import 'codemirror/addon/tern/tern.css';

import 'codemirror/addon/dialog/dialog.js';
import 'codemirror/addon/hint/show-hint.js';
import 'codemirror/addon/tern/tern.js';
import 'acorn';
import 'acorn-loose';
import 'acorn-walk';
import 'ternjs';
import 'tern-threejs';
import '../vendors/examples/js/vr/HelioWebXRPolyfill.js';

// Module JS
import * as THREE from 'three'
import { Editor } from '../@zipc/editor/js/Editor.js';
import { Viewport } from '../@zipc/editor/js/Viewport.js';
import { Toolbar } from '../@zipc/editor/js/Toolbar.js';
import { Script } from '../@zipc/editor/js/Script.js';
import { Player } from '../@zipc/editor/js/Player.js';
import { Sidebar } from '../@zipc/editor/js/Sidebar';
import { Menubar } from '../@zipc/editor/js/Menubar.js';
import { PopupForm } from '../@zipc/editor/js/PopupForm.js';
import { LabelForm } from '../@zipc/editor/js/LabelForm.js';
import { MapManageForm } from '../@zipc/editor/js/MapManageForm.js';
import { LoadingScreen } from '../@zipc/editor/js/LoadingScreen.js';
import { ViewportCamera } from '../@zipc/editor/js/Viewport.Camera.js';

//
window.URL = window.URL || window.webkitURL;
window.BlobBuilder = window.BlobBuilder || window.WebKitBlobBuilder || window.MozBlobBuilder;

Number.prototype.format = function () {

    return this.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");

};

//

var editor = new Editor();

window.editor = editor; // Expose editor to Console
window.THREE = THREE; // Expose THREE to APP Scripts and Console

var loadingScreen = new LoadingScreen(editor);
document.body.appendChild(loadingScreen.dom);

var mapManageForm = new MapManageForm(editor);
document.body.appendChild(mapManageForm.dom);

var popupForm = new PopupForm(editor);
document.body.appendChild(popupForm.dom);

var labelForm = new LabelForm(editor);
document.body.appendChild(labelForm.dom);

var viewport = new Viewport(editor);
document.body.appendChild(viewport.dom);

var toolbar = new Toolbar(editor);
document.body.appendChild(toolbar.dom);

var viewportCamera = new ViewportCamera(editor);
document.body.appendChild(viewportCamera.dom);

var script = new Script(editor);
document.body.appendChild(script.dom);

var sidebar = new Sidebar(editor);
document.body.appendChild(sidebar.dom);

var player = new Player(editor);
document.body.appendChild(player.dom);

var menubar = new Menubar(editor);
document.body.appendChild(menubar.dom);

//

editor.storage.init(function () {

    editor.storage.get(function (state) {

        if (isLoadingFromHash) return;

        if (state !== undefined) {

            editor.fromJSON(state);

        }

        var selected = editor.config.getKey('selected');

        if (selected !== undefined) {

            editor.selectByUuid(selected);

        }

    });

    //

    var timeout;

    function saveState() {

        if (editor.config.getKey('autosave') === false) {

            return;

        }

        clearTimeout(timeout);

        timeout = setTimeout(function () {

            editor.signals.savingStarted.dispatch();

            timeout = setTimeout(function () {

                editor.storage.set(editor.toJSON());

                editor.signals.savingFinished.dispatch();

            }, 100);

        }, 1000);

    }

    var signals = editor.signals;

    signals.geometryChanged.add(saveState);
    signals.objectAdded.add(saveState);
    signals.objectChanged.add(saveState);
    signals.objectRemoved.add(saveState);
    signals.materialChanged.add(saveState);
    signals.scenarioDirectionChanged.add(saveState);
    signals.sceneBackgroundChanged.add(saveState);
    signals.sceneGraphChanged.add(saveState);
    signals.scriptChanged.add(saveState);
    signals.historyChanged.add(saveState);

});

//

document.addEventListener('dragover', function (event) {

    event.preventDefault();
    event.dataTransfer.dropEffect = 'copy';

}, false);

document.addEventListener('drop', function (event) {

    event.preventDefault();

    editor.loader.loadFiles(event.dataTransfer.files);

}, false);

function onWindowResize() {

    editor.signals.windowResize.dispatch();

}

window.addEventListener('resize', onWindowResize, false);

onWindowResize();

// 

var isLoadingFromHash = false;
