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
			System.out.println("����̺� �ε�");
    	
    	}
    	catch (Exception e) { e.printStackTrace(); }   	
    }
    
    // ���� �ð� ���ϱ�
    public String getDate()
    {    	
    	try
    	{
    		// sql ���� ���� �ð��� �������� ����
    		String query = "Select now()";
    		//query = "select sysdate from dual";
    		PreparedStatement pstm = conn.prepareStatement(query);
    		rs = pstm.executeQuery();
    		
    		if( rs.next() ) { return rs.getString(1); }			
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
		return ""; // DB ���� �߻� ��, �˸��� ����
    }
    
    // �Խù� ��ȣ ���ϱ�
    public int getNext()
    {   	
    	try
    	{	// �Խù� ��ȣ ��������
    		// order..desc �� ���� �������� ���� ���� ��ȣ �����´�.
    		// bbsId �� �����̹Ƿ� �������� ����
    		String query = "select bbsId from bbs order by bbsId desc";
    		PreparedStatement pstm = conn.prepareStatement(query);
    		rs = pstm.executeQuery();
    		
    		if( rs.next() ) { return rs.getInt(1) + 1;  } // �� ���� �Խù� ��ȣ
    		
    		return 1; // ù��° �Խù��� ���			
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
		return -1; // DB ���� �߻� ��, �˸��� ����
    }
// #######################################################################################################    
    // ���� �� �ۼ��ϴ� �޼ҵ�
    public int write(String bbsTitle, String userId, String bbsContent)
    {
    	
    	System.out.println("�����ͺ��̽� �Խ��� ����ֱ�");
    	
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
    		// �� �ۼ��̶�, ���� �����ϱ� ������ 1
    		pstm.setInt(6, 1);   		
    		
    		// �� �ۼ� ���� �� 1, �ƴϸ� �ؿ��� -1 ��ȯ   		
    		return pstm.executeUpdate();	
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
		return -1; // DB ���� �߻� ��, �˸��� ����
    }
    
    // ������ ���
    public ArrayList<bbsDTO> getList(int pageNumber)
    {
    	ArrayList<bbsDTO> lists = new ArrayList<bbsDTO>();
    	
    	try
    	{	
    		// �Խñ��� �����ϴ� �� �� �� 10���� �����´�.
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
    
    // ����¡ ó��
    // �������� ���� ��Ÿ���� �޼ҵ�
    public boolean nextPage(int pageNumber)
    {  	
    	try
    	{	
    		// �Խñ��� �����ϴ� �� �� �� 10���� �����´�.
    		String query = "select * from bbs where bbsId < ? AND bbsAVailable = 1";   		
    		PreparedStatement pstm = conn.prepareStatement(query);
    		pstm.setInt(1, getNext() - (pageNumber - 1) * 10);
    		rs = pstm.executeQuery();
    		
    		if( rs.next() ) { return true; } // ���� �������� �Ѿ �� ����
    					
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
		return false; // ���� �������� �Ѿ �� ����
    }
    
    // �ϳ��� �� ������ �ҷ����� �޼ҵ�
    public bbsDTO getBbs(int bbsId)
    {
    	try
    	{	
    		// bbsId �� ������, �� ���ڿ� �ش��ϴ� ���� �ҷ��´�
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
    			
    			// bbs ��ü�� ����� �ҷ��� �Լ��� ��ȯ
    			return bbs;
    		}
    					
		}
    	catch (Exception e) { e.printStackTrace(); }
    	
    	// �ش� ���� �������� ���� ���
		return null;
    }
    
    // �� ����
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
    	
		return -1; // DB ���� �߻� ��, �˸��� ����
    }
    
    // �� ���� �޼ҵ�
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
    	
		return -1; // DB ���� �߻� ��, �˸��� ����
    }
    
}
