package crm_project_02.controller;
/* package: 
- controller là nơi chứa toàn bộ file liên quan đến khai báo đường dẫn và xử lý đường dẫn
*/
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.PreparableStatement;

import crm_project_02.config.mysqlconfig;
import crm_project_02.entity.users;

@WebServlet(name="loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet { 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int maxAge = 8*60*60;
		Cookie cookie = new Cookie("hoten", URLEncoder.encode("Nguyễn Văn A", "UTF-8"));
		cookie.setMaxAge(maxAge);
		
		// yêu cầu client tạo cookie 
		resp.addCookie(cookie);

		req.getRequestDispatcher("login.html").forward(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//B1. 
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		//B2.
		String query = "SELECT * FROM Users u \n"
				+ "WHERE u.email= ? AND u.pwd =?"; //?: đại diện cho tham số sẽ được truyền vào khi sử dụng JDBC
		
		//B3.
		// mở kết nối tới db
		Connection conn = mysqlconfig.getConnection();
		try {// chuẩn bị câu query để truyền xuống db thông qua PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);	
			// gán giá trị cho tham số trong câu query (phần ?)
			statement.setString(1, email);
			statement.setString(2, password);
			// thực thi câu query
			//executeUpdate: nếu câu query là câu INSERT, UPDATE, DELETE,...
			//executeQuery: nếu câu query là câu SELECT
			ResultSet resultSet = statement.executeQuery();
			List<users> listUsers = new ArrayList<users>();
			// khi nào resultSet còn qua dòng tiếp theo được thì làm
			while (resultSet.next()) {
				users users = new users();
				// lấy dữ liệu từ cột duyệt được và lưu vào thuộc tính của đối tượng users
				users.setId(resultSet.getInt("id"));
				users.setEmail(resultSet.getString("email"));
				
				listUsers.add(users);
			}
			if(listUsers.size()>0) {
				// user tồn tại = login thành công
				System.out.println("Đăng nhập thành công");
			} else {
				// user không tồn tại = login không thành công
				System.out.println("Đăng nhập thất bại");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lỗi thực thi truy vấn" + e.getLocalizedMessage());
		} finally {
			
		}
		
	}
}
