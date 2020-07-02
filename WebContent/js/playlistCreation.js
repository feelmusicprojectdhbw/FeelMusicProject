function doGenre(genre) {
	var radios = document.getElementsByClassName(genre + "radio");
	var newChecked = -1;
	for(var index = 0; index < radios.length; index++){
	if(radios[index].checked == true){
		radios[index].checked = false;
		newChecked = index + 1;
		break;
		}
	} 
	if(newChecked == radios.length){
		newChecked = 0;
	}
	radios[newChecked].checked = true;
	document.getElementById(genre + "btn").style.backgroundColor = radios[newChecked].getAttribute("value");
	document.getElementById(genre + "btn").setAttribute("selection", newChecked);
	
	var subs = document.getElementById(genre + "div").getElementsByTagName("genre");
	for(var i = 0; i < subs.length; i++){
	doSubgenres(subs[i].getAttribute("value"), newChecked);
	}
}

function doSubgenres(genre, newChecked){
	var radios = document.getElementsByClassName(genre + "radio");
	for(var index = 0; index < radios.length; index++){
		radios[index].checked = false;
	}
	radios[newChecked].checked = true;
	document.getElementById(genre + "btn").style.backgroundColor = radios[newChecked].getAttribute("value");
	document.getElementById(genre + "btn").setAttribute("selection", newChecked);
}

function processValidations(){
	processGenres();
	processFeelings();
	processStyles();
	processLanguages();
}

function processGenres()
{
    var genres = document.getElementsByName("genre");
    var selectedGenres = [];
    var blockedGenres = [];
    for (var i = 0; i < genres.length; i++) {
        if (genres[i].getAttribute("selection") == 1) {
        	selectedGenres.push(genres[i].textContent);
        }else if (genres[i].getAttribute("selection") == 2) {
        	blockedGenres.push(genres[i].textContent);
        }
    }
    var hiddenSelectedGenres = document.getElementById("selectedGenres");
    hiddenSelectedGenres.value = selectedGenres.join(";");
    var hiddenBlockedGenres = document.getElementById("blockedGenres");
    hiddenBlockedGenres.value = blockedGenres.join(";");
}

function doFeeling(feeling) {
	var radios = document.getElementsByClassName(feeling + "radio");
	var newChecked = -1;
	for(var index = 0; index < radios.length; index++){
	if(radios[index].checked == true){
		radios[index].checked = false;
		newChecked = index + 1;
		break;
		}
	} 
	if(newChecked == radios.length){
		newChecked = 0;
	}
	radios[newChecked].checked = true;
	document.getElementById(feeling + "btn").style.backgroundColor = radios[newChecked].getAttribute("value");
	document.getElementById(feeling + "btn").setAttribute("selection", newChecked);
}

function processFeelings()
{
    var feelings = document.getElementsByName("feeling");
    var selectedFeelings = [];
    var blockedFeelings = [];
    for (var i = 0; i < feelings.length; i++) {
        if (feelings[i].getAttribute("selection") == 1) {
        	selectedFeelings.push(feelings[i].textContent);
        }else if (feelings[i].getAttribute("selection") == 2) {
        	blockedFeelings.push(feelings[i].textContent);
        }
    }
    var hiddenSelectedFeelings = document.getElementById("selectedFeelings");
    hiddenSelectedFeelings.value = selectedFeelings.join(";");
    var hiddenBlockedFeelings = document.getElementById("blockedFeelings");
    hiddenBlockedFeelings.value = blockedFeelings.join(";");
}

function doStyle(style) {
	var radios = document.getElementsByClassName(style + "radio");
	var newChecked = -1;
	for(var index = 0; index < radios.length; index++){
	if(radios[index].checked == true){
		radios[index].checked = false;
		newChecked = index + 1;
		break;
		}
	} 
	if(newChecked == radios.length){
		newChecked = 0;
	}
	radios[newChecked].checked = true;
	document.getElementById(style + "btn").style.backgroundColor = radios[newChecked].getAttribute("value");
	document.getElementById(style + "btn").setAttribute("selection", newChecked);
}

function processStyles()
{
    var styles = document.getElementsByName("style");
    var selectedStyles = [];
    var blockedStyles = [];
    for (var i = 0; i < styles.length; i++) {
        if (styles[i].getAttribute("selection") == 1) {
        	selectedStyles.push(styles[i].textContent);
        }else if (styles[i].getAttribute("selection") == 2) {
        	blockedStyles.push(styles[i].textContent);
        }
    }
    var hiddenSelectedStyles = document.getElementById("selectedStyles");
    hiddenSelectedStyles.value = selectedStyles.join(";");
    var hiddenBlockedStyles = document.getElementById("blockedStyles");
    hiddenBlockedStyles.value = blockedStyles.join(";");
}

function doLanguage(language) {
	var radios = document.getElementsByClassName(language + "radio");
	var newChecked = -1;
	for(var index = 0; index < radios.length; index++){
	if(radios[index].checked == true){
		radios[index].checked = false;
		newChecked = index + 1;
		break;
		}
	} 
	if(newChecked == radios.length){
		newChecked = 0;
	}
	radios[newChecked].checked = true;
	document.getElementById(language + "btn").style.backgroundColor = radios[newChecked].getAttribute("value");
	document.getElementById(language + "btn").setAttribute("selection", newChecked);
}

function processLanguages()
{
    var languages = document.getElementsByName("language");
    var selectedLanguages = [];
    var blockedLanguages = [];
    for (var i = 0; i < languages.length; i++) {
        if (languages[i].getAttribute("selection") == 1) {
        	selectedLanguages.push(languages[i].textContent);
        }else if (languages[i].getAttribute("selection") == 2) {
        	blockedLanguages.push(languages[i].textContent);
        }
    }
    var hiddenSelectedLanguages = document.getElementById("selectedLanguages");
    hiddenSelectedLanguages.value = selectedLanguages.join(";");
    var hiddenBlockedLanguages = document.getElementById("blockedLanguages");
    hiddenBlockedLanguages.value = blockedLanguages.join(";");
}