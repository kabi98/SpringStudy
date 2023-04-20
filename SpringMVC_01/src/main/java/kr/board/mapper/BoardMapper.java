package kr.board.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.board.entity.Board;

public interface BoardMapper {
	// 데이터베이스 연결작업(X) -> 설정(xml)
	// 게시판 전체 리스트 가져오기.
	public List<Board> getLists();
	
	// 게시물을 등록하는 메소드 정의
	public void register(Board vo);

	public Board get(int num);
	
}	
	



//	{
		// 연결객체(Connect, SuqSession 를 가져오기
		// SQL 문장 전송 : PreparedStatement : @{} 
		// SQL 결과 집합 : ResultSet : next
		// 레크드 한개씩 -> Board -> List<Board> : while()
		// return list

		
//		SqlSession session = sqlSessionFactory.openSession();
//		List<Board> list = session.selectList("getLists");
//		session.close();
//		
//		return list;
		
		
		
/*		
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
*/		
//	}
	
