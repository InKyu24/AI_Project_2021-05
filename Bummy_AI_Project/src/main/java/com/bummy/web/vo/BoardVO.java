package com.bummy.web.vo;

import java.util.Date;

public class BoardVO {
	private int level,board_articleNO,board_parentNO;
	private String board_title,board_content,board_filename,board_id;
	Date board_Date;
	
   public int getLevel() {
      return level;
   }
   public void setLevel(int level) {
      this.level = level;
   }
   public int getBoard_articleNO() {
      return board_articleNO;
   }
   public void setBoard_articleNO(int board_articleNO) {
      this.board_articleNO = board_articleNO;
   }
   public int getBoard_parentNO() {
      return board_parentNO;
   }
   public void setBoard_parentNO(int board_parentNO) {
      this.board_parentNO = board_parentNO;
   }
   public String getBoard_title() {
      return board_title;
   }
   public void setBoard_title(String board_title) {
      this.board_title = board_title;
   }
   public String getBoard_content() {
      return board_content;
   }
   public void setBoard_content(String board_content) {
      this.board_content = board_content;
   }
   public String getBoard_FileName() {
      return board_filename;
   }
   public void setBoard_FileName(String board_FileName) {
      this.board_filename = board_FileName;
   }
   public String getBoard_id() {
      return board_id;
   }
   public void setBoard_id(String board_id) {
      this.board_id = board_id;
   }
   public Date getBoard_Date() {
      return board_Date;
   }
   public void setBoard_Date(Date board_Date) {
      this.board_Date = board_Date;
   }
   
   @Override
   public String toString() {
      return "BoardVO [level=" + level + ", board_articleNO=" + board_articleNO + ", board_parentNO=" + board_parentNO
            + ", board_title=" + board_title + ", board_content=" + board_content + ", board_FileName="
            + board_filename + ", board_id=" + board_id + ", board_Date=" + board_Date + "]";
   }

}