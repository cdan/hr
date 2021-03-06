package org.xianairlines.model;

// Generated Dec 6, 2010 12:44:21 PM by Hibernate Tools 3.3.0.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * User generated by hbm2java
 */

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class User implements java.io.Serializable {

	private int id;
	private String name;
	private String password;
	private String role;

	public User() {
	}

	public User(int id) {
		this.id = id;
	}

	public User(int id, String name, String password, String role) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", unique = true, length = 45)
	@NotNull
	@Length(max = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", length = 45)
	@NotNull
	@Length(max = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "role", length = 45)
	@NotNull
	@Length(max = 45)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
