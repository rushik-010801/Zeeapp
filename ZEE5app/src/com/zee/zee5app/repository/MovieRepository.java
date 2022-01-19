package com.zee.zee5app.repository;

import com.zee.zee5app.dto.Movies;

public class MovieRepository {
	private Movies[] movies = new Movies[10];
	private int count = -1;
	
	public MovieRepository() {
		// TODO Auto-generated constructor stub
	}
	
	public String addMovie(Movies movie) {
		if(count == movies.length - 1) {
			Movies[] temp = new Movies[movies.length * 2];
			System.arraycopy(movies, 0, temp, 0, movies.length);
			movies = temp;
		}
		movies[++count] = movie;
		return "success";
	}
	
	public Movies[] getMovies() {
		return movies;
	}
	
	public Movies getMovieById(String id) {
		for(Movies mov : movies) {
			if(mov != null && mov.getId().equals(id)) {
				return mov;
			}
		}
		return null;
	}
	
	public String modifyMovieById(String id, String movieName, String category, String releaseDate,
							String trailer, String language, String cast[], int length) {
		for(Movies mov : movies) {
			if(mov != null && mov.getId().equals(id)) {
				mov.setMovieName(movieName);
				mov.setCategory(category);
				mov.setReleaseDate(releaseDate);
				mov.setTrailer(trailer);
				mov.setLanguage(language);
				mov.setCast(cast);
				mov.setLength(length);
				return "Success";
			}
		}
		return "Fail";
	}
	
	public String deleteMovieById(String id) {
		for(int i = 0; i < movies.length; i++) {
			if(movies[i] != null && movies[i].getId().equals(id)) {
				for(int j = i + 1; j < movies.length; i++, j++) {
					movies[i] = movies[j]; 
				}
				movies[i] = null;
				return "Success";
			}
		}
		return "Fail";
	}
	
	private static MovieRepository movrepo;
	public static MovieRepository getInstance() {
		if(movrepo == null) {
			movrepo = new MovieRepository();
		}
		return movrepo;
	}
}
