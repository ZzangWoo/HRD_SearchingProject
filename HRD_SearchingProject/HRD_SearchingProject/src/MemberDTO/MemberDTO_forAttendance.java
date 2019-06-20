package MemberDTO;

public class MemberDTO_forAttendance {
	
	private String date; // 날짜
	private String chk; // 출석여부 (한글)
	private String startTime; // 출근시간
	private String endTime; // 퇴근시간
	private int chkNum; // 출석여부 (숫자)
	
	public MemberDTO_forAttendance() {
		super();
	}

	public MemberDTO_forAttendance(String date, String chk, String startTime, String endTime, int chkNum) {
		super();
		this.date = date;
		this.chk = chk;
		this.startTime = startTime;
		this.endTime = endTime;
		this.chkNum = chkNum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getChk() {
		return chk;
	}

	public void setChk(String chk) {
		this.chk = chk;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getChkNum() {
		return chkNum;
	}

	public void setChkNum(int chkNum) {
		this.chkNum = chkNum;
	}
	
}
