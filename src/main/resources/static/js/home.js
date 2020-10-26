$(document).ready(function () {
    $("#employeesTable").DataTable({
        serverSide: true,
        processing: true,
        autoWidth: true,
        searching: false,
        ordering: false,
        draw: 1,
        ajax: function (data, callback, settings) {
            $.ajax({
                type: "GET",
                dataType: "json",
                url: "/employees",
                data: data,
                success: function (response) {
                    callback(response);
                }
            });
        },
        "columns": [
            {
                "data": "",
                "render": function (data, type, full, meta) {
                    return meta.row + 1;
                }
            },
            {
                "data": "",
                "render": function (data, type, full, meta) {
                    return full.firstname + " " + full.lastname;
                }
            },
            {"data": "birthDate",
                "render": function (data, type, full, meta) {
                    return formatLocalDate(data);
                }
            },
            {"data": "salary"},
            {"data": "position"},
            {"data": "department.name"},
            {
                "data": "",
                "render": function (data, type, full, meta) {
                    var html = "";
                    html = html + "<button style=\"font-size: 15px\" class=\"btn btn-warning btn-sm fa fa-edit ml-2\" data-toggle=\"modal\" data-target=\"#newEmployeeModal\" onclick='getModifyEmployeeModal(" + full.employeeid + ")' type=\"buttton\"></i></button>";
                    html = html + "<button style=\"font-size: 15px\" class=\"btn btn-danger btn-sm fa fa-trash ml-2\" type=\"buttton\" onclick='deleteEmployee(" + full.employeeid + ")'></i></button>";
                    return html;

                }
            }
        ]
    });

    $("#departmentTable").DataTable({
        serverSide: true,
        processing: true,
        autoWidth: true,
        searching: false,
        ordering: false,
        draw: 1,
        ajax: function (data, callback, settings) {
            $.ajax({
                type: "GET",
                dataType: "json",
                url: "/departments",
                data: data,
                success: function (response) {
                    callback(response);
                }
            });
        },
        "columns": [
            {
                "data": "",
                "render": function (data, type, full, meta) {
                    return meta.row + 1;
                }
            },
            {"data": "name"},
            {
                "data": "createdtime",
                "render": function (data, type, full, meta) {
                    return formatTimestamp(data);
                }
            },
            {
                "data": "",
                "render": function (data, type, full, meta) {
                    var html = "";
                    html = html + "<button style=\"font-size: 15px\" class=\"btn btn-warning btn-sm fa fa-edit ml-2\" data-toggle=\"modal\" data-target=\"#newDepartmentModal\" onclick='getModifyDepartmentModal(" + full.departmentid + ")' type=\"buttton\"></i></button>";
                    html = html + "<button style=\"font-size: 15px\" class=\"btn btn-danger btn-sm fa fa-trash ml-2\" type=\"buttton\" onclick='deleteDepartment(" + full.departmentid + ")'></i></button>";
                    return html;

                }
            }

        ]
    });
});


function formatTimestamp(timestamp){
    if(timestamp === null){
        return "";
    }
    var date = new Date(timestamp);
    var day = date.getDate();
    if (day < 10) {
        day = "0" + day;
    }
    var month = date.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    return day + "/" + month + "/" + date.getFullYear();
}



function formatLocalDate(data){
    if(data === null){
        return "";
    }
    var dateN = "";
    // var data_ = data.reverse();
    if(data.length === 3){
        if(data[2] < 10){
            dateN = dateN + "0" + data[2] + "/";
        }else{
            dateN = dateN + data[2] + "/";
        }
        if(data[1] < 10){
            dateN = dateN + "0" + data[1] + "/";
        }else{
            dateN = dateN + data[1] + "/";
        }
        return dateN + data[0]
    }
}


function getEmployeeModal() {
    $.ajax({
        type: "GET",
        url: "/getEmployeeModal",
        success: function (data) {
            $("#newEmployeeModal .modal-content").html($(data));
        }
    });
}


function getModifyDepartmentModal(id) {
    $.ajax({
        type: "GET",
        url: "/getModifyDepartmentModal/" + id,
        success: function (data) {
            $("#newDepartmentModal .modal-content").html($(data));
        }
    });
}


function getModifyEmployeeModal(id) {
    $.ajax({
        type: "GET",
        url: "/getModifyEmployeeModal/"+ id,
        success: function (data) {
            $("#newEmployeeModal .modal-content").html($(data));
        }
    });
}


function getDepartmentModal() {
    $.ajax({
        type: "GET",
        url: "/getDepartmentModal",
        success: function (data) {
            $("#newDepartmentModal .modal-content").html($(data));
        }
    });
}



function deleteDepartment(departmentid) {
    event.preventDefault();
    swal({
            title: "Are u sure?",
            text: "You will not be able to recover this item!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes",
            cancelButtonText: "No",
            closeOnConfirm: false,
            closeOnCancel: false
        },
        function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    type: "DELETE",
                    url: "/deleteDepartment/" + departmentid,
                    success: function (response) {
                        if (!response.error) {
                            swal({
                                    title: "Success",
                                    type: "success",
                                    text: response.message
                                },
                                function (isConfirm) {
                                    if (isConfirm) {
                                        $('#departmentTable').DataTable().draw();
                                    }
                                }
                            );
                        }
                        else {
                            swal({
                                title: "Error",
                                type: "error",
                                text: response.message
                            });
                        }
                    }
                })
            } else {
                swal("Canceled", "The item is safe", "error");
            }
        }
    );
    return false;
}


function deleteEmployee(employeeid) {
    event.preventDefault();
    swal({
            title: "Are u sure?",
            text: "You will not be able to recover this item!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes",
            cancelButtonText: "No",
            closeOnConfirm: false,
            closeOnCancel: false
        },
        function (isConfirm) {
            if (isConfirm) {
                $.ajax({
                    type: "DELETE",
                    url: "/deleteEmployee/" + employeeid,
                    success: function (response) {
                        if (!response.error) {
                            swal({
                                    title: "Success",
                                    type: "success",
                                    text: response.message
                                },
                                function (isConfirm) {
                                    if (isConfirm) {
                                        $('#employeesTable').DataTable().draw();
                                    }
                                }
                            );
                        }
                        else {
                            swal({
                                title: "Error",
                                type: "error",
                                text: response.message
                            });
                        }
                    }
                })
            } else {
                swal("Canceled", "The item is safe", "error");
            }
        }
    );
    return false;
}

