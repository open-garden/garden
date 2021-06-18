
import { Command } from '../Command.js';
import * as THREE from 'three';

import * as GARDEN from '../../../garden-objects/js/roads/GardenObjects.js';

/**
 * @param editor Editor
 * @param origin GARDEN.RoadObject
 * @constructor
 */
var CloneAndConnectRoadObjectCommand = function (editor, origin) {

	Command.call(this, editor);

	this.type = 'CloneAndConnectRoadObjectCommand';

	this.origin = origin
	if (origin !== undefined) {

		this.name = 'Clone RoadObject: ' + origin.name;

	}

	this.object = new GARDEN.RoadObject();

};

CloneAndConnectRoadObjectCommand.prototype = {

	execute: function () {

		const wireframe = editor.config.getKey('wireframe')

		var originData = this.origin.metalData;
		if (originData === null) return;

		var roadData = JSON.parse(JSON.stringify(originData));
		for (let lane of roadData.lanes.center) {
			if (lane.hasOwnProperty('sprite01')) delete lane['sprite01'];
			if (lane.hasOwnProperty('sprite02')) delete lane['sprite02'];
			if (lane.hasOwnProperty('spriteEdge')) delete lane['spriteEdge'];
			break;
		}
		for (let lane of roadData.lanes.left) {
			if (lane.hasOwnProperty('sprite01')) delete lane['sprite01'];
			if (lane.hasOwnProperty('sprite02')) delete lane['sprite02'];
			if (lane.hasOwnProperty('spriteEdge')) delete lane['spriteEdge'];
		}
		for (let lane of roadData.lanes.right) {
			if (lane.hasOwnProperty('sprite01')) delete lane['sprite01'];
			if (lane.hasOwnProperty('sprite02')) delete lane['sprite02'];
			if (lane.hasOwnProperty('spriteEdge')) delete lane['spriteEdge'];
		}

		roadData.connection = { "road": this.origin.name, "lane": "" };
		this.object.parent = this.editor.scene;
		this.object.setFromMetalData(roadData);
		this.editor.addObject(this.object);
		for (let child of this.object.children) {
			if (child.type === 'Lane') {
				editor.signals.objectAdded.dispatch(child);
			}
		}

		if (roadData.hasOwnProperty('point')) delete roadData['point'];

		this.origin.connectedTo.add(this.object);
		var connectionInfo = roadData.reverse ? this.origin.getNegative() : this.origin.getPositive();
		this.object['metalData']['connectionType'] = this.origin.type === 'Lane' ? this.origin.metalData.position : 'center';
		this.object['metalData']['connectionId'] = '' + this.origin.id;
		this.object['metalData']['rotationZ'] = connectionInfo.rotationZ;

		this.object.position.copy(connectionInfo.position);
		this.object.rotation.copy(new THREE.Euler(0, connectionInfo.rotationY, 0), 'XYZ');
		this.object.setFromMetalData(this.object.metalData);

		this.editor.select(this.object);

	},

	undo: function () {

		if (this.object !== undefined) {
			this.editor.removeObject(this.object);
			this.editor.deselect();
		}

	},

	toJSON: function () {

		var output = Command.prototype.toJSON.call(this);
		output.object = this.object.toJSON();

		return output;

	},

	fromJSON: function (json) {

		Command.prototype.fromJSON.call(this, json);

		this.object = this.editor.objectByUuid(json.object.object.uuid);

		if (this.object === undefined) {

			var loader = new THREE.ObjectLoader();
			this.object = loader.parse(json.object);

		}

	}

};

export { CloneAndConnectRoadObjectCommand };
