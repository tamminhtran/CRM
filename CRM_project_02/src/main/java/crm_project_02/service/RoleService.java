package crm_project_02.service;

import java.util.List;

import crm_project_02.entity.role;
import crm_project_02.repository.RoleRepository;

/**
 * service: chứa những class chuyên xử lý logic cho controller. Đặt tên class giống như tên class controller
 * VD: RoleSerice < => RoleController
 * 
 * Đặt tên hàm ứng với chức năng sẽ làm trên giao diện hoặc chức năng của controller
 * hàm có tham số hay không => check với doPost ở Controller
 */
public class RoleService {
	private RoleRepository roleRepository = new RoleRepository();
	
	public boolean addRole(String name, String desc){
		int count = roleRepository.insert(name, desc);
		return count>0;
	}
	public List<role> getAllRoles(){
		return roleRepository.getAllRole();
	}
	public boolean deleteById( int id) {
		int count = roleRepository.deleteById(id);
		return count>0;
	}
	public role findById(int id) {
		return roleRepository.findById(id);
	}
	public boolean updateById (String name, String description, int id) {
		int count = roleRepository.updateById(name, description, id);
		return count >0;
	}
	
}
