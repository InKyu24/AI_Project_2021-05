package com.bummy.web.vo;

public class BreakVO {
	private String user_belong, breakTimeMsg;
	private int breakTime;
	private boolean breakBool;
	
	public BreakVO(String user_belong, int breakTime, String breakTimeMsg, boolean breakbool) {
		setUser_belong(user_belong);
		setBreakTime(breakTime);
		setBreakTimeMsg(breakTimeMsg);
		setBreakBool(breakbool);
	}
	
	public BreakVO(String user_belong) {
		setUser_belong(user_belong);
	}

	public String getUser_belong() {
		return user_belong;
	}
	public void setUser_belong(String user_belong) {
		this.user_belong = user_belong;
	}
	public String getBreakTimeMsg() {
		return breakTimeMsg;
	}
	public void setBreakTimeMsg(String breakTimeMsg) {
		this.breakTimeMsg = breakTimeMsg;
	}
	public int getBreakTime() {
		return breakTime;
	}
	public void setBreakTime(int breakTime) {
		this.breakTime = breakTime;
	}
	public boolean isBreakBool() {
		return breakBool;
	}
	public void setBreakBool(boolean breakBool) {
		this.breakBool = breakBool;
	}
	@Override
	public String toString() {
		return "BreakVO [user_belong=" + user_belong + ", breakTimeMsg=" + breakTimeMsg + ", breakTime=" + breakTime
				+ ", breakBool=" + breakBool + "]";
	}	
}
