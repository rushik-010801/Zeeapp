package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;
import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.service.SeriesService;

@Service
public class SeriesServiceImpl implements SeriesService {
	@Autowired
	SeriesRepository repository;
	
	@Override
	public String addSeries(Series serie) {
		Series serie2 = repository.save(serie);
		if(serie2 != null) {
			return "success for spring boot service";
		}
		else {
			return "fail for spring boot service";
		}
	}

	@Override
	public Optional<List<Series>> getSeries() throws NameNotFoundException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repository.findAll());
	}

	@Override
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException, NameNotFoundException {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public String deleteSeriesById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		try {
			Optional<Series> optional = this.getSeriesById(id);
			if(optional.isEmpty()) {
				throw new IdNotFoundException("record not found");
			}
			else {
				repository.deleteById(id);
			return "success";}
		} catch (IdNotFoundException | NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

	@Override
	public String modifySeriesById(String id, Series serie) {
		// TODO Auto-generated method stub
		return addSeries(serie);
	}

}
