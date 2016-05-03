<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script id="searchTemplate" type="text/html">
	{{each list as value index}}
	    <li><a class="searchTipOption" href="javascript:;">{{value.query}}</a></li>
	{{/each}}
</script>