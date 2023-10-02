package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;

import crm_project_02.config.mysqlconfig;
import crm_project_02.entity.role;

/*
 * RoleReposity chứa tất cả các câu query liên quan đến bảng role
 * 
 * */


public class RoleRepository {
	 public int insert(String name, String desc) { // khai báo 2 tham số do câu query update nhận 2 tham số từ FE
		 String query = "INSERT INTO `Role`(name, description) VALUES (?, ?)";
		 Connection connection = mysqlconfig.getConnection();
		 int count = 0;
		 try {
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, name);
			 statement.setString(2, desc);
			 
			 count = statement.executeUpdate();
			 
		} catch (Exception e) {
			System.out.println("Lỗi thêm role" + e.getLocalizedMessage());
		}
		 return count;
	 }
	 
	 // đối với câu select tên hàm sẽ bắt đầu bằng find
	 // nếu có điều kiện where -> tên hàm có thêm chữ by
	 public List<role> getAllRole(){
		 //public List<role> findAll() {}
			String query = "SELECT * FROM `Role`";
			Connection connection = mysqlconfig.getConnection();
			List<role> listrole = new ArrayList<role>();
			try {
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next()) {
					role role = new role();
					role.setId(resultSet.getInt("id"));
					role.setName(resultSet.getString("name"));
					role.setDescription(resultSet.getString("description"));
					
					listrole.add(role);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					connection.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					System.out.println("Lỗi đóng kết nối" + e2.getLocalizedMessage());
				}
			}
			return listrole;
		}
	 public int deleteById(int id) {
		 String query = "DELETE FROM `Role` r WHERE r.id = ?";
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
				System.out.println("Lỗi đóng cơ sở dữ liệu " + e2.getLocalizedMessage());
			}
		} return count;
	 }
	 public int updateById(String name, String description, int id) {
		 String query = "UPDATE `Role` r  SET r.name = ?, r.description =? WHERE r.id =?";
		 Connection connection = mysqlconfig.getConnection();
		 int count = 0;
		 try {
			 PreparedStatement statement = connection.prepareStatement(query);
			 statement.setString(1, name);
			 statement.setString(2, description);
			 statement.setInt(3, id);
			 count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi kết nối cơ sở dữ liệu " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("Lỗi đóng cơ sở dữ liệu "+ e2.getLocalizedMessage());
			}
		}
		 return count;
	 } 
	 public role findById(int id) {
		 String query = "SELECT * FROM Role r WHERE r.id=?";
		 role role = new role();
		 Connection connection = mysqlconfig.getConnection();
		 try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				role.setName(resultSet.getString("name"));
				role.setDescription(resultSet.getString("description"));
			}
		} catch (Exception e) {
			System.out.println("Lỗi kết nối cơ sở dữ liệu " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("Lỗi kết nối cơ sở dữ liệu " + e2.getLocalizedMessage());
			}
		} return role;
	 }
}
	
