import * as THREE from 'three';

export namespace GeoUtils {

    export function createExtrudeBufferGeometry(
        points: THREE.Vector3[],
        width: number
    ): THREE.ExtrudeBufferGeometry {
        let shape = createShape(width);
        let opts = {
            steps: points.length,
            curveSegments: points.length,
            extrudePath: new THREE.CatmullRomCurve3(points),
            bevelEnabled: false
        };
        return new THREE.ExtrudeBufferGeometry(shape, opts);
    }

    function createShape(width: number): THREE.Shape {
        return new THREE.Shape()/*
            .moveTo(0.25, -width / 2)
            .lineTo(0.25, width / 2)
            .lineTo(-0.25, width / 2)
            .lineTo(-0.25, -width / 2)
            .lineTo(0.25, -width / 2);*/
            .moveTo(width / 2, -0.5 / 2)
            .lineTo(width / 2, 0.5 / 2)
            .lineTo(-width / 2, 0.5 / 2)
            .lineTo(-width / 2, -0.5 / 2)
            .lineTo(width / 2, -0.5 / 2);
    };

}