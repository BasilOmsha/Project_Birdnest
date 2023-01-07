package model;

import java.io.Serializable;

public class Pilot implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String createdDt;
	private String email;

	public Pilot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pilot(String id, String firstname, String lastName, String phoneNumber, String createdDt, String email) {
		super();
		this.id = id;
		this.firstName = firstname;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.createdDt = createdDt;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(String createdDt) {
		this.createdDt = createdDt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Pilot [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", createdDt=" + createdDt + ", email=" + email + "]";
	}
	
}
