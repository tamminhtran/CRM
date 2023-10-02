$(document).ready(function(){
	
	//Đăng ký sự kiện click: $("tên_thẻ || tên_class || id").click()
	//class => .
	//id => #
	
	//Đăng ký sự kiện click cho toàn bộ class có tên là btn-xoa
	$(".btn-edit").click(function(){
		//Lấy giá trị của thuộc tính bên thẻ có class là .btn-xoa
		//$(this): đại diện cho function đang thực thi
		var id = $(this).attr('id-user')
		var firstname  = $("#name").val()
		var lastname  = $("#lastname").val()
		var fullname  = $("#fullname").val()
		var username  = $("#username").val()
		var email  = $("#email").val()
		var password  = $("#password").val()
		var phone  = $("#phone").val()
		var idrole  = $("#role").val()
		
		
		//RestTemplate, HttpClient
		$.ajax({
			  method: "POST",
			  url: `http://localhost:8080/crm_project_02/api/user/edit`, //string literals
			  data: { id:id , firstname:firstname, lastname:lastname, fullname:fullname, username:username, email:email, password:password, phone:phone, idrole:idrole } //Tham số data chỉ dành cho phương thức POST
			})
			  .done(function( result ) {
				  if(result.data==true){
					  alert("Cập nhật thành công")
				  }else{
					  alert("Cập nhật thất bại")
				  }
				  window.location.href = 'http://localhost:8082/crm_project_02/user-table'
			});
		
	})
	
	
	
	
	
})