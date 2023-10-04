package crm_project_02.repository;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;

import java.util.*;


import crm_project_02.config.mysqlconfig;
import crm_project_02.entity.Groupwork;
import crm_project_02.entity.Status;
import crm_project_02.entity.Task;
import crm_project_02.entity.users;

public class TaskRepository {
	private String changeDateFormat(String oldDate){
        String newDate = "";

        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(oldDate);
            newDate = new SimpleDateFormat("dd/MM/yyyy").format(date);

        } catch (Exception e){
            System.out.println("An error occurred when change date format | "+e.getMessage());
            e.printStackTrace();
        }

        return newDate;
    }
	public int insert(String name, int idProject, int idUser, String startDate, String endDate) {
		String query = "INSERT INTO Job(name, id_project, id_user, startDate, endDate) \n"
						+ "VALUES(?,?,?,?,?)";
		Connection connection = mysqlconfig.getConnection();
		int count = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setInt(2, idProject);
			statement.setInt(3, idUser);
			statement.setString(4, startDate);
			statement.setString(5, endDate);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi thêm task "+ e.getLocalizedMessage());
		}finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("An error occurred when close database | "+e.getMessage());
                    e.printStackTrace();
                }
            }
        }return count;
	} 
	public List<Task> getAllTask(){
		List<Task> list = new ArrayList<Task>();
		StringBuilder query = new StringBuilder("SELECT j.id, j.name, j.startDate, j.endDate, p.name as project_name, u.fullName as fullname, s.name as status_name");
		query.append("	FROM Job j");
		query.append(" JOIN Project p ON p.id= j.id_project");
		query.append(" JOIN Users u ON u.id = j.id_user");
		query.append(" JOIN Status s ON j.id_status = s.id");
//		String query = "SELECT j.id, j.name, j.startDate, j.endDate, p.name as project_name, u.fullName as fullname, s.name as status_name FROM Job j \n"
//				+ "JOIN Project p ON p.id= j.id_project \n"
//				+ "JOIN Users u ON u.id = j.id_user\n"
//				+ " ";
		Connection connection = mysqlconfig.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(query.toString());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Task task = new Task();
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setStartDate(changeDateFormat(resultSet.getString("startDate")));
				task.setEndDate(changeDateFormat(resultSet.getString("endDate")));
				Groupwork groupwork = new Groupwork();
				groupwork.setName(resultSet.getString("project_name"));
				task.setGroupwork(groupwork);
				users users = new users();
				users.setFullName(resultSet.getNString("fullname"));
				task.setUsers(users);
				Status status = new Status();
				status.setName(resultSet.getString("status_name"));
				task.setStatus(status);
				
				list.add(task);				
			}
		} catch (Exception e) {
			System.out.println("Lỗi lấy dữ liệu công việc "+ e.getLocalizedMessage());
		} finally {
			if(connection!= null) {
				try {
					connection.close();
				} catch (Exception e2) {
					System.out.println("Lỗi đóng kết nối "+e2.getLocalizedMessage());
				}
			}
		} return list;
	}
	public int updateById(String name, String startDate, String endDate, int idProject, int idUser, int idStatus, int id) {
		int count = 0;
		String query = "UPDATE Job as j.name=?, t.startDate=?, t.endDate=?, t.id_user=?, t.id_project=?, t.id_status=? WHERE t.id=?";
		Connection connection = mysqlconfig.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, name);
			statement.setString(2, startDate);
			statement.setString(3, endDate);
			statement.setInt(4, idProject);
			statement.setInt(5, idUser);
			statement.setInt(6, idStatus);
			statement.setInt(7, id);
			
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi cập nhật công việc "+ e.getLocalizedMessage());
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (Exception e2) {
					System.out.println("Lỗi đóng kết nối "+ e2.getLocalizedMessage());
				}
			}
		} return count;
	}
	public int deleteById(int id) {
		int count = 0;
		Connection connection = mysqlconfig.getConnection();
		String query = "DELETE FROM Job j WHERE j.id=?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			count = statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Lỗi xóa dữ liệu "+ e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (Exception e2) {
				System.out.println("Lỗi đóng kết nối "+ e2.getLocalizedMessage());
			}
		}return count;
	}
public Task findById(int id) {
		
		String query = "SELECT j.id, j.name, p.name as project_name, u.fullname as user_name, j.startDate, j.endDate, s.name as status_name FROM Job AS j\r\n"
				+ "JOIN Users AS u ON j.id_user = u.id \r\n"
				+ "JOIN Project AS p ON j.id_project = p.id\r\n"
				+ "JOIN Status AS s ON j.id_status = s.id\r\n"
				+ "WHERE j.id = ?";
		Connection connection = mysqlconfig.getConnection();
		Task task = new Task();
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setStartDate(resultSet.getString("startDate"));
				task.setEndDate(resultSet.getString("endDate"));
				Groupwork groupwork = new Groupwork();
				groupwork.setName(resultSet.getString("project_name"));
				task.setGroupwork(groupwork);
				users users = new users();
				users.setFullName(resultSet.getString("user_name"));
				task.setUsers(users);
				Status status = new Status();
				status.setName(resultSet.getString("status_name"));
				task.setStatus(status);
				
			}
		} catch (SQLException e) {
			System.out.println("Lỗi find Task by id " + e.getLocalizedMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
			}
		}
			
		return task;
		
	}
}
