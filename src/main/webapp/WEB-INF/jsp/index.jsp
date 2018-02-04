<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css"
          href="/css/style.css"/>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js" defer></script>
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header navbar-brand">Login</div>
        <div class="navbar-collapse collapse">
            <form:form class="navbar-form navbar-right" role="form" action="spring_security_check" method="post">
                <div class="form-group">
                    <input type="text" placeholder="Email" class="form-control" name="username">
                </div>
                <div class="form-group">
                    <input type="password" placeholder="Password" class="form-control" name="password">
                </div>
                <button type="submit" class="btn btn-success">
                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                </button>
            </form:form>
        </div>
    </div>
</div>
<div class="jumbotron">
    <div class="container">
        <a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/register">Register</a>
    </div>
</div>
</body>

</html>