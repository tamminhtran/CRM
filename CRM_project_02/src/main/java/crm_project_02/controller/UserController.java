package crm_project_02.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Query;

import crm_project_02.config.mysqlconfig;
import crm_project_02.entity.role;
import crm_project_02.entity.users;
import crm_project_02.service.RoleService;
import crm_project_02.service.UserService;

@WebServlet(name="userController", urlPatterns = {"/user-add", "/user-table", "/user-update"})
public class UserController extends HttpServlet {
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		List<users> listUser = new ArrayList<users>();
		listUser= userService.getAllUsers();
		List<role> list = new ArrayList<role>();
		try {
			list = roleService.getAllRoles();
		} catch (Exception e) {
			System.out.println("Lỗi get all role" + e.getLocalizedMessage());
		}
		switch (path) {
		case "/user-table":
			
			req.setAttribute("listUser", listUser);
			req.getRequestDispatcher("user-table.jsp").forward(req,resp);
			break;
			
		case "/user-add":
			
			req.setAttribute("listRole", list);
			req.getRequestDispatcher("user-add.jsp").forward(req,resp);
			break;
		case "/user-update":
			int userId = Integer.parseInt(req.getParameter("userId"));
			users users = userService.getUserById(userId);
			req.setAttribute("users", users);
			req.setAttribute("listRole", list);
			req.getRequestDispatcher("user-update.jsp").forward(req, resp);
			break;
		default:
			break;
			
		}
//		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch(path) {
		case "/user-add": {
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			String fullname = req.getParameter("fullname");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String phone = req.getParameter("phone");
			String email = req.getParameter("email");
			int idRole = Integer.parseInt(req.getParameter("role"));
			
			boolean isSuccess = userService.addUser(firstname, lastname, fullname, username, email, email, phone, idRole);
			
			List<role> list = new ArrayList<role>();
			list = roleService.getAllRoles();		
			req.setAttribute("listRole", list);
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			break;
		}
		case "/user-update": {
			int id = Integer.parseInt(req.getParameter("id"));
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			String fullname = req.getParameter("fullname");
			String username = req.getParameter("username");
			String email = req.getParameter("email");
			String pwd = req.getParameter("password");
			String phone = req.getParameter("phone");
			int idRole = Integer.parseInt(req.getParameter("role"));
			
			boolean isSuccess = userService.updateById(firstname, lastname, fullname, username, email, pwd, phone, idRole, id);
			List<role> list = new ArrayList<role>();
			list = roleService.getAllRoles();		
			
			req.setAttribute("listRole", list);
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("user-update.jsp").forward(req, resp);
			break;
		}
		}
	}
}
		
		// lấy tham số từ thẻ form truyền qua khi người dùng click button submit
//		String fullname = req.getParameter("fullname");
//		String password = req.getParameter("password");
//		String phone = req.getParameter("phone");
//		String email = req.getParameter("email");
//		int idRole = Integer.parseInt(req.getParameter("role"));
//		
//		boolean isSuccess = userService.addUser(fullname,password,phone, email,idRole);
//		if(isSuccess) {
//			System.out.println("Thành công");
//		} else {
//			System.out.println("Thêm thất bại");
//		}
//		List<role> list = new ArrayList<role>();
//		list = roleService.getAllRoles();
//		req.setAttribute("listrole", list);
//		req.getRequestDispatcher("user-add.jsp").forward(req,resp);
//		}

//		// tạo ra câu truy vấn
//		String query = "INSERT INTO Users(fullname, email, pwd, phone,id_role)\n"
//				+ "VALUES (?, ?,?,?,?)";
//		// mở kết nối tới db
//		Connection connection = mysqlconfig.getConnection();
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setString(1, fullname);
//			statement.setString(2, email);
//			statement.setString(3, password);
//			statement.setString(4, phone);
//			statement.setInt(5, idRole);
			
//			int count = statement.executeUpdate();
//			if(count>0) {
//				// insert thành công
//				System.out.println("Thêm thành công");
//			} else {
//				// insert thất bại
//				System.out.println("Thêm thất bại");
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("Lỗi thêm dữ liệu user" + e.getLocalizedMessage());
//		} finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				System.out.println("Lỗi đóng kết nối" + e.getLocalizedMessage());
//			}
//		}
//		// get all role cho giao diện
//		List<role> list = new ArrayList<role>();
//		try {
//			list = UserRegetAllRole();
//		} catch (Exception e) {
//			System.out.println("Lỗi get all role" + e.getLocalizedMessage());
//		}
//		req.setAttribute("listrole", list);
//		req.getRequestDispatcher("user-add.jsp").forward(req,resp);
//	}
//	private List<role> getAllRole() throws Exception {
//		String query = "SELECT * FROM `Role`";
//		// mở kết nối db
//		Connection connection = mysqlconfig.getConnection();
//		// truyền câu qyery vào connection
//		PreparedStatement statement = connection.prepareStatement(query);
//		// thực thi câu query
//		ResultSet resultSet = statement.executeQuery();
//		List<role> listrole = new ArrayList<role>();
//		while(resultSet.next()) {
//			role role = new role();
//			role.setId(resultSet.getInt("id"));
//			role.setName(resultSet.getString("name"));
//			role.setDescription(resultSet.getString("description"));
//			
//			listrole.add(role);
//		}
//		return listrole;
	
