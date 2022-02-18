package com.learning.dto;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.learning.dto.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//Adding Lombok annotations for generating setter and getter, constructors, toString method
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

//This is used for ORM mapping
@Entity
//For customizing the table name
@Table(name = "register", uniqueConstraints = {@UniqueConstraint (columnNames = {"password", "email"})})

public class Register {
	
	@Id //this is for primary key
	@Column(name = "regId") // used for column name customization
	@GeneratedValue(strategy = GenerationType.AUTO) // for generating sequential values
	private int regId;
	
	@Size(max = 50)
	@NotBlank
	@Email
	private String email;
	
	@Size(max = 50)
	@NotBlank
	private String name;
	
	@Size(max = 100)
	@NotBlank
	private String password;
	
	@Size(max = 150)
	@NotBlank
	private String address;
	
	@ManyToMany(fetch = FetchType.LAZY) // creating new table for roles
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "regId"), 
	inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> roles = new HashSet<>();
	
	public Register(String email, String name, String password, String address) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.address = address;
	}
}
