package crm_project_02.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project_02.entity.Groupwork;
import crm_project_02.repository.GroupWorkReposity;
import crm_project_02.service.GroupWorkService;


import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "GroupWorkController", urlPatterns = {"/groupwork-add", "/groupwork-table", "/groupwork-update"})
public class GroupWorkController extends HttpServlet {
	private GroupWorkService groupWorkService = new GroupWorkService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/groupwork-add")) {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		} else if(path.equals("/groupwork-table")) {
			List<Groupwork> list = new ArrayList<Groupwork>();
			list = groupWorkService.getAllGroupworks();
			req.setAttribute("listgroupwork", list);
			req.getRequestDispatcher("groupwork-table.jsp").forward(req, resp);
		} else if(path.equals("/groupwork-update")) {
			Groupwork groupwork = groupWorkService.findById(Integer.parseInt(req.getParameter("groupworkId")));
			req.setAttribute("groupwork", groupwork);
			req.getRequestDispatcher("/groupwork-update.jsp").forward(req, resp);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();
		if (path.equals("/groupwork-add")) {
			String name = req.getParameter("name");
	        String startDate= req.getParameter("startDate");
	        String endDate = req.getParameter("endDate");
	        
	        boolean isSuccess = groupWorkService.addGroupWork(name, startDate, endDate);
	        
	        req.setAttribute("isSuccess", isSuccess);
	        req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
	        
		} else if(path.equals("/groupwork-update")) {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("groupwork-name");
			String startDate = req.getParameter("startDate");
			String endDate = req.getParameter("endDate");
			boolean isSuccess = groupWorkService.updateById(id, name, startDate, endDate); 
			req.setAttribute("isSuccess", isSuccess);
			
			req.getRequestDispatcher("groupwork-update.jsp").forward(req, resp);
		}
		
	}
}
