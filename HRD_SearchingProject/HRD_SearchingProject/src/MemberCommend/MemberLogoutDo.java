package MemberCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutDo implements QueryCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("로그아웃 페이지");
		
		HttpSession hs = request.getSession();
		
		if (hs != null) hs.invalidate();
		
		request.setAttribute("url", "/index.jsp");
		
	}

}
