package com.basic.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.basic.dao.UserDAO;
import com.basic.dto.UserDTO;

import lombok.AllArgsConstructor;

@Repository("userDAO")
@AllArgsConstructor
public class UserDAOJdbc implements UserDAO {
	
	private final UserMybatis userMybatis;
	
	public List<UserDTO> findUserList(UserDTO userDTO) throws Exception {
		return userMybatis.findUserList(userDTO);
	}
	
	public Integer findUserListCnt(UserDTO userDTO) throws Exception {
		return userMybatis.findUserListCnt(userDTO);
	}
}
