package com.basic.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class CommDefaultDTO implements Serializable {
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
	private int draw = 1;
	/** 한페이지에 보여줄 로우 */
	private int pageUnit = 10;
	/** 페이지리스트에 게시되는 페이지건수 */
	private int pageSize = 10;
	/** firstIndex */
	private int firstIndex = 1;
	/** lastIndex */
	private int lastIndex = 1;
	/** 한페이지당 게시되는 게시물 건수 */
	private int recordCountPerPage = 10;
	/** 등록아이디 */
	private String regId;
	/** 등록일 */
	private String regDt;
	/** 수정아이디 */
	private String modId;
	/** 수정일 */
	private String modDt;
	/** 삭제여부 */
	private String delYn;
}
