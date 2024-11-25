package com.basic.service;

import java.util.Map;

import com.basic.dto.UserDTO;

public interface UserService {
	public Map<String, Object> findUserList(UserDTO userDTO) throws Exception;
}
