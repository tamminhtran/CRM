$(document).ready(function(){
	$(".btn-xoa").click (function() {
		var id = $(this).attr('id-task')
		var This = $(this)
	$.ajax({
	  method: "GET",
	  url: `http://localhost:8080/CRM_project_02/api/task/delete?id=${id}`,
	})
	  .done(function( msg ) {
		  if(msg.data==true) {
			 This.closest('tr').remove() 
		  }
		  console.log(msg.data) 
	  });
	});
 $(".btn-sua").click(function () {
        var id = $(this).attr('id-task');
        // Add your AJAX request for update here
        // Example:
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/CRM_project_02/api/task/update?id=${id}`,
        })
            .done(function (msg) {
                window.location.href = `task-update.jsp?id=${id}`;
                console.log(msg);
                // You can update the UI or perform other actions as needed
            });
    });
});