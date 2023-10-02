$(document).ready(function(){
	$(".btn-update").click(function(){
		var id = $(this).attr('id-task')
		var project_name = $('#project').val()
		var name = $('#name').val()
		var user_name = $('#user').val()
		var startDate  = $("#startDate").val()
		var endDate  = $("#endDate").val()
		var status_name = $('#status').val()

		$.ajax({
			  method: "POST",
			  url: `http://localhost:8080/crm_project_02/api/task/update`, //string literals
			  data: { id:id , project_name:project_name, name:name, user_name:user_name, startDate:startDate, endDate:endDate, status_name:status_name } //Tham số data chỉ dành cho phương thức POST
			})
			  .done(function( result ) {
				  if(result.data==true){
					  alert("Cập nhật thành công")
				  }else{
					  alert("Cập nhật thất bại")
				  }
				  window.location.href = 'http://localhost:8082/crm_project_02/task-table'
			});
		
	})
	
	
	
	
	
})
