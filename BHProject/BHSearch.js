function getSearchParameters() {
	var prmstr = window.location.search.substr(1);
	return prmstr != null && prmstr != "" ? transformToAssocArray(prmstr) : {};
}

var params = getSearchParameters();

function transformToAssocArray(prmstr) {
	var params = {};
	var prmarr = prmstr.split("&");
	for (var i = 0; i < prmarr.length; i++) {
		var tmparr = prmarr[i].split("=");
		params[tmparr[0]] = tmparr[1];
	}
	return params;
}

function smt() {
	var s = document.getElementById("txtare").value;
	window.location.replace("BHSearch.php?ctg="+params.ctg + "&search=" + s + "");
}

window.onload = function() {
	var objs = document.getElementsByClassName("srcbtn");
	var len = objs.length;
	var x=0;
	for(x=0;x<len;x++){
		objs[x].onclick = sendTo(objs[x].id);
	}
};

function sendTo(txt){
	return function(){
		window.location.replace("BHItem.php?iid="+txt);
	};
}