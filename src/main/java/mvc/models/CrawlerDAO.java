package mvc.models;

import java.sql.SQLException;
import java.util.List;

public interface CrawlerDAO {
	
	void insertWord(WordDTO wordDTO) throws SQLException;
	
}
