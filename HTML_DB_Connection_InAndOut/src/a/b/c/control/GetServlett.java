package a.b.c.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;

import a.b.c.model.DeptDTO;

@WebServlet("/GetServlett")
public class GetServlett extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		int deptno1 = Integer.parseInt(request.getParameter("deptno"));
		DeptDTO memberDTO = new DeptDTO();
		memberDTO.setDeptno(deptno1);

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		ResultSet resultSet = null;

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			
			//기존 컨텍스트를 사용하면안되고 새로 정의해야함 아직 배움X
			Context context = new InitialContext();
			
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();

			sql = "select deptno, dname, loc from dept ";
			sql += " where deptno = ?";
			preparedStatement = connection.prepareStatement(sql);

			// 물음표의 순서대로 넣을 값을입력 DeptDTO에서 값을 받아옴
			preparedStatement.setInt(1, deptno1);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int deptno = resultSet.getInt("deptno");
				String dname = resultSet.getString("dname");
				String loc = resultSet.getString("loc");
				out.println(deptno + " " + dname + " " + loc + "<br/>");
			}
		} catch (Exception e) {
			out.println("레코드가 존재하지 않습니다.");
			e.printStackTrace();
		
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
