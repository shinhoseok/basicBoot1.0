package com.basic.user.service;

import java.util.Map;

public interface UserService {
	public Map<String, Object> findUserList(UserVO userVO) throws Exception;
}
