package com.revature.project1.beans;

import java.time.LocalDateTime;

public class ReimbursementRequest {

	private int ticketNo;
	private int empId;
	private int amount;
	private String status;
	private int resolvedBy;
	private String details;
	private LocalDateTime lastUpdate;
	public ReimbursementRequest(int ticketNo, int empId, int amount, String status, int resolvedBy, String details,
			LocalDateTime lastUpdate) {
		super();
		this.ticketNo = ticketNo;
		this.empId = empId;
		this.amount = amount;
		this.status = status;
		this.resolvedBy = resolvedBy;
		this.details = details;
		this.lastUpdate = lastUpdate;
	}
	public int getTicketNo() {
		return ticketNo;
	}
	public int getEmpId() {
		return empId;
	}
	public int getAmount() {
		return amount;
	}
	public String getStatus() {
		return status;
	}
	public int getResolvedBy() {
		return resolvedBy;
	}
	public String getDetails() {
		return details;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setResolvedBy(int resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
