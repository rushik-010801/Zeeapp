package com.zee.zee5app.service;

import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.repository.MovieRepository;

public class MovieService {
private MovieRepository repository = MovieRepository.getInstance();
	
	private MovieService() {
		// TODO Auto-generated constructor stub
	}
	
	private static MovieService  service = null;
	
	public static MovieService getInstance() {
		if(service==null)
			service = new MovieService();
		return service;
	}
	
	public String addMovie(Movies movie) {
		return this.repository.addMovie(movie);
	}
	
	public Movies getMovieById(String id) {
		return this.repository.getMovieById(id);
	}
	
	public Movies[] getMovies() {
		return repository.getMovies();
	}
	public String modifyMovieById(String id, String movieName, String category, String releaseDate,
			String trailer, String language, String cast[], int length) {
		return repository.modifyMovieById(id, movieName, category, releaseDate, trailer, language, cast, length);
	}
	
	public String deleteMovieById(String id) {
		return repository.deleteMovieById(id);
	}
}
