package com.learning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.dto.Role;
import com.learning.exception.IdNotFoundException;
import com.learning.service.RoleService;
import com.learning.repo.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository repository;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addRole(Role role) {
		// TODO Auto-generated method stub
		Role role2 = repository.save(role);
		if(role2 != null)
			return "success";
		else
			return "fail";
	}

	@Override
	public String deleteRole(int roleId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		try {
			Optional<Role> optional = repository.findById(roleId);
			if(optional.isEmpty()) {
				throw new IdNotFoundException("record not found");
			}
			else {
				repository.deleteById(roleId);
				return "success";
			}
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}

}
