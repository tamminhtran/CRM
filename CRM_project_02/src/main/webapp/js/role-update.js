$(document).ready(function(){
	$(".btn-update").click(function(){
		var id = $(this).data('id-role')
		var name  = $("#name").val()
		var description  = $("#description").val()
		$.ajax({
			  method: "POST",
			  url: `http://localhost:8080/crm_project_02/api/role/edit`,
			  data: { id:id , name:name, description:description }
			})
			  .done(function( result ) {
				  if(result.data==true){
					  alert("Cập nhật thành công")
				  }else{
					  alert("Cập nhật thất bại")
				  }
				  window.location.href = 'http://localhost:8082/crm_project_02/role-table'
			});
		
	})
	
	
	
	
	
})