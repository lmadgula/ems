const newRowSupplier = function(){
    const newRow = '<td><input type="text" class="form-control" name="fName" id="fName"></td>' +
                   '<td><input type="text" class="form-control" name="lName" id="lName"></td>' +
                   '<td><input type="date" class="form-control" name="dob" id="dob"></td>' +
                   '<td><input type="email" class="form-control" name="email" id="email"></td>' +
                   '<td><select name="department" id="department"><option value="1">HR</option><option value="2">Admin</option></select></td>';
    return newRow;
}