import * as THREE from 'three';

const _axis = new THREE.Vector3();

class GOArrowLine extends THREE.Object3D {

    readonly type = 'GOArrowLine';

    line: THREE.Line;
    cone: THREE.Mesh;

    constructor(
        color?: number,
        headLength?: number,
        headWidth?: number) {

        super();
        // dir is assumed to be normalized

        if (color === undefined) color = 0x000000;
        if (headLength === undefined) headLength = 1;
        if (headWidth === undefined) headWidth = 1;

        let _lineGeometry = new THREE.BufferGeometry();
        _lineGeometry.setAttribute('position', new THREE.Float32BufferAttribute([0, 0, 0, 0, 0, 0], 3));
        let _coneGeometry = new THREE.CylinderBufferGeometry(0, 0.5, 1, 5, 1);
        _coneGeometry.translate(0, - 0.5, 0);

        let origin = new THREE.Vector3(0, 0, 0);
        this.position.copy(origin);

        this.line = new THREE.Line(_lineGeometry, new THREE.LineBasicMaterial({ color: color, toneMapped: false }));
        this.line.matrixAutoUpdate = false;
        this.add(this.line);

        this.cone = new THREE.Mesh(_coneGeometry, new THREE.MeshBasicMaterial({ color: color, toneMapped: false }));
        this.cone.matrixAutoUpdate = false;
        this.add(this.cone);

    }

    setFromPoints(points: THREE.Vector3[]): GOArrowLine {
        if (points.length < 2) {
            points = [new THREE.Vector3(0, 0, 0), new THREE.Vector3(0, 0, 0)];
        }

        let line3 = new THREE.Line3(points[points.length - 2], points[points.length - 1]);
        let dir = line3.delta(new THREE.Vector3()).normalize();
        let origin = line3.at(1, new THREE.Vector3());

        this.line.geometry.setFromPoints(points);
        this.line.updateMatrix();

        this.cone.position.copy(origin);
        if (dir.y > 0.99999) {
            this.quaternion.set(0, 0, 0, 1);
        } else if (dir.y < - 0.99999) {
            this.cone.quaternion.set(1, 0, 0, 0);
        } else {
            _axis.set(dir.z, 0, - dir.x).normalize();
            const radians = Math.acos(dir.y);
            this.cone.quaternion.setFromAxisAngle(_axis, radians);
        }
        this.cone.updateMatrix();

        return this;
    }

    setDirection(dir: THREE.Vector3): void {
        // dir is assumed to be normalized
        if (dir.y > 0.99999) {
            this.quaternion.set(0, 0, 0, 1);
        } else if (dir.y < - 0.99999) {
            this.quaternion.set(1, 0, 0, 0);
        } else {
            _axis.set(dir.z, 0, - dir.x).normalize();
            const radians = Math.acos(dir.y);
            this.quaternion.setFromAxisAngle(_axis, radians);
        }

    }

    setLength(length: number, headLength?: number, headWidth?: number) {
        if (headLength === undefined) headLength = 0.2 * length;
        if (headWidth === undefined) headWidth = 0.2 * headLength;
        this.line.scale.set(1, Math.max(0.0001, length - headLength), 1); // see #17458
        this.line.updateMatrix();
        this.cone.scale.set(headWidth, headLength, headWidth);
        this.cone.position.y = length;
        this.cone.updateMatrix();

    }

    setColor(color: number): void {
        (this.line.material as THREE.LineBasicMaterial).color.set(color);
        (this.cone.material as THREE.MeshBasicMaterial).color.set(color);
    }

    copy(source: any): any {
        super.copy(source, false);
        this.line.copy(source.line);
        this.cone.copy(source.cone);
        return this;
    }

}


export { GOArrowLine };