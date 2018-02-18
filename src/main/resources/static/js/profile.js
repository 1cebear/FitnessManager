var categoriesURL = "http://localhost:8080/rest/categories";

var userURL = "http://localhost:8080/rest/users";

var userExercisesURL = "http://localhost:8080/rest/userexercises";

var currentUser;

var currentCategoryId;

var userExercisesArray;

var currentExercise;

var currentExerciseId;

var currentUserExercise;

refresh();

function refresh() {
    userExercisesArray = [];
    findCategories();
    findUser();
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
            $("#userName").val(currentUser.name);
            $("#userEmail").val(currentUser.email);
        }
    });

}

function findCategories() {
    console.log('findAllCategories:');
    $.ajax({
        type: 'GET',
        url: categoriesURL,
        dataType: "json", // data type of response
        success: renderListCategories
    });
}

function renderListCategories(data) {
    // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    $.each(list, function (index, category) {
        $li = $('<li/>');
        $li.attr('data-identity', category.id);
        $li.append("<a href='profile#''>" + category.name + "</a>");
        $('#categoriesList').append($li);
    });

}

function updateProfile() {
    if ($("#userPass").val() != $("#repeatPassword").val()) {
        alert("Passwords do not match");
    }
    else {
        updateUser();
    }
}

function updateUser() {
    console.log('updateUser');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: userURL + "/" + currentUser.id,
        dataType: "text",
        data: userToJSON(),
        success: function () {
            alert('User updated successfully');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateUser error: ' + textStatus);
        }
    });
}

function userToJSON() {
    return JSON.stringify({
        "id": currentUser.id,
        "email": $("#userEmail").val(),
        "name": $("#userName").val(),
        "password": $("#userPass").val(),
        "enabled": currentUser.enabled,
        "registered": currentUser.registered,
        "roles": currentUser.roles,
    });
}

$(document).on('click', "li", function () {
    currentCategoryId = $(this).data('identity');
    $('#exercisesTable thead th').remove();
    $('#exercisesTable tbody tr').remove();
    findUserExercises();
})

function findExercisesByCategory() {
    console.log('findExercisesByCategory:');
    $.ajax({
        type: 'GET',
        url: categoriesURL + "/" + currentCategoryId + "/exercises",
        dataType: "json", // data type of response
        success: renderExercisesByCategory
    });
}

function renderExercisesByCategory(data) {
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $("#exercisesTable thead").append("<tr>");
    $("#exercisesTable thead").append("<th>Do</th>");
    $("#exercisesTable thead").append("<th>Exercise</th>");
    $("#exercisesTable thead").append("</tr>")
    $.each(list, function (index, row) {
        var markup = "<tr data-exerciseId = " + row.id + ">";
        markup += "<td><input class='record' type='checkbox' name='record' " + ((userExercisesArray.indexOf(row.id) != -1) ? "checked" : "") + "></td>";
        markup += "<td>" + row.name + "</td></tr>"
        $("#exercisesTable tbody").append(markup);
    });
}

function findUserExercises() {
    console.log('findUserExercises:');
    $.ajax({
        type: 'GET',
        url: userExercisesURL + "/" + currentUser.id,
        dataType: "json", // data type of response
        success: renderUserExercises
    });
}

function renderUserExercises(data) {
    userExercisesArray = [];
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $.each(list, function (index, row) {
        userExercisesArray.push(row[1]);
    });
    findExercisesByCategory();
}

$(document).ready(function () {
    $('#exercisesTable').on('click', 'input[type="checkbox"]', function () {
        currentExerciseId = $(this).closest('tr').attr("data-exerciseId");
        if ($(this).is(":checked")) {
            createUserExerciseByExercise($(this));
        }
        else {
            deleteUserExerciseByExercise($(this));
        }
    });
});

function createUserExerciseByExercise(elem) {
    var exerciseId = elem.closest('tr').attr("data-exerciseId");
    console.log('findExercise:');
    $.ajax({
        type: 'GET',
        url: categoriesURL + "/" + currentCategoryId + "/exercises/" + exerciseId,
        dataType: "json", // data type of response
        success: function (data) {
            console.log('findExercise success: ' + data.name);
            currentExercise = data;
            createUserExercise(elem);

        }
    });
}

function createUserExercise(elem) {
    console.log('createUserExercise');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: userExercisesURL + "/" + currentUser.id + "/" + currentExerciseId,
        dataType: "text",
        data: userExerciseToJSON(),
        success: function () {
            alert('UserExercise created successfully');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createUserExercise error: ' + textStatus);
        }
    });
}

function deleteUserExerciseByExercise(elem) {
    console.log('findUserExercise:');
    $.ajax({
        type: 'GET',
        url: userExercisesURL + "/" + currentUser.id + "/" + currentExerciseId,
        dataType: "json", // data type of response
        success: function (data) {
            currentUserExercise = data[0];
            deleteUserExercise();
        }
    });
}

function deleteUserExercise() {
    console.log('deleteUserExercise');
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: userExercisesURL + "/" + currentUser.id + "/" + currentExerciseId + "/" + currentUserExercise.id,
        dataType: "text",
        success: function () {
            alert('UserExercise deleted successfully');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteUserExercise error: ' + textStatus);
        }
    });
}

function userExerciseToJSON(id) {
    return JSON.stringify({
        "id": id == null ? null : id,
        "user": currentUser,
        "exercise": currentExercise
    });
}