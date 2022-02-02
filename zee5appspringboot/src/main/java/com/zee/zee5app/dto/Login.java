package com.zee.zee5app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "login", uniqueConstraints = {@UniqueConstraint (columnNames = "password")})
public class Login {
	
	@Id
	@Column(name = "username")
	private String userName;
	
	@Size(max = 200)
	@NotBlank
	private String password;
	
	@NotBlank
	private String regId;
	
	@NotNull
	private ROLE role;
}
