package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import crm_project_02.config.mysqlconfig;
import crm_project_02.entity.Groupwork;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GroupWorkReposity {
	public int insert(String name, String startDate, String endDate) {
		String query = "INSERT INTO Project(name, startDate, endDate) VALUES (?,?,?)";
		Connection connection = mysqlconfig.getConnection();
		int count = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, convertToDate(startDate));
            statement.setString(3, convertToDate(endDate));
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi kết nối cơ sở dữ liệu" + e.getLocalizedMessage());
		}
		return count;
	}
	public List<Groupwork> findAll(){
		List<Groupwork> list = new ArrayList<Groupwork>();
		String query = "SELECT * FROM Project";
		
		Connection connection = mysqlconfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				Groupwork groupwork = new Groupwork();
				groupwork.setId(resultSet.getInt("id"));
				groupwork.setName(resultSet.getString("name"));
				groupwork.setStartDate(resultSet.getString("startDate"));
				groupwork.setEndDate(resultSet.getString("endDate"));
				list.add(groupwork);
			}
		} catch (Exception e) {
			System.out.println("Lỗi kết nối cơ sở dữ liệu" + e.getLocalizedMessage());
		}
		return list;
	}
	public int deleteById(int id) {
		String query = "DELETE FROM Project p WHERE p.id = ?";
		int count = 0;
		Connection connection = mysqlconfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi delete project by id "+ e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		
		return count;
	}
	public Groupwork findById(int id) {
		String query = "SELECT * FROM Project WHERE id = ?";
		Connection connection = mysqlconfig.getConnection();
		Groupwork groupwork = new Groupwork();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				groupwork.setId(resultSet.getInt("id"));
				groupwork.setName(resultSet.getString("name"));
				groupwork.setStartDate(resultSet.getString("startDate"));
				groupwork.setEndDate(resultSet.getString("endDate"));
			}
		} catch (Exception e) {
			System.out.println("Loi get data "+ e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("Loi dong ket noi "+ e2.getLocalizedMessage());
			}
		}	
		return groupwork;
	}
	public int updateById(int id, String name, String startDate, String endDate) {
		String query = "UPDATE Project p \r\n"
				+ "SET p.name = ?, p.startDate = ?, p.endDate = ? WHERE p.id = ?";
		int count = 0;
		Connection connection = mysqlconfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, startDate);
			statement.setString(3, endDate);
			statement.setInt(4, id);
			count = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Lỗi edit project by id " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
		
		return count;
	}
	private String convertToDate(String dateString) {
		String[] strings;

	    if (dateString.contains("/")) {
	        strings = dateString.split("/");
	        return strings[2] + "-" + strings[1] + "-" + strings[0];
	    } else if (dateString.contains("-")) {
	        strings = dateString.split("-");
	        return strings[0] + "-" + strings[1] + "-" + strings[2];
	    } else {
	        // Handle invalid date format
	        return null;
	    }
	}
	
}
