package com.basic.dao;

import java.util.List;

import com.basic.dto.UserDTO;

public interface UserDAO {
	
	public List<UserDTO> findUserList(UserDTO userDTO) throws Exception;
	
	public Integer findUserListCnt(UserDTO userDTO) throws Exception;
}
