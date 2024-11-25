package com.basic.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.basic.dto.UserDTO;
import com.basic.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
	@GetMapping("/user/list")
	public String list(@ModelAttribute UserDTO userDTO, Model model) throws Exception {
		log.debug(">>/user/list");
		Map<String, Object> rslt = userService.findUserList(userDTO);
		model.addAttribute("rslt", rslt);
		return "admin/user/userList";
	}
	
	@PostMapping("/user/saveUser")
	public String saveUser(@ModelAttribute UserDTO userDTO, Model model) throws Exception {
		log.debug(">>/user/saveUser");
		return "admin/user/userInsert";
	}
}
