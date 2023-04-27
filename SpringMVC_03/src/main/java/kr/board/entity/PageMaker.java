package kr.board.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.board.controller.HomeController;
import lombok.Data;

//                    startPage   endPage
// 페이징 처리를 만드는 클래스 ( < 1 2 3 4 5 > )  start = 1, end = 5
//페이징 처리를 만드는 클래스 (  < 6 7 8 9 10 > ) start = 6, end = 10
@Data
public class PageMaker {
	private static final Logger logger = LoggerFactory.getLogger(PageMaker.class);
	
	private Criteria cri;
	private int totalCount; // 총 게시글의 수
	private int displayPageNum=3; // 화면에 보여질 페이지의 수
	private int startPage; // 시작 페이지 번호
	private int endPage; // 끝 페이지 번호(조정)
	private boolean prev; // 이전 버튼 (true, false)
	private boolean next; // 다음 버튼 (true, false)
	
	// 총게시글의 수를 저장하는 메서드
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		pageCalc();
		
	}
	
	private void pageCalc() {
		logger.info("pageCalc displayPageNum: {}.", displayPageNum);

		// 1. 화면에 보여질 마지막 페이지 번호
		endPage = (int)(Math.ceil( cri.getPage() / (double)displayPageNum) * displayPageNum);
		// 2. 화면에 보여질 시작 페이지 번호
		startPage = (endPage - displayPageNum) + 1;
		// 3. 전체 마지막 페이지 계산
		int tempEndPage = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		// 4. 화면에 보여질 마지막 페이지 유효성 검사.
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		// 5. 이전 페이지 버튼의 존재 여부
		prev = (startPage == 1) ? false : true;
		next = (endPage < tempEndPage) ? true : false;
		
	}
}



