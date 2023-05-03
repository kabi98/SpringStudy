package kr.board.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;
import kr.board.entity.Criteria;
import kr.board.entity.Member;
// JDBC -> MyBatis -> [Mybatis-Spring] -> Spring JPA
public interface BoardMapper {   
	//데이터베이스 연결작업(X) -> 설정(xml)
	// 게시판 전체 리스트 가져오기
	public List<Board> getLists(Criteria cri); 
    // 게시물을 등록하는 메서드 정의
	public void register(Board vo);
	// 선택한 번호에 해당하는 게시물 1개를 가져오는 메서드 정의
	public Board get(int num);
	// 선택한 번호의 게시물 1개를 삭제하는 메서드 정의
	@Update("update reply set bdelete=1 where num=#{num}")
	public void remove(int num);
	// 선택한 번호에 해당하는 제목과 내용을 수정하는 메서드 정의	
	public void modify(Board vo);
	// 조회수 누적 메서드
	@Update("update reply set count=count+1 where num=#{num}")
	public void count(int num);
	// 회원 로그인 처리 메서드 정의
	@Select("select * from member where username=#{username} and password=#{password}")
	public Member login(Member mvo);
	
	public void replySeqUpdate(Board parent);
	public void replyInsert(Board vo);
	
//	@Select("select count(*) from reply")
	public int totalCount(Criteria cri);
	
	@Select("select * from board order by num desc")
	public List<Board> ajaxList();
	
}

/*
 * public class SqlSessionFactory implements BoardMapper,,,,,{
 * 
 * public List<Board> getLists(){ SqlSession
 * session=sqlSessionFactory.openSession(); List<Board>
 * list=session.selectList("getLists"); session.close(); return list;
 * 
 * } }
 * 
 * BoardMapper mapper=new SqlSessionFactory(); List<Board>
 * list=mapper.getLists();
 */







