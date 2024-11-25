package com.basic.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends CommDefaultDTO implements Serializable {
	/** 페이징넘버 */
	private String rowNumber;
	/** 사용자 아이 */
	private String userId;
	/** 이메일 */
	private String email;
	/** 사용자 이름 */
	private String name;
	/** 핸드폰번호 */
	private String phoneNumber;
	/** 사용자 비밀번호 */
	private String password;
	/** 최근접속일 */
	private LocalDateTime lastLoginDt;
	/** 계정상태 */
	private String accountStatus;
}
