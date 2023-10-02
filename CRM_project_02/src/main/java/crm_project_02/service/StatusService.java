package crm_project_02.service;

import java.util.List;

import crm_project_02.entity.Status;
import crm_project_02.repository.StatusRepository;

public class StatusService {
	private StatusRepository statusRepository = new StatusRepository();
	
	public List<Status> findAll() {
		return statusRepository.findAll();
	}
	public Status findById(int id){
		return statusRepository.findById(id);
	}
	
}
