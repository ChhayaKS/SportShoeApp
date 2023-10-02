package org.Project.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.Project.entities.Role;
import org.Project.entities.User;
import org.Project.repository.RoleRepository;
import org.Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

//	public void saveUser(User user) {
//		User users = new User();
//		user.setusername(users.getusername());
//		user.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
//		user.setAddress(users.getAddress());
//		user.setEmail(users.getEmail());
//		Role role = roleRepository.findByName("ROLE_ADMIN");
//		if (role == null) {
//			role = checkRoleExist();
//		}
//		user.setRoles(Arrays.asList(role));
//		userRepository.save(user);
//	  }
//	
	public User findUserByName(String username)
	{
		return userRepository.findUserByUsername(username);
      	
	}

	public List<User> FindAllUser() {
		return userRepository.findAll();
	}
	
//	public List<User> findAllUser() {
//		List<User> users=userRepository.findAll();
//		 return users.stream().map((user)-> mapToUser(user)).collect(Collectors.toList());
//				 
//	}
//
//	
//	private User mapToUser(User user) {
//		User user1 = new User();
//        String st=user1.getusername();
//        user1.setAddress(user1.getAddress());;
//        user1.setEmail(user1.getEmail());
//        return user1;
//	}
//
//	
	
	public void deteteUserById(Long id) {
		userRepository.deleteById(id);
	}

//	private Role checkRoleExist() {
//		Role role = new Role();
//		role.setName("ROLE_ADMIN");
//		return roleRepository.save(role);
//	}

}
