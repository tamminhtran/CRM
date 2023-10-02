package crm_project_02.config;

import java.sql.Connection;
import java.sql.DriverManager;

// cấu hình JDBC kết nối tới server mysql
public class mysqlconfig {
	public static Connection getConnection() {
		try { //khai báo driver sử dụng cho jdbc tương ứng với db cần kết nối
			Class.forName("com.mysql.cj.jdbc.Driver");
			// khai báo Driver sẽ mở kết nối tới db nào
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/crm", "root", "Cun9");
		} catch (Exception e) {
			// lỗi xảy ra sẽ chạy vào đây
			System.out.println("Lỗi kết nối database" + e.getLocalizedMessage());
			return null;
		}
		
	}
}
