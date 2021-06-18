

function _UseBPTable(jsonRoot,appId,isCalledFromSCS) {
	var ID = "#"+appId
	//local register components
	Vue.component("bptable", {
		props : [ "items", "columns" ],
		template : `
		    <b-table-simple>
		      <b-thead>
		        <b-tr>
		          <b-th class="sorter-false" v-for="(col,index) in columns">
		            {{col['label']}}
		          </b-th>
		        </b-tr>
		      </b-thead>
		      <b-tbody>
		        <b-tr v-for="item in items">
		          <b-td class="patterncell" v-if='item["rowspan"] === "true"' :rowspan='item["colcount"]'>
		              {{item["no"]}}
		          </b-td>
		          <b-td class="patterncell" v-if='item["patternid"] && item["rowspan"] === "true"' :rowspan='item["colcount"]'>
		              {{item["patternid"]}}
		          </b-td>
		          <b-td
		            v-for='col in columns.filter((value) => value["key"]!=="no" && value["key"]!=="patternid")'
		            v-if='col["key"]==="initial"'>
		              {{item[col["key"]]}}
		          </b-td> <b-td v-else> {{item[col["key"]]["attr"]}}</b-td>
		        </b-tr>
		      </b-tbody>
		    </b-table-simple>
		`,
	});
	
	var col = [];
	var prepData = jsonRoot.items;
	var resizeColWidthPara = []
	col.push({
		key : 'no',
		label : 'No',
	});
	if(!isCalledFromSCS){
		col.push({
			key : 'patternid',
			label : 'patternid',
		});
	}
	col.push({
		key : 'initial',
		label : 'StateMachine',
	});
	for (var index = 1; index < jsonRoot.columns.length; index++) {
		col.push({
			key : jsonRoot.columns[index],
			label : jsonRoot.columns[index],
		});
	}
	for (var colIndex = 0; colIndex < col.length; colIndex++) {
		if (colIndex === 0) {
			resizeColWidthPara.push(100 + "px")
		} else {
			resizeColWidthPara.push(60 + "px")
		}
	}
	//rebuild data for table Show
	var formatedReords = [];
	for (var index = 0; index < prepData.length; index++) {
		var tempPattern = prepData[index];
		for (var subindex = 0; subindex < tempPattern["stateMachine"].length; subindex++) {
			var subBehavior = tempPattern["stateMachine"][subindex];
			if (subindex === 0) {
				if(!isCalledFromSCS){
					subBehavior["patternid"] = tempPattern["patternid"];
				}
				subBehavior["no"] = tempPattern["no"];
				subBehavior["colcount"] = tempPattern["colcount"];
			}
			formatedReords.push(subBehavior);
		}
	}
	
	new Vue({
		el : ID,
		data : {
			column : col,
			records : formatedReords
		}
	})

	$(function() {
		// overflow table
		$(ID+".bpeditorWrapper table").tablesorter({
			theme : 'blue',
			widgets : [ 'zebra', 'resizable' ],
			widgetOptions : {
				resizable_addLastColumn : true,
				resizable_widths : resizeColWidthPara
			}
		});
	});
	var thList = document.querySelectorAll(ID+".bpeditorWrapper th")
	for(var index=0;index<thList.length;index++)
	{
		thList[index].classList.add("sorter-false");
	}
}