import * as THREE from 'three';

const LINE_MODE = {
    "none": { "dashSize": 0, "gapSize": Number.MAX_VALUE, "precRate": 1 },
    "solid": { "dashSize": Number.MAX_VALUE, "gapSize": 0, "precRate": 1 },
    "broken": { "dashSize": 3, "gapSize": 1.5, "precRate": 0.5 },
    "broken2-2": { "dashSize": 4, "gapSize": 2, "precRate": 1 },
    "center5-5": { "dashSize": 10, "gapSize": 5, "precRate": 1 },
    "center8-12": { "dashSize": 20, "gapSize": 12, "precRate": 1 }
};

interface LineOptions {
    /**
     * @default 'right'
     */
    position?: 'left' | 'right';
    /**
     * @default 0.15
     */
    width?: number;
    /**
     * @default 0.0
     */
    offset?: number;
    /**
     * @default 0xFFFFFF
     */
    color?: THREE.Color | string | number;
}

class LineSegment extends THREE.Object3D {

    readonly type = 'RoadMark.LineSegment';

    lane: [number, any];
    start: number;
    end: number;

    constructor(
        lane?: [number, any],
        start?: number,
        end?: number
    ) {
        super();
        this.name = lane !== undefined ? lane[1] : 'none';
        this.lane = lane !== undefined ? lane : [Number.MIN_SAFE_INTEGER, { "road": "", "lane": "" }];
        this.start = start !== undefined ? start : -1;
        this.end = end !== undefined ? end : -1;
    }

    toJSON(): any {
        const data = {
            'type': this.type,
            'lane': this.lane[1],
            'start': this.start,
            'end': this.end
        };

        return data;
    }
}

class Line extends THREE.Mesh {

    readonly type = 'RoadMark.Line';

    /**
     * @default 'solid'
     */
    mode: 'none' | 'solid' | 'broken' | 'center5-5' | 'center8-12' | string;

    parameters: LineOptions;

    segments: Array<LineSegment>;

    constructor(
        mode?: string,
        parameters?: LineOptions,
        segments?: Array<LineSegment>
    ) {
        super();
        this.mode = mode !== undefined ? mode : 'solid';
        this.parameters = parameters !== undefined ? parameters : { position: 'right', width: 0.15, offset: 0.0, color: 0xFFFFFF };
        this.segments = segments !== undefined ? segments : new Array<LineSegment>();

        let vertices = (this.geometry as THREE.BufferGeometry).attributes.position;
        if (vertices === undefined) {
            (this.geometry as THREE.BufferGeometry).attributes.position = new THREE.Float32BufferAttribute(0, 3);
        }

        if (this.parameters.color !== undefined) {
            (this.material as THREE.MeshBasicMaterial).color.setHex(this.parameters.color as number);
        }

        this.computeGeometry();
    }

    computeGeometry(): void {
        if (this.segments.length === 0) return;
        for (let s of this.segments) {
            this.add(s);
        }
    }

    toJSON(): any {
        const data = {
            'name': this.name,
            'type': this.type,
            'mode': this.mode,
            'parameters': this.parameters,
            'segments': new Array<any>()
        };

        for (let segment of this.segments) {
            data.segments.push(segment.toJSON());
        }

        return data;
    }

    copy(source: this, recursive?: boolean): this {
        super.copy(source, recursive);

        this.mode = source.mode;
        this.parameters = source.parameters;
        this.segments = source.segments;

        this.material = source.material;
        this.geometry = source.geometry;

        return this;
    }
}

export { Line, LineSegment, LINE_MODE }