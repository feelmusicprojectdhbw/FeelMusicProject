function searchSongs(){
	var srch = document.getElementById('inputSearch').value;
	var slctArtist = document.getElementById("artist");
	var art = slctArtist.options[slctArtist.selectedIndex].text;
	$.ajax({
		url : 'SearchSong',
		data : {
			inputSearch : srch,
			artist: art
		},
		success : function(responseText) {
			if(responseText === "failed"){
				console.log("Couldn't find any Song.");
			}else{
				document.getElementById("resultwrapper").innerHTML = responseText; 
			}
		}
	});
}