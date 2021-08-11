package com.example.ui.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8977315337460837419L;

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "username", nullable = false, length = 50)
	private String username;

	@Column(name = "firstname", nullable = false, length = 50)
	private String firstName;
	@Column(name = "lastname", nullable = false, length = 50)
	private String lastName;

	@Column(name = "email", nullable = false, length = 120, unique = true)
	private String email;

	@Column(name = "role", nullable = false, length = 50)
	private String role;

	@Column(name = "ssn", nullable = false, unique = true)
	private Long ssn;

	public UserModel(int id, String username, String firstName, String lastName, String email, String role, Long ssn) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}

	public UserModel() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getSsn() {
		return ssn;
	}

	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
