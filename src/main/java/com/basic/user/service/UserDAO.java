package com.basic.user.service;

import java.util.List;

public interface UserDAO {
	List<UserVO> findUserList(UserVO userVO);
	Integer findUserListCnt(UserVO userVO);
}
