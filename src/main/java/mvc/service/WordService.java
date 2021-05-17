package mvc.service;

import java.util.List;

import mvc.models.MyWordDTO;
import mvc.models.WordDTO;

public interface WordService {
	
	void insertWord(WordDTO wordDTO) throws Exception;
	
	void insertMyWord(String user_id, WordDTO wordDTO) throws Exception;

	WordDTO getWord(long word_id) throws Exception;
	
	List<WordDTO> getWordList() throws Exception;

	void updateWord(WordDTO wordDTO) throws Exception;
	
	void deleteWord(WordDTO wordDTO) throws Exception;
	
	List<MyWordDTO> getWordList(String user_id) throws Exception;
	
	String getWordCount() throws Exception;

	void deleteMyWord(long word_id) throws Exception;
	
}
