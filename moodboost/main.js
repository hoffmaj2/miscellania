var incht = false;
var msgtxt = "";
var ttime = 3000;

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

window.onload = function() {
	incht = false;
	msgtxt = "";
	document.getElementById("initCht").onclick = initc;
	document.getElementById("endCht").onclick = endc;
	document.getElementById("initCht").style.display = 'block';
	document.getElementById("endCht").style.display = 'none';
	
	//setTimeout(chupdate, ttime);	
	document.getElementById("txtare").value = "";
};

function initc() {
	runProc(0);
}

function endc() {
	runProc(1);
}

function btoggle() {
	msgtxt = "";
	//incht = true;
	//incht = !incht;
	//alert(incht+"");
	if (incht) {
		document.getElementById("initCht").style.display = 'none';
		document.getElementById("endCht").style.display = 'block';
	} else {
		document.getElementById("initCht").style.display = 'block';
		document.getElementById("endCht").style.display = 'none';
	}
}

function runProc(p) {
	//if(p==3)alert("JJJ");
	var m = params.mood;
	var ans = false;
	var myUrl = "procs.php";
	if (p == 0)
		ans = true;
	var temp = document.createElement("span");
	$.ajax({
		url : myUrl,// + "?un="+un,
		type : 'POST',
		data : "p=" + p + "&m=" + m + "&t=" + msgtxt + "",
		datatype : 'xml',
		success : function(data) {
			ans = ans;
			if(p==3)
				{
					//alert(data);
					var arr = data.split("_*459");
					var xx = 0;
					//alert(arr.toString());
					document.getElementById("vieware").value = "";
					document.getElementById("vieware").innerhtml = "";
					for(xx=0;xx<arr.length - 1;xx+=4){
						var ttl = "you";
						if(arr[xx + 0]!=(readCookie("mbctag")+""))
							ttl = "someone";
						document.getElementById("vieware").value = document.getElementById("vieware").value + "\n" + arr[xx+2] + "              ["+ arr[xx+3] +"... "+ttl+"]";
					}
				}
			else if(p==2)
				runProc(3);
			else if(p==1){
				document.getElementById("vieware").value = "";
				document.getElementById("vieware").innerhtml = "";
			}
		},
		error : function() {
			ans = !ans;
		},
		complete : function() {
			//if(!(ans==true))ans = false;
			if (p == 0 || p == 1)
				incht = ans;
				btoggle();
		}

	});

	return ans;
}

function smt() {
	msgtxt = document.getElementById("txtare").value;
	if (msgtxt != "") {
		runProc(2);
		document.getElementById("txtare").value = "";
		
	}
	msgtxt = "";
}


function handle(e){
    //alert("FORM WAS SUBMITTED");
	smt();
    return false; //Otherwise the form will be submitted
}

function chupdate(){
	if(incht)
		runProc(3);
		//document.getElementById("vieware").value = runProg(3);
	//setTimeout(chupdate, ttime);	
}


function readCookie(name) {
	var cookiename = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++)
	{
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(cookiename) == 0) return c.substring(cookiename.length,c.length);
	}
	return null;
}