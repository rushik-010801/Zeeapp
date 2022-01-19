package com.zee.zee5app.repository;

import com.zee.zee5app.dto.Series;

public class SeriesRepository {
	private Series[] series = new Series[10];
	private int count = -1;
	
	public SeriesRepository() {
		// TODO Auto-generated constructor stub
	}
	
	public String addSeries(Series serie) {
		if(count == series.length - 1) {
			Series[] temp = new Series[series.length * 2];
			System.arraycopy(series, 0, temp, 0, series.length);
			series = temp;
		}
		series[++count] = serie;
		return "success";
	}
	
	public Series[] getSeries() {
		return series;
	}
	
	public Series getSeriesById(String id) {
		for(Series ser : series) {
			if(ser != null && ser.getId().equals(id)) {
				return ser;
			}
		}
		return null;
	}
	
	public String modifySeriesById(String id, String seriesName, String trailer, int noofSeasons, int noofepisodes, String cast[], int lengthOfEpisode) {
		for(Series ser : series) {
			if(ser != null && ser.getId().equals(id)) {
				ser.setSeriesName(seriesName);
				ser.setNoofepisodes(noofepisodes);
				ser.setNoofSeasons(noofSeasons);
				ser.setTrailer(trailer);
				ser.setCast(cast);
				ser.setLengthOfEpisode(lengthOfEpisode);
				return "Success";
			}
		}
		return "Fail";
	}
	
	public String deleteSeriesById(String id) {
		for(int i = 0; i < series.length; i++) {
			if(series[i] != null && series[i].getId().equals(id)) {
				for(int j = i + 1; j < series.length; i++, j++) {
					series[i] = series[j]; 
				}
				series[i] = null;
				return "Success";
			}
		}
		return "Fail";
	}
	
	private static SeriesRepository serrepo;
	public static SeriesRepository getInstance() {
		if(serrepo == null) {
			serrepo = new SeriesRepository();
		}
		return serrepo;
	}
}
