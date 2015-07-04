window.onload = function() {
	var objs = document.getElementsByClassName("sqr1");
	var len = objs.length;
	var x=0;
	for(x=0;x<len;x++){
		objs[x].onclick = sendTo(objs[x].id);
	}
};

function sendTo(txt){
	return function(){
		window.location.replace("main.php?mood="+txt);
	};
}