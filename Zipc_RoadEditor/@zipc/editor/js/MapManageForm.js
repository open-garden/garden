/**
 * @author mrdoob / http://mrdoob.com/
 */

import { UIPanel, UIRow, UIInput, UIButton, UIAccordion, UINumber, UIText } from '../../../vendors/js/libs/ui.js';

var MapManageForm = function (editor) {

	var strings = editor.strings;
	var signals = editor.signals;

	// container
	var container = new UIPanel();
	container.setId('mapManageForm');

	var contentPanel = new UIPanel();
	contentPanel.setId('search-content');
	container.add(contentPanel);


	// Search Close Button
	var searchCloseButton = new UIButton('×').setPadding('0').setBackgroundColor('#333').onClick(function () {
		container.setDisplay('none');
	});
	searchCloseButton.setId('closeMapManageFormButton');
	contentPanel.add(searchCloseButton);

	// Search Panel
	var conditionPanel = new UIPanel();
	conditionPanel.setId('condition-panel');

	// Search Input
	var mapSearchInputRow = new UIRow();
	var mapSearchInput = new UIInput('').setPadding('12px 20px 12px 40px').setPlaceholder('Search for names..');
	mapSearchInput.dom.type = 'search';
	mapSearchInput.dom.addEventListener('search', function () {
		searchResult.setItems([]);

		const lon = centerX.getValue();
		const lat = centerY.getValue();
		const width = regionWidth.getValue();
		const height = regionHeight.getValue();
		const searchText = mapSearchInput.getValue();
		const keywords = searchText.split(/[\x20\u3000,]+/).filter(function (v) { return v !== '' }).join(',');

		signals.loadingScreenShow.dispatch();
		fetch(`${window.location.protocol}//${window.location.hostname}:${window.location.port}/road_editor/map/boundary/${isNaN(lon) ? 0 : lon}/${isNaN(lat) ? 0 : lat}/${isNaN(width) ? 0 : width}/${isNaN(height) ? 0 : height}/${keywords}`, {
			method: 'GET'
		}).then(response => {
			return response.text();
		}).then(data => {
			try {
				searchResult.setItems(Object.values(JSON.parse(data)));
			} catch (e) {
				searchResult.setItems([]);
			}
		}).catch(error => {
			console.error('Error:', error);
		}).finally(() => {
			signals.loadingScreenHide.dispatch();
		});

	}, false);
	mapSearchInput.setId('mapSearchInput');
	mapSearchInputRow.add(mapSearchInput);
	conditionPanel.add(mapSearchInputRow);
	//contentPanel.add(mapSearchInputRow);

	//Search Conditions

	var centerPositionRow = new UIRow();
	var centerX = new UINumber().setPrecision(7).setStep(10).setNudge(0.1).setRange(-180, 180).setUnit('°').setWidth('100px').onChange(() => { });
	centerX.setValue(0);
	var centerY = new UINumber().setPrecision(7).setStep(10).setNudge(0.1).setRange(-90, 90).setUnit('°').setWidth('100px').onChange(() => { });
	centerY.setValue(0);
	centerPositionRow.add(new UIText('Center(lon, lat)').setId('mapCenterPositionRow').setWidth('180px'));
	centerPositionRow.add(centerX, centerY);
	conditionPanel.add(centerPositionRow);

	var regionRow = new UIRow();
	var regionWidth = new UINumber().setPrecision(0).setStep(10).setNudge(10).setRange(0, 2000).setUnit('m').setWidth('100px').onChange(() => {
		let newValue = Math.ceil((isNaN(regionWidth.getValue()) ? 0 : regionWidth.getValue()) / 10) * 10;
		regionWidth.setValue(newValue);
	});
	regionWidth.setValue(0);
	var regionHeight = new UINumber().setPrecision(0).setStep(10).setNudge(10).setRange(0, 2000).setUnit('m').setWidth('100px').onChange(() => {
		let newValue = Math.ceil((isNaN(regionHeight.getValue()) ? 0 : regionHeight.getValue()) / 10) * 10;
		regionHeight.setValue(newValue);
	});
	regionHeight.setValue(0);
	regionRow.add(new UIText('Region(Width, Height)').setId('mapRegionRow').setWidth('180px'));
	regionRow.add(regionWidth, regionHeight);
	conditionPanel.add(regionRow);

	contentPanel.add(conditionPanel);

	// Search Input
	var mapFilterInputRow = new UIRow();
	var mapFilterInput = new UIInput('').setPadding('12px 20px 12px 40px').setBorder('1px solid #ddd').setPlaceholder('Filter for keywords..');
	mapFilterInput.dom.addEventListener('keyup', function () {
		var filter, txtValue;
		filter = mapFilterInput.getValue().toUpperCase();
		if (!filter) {
			// TODO
		} else {
			const keywords = filter.split(/[\x20\u3000,]+/).filter(function (v) { return v !== '' });
			for (var item of searchResult.listitems) {
				if (item) {
					if (keywords.length === 0) item.setDisplay('grid');
					txtValue = item.getTextContent();
					for (var kw of keywords) {
						if (txtValue.toUpperCase().indexOf(kw) > -1) {
							item.setDisplay('grid');
						} else {
							item.setDisplay('none');
							break;
						}
					}
				}
			}
		}
	}, false);
	mapFilterInput.setId('mapFilterInput');
	mapFilterInput.dom.style.backgroundColor = 'none';
	mapFilterInputRow.add(mapFilterInput);
	contentPanel.add(mapFilterInputRow);

	// Search Result
	var searchResult = new UIAccordion().setFontSize('14px');

	searchResult.dom.addEventListener('run', function () {
		if (confirm('Any unsaved data will be lost. Are you sure?')) {
			const mapId = searchResult.getValue();
			container.setDisplay('none');
			signals.loadingScreenShow.dispatch();
			fetch(`${window.location.protocol}//${window.location.hostname}:${window.location.port}/road_editor/map/${mapId}`, {
				method: 'GET'
			}).then(response => {
				return response.text();
			}).then(async (data) => {
				try {
					const mapData = JSON.parse(data);
					editor.clear();
					editor.loader.loadFromJson(mapData);
					editor.scene._id = mapId;
					editor.select(editor.scene);

					var boundary = editor.backgroundMap.boundary;
					if (mapData.boundary !== undefined && mapData.boundary !== null) {
						if (boundary.n !== mapData.boundary.n) boundary.n = mapData.boundary.n;
						if (boundary.e !== mapData.boundary.e) boundary.e = mapData.boundary.e;
						if (boundary.s !== mapData.boundary.s) boundary.s = mapData.boundary.s;
						if (boundary.w !== mapData.boundary.w) boundary.w = mapData.boundary.w;
						editor.backgroundMap.visible = false;
						editor.signals.objectChanged.dispatch(editor.scene);
						editor.signals.rendererUpdated.dispatch();
						// await editor.backgroundMap.asyncLoadOverpassDataByBoundary(mapData.boundary);
					}
				} catch (e) {
					console.error('Error:', e);
				}
			}).catch(err => {
				console.error('Error:', error);
			}).finally(() => {
				signals.loadingScreenHide.dispatch();
			});
		}
	});

	contentPanel.add(searchResult);

	//

	signals.mapManageScreenShow.add(function () {
		container.setDisplay('block');
	});

	signals.mapManageScreenHide.add(function () {
		container.setDisplay('none');
	});



	return container;

};

export { MapManageForm };
