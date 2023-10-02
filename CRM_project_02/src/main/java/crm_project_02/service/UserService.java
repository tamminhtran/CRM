package crm_project_02.service;

import java.util.ArrayList;
import java.util.List;

import crm_project_02.entity.role;
import crm_project_02.entity.users;
import crm_project_02.repository.RoleRepository;
import crm_project_02.repository.UserRepository;

public class UserService {
	private UserRepository userRepository = new UserRepository();
	private RoleRepository roleRepository = new RoleRepository();
	
	public boolean addUser(String firstname, String lastname, String fullname, String username, String email, String pwd, String phone, int idRole) {
		int count = userRepository.insert(firstname, lastname, fullname, username, email, pwd, phone, idRole);
		return count>0;
	}
	public List<users> getAllUsers(){
		return userRepository.getAllUsers();
	}
	public boolean deleteById(int id) {
		int count = userRepository.deleteById(id);
		return count>0;
	}
	public boolean updateById( String firstname, String lastname,String fullname, String userName, String email, String password, String phone, int idRole, int id) {
		int count = userRepository.updateById(fullname, email, firstname, lastname, userName, password, phone, idRole, id);
		return count>0;
	}
	public users getUserById(int id) {
			return userRepository.getUserById(id);
	}
}
