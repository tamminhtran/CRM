package crm_project_02.service;
import java.util.List;
import crm_project_02.entity.Groupwork;
import crm_project_02.repository.GroupWorkReposity;

public class GroupWorkService {
	private GroupWorkReposity groupWorkReposity = new GroupWorkReposity();
	
	public boolean addGroupWork(String name, String startDate, String endDate) {
		int count = groupWorkReposity.insert(name, startDate, endDate);
		return count>0;
	}
	public List<Groupwork> getAllGroupworks() {
		return groupWorkReposity.findAll();
	}
	public boolean deleteById(int id) {
		int count = groupWorkReposity.deleteById(id);
		return count >0;
	}
	public Groupwork findById(int id) {
		return groupWorkReposity.findById(id);
	}
	public boolean updateById(int id, String name, String startDate, String endDate) {
		int count = groupWorkReposity.updateById(id, name, startDate, endDate);
		return count >0;
	}
}
