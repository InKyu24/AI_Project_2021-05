package com.bummy.web.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bummy.web.dao.NoticeDAO;
import com.bummy.web.vo.NoticeVO;

@Service
public class NoticeService {
	@Autowired
	NoticeDAO noticeDAO;

	public List<NoticeVO> listNoti() {
		return noticeDAO.listNoti();
	}

	public void noticeWrite(Map<String, Object> notiMap) {
		noticeDAO.noticeWrite(notiMap);	
	}

	public NoticeVO viewNoti(int notice_notiNO) {
		return noticeDAO.viewNoti(notice_notiNO);
	}

	public void modNoti(Map<String, Object> notiMap) {
		noticeDAO.modNoti(notiMap);
	}

	public void removeNoti(NoticeVO noticeVO) {
		noticeDAO.removeNoti(noticeVO);
		
	}
}