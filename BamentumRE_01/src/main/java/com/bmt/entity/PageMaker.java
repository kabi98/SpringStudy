package com.bmt.entity;

import lombok.Data;

// 페이징 처리를 만드는 클래스( < 1 2 3 4 5 >
@Data
public class PageMaker {
  private Criteria cri;
  private int totalCount; // 총 게시글의 수
  private int displayPageNum=3; // 화면에 보여질 페이지의 수
  private int startPage; // 시작페이지 번호
  private int endPage; // 끝페이지 번호(조정)
  private boolean prev; // 이전버튼(true, false)
  private boolean next; // 다음버튼(true, false)
  // 총게시글의 수를 저장하는 메서드
  public void setTotalCount(int totalCount) {
	  this.totalCount=totalCount;
	  pageCalc();
  }
  private void pageCalc() {
	 // 1. 화면에 보여질 마지막 페이지 번호
	  endPage=(int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
	 // 2. 화면에 보여질 시작 페이지 번호  
	  startPage=(endPage-displayPageNum)+1;
	 // 3. 전제 마지막 페이지 계산 
	 int tempEndPage=(int)(Math.ceil(totalCount/(double)cri.getPerPageNum())); 
	 // 4. 화면에 보여질 마지막 페이지의 유효성 검사
	 if(endPage>tempEndPage) {
		 endPage=tempEndPage;
	 }
	 // 5. 이전페이지 버튼의 존재 여부
	 prev=(startPage==1) ? false : true;
	 // 6. 다음페이지 버튼의 존재 여부
	 next=(endPage<tempEndPage) ? true : false;	 
  }
}
