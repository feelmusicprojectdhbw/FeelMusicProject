//Sichtbarkeit der Zuordnung umschalten, falls Label oder Artist ausgewählt sind.
function changedUsertype(currentusertype, currentconnection){
	var val = document.getElementById("usertypeselector").value;
	if(val == 4 || val == 5){
		document.getElementById("zuordnugsdiv").style.display = "block";
		if(val == 4){
			document.getElementById("labelToConnect").style.display = "none";
			document.getElementById("artistToConnect").style.display = "inline";
			if(currentusertype == 4){
				if(currentconnection != 0){
					document.getElementById("artistToConnect").value = currentconnection;
				}
			}
		}
		if(val == 5){
			document.getElementById("labelToConnect").style.display = "inline";
			document.getElementById("artistToConnect").style.display = "none";
			if(currentusertype == 5){
				if(currentconnection != 0){
					document.getElementById("labelToConnect").value = currentconnection;
				}
			}
		}
	}else{		
		document.getElementById("labelToConnect").style.display = "none";
		document.getElementById("artistToConnect").style.display = "none";
		document.getElementById("zuordnugsdiv").style.display = "none";
	}
}

//Suche mit validierung
function searchUser(ownusertypeid){
	var srch = document.getElementById('inputSearch').value;
	$.ajax({
		url : 'UserControl',
		data : {
			inputSearch : srch,
		},
		success : function(responseText) {
			if(responseText === "failed"){
				console.log("Couldn't find User.");
				document.getElementById("userpagewrapper").style.display = "none";
			}else{
				var splitted = responseText.split(";");
				if(splitted.length > 0){
					document.getElementById("userIdSpan").innerHTML = splitted[0];
					document.getElementById("hiddenid").value = splitted[0];
					document.getElementById("usernameSpan").innerHTML = splitted[1];
					document.getElementById("emailSpan").innerHTML = splitted[2];
					document.getElementById("birthdateSpan").innerHTML = "" + splitted[3].charAt(6) + splitted[3].charAt(7) + "." + splitted[3].charAt(4) + splitted[3].charAt(5) + "." + splitted[3].charAt(0) + splitted[3].charAt(1) + splitted[3].charAt(2) + splitted[3].charAt(3);	
					var usertypeselector = document.getElementById("usertypeselector");
					
					usertypeselector.setAttribute('onchange','changedUsertype(' + splitted[4] + ', ' + splitted[5] + ')');
					if(splitted[4] == 4 ){
						document.getElementById("labelToConnect").style.display = "none";
						document.getElementById("artistToConnect").style.display = "inline";
						document.getElementById("zuordnugsdiv").style.display = "block";
						if(splitted[5] != 0){
							document.getElementById("artistToConnect").value = splitted[5];
						}		
					}else if(splitted[4] == 5){
						document.getElementById("zuordnugsdiv").style.display = "block";
						document.getElementById("labelToConnect").style.display = "inline";
						document.getElementById("artistToConnect").style.display = "none";
						if(splitted[5] != 0){
							document.getElementById("labelToConnect").value = splitted[5];
						}
					}else{
						document.getElementById("zuordnugsdiv").style.display = "none";
					}
					var options = usertypeselector.options;
					usertypeselector.value = splitted[4];
					
					if(ownusertypeid == 3){
						if(splitted[4] == 1 || splitted[4] == 3){
							usertypeselector.disabled = true;
						}else{
							usertypeselector.disabled = false;								
						}
					}									
					document.getElementById("userpagewrapper").style.display = "inline";
				}else{
					document.getElementById("userpagewrapper").style.display = "none";
				}
			}
		}
	});
}