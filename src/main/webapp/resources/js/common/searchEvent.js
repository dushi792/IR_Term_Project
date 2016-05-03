define(function(require, exports, module) {
	var $ = require("jquery");
	var template = require("template");

	// define searchEvent
	var searchEvent = {
	    selector : {
	        inputBox : "",
	        searchTip : "",
	        searchOption : "",
	        searchButton : ""
	    },
	    setSelector : function(obj) {
		    this.selector = obj;
	    },
	    bindEvent : function() {
		    // show searchTip event
		    $(this.selector.inputBox).on("keyup", function(e) {
			    if (event.which == 13 || event.keyCode == 13) {
				    $(searchEvent.selector.searchButton).click();
			    } else {
				    if ($(this).val()) {
					    searchEvent.sendRequest($(this).val());
				    } else {
					    $(searchEvent.selector.searchTip).hide();
				    }
			    }
		    });

		    // close searchTip event
		    $(document).on(
		            "click",
		            ":not(" + this.selector.inputBox + ", " + this.selector.searchTip + ","
		                    + this.selector.searchOption + ")", function(e) {
			            $(searchEvent.selector.searchTip).hide();
		            }).on("click",
		            this.selector.inputBox + ", " + this.selector.searchTip + "," + this.selector.searchOption,
		            function(e) {
			            return false;// equivalent to e.stopPropagation
		            });

		    // searchTip Option event
		    $(this.selector.searchTip).on("click", ".searchTipOption", function(e) {
			    $(searchEvent.selector.inputBox).val($(this).text());
			    $(searchEvent.selector.searchTip).hide();
			    $(searchEvent.selector.searchButton).click();
		    });

		    // search button event
		    $(this.selector.searchButton).on("click", function(e) {
			    var query = $(searchEvent.selector.inputBox).val();

			    var url = "./search.html?query=" + encodeURI(query);
			    location.href = url;
		    })
	    },
	    sendRequest : function(text) {
		    $.ajax({
		        url : "/searchRecord/searchTip.json",
		        dataType : "json",
		        data : {
			        query : text
		        },
		        success : function(data) {
			        // data type must be json

			        var html = template('searchTemplate', data);
			        if (html) {
				        $(searchEvent.selector.searchTip).html(html).show();
			        } else {
				        $(searchEvent.selector.searchTip).hide();
			        }
		        }
		    });
	    }
	};
	module.exports = searchEvent;
});