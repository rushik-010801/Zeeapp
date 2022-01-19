package com.zee.zee5app.service;


import com.zee.zee5app.dto.Series;
import com.zee.zee5app.repository.SeriesRepository;

public class SeriesService {
private SeriesRepository repository = SeriesRepository.getInstance();
	
	private SeriesService() {
		// TODO Auto-generated constructor stub
	}
	
	private static SeriesService  service = null;
	
	public static SeriesService getInstance() {
		if(service==null)
			service = new SeriesService();
		return service;
	}
	
	public String addSeries(Series serie) {
		return this.repository.addSeries(serie);
	}
	
	public Series getSeriesById(String id) {
		return this.repository.getSeriesById(id);
	}
	
	public Series[] getSeries() {
		return repository.getSeries();
	}
	public String modifySeriesById(String id, String seriesName, String trailer, int noofSeasons, int noofepisodes, String cast[], int lengthOfEpisode) {
		return repository.modifySeriesById(id, seriesName, trailer, noofSeasons, noofepisodes, cast, lengthOfEpisode);
	}
	
	public String deleteSeriesById(String id) {
		return repository.deleteSeriesById(id);
	}
}
