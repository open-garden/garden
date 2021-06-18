/**
 * @author mrdoob / http://mrdoob.com/
 */

import {
	BufferGeometry,
	Geometry,
	Line,
	Matrix3,
	Mesh,
	Vector2,
	Vector3
} from "../../../build/three.module.js";

var OBJExporter = function () { };

OBJExporter.prototype = {

	constructor: OBJExporter,

	parse: function (object) {

		var output = '';

		var indexVertex = 0;
		var indexVertexUvs = 0;
		var indexNormals = 0;

		var vertex = new Vector3();
		var normal = new Vector3();
		var uv = new Vector2();

		var i, j, k, l, m, face = [];

		var parseRoadStructure = function (roadStructure) {

			// name of the mesh object
			output += 'o ' + roadStructure.gid + '\n';

			var vOutput = '';
			var vtOutput = '';
			var vnOutput = '';
			var fOutput = '';

			for (let road of roadStructure.children) {

				let targetObjects = new Array();

				if (road.type === 'Road') {

					Array.prototype.push.apply(targetObjects, road.children.filter(child => child.type === 'Lane').flatMap(child => child.children))
					// targetObjects.concat(road.children.filter(child => child.type === 'Lane').flatMap(child => child.children));

				} else if (road.type === 'Junction') {

					Array.prototype.push.apply(targetObjects, road.children);
					// targetObjects.concat(road.flatMap(child => child.children));

				} else {

					continue;

				}

				for (let mesh of targetObjects) {

					var nbVertex = 0;
					var nbNormals = 0;
					var nbVertexUvs = 0;

					if (mesh.isMesh === true) {

						if (mesh.type === 'RoadMark.Sign' || mesh.type === 'RoadMark.Crosswalk' || mesh.type === 'RoadMark.TurningMark') continue;

						var geometry = mesh.geometry;

						var normalMatrixWorld = new Matrix3();

						if (geometry.isGeometry === true) {

							geometry = new BufferGeometry().setFromObject(mesh);

						}

						if (geometry.isBufferGeometry === true) {

							// shortcuts
							var vertices = geometry.getAttribute('position');
							var normals = geometry.getAttribute('normal');
							var uvs = geometry.getAttribute('uv');
							var indices = geometry.getIndex();

							// vertices

							if (vertices !== undefined) {

								for (i = 0, l = vertices.count; i < l; i++, nbVertex++) {

									vertex.x = vertices.getX(i);
									vertex.y = vertices.getY(i);
									vertex.z = vertices.getZ(i);

									// transform the vertex to world space
									vertex.applyMatrix4(mesh.matrixWorld);

									// transform the vertex to export format
									vOutput += 'v ' + vertex.x + ' ' + vertex.y + ' ' + vertex.z + '\n';

								}

							}

							// uvs

							if (uvs !== undefined) {

								for (i = 0, l = uvs.count; i < l; i++, nbVertexUvs++) {

									uv.x = uvs.getX(i);
									uv.y = uvs.getY(i);

									// transform the uv to export format
									vtOutput += 'vt ' + uv.x + ' ' + uv.y + '\n';

								}

							}

							// normals

							if (normals !== undefined) {

								normalMatrixWorld.getNormalMatrix(mesh.matrixWorld);

								for (i = 0, l = normals.count; i < l; i++, nbNormals++) {

									normal.x = normals.getX(i);
									normal.y = normals.getY(i);
									normal.z = normals.getZ(i);

									// transform the normal to world space
									normal.applyMatrix3(normalMatrixWorld).normalize();

									// transform the normal to export format
									vnOutput += 'vn ' + normal.x + ' ' + normal.y + ' ' + normal.z + '\n';

								}

							}

							// faces

							if (indices !== null) {

								for (i = 0, l = indices.count; i < l; i += 3) {

									for (m = 0; m < 3; m++) {

										j = indices.getX(i + m) + 1;

										face[m] = (indexVertex + j) + (normals || uvs ? '/' + (uvs ? (indexVertexUvs + j) : '') + (normals ? '/' + (indexNormals + j) : '') : '');

									}

									// transform the face to export format
									fOutput += 'f ' + face.join(' ') + "\n";

								}

							} else {

								for (i = 0, l = vertices.count; i < l; i += 3) {

									for (m = 0; m < 3; m++) {

										j = i + m + 1;

										face[m] = (indexVertex + j) + (normals || uvs ? '/' + (uvs ? (indexVertexUvs + j) : '') + (normals ? '/' + (indexNormals + j) : '') : '');

									}

									// transform the face to export format
									fOutput += 'f ' + face.join(' ') + "\n";

								}

							}

						} else {

							console.warn('THREE.OBJExporter.parseMesh(): geometry type unsupported', geometry);

						}

						// update index
						indexVertex += nbVertex;
						indexVertexUvs += nbVertexUvs;
						indexNormals += nbNormals;

					}

				}

			}

			// update output
			output += vOutput;
			output += vtOutput;
			output += vnOutput;
			output += fOutput;

		};

		var parseRoadMarkLine = function (roadStructure) {

			// name of the mesh object
			output += 'o ' + roadStructure.gid + '_RoadMark.Line' + '\n';

			var vOutput = '';
			var vtOutput = '';
			var vnOutput = '';
			var fOutput = '';

			for (let child of roadStructure.children) {

				let targetObjects = new Array();

				if (child.type === 'RoadMark.Line') {

					targetObjects.push(child);

				} else {

					continue;

				}

				for (let mesh of targetObjects) {

					var nbVertex = 0;
					var nbNormals = 0;
					var nbVertexUvs = 0;

					if (mesh.isMesh === true) {

						var geometry = mesh.geometry;

						var normalMatrixWorld = new Matrix3();

						if (geometry.isGeometry === true) {

							geometry = new BufferGeometry().setFromObject(mesh);

						}

						if (geometry.isBufferGeometry === true) {

							// shortcuts
							var vertices = geometry.getAttribute('position');
							var normals = geometry.getAttribute('normal');
							var uvs = geometry.getAttribute('uv');
							var indices = geometry.getIndex();

							// vertices

							if (vertices !== undefined) {

								for (i = 0, l = vertices.count; i < l; i++, nbVertex++) {

									vertex.x = vertices.getX(i);
									vertex.y = vertices.getY(i);
									vertex.z = vertices.getZ(i);

									// transform the vertex to world space
									vertex.applyMatrix4(mesh.matrixWorld);

									// transform the vertex to export format
									vOutput += 'v ' + vertex.x + ' ' + vertex.y + ' ' + vertex.z + '\n';

								}

							}

							// uvs

							if (uvs !== undefined) {

								for (i = 0, l = uvs.count; i < l; i++, nbVertexUvs++) {

									uv.x = uvs.getX(i);
									uv.y = uvs.getY(i);

									// transform the uv to export format
									vtOutput += 'vt ' + uv.x + ' ' + uv.y + '\n';

								}

							}

							// normals

							if (normals !== undefined) {

								normalMatrixWorld.getNormalMatrix(mesh.matrixWorld);

								for (i = 0, l = normals.count; i < l; i++, nbNormals++) {

									normal.x = normals.getX(i);
									normal.y = normals.getY(i);
									normal.z = normals.getZ(i);

									// transform the normal to world space
									normal.applyMatrix3(normalMatrixWorld).normalize();

									// transform the normal to export format
									vnOutput += 'vn ' + normal.x + ' ' + normal.y + ' ' + normal.z + '\n';

								}

							}

							// faces

							if (indices !== null) {

								for (i = 0, l = indices.count; i < l; i += 3) {

									for (m = 0; m < 3; m++) {

										j = indices.getX(i + m) + 1;

										if (indexVertex + j > 606) {
											console.log();
										}

										face[m] = (indexVertex + j) + (normals || uvs ? '/' + (uvs ? (indexVertexUvs + j) : '') + (normals ? '/' + (indexNormals + j) : '') : '');

									}

									// transform the face to export format
									fOutput += 'f ' + face.join(' ') + "\n";

								}

							} else {

								for (i = 0, l = vertices.count; i < l; i += 3) {

									for (m = 0; m < 3; m++) {

										j = i + m + 1;

										face[m] = (indexVertex + j) + (normals || uvs ? '/' + (uvs ? (indexVertexUvs + j) : '') + (normals ? '/' + (indexNormals + j) : '') : '');

									}

									// transform the face to export format
									fOutput += 'f ' + face.join(' ') + "\n";

								}

							}

						} else {

							console.warn('THREE.OBJExporter.parseMesh(): geometry type unsupported', geometry);

						}

						// update index
						indexVertex += nbVertex;
						indexVertexUvs += nbVertexUvs;
						indexNormals += nbNormals;

					}

				}

			}

			// update output
			output += vOutput;
			output += vtOutput;
			output += vnOutput;
			output += fOutput;

		};

		var parseRoadMarkSign = function (roadStructure) {

			// name of the mesh object
			output += 'o ' + roadStructure.gid + '_RoadMark.Sign' + '\n';

			var vOutput = '';
			var vtOutput = '';
			var vnOutput = '';
			var fOutput = '';

			for (let road of roadStructure.children) {

				let targetObjects = new Array();

				if (road.type === 'Road') {

					Array.prototype.push.apply(targetObjects, road.children.filter(child => child.type === 'RoadMark.Sign'));
					Array.prototype.push.apply(targetObjects, road.children.filter(child => child.type === 'Lane').flatMap(child => child.children));

				} else if (road.type === 'Junction') {

					Array.prototype.push.apply(targetObjects, road.children);

				} else {

					continue;

				}

				for (let mesh of targetObjects) {

					var nbVertex = 0;
					var nbNormals = 0;
					var nbVertexUvs = 0;

					if (mesh.isMesh === true) {

						if (!(mesh.type === 'RoadMark.Sign' || mesh.type === 'RoadMark.TurningMark')) continue;

						var geometry = mesh.geometry;

						var normalMatrixWorld = new Matrix3();

						if (geometry.isGeometry === true) {

							geometry = new BufferGeometry().setFromObject(mesh);

						}

						if (geometry.isBufferGeometry === true) {

							// shortcuts
							var vertices = geometry.getAttribute('position');
							var normals = geometry.getAttribute('normal');
							var uvs = geometry.getAttribute('uv');
							var indices = geometry.getIndex();

							// vertices

							if (vertices !== undefined) {

								for (i = 0, l = vertices.count; i < l; i++, nbVertex++) {

									vertex.x = vertices.getX(i);
									vertex.y = vertices.getY(i);
									vertex.z = vertices.getZ(i);

									// transform the vertex to world space
									vertex.applyMatrix4(mesh.matrixWorld);

									// transform the vertex to export format
									vOutput += 'v ' + vertex.x + ' ' + vertex.y + ' ' + vertex.z + '\n';

								}

							}

							// uvs

							if (uvs !== undefined) {

								for (i = 0, l = uvs.count; i < l; i++, nbVertexUvs++) {

									uv.x = uvs.getX(i);
									uv.y = uvs.getY(i);

									// transform the uv to export format
									vtOutput += 'vt ' + uv.x + ' ' + uv.y + '\n';

								}

							}

							// normals

							if (normals !== undefined) {

								normalMatrixWorld.getNormalMatrix(mesh.matrixWorld);

								for (i = 0, l = normals.count; i < l; i++, nbNormals++) {

									normal.x = normals.getX(i);
									normal.y = normals.getY(i);
									normal.z = normals.getZ(i);

									// transform the normal to world space
									normal.applyMatrix3(normalMatrixWorld).normalize();

									// transform the normal to export format
									vnOutput += 'vn ' + normal.x + ' ' + normal.y + ' ' + normal.z + '\n';

								}

							}

							// faces

							if (indices !== null) {

								for (i = 0, l = indices.count; i < l; i += 3) {

									for (m = 0; m < 3; m++) {

										j = indices.getX(i + m) + 1;

										face[m] = (indexVertex + j) + (normals || uvs ? '/' + (uvs ? (indexVertexUvs + j) : '') + (normals ? '/' + (indexNormals + j) : '') : '');

									}

									// transform the face to export format
									fOutput += 'f ' + face.join(' ') + "\n";

								}

							} else {

								for (i = 0, l = vertices.count; i < l; i += 3) {

									for (m = 0; m < 3; m++) {

										j = i + m + 1;

										face[m] = (indexVertex + j) + (normals || uvs ? '/' + (uvs ? (indexVertexUvs + j) : '') + (normals ? '/' + (indexNormals + j) : '') : '');

									}

									// transform the face to export format
									fOutput += 'f ' + face.join(' ') + "\n";

								}

							}

						} else {

							console.warn('THREE.OBJExporter.parseMesh(): geometry type unsupported', geometry);

						}

						// update index
						indexVertex += nbVertex;
						indexVertexUvs += nbVertexUvs;
						indexNormals += nbNormals;

					}

				}

			}

			// update output
			output += vOutput;
			output += vtOutput;
			output += vnOutput;
			output += fOutput;

		};

		var parseRoadMarkCrosswalk = function (roadStructure) {

			// name of the mesh object
			output += 'o ' + roadStructure.gid + '_RoadMark.Crosswalk' + '\n';

			var vOutput = '';
			var vtOutput = '';
			var vnOutput = '';
			var fOutput = '';

			for (let road of roadStructure.children) {

				let targetObjects = new Array();

				if (road.type === 'Road') {

					Array.prototype.push.apply(targetObjects, road.children);
					// targetObjects.concat(road.children.filter(child => child.type === 'Lane').flatMap(child => child.children));

				} else if (road.type === 'Junction') {

					Array.prototype.push.apply(targetObjects, road.children);
					// targetObjects.concat(road.flatMap(child => child.children));

				} else if (road.type === 'RoadMark.Crosswalk') {

					targetObjects.push(road);

				} else {

					continue;

				}

				for (let mesh of targetObjects) {

					var nbVertex = 0;
					var nbNormals = 0;
					var nbVertexUvs = 0;

					if (mesh.isMesh === true) {

						if (mesh.type !== 'RoadMark.Crosswalk') continue;

						var geometry = mesh.geometry;

						var normalMatrixWorld = new Matrix3();

						if (geometry.isGeometry === true) {

							geometry = new BufferGeometry().setFromObject(mesh);

						}

						if (geometry.isBufferGeometry === true) {

							// shortcuts
							var vertices = geometry.getAttribute('position');
							var normals = geometry.getAttribute('normal');
							var uvs = geometry.getAttribute('uv');
							var indices = geometry.getIndex();

							// vertices

							if (vertices !== undefined) {

								for (i = 0, l = vertices.count; i < l; i++, nbVertex++) {

									vertex.x = vertices.getX(i);
									vertex.y = vertices.getY(i);
									vertex.z = vertices.getZ(i);

									// transform the vertex to world space
									vertex.applyMatrix4(mesh.matrixWorld);

									// transform the vertex to export format
									vOutput += 'v ' + vertex.x + ' ' + vertex.y + ' ' + vertex.z + '\n';

								}

							}

							// uvs

							if (uvs !== undefined) {

								for (i = 0, l = uvs.count; i < l; i++, nbVertexUvs++) {

									uv.x = uvs.getX(i);
									uv.y = uvs.getY(i);

									// transform the uv to export format
									vtOutput += 'vt ' + uv.x + ' ' + uv.y + '\n';

								}

							}

							// normals

							if (normals !== undefined) {

								normalMatrixWorld.getNormalMatrix(mesh.matrixWorld);

								for (i = 0, l = normals.count; i < l; i++, nbNormals++) {

									normal.x = normals.getX(i);
									normal.y = normals.getY(i);
									normal.z = normals.getZ(i);

									// transform the normal to world space
									normal.applyMatrix3(normalMatrixWorld).normalize();

									// transform the normal to export format
									vnOutput += 'vn ' + normal.x + ' ' + normal.y + ' ' + normal.z + '\n';

								}

							}

							// faces

							if (indices !== null) {

								for (i = 0, l = indices.count; i < l; i += 3) {

									for (m = 0; m < 3; m++) {

										j = indices.getX(i + m) + 1;

										if (indexVertex + j > 606) {
											console.log();
										}

										face[m] = (indexVertex + j) + (normals || uvs ? '/' + (uvs ? (indexVertexUvs + j) : '') + (normals ? '/' + (indexNormals + j) : '') : '');

									}

									// transform the face to export format
									fOutput += 'f ' + face.join(' ') + "\n";

								}

							} else {

								for (i = 0, l = vertices.count; i < l; i += 3) {

									for (m = 0; m < 3; m++) {

										j = i + m + 1;

										face[m] = (indexVertex + j) + (normals || uvs ? '/' + (uvs ? (indexVertexUvs + j) : '') + (normals ? '/' + (indexNormals + j) : '') : '');

									}

									// transform the face to export format
									fOutput += 'f ' + face.join(' ') + "\n";

								}

							}

						} else {

							console.warn('THREE.OBJExporter.parseMesh(): geometry type unsupported', geometry);

						}

						// update index
						indexVertex += nbVertex;
						indexVertexUvs += nbVertexUvs;
						indexNormals += nbNormals;

					}

				}

			}

			// update output
			output += vOutput;
			output += vtOutput;
			output += vnOutput;
			output += fOutput;

		};


		var parseMesh = function (mesh) {

			var nbVertex = 0;
			var nbNormals = 0;
			var nbVertexUvs = 0;

			var geometry = mesh.geometry;

			var normalMatrixWorld = new Matrix3();

			if (geometry.isGeometry === true) {

				geometry = new BufferGeometry().setFromObject(mesh);

			}

			if (geometry.isBufferGeometry === true) {

				// shortcuts
				var vertices = geometry.getAttribute('position');
				var normals = geometry.getAttribute('normal');
				var uvs = geometry.getAttribute('uv');
				var indices = geometry.getIndex();

				// name of the mesh object
				if (mesh.parent !== null && mesh.parent.type === 'Lane') {
					output += 'o ' + mesh.parent.name + '\n';
				} else {
					output += 'o ' + mesh.name + '\n';
				}

				// name of the mesh material
				if (mesh.material && mesh.material.name) {

					output += 'usemtl ' + mesh.material.name + '\n';

				}

				// vertices

				if (vertices !== undefined) {

					for (i = 0, l = vertices.count; i < l; i++, nbVertex++) {

						vertex.x = vertices.getX(i);
						vertex.y = vertices.getY(i);
						vertex.z = vertices.getZ(i);

						// transform the vertex to world space
						vertex.applyMatrix4(mesh.matrixWorld);

						// transform the vertex to export format
						output += 'v ' + vertex.x + ' ' + vertex.y + ' ' + vertex.z + '\n';

					}

				}

				// uvs

				if (uvs !== undefined) {

					for (i = 0, l = uvs.count; i < l; i++, nbVertexUvs++) {

						uv.x = uvs.getX(i);
						uv.y = uvs.getY(i);

						// transform the uv to export format
						output += 'vt ' + uv.x + ' ' + uv.y + '\n';

					}

				}

				// normals

				if (normals !== undefined) {

					normalMatrixWorld.getNormalMatrix(mesh.matrixWorld);

					for (i = 0, l = normals.count; i < l; i++, nbNormals++) {

						normal.x = normals.getX(i);
						normal.y = normals.getY(i);
						normal.z = normals.getZ(i);

						// transform the normal to world space
						normal.applyMatrix3(normalMatrixWorld).normalize();

						// transform the normal to export format
						output += 'vn ' + normal.x + ' ' + normal.y + ' ' + normal.z + '\n';

					}

				}

				// faces

				if (indices !== null) {

					for (i = 0, l = indices.count; i < l; i += 3) {

						for (m = 0; m < 3; m++) {

							j = indices.getX(i + m) + 1;

							face[m] = (indexVertex + j) + (normals || uvs ? '/' + (uvs ? (indexVertexUvs + j) : '') + (normals ? '/' + (indexNormals + j) : '') : '');

						}

						// transform the face to export format
						output += 'f ' + face.join(' ') + "\n";

					}

				} else {

					for (i = 0, l = vertices.count; i < l; i += 3) {

						for (m = 0; m < 3; m++) {

							j = i + m + 1;

							face[m] = (indexVertex + j) + (normals || uvs ? '/' + (uvs ? (indexVertexUvs + j) : '') + (normals ? '/' + (indexNormals + j) : '') : '');

						}

						// transform the face to export format
						output += 'f ' + face.join(' ') + "\n";

					}

				}

			} else {

				console.warn('THREE.OBJExporter.parseMesh(): geometry type unsupported', geometry);

			}

			// update index
			indexVertex += nbVertex;
			indexVertexUvs += nbVertexUvs;
			indexNormals += nbNormals;

		};

		var parseLine = function (line) {

			var nbVertex = 0;

			var geometry = line.geometry;
			var type = line.type;

			if (geometry.isGeometry === true) {

				geometry = new BufferGeometry().setFromObject(line);

			}

			if (geometry.isBufferGeometry === true) {

				// shortcuts
				var vertices = geometry.getAttribute('position');

				// name of the line object
				output += 'o ' + line.name + '\n';

				if (vertices !== undefined) {

					for (i = 0, l = vertices.count; i < l; i++, nbVertex++) {

						vertex.x = vertices.getX(i);
						vertex.y = vertices.getY(i);
						vertex.z = vertices.getZ(i);

						// transform the vertex to world space
						vertex.applyMatrix4(line.matrixWorld);

						// transform the vertex to export format
						output += 'v ' + vertex.x + ' ' + vertex.y + ' ' + vertex.z + '\n';

					}

				}

				if (type === 'Line') {

					output += 'l ';

					for (j = 1, l = vertices.count; j <= l; j++) {

						output += (indexVertex + j) + ' ';

					}

					output += '\n';

				}

				if (type === 'LineSegments') {

					for (j = 1, k = j + 1, l = vertices.count; j < l; j += 2, k = j + 1) {

						output += 'l ' + (indexVertex + j) + ' ' + (indexVertex + k) + '\n';

					}

				}

			} else {

				console.warn('THREE.OBJExporter.parseLine(): geometry type unsupported', geometry);

			}

			// update index
			indexVertex += nbVertex;

		};

		var traverseVisible = function (object, callback) {

			if (object.visible === false) return;

			parseRoadStructure(object);

			parseRoadMarkLine(object);

			parseRoadMarkSign(object);

			parseRoadMarkCrosswalk(object);

		};

		traverseVisible(object, function (child) {

			if (child.parent !== null && child.parent.type === 'ArrowHelper') {

				return;

			}

			if (child.isMesh === true) {

				parseMesh(child);

			}

			if (child instanceof Line) {

				parseLine(child);

			}

		});

		/*object.traverseVisible(function (child) {

			if (child.parent !== null && child.parent.type === 'ArrowHelper') {

				return;

			}

			if (child.isMesh === true) {

				parseMesh(child);

			}

			if (child instanceof Line) {

				parseLine(child);

			}

		});*/

		return output;

	}

};

export { OBJExporter };
