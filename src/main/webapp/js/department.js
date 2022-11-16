const newRowSupplier = function(){
    return '<td><input type="text" class="form-control" name="deptName" id="deptName"/></td>';
}

const addRowSupplier = function(successHandler, errorHandler){
    $.ajax({url: "department",
            type: "POST",
            data: {"deptName": $('#deptName').val()},
            success: function(result) {
                $('#notif').addClass('alert-success');
                $('#notif').html("New department saved successfully");
                $('#notif').show().delay(5000).fadeOut();
                successHandler();
            },
            error: function(error) {
                $('#notif').addClass('alert-danger');
                $('#notif').html("Operation failure check server logs");
                $('#notif').show().delay(5000).fadeOut();
                errorHandler();
            }
    });
}

const deleteRowSupplier = function(elem, successHandler){
    var deptId = $(elem).parents("tr").find(".deptId").val();
    console.log(deptId);
    $.ajax({url: "department",
            type: "DELETE",
            data: {"deptId": deptId},
            success: function(result) {
                console.log('deleted successfull');
                successHandler();
                $('#notif').addClass('alert-success');
                $('#notif').html("Department deleted successfully");
                $('#notif').show().delay(5000).fadeOut();
            }
    });
}