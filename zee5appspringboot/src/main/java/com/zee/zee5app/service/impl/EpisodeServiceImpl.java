package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Episodes;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.service.EpisodeService;

@Service
public class EpisodeServiceImpl implements EpisodeService {
	@Autowired
	EpisodeRepository repository;
	
	@Override
	public String addEpisode(Episodes episode) {
		Episodes episode2 = repository.save(episode);
		if(episode2 != null) {
			return "success for spring boot service";
		}
		else {
			return "fail for spring boot service";
		}
	}

	@Override
	public Optional<List<Episodes>> getEpisodes() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(repository.findAll());
	}

	@Override
	public Optional<Episodes> getEpisodeById(String id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public String deleteEpisodeById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		try {
			Optional<Episodes> optional = this.getEpisodeById(id);
			if(optional.isEmpty()) {
				throw new IdNotFoundException("record not found");
			}
			else {
				repository.deleteById(id);
			return "success";}
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

	@Override
	public String modifyEpisodeById(String id, Episodes episode) {
		// TODO Auto-generated method stub
		return addEpisode(episode);
	}

}
