var strt = function() {
	if(window.location.hash) {
	      var hash = window.location.hash.substring(1); //Puts hash in variable, and removes the # character
	      if(hash == "login"){
	    	  document.getElementById("login").style.display = 'block';
	    	  document.getElementById("signup").style.display = 'none';
	      }
	      else{
	    	  document.getElementById("login").style.display = 'none';
	    	  document.getElementById("signup").style.display = 'block';
	      }
	  } else {
	      // No hash found
	  }
};

window.onpopstate = strt;
window.onload = strt;