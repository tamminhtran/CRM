package crm_project_02.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import crm_project_02.payload.response.BaseResponse;
import com.google.gson.Gson;

import crm_project_02.entity.Groupwork;
import crm_project_02.service.GroupWorkService;

@WebServlet(name = "apiGroupworkController", urlPatterns = {"/api/groupwork", "/api/groupwork/delete", "/api/groupwork/update"})
public class ApiGroupWorkController extends HttpServlet {
	private GroupWorkService groupWorkService = new GroupWorkService();
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/api/groupwork")) {
			List<Groupwork> listGroupwork = groupWorkService.getAllGroupworks();
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage("");
			response.setData(listGroupwork);
			
			// Chuyển list hoặc mảng về Json
			String dataJson = gson.toJson(response);
			
			// Trả dữ liệu dạng Gson
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);	
			out.flush();
		} else if(path.equals("/api/groupwork/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = groupWorkService.deleteById(id);
			
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
		} else if(path.equals("/api/groupwork/update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			String name  = req.getParameter("groupwork-name");
			String startDate = req.getParameter("startDate");
			String endDate = req.getParameter("endDate");
			
			boolean isSuccess = groupWorkService.updateById(id, name, startDate, endDate);
			
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage(isSuccess ? "Sửa thành công" : "Sửa thất bại");
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
