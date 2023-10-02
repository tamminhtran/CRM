package crm_project_02.entity;

import org.eclipse.jdt.internal.compiler.IDebugRequestor;
// entity: nơi khai báo ra các class đặt tên và thuộc tính giống tên và column của bảng trong db
public class users {
	private int id;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String fullName;
	private String userName;
	private String phone;
	private role role; // nếu cột là khóa ngoại thì không khai báo biến mà sẽ chuyển thành đối tượng (entity) của bảng được tham chiếu tới
	
	public role getRole() {
		return role;
	}
	public void setRole(role role) {
		this.role = role;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullname) {
		this.fullName = fullname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
