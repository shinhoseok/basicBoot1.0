package com.basic.service.impl;

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
		Map<String, Object> rsltMap = new HashMap<>();
		userDTO.setPageUnit(propertiesService.getInt("pageUnit"));
		userDTO.setPageSize(propertiesService.getInt("pageSize"));

		// pagination setting
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userDTO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userDTO.getPageUnit());
		paginationInfo.setPageSize(userDTO.getPageSize());

		userDTO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userDTO.setLastIndex(paginationInfo.getLastRecordIndex());
		userDTO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int cnt = userDAO.findUserListCnt(userDTO);
		paginationInfo.setTotalRecordCount(cnt);
		List<UserDTO> resultList = null;
		if(cnt > 0) {
			resultList = userDAO.findUserList(userDTO);
		}
		rsltMap.put("resultList", resultList);
		rsltMap.put("resultListCnt", cnt);
		rsltMap.put("paginationInfo", paginationInfo);
		
		return rsltMap;
	}
}
