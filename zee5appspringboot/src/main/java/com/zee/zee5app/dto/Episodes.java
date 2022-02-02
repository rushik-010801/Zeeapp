package com.zee.zee5app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "episodes", uniqueConstraints = {@UniqueConstraint (columnNames = "episodename")})
public class Episodes {
	
	@Id
	@Column(name = "epiId")
	private String epiId;
	
	@NotBlank
	private String serId;
	
	@NotBlank
	private String episodename;
	
	@NotNull
	private float epilength;
	
	@NotBlank
	private String location;
	
//	@NotBlank
//	private String trailer;
}
