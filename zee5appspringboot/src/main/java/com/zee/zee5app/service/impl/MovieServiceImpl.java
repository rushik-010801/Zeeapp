package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;
import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movies;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.exception.LocationNotFound;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository repository;
	
	@Override
	public String addMovie(Movies movie) {
		// TODO Auto-generated method stub
		Movies movie2 = repository.save(movie);
		if(movie2 != null) {
			return "success for spring boot service";
		}
		else {
			return "fail for spring boot service";
		}
	}

	@Override
	public Optional<List<Movies>> getMovies() throws NameNotFoundException, LocationNotFound {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repository.findAll());
	}

	@Override
	public Optional<Movies> getMovieById(String id)
			throws IdNotFoundException, NameNotFoundException, LocationNotFound {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public String deleteMovieById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		try {
			Optional<Movies> optional = this.getMovieById(id);
			if(optional.isEmpty()) {
				throw new IdNotFoundException("record not found");
			}
			else {
				repository.deleteById(id);
			return "success";}
		} catch (IdNotFoundException | NameNotFoundException | LocationNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

	@Override
	public String modifyMovieById(String id, Movies movie) {
		// TODO Auto-generated method stub
		return addMovie(movie);
	}

}
