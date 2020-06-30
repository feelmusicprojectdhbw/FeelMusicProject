function focusPlaylist(id){
	$.ajax({
		url : 'Userpage_Ajax_Servlet',
		data : {
			playlistid : id
		},
		success : function(responseText) {
			if(responseText === "failed"){
				console.log("Couldn't load the Playlist.");
			}else{
				console.log(responseText);
				document.getElementById("playlistsongsdiv").innerHTML = responseText; 
			}
		}
	});
}

function playPlaylist(p, u){
	window.location.href = ("PlaylistAction_Servlet?a=play&p=" + p + "&u=" + u);
}

function deletePlaylist(pllst, usr){	
	var result = confirm("Are you sure you want to delete this playlist?");
	if (result) {
		$.ajax({
			url : 'PlaylistAction_Servlet',
			data : {
				a : 'delete',
				p : pllst,
				u : usr
			},
			success : function(responseText) {
				if(responseText === "failed"){
					console.log("Couldn't delete the Playlist.");
				}else{
					console.log(responseText);
					document.getElementById("pllstdiv" + pllst ).style.display = "none";
					document.getElementById("playlistsongsdiv").innerHTML = ""; 
				}
			}
		});
	}
}