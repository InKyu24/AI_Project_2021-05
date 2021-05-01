package com.bummy.web.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bummy.web.dao.BoardDAO;
import com.bummy.web.vo.BoardVO;

@Service
public class BoardService {
	@Autowired
	BoardDAO boardDAO;

	public List<BoardVO> listArticles() {
		return boardDAO.listArticles();
	}

	public void boardWrite(Map<String, Object> articleMap) {
		boardDAO.boardWrite(articleMap);	
	}

	public BoardVO viewArticle(int board_articleNO) {
		return boardDAO.viewArticle(board_articleNO);
	}

	public void modArticle(Map<String, Object> articleMap) {
		boardDAO.modArticle(articleMap);
	}

	public void removeArticle(BoardVO boardVO) {
		boardDAO.removeArticle(boardVO);
		
	}
}