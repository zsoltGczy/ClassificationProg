package com.classproject.usersandroles;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="Users")
public class User {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Column(unique=true, nullable=false)
	@Email(message = "You must add valid e-mail address!")
	private String email;
	@Column(nullable=false)	//Validalas //frontend hibauzenetek
	private String password;
	@Column(nullable=false)
	private String passwordCheck;
	private String firstName;
	private String lastName;
	private String activation;
	private Boolean enabled;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name="users_roles",
			joinColumns = {@JoinColumn(name="user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	private Set<Role> roles = new HashSet<Role>();
	
	
	
	
	public User() {}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
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

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	
	public void addRoles(String roleName) {
		if(this.roles == null || this.roles.isEmpty()) 
			this.roles = new HashSet<Role>();
		this.roles.add(new Role(roleName));
	}

	
	
}
