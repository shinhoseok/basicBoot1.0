package com.basic.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.egovframe.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.basic.user.service.UserDAO;
import com.basic.user.service.UserService;
import com.basic.user.service.UserVO;

@Service("userServiceImpl")
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	/** UserDAO */
	@Autowired
    @Qualifier("userMapper")
	private UserDAO userDAO;

	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	public Map<String, Object> findUserList(UserVO userVO) throws Exception {
		LOGGER.debug("findUserList service>>>");
		Map<String, Object> rsltMap = new HashMap<>();
		userVO.setPageUnit(propertiesService.getInt("pageUnit"));
		userVO.setPageSize(propertiesService.getInt("pageSize"));

		// pagination setting
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userVO.getPageUnit());
		paginationInfo.setPageSize(userVO.getPageSize());

		userVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int cnt = userDAO.findUserListCnt(userVO);
		paginationInfo.setTotalRecordCount(cnt);
		List<UserVO> resultList = null;
		if(cnt > 0) {
			resultList = userDAO.findUserList(userVO);
		}
		rsltMap.put("resultList", resultList);
		rsltMap.put("resultListCnt", cnt);
		rsltMap.put("paginationInfo", paginationInfo);
		
		return rsltMap;
	}
}
