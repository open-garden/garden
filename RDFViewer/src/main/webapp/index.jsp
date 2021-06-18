<%@page import="com.zipc.garden.turtle.Common.Attribute"%>
<%@page import="com.zipc.garden.turtle.Common"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.zipc.garden.turtle.rdf.RDFUtil"%>
<%@page import="com.zipc.garden.turtle.dao.TurtleDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=Shift_JIS"
	pageEncoding="UTF-8"%>
<%
Map<String, String> prefixMap = (Map<String, String>) getServletContext().getAttribute(Attribute.PREFIX.getKey());
List<TurtleDao> turtleDaoList = (List<TurtleDao>) getServletContext().getAttribute(Attribute.TTL_DAO_LIST.getKey());
String digraph = (String) getServletContext().getAttribute(Attribute.DIGRAPH.getKey());
String uploadMessage = (String) getServletContext().getAttribute(Attribute.UPLOAD_MESSAGE.getKey());

List<TurtleDao> classDatas = RDFUtil.getOwlClassFilter(turtleDaoList);
List<TurtleDao> objectPropertyDatas = RDFUtil.getOwlObjectPropertyFilter(turtleDaoList);

List<String> nameSpaces = RDFUtil.getNameSpace(turtleDaoList);
List<String> labelNames = RDFUtil.getLabelNameList(turtleDaoList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>RDF Viewer</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="./css/common.css" rel="stylesheet">
<script src="./js/jquery-3.5.1.js"></script>
<script src="./js/bootstrap.bundle.min.js"></script>
<script src="./js/viz.js"></script>
<script>
window.onload = function(){
	console.log("window.onload")
   createGraph('<%=digraph%>');
}

function createGraph(digraph){
   removeGraph();
   console.log("createGraph")
   const workerURL = "js/full.render.js";
   let viz = new Viz({workerURL});
   viz.renderSVGElement(digraph).then(function(element) {
	   console.log(element)
      document.getElementById("rdfGraph").appendChild(element);
   }).catch(error => {
      document.getElementById("rdfGraph").innerHTML = error;
   }).finally(()=>{
      const nodes = document.getElementsByClassName("node");
      for(let i = 0; i < nodes.length; i++){
         var texts = nodes[i].getElementsByTagName("text");
         // ラベルの位置を特定し、色を変更する。ラベル以前のテキストはクラスの為、まとめて色を変更する
         if(texts.length > 1){
            var idx=texts.length-2;
         <%for (String ln : labelNames) {%>
               if(texts[idx].innerHTML=="<%=ln%>"){
                   texts[idx].setAttribute("fill", "#FFA500");
                   idx=texts.length-3;
               }
         <%}%>
            for(let j = idx; j >= 0; j--){
                texts[j].setAttribute("fill", "#008000");
            }
         }
          
         nodes[i].addEventListener("click", (e) => {
            var texts2 = nodes[i].getElementsByTagName("text");
            var text = texts2[texts2.length-1];
            $.ajax({
               url: '/rdf_viewer/RDFNodeDetailServlet',
               type: 'POST',
               async: true,
               data: { nodeName: text.innerHTML },
               success : function(data) {
                  insertHtml = '<div class="tgToolTip">' + data + '</div>';
                  $('#rdfGraph').append(insertHtml);
                  $(".tgToolTip").css({'top': e.offsetY + 'px', 'left': e.offsetX + 'px'});
               }
            });
         }, false);
      }
      const edges = document.getElementsByClassName("edge");
      document.getElementById("graph0").addEventListener("click", (e) => {
         for(let j = 0; j < edges.length; j++){
            var paths = edges[j].getElementsByTagName("path");
            var path = paths[paths.length-1];
            path.setAttribute("stroke", "#000000");
            path.setAttribute("stroke-width", "1");
            var texts = edges[j].getElementsByTagName("text");
            var text = texts[texts.length-1];
            text.setAttribute("fill", "#000000");
            text.setAttribute("font-weight", "");
            var polygons = edges[j].getElementsByTagName("polygon");
            var polygon = polygons[polygons.length-1];
            polygon.setAttribute("fill", "#000000");
            polygon.setAttribute("stroke", "#000000");
         }
         $('.tgToolTip').remove();
      }, true);
      for(let i = 0; i < edges.length; i++){
         edges[i].addEventListener("click", (e) => {
            var paths = edges[i].getElementsByTagName("path");
            var path = paths[paths.length-1];
            path.setAttribute("stroke", "#ff0000");
            path.setAttribute("stroke-width", "2");
            var texts = edges[i].getElementsByTagName("text");
            var text = texts[texts.length-1];
            text.setAttribute("fill", "#ff0000");
            text.setAttribute("font-weight", "bold");
            var polygons = edges[i].getElementsByTagName("polygon");
            var polygon = polygons[polygons.length-1];
            polygon.setAttribute("fill", "#ff0000");
            polygon.setAttribute("stroke", "#ff0000");
         }, false);
      }
   });
}

function removeGraph(){
   removeChild("rdfGraph");
}

function removePrefix(){
   removeChild("prefixTbody");
}

function removeFilter(){
   removeChild("classTbody");
   removeChild("objectPropertyTbody");
}

function removeChild(elById){
   var parent = document.getElementById(elById);
   while(parent.firstChild){
      parent.removeChild(parent.firstChild);
   }
}

$(document).ready(function(){
   $("#classFilter").on("keyup", function() {
      var value = $(this).val().toLowerCase();
      $("#classTbody tr").filter(function() {
         $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
   });
   $("#objectPropertyFilter").on("keyup", function() {
      var value = $(this).val().toLowerCase();
      $("#objectPropertyTbody tr").filter(function() {
         $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
   });
});

function filterOK(){
   // プレフィックス 
   prefixCheck     = convertElementsToArrays( document.getElementsByName("prefixCheck"), "checkbox");
   prefixNameSpace = convertElementsToArrays( document.getElementsByName("prefixNameSpace"), "text");
   prefix          = convertElementsToArrays( document.getElementsByName("prefix"), "hidden");
   
   // Classタブ
   checkedClass = getCheckElements("class", true);

   // ObjectPropertyタブ
   checkedObjectProperty = getCheckElements("objectProperty", true);
   
   var selectType = getSelectType();
   
   $.ajax({
      url: '/rdf_viewer/RDFFilterServlet',
      type: 'POST',
      dataType: "text",
      data: { prefixCheck: prefixCheck ,
              prefixNameSpace: prefixNameSpace , 
              prefix: prefix , 
              classSearchTarget: checkedClass , 
              objectPropertySearchTarget: checkedObjectProperty,
              selectType: selectType}, 
      async: true,
      success : function(data) {
         createGraph(data);
      },
      error : function(XMLHttpRequest, textStatus, errorThrown) {
         alert("Failed to set prefix or filter.");
         console.log("XMLHttpRequest : " + XMLHttpRequest.status);
         console.log("textStatus     : " + textStatus);
         console.log("errorThrown    : " + errorThrown.message);
     }
  });
}

function checkForm($this) {
    var str=$this.value;
    while(str.match(/[^A-Z^a-z\d\-]/)) {
        str=str.replace(/[^A-Z^a-z\d\-]/,"");
    }
    $this.value=str;
}

function convertElementsToArrays(elements, type){
   arrayStr = [];
   for (let i = 0; i < elements.length; i++){
      if("checkbox" === type){
         arrayStr.push(elements[i].checked);
      }else{
         arrayStr.push(elements[i].value);
      }
   }
   return arrayStr;
}

/**
 * elName : 要素の名前
 * filterFlag : フィルタ条件に一致するものだけ調べる場合はTrue / 全件調べる場合はFalse
 */
function getCheckElements(elName, filterFg) {
   var result = [];
   var filter = document.getElementById(elName + "Filter").value.toLowerCase();
   var checks = document.getElementsByName(elName + "Check");
   var names = document.getElementsByName(elName + "Name");
   for (let i = 0; i < checks.length; i++){
      if(checks[i].checked){
         if (filter == null || filter == "" || filterFg == false) {
            result.push(names[i].value);
         } else if (names[i].value.toLowerCase().indexOf(filter) != -1) {
            result.push(names[i].value);
         }
      }
   }
   return result;
}

function allCheck(elName, checked) {
   var filter;
   if (elName === "class" || elName === "objectProperty") {
      filter = document.getElementById(elName + "Filter").value.toLowerCase();
   }
   var checkList = document.getElementsByName(elName + "Check");
   var nameList  = document.getElementsByName(elName + "Name");
   for (let i = 0 ; i < checkList.length ; i++ ) {
      if (filter == null || filter == "") {
         checkList[i].checked = checked;
      } else if (nameList[i].value.toLowerCase().indexOf(filter) != -1) {
         checkList[i].checked = checked;
      }
   }
}

function filterRead() {
    let fileInput = document.getElementById('filterRead');
    let fileReader = new FileReader();
    fileInput.onchange = () => {
        let file = fileInput.files[0];
        if(file != null){
            fileReader.readAsText(file);
        }
    };
    fileReader.onload = () => {
        var jsonData = JSON.parse(fileReader.result);
        
        // Classタブの設定
        allCheck("class", false);
        var classFilter = document.getElementById("classFilter");
        var classCheck = document.getElementsByName("classCheck");
        var classNames = document.getElementsByName("className");
        classFilter.value = jsonData.Class.Filter;
        classFilter.dispatchEvent(new KeyboardEvent("keyup"));
        for(let i = 0; i < classNames.length; i++){
            if(jsonData.Class.Name.indexOf(classNames[i].value) >= 0){
                classCheck[i].checked = true;
            }
        }
        
        // ObjectPropertyタブの設定
        allCheck("objectProperty", false);
        var opFilter = document.getElementById("objectPropertyFilter");
        var opCheck = document.getElementsByName("objectPropertyCheck");
        var opNames = document.getElementsByName("objectPropertyName");
        opFilter.value = jsonData.ObjectProperty.Filter;
        opFilter.dispatchEvent(new KeyboardEvent("keyup"));
        for(let i = 0; i < opNames.length; i++){
            if(jsonData.ObjectProperty.Name.indexOf(opNames[i].value) >= 0){
                opCheck[i].checked = true;
            }
        }
        
    };
}

function filterSave() {
   var classFilter = document.getElementById("classFilter").value.toLowerCase();
   var objectPropertyFilter = document.getElementById("objectPropertyFilter").value.toLowerCase();
   var checkedClass = getCheckElements("class", false);
   var checkedObjectProperty = getCheckElements("objectProperty", false);
   var json = {
      "Class"          : {"Filter" : classFilter,          "Name" : checkedClass},
      "ObjectProperty" : {"Filter" : objectPropertyFilter, "Name" : checkedObjectProperty}
   };
   downloadFile("Filter.json" , JSON.stringify(json), "data:text/plain");
}

function downloadFile(fileName, content, contentType){
   var blob = new Blob([content], { type: contentType });
   var anchor = document.createElement("a");
   anchor.href = URL.createObjectURL(blob);
   anchor.target = "_blank";
   anchor.download = fileName;
   anchor.click();
   anchor.remove();
}

function getSelectType() {
   var result = null;
   var select_datas = document.getElementsByName("select_data");
   for (let i = 0; i < select_datas.length; i++) {
       if (select_datas[i].checked) {
           console.log(select_datas[i].value)
           return select_datas[i].value;
           
       }
   }
   return null;
}

</script>
</head>
<body>
	<div class="container-fluid h-100">
		<div class="row h-100">
			<div class="col-2 border border-secondary fixed-top bg-white">
				<ul class="nav flex-column nav-pills">
					<form name="form1">
						<input type="radio" name="select_data" value="Tag" checked>Tag</label>
						<input type="radio" name="select_data" value="Scene">Scene</label>
					</form>
					<input type="button" value="View" onclick="filterOK()" class="nav-link"
						id="v-view-tab" data-toggle="pill" href="#v-view" role="tab"
						aria-controls="v-view" aria-selected="false" />
					<li>
						<!-- a class="nav-link" id="v-view-tab" data-toggle="pill"
						href="#v-view" role="tab" aria-controls="v-view"
						aria-selected="false">VIEW</a -->
						<ul>
							<li><a class="nav-link" href="#" data-toggle="modal"
								data-target="#prefixModal">PREFIX</a></li>
							<li><a class="nav-link" href="#" data-toggle="modal"
								data-target="#filterModal">FILTER</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<div class="col-2"></div>
			<div class="col-10">
				<div class="tab-content h-100">
					<div class="tab-pane fade" id="v-view" role="tabpanel"
						aria-labelledby="v-view-tab">
						<div id="rdfGraph"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!----------------------------------------
- Start Prefix Setting Modal
------------------------------------------>
	<div class="modal fade" id="prefixModal" tabindex="-1" role="dialog"
		aria-labelledby="prefixModalLabel">
		<div class="modal-dialog modal-dialog-scrollable modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="prefixModalLabel">Prefix Setting</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form class="px-4 py-3">
						<div>
							<input type="button" onclick="allCheck('prefix', true)"
								value="Select All"> <input type="button"
								onclick="allCheck('prefix', false)" value="Deselect All">
						</div>
						<table class="table">
							<thead class="thead-lignt">
								<tr>
									<th class="prefixCell-01">Show</th>
									<th class="prefixCell-02">Prefix</th>
									<th class="prefixCell-03">NameSpace</th>
								</tr>
							</thead>
							<tbody id="prefixTbody">
								<%
								for (String nameSpace : nameSpaces) {
								    String prefix = prefixMap.get(nameSpace);
								    if (prefix == null)
								        prefix = "";
								%>
								<tr>
									<td class="prefixCell-01"><input type="checkbox"
										name="prefixCheck" checked></td>
									<td class="prefixCell-02"><input type="text" name="prefix"
										onInput="checkForm(this)" size="6" maxlength="10"
										value="<%=prefix%>"></td>
									<td class="prefixCell-03"><input type="hidden"
										name="prefixNameSpace" value="<%=nameSpace%>"><%=nameSpace%></td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</form>
				</div>
				<div class="modal-footer text-center">
					<div class="col-12">
						<button type="submit" class="btn btn-primary w-25"
							data-dismiss="modal" onclick="filterOK();">OK</button>
						<button type="button" class="btn btn-secondary w-25"
							data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!----------------------------------------
- End Prefix Setting Modal
------------------------------------------>

	<!----------------------------------------
- Start Filter Setting Modal
------------------------------------------>
	<div class="modal fade" id="filterModal" tabindex="-1" role="dialog"
		aria-labelledby="filterModalLabel">
		<div class="modal-dialog modal-dialog-scrollable modal-xl"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="filterModalLabel">Filter Setting</h5>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<ul class="nav nav-pills">
					<li><a class="nav-link active" href="#v-class"
						id="v-class-tab" data-toggle="pill" href="#v-class" role="tab"
						aria-controls="v-class" aria-selected="true">Class</a></li>
					<li><a class="nav-link" href="#v-objectProperty"
						id="v-objectProperty-tab" data-toggle="pill" role="tab"
						aria-controls="v-objectProperty" aria-selected="false">ObjectProperty</a></li>
				</ul>
				<div class="modal-body">
					<form class="px-4 py-3">
						<div class="tab-content">
							<div class="tab-pane fade show active" id="v-class"
								role="tabpanel" aria-labelledby="v-class-tab">
								<div class="form-group row mt-2">
									<label class="col-sm-2 col-form-label">Filter</label> <input
										id="classFilter" type="text" class="col-sm-10 form-control">
								</div>
								<div>
									<input type="button" onclick="allCheck('class', true)"
										value="Select All"> <input type="button"
										onclick="allCheck('class', false)" value="Deselect All">
								</div>
								<table class="table">
									<thead class="thead-lignt">
										<tr>
											<th class="filterCell-01">Show</th>
											<th class="filterCell-02">Node</th>
										</tr>
									</thead>
									<tbody id="classTbody">
										<%
										for (TurtleDao dao : classDatas) {
										%>
										<tr>
											<td class="filterCell-01"><input type="checkbox"
												name="classCheck" checked></td>
											<td class="filterCell-02"><input type="hidden"
												name="className" value="<%=dao.getSubject()%>"><%=dao.getSubject()%></td>
										</tr>
										<%
										}
										%>
									</tbody>
								</table>
							</div>
							<div class="tab-pane" id="v-objectProperty" role="tabpanel"
								aria-labelledby="v-objectProperty-tab">
								<div class="form-group row mt-2">
									<label class="col-sm-2 col-form-label">Filter</label> <input
										id="objectPropertyFilter" type="text"
										class="col-sm-10 form-control">
								</div>
								<div>
									<input type="button" onclick="allCheck('objectProperty', true)"
										value="Select All"> <input type="button"
										onclick="allCheck('objectProperty', false)"
										value="Deselect All">
								</div>
								<table class="table">
									<thead class="thead-lignt">
										<tr>
											<th class="filterCell-01">Show</th>
											<th class="filterCell-02">Node</th>
										</tr>
									</thead>
									<tbody id="objectPropertyTbody">
										<%
										for (TurtleDao dao : objectPropertyDatas) {
										%>
										<tr>
											<td class="filterCell-01"><input type="checkbox"
												name="objectPropertyCheck" checked></td>
											<td class="filterCell-02"><input type="hidden"
												name="objectPropertyName" value="<%=dao.getSubject()%>"><%=dao.getSubject()%></td>
										</tr>
										<%
										}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer text-center">
					<div class="col-12">
						<input id="filterRead" type="file" style="display: none;"
							onclick="filterRead();" />
						<!-- READボタン押下で発火 -->

						<button type="button" class="btn btn-info w-20"
							onclick="$('#filterRead').click();">READ</button>
						<button type="button" class="btn btn-info w-20"
							onclick="filterSave();">SAVE</button>
						<button type="submit" class="btn btn-primary w-20"
							data-dismiss="modal" onclick="filterOK();">OK</button>
						<button type="button" class="btn btn-secondary w-20"
							data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!----------------------------------------
- End Filter Setting Modal
------------------------------------------>
</body>
</html>