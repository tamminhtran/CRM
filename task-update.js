$(document).ready(function(){
	$(".btn-xoa").click (function() {
		var id = $(this).attr('id-user')
		var This = $(this)
	$.ajax({
	  method: "GET",
	  url: `http://localhost:8080/CRM_project_02/api/user/delete?id=${id}`,
	})
	  .done(function( msg ) {
		  if(msg.data==true) {
			 This.closest('tr').remove() 
		  }
		  console.log(msg.data) 
	  });
	});
 $(".btn-sua").click(function () {
        var id = $(this).attr('id-user');
        // Add your AJAX request for update here
        // Example:
        $.ajax({
            method: "GET",
            url: `http://localhost:8080/CRM_project_02/api/user/update?id=${id}`,
        })
            .done(function (msg) {
                // Handle the response for the update
                console.log(msg);
                // You can update the UI or perform other actions as needed
            });
    });
});