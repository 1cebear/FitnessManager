<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="/js/admin.js"></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <input type="button" id="createCategory" value="Create category"/>
    <input type="button" id="updateCategory" value="Update category"/>
    <input type="button" id="deleteCategory" value="Delete category"/>
    <input type="button" id="createExercise" value="Create exercise"/>
    <input type="button" id="updateExercise" value="Update exercise"/>
    <input type="button" id="deleteExercise" value="Delete exercise"/>
    <input type="button" id="createParameter" value="Create parameter"/>
    <input type="button" id="updateParameter" value="Update parameter"/>
    <input type="button" id="deleteParameter" value="Delete parameter"/>
</div>
<div class="mainArea">
    <div class="leftTopArea">
        <ul id="categoriesList"></ul>
    </div>
    <div class="leftBottomArea">
        <ul id="parametersList"></ul>
    </div>
    <div class="rightTopArea">
        <textarea id="description" name="description" class="textAreaDescription"></textarea>
    </div>
    <div class="rightBottomArea">
        <textarea id="descriptionParameter" name="descriptionParameter" class="textAreaDescription"></textarea>
    </div>
</div>

<div class="modal fade" id="editForm">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitleItem"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="editForm">
                    <input type="hidden" id="editId" name="editId">
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="editName" name="editName"
                                   placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Description</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="editDescription" name="editDescription"
                                   placeholder="Description">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="edit()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>

</html>