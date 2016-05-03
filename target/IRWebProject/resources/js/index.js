define(function(require, exports) {
	var searchEvent = require("searchEvent");
	var language = require("language");

	searchEvent.setSelector({
	    inputBox : "#inputBox",
	    searchTip : "#searchTip",
	    searchOption : ".searchOption",
	    searchButton : "#searchButton"
	});
	searchEvent.bindEvent();
	
	language.bindEvent();
});