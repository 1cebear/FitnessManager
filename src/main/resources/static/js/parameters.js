var userURL = "http://localhost:8080/rest/users";

var userParametersURL = "http://localhost:8080/rest/userparameters";

var parametersURL = "http://localhost:8080/rest/parameters";

var monthArray;

var currentYear;

var currentMonth;

var startDate;

var endDate;

var currentParameter;

var graphData;

refresh();

$(document).ready(function () {
    $('#month').val(monthArray[currentMonth]);
    $('#year').val(currentYear);
    findUser();
    $('select.year').on('change', function () {
        currentYear = this.value;
        startDate = new Date(currentYear, currentMonth, 1);
        endDate = new Date(currentYear, currentMonth + 1, 0, 23, 59, 59);
        refreshTable();
    });
    $('select.month').on('change', function () {
        currentMonth = monthArray.indexOf(this.value);
        startDate = new Date(currentYear, currentMonth, 1);
        endDate = new Date(currentYear, currentMonth + 1, 0, 23, 59, 59);
        refreshTable();
    });
    $('select.viewType').on('change', function () {
        if (this.value == "table") {
            $('#chartContainer').prop('hidden', true);
            $('#parametersTable').prop('hidden', false);
            refreshTable();
        }
        else {
            $('#chartContainer').prop('hidden', false);
            $('#parametersTable').prop('hidden', true);
            getGraphData();
        }
    });
    $("#parametersTable").on("click", "td", function () {
        if ($(this).attr("data-parameterName") != undefined) {
            var now = new Date(startDate.getFullYear(), startDate.getMonth(), $(this).attr("data-day"));
            var day = ("0" + now.getDate()).slice(-2);
            var month = ("0" + (now.getMonth() + 1)).slice(-2);

            var today = now.getFullYear() + "-" + (month) + "-" + (day);

            $("#Parameter").val($(this).attr("data-parameterName"));
            $("#ParameterId").val($(this).attr("data-parameterId"));
            $("#UpValue").val($(this).attr("data-value"));
            $("#Date").val(today);
            $("#upId").val($(this).attr("data-upId"));
            $('#parametersForm').modal();
        }
    });
});

function refresh() {
    monthArray = [];
    monthArray.push("January");
    monthArray.push("February");
    monthArray.push("March");
    monthArray.push("April");
    monthArray.push("May");
    monthArray.push("June");
    monthArray.push("July");
    monthArray.push("August");
    monthArray.push("September");
    monthArray.push("October");
    monthArray.push("November");
    monthArray.push("December");
    var d = new Date();
    currentYear = d.getFullYear();
    currentMonth = d.getMonth();
    startDate = new Date(currentYear, currentMonth, 1);
    endDate = new Date(currentYear, currentMonth + 1, 0, 23, 59, 59);
}

function findUser() {
    console.log('findUser:');
    $.ajax({
        type: 'GET',
        url: userURL + "/active",
        dataType: "json", // data type of response
        success: function (data) {
            console.log('findUser success: ' + data.name);
            currentUser = data;
            refreshTable();
        }
    });
}

function refreshTable() {
    $('#parametersTable thead th').remove();
    $('#parametersTable tbody tr').remove();
    $("#parametersTable thead").append("<tr>");
    $("#parametersTable thead").append("<th>Parameter</th>");
    var currentDate = new Date(startDate.getTime());
    while (currentDate < endDate) {
        var curr_date = currentDate.getDate();
        var curr_month = currentDate.getMonth() + 1;
        var curr_year = currentDate.getFullYear();
        $("#parametersTable thead").append("<th>" + curr_date + "." + curr_month + " \n " + curr_year + "</th>");
        currentDate.setDate(currentDate.getDate() + 1);
    }
    $("#parametersTable thead").append("</tr>");
    findParameters();
}

function findParameters() {
    console.log('findParameters:');
    $.ajax({
        type: 'GET',
        url: userParametersURL + "/" + currentUser.id + "/foruser/" + "?startDate=" + startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate() + "&endDate=" + endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDate(),
        dataType: "json", // data type of response
        success: renderListParameters,
        error: function (jqXHR, textStatus, errorThrown) {
            alert('findParameters error: ' + textStatus);
        }
    });
}

function renderListParameters(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    $.each(list, function (index, row) {
        var markup = "<tr><td>" + row.parameter.name + "</td>";
        for (var i = 0; i < row.userParametersList.length; i++) {
            upRow = row.userParametersList[i];
            var up = "";
            if (upRow == null) {
                up = "";
            }
            else {
                up = upRow.value;
            }
            markup += "<td data-parameterName = " + row.parameter.name + " data-parameterId = " + row.parameter.id + " data-value = " + (upRow == null ? 0 : upRow.value) + " data-upId = " + (upRow == null ? null : upRow.id) + " data-day = " + (i + 1) + " > " + (upRow == null ? " - " : up) + " </td>";
        }
        markup += "</tr>";
        $("#parametersTable tbody").append(markup);
    });

}

function createUpdateUp() {
    findParameter();
}

function findParameter() {
    console.log('findParameter:');
    $.ajax({
        type: 'GET',
        url: parametersURL + "/" + $("#ParameterId").val(),
        dataType: "json", // data type of response
        success: function (data) {
            console.log('findParameter success: ' + data.name);
            currentParameter = data;
            if ($("#upId").val() == "null") {
                createUp();
            }
            else {
                updateUp();
            }
        }
    });
}

function createUp() {
    console.log('createUp');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: userParametersURL + "/" + currentUser.id + "/" + currentParameter.id,
        dataType: "text",
        data: upToJSON(null),
        success: function () {
            alert('UserParameter created successfully');
            refreshTable();

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createUp error: ' + textStatus);
        }
    });
}

function updateUp() {
    console.log('updateUp');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: userParametersURL + "/" + currentUser.id + "/" + currentParameter.id + "/" + $("#upId").val(),
        dataType: "text",
        data: upToJSON($("#trainingId").val()),
        success: function () {
            alert('updateUp updated successfully');
            refreshTable();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateUp error: ' + textStatus);
        }
    });
}

function upToJSON(id) {
    return JSON.stringify({
        "id": id == null ? null : id,
        "parameter": currentParameter,
        "user": currentUser,
        "value": $("#UpValue").val(),
        "date": $("#Date").val()
    });
}

function drawGraph() {
    var options = {
        animationEnabled: true,
        theme: "light2",
        title: {
            text: "Parameters"
        },
        axisX: {
            valueFormatString: "DD MMM"
        },
        axisY: {
            title: "Value",
            suffix: "K",
            minimum: 30
        },
        toolTip: {
            shared: true
        },
        legend: {
            cursor: "pointer",
            verticalAlign: "bottom",
            horizontalAlign: "left",
            dockInsidePlotArea: true,
            itemclick: toogleDataSeries
        },
        data: graphData
    };
    $("#chartContainer").CanvasJSChart(options);

    function toogleDataSeries(e) {
        if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
            e.dataSeries.visible = false;
        } else {
            e.dataSeries.visible = true;
        }
        e.chart.render();
    }
}

function getGraphData() {
    var startDateG = new Date(startDate.getFullYear(), 0, 1);
    var endDateG = new Date(endDate.getFullYear(), 11, 31);
    console.log('findParameters:');
    $.ajax({
        type: 'GET',
        url: userParametersURL + "/" + currentUser.id + "/foruser/" + "?startDate=" + startDateG.getFullYear() + "-" + (startDateG.getMonth() + 1) + "-" + startDateG.getDate() + "&endDate=" + endDateG.getFullYear() + "-" + (endDateG.getMonth() + 1) + "-" + endDateG.getDate(),
        dataType: "json", // data type of response
        success: renderGraphData,
        error: function (jqXHR, textStatus, errorThrown) {
            alert('findParameters error: ' + textStatus);
        }
    });
}

function renderGraphData(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    graphData = [];
    $.each(list, function (index, row) {
        var dataPoints = [];
        for (var i = 0; i < row.userParametersList.length; i++) {
            value = row.userParametersList[i];
            if (value != null) {
                dataPoints.push(
                    {
                        x: new Date(value.date),
                        y: value.value
                    }
                )
            }
        }
        graphData.push(
            {
                type: "line",
                showInLegend: true,
                name: row.parameter.name,
                xValueFormatString: "DD MMM, YYYY",
                yValueFormatString: "#,##",
                dataPoints: dataPoints
            }
        )
    });
    drawGraph();
}