var categoriesURL = "http://localhost:8080/rest/categories";

var parametersURL = "http://localhost:8080/rest/parameters";

var currentCategoryElem;

var clickFlag;

var currentCategoryId;

var currentCategory;

var currentExerciseId;

var currentExercise;

var exercisesArray;

var categoriesArray;

var parametersArray;

var currentParam;

var currentParamId;

var isCategoryCreate;

var isExerciseCreate;

var isParameterCreate;

var categoryEdit;

var exerciseEdit;

var parameterEdit;

refresh();

$(document).ready(function () {
    $("#createCategory").click(function () {
        categoryEdit = true;
        exerciseEdit = false;
        parameterEdit = false;
        isCategoryCreate = true;
        $('#editForm').modal();
    });
    $("#updateCategory").click(function () {
        if (currentCategoryId == 0) {
            alert('Select category first');
        }
        else {
            $('#editName').val(currentCategory.name);
            $('#editDescription').val(currentCategory.description);
            categoryEdit = true;
            exerciseEdit = false;
            parameterEdit = false;
            isCategoryCreate = false;
            $('#editForm').modal();
        }
    });
    $("#deleteCategory").click(function () {
        if (currentCategoryId == 0) {
            alert('Select category first');
        }
        else {
            deleteCategory();
        }
    });
    $("#createExercise").click(function () {
        categoryEdit = false;
        exerciseEdit = true;
        parameterEdit = false;
        isExerciseCreate = true;
        $('#editForm').modal();
    });
    $("#updateExercise").click(function () {
        if (currentExerciseId == 0) {
            alert('Select exercise first');
        }
        else {
            $('#editName').val(currentExercise.name);
            $('#editDescription').val(currentExercise.description);
            categoryEdit = false;
            exerciseEdit = true;
            parameterEdit = false;
            isExerciseCreate = false;
            $('#editForm').modal();
        }
    });
    $("#deleteExercise").click(function () {
        if (currentExerciseId == 0) {
            alert('Select exercise first');
        }
        else {
            deleteExercise();
        }
    });
    $("#createParameter").click(function () {
        categoryEdit = false;
        exerciseEdit = false;
        parameterEdit = true;
        isParameterCreate = true;
        $('#editForm').modal();
    });
    $("#updateParameter").click(function () {
        if (currentParamId == 0) {
            alert('Select parameter first');
        }
        else {
            $('#editName').val(currentParam.name);
            $('#editDescription').val(currentParam.description);
            categoryEdit = false;
            exerciseEdit = false;
            parameterEdit = true;
            isParameterCreate = false;
            $('#editForm').modal();
        }
    });
    $("#deleteParameter").click(function () {
        if (currentParamId == 0) {
            alert('Select parameter first');
        }
        else {
            deleteParameter();
        }
    });
});

$(document).on('click', "li", function () {
    if ($(this).data('category') == false) {
        clickFlag = false;
        currentExerciseId = $(this).data('identity');
        findExerciseById();
    }
    if ($(this).parent().get(0) == $('#categoriesList').get(0) && clickFlag) {
        currentCategoryId = $(this).data('identity');
        for (var i = 0; i < exercisesArray.length; i++) {
            exercisesArray[i].detach();
        }
        currentCategoryElem = $(this);
        findExercisesByCategory();

    }
    else if ($(this).parent().get(0) == $('#categoriesList').get(0) && !clickFlag) {
        clickFlag = true;
    }
    else if ($(this).parent().get(0) == $('#parametersList').get(0)) {
        currentParamId = $(this).data('identity');
        findParameterById();
    }
})

function refresh() {
    currentCategory = null;
    currentExercise = null;
    currentParam = null;
    currentCategoryId = 0;
    currentExerciseId = 0;
    currentParamId = 0;
    clickFlag = true;
    if (categoriesArray != null) {
        for (var i = 0; i < categoriesArray.length; i++) {
            categoriesArray[i].detach();
        }
    }
    categoriesArray = [];
    if (exercisesArray != null) {
        for (var i = 0; i < exercisesArray.length; i++) {
            exercisesArray[i].detach();
        }
    }
    exercisesArray = [];
    if (parametersArray != null) {
        for (var i = 0; i < parametersArray.length; i++) {
            parametersArray[i].detach();
        }
    }
    parametersArray = [];
    findAllCategories();
    findAllParameters();
}

function findAllCategories() {
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
        $li.attr('data-category', true);
        $li.append("<a href='admin#''>" + category.name + "</a>");
        categoriesArray.push($li);
        $('#categoriesList').append($li);
    });

}


function findExerciseById() {
    console.log('findExerciseById:');
    $.ajax({
        type: 'GET',
        url: categoriesURL + "/" + currentCategoryId + "/exercises/" + currentExerciseId,
        dataType: "json", // data type of response
        success: function (data) {
            console.log('findItemById success: ' + data.name);
            currentExercise = data;
            $('#description').val(currentExercise.name + ":\n\n " + currentExercise.description);
        }
    });
}

function findExercisesByCategory() {
    console.log('findExercisesByCategory:');
    $.ajax({
        type: 'GET',
        url: categoriesURL + "/" + currentCategoryId + "/exercises",
        dataType: "json", // data type of response
        success: renderExercisesByCategory
    });

    $.ajax({
        type: 'GET',
        url: categoriesURL + '/' + currentCategoryId,
        dataType: "json",
        success: function (data) {
            console.log('findExercisesByCategory success: ' + data.name);
            currentCategory = data;
            $('#description').val(currentCategory.name + ":\n\n " + currentCategory.description);
        }
    });
}

function renderExercisesByCategory(data) {
    exercisesArray = [];
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $ul = $('<ul/>');
    $ul.addClass("ulExercises");
    $.each(list, function (index, item) {
        $li = $('<li/>');
        $li.attr('data-identity', item.id);
        $li.attr('data-category', false);
        $li.append("<a href='admin#''>" + item.name + "</a>");
        exercisesArray.push($li);
        $ul.append($li);
    });
    currentCategoryElem.append($ul);

}

function findAllParameters() {
    console.log('findAllParameters:');
    $.ajax({
        type: 'GET',
        url: parametersURL,
        dataType: "json", // data type of response
        success: renderListParameters
    });
}

function renderListParameters(data) {
    // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    $.each(list, function (index, parameter) {
        $li = $('<li/>');
        $li.attr('data-identity', parameter.id);
        $li.attr('data-category', true);
        $li.append("<a href='admin#''>" + parameter.name + "</a>");
        parametersArray.push($li);
        $('#parametersList').append($li);
    });

}


function findParameterById() {
    console.log('findParameterById:');
    $.ajax({
        type: 'GET',
        url: parametersURL + "/" + currentParamId,
        dataType: "json", // data type of response
        success: function (data) {
            console.log('findParameterById success: ' + data.name);
            currentParam = data;
            $('#descriptionParameter').val(currentParam.name + ":\n\n " + currentParam.description);
        }
    });
}

function edit() {
    if (categoryEdit) {
        if (isCategoryCreate) {
            createCategory();
        }
        else {
            updateCategory();
        }
    }
    else if (exerciseEdit) {
        if (isExerciseCreate) {
            createExercise();
        }
        else {
            updateExercise();
        }
    }
    else if (parameterEdit) {
        if (isParameterCreate) {
            createParameter();
        }
        else {
            updateParameter();
        }
    }
}

function createCategory() {
    console.log('createCategory');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: categoriesURL,
        dataType: "text",
        data: editValueToJSON(null),
        success: function () {
            alert('Category created successfully');
            refresh();

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createCategory error: ' + textStatus);
        }
    });
}

function updateCategory() {
    console.log('updateCategory');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: categoriesURL + "/" + currentCategoryId,
        dataType: "text",
        data: editValueToJSON(currentCategoryId),
        success: function () {
            alert('Category updated successfully');
            refresh();
            $('#description').val($('#editName').val() + ":\n\n " + $('#editDescription').val());
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateCategory error: ' + textStatus);
        }
    });
}

function deleteCategory() {
    console.log('deleteCategory');
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: categoriesURL + "/" + currentCategoryId,
        dataType: "text",
        success: function () {
            alert('Category deleted successfully');
            refresh();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteCategory error: ' + textStatus);
        }
    });
}

function createExercise() {
    console.log('createExercise');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: categoriesURL + "/" + currentCategoryId + "/exercises",
        dataType: "text",
        data: editValueToJSON(null),
        success: function () {
            alert('Exercise created successfully');
            refresh();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createExercise error: ' + textStatus);
        }
    });
}

function updateExercise() {
    console.log('updateExercise');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: categoriesURL + "/" + currentCategoryId + "/exercises/" + currentExerciseId,
        dataType: "text",
        data: editValueToJSON(currentExerciseId),
        success: function () {
            alert('Exercise updated successfully');
            refresh();
            $('#description').val($('#editName').val() + ":\n\n " + $('#editDescription').val());
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateExercise error: ' + textStatus);
        }
    });
}

function deleteExercise() {
    console.log('deleteExercise');
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: categoriesURL + "/" + currentCategoryId + "/exercises/" + currentExerciseId,
        dataType: "text",
        success: function () {
            alert('Exercise deleted successfully');
            refresh();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteExercise error: ' + textStatus);
        }
    });
}

function createParameter() {
    console.log('createParameter');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: parametersURL,
        dataType: "text",
        data: editValueToJSON(null),
        success: function () {
            alert('Parameter created successfully');
            refresh();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createParameter error: ' + textStatus);
        }
    });
}

function updateParameter() {
    console.log('updateParameter');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: parametersURL + "/" + currentParamId,
        dataType: "text",
        data: editValueToJSON(currentParamId),
        success: function () {
            alert('Parameter updated successfully');
            refresh();
            $('#descriptionParameter').val($('#editName').val() + ":\n\n " + $('#editDescription').val());
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateParameter error: ' + textStatus);
        }
    });
}

function deleteParameter() {
    console.log('deleteParameter');
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: parametersURL + "/" + currentParamId,
        dataType: "text",
        success: function () {
            alert('Parameter deleted successfully');
            refresh();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteParameter error: ' + textStatus);
        }
    });
}

function editValueToJSON(id) {
    return JSON.stringify({
        "id": id == null ? null : id,
        "name": $('#editName').val(),
        "description": $('#editDescription').val()
    });
}
