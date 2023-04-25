package kr.board.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;
import kr.board.entity.Member;


// JDBC -> MyBatis -> [MyBatis-Spring] -> Spring JPA
public interface BoardMapper {
	
	// 데이터베이스 연결작업(X) -> 설정(xml)
	// 게시판 전체 리스트 가져오기.
	public List<Board> getLists();
	
	// 게시물을 등록하는 메소드 정의
	public void register(Board vo);

	// 선택한 번호의 게시물 1개를 가져오는 메서드 정의
	public Board get(int num);
	
	// 선택한 번호의 게시물 1개를 삭제하는 메서드 정의
	@Delete("delete from reply where num = #{num} ")
	public void remove(int num);

	// 선택한 번호에 해당하는 제목과 내용을 수정하는 메서드 정의
	public void modify(Board vo);

	// 선택한 번호의 게시물 1개를 삭제하는 메서드 정의
	@Update("update reply set count=count+1 where num = #{num}")
	public void count(int num);
	
	// 회원 로그인 처리 메서드 정의
	@Select("select * from member where username=#{username} and password=#{password}")
	public Member login(Member mvo);

	// 답글의 Seq를 1씩 더해주기.
	public void replySeqUpdate(Board parent);
	
	// 답글을 insert 하기.
	public void replyInsert(Board vo);
	
	
	
}	
	
