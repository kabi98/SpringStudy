package com.bmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.bmt.entity.Bmt_post;
import com.bmt.entity.Bmt_user;
import com.bmt.entity.Criteria;

public interface BoardMapper {

	// 데이터베이스 연결작업(X) -> 설정(xml)
	// 게시판 전체 리스트 가져오기.
	public List<Bmt_post> getLists(Criteria cri); 

	
	// 회원 로그인 처리 메서드 정의
	@Select("select * from bmt_user where email=#{email} and pw=#{pw}")
	public Bmt_user login(Bmt_user mvo);
	
	public int totalCount(Criteria cri);
	
	
}
