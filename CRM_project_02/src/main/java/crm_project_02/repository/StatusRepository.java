package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.config.mysqlconfig;
import crm_project_02.entity.Status;

public class StatusRepository {
	public List<Status> findAll(){
		String query = "SELECT * FROM Status";
		Connection connection = mysqlconfig.getConnection();
		List<Status> list = new ArrayList<Status>();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Status status = new Status();
				status.setId(resultSet.getInt("id"));
				status.setName(resultSet.getString("name"));
				list.add(status);
			}
		} catch (SQLException e) {
			System.out.println("Lỗi find all status " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		
		return list;
	}
	
	public Status findById(int id) {
		String query = "SELECT * FROM Status s WHERE s.id =?";
		Connection connection = mysqlconfig.getConnection();
		Status status = new Status();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				status.setId(resultSet.getInt("id"));
				status.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			System.out.println("Lỗi find status by id " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		
		return status;
		
	}
	
	
	
}