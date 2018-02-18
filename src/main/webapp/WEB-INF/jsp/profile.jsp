<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="/js/profile.js"></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">

</div>
<div class="leftTopArea">
    <ul id="categoriesList"></ul>
</div>
<div class="rightTopArea">
    <table id="exercisesTable">
        <thead>

        </thead>
        <tbody>

        </tbody>
    </table>
</div>
<div class="bottomArea">
    <br>
    <label for="userName" class="col-sm-2 control-label">Name</label>
    <input type="text" name="userName" id="userName"><br><br>
    <label for="userEmail" class="col-sm-2 control-label">Email</label>
    <input type="email" name="userEmail" id="userEmail"><br><br>
    <label for="userPass" class="col-sm-2 control-label">Password</label>
    <input type="password" name="userPass" id="userPass"><br><br>
    <label for="repeatPassword" class="col-sm-2 control-label">Repeat password</label>
    <input type="password" name="repeatPassword" id="repeatPassword"><br><br>
    <button class="btn btn-primary" type="button" onclick="updateProfile()">
        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
    </button>
</div>
</body>

</html>