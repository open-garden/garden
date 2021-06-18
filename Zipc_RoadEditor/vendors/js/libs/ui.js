/**
 * @author mrdoob / http://mrdoob.com/
 */

var UIElement = function (dom) {

	this.dom = dom;

};

UIElement.prototype = {

	add: function () {

		for (var i = 0; i < arguments.length; i++) {

			var argument = arguments[i];

			if (argument instanceof UIElement) {

				this.dom.appendChild(argument.dom);

			} else {

				console.error('UIElement:', argument, 'is not an instance of UIElement.');

			}

		}

		return this;

	},

	remove: function () {

		for (var i = 0; i < arguments.length; i++) {

			var argument = arguments[i];

			if (argument instanceof UIElement) {

				this.dom.removeChild(argument.dom);

			} else {

				console.error('UIElement:', argument, 'is not an instance of UIElement.');

			}

		}

		return this;

	},

	clear: function () {

		while (this.dom.children.length) {

			this.dom.removeChild(this.dom.lastChild);

		}

	},

	setId: function (id) {

		this.dom.id = id;

		return this;

	},

	getId: function () {

		return this.dom.id;

	},

	setClass: function (name) {

		this.dom.className = name;

		return this;

	},

	addClass: function (name) {

		this.dom.classList.add(name);

		return this;

	},

	removeClass: function (name) {

		this.dom.classList.remove(name);

		return this;

	},

	setStyle: function (style, array) {

		for (var i = 0; i < array.length; i++) {

			this.dom.style[style] = array[i];

		}

		return this;

	},

	setDisabled: function (value) {

		this.dom.disabled = value;

		return this;

	},

	setTextContent: function (value) {

		this.dom.textContent = value;

		return this;

	},

	getTextContent: function () {

		return this.dom.textContent;

	}

};

// properties

var properties = ['position', 'left', 'top', 'right', 'bottom', 'width', 'height', 'border', 'borderLeft',
	'borderTop', 'borderRight', 'borderBottom', 'borderColor', 'display', 'overflow', 'margin', 'marginLeft', 'marginTop', 'marginRight', 'marginBottom', 'padding', 'paddingLeft', 'paddingTop', 'paddingRight', 'paddingBottom', 'color',
	'background', 'backgroundColor', 'opacity', 'fontSize', 'fontWeight', 'textAlign', 'textDecoration', 'textTransform', 'cursor', 'zIndex'];

properties.forEach(function (property) {

	var method = 'set' + property.substr(0, 1).toUpperCase() + property.substr(1, property.length);

	UIElement.prototype[method] = function () {

		this.setStyle(property, arguments);

		return this;

	};

});

// events

var events = ['KeyUp', 'KeyDown', 'MouseOver', 'MouseOut', 'Click', 'DblClick', 'Change'];

events.forEach(function (event) {

	var method = 'on' + event;

	UIElement.prototype[method] = function (callback) {

		this.dom.addEventListener(event.toLowerCase(), callback.bind(this), false);

		return this;

	};

});

// UISpan

var UISpan = function () {

	UIElement.call(this);

	this.dom = document.createElement('span');

	return this;

};

UISpan.prototype = Object.create(UIElement.prototype);
UISpan.prototype.constructor = UISpan;

// UIDiv

var UIDiv = function () {

	UIElement.call(this);

	this.dom = document.createElement('div');

	return this;

};

UIDiv.prototype = Object.create(UIElement.prototype);
UIDiv.prototype.constructor = UIDiv;

// UIFieldset

var UIFieldset = function (text) {

	UIElement.call(this);

	var legend = document.createElement('legend');
	var text = document.createTextNode(text || 'NONE');
	var dom = document.createElement('fieldset');

	legend.appendChild(text);
	dom.appendChild(legend);

	this.dom = dom;

	return this;

};

UIFieldset.prototype = Object.create(UIElement.prototype);
UIFieldset.prototype.constructor = UIFieldset;

// UIRow

var UIRow = function () {

	UIElement.call(this);

	var dom = document.createElement('div');
	dom.className = 'Row';

	this.dom = dom;

	return this;

};

UIRow.prototype = Object.create(UIElement.prototype);
UIRow.prototype.constructor = UIRow;

// UIPanel

var UIPanel = function () {

	UIElement.call(this);

	var dom = document.createElement('div');
	dom.className = 'Panel';

	this.dom = dom;

	return this;

};

UIPanel.prototype = Object.create(UIElement.prototype);
UIPanel.prototype.constructor = UIPanel;

// UIText

var UIText = function (text) {

	UIElement.call(this);

	var dom = document.createElement('span');
	dom.className = 'Text';
	dom.style.cursor = 'default';
	dom.style.display = 'inline-block';
	dom.style.verticalAlign = 'middle';

	this.dom = dom;
	this.setValue(text);

	return this;

};

UIText.prototype = Object.create(UIElement.prototype);
UIText.prototype.constructor = UIText;

UIText.prototype.getValue = function () {

	return this.dom.textContent;

};

UIText.prototype.setValue = function (value) {

	if (value !== undefined) {

		this.dom.textContent = value;

	}

	return this;

};


// UIInput

var UIInput = function (text) {

	UIElement.call(this);

	var dom = document.createElement('input');
	dom.className = 'Input';
	dom.autocomplete = 'off';
	dom.style.padding = '2px';
	dom.style.border = '1px solid transparent';

	dom.addEventListener('keydown', function (event) {

		event.stopPropagation();

	}, false);

	this.dom = dom;
	this.setValue(text);

	return this;

};

UIInput.prototype = Object.create(UIElement.prototype);
UIInput.prototype.constructor = UIInput;

UIInput.prototype.getValue = function () {

	return this.dom.value;

};

UIInput.prototype.setValue = function (value) {

	this.dom.value = value;

	return this;

};

UIInput.prototype.getPlaceholder = function () {

	return this.dom.placeholder;

};

UIInput.prototype.setPlaceholder = function (value) {

	this.dom.placeholder = value;

	return this;

};

UIInput.prototype.setEditable = function (value) {

	this.dom.readOnly = !value;

	return this;

};

UIInput.prototype.isCorrectFormat = function () {
	var formatCheck = /^[a-zA-Z]+[a-zA-Z0-9.]*$/;

	return formatCheck.test(this.dom.value);
};


// UITextArea

var UITextArea = function () {

	UIElement.call(this);

	var dom = document.createElement('textarea');
	dom.className = 'TextArea';
	dom.style.padding = '2px';
	dom.spellcheck = false;

	dom.addEventListener('keydown', function (event) {

		event.stopPropagation();

		if (event.keyCode === 9) {

			event.preventDefault();

			var cursor = dom.selectionStart;

			dom.value = dom.value.substring(0, cursor) + '\t' + dom.value.substring(cursor);
			dom.selectionStart = cursor + 1;
			dom.selectionEnd = dom.selectionStart;

		}

	}, false);

	this.dom = dom;

	return this;

};

UITextArea.prototype = Object.create(UIElement.prototype);
UITextArea.prototype.constructor = UITextArea;

UITextArea.prototype.getValue = function () {

	return this.dom.value;

};

UITextArea.prototype.setValue = function (value) {

	this.dom.value = value;

	return this;

};


// UISelect

var UISelect = function () {

	UIElement.call(this);

	var dom = document.createElement('select');
	dom.className = 'Select';
	dom.style.padding = '2px';

	this.dom = dom;

	return this;

};

UISelect.prototype = Object.create(UIElement.prototype);
UISelect.prototype.constructor = UISelect;

UISelect.prototype.setMultiple = function (boolean) {

	this.dom.multiple = boolean;

	return this;

};

UISelect.prototype.setOptions = function (options) {

	var selected = this.dom.value;

	while (this.dom.children.length > 0) {

		this.dom.removeChild(this.dom.firstChild);

	}

	for (var key in options) {

		var option = document.createElement('option');
		option.value = key;
		option.innerHTML = options[key];
		this.dom.appendChild(option);

	}

	this.dom.value = selected;

	return this;

};

UISelect.prototype.getValue = function () {

	return this.dom.value;

};

UISelect.prototype.setValue = function (value) {

	value = String(value);

	if (this.dom.value !== value) {

		this.dom.value = value;

	}

	return this;

};

// UICheckbox

var UICheckbox = function (boolean) {

	UIElement.call(this);

	var dom = document.createElement('input');
	dom.className = 'Checkbox';
	dom.type = 'checkbox';

	this.dom = dom;
	this.setValue(boolean);

	return this;

};

UICheckbox.prototype = Object.create(UIElement.prototype);
UICheckbox.prototype.constructor = UICheckbox;

UICheckbox.prototype.getValue = function () {

	return this.dom.checked;

};

UICheckbox.prototype.setValue = function (value) {

	if (value !== undefined) {

		this.dom.checked = value;

	}

	return this;

};


// UIColor

var UIColor = function () {

	UIElement.call(this);

	var dom = document.createElement('input');
	dom.className = 'Color';
	dom.style.width = '64px';
	dom.style.height = '17px';
	dom.style.border = '0px';
	dom.style.padding = '2px';
	dom.style.backgroundColor = 'transparent';

	try {

		dom.type = 'color';
		dom.value = '#ffffff';

	} catch (exception) { }

	this.dom = dom;

	return this;

};

UIColor.prototype = Object.create(UIElement.prototype);
UIColor.prototype.constructor = UIColor;

UIColor.prototype.getValue = function () {

	return this.dom.value;

};

UIColor.prototype.getHexValue = function () {

	return parseInt(this.dom.value.substr(1), 16);

};

UIColor.prototype.setValue = function (value) {

	this.dom.value = value;

	return this;

};

UIColor.prototype.setHexValue = function (hex) {

	this.dom.value = '#' + ('000000' + hex.toString(16)).slice(- 6);

	return this;

};


// UINumber

var UINumber = function (number) {

	UIElement.call(this);

	var scope = this;

	var dom = document.createElement('input');
	dom.className = 'Number';
	dom.value = '0.00';

	this.value = 0;

	this.min = - Infinity;
	this.max = Infinity;

	this.precision = 2;
	this.step = 1;
	this.unit = '';
	this.nudge = 0.01;

	this.dom = dom;

	this.setValue(number);

	var changeEvent = document.createEvent('HTMLEvents');
	changeEvent.initEvent('change', true, true);

	var distance = 0;
	var onMouseDownValue = 0;

	var pointer = [0, 0];
	var prevPointer = [0, 0];

	function onMouseDown(event) {

		event.preventDefault();

		distance = 0;

		onMouseDownValue = scope.value;

		prevPointer = [event.clientX, event.clientY];

		document.addEventListener('mousemove', onMouseMove, false);
		document.addEventListener('mouseup', onMouseUp, false);

	}

	function onMouseMove(event) {

		var currentValue = scope.value;

		pointer = [event.clientX, event.clientY];

		distance += (pointer[0] - prevPointer[0]) - (pointer[1] - prevPointer[1]);

		var value = onMouseDownValue + (distance / (event.shiftKey ? 5 : 50)) * scope.step;
		value = Math.min(scope.max, Math.max(scope.min, value));

		if (currentValue !== value) {

			scope.setValue(value);
			dom.dispatchEvent(changeEvent);

		}

		prevPointer = [event.clientX, event.clientY];

	}

	function onMouseUp() {

		document.removeEventListener('mousemove', onMouseMove, false);
		document.removeEventListener('mouseup', onMouseUp, false);

		if (Math.abs(distance) < 2) {

			dom.focus();
			dom.select();

		}

	}

	function onTouchStart(event) {

		if (event.touches.length === 1) {

			distance = 0;

			onMouseDownValue = scope.value;

			prevPointer = [event.touches[0].pageX, event.touches[0].pageY];

			document.addEventListener('touchmove', onTouchMove, false);
			document.addEventListener('touchend', onTouchEnd, false);

		}

	}

	function onTouchMove(event) {

		var currentValue = scope.value;

		pointer = [event.touches[0].pageX, event.touches[0].pageY];

		distance += (pointer[0] - prevPointer[0]) - (pointer[1] - prevPointer[1]);

		var value = onMouseDownValue + (distance / (event.shiftKey ? 5 : 50)) * scope.step;
		value = Math.min(scope.max, Math.max(scope.min, value));

		if (currentValue !== value) {

			scope.setValue(value);
			dom.dispatchEvent(changeEvent);

		}

		prevPointer = [event.touches[0].pageX, event.touches[0].pageY];

	}

	function onTouchEnd(event) {

		if (event.touches.length === 0) {

			document.removeEventListener('touchmove', onTouchMove, false);
			document.removeEventListener('touchend', onTouchEnd, false);

		}

	}

	function onChange() {

		scope.setValue(dom.value);

	}

	function onFocus() {

		dom.style.backgroundColor = '';
		dom.style.cursor = '';

	}

	function onBlur() {

		dom.style.backgroundColor = 'transparent';
		dom.style.cursor = 'col-resize';

	}

	function onKeyDown(event) {

		event.stopPropagation();

		switch (event.keyCode) {

			case 13: // enter
				dom.blur();
				break;

			case 38: // up
				event.preventDefault();
				scope.setValue(scope.getValue() + scope.nudge);
				dom.dispatchEvent(changeEvent);
				break;

			case 40: // down
				event.preventDefault();
				scope.setValue(scope.getValue() - scope.nudge);
				dom.dispatchEvent(changeEvent);
				break;

		}

	}

	onBlur();

	dom.addEventListener('keydown', onKeyDown, false);
	dom.addEventListener('mousedown', onMouseDown, false);
	dom.addEventListener('touchstart', onTouchStart, false);
	dom.addEventListener('change', onChange, false);
	dom.addEventListener('focus', onFocus, false);
	dom.addEventListener('blur', onBlur, false);

	return this;

};

UINumber.prototype = Object.create(UIElement.prototype);
UINumber.prototype.constructor = UINumber;

UINumber.prototype.getValue = function () {

	return this.value;

};

UINumber.prototype.setValue = function (value) {

	if (value !== undefined) {

		value = parseFloat(value);

		if (value < this.min) value = this.min;
		if (value > this.max) value = this.max;

		this.value = value;
		this.dom.value = value.toFixed(this.precision);

		if (this.unit !== '') this.dom.value += ' ' + this.unit;

	}

	return this;

};

UINumber.prototype.setPrecision = function (precision) {

	this.precision = precision;

	this.nudge = Math.pow(10, -precision);

	return this;

};

UINumber.prototype.setStep = function (step) {

	this.step = step;

	return this;

};

UINumber.prototype.setNudge = function (nudge) {

	this.nudge = nudge;

	return this;

};

UINumber.prototype.setRange = function (min, max) {

	this.min = min;
	this.max = max;

	return this;

};

UINumber.prototype.setUnit = function (unit) {

	this.unit = unit;

	return this;

};

// UIInteger

var UIInteger = function (number) {

	UIElement.call(this);

	var scope = this;

	var dom = document.createElement('input');
	dom.className = 'Number';
	dom.value = '0';

	this.value = 0;

	this.min = - Infinity;
	this.max = Infinity;

	this.step = 1;
	this.nudge = 1;

	this.dom = dom;

	this.setValue(number);

	var changeEvent = document.createEvent('HTMLEvents');
	changeEvent.initEvent('change', true, true);

	var distance = 0;
	var onMouseDownValue = 0;

	var pointer = [0, 0];
	var prevPointer = [0, 0];

	function onMouseDown(event) {

		event.preventDefault();

		distance = 0;

		onMouseDownValue = scope.value;

		prevPointer = [event.clientX, event.clientY];

		document.addEventListener('mousemove', onMouseMove, false);
		document.addEventListener('mouseup', onMouseUp, false);

	}

	function onMouseMove(event) {

		var currentValue = scope.value;

		pointer = [event.clientX, event.clientY];

		distance += (pointer[0] - prevPointer[0]) - (pointer[1] - prevPointer[1]);

		var value = onMouseDownValue + (distance / (event.shiftKey ? 5 : 50)) * scope.step;
		value = Math.min(scope.max, Math.max(scope.min, value)) | 0;

		if (currentValue !== value) {

			scope.setValue(value);
			dom.dispatchEvent(changeEvent);

		}

		prevPointer = [event.clientX, event.clientY];

	}

	function onMouseUp() {

		document.removeEventListener('mousemove', onMouseMove, false);
		document.removeEventListener('mouseup', onMouseUp, false);

		if (Math.abs(distance) < 2) {

			dom.focus();
			dom.select();

		}

	}

	function onChange() {

		scope.setValue(dom.value);

	}

	function onFocus() {

		dom.style.backgroundColor = '';
		dom.style.cursor = '';

	}

	function onBlur() {

		dom.style.backgroundColor = 'transparent';
		dom.style.cursor = 'col-resize';

	}

	function onKeyDown(event) {

		event.stopPropagation();

		switch (event.keyCode) {

			case 13: // enter
				dom.blur();
				break;

			case 38: // up
				event.preventDefault();
				scope.setValue(scope.getValue() + scope.nudge);
				dom.dispatchEvent(changeEvent);
				break;

			case 40: // down
				event.preventDefault();
				scope.setValue(scope.getValue() - scope.nudge);
				dom.dispatchEvent(changeEvent);
				break;

		}

	}

	onBlur();

	dom.addEventListener('keydown', onKeyDown, false);
	dom.addEventListener('mousedown', onMouseDown, false);
	dom.addEventListener('change', onChange, false);
	dom.addEventListener('focus', onFocus, false);
	dom.addEventListener('blur', onBlur, false);

	return this;

};

UIInteger.prototype = Object.create(UIElement.prototype);
UIInteger.prototype.constructor = UIInteger;

UIInteger.prototype.getValue = function () {

	return this.value;

};

UIInteger.prototype.setValue = function (value) {

	if (value !== undefined) {

		value = parseInt(value);

		this.value = value;
		this.dom.value = value;

	}

	return this;

};

UIInteger.prototype.setStep = function (step) {

	this.step = parseInt(step);

	return this;

};

UIInteger.prototype.setNudge = function (nudge) {

	this.nudge = nudge;

	return this;

};

UIInteger.prototype.setRange = function (min, max) {

	this.min = min;
	this.max = max;

	return this;

};


// UIBreak

var UIBreak = function () {

	UIElement.call(this);

	var dom = document.createElement('br');
	dom.className = 'Break';

	this.dom = dom;

	return this;

};

UIBreak.prototype = Object.create(UIElement.prototype);
UIBreak.prototype.constructor = UIBreak;


// UIHorizontalRule

var UIHorizontalRule = function () {

	UIElement.call(this);

	var dom = document.createElement('hr');
	dom.className = 'HorizontalRule';

	this.dom = dom;

	return this;

};

UIHorizontalRule.prototype = Object.create(UIElement.prototype);
UIHorizontalRule.prototype.constructor = UIHorizontalRule;


// UIButton

var UIButton = function (value) {

	UIElement.call(this);

	var dom = document.createElement('button');
	dom.className = 'Button';

	this.dom = dom;
	this.dom.textContent = value;

	return this;

};

UIButton.prototype = Object.create(UIElement.prototype);
UIButton.prototype.constructor = UIButton;

UIButton.prototype.setLabel = function (value) {

	this.dom.textContent = value;

	return this;

};


// UITabbedPanel

var UITabbedPanel = function () {

	UIElement.call(this);

	var dom = document.createElement('div');

	this.dom = dom;

	this.setClass('TabbedPanel');

	this.tabs = [];
	this.panels = [];

	this.tabsDiv = new UIDiv();
	this.tabsDiv.setClass('Tabs');

	this.panelsDiv = new UIDiv();
	this.panelsDiv.setClass('Panels');

	this.add(this.tabsDiv);
	this.add(this.panelsDiv);

	this.selected = '';

	return this;

};

UITabbedPanel.prototype = Object.create(UIElement.prototype);
UITabbedPanel.prototype.constructor = UITabbedPanel;

UITabbedPanel.prototype.select = function (id) {

	var tab;
	var panel;
	var scope = this;

	// Deselect current selection
	if (this.selected && this.selected.length) {

		tab = this.tabs.find(function (item) {

			return item.dom.id === scope.selected;

		});
		panel = this.panels.find(function (item) {

			return item.dom.id === scope.selected;

		});

		if (tab) {

			tab.removeClass('selected');

		}

		if (panel) {

			panel.setDisplay('none');

		}

	}

	tab = this.tabs.find(function (item) {

		return item.dom.id === id;

	});
	panel = this.panels.find(function (item) {

		return item.dom.id === id;

	});

	if (tab) {

		tab.addClass('selected');

	}

	if (panel) {

		panel.setDisplay('');

	}

	this.selected = id;

	return this;

};

UITabbedPanel.prototype.addTab = function (id, label, items) {

	var tab = new UITabbedPanel.Tab(label, this);
	tab.setId(id);
	this.tabs.push(tab);
	this.tabsDiv.add(tab);

	var panel = new UIDiv();
	panel.setId(id);
	panel.add(items);
	panel.setDisplay('none');
	this.panels.push(panel);
	this.panelsDiv.add(panel);

	this.select(id);

};

UITabbedPanel.Tab = function (text, parent) {

	UIText.call(this, text);
	this.parent = parent;

	this.setClass('Tab');

	var scope = this;

	this.dom.addEventListener('click', function () {

		scope.parent.select(scope.dom.id);

	});

	return this;

};

UITabbedPanel.Tab.prototype = Object.create(UIText.prototype);
UITabbedPanel.Tab.prototype.constructor = UITabbedPanel.Tab;

// UIListbox
var UIListbox = function () {

	UIElement.call(this);

	var dom = document.createElement('div');
	dom.className = 'Listbox';
	dom.tabIndex = 0;

	this.dom = dom;
	this.items = [];
	this.listitems = [];
	this.selectedIndex = 0;
	this.selectedValue = null;

	return this;

};

UIListbox.prototype = Object.create(UIElement.prototype);
UIListbox.prototype.constructor = UIListbox;

UIListbox.prototype.setItems = function (items) {

	if (Array.isArray(items)) {

		this.items = items;

	}

	this.render();

};

UIListbox.prototype.render = function () {

	while (this.listitems.length) {

		var item = this.listitems[0];

		item.dom.remove();

		this.listitems.splice(0, 1);

	}

	for (var i = 0; i < this.items.length; i++) {

		var item = this.items[i];

		var listitem = new UIListbox.ListboxItem(this);
		listitem.setId(item.id || `Listbox-${i}`);
		listitem.setTextContent(item.name || item.type);
		this.add(listitem);

	}

};

// Assuming user passes valid list items
UIListbox.prototype.add = function () {

	var items = Array.from(arguments);

	this.listitems = this.listitems.concat(items);

	UIElement.prototype.add.apply(this, items);

};

UIListbox.prototype.selectIndex = function (index) {

	if (index >= 0 && index < this.items.length) {

		this.setValue(this.listitems[index].getId());

	}

	this.selectedIndex = index;

};

UIListbox.prototype.getValue = function () {

	return this.selectedValue;

};

UIListbox.prototype.setValue = function (value) {

	for (var i = 0; i < this.listitems.length; i++) {

		var element = this.listitems[i];

		if (element.getId() === value) {

			element.addClass('active');

		} else {

			element.removeClass('active');

		}

	}

	this.selectedValue = value;

	var changeEvent = document.createEvent('HTMLEvents');
	changeEvent.initEvent('change', true, true);
	this.dom.dispatchEvent(changeEvent);

};

// Listbox Item
UIListbox.ListboxItem = function (parent) {

	UIElement.call(this);

	var dom = document.createElement('div');
	dom.className = 'ListboxItem';

	this.parent = parent;
	this.dom = dom;

	var scope = this;

	function onClick() {

		if (scope.parent) {

			scope.parent.setValue(scope.getId());

		}

	}

	dom.addEventListener('click', onClick, false);

	return this;

};

UIListbox.ListboxItem.prototype = Object.create(UIElement.prototype);
UIListbox.ListboxItem.prototype.constructor = UIListbox.ListboxItem;

// UITable
var UITable = function (titles) {

	UIElement.call(this);

	titles = Array.isArray(titles) ? titles : [];

	var dom = document.createElement('table');
	dom.className = 'Table';
	dom.tabIndex = 0;
	dom.createTHead();
	dom.createTBody();

	var headerRow = dom.tHead.insertRow(0);
	for (var i = 0; i < titles.length; i++) {
		headerRow.appendChild(document.createElement('th'));
		headerRow.children[i].style = titles[i].style;
		headerRow.children[i].innerHTML = titles[i].label;
	}


	this.dom = dom;
	this.titles = titles;
	this.items = [];
	this.tableitems = [];
	this.selectedIndex = 0;
	this.selectedValue = null;
	this.selectedIndex = Number.MIN_SAFE_INTEGER;

	return this;

};

UITable.prototype = Object.create(UIElement.prototype);
UITable.prototype.constructor = UITable;

UITable.prototype.setItems = function (items) {

	if (Array.isArray(items)) {

		this.items = items;

	}

	this.render();

};

UITable.prototype.render = function () {

	while (this.tableitems.length) {

		var item = this.tableitems[0];

		item.dom.remove();

		this.tableitems.splice(0, 1);

	}

	for (var i = 0; i < this.items.length; i++) {

		var item = this.items[i];

		var tableitem = new UITable.TableItem(this, item);
		tableitem.setId(item.objectId || `Table-${i}`);
		this.add(tableitem);

	}

};

// Assuming user passes valid list items
UITable.prototype.add = function () {

	var items = Array.from(arguments);

	this.tableitems = this.tableitems.concat(items);

	for (var i = 0; i < arguments.length; i++) {

		var argument = arguments[i];

		if (argument instanceof UIElement) {

			this.dom.tBodies[0].appendChild(argument.dom);

		} else {

			console.error('UIElement:', argument, 'is not an instance of UIElement.');

		}

	}

	return this;

};

UITable.prototype.selectIndex = function (index) {

	if (index >= 0 && index < this.items.length) {

		this.setValue(this.tableitems[index].getId());

	}

	this.selectedIndex = index;

};

UITable.prototype.getValue = function () {

	return this.selectedValue;

};

UITable.prototype.setValue = function (value) {

	this.selectedIndex = Number.MIN_SAFE_INTEGER;

	for (var i = 0; i < this.tableitems.length; i++) {

		var element = this.tableitems[i];

		if (element.getId() === value) {

			element.addClass('active');

			this.selectedIndex = i;

		} else {

			element.removeClass('active');

		}

	}

	this.selectedValue = value;

	var changeEvent = document.createEvent('HTMLEvents');
	changeEvent.initEvent('change', true, true);
	this.dom.dispatchEvent(changeEvent);

};

// Table Item
UITable.TableItem = function (parent, item) {

	UIElement.call(this);

	var dom = document.createElement('tr');
	dom.className = 'TableItem';

	var newCell;
	for (var i = 0; i < parent.titles.length; i++) {
		newCell = dom.insertCell(i);
		newCell.style = parent.titles[i].style;
		newCell.innerHTML = item[parent.titles[i].name] !== undefined ? item[parent.titles[i].name] : '';
	}

	this.parent = parent;
	this.dom = dom;

	var scope = this;

	function onClick() {

		if (scope.parent) {

			scope.parent.setValue(scope.getId());

		}

	}

	dom.addEventListener('click', onClick, false);

	return this;

};

UITable.TableItem.prototype = Object.create(UIElement.prototype);
UITable.TableItem.prototype.constructor = UITable.TableItem;

//################################################################################################


// UIAccordion
var UIAccordion = function () {

	UIElement.call(this);

	var dom = document.createElement('div');
	dom.className = 'Accordion';
	dom.tabIndex = 0;

	this.dom = dom;
	this.items = [];
	this.listitems = [];
	this.selectedIndex = 0;
	this.selectedValue = null;
	this.selectedItem = null;

	return this;

};

UIAccordion.prototype = Object.create(UIElement.prototype);
UIAccordion.prototype.constructor = UIAccordion;

UIAccordion.prototype.setItems = function (items) {

	if (Array.isArray(items)) {

		this.items = items;

	}

	this.render();

};

UIAccordion.prototype.render = function () {

	while (this.listitems.length) {

		var item = this.listitems[0];

		// if (item.getId() === 'runAccordionItemButton') continue;

		item.dom.remove();

		this.listitems.splice(0, 1);

	}

	for (var i = 0; i < this.items.length; i++) {

		var item = this.items[i];

		var listitem = new UIAccordion.AccordionItem(this);
		listitem.setId(item.gid || `Accordion-${i}`);
		listitem.setTitle(item.title || item.type);
		listitem.setDescription(item.description || '', item.rectangle1, item.rectangle2);
		this.add(listitem);

	}

};

// Assuming user passes valid list items
UIAccordion.prototype.add = function () {

	var items = Array.from(arguments);

	this.listitems = this.listitems.concat(items);

	UIElement.prototype.add.apply(this, items);

};

UIAccordion.prototype.selectIndex = function (index) {

	if (index >= 0 && index < this.items.length) {

		this.selectedItem = this.items[index];

		this.setValue(this.listitems[index].getId());

	}

	this.selectedIndex = index;

};

UIAccordion.prototype.getValue = function () {

	return this.selectedValue;

};

UIAccordion.prototype.getItem = function () {

	return this.selectedItem;

};

UIAccordion.prototype.runItem = function () {

	var runEvent = document.createEvent('HTMLEvents');
	runEvent.initEvent('run', true, true);
	this.dom.dispatchEvent(runEvent);

};

UIAccordion.prototype.setValue = function (value) {

	this.selectedIndex = 0;
	this.selectedItem = null;

	for (var i = 0; i < this.listitems.length; i++) {

		var element = this.listitems[i];

		if (element.getId() === value) {

			this.selectedIndex = i;
			this.selectedItem = this.items[i];

			element.addClass('active');
			element.itemButton.disabled = false;
			element.itemInfo.classList.toggle("active");
			var panel = element.itemDetl;
			if (panel.style.maxHeight) {
				panel.style.maxHeight = null;
			} else {
				panel.style.maxHeight = panel.scrollHeight + "px";
			}

			//this.runAccordionItemButton.style.top = `${element.dom.offsetTop + 2}px`;
			//this.runAccordionItemButton.style.left = `${element.dom.offsetLeft + 80}px`;

		} else {

			element.removeClass('active');
			element.itemButton.disabled = true;

		}

	}

	this.selectedValue = value;

	var changeEvent = document.createEvent('HTMLEvents');
	changeEvent.initEvent('change', true, true);
	this.dom.dispatchEvent(changeEvent);

};

// Accordion Item
UIAccordion.AccordionItem = function (parent) {

	UIElement.call(this);

	var itemInfo = document.createElement('div');
	itemInfo.className = 'AccordionItemInfo';
	var itemInfoInput = document.createElement('input');
	itemInfoInput.readOnly = true;

	var itemCopyButton = document.createElement('button');
	itemCopyButton.id = 'AccordionItemButtonC';
	itemCopyButton.innerText = 'copy';
	var itemDetl = document.createElement('div');
	itemDetl.className = 'AccordionItemDetl';
	var itemButton = document.createElement('button');
	itemButton.id = 'AccordionItemButtonP';
	itemButton.innerText = '\u25ba';
	itemButton.disabled = true;

	var dom = document.createElement('div');
	dom.className = 'AccordionItem';


	itemInfo.appendChild(itemInfoInput);
	dom.appendChild(itemInfo);
	dom.appendChild(itemCopyButton);
	dom.appendChild(itemButton);
	dom.appendChild(itemDetl);

	this.parent = parent;
	this.dom = dom;
	this.itemInfo = itemInfo;
	this.itemDetl = itemDetl;
	this.itemButton = itemButton;
	this.itemCopyButton = itemCopyButton;

	var scope = this;

	function onClick() {

		if (scope.parent) {

			scope.parent.setValue(scope.getId());

		}

	}

	dom.addEventListener('click', onClick, false);


	function onItemButtonClick(event) {

		event.preventDefault();
		event.stopPropagation();


		if (scope.parent) {

			scope.parent.runItem();

		}

	}

	itemButton.addEventListener('click', function (event) { onItemButtonClick(event) }, false);


	function onItemCopyButtonClick(event) {

		event.preventDefault();
		event.stopPropagation();


		if (scope.parent) {

			itemInfoInput.select();
			document.execCommand("copy");
			document.getSelection().removeAllRanges()

		}

	}

	itemCopyButton.addEventListener('click', function (event) { onItemCopyButtonClick(event) }, false);

	return this;

};

UIAccordion.AccordionItem.prototype = Object.create(UIElement.prototype);
UIAccordion.AccordionItem.prototype.constructor = UIAccordion.AccordionItem;

UIAccordion.AccordionItem.prototype.setTitle = function (value) {
	this.itemInfo.childNodes[0].value = value;
	var changeEvent = document.createEvent('HTMLEvents');
	changeEvent.initEvent('change', true, true);
	this.dom.dispatchEvent(changeEvent);

};

UIAccordion.AccordionItem.prototype.setDescription = function (value, style1, style2) {
	if (!style1 || !style2) {
		this.itemDetl.innerHTML = `<p class='itemDescriptionText'>${value}</p>`;

	} else {
		let rectangle1Style = `width: ${style1.width}px; height: ${style1.height}px;`;
		let rectangle2Style = `width: ${style2.width}px; height: ${style2.height}px; top: ${style2.top}px; left:${style2.left}px`;
		this.itemDetl.innerHTML = `<p class='itemDescriptionText'>${value}</p><div class='itemDescriptionFigure'><div class="rectangle1" style="${rectangle1Style}"><div class="rectangle2" style="${rectangle2Style}"></div></div></div>`;
	}
	var changeEvent = document.createEvent('HTMLEvents');
	changeEvent.initEvent('change', true, true);
	this.dom.dispatchEvent(changeEvent);

};


export { UIElement, UISpan, UIDiv, UIFieldset, UIRow, UIPanel, UIText, UIInput, UITextArea, UISelect, UICheckbox, UIColor, UINumber, UIInteger, UIBreak, UIHorizontalRule, UIButton, UITabbedPanel, UIListbox, UITable, UIAccordion };
