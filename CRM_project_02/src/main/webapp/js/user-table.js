$(document).ready(function(){
	// đăng ký sự kiện click: $("tên_thẻ || tên_class || id").click()
	// class kí hiệu .; id kí hiệu #
	$(".btn-xoa").click (function() {
		var id = $(this).attr('id-user')         // let và var
		// lấy giá trị của thuộc tính (attr) bên thẻ có class là .btn-xoa
		//$(this) // đại diện cho function đang thực thi
		var This = $(this)
		alert("Xóa")
	$.ajax({
	  method: "GET",
	  url: `http://localhost:8080/CRM_project_02/api/user/delete?id=${id}`, // string literals
	  // data: { name: "John", location: "Boston" } tham số data chỉ dành cho phương thức POST
	})
	  .done(function( msg ) {
		  if(msg.data==true) {
			 // cách 1: This.parent().parent().remove() //.parent là đi ra thẻ cha
			 This.closest('tr').remove(); // đi ra thẻ cha chỉ định
		  }
		  console.log(msg.data); // lấy giá trị kiểu object trong js thì dùng cú pháp tên_biến.key
	  });
		
		
		//alert("Đã click em" + id)
		
		
	}); // đăng kí sự kiện click cho toàn bộ class có tên là btn-xoa
	
	
}); // cú pháp để khởi chạy javascript. Ý nghĩa: khi nào file html được load thì thực hiện function nào đó
//RestTemplate 
//HttpClient
	
	//Đăng ký sự kiện click: $("tên_thẻ || tên_class || id").click()
	//class => .
	//id => #
	
	//Đăng ký sự kiện click cho toàn bộ class có tên là btn-xoa
	

	