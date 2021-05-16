package mvc.models;

import java.sql.SQLException;
import java.util.List;


public interface MemberDAO {
	
	void insertMember(MemberDTO memberDTO) throws SQLException;
	
	List<MemberDTO> getMemberList() throws SQLException;
	
	int loginMember(MemberDTO memberDTO) throws SQLException;
}
