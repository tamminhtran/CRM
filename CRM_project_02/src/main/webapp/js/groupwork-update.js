$(document).ready(function(){
	$(".btn-update").click(function(){
		var id = $(this).attr('id-groupwork')
		var name  = $("#groupwork-name").val()
		var startDate  = $("#startDate").val()
		var endDate  = $("#endDate").val()
		
		$.ajax({
			  method: "POST",
			  url: `http://localhost:8080/crm_project_02/api/groupwork/update`, //string literals
			  data: { id:id , name:name, startDate:startDate, endDate:endDate } 
			})
			  .done(function( result ) {
				  if(result.data==true){
					  alert("Cập nhật thành công")
				  }else{
					  alert("Cập nhật thất bại")
				  }
				  window.location.href = 'http://localhost:8082/crm_project_02/groupwork-table'
			});
		
	})
	
	
	
	
	
})