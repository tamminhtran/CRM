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

import crm_project_02.entity.Task;
import crm_project_02.payload.response.BaseResponse;
import crm_project_02.service.TaskService;

@WebServlet(name="apiTaskController", urlPatterns = {"/api/task","/api/task/update", "/api/task/delete"})
public class ApiTaskController extends HttpServlet {
	private TaskService taskService = new TaskService();
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/api/task")){
			List<Task> listTasks = taskService.getAllTask();
			BaseResponse response = new BaseResponse();
			response.setStatusCode(200);
			response.setMessage("");
			response.setData(listTasks);
			String dataJson = gson.toJson(listTasks);
			
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			out.print(dataJson);
			out.flush();
		}
		else if(path.equals("/api/task/update")) {			
			
			String name = req.getParameter("name");
		    int idProject = Integer.parseInt(req.getParameter("idProject"));
		    int idUser = Integer.parseInt(req.getParameter("idUser"));
		    String startDate = req.getParameter("startDate");
		    String endDate = req.getParameter("endDate");
		    int idStatus = Integer.parseInt(req.getParameter("idStatus"));
		    int id = Integer.parseInt(req.getParameter("id")); 
		    

		    boolean isSuccess = taskService.updateById(name, idProject, idUser, startDate, endDate, idStatus, id);
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
		} else if(path.equals("/api/task/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = taskService.deleteById(id);
			
			BaseResponse response = new BaseResponse();
			 response.setStatusCode(200);
			 response.setMessage(isSuccess ? "Xóa thành công" : "Xóa thất bại");
			 response.setData(isSuccess);
			 
			 String json = gson.toJson(response);
			 
			 PrintWriter out = resp.getWriter();
			 resp.setContentType("application/json");
			 resp.setCharacterEncoding("UTF-8");
			 out.print(json);
		}
	}
	
}
