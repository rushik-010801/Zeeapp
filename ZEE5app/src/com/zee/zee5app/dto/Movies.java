package com.zee.zee5app.dto;

import lombok.Data;

@Data
public class Movies {
	private String movieName;
	private String category;
	private String releaseDate;
	private String trailer;
	private String language;
	private String cast[];
	private int length;
	private String id;
	
}
