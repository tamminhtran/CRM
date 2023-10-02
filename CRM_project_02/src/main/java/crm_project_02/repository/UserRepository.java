package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;

import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

import crm_project_02.config.mysqlconfig;
import crm_project_02.entity.role;
import crm_project_02.entity.users;
import crm_project_02.service.UserService;

public class UserRepository {
	public int insert(String firstname, String lastname, String fullname, String username, String email, String pwd, String phone, int idRole) {
		String query = "INSERT INTO Users(firstname, lastname, fullname,username, email, pwd, phone,id_role)\n"
				+ "VALUES (?, ?,?,?,?,?,?,?)";
		 Connection connection = mysqlconfig.getConnection();
		 int count = 0;
		 try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, firstname);
			statement.setString(2, lastname);
			statement.setString(3, fullname);
			statement.setString(4, username);
			statement.setString(5, email);
			statement.setString(6, pwd);
			statement.setString(7, phone);
			statement.setInt(8, idRole);
			
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi thêm user" + e.getLocalizedMessage());
		}
		 return count;
	}
	public List<users> getAllUsers() {
		List<users> list = new ArrayList<users>();
		String query = "SELECT u.id, u.firstName, u.lastName, u.userName, r.name  FROM Users u join Role r on u.id_role = r.id";
		Connection connection = mysqlconfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				users users = new users();
				users.setId(resultSet.getInt("id"));
				users.setFirstName(resultSet.getString("firstName"));
				users.setLastName(resultSet.getString("lastName"));
				users.setUserName(resultSet.getString("userName"));
				role role = new role();
				role.setName(resultSet.getString("name"));
				users.setRole(role);
				list.add(users);
			}
			
		} catch (Exception e) {
			System.out.println("Lỗi lấy danh sách user "+ e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("Lỗi đóng kết nối cơ sở dữ liệu "+ e2.getLocalizedMessage());
			}
		}
		return list;
	} 
	public int deleteById(int id) {
		String query = "DELETE FROM Users u WHERE u.id = ?";
		Connection connection = mysqlconfig.getConnection();
		int count = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			count = statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("Lỗi kết nối cơ sở dữ liệu " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("Lỗi đóng cơ sở dữ liệu "+ e2.getLocalizedMessage());
			}
		} return count;
	}
	public int updateById(String fullname, String email, String firstname, String lastname, String userName, String password, String phone, int idRole, int id) {
		String query = "UPDATE Users  SET email = ?, pwd = ?, firstName = ?, lastName = ?, fullName = ?, userName = ?, phone = ?, id_role = ?\n"
				+ "WHERE id = ?";
		 Connection connection = mysqlconfig.getConnection();
		 int count = 0;
		 try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, fullname);
			statement.setString(2, email);
			statement.setString(3, firstname);
			statement.setString(4, lastname);
			statement.setString(5, userName);
			statement.setString(6, password);
			statement.setString(7, phone);
			statement.setInt(8,idRole);
			statement.setInt(9,id);
			
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi thêm user" + e.getLocalizedMessage());
		}
		 return count;
	}
	public users getUserById(int id) {
		String query = "SELECT * FROM Users u\n"
				+ "JOIN `Role` r WHERE u.id = r.id and u.id=?";
		Connection connection = mysqlconfig.getConnection();
		users users = new users();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				users.setId(resultSet.getInt("id"));
				users.setEmail(resultSet.getString("email"));
				users.setPassword(resultSet.getString("pwd"));
				users.setFirstName(resultSet.getString("firstName"));
				users.setLastName(resultSet.getString("lastName"));
				users.setFullName(resultSet.getNString("fullName"));
				users.setUserName(resultSet.getString("userName"));
				users.setPhone(resultSet.getString("phone"));
				role role = new role();
				role.setName(resultSet.getString("name"));
				users.setRole(role);;
			}
		} catch (Exception e) {
			System.out.println("Lỗi tra cứu dữ liệu "+ e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("Lỗi đóng kết nối db "+ e2.getLocalizedMessage());
			}
		}return users;
	} 
}