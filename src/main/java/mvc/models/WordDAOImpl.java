package mvc.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WordDAOImpl implements WordDAO {

	@Autowired
	private DataSource dataSource;
	
	
	private WordDAOImpl() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/MyWordSP");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	// 단어 등록(크롤링으로 등록)
	@Override
	public void insertWord(WordDTO wordDTO) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO word(id, member_id, question, answer) ");
		sql.append("VALUES(seq_word.nextval, ?, ?, ?) ");
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			
			ps.setString(1, wordDTO.getMember_id());
			ps.setString(2, wordDTO.getQuestion());
			ps.setString(3, wordDTO.getAnswer());
			ps.executeUpdate();
			
		}
	}

	@Override
	public int insertWord(String user_id, WordDTO wordDTO) throws SQLException{
		
		
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		
		
		sql.append("INSERT INTO my_word(id, member_id, word_id, question, answer) ");
		sql.append("VALUES(seq_word.nextval, ?, ?, ?, ?) ");
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql.toString())){
				
				ps.setString(1, user_id);
				ps.setLong(2, wordDTO.getId());
				ps.setString(3, wordDTO.getQuestion());
				ps.setString(4, wordDTO.getAnswer());
				ps.executeUpdate();
				return 1;
			}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	//나의 단어 제거
	@Override
	public void deleteMyWord(long word_id) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE my_word  ");
		sql.append("WHERE id=?  ");
		
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
			//to-do
			
			pstmt.setLong(1, word_id);
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	
	
	@Override
	public WordDTO getWord(long word_id) throws SQLException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("select id, question, answer ");
		sql.append("FROM word ");
		sql.append("where id = ? ");
		
		WordDTO wordDTO = new WordDTO();
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			  ps.setLong(1,word_id);
			  
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					
					
					wordDTO.setId(rs.getLong("id"));
					wordDTO.setQuestion(rs.getString("question"));
					wordDTO.setAnswer(rs.getString("answer"));
					
				}
			}
		} 
		return wordDTO;
	}
	
	// 단어 목록 조회
	@Override
	public List<WordDTO> getWordList() throws SQLException {
		StringBuffer sql = new StringBuffer();

	
		sql.append("select id, question, answer ");
		sql.append("FROM word ");
		
		List<WordDTO> list = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					WordDTO wordDTO = new WordDTO();
					wordDTO.setId(Integer.parseInt(rs.getString("id")));
					wordDTO.setQuestion(rs.getString("question"));
					wordDTO.setAnswer(rs.getString("answer"));
					list.add(wordDTO);
				}
			}
		} 
		return list;
		
	}
	
	@Override
	public List<MyWordDTO> getWordList(String user_id) throws SQLException {
		StringBuffer sql = new StringBuffer();

		sql.append("select id, question, answer ");
		sql.append("FROM my_word ");
		sql.append("where member_id = ? ");
		
		
		List<MyWordDTO> list = new ArrayList<>();
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			  ps.setString(1,user_id);
			  
			try (ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					
					MyWordDTO mywordDTO = new MyWordDTO();
					mywordDTO.setId(rs.getLong("id"));
					mywordDTO.setQuestion(rs.getString("question"));
					mywordDTO.setAnswer(rs.getString("answer"));
					list.add(mywordDTO);
				}
			}
		} 
		return list;
		
	}
	
	

	// 단어 수정
	@Override
	public int updateWord(WordDTO wordDTO) throws SQLException {

		return 0;
		
	}

	// 단어 삭제
	@Override
	public int deleteWord(WordDTO wordDTO) throws SQLException {

		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE t_board  ");
		sql.append("WHERE member_id=?");
		
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
			
			return pstmt.executeUpdate();
		
		}
		
	}
	@Override
	public int allDeleteWord(WordDTO wordDTO) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE   ");
		sql.append("FROM WORD; ");
		
		
		try (Connection conn = dataSource.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql.toString())){

			return pstmt.executeUpdate();
		
		}
		
	}
	
	@Override
	public String getWordCount() throws SQLException {

		StringBuffer sql = new StringBuffer();
		sql.append("select count(*)  ");
		sql.append("FROM WORD ");
		
		String row_count="" ;
		try (Connection conn = dataSource.getConnection();
				 PreparedStatement ps = conn.prepareStatement(sql.toString())){
			
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					row_count = rs.getString(1);
					
				}
			}
		} 
		return row_count;
		
	}
}
	
