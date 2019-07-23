package com.revature.project1.beans;

import java.sql.Blob;

public class Receipt {

	private int ticketNo;
	private int receiptNo;
	private Blob image;
	
	public Receipt(int ticketNo, int receiptNo, Blob image) {
		super();
		this.ticketNo = ticketNo;
		this.receiptNo = receiptNo;
		this.image = image;
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public int getReceiptNo() {
		return receiptNo;
	}

	public Blob getImage() {
		return image;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
}
