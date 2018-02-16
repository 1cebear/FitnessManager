var userURL = "http://localhost:8080/rest/users";

var categoriesURL = "http://localhost:8080/rest/categories";

var trainingsURL = "http://localhost:8080/rest/trainings";

var monthArray;

var currentYear;

var currentMonth;

var startDate;

var endDate;

var currentUser;

var currentExercise;

refresh();

$(document).ready(function () {
    $('#month').val(monthArray[currentMonth]);
    $('#year').val(currentYear);
    findUser();
    $("#test").click(function () {
        alert("" + startDate + " - " + endDate);
    });

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
    $("#trainingsTable").on("click", "td", function () {
        var now = new Date(startDate.getFullYear(), startDate.getMonth(), $(this).attr("data-day"));
        var day = ("0" + now.getDate()).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);

        var today = now.getFullYear() + "-" + (month) + "-" + (day);

        $("#Exercise").val($(this).attr("data-exerciseName"));
        $("#ExerciseId").val($(this).attr("data-exerciseId"));
        $("#Category").val($(this).attr("data-categoryName"));
        $("#CategoryId").val($(this).attr("data-categoryId"));
        $("#Weight").val($(this).attr("data-weight"));
        $("#Date").val(today);
        $("#Done").prop('checked', $(this).attr("data-done") == "false" ? false : true);
        $("#trainingId").val($(this).attr("data-trainingId"));
        $('#trainingForm').modal();
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

function refreshTable() {
    $('#trainingsTable thead th').remove();
    $('#trainingsTable tbody tr').remove();
    $("table thead").append("<tr>");
    $("table thead").append("<th>Category</th>");
    $("table thead").append("<th>Exercise</th>");
    var currentDate = new Date(startDate.getTime());
    while (currentDate < endDate) {
        var curr_date = currentDate.getDate();
        var curr_month = currentDate.getMonth() + 1;
        var curr_year = currentDate.getFullYear();
        $("table thead").append("<th>" + curr_date + "." + curr_month + " \n " + curr_year + "</th>");
        currentDate.setDate(currentDate.getDate() + 1);
    }
    $("table thead").append("</tr>");
    findTrainings();
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

function findTrainings() {
    console.log('findTrainings:');
    $.ajax({
        type: 'GET',
        url: trainingsURL + "/foruser/" + currentUser.id + "?startDate=" + startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate() + "&endDate=" + endDate.getFullYear() + "-" + (endDate.getMonth() + 1) + "-" + endDate.getDate(),
        dataType: "json", // data type of response
        success: renderListTrainings,
        error: function (jqXHR, textStatus, errorThrown) {
            alert('findTrainings error: ' + textStatus);
        }
    });
}

function renderListTrainings(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    $.each(list, function (index, row) {
        var markup = "<tr><td>" + row.category.name + "</td><td>" + row.exercise.name + "</td>";
        for (var i = 0; i < row.trainings.length; i++) {
            value = row.trainings[i];
            var training = "";
            if (value == null) {
                training = "";
            }
            else if (value.done) {
                training = "<span class=\"done\" >" + value.weight + "</span>";
            }
            else if (value.weight == 0) {
                training = value.weight;
            }
            else {
                training = "<span class=\"planned\" >" + value.weight + "</span>";
            }
            markup += "<td data-categoryId = " + row.category.id + " data-categoryName = " + row.category.name + " data-exerciseId = " + row.exercise.id + " data-exerciseName = " + row.exercise.name + " data-weight = " + (value == null ? 0 : value.weight) + " data-trainingId = " + (value == null ? null : value.id) + " data-done = " + (value == null ? false : value.done) + " data-day = " + (i + 1) + " > " + (value == null ? " - " : training) + " </td>";
        }
        markup += "</tr>";
        $("table tbody").append(markup);
    });
}

function createUpdateTraining() {
    findExercise();
}

function findExercise() {
    console.log('findExercise:');
    $.ajax({
        type: 'GET',
        url: categoriesURL + "/" + $("#CategoryId").val() + "/exercises/" + $("#ExerciseId").val(),
        dataType: "json", // data type of response
        success: function (data) {
            console.log('findExercise success: ' + data.name);
            currentExercise = data;
            if ($("#trainingId").val() == "null") {
                createTraining();
            }
            else {
                updateTraining();
            }
        }
    });
}

function createTraining() {
    console.log('createTraining');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: trainingsURL + "/" + currentUser.id + "/" + currentExercise.id,
        dataType: "text",
        data: trainingToJSON(null),
        success: function () {
            alert('Training created successfully');
            refreshTable();

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createTraining error: ' + textStatus);
        }
    });
}

function updateTraining() {
    console.log('updateTraining');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: trainingsURL + "/" + currentUser.id + "/" + currentExercise.id + "/" + $("#trainingId").val(),
        dataType: "text",
        data: trainingToJSON($("#trainingId").val()),
        success: function () {
            alert('Training updated successfully');
            refreshTable();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateTraining error: ' + textStatus);
        }
    });
}

function trainingToJSON(id) {
    return JSON.stringify({
        "id": id == null ? null : id,
        "exercise": currentExercise,
        "user": currentUser,
        "weight": $("#Weight").val(),
        "date": $("#Date").val(),
        "done": $("#Done").is(":checked")
    });
}