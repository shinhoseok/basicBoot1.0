package com.basic.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.basic.dao.UserDAO;
import com.basic.dto.UserDTO;
import com.basic.service.UserService;

import lombok.AllArgsConstructor;


@Service("userService")
@AllArgsConstructor
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	/** UserDAO */
	private final UserDAO userDAO;

	/** ID Generation */
	private final EgovIdGnrService egovIdGnrService;
	
	/** EgovPropertyService */
	private final EgovPropertyService propertiesService;
	
	public Map<String, Object> findUserList(UserDTO userDTO) throws Exception {
		LOGGER.debug("findUserList service>>>");

	    // Pagination 설정
	    userDTO.setPageUnit(propertiesService.getInt("pageUnit"));
	    userDTO.setPageSize(propertiesService.getInt("pageSize"));

	    PaginationInfo paginationInfo = new PaginationInfo();
	    paginationInfo.setCurrentPageNo(userDTO.getPageIndex());
	    paginationInfo.setRecordCountPerPage(userDTO.getPageUnit());
	    paginationInfo.setPageSize(userDTO.getPageSize());

	    userDTO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	    userDTO.setLastIndex(paginationInfo.getLastRecordIndex());
	    userDTO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

	    // 데이터 조회
	    int totalRecords = userDAO.findUserListCnt(userDTO);
	    List<UserDTO> userList = (totalRecords > 0) ? userDAO.findUserList(userDTO) : new ArrayList<>();

	    // DataTables 형식으로 데이터 매핑
	    Map<String, Object> response = new HashMap<>();
	    response.put("draw", userDTO.getDraw()); // DataTables 요청의 draw 값
	    response.put("recordsTotal", totalRecords); // 전체 데이터 수
	    response.put("recordsFiltered", totalRecords); // 필터링된 데이터 수
	    response.put("data", userList); // 데이터 목록

	    return response;
	}
}
