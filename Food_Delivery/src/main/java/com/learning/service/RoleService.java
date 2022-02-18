package com.learning.service;

import com.learning.dto.Role;
import com.learning.exception.IdNotFoundException;

public interface RoleService {
	public String addRole(Role erole);
	public String deleteRole(int roleId) throws IdNotFoundException;
}
