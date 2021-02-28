function createProject() {
    var record = {
        name : document.getElementById("name").value,
        num_groups : document.getElementById("num_groups").value,
        num_students : document.getElementById("num_students").value
    };

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/createProject");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(record));
    setTimeout(function() {
        location.href = "/view";
    }, 1000);
}

function addStudent(project_id) {
    var record = {
        name : document.getElementById("name").value
    };

    var xhr = new XMLHttpRequest();

    xhr.open("POST", "/students/add?id="+project_id);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(record));
    setTimeout(function() {
        location.reload();
    }, 100);
}


function removeStudent(id) {
    var xhr = new XMLHttpRequest();
    xhr.onload=deleteStudent;
    xhr.open("GET", "/students/remove?id="+id);
    xhr.send();
}

function deleteStudent() {
    var id = this.responseText;

    var table = document.getElementById("students");
    var row = document.getElementById("row_" + id);
    table.deleteRow(row.rowIndex);
}

function viewProject(id) {
    location.href="viewProject/"+id;
}
