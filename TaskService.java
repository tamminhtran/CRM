package crm_project_02.service;

import java.lang.invoke.StringConcatFactory;
import java.util.List;

import crm_project_02.entity.Task;
import crm_project_02.repository.TaskRepository;

public class TaskService {
	private TaskRepository taskRepository = new TaskRepository();
	
	public boolean addTask (String name, int idProject, int idUser, String startDate, String endDate, int idStatus) {
		int count = taskRepository.insert(name, idProject,idUser,startDate,endDate,idStatus);
		return count>0;
	}
	public List<Task> getAllTask(){
		return taskRepository.getAllTask();
	}
	public boolean updateById(String name, int idProject, int idUser,String startDate, String endDate, int idStatus, int id) {
		int count = taskRepository.updateById(name,startDate,endDate, idProject, idUser, idStatus,id);
		return count>0;
	}
	public boolean deleteById(int id) {
		int count = taskRepository.deleteById(id);
		return count>0;
	}

}
