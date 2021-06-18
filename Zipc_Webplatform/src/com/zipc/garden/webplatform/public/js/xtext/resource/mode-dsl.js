// highlight rules for SCModel
define("xtext/resource/sc",["require","exports","module"], function(require, exports, module) {

	var oop = require("ace/lib/oop");
	var mText = require("ace/mode/text");
	var mTextHighlightRules = require("ace/mode/text_highlight_rules");
	
	var HighlightRules = function() {
		var keywords = "Bike|Car|EgoCar|Environment|Goal|Lane|Mid|Model|Object|Range|Road|Routes|Scenario|Start|average|biking|border|center|circular|clothoid_in|clothoid_out|cloud_density|connection|cubic_left|cubic_right|direction|driving|fog_density|height|height_angle|lane|lanechange_end|lanechange_start|left|length|light_intensity|max|median|min|mode|model|position|radius|rain_density|ramp_angle|reverse|right|shift|snow_density|straight|time_of_day|type|value|velocity|wheelbase|width";
		this.$rules = {
			"start": [
				{token: "comment", regex: "\\/\\/.*$"},
				{token: "comment", regex: "\\/\\*", next : "comment"},
				{token: "string", regex: '["](?:(?:\\\\.)|(?:[^"\\\\]))*?["]'},
				{token: "string", regex: "['](?:(?:\\\\.)|(?:[^'\\\\]))*?[']"},
				{token: "constant.numeric", regex: "[+-]?\\d+(?:(?:\\.\\d*)?(?:[eE][+-]?\\d+)?)?\\b"},
				{token: "lparen", regex: "[\\[{]"},
				{token: "rparen", regex: "[\\]}]"},
				{token: "keyword", regex: "\\b(?:" + keywords + ")\\b"}
			],
			"comment": [
				{token: "comment", regex: ".*?\\*\\/", next : "start"},
				{token: "comment", regex: ".+"}
			]
		};
	};
	oop.inherits(HighlightRules, mTextHighlightRules.TextHighlightRules);
	
	var Mode = function() {
		this.HighlightRules = HighlightRules;
	};
	oop.inherits(Mode, mText.Mode);
	Mode.prototype.getCompletions = function(state, session, pos, prefix) {
		return [];
	}
	
	exports.Mode = Mode;
});

// highlight rules for FMCModel
define("xtext/resource/fmc",["require","exports","module"], function(require, exports, module) {

	var oop = require("ace/lib/oop");
	var mText = require("ace/mode/text");
	var mTextHighlightRules = require("ace/mode/text_highlight_rules");

	var HighlightRules = function() {
		var keywords = "mutex|removes";
		this.$rules = {
			"start": [
				{token: "comment", regex: "\\/\\/.*$"},
				{token: "comment", regex: "\\/\\*", next : "comment"},
				{token: "string", regex: '["](?:(?:\\\\.)|(?:[^"\\\\]))*?["]'},
				{token: "string", regex: "['](?:(?:\\\\.)|(?:[^'\\\\]))*?[']"},
				{token: "constant.numeric", regex: "[+-]?\\d+(?:(?:\\.\\d*)?(?:[eE][+-]?\\d+)?)?\\b"},
				{token: "lparen", regex: "[(]"},
				{token: "rparen", regex: "[)]"},
				{token: "keyword", regex: "\\b(?:" + keywords + ")\\b"}
			],
			"comment": [
				{token: "comment", regex: ".*?\\*\\/", next : "start"},
				{token: "comment", regex: ".+"}
			]
		};
	};
	oop.inherits(HighlightRules, mTextHighlightRules.TextHighlightRules);
	
	var Mode = function() {
		this.HighlightRules = HighlightRules;
	};
	oop.inherits(Mode, mText.Mode);
	Mode.prototype.getCompletions = function(state, session, pos, prefix) {
		return [];
	}
	
	return {
		Mode: Mode
	};
});

