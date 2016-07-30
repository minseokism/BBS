<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">Minseokism World</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="/">Main <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
      </form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/users/signup">Sign Up</a></li>
        <li><a href="#" data-toggle="popover" id="signin">Sign In</a></ul>
        
        <div id="SignInForm" class="hide">
		    <form action="" id="popForm" method="get">
		        <div style="margin-right:80px; left:-100px;">
		            <label for="signinid">Id:</label>
		            <input type="text" name="signinid" id="signinid" class="form-control input-md">
		            <label for="signinpwd">Password:</label>
		            <input type="password" name="signinpwd" id="signinpwd" class="form-control input-md" style="margin-bottom:10px;">
		            <button type="button" id="signinsubmit"class="btn btn-primary" data-loading-text="Sending info.."><em class="icon-ok"></em> Sign In</button>
		        </div>
		    </form>
		</div>
		
	 </div>
  </div>
</nav>