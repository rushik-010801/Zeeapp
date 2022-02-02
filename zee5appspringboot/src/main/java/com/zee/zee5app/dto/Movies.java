package com.zee.zee5app.dto;

import javax.naming.NameNotFoundException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zee.zee5app.exception.LocationNotFound;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "movieName") }, name = "movies")
public class Movies {
	
	@Id
	@Column(name = "movId")
	private String id;
	
	@NotBlank
	private String movieName;
	
	@NotBlank
	private String category;
	
	@NotBlank
	private String releaseDate;
	
	@NotBlank
	private String trailer;
	
	@NotBlank
	private String language;
	
	@NotBlank
	private String cast;
	
	@NotNull
	private int length;
	
	@Max(value = 70)
	private int agelimit;

//	public void setMovieName(String movieName) throws NameNotFoundException {
//		if(movieName == null || movieName.length() == 0) {
//			throw new NameNotFoundException("Movie Name is not found");
//		}
//		this.movieName = movieName;
//	}
//	public void setTrailer(String trailer) throws LocationNotFound {
//		if(trailer == null || trailer.length() == 0) {
//			throw new LocationNotFound("Trailer Location not found");
//		}
//		this.trailer = trailer;
//	}
}
