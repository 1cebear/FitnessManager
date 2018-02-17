<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="/js/parameters.js"></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">

</div>
<select class = "month" name = "month" id="month">
    <option value="January">January</option>
    <option value="February">February</option>
    <option value="March">March</option>
    <option value="April">April</option>
    <option value="May">May</option>
    <option value="June">June</option>
    <option value="July">July</option>
    <option value="August">August</option>
    <option value="September">September</option>
    <option value="October">October</option>
    <option value="November">November</option>
    <option value="December">December</option>
</select>
<select class="year" name="year" id="year">
    <option value="2015">2015</option>
    <option value="2016">2016</option>
    <option value="2017">2017</option>
    <option value="2018">2018</option>
    <option value="2019">2019</option>
    <option value="2020">2020</option>
</select>
<select class="viewType" name="viewType" id="viewType">
    <option value="table">Table</option>
    <option value="graph">Graph </option>
</select>
<br>
<br>
<br>
<table id="parametersTable">
    <thead>

    </thead>
    <tbody>

    </tbody>
</table>
<br>
<br>
<br>
<div id="chartContainer"></div>
<div class="modal fade" id="parametersForm">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitleTraining"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="parametersForm">
                    <input type="hidden" id="upId" name="upId">
                    <input type="hidden" id="ParameterId" name="ParameterId">
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Parameter</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="Parameter" name="Parameter"
                                   placeholder="Parameter" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Date</label>

                        <div class="col-xs-9">
                            <input type="date" class="form-control" id="Date" name="Date"
                                   placeholder="Date" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Value</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="UpValue" name="UpValue"
                                   placeholder="Value" step="0.01" min="0">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="createUpdateUp()">
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