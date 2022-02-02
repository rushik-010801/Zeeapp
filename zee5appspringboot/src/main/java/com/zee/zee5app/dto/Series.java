package com.zee.zee5app.dto;

import javax.naming.NameNotFoundException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.zee.zee5app.exception.LocationNotFound;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "seriesName")}, name = "series")
public class Series implements Comparable<Series>{
	
	@Id
	@Column(name = "serId")
	private String id;
	
	@NotBlank
	private String seriesName;
	
	@Min(value = 2)
	private int noofepisodes;
	
	@NotBlank
	private String releasedate;
	
//	@NotBlank
//	private String trailer;
	
	@NotBlank
	private String cast;
	
	@NotBlank
	private String category;
	
	@Max(value=70)
	private int agelimit;
	
	@NotBlank
	private String language;
	
//	public void setSeriesName(String seriesName) throws NameNotFoundException {
//		if(seriesName == null || seriesName.length() == 0)
//			throw new NameNotFoundException("Name not found");
//		this.seriesName = seriesName;
//	}
//	public void setTrailer(String trailer) throws LocationNotFound {
//		if(trailer == null || trailer.length() == 0)
//			throw new LocationNotFound("Location Not found");
//		this.trailer = trailer;
//	}
	@Override
	public int compareTo(Series o) {
		// TODO Auto-generated method stub
		return o.id.compareTo(this.id);
	}
	
	
}
