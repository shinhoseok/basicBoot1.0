package com.basic.dao.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.basic.dto.UserDTO;

@Mapper
public interface UserMybatis {
	
	public List<UserDTO> findUserList(UserDTO userDTO) throws Exception;
	
	public Integer findUserListCnt(UserDTO userDTO) throws Exception;
}
