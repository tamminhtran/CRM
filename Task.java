package crm_project_02.entity;

import java.security.KeyStore.PrivateKeyEntry;

public class Task {
	private int id;
	private String name;
	private String startDate;
	private String endDate;
	private users users;
	private Groupwork groupwork;
	private Status status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public users getUsers() {
		return users;
	}
	public void setUsers(users users) {
		this.users = users;
	}
	public Groupwork getGroupwork() {
		return groupwork;
	}
	public void setGroupwork(Groupwork groupwork) {
		this.groupwork = groupwork;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
