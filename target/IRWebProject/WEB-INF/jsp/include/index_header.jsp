<!-- start header -->
<nav class="navbar navbar-inverse">
	<div class="container-fluid row">
		<div class="col-xs-3 col-sm-offset-9 col-sm-2 col-sm-offset-10">
			<div style="margin: 8px 0;" class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" style="width:50%">
					${currentLanguage.displayName}</button>
				<ul class="dropdown-menu" style="min-width: 50%;width:50%;">
					<li><a class="dropdownOption" href="javascript:;" language="${optionLanguage.shortName}">${optionLanguage.displayName}</a></li>
				</ul>
			</div>
		</div>

		<!-- <div class="navbar-header">
			<a class="navbar-brand" href="./index.html"></a>
		</div> -->
		<%-- <div class="col-sm-6 col-md-6">
			<form action="./search.html" class="navbar-form" role="search">
				<div class="input-group col-sm-12 col-md-12">
					<input type="text" class="form-control" placeholder="Search" name="query"
						value="${query == null ? '' : query}"/>
					<div class="input-group-btn">
						<button class="btn btn-default" type="submit">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</form>
		</div> --%>
	</div>
</nav>
<!-- end header -->