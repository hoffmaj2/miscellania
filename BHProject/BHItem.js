
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

function checkout(){
	window.location.replace("BHItem.php?iid="+params.iid+"&ct=true");
}

function rem(){
	window.location.replace("BHItem.php?iid="+params.iid+"&rm=true");
}

function ret(){
	window.location.replace("BHItem.php?iid="+params.iid+"&rt=true");
}

function wlistadd(){
	window.location.replace("BHItem.php?iid="+params.iid+"&ad=true");
}
