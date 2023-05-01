package kr.board.entity;

import lombok.Data;

@Data
public class Criteria {
  private int page; //현재 페이지 번호
  private int perPageNum; // 한페이지에 보여질 게시글의 수
  // 검색 기능에 필요한 변수
  private String type; // 이름(writer), 제목(title), 내용(content)
  private String keyword; // 검색어. 
  
  public Criteria() {
	  this.page=1;
	  this.perPageNum=5; // 조정
  }
  // 현재 페이지의 게시글의 시작번호
  public int getPageStart() {     // 1page  2page    3page
	  return (page-1)*perPageNum; // 0~9    10~19    20~29
  }
}
