package com.bummy.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.bummy.web.vo.MemberVO;

public interface MemberDAO {

	void signup(MemberVO memberVO) ;
	
}
