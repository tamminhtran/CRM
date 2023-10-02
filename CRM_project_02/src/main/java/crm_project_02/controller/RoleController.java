package crm_project_02.controller;

import java.io.IOException;
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

import org.apache.taglibs.standard.extra.spath.AbsolutePath;

import crm_project_02.config.mysqlconfig;
import crm_project_02.entity.role;
import crm_project_02.service.RoleService;

@WebServlet(name="roleController", urlPatterns = {"/role-add", "/role-table", "/role-update"})
public class RoleController extends HttpServlet {
	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		List<role> list = new ArrayList<role>();
		switch (path) {
		case "/role-table":
			try {
				list = roleService.getAllRoles();
			} catch (Exception e) {
				System.out.println("Lỗi truy xuất role "+ e.getLocalizedMessage());
			}
			req.setAttribute("list", list);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			break;
		case "/role-add":
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		case "/role-update":
			role role = roleService.findById(Integer.parseInt(req.getParameter("roleId")));
			req.setAttribute("role", role);
			req.getRequestDispatcher("role-update.jsp").forward(req, resp);
			break;
		default:
			break;
		}	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch(path) {
		case "/role-add": {
			String roleName = req.getParameter("role-name");
			String desc = req.getParameter("desc");
			boolean isSuccess = roleService.addRole(roleName, desc);
				
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			break;
		}
		case "/role-update":{
			int id = Integer.parseInt(req.getParameter("id"));
			String roleName = req.getParameter("role-name");
			String desc = req.getParameter("desc");
			
			boolean isSuccess = roleService.updateById(roleName, desc, id);
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("role-update.jsp").forward(req, resp);
			break;
		}
		}
		
		
		
		
		
		
		
		
//		String roleName = req.getParameter("role-name");
//		String desc = req.getParameter("desc");
//		
//		String query ="INSERT INTO `Role`(name, description) VALUES (?, ?);";
//		Connection connection = mysqlconfig.getConnection();
//		boolean isSuccess = false;
//		try {
//			PreparedStatement statement = connection.prepareStatement(query);
//			statement.setString(1, roleName);
//			statement.setNString(2, desc);
//			
//			int count = statement.executeUpdate();
//			if(count>0) {
//				isSuccess = true;
//				System.out.println("Thêm thành công");
//			} else {
//				System.out.println("Thêm thất bại");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(connection != null) {
//					connection.close();
//				}
//			} catch (Exception e2) {
//				// TODO Auto-generated catch block
//				System.out.println("Lỗi đóng kết nối" + e2.getLocalizedMessage());
//		}
//		req.setAttribute("isSuccess", isSuccess);
//		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
//	}
	}
}
