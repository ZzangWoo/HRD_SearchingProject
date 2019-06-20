package MemberCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MemberDAO.MemberDAO;

public class MemberCheckedID implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("아이디 체크");
		
		String userID = request.getParameter("userID");
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int rs = dao.idCheck(userID);
		
		System.out.println(userName);
		
		// 아이디 체크한 결과 setAtrribute에 저장
		// rs + ""한거는 rs가 int형이라 String으로 바꿔주기 위한거
		request.setAttribute("resultAfterIDCheck", rs + "");
		
		if (rs == 1) {
			System.out.println("중복아이디");
		} else {
			System.out.println("사용할 수 있는 아이디");
			request.setAttribute("userID", userID);
			request.setAttribute("userName", userName);
			request.setAttribute("userEmail", userEmail);
			request.setAttribute("Checked", "Checked");
		}
		
		request.setAttribute("url", "/idChecked.jsp");
		
	}

}
