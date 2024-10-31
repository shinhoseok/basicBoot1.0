package com.basic.user.web;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.basic.user.service.UserService;
import com.basic.user.service.UserVO;

@Controller
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name = "userServiceImpl")
	private UserService userService;
	
	@GetMapping("/user/list")
	public String list(@ModelAttribute UserVO userVO, Model model) throws Exception {
		LOGGER.debug(">>/user/list");
		Map<String, Object> rslt = userService.findUserList(userVO);
		model.addAttribute("rslt", rslt);
		return "admin/user/userList";
	}
}
