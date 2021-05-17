package mvc.models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	@Override
	public List<MemberDTO> getMemberList() throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select name, password, zipcode, phone_number ");
		sql.append("from member ");
		
		
		List<MemberDTO> list = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					MemberDTO memberDTO = new MemberDTO();
					
					memberDTO.setUser_id(rs.getString("user_id"));
					memberDTO.setName(rs.getString("name"));
					memberDTO.setPassword(rs.getString("password"));
					
					
					list.add(memberDTO);
				}
			}
		} 
		return list;
	}

	@Override
	public void insertMember(MemberDTO memberDTO) throws SQLException {
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO member(id, user_id, password, name) ");
		sql.append("VALUES(seq_member.nextval, ?, ? , ?) ");

		Object[] args= {
				memberDTO.getUser_id(),
				memberDTO.getPassword(),
				memberDTO.getName()
		};
		jdbcTemplate.update(sql.toString(),args);
		
	}

	@Override
	public int loginMember(MemberDTO memberDTO) throws SQLException {

		String runP = "{ call member_login(?, ?, ?) }";
		
		try (Connection conn = dataSource.getConnection();
			 CallableStatement callableStatement = conn.prepareCall(runP.toString())){
			
			
			callableStatement.setString(1, memberDTO.getUser_id());
			callableStatement.setString(2, memberDTO.getPassword());
			callableStatement.registerOutParameter(3, java.sql.Types.INTEGER);
			callableStatement.executeUpdate();	
			
			int check = callableStatement.getInt(3);
			if(check == 0) {
				
				return 0;
				
			}
			else {
				
				
				return 1;
				
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;

		
	
	}
	
}

