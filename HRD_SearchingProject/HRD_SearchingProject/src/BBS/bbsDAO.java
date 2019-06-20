package BBS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DBConnect.DBConnect;

public class bbsDAO {

    private Connection conn = null;
    private ResultSet rs = null;

/*
    private String driver = "oracle.jdbc.driver.OracleDriver";
    private String dbURL  = "jdbc:oracle:thin:@localhost:1521:orcl";
    private String hostId = "orcl";
    private String hostPw = "12345";
*/
    
    
    public bbsDAO()
    {
    	try
    	{
    		conn = DBConnect.getConnection();
			/*
			 * String driver = "com.mysql.cj.jdbc.Driver"; String dbURL =
			 * "jdbc:mysql://3.17.133.136:3306/team1DB?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
			 * String hostId = "team1"; String hostPw = "Daewoo1team!";
			 * 
			 * Class.forName(driver); conn = DriverManager.getConnection(dbURL, hostId,
			 * hostPw);
			 */	
			System.out.println("드라이브 로드");
    	
    	}
    	catch (Exception e) { e.printStackTrace(); }   	
    }
    
    // 현재 시간 구하기
    public String getDate()
    {    	
    	try
    	{
    		// sql 에서 현재 시간을 가져오는 쿼리
    		String query = "Select now()";
    		//query = "select sysdate from dual";
    		PreparedStatement pstm = conn.prepareStatement(query);
    		rs = pstm.executeQuery();
    		
    		if( rs.next() ) { return rs.getString(1); }			
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
		return ""; // DB 오류 발생 시, 알리기 위해
    }
    
    // 게시물 번호 구하기
    public int getNext()
    {   	
    	try
    	{	// 게시물 번호 가져오기
    		// order..desc 는 가장 마지막에 쓰인 글의 번호 가져온다.
    		// bbsId 는 숫자이므로 내림차순 정렬
    		String query = "select bbsId from bbs order by bbsId desc";
    		PreparedStatement pstm = conn.prepareStatement(query);
    		rs = pstm.executeQuery();
    		
    		if( rs.next() ) { return rs.getInt(1) + 1;  } // 그 다음 게시물 번호
    		
    		return 1; // 첫번째 게시물인 경우			
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
		return -1; // DB 오류 발생 시, 알리기 위해
    }
// #######################################################################################################    
    // 실제 글 작성하는 메소드
    public int write(String bbsTitle, String userId, String bbsContent)
    {
    	
    	System.out.println("데이터베이스 게시판 집어넣기");
    	
    	try
    	{	    		
    		//query = "insert into bbs values(" + getNext() + ",?,?,?,?,1)";
    		String query = "insert into bbs values (?, ?, ?, ?, ?, ?)";
    		PreparedStatement pstm = conn.prepareStatement(query);

    		pstm.setInt(1, getNext() );
    		pstm.setString(2, bbsTitle);
    		pstm.setString(3, userId);
    		pstm.setString(4, getDate() ); // .substring(0, 10)
    		pstm.setString(5, bbsContent);
    		// 글 작성이라, 글이 존재하기 때문에 1
    		pstm.setInt(6, 1);   		
    		
    		// 글 작성 성공 시 1, 아니면 밑에서 -1 반환   		
    		return pstm.executeUpdate();	
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
		return -1; // DB 오류 발생 시, 알리기 위해
    }
    
    // 페이지 목록
    public ArrayList<bbsDTO> getList(int pageNumber)
    {
    	ArrayList<bbsDTO> lists = new ArrayList<bbsDTO>();
    	
    	try
    	{	
    		// 게시글을 존재하는 것 중 총 10개만 가져온다.
    		String query = "select * from bbs where bbsId < ? AND bbsAVailable = 1 order by bbsId desc limit 10";   		
    		PreparedStatement pstm = conn.prepareStatement(query);
    		pstm.setInt(1, getNext() - (pageNumber - 1) * 10);
    		rs = pstm.executeQuery();
    		
    		while( rs.next() )
    		{
    			bbsDTO bbs = new bbsDTO();
    			bbs.setBbsId(rs.getInt(1));
    			bbs.setBbsTitle(rs.getString(2));
    			bbs.setUserId(rs.getString(3));
    			bbs.setBbsDate(rs.getString(4));
    			bbs.setBbsContent(rs.getString(5));
    			bbs.setBbsAvailable(rs.getInt(6));  
    			lists.add(bbs);
    		}
    					
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
		return lists;
    }
    
    // 페이징 처리
    // 페이지의 수를 나타내는 메소드
    public boolean nextPage(int pageNumber)
    {  	
    	try
    	{	
    		// 게시글을 존재하는 것 중 총 10개만 가져온다.
    		String query = "select * from bbs where bbsId < ? AND bbsAVailable = 1";   		
    		PreparedStatement pstm = conn.prepareStatement(query);
    		pstm.setInt(1, getNext() - (pageNumber - 1) * 10);
    		rs = pstm.executeQuery();
    		
    		if( rs.next() ) { return true; } // 다음 페이지로 넘어갈 수 있음
    					
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
		return false; // 다음 페이지로 넘어갈 수 없음
    }
    
    // 하나의 글 내용을 불러오는 메소드
    public bbsDTO getBbs(int bbsId)
    {
    	try
    	{	
    		// bbsId 를 받으면, 그 숫자에 해당하는 글을 불러온다
    		String query = "select * from bbs where bbsId = ?";   		
    		PreparedStatement pstm = conn.prepareStatement(query);
    		pstm.setInt(1, bbsId);
    		rs = pstm.executeQuery();
    		
    		if( rs.next() )
    		{ 
    			bbsDTO bbs = new bbsDTO();
    			bbs.setBbsId(rs.getInt(1));
    			bbs.setBbsTitle(rs.getString(2));
    			bbs.setUserId(rs.getString(3));
    			bbs.setBbsDate(rs.getString(4));
    			bbs.setBbsContent(rs.getString(5));
    			bbs.setBbsAvailable(rs.getInt(6));  
    			
    			// bbs 객체를 만들어 불러온 함수에 반환
    			return bbs;
    		}
    					
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
    	// 해당 글이 존재하지 않을 경우
		return null;
    }
    
    // 글 수정
    public int update(int bbsId, String bbsTitle, String bbsContent)
    {
    	try
    	{	    		
    		String query = "update bbs set bbsTitle = ?, bbsContent = ? where bbsId = ?";
    		PreparedStatement pstm = conn.prepareStatement(query);
    		
    		pstm.setString(1, bbsTitle);
    		pstm.setString(2, bbsContent);
    		pstm.setInt(3, bbsId);

    		return pstm.executeUpdate();		
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
		return -1; // DB 오류 발생 시, 알리기 위해
    }
    
    // 글 삭제 메소드
    public int delete(int bbsId)
    {
    	try
    	{	    		
    		String query = "update bbs set bbsAvailable = 0 where bbsId = ?";
    		//String query = "delete from bbs where bbsId = ?";
    		PreparedStatement pstm = conn.prepareStatement(query);
    		
    		pstm.setInt(1, bbsId);
    		
    		return pstm.executeUpdate();		
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
		return -1; // DB 오류 발생 시, 알리기 위해
    }
    
}
