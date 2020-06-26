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