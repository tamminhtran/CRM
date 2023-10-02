package crm_project_02.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project_02.entity.Groupwork;
import crm_project_02.entity.Status;
import crm_project_02.entity.Task;
import crm_project_02.entity.users;
import crm_project_02.service.GroupWorkService;
import crm_project_02.service.StatusService;
import crm_project_02.service.TaskService;
import crm_project_02.service.UserService;

@WebServlet(name="taskController", urlPatterns = {"/task-table", "/task-add", "/task-update"})
public class TaskController extends HttpServlet{
	private TaskService taskService = new TaskService();
	private UserService userService = new UserService();
	private GroupWorkService groupWorkService = new GroupWorkService();
	private StatusService statusService = new StatusService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		switch (path) {
			case "/task-add": {
				List<users> listUser = new ArrayList<users>();
				listUser = userService.getAllUsers();
				List<Groupwork> listProject = new ArrayList<Groupwork>();
				listProject = groupWorkService.getAllGroupworks();
				req.setAttribute("listUser", listUser);
				req.setAttribute("listProject", listProject);
				req.getRequestDispatcher("task-add.jsp").forward(req, resp);
				break;
			}
			case "/task-table": {
				List<Task> listTask = new ArrayList<Task>();
				listTask = taskService.getAllTask();
				req.setAttribute("listTask", listTask);
				req.getRequestDispatcher("task-table.jsp").forward(req, resp);
				break;
			}
			case "/task-update": {
				List<users> listUser = userService.getAllUsers();
				req.setAttribute("listUser", listUser);
				List<Groupwork> listProject = groupWorkService.getAllGroupworks();
				req.setAttribute("listProject", listProject);
				List<Status> listStatus = statusService.findAll();
				req.setAttribute("listStatus", listStatus);
				Task task = taskService.findById(Integer.parseInt(req.getParameter("taskId")));
				req.setAttribute("task", task);
				req.getRequestDispatcher("task-update.jsp").forward(req, resp);
				break;
			}
				
			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		switch (path) {
			case "/task-add": {
				int idProject = Integer.parseInt(req.getParameter("project"));
				String name = req.getParameter("name");
				int idUser = Integer.parseInt(req.getParameter("user"));
				String startDate = req.getParameter("startDate");
				String endDate = req.getParameter("endDate");
				
				boolean isSuccess = taskService.addTask(name, idProject, idUser, startDate, endDate);
				List<users> listUser = new ArrayList<users>();
				listUser = userService.getAllUsers();
				List<Groupwork> listProject = new ArrayList<Groupwork>();
				listProject = groupWorkService.getAllGroupworks();
				
				req.setAttribute("listUser", listUser);	
				req.setAttribute("listProject", listProject);
				req.setAttribute("isSuccess", isSuccess);
				req.getRequestDispatcher("task-add.jsp").forward(req, resp);
				break;
			}
			case "/task-update":{
				int id = Integer.parseInt(req.getParameter("id"));
				int idProject = Integer.parseInt(req.getParameter("project"));
				String name = req.getParameter("name");
				int idUser = Integer.parseInt(req.getParameter("user"));
				String startDate = req.getParameter("startDate");
				String endDate = req.getParameter("endDate");
				int idStatus = Integer.parseInt(req.getParameter("status"));
				
				boolean isSuccess = taskService.updateById(name, idProject, idUser,startDate, endDate,  idStatus, id);
				List<users> listUser = new ArrayList<users>();
				listUser = userService.getAllUsers();
				List<Groupwork> listProject = new ArrayList<Groupwork>();
				listProject = groupWorkService.getAllGroupworks();
				List<Status> listStatus = new ArrayList<Status>();
				listStatus = statusService.findAll();
				
				req.setAttribute("listStatus", listStatus);
				req.setAttribute("listUser", listUser);	
				req.setAttribute("listProject", listProject);
				req.setAttribute("isSuccess", isSuccess);
				req.getRequestDispatcher("task-update.jsp").forward(req, resp);
				break;
			}
		
		}
		
		
	}
}
