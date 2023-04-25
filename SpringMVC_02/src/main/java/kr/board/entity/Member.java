package kr.board.entity;

import lombok.Data;

@Data
public class Member {
	
	private int idx; // 일련번호(자동증가컬럼)
	private String username;  // 아이디
	private String password;  // 비밀번호 : 12345(평문) -> a#12ssa(암호화)
	private String name;
	private String email;

}
