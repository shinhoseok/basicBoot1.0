package com.basic.common.service;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommDefaultVO {
	/** 검색조건 */
	private String searchCondition = "";
	/** 검색Keyword */
	private String searchKeyword = "";
	/** 정렬명 */
	private String sortSubject = "";
	/** 정렬오름/내림 */
	private String sortDescend = "";
	/** 현재페이지 */
	private int pageIndex = 1;
	/** 페이지갯수 */
	private int pageUnit = 10;
	/** 페이지사이즈 */
	private int pageSize = 10;
	/** firstIndex */
	private int firstIndex = 1;
	/** lastIndex */
	private int lastIndex = 1;
	/** recordCountPerPage */
	private int recordCountPerPage = 10;
	/** 등록아이디 */
	private String regId;
	/** 등록일 */
	private LocalDateTime regDt;
	/** 수정아이디 */
	private String modId;
	/** 수정일 */
	private LocalDateTime modDt;
	/** 삭제여부 */
	private String delYn;
}
