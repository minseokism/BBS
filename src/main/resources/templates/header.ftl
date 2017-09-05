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
                <li class="active"><a href="#">게시판1<span class="sr-only">(current)</span></a></li>
                <li><a href="#">게시판2</a></li>
                <li><a href="#">게시판3</a></li>
                <li><a href="#">게시판4</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
            <#assign user = signInUser?if_exists>
     	    <#assign id = user.id?if_exists>
       	    <#if id == "">
                <li><a href="/users/signup">Sign Up</a></li>
       	        <li><a href="/users/signin">Sign In</a></li>
		    <#else>
                <li><a href="/users/updateForm">My Account</a></li>
                <li><a href="/users/signout"><Strong>Sign Out (${id})</Strong></a></li>
		    </#if>
            </ul>
        </div>
    </div>
</nav>