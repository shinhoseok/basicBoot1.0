package com.basic.user.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.basic.user.service.UserDAO;
import com.basic.user.service.UserVO;

@Mapper
@Repository("userMapper")
public interface UserMapper extends UserDAO {
	List<UserVO> findUserList(UserVO userVO);
	
	Integer findUserListCnt(UserVO userVO);
}
