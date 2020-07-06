function processValidations(){
		processFeelings();
		processStyles();
	}

    function processFeelings()
    {
        var feelings = document.getElementsByName("feeling");
        var selectedFeelings = [];
        for (var i = 0; i < feelings.length; i++) {
            if (feelings[i].checked) {
            	selectedFeelings.push(feelings[i].value);
            }
        }
        
        var hiddenSelectedFeelings = document.getElementById("selectedFeelings");
        hiddenSelectedFeelings.value = selectedFeelings.join(";");
    }
    
    function processStyles()
    {
        var styles = document.getElementsByName("style");
        var selectedStyles = [];
        for (var i = 0; i < styles.length; i++) {
            if (styles[i].checked) {
            	selectedStyles.push(styles[i].value);
            }
        }
        
        var hiddenSelectedStyles = document.getElementById("selectedStyles");
        hiddenSelectedStyles.value = selectedStyles.join(";");
        
    }