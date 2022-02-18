package com.learning.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.EROLE;
import com.learning.dto.Register;
import com.learning.dto.Role;
import com.learning.exception.IdNotFoundException;
import com.learning.exception.RecordAlreadyExistsException;
import com.learning.payload.request.LoginRequest;
import com.learning.payload.request.SignupRequest;
import com.learning.payload.response.JwtResponse;
import com.learning.payload.response.MessageResponse;
import com.learning.repo.RoleRepository;
import com.learning.repo.UserRepository;
import com.learning.security.jwt.JwtUtils;
import com.learning.security.services.UserDetailsImpl;
import com.learning.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	//this method is mapped to addUser in UserServiceImpl
	@PostMapping("/register")
	public ResponseEntity<?> addUser(@Valid @RequestBody SignupRequest signupRequest) throws RecordAlreadyExistsException, IdNotFoundException {
//			Register res = userService.addUser(register);
//			System.out.println(res);
//			return ResponseEntity.status(201).body(res);
		if(userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity.badRequest().body("Error : Email is already taken.");
		}
		
		Register user = new Register(signupRequest.getEmail(), signupRequest.getName(), passwordEncoder.encode(signupRequest.getPassword())
				, signupRequest.getAddress());
		
		Set<String> strRoles = signupRequest.getRole();
		
		Set<Role> roles = new HashSet<>();
		
		if(strRoles == null) {
			Role userRole = roleRepository.findByRoleName(EROLE.ROLE_USER).orElseThrow(()->new RuntimeException("Error : Role not found."));
		}
		else {
			strRoles.forEach(e->{
				switch (e) {
				case "admin":
						Role roleAdmin = roleRepository.findByRoleName(EROLE.ROLE_ADMIN)
						.orElseThrow(()->new RuntimeException("Error : Role not found."));
						roles.add(roleAdmin);
					break;
				default:
					Role userRole = roleRepository.findByRoleName(EROLE.ROLE_USER)
					.orElseThrow(()->new RuntimeException("Error : Role not found."));
					roles.add(userRole);
					break;
				}
			});
		}
		
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.status(201).body(new MessageResponse("user created successfully"));
	}
	
	@PostMapping("/authenticate")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail()
						, loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateToken(authentication);
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetailsImpl.getAuthorities()
				.stream()
				.map(i->i.getAuthority())
				.collect(Collectors.toList());
	
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetailsImpl.getId(),
				userDetailsImpl.getEmail(),
				roles));
	}
	
	//this method is mapped to getAllUsers in UserServiceImpl
	@GetMapping("/users")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // this is mention that either user or admin have access to these functions
	public ResponseEntity<?> getAllUsersDetails(){
		Register[] register = userService.getAllUsers();
		if(register.length == 0) {
			Map<String, String> map = new HashMap<>();
			map.put("Message" , "No record found.");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
		}
		return ResponseEntity.ok(register);
	}
	
	//this method is mapped to getById in UserServiceImpl
	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getById(@PathVariable("id") int id) throws IdNotFoundException{
		Register res = userService.getUserById(id);
		return ResponseEntity.status(200).body(res);
	}
	
	//this method is mapped to deleteUserById in UserServiceImpl
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") int id) throws IdNotFoundException {
		String res = userService.deleteUserById(id);
		Map<String, String> map = new HashMap<>();
		if(res.equals("success")) {
			map.put("Message" , "User Deleted Successfully.");
			return ResponseEntity.status(201).body(map);
		}
		else {
			map.put("Message" , "User Not Deleted.");
			return ResponseEntity.status(404).body(map);
		}
	}
	
	//this method is mapped to updateUserById in UserServiceImpl
	@PutMapping("/users/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Register register) throws IdNotFoundException, RecordAlreadyExistsException {
		Register res = userService.updateUserById(id, register);
		if(res != null)
			return ResponseEntity.status(201).body(res);
		else {
			Map<String, String> map = new HashMap<>();
			map.put("Message" , "User this ID not found.");
			return ResponseEntity.status(403).body(map);
		}
			
	}
	
	
	//Below Method is before spring security
		//this method is mapped to Authenticate in UserServiceImpl
//		@PostMapping("/authenticate")
//		public ResponseEntity<?> authenticate(@Valid @RequestBody Credential credential) throws RecordAlreadyExistsException, IdNotFoundException {
//				String res = userService.Authenticate(credential);
//				Map<String, String> map = new HashMap<>();
//				if(res.equals("success")) {
//					map.put("Message" , "Success");
//					return ResponseEntity.status(201).body(map);
//				}
//				else {
//					map.put("Message" , "fail");
//					return ResponseEntity.status(403).body(map);
//				}
//		}
}
