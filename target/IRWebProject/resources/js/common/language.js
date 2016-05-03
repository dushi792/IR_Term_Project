/**
 * language.js for language dropdown event by Ian Hu @ Dec 15, 2015
 */
define(function(require, exports, module) {
	var $ = require("jquery");
	require("bootstrap");// for dropdown effect

	var language = {
		bindEvent : function() {
			$(".dropdown-menu").on("click", ".dropdownOption", function() {
				console.log($(this), $(this).attr("language"))
				$.ajax({
				    url : "/language/setLanguage.json",
				    dataType : "json",
				    data : {
					    language : $(this).attr("language")
				    },
				    success : function(data) {
					    // data type must be json
					    if (data.errorCode == 0) {// success
						    location.reload();
					    } else {
						    // no way to be error
					    }
				    }
				});
			});
		}
	};
	module.exports = language;
});