<!-- start header -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="col-md-2 col-sm-3 col-xs-4 col-md-offset-1">
			<a class="navbar-brand" href="./index.html"> <img src="./resources/img/logo2.png"
				class="img-responsive center-block" alt="logo">
			</a>
		</div>
		<div class="col-sm-6 col-md-6 col-xs-6">
			<div class="navbar-form" style="padding-top: 0; padding-bottom: 0;" role="search">
				<div class="input-group col-sm-12 col-md-12">
					<input id="inputBox_header" type="text" class="form-control" placeholder="Ask your question"
						value="${query}" />
					<div id="searchTipBox" class="dropdown">
						<ul id="searchTip_header" class="dropdown-menu">
							<li>aaaa</li>
						</ul>
					</div>
					<div class="input-group-btn">
						<button id="searchButton_header" class="btn btn-default">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xs-3 col-sm-2">
			<div style="margin: 8px 0;" class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" style="width:50%">
					${currentLanguage.displayName}</button>
				<ul class="dropdown-menu" style="min-width: 50%;width:50%;">
					<li><a class="dropdownOption" href="javascript:;" language="${optionLanguage.shortName}">${optionLanguage.displayName}</a></li>
				</ul>
			</div>
		</div>
	</div>
</nav>

<!-- end header -->