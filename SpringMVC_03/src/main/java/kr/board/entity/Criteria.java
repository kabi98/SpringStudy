package kr.board.entity;

import lombok.Data;

@Data
public class Criteria {
	private int page; // 현재 페이지번호
	private int perPageNum; // 한페이지에 보여질 게시글의 수

	public Criteria() {
		this.page = 1;
		this.perPageNum = 5; // 조정
	}
	
	// 현재 페이지의 게시글의 시작번호
	public int getPageStart() { 		// 1page 	2page	3page
		return ((page-1)*perPageNum); // 1-10		11-20	21-30
	}
	

}
