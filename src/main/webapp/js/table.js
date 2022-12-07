$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
	var actions = $("table td:last-child").html();

	// Append table with add row form on add new button click
    $(".add-new").on('click', { newRowHtmlCallback : newRowSupplier }, function(e){
		$(this).attr("disabled", "disabled");
		var index = $("table tbody tr:last-child").index();
		var rowHtml = e.data.newRowHtmlCallback();
        var row = '<tr>' + rowHtml + '<td>' + actions + '</td> </tr>';
    	$("table").append(row);
		$("table tbody tr").eq(index + 1).find(".add, .edit").toggle();
        $('[data-toggle="tooltip"]').tooltip();
    });

	// Add row on add button click
	$(document).on("click", ".add", { addRowCallback : addRowSupplier }, function(e){
	    console.log("add clicked");
		var empty = false;
		const thisRow = $(this).parents("tr");
		var input = $(thisRow).find('input[type="text"]');
        input.each(function(){
			if(!$(this).val()){
				$(this).addClass("error");
				empty = true;
			} else{
                $(this).removeClass("error");
            }
		});
		$(thisRow).find(".error").first().focus();
		if(!empty){
		    e.data.addRowCallback(thisRow,
                function(){
                    input.each(function(){
                        $(this).parent("td").html('<span>' + $(this).val() + '</span>');
                    });
                    $(thisRow).find(".add, .edit").toggle();
                    $(".add-new").removeAttr("disabled");
                },
                function(){
                    $(this).addClass("error");
                    empty = true;
                }
			);
		}
    });

	// Edit row on edit button click
	$(document).on("click", ".edit", function(){
        $(this).parents("tr").find("td:not(:last-child)").find("span").each(function(){
			$(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
		});
		$(this).parents("tr").find(".add, .edit").toggle();
		$(".add-new").attr("disabled", "disabled");
    });

	// Delete row on delete button click
	$(document).on("click", ".delete", { deleteRowCallback : deleteRowSupplier }, function(e){
	    const thisRow = $(this).parents("tr");
	    e.data.deleteRowCallback($(this), function(){
	        $(thisRow).remove();
            $(".add-new").removeAttr("disabled");
	    });

    });
});