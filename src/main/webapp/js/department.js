const newRowSupplier = function(){
    return '<td><input type="text" class="form-control" name="deptName" id="deptName"/></td>';
}

const addRowSupplier = function(elem, successHandler, errorHandler){
    let deptId = $(elem).find(".deptId").val();
    deptId = (typeof deptId === 'undefined') ? -1 : deptId;

    let deptName = $('#deptName').val();
    deptName = (typeof deptName === 'undefined') ? $(elem).find("span").find("input").val() : deptName;
    $.ajax({url: "department",
            type: "POST",
            data: {"deptName": deptName, "deptId": deptId},
            success: function(result) {
                $('#notif').addClass('alert-success');
                $('#notif').html("New department saved successfully");
                $('#notif').show().delay(5000).fadeOut();
                successHandler();
                const prevTdHtml = $(elem).find('td').html();
                $(elem).find('td').first().html('<input type = "hidden" value = "'+result.deptId+'" class="deptId"/>' + prevTdHtml);
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
                console.log('deleted successfully');
                successHandler();
                $('#notif').addClass('alert-success');
                $('#notif').html("Department deleted successfully");
                $('#notif').show().delay(5000).fadeOut();
            }
    });
}

const editRowSupplier = function(elem, successHandler){
    var deptName = $(elem).parents("tr").find("td").first().html();
    console.log(deptId);
    $.ajax({url: "department",
            type: "PUT",
            data: {"deptId": deptId},
            success: function(result) {
                console.log('deleted successfully');
                successHandler();
                $('#notif').addClass('alert-success');
                $('#notif').html("Department deleted successfully");
                $('#notif').show().delay(5000).fadeOut();
            }
    });
}