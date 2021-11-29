package com.fortune.domains;

import java.util.Date;

public class EnquiryDom {

	private String firstName;
	private String lastName;
	private Date dateInit;
	private Date dateEnd;
	private boolean headOfDepartment = false;

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

	public Date getDateInit() {
		return dateInit;
	}

	public void setDateInit(Date dateInit) {
		this.dateInit = dateInit;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public boolean isHeadOfDepartment() {
		return headOfDepartment;
	}

	public void setHeadOfDepartment(boolean headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}

}
