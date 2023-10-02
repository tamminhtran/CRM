$(document).ready(function(){
	
	//Đăng ký sự kiện click: $("tên_thẻ || tên_class || id").click()
	//class => .
	//id => #
	
	//Đăng ký sự kiện click cho toàn bộ class có tên là btn-xoa
	$(".btn-update").click(function(){
		$(".btn-update").click(function(e){
		e.preventDefault(); // Prevent the default form submission
		
		// Get the user ID from the "id-user" attribute
		var id = $(this).data('id-user');
		
		// Collect form data
		var firstname = $("#firstname").val();
		var lastname = $("#lastname").val();
		var fullname = $("#fullname").val();
		var username = $("#username").val();
		var email = $("#email").val();
		var password = $("#password").val();
		var phone = $("#phone").val();
		var idRole = $("#role").val();
		
		// Send an AJAX POST request to update the user
		$.ajax({
			method: "POST",
			url: `http://localhost:8080/crm_project_02/api/user/update`,
			data: {
				id: id,
				firstname: firstname,
				lastname: lastname,
				fullname: fullname,
				username: username,
				email: email,
				password: password,
				phone: phone,
				idRole: idRole
			},
			success: function(result) {
				if (result.data === true) {
					alert("Cập nhật thành công");
				} else {
					alert("Cập nhật thất bại");
				}
				window.location.href = 'http://localhost:8082/crm_project_02/user-table';
			},
			error: function(xhr, textStatus, errorThrown) {
				console.error('Error:', errorThrown);
				alert('Cập nhật thất bại');
			}
		});
	});
});
})