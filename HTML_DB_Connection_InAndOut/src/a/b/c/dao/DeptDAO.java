package a.b.c.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import a.b.c.dbcp.Context;
import a.b.c.model.DeptDTO;

public class DeptDAO {

	DeptDTO memberDTO = new DeptDTO();

	// 멤버 DTO에서 인스턴스 변수들을 다 가지고온다.
	public void insert(DeptDTO memberDTO) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Context context = new Context();
		String sql = null;

		try {

			// 커넥션인스턴스를 커넥션풀이랑 연결시켜줌
			DataSource dataSource = context.basicDataSource;
			connection = dataSource.getConnection();

			sql = "Insert into dept(deptno,dname,loc) values (?,?,?)";
			preparedStatement = connection.prepareStatement(sql);

			// 물음표의 순서대로 넣을 값을입력 맴버DTO에서 값을 받아옴

			preparedStatement.setInt(1, memberDTO.getDeptno());
			preparedStatement.setString(2, memberDTO.getDname());
			preparedStatement.setString(3, memberDTO.getLoc());
			
			int count = preparedStatement.executeUpdate();
			if (count > 0) {
				System.out.println("부서입력이 완료되었습니다.");
				connection.commit();

			} else {
				connection.rollback();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}