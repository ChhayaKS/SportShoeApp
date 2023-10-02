package org.Project.controller;

import java.util.ArrayList;
import java.util.List;

import org.Project.GlobalData.GlobalData;
import org.Project.entities.Role;
import org.Project.entities.User;
import org.Project.repository.RoleRepository;
import org.Project.repository.UserRepository;
import org.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRepository userRepository;
	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;

	public LoginController( UserRepository userRepository,
			RoleRepository roleRepository) {
		super();
	    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
	}

	@GetMapping("/login")
	public String login() {
		GlobalData.cart.clear();
		return "index";
	}

	@GetMapping("/register")
	public String registerGet(Model model) {
		User user=new User();
		model.addAttribute("user", "user");
		return "register";

	}
	
//	 @PostMapping("/register")
//	    public String registration(@ModelAttribute("user") User user,
//	                               BindingResult result,
//	                               Model model){
//	        User existingUser = userService.findUserByName(user.getusername());
//	
//	        if(existingUser != null && existingUser.getusername() != null && !existingUser.getusername().isEmpty()){
//	            result.rejectValue("username", null,
//	                    "There is already an account registered with the same username");
//	        }
//
//	        if(result.hasErrors()){
//	            model.addAttribute("user", user);
//	            return "/register";
//	        }
//
//	        userService.saveUser(user);
//	        return "redirect:/";
//	    }
	 
	 @PostMapping("/register")
		public String registerPost(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
			String password = user.getPassword();
			user.setPassword(bCryptPasswordEncoder.encode(password));
			List<Role> roles = new ArrayList<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));
			user.setRoles(roles);
			userRepository.save(user);
			request.login(user.getusername(), password);
			return "redirect:/";
		}
}
