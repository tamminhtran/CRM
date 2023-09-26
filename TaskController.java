package crm_project_02.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project_02.entity.Task;
import crm_project_02.service.TaskService;
import crm_project_02.service.UserService;

@WebServlet(name="taskController", urlPatterns = {"/task"})
public class TaskController extends HttpServlet{
	private TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Task> listTask = new ArrayList<Task>();
		listTask = taskService.getAllTask();
		req.setAttribute("listTask", listTask);
		req.getRequestDispatcher("task-table.jsp").forward(req,resp);
		
	}
}
