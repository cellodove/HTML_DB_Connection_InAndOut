package a.b.c.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import a.b.c.model.DeptDTO;

import a.b.c.dao.DeptDAO;

@WebServlet("/SendServlett")
public class SendServlett extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//html에서 입력된 데이터를 받아온다.
		request.setCharacterEncoding("utf-8");
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		String dname = request.getParameter("dname");
		String loc = request.getParameter("loc");

		//입력받은 데이터를 DTO에 넣어준다.
		DeptDTO dto = new DeptDTO();
		dto.setDeptno(deptno);
		dto.setDname(dname);
		dto.setLoc(loc);
		
		//입력받은 데이터를 DB에 저장하기위해 DTO에 저장된 데이터를 DAO에insert메소드에 넣어준다.
		DeptDAO memberDAO = new DeptDAO();
		memberDAO.insert(dto);
		
		////////////////////넣은값 html출력/////////////////////
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(deptno);
		out.println(dname);
		out.println(loc);
		////////////////////////////////////////////////////
	}
}
