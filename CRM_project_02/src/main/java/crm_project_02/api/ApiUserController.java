package crm_project_02.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.io.PrintWriter;
import crm_project_02.entity.users;
import crm_project_02.payload.response.BaseResponse;
import crm_project_02.service.UserService;

@WebServlet(name = "apiUserController", urlPatterns = {"/api/user","/api/user/delete","/api/user/update"})
public class ApiUserController extends HttpServlet{
	private UserService userService = new UserService();
	private Gson gson = new Gson();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/api/user")) {
			List<users> listUser = userService.getAllUsers();
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage("");
			response.setData(listUser);
			String dataJson = gson.toJson(response);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);
			out.flush();
			
//			String dataJson = gson.toJson(listUser);
//			PrintWriter out = resp.getWriter();
//			resp.setContentType("application/json");
//			resp.setCharacterEncoding("UTF-8");
//			out.print(dataJson);
//			out.flush();
		} else if(path.equals("/api/user/delete")) {
			int id = Integer.parseInt(req.getParameter("id")); 
			 boolean isSuccess = userService.deleteById(id);
			 
			 BaseResponse response = new BaseResponse();
			 response.setStatusCode(200);
			 response.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
			 response.setData(isSuccess);
			 
			 String json = gson.toJson(response);
			 
			 PrintWriter out = resp.getWriter();
			 resp.setContentType("application/json");
			 resp.setCharacterEncoding("UTF-8");
			 out.print(json);
			 out.flush();				
		}
		else if(path.equals("/api/user/update")) {
			int id = Integer.parseInt(req.getParameter("id")); 
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
		    String firstname = req.getParameter("firstname");
		    String lastname = req.getParameter("lastname");
		    String userName = req.getParameter("userName");
		    String password = req.getParameter("password");
		    String phone = req.getParameter("phone");
		    int idRole = Integer.parseInt(req.getParameter("idRole"));

		    boolean isSuccess = userService.updateById(fullname, email, firstname, lastname, userName, password, phone, idRole, id);
		    BaseResponse response = new BaseResponse();
		    response.setStatusCode(200);
		    response.setMessage(isSuccess ? "Cập nhật thành công" : "Cập nhật thất bại");
		    response.setData(isSuccess);

		    String json = gson.toJson(response);

		    PrintWriter out = resp.getWriter();
		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    out.print(json);
		    out.flush();
		}
	}
}
/* payload:gồm 2 
 * - response: chứa các class liên quan tới format json trả ra cho client
 * - request: chứa các class liên quan tới format json từ request do client truyền lên server
 * 
 * 
 * */

