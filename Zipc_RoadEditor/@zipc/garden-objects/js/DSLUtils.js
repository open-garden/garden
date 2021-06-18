

import { Utils } from './Utils.js';

var DSLUtils = (function () {

    function template(strings, ...keys) {
        return (function (...values) {
            var dict = values[values.length - 1] || {};
            var result = [strings[0]];
            keys.forEach(function (key, i) {

                var value;

                if (key === 'scenario_roads') {
                    if (Array.isArray(dict['roads'])) {
                        value = '';
                        for (let road of dict['roads']) {
                            if (road !== null) {
                                value += serializers['road'](values[0] + '    ', road);
                            }
                        }
                    }
                } else if (key === 'scenario_junctions') {
                    if (Array.isArray(dict['junctions'])) {
                        value = '';
                        for (let junction of dict['junctions']) {
                            if (junction !== null) {
                                value += serializers['junction'](values[0] + '    ', junction);
                            }
                        }
                    }
                } else if (key === 'scenario_objects') {
                    if (Array.isArray(dict['objects'])) {
                        value = '';
                        for (let object of dict['objects']) {
                            if (object !== null) {
                                value += serializers['object'](values[0] + '    ', object);
                            }
                        }
                    }
                } else if (key === 'scenario_trajectories') {
                    if (Array.isArray(dict['trajectories'])) {
                        value = '';
                        for (let trajectory of dict['trajectories']) {
                            if (trajectory !== null) {
                                value += serializers['trajectory'](values[0] + '    ', trajectory);
                            }
                        }
                    }
                } else if (key === 'trajectory_mode') {
                    value = '';
                    if (dict['mode'] !== null) {
                        value += serializers['actor_points'](values[0] + '    ', dict['mode']);
                    }
                } else if (key === 'trajectory_average') {
                    value = '';
                    if (dict['average'] !== null) {
                        value += serializers['actor_points'](values[0] + '    ', dict['average']);
                    }
                } else if (key === 'trajectory_edge') {
                    value = '';
                    if (dict['edge'] !== null) {
                        value += serializers['actor_points'](values[0] + '    ', dict['edge']);
                    }
                } else if (key === 'road_priority') {
                    value = dict['priorityRoad'] ? 'priorityRoad' : '';
                } else if (key === 'road_connection') {
                    if (dict['connection'] !== undefined && dict['connection'] !== null && dict['connection'] !== '' && dict['connection'] !== Utils.MIN_INTEGER_STRING) {
                        value = 'connection: ' + dict['connection'];
                        value += ' ';
                        value += dict['reverse'] ? 'predecessor' : 'successor'
                    }
                } else if (key === 'road_lanes') {
                    if (Array.isArray(dict['lanes'])) {
                        value = '';
                        for (let lane of dict['lanes']) {
                            if (lane !== null) {
                                value += serializers['lane'](values[0] + '    ', lane);
                            }
                        }
                    }
                } else if (key === 'junctions_entries') {
                    if (Array.isArray(dict['connections'])) {
                        value = '';
                        for (let connection of dict['connections']) {
                            if (connection.id !== undefined && connection.id !== null && connection.id !== '' && connection.id !== Utils.MIN_INTEGER_STRING) {
                                value += serializers['junctionEntry'](values[0] + '    ', connection);
                            }
                        }
                    }
                } else if (key === 'actor_routes') {
                    if (Array.isArray(dict['routes'])) {
                        value = '';
                        for (let route of dict['routes']) {
                            if (route !== null) {
                                if (route.entities) {
                                    value += serializers['actor_routes'](values[0] + '    ', route);
                                } else if (route.points) {
                                    value += serializers['actor_points'](values[0] + '    ', route);
                                }
                            }
                        }
                    }
                } else if (key === 'routes_entries') {
                    if (Array.isArray(dict['entities'])) {
                        value = '';
                        for (let entry of dict['entities']) {
                            if (entry !== null) {
                                value += serializers['routes_' + entry.type](values[0] + '    ', entry);
                            }
                        }
                    }
                } else if (key === 'waypoint_points') {
                    if (Array.isArray(dict['points'])) {
                        value = '';
                        for (let point of dict['points']) {
                            if (point !== null) {
                                value += serializers['point'](values[0] + '    ', point);
                            }
                        }
                    }
                } else {
                    value = Number.isInteger(key) ? values[key] : dict[key];
                }


                if (typeof (value) === 'object') {
                    value = serializers[key](values[0] + '    ', value);
                }

                result.push(value, strings[i + 1]);
            });
            return result.join('');
        });
    }

    var scenarioSerializer = template`
${0}Scenario ${'id'} { 
${0}    direction: ${'direction'}
${0}    ${'scenario_roads'}
${0}    ${'scenario_junctions'}
${0}    ${'egoCar'}
${0}    ${'scenario_objects'}
${0}    ${'scenario_trajectories'}
${0}}`;

    var roadSerializer = template`
${0}Road ${'id'} {
${0}    ${'road_priority'}
${0}    type: ${'type'}
${0}    ${'point'}
${0}    length: ${'length'}
${0}    height: ${'height'}
${0}    ramp_angle: ${'ramp_angle'}
${0}    ${'road_connection'}
${0}    radius: ${'radius'}
${0}    ${'road_lanes'}
${0}}`;

    var laneSerializer = template`
${0}Lane ${'id'} { 
${0}    type: ${'type'}
${0}    position: ${'position'}
${0}    width: ${'width'}
${0}}`;

    var junctionSerializer = template`
${0}Junction ${'id'} { 
${0}    ${'junctions_entries'}
${0}}`;

    var junctionEntrySerializer = template`
${0}${'id'}<${'type'}>`;

    var pointSerializer = template`
${0}Point { x: ${'x'} y: ${'y'} z: ${'z'} roll: ${'roll'} yaw: ${'yaw'} pitch: ${'pitch'} }`;

    var egoCarSerializer = template`
${0}EgoCar { 
${0}    ${'actor_routes'}
${0}}`;

    var objectSerializer = template`
${0}Object ${'id'} { 
${0}    ${'actor_routes'}
${0}}`;

    var trajectorySerializer = template`
${0}Trajectory ${'id'} { 
${0}    ${'actor_routes'}
${0}    mode: ${'trajectory_mode'}
${0}    average: ${'trajectory_average'}
${0}    edge: ${'trajectory_edge'}
${0}}`;

    var routesSerializer = template`
${0}Routes {
${0}    accel: ${'accel'}
${0}    ${'routes_entries'}
${0}}`;

    var wayPointsSerializer = template`
${0}WayPoint {
${0}    accel: ${'accel'}
${0}    ${'waypoint_points'}
${0}}`;

    var routesStartSerializer = template`
${0}Start {
${0}    lane: ${'lane'}
${0}    shift: ${'shift'}
${0}    lanechange_start: ${'lanechange_start'}
${0}    lanechange_end: ${'lanechange_end'}
${0}    velocity: ${'velocity'}
${0}}`;

    var routesMidSerializer = template`
${0}Mid {
${0}    lane: ${'lane'}
${0}    lanechange_start: ${'lanechange_start'}
${0}    lanechange_end: ${'lanechange_end'}
${0}    velocity: ${'velocity'}
${0}}`;

    var routesGoalSerializer = template`
${0}Goal {
${0}    lane: ${'lane'}
${0}    shift: ${'shift'}
${0}    lanechange_start: ${'lanechange_start'}
${0}    lanechange_end: ${'lanechange_end'}
${0}    velocity: ${'velocity'}
${0}}`;

    var serializers = {};
    serializers['scenario'] = scenarioSerializer;
    serializers['road'] = roadSerializer;
    serializers['point'] = pointSerializer;
    serializers['lane'] = laneSerializer;
    serializers['junction'] = junctionSerializer;
    serializers['junctionEntry'] = junctionEntrySerializer;
    serializers['egoCar'] = egoCarSerializer;
    serializers['object'] = objectSerializer;
    serializers['trajectory'] = trajectorySerializer;
    serializers['actor_routes'] = routesSerializer;
    serializers['actor_points'] = wayPointsSerializer;
    serializers['routes_start'] = routesStartSerializer;
    serializers['routes_mid'] = routesMidSerializer;
    serializers['routes_goal'] = routesGoalSerializer;


    return {
        scenarioSerializer: scenarioSerializer
    };

})();

export { DSLUtils };