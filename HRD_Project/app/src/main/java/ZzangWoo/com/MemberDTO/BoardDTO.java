package ZzangWoo.com.MemberDTO;

public class BoardDTO {

    private int bbsID;
    private String bbsTitle;
    private String userID;
    private String bbsDate;

    public BoardDTO() {
        super();
    }

    public BoardDTO(int bbsID, String bbsTitle, String userID, String bbsDate) {
        this.bbsID = bbsID;
        this.bbsTitle = bbsTitle;
        this.userID = userID;
        this.bbsDate = bbsDate;
    }

    public int getBbsID() {
        return bbsID;
    }

    public void setBbsID(int bbsID) {
        this.bbsID = bbsID;
    }

    public String getBbsTitle() {
        return bbsTitle;
    }

    public void setBbsTitle(String bbsTitle) {
        this.bbsTitle = bbsTitle;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBbsDate() {
        return bbsDate;
    }

    public void setBbsDate(String bbsDate) {
        this.bbsDate = bbsDate;
    }
}