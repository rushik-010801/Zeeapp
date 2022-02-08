package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.stereotype.Service;

import com.learning.dto.Register;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.RecordAlreadyExistsException;
import com.learning.repo.UserRepository;
import com.learning.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	//This method is used for Add the register
	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = RecordAlreadyExistsException.class)
	public Register addUser(Register register) throws RecordAlreadyExistsException, IdNotFoundException{
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(register.getEmail())) {
			
			throw new RecordAlreadyExistsException("This record is already existing.");
		}
		Register register2 = userRepository.save(register);
		if(register2 != null) {
				return register2;
		}
		else {
			return null;
		}
	}

	//this method gives all the user details
	@Override
	public Register[] getAllUsers() {
		// TODO Auto-generated method stub
		List<Register> list = userRepository.findAll();
		Register register[] = new Register[list.size()];
		list.toArray(register);
		return register;
	}

	//this method gives detials by ID
	@Override
	public Register getUserById(int id) throws IdNotFoundException{
		// TODO Auto-generated method stub
		Optional<Register> optional = userRepository.findById(id);
		if(optional.isEmpty()) {
			throw new IdNotFoundException(String.valueOf(id));
		}
		else {
			return optional.get();
		}
	}

	//this method is used for updating the register
	@Override
	public Register updateUserById(int id, Register register) throws IdNotFoundException, RecordAlreadyExistsException {
		// TODO Auto-generated method stub
		if(getUserById(id) == null) {
			throw new IdNotFoundException(String.valueOf(id));
		}
		else {
			Register register2 = userRepository.getById(id);
			if(register.getAddress() != null) {
				register2.setAddress(register.getAddress());
			}
			if(register.getEmail() != null) {
				register2.setEmail(register.getEmail());
			}
			if(register.getName() != null) {
				register2.setName(register.getName());
			}
			if(register.getPassword() != null) {
				register2.setPassword(register.getPassword());
			}
			Register register3 = userRepository.save(register2);
			return register3;
		}
	}

	//this method is used for deleting the record
	@Override
	public String deleteUserById(int id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Register register = userRepository.getById(id);
		if(register == null) {
			throw new IdNotFoundException(String.valueOf(id));
		}
		else {
			userRepository.deleteById(id);
			return "success";
			
		}
	}

	//this method is used for login purposes
	@Override
	public String Authenticate(Credential credential) {
		// TODO Auto-generated method stub
		String username = credential.getUsername();
		String password = credential.getPassword();
		Optional<Register> optional = userRepository.findByEmail(username);
		if(optional.isEmpty()) {
			return "fail";
		}
		else {
			if(optional.get().getPassword().equals(password)) {
				return "success";
			}
			else {
				return "fail";
			}
		}
		
	}
	
	

}
