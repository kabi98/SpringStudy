package kr.board.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.board.entity.Board;

public class BoardDAO {

	// 게시판 전체 리스트 가져오기.
	public List<Board> getLists(){
		
		// 게시판 3개를 생성하고 List에 저장하기
		Board vo1 = new Board(1, "게시판연습", "게시판연습", "관리자", new Date(), 0);
		Board vo2 = new Board(2, "게시판연습", "게시판연습", "문경수", new Date(), 0);
		Board vo3 = new Board(3, "게시판연습", "게시판연습", "홍길동", new Date(), 0);
		
		List<Board> list = new ArrayList<Board>();
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		
		for(int i=0; i< list.size(); i++) {
			System.out.println(list.get(i).getNum() + list.get(i).getWriter());
		}
		
		
		return list;
	}
}