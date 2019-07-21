package beans;

import java.sql.Date;

public class Request 
{
	private int employeeID;
	private int managerID;
	private double amount;
	private int requestStatus;
	private Date timestamp;
	private String description;
	private int category;
	private int ticketNumb;
	

	public Request(int empID, int manID, double amount, int requestStatus, String desc, int cat, int ticketNumb) {}
	
	public int getTicketNumb() {
		return ticketNumb;
	}
	
	public void setTicketNumb(int ticketNumb) {
		this.ticketNumb = ticketNumb;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	private int ticketNumber;
	
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public int getManagerID() {
		return managerID;
	}
	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
}
