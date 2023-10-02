package crm_project_02.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm_project_02.entity.role;
import crm_project_02.payload.response.BaseResponse;
import crm_project_02.service.RoleService;

@WebServlet(name = "apiRoleController", urlPatterns = {"/api/role", "/api/role/delete"})
public class ApiRoleController extends HttpServlet{
	private RoleService roleService = new RoleService();
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/api/role")) {
			List<role> listRoles = roleService.getAllRoles();
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage("");
			response.setData(listRoles);
			String dataJson = gson.toJson(response);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);
			out.flush();
			
		} else if(path.equals("/api/role/delete")) {
			int id = Integer.parseInt(req.getParameter("id")); 
			 boolean isSuccess = roleService.deleteById(id);
			 BaseResponse response = new BaseResponse();
			 response.setStatusCode(200);
			 response.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
			 response.setData(isSuccess);
			 
			 String json = gson.toJson(response);
			 
			 PrintWriter out = resp.getWriter();
			 resp.setContentType("application/json");
			 resp.setCharacterEncoding("UTF-8");
			 out.print(json);
		} else if(path.equals("api/role/update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			String roleName  = req.getParameter("role-name");
			String desc = req.getParameter("desc");
			
			boolean isSuccess = roleService.updateById(roleName, desc, id);
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Sửa thành công" : "Sửa thất bại");
			response.setData(isSuccess);
			
			String json = gson.toJson(response);
			
			// Trả dữ liệu dạng Gson
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(json);
			out.flush();
		}
	}
	
}
