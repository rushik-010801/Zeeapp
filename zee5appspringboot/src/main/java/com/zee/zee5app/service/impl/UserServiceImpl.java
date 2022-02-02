package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Login;
import com.zee.zee5app.dto.ROLE;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidEmailException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.InvalidPasswordException;
import com.zee.zee5app.repository.LoginRepository;
import com.zee.zee5app.repository.UserRepository;
import com.zee.zee5app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	LoginServiceImpl loginservice;
	
	@Override
	public String addUser(Register register) {
		Register register2 = userrepository.save(register);
		if(register2 != null) {
			Login login = new Login(register.getEmail(), register.getPassword(), register.getId(), ROLE.ROLE_USER);
			String res = loginservice.addCredentials(login);
			if(res.equals("success"))
				return "success for spring boot service";
			else
				return "Fail for spring boot service";
		}
		else {
			return "Fail for spring boot service";
		}
	}

	@Override
	public String updateUser(String id, Register register) {
		// TODO Auto-generated method stub
		return addUser(register);
	}

	@Override
	public Optional<Register> getUserById(String id) throws IdNotFoundException, InvalidIdLengthException,
			InvalidNameException, InvalidEmailException, InvalidPasswordException {
		return userrepository.findById(id);
	}

	@Override
	public Register[] getAllUsers()
			throws InvalidNameException, InvalidIdLengthException, InvalidEmailException, InvalidPasswordException {
		// TODO Auto-generated method stub
		List<Register> list = userrepository.findAll();
		Register[] registers = new Register[list.size()];
		return list.toArray(registers);
	}

	@Override
	public Optional<List<Register>> getAllUserDetails()
			throws InvalidNameException, InvalidIdLengthException, InvalidEmailException, InvalidPasswordException {
		// TODO Auto-generated method stub
		return Optional.ofNullable(userrepository.findAll());
	}

	@Override
	public String deleteUserById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		try {
			Optional<Register> optional = this.getUserById(id);
			if(optional.isEmpty()) {
				throw new IdNotFoundException("record not found");
			}
			else {
				userrepository.deleteById(id);
				String res = loginservice.deleteCredentials(optional.get().getEmail());
				if(res.equals("success"))
					return "success";
				else
					return "fail";
			}
		} catch (IdNotFoundException | InvalidNameException | InvalidIdLengthException | InvalidEmailException | InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IdNotFoundException(e.getMessage());
		}
	}


}
