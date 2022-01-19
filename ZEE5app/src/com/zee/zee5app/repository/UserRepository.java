package com.zee.zee5app.repository;

import com.zee.zee5app.dto.Register;

public class UserRepository {
	private Register[] registers = new Register[10];
	private static int count = -1;
	
	private UserRepository() {
		// TODO Auto-generated constructor stub
	}
	
	public String addUser(Register register) {
		if(count == registers.length - 1) {
			Register[] temp = new Register[registers.length * 2];
			System.arraycopy(registers, 0, temp, 0, registers.length);
			registers = temp;
		}
		registers[++count] = register;
		return "success";
	}
	
	public Register getUserById(String id) {
		for(Register register : registers) {
			if(register != null && register.getId().equals(id)) {
				return register;
			}
		}
		return null;
	}
	
	public Register[] getUsers() {
		return registers;
	}
	
	private static UserRepository userRepository;
	public static UserRepository getInstance() {
		
		if(userRepository==null)
			userRepository = new UserRepository();
		return userRepository;
		
	}

}
