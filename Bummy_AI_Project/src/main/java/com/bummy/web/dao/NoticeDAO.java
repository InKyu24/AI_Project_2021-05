package com.bummy.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bummy.web.vo.NoticeVO;



@Repository
public class NoticeDAO {
	
	@Autowired
	SqlSession sqlSession;

	public List<NoticeVO> listNoti() {
		return sqlSession.selectList("mapper.notice.selectAllNotice");
	}

	public NoticeVO viewNoti(int notice_notiNO){
		return sqlSession.selectOne("mapper.notice.selectNoti", notice_notiNO);
	}

	public void noticeWrite(Map<String, Object> notiMap) {
		sqlSession.insert("mapper.notice.noticeWrite", notiMap);
	}

	public void modNoti(Map<String, Object> notiMap) {
		sqlSession.update("mapper.notice.modNoti", notiMap);
	}

	public void removeNoti(NoticeVO noticeVO) {
		sqlSession.delete("mapper.notice.removeNoti", noticeVO);
	}
}