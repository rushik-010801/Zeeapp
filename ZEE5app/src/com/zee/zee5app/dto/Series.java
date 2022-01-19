package com.zee.zee5app.dto;

import lombok.Data;

@Data
public class Series {
	private String seriesName;
	private int noofSeasons;
	private int noofepisodes;
	private String trailer;
	private int lengthOfEpisode;
	private String cast[];
	private String id;
	
}
