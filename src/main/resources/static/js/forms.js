$("#employeeForm").submit(function (e) {
    e.preventDefault();
    var form = $(this);
    $(".submitBtn").attr("disabled", true);
    $.ajax({
        type: form.attr('method'), // method attribute of form
        url: form.attr('action'),  // action attribute of form
        data: form.serialize(),
        success: function (response) {
            if (!response.error) {
                $('#newEmployeeModal').modal('toggle');
                swal({
                        title: "Success",
                        type: "success",
                        text: response.message
                    },
                    function (isConfirm) {
                        if (isConfirm) {
                            $("#employeesTable").DataTable().draw();
                        }
                    }
                );
            } else {
                swal({
                    title: "Error",
                    type: "error",
                    text: response.message
                });
            }
        }
    });
});


$("#departmentForm").submit(function (e) {
    e.preventDefault();
    var form = $(this);
    $(".submitBtn").attr("disabled", true);
    $.ajax({
        type: form.attr('method'), // method attribute of form
        url: form.attr('action'),  // action attribute of form
        data: form.serialize(),
        success: function (response) {
            if (!response.error) {
                $('#newDepartmentModal').modal('toggle');
                swal({
                        title: "Success",
                        type: "success",
                        text: response.message
                    },
                    function (isConfirm) {
                        if (isConfirm) {
                            $("#departmentTable").DataTable().draw();
                        }
                    }
                );
            } else {
                swal({
                    title: "Error",
                    type: "error",
                    text: response.message
                });
            }
        }
    });
});