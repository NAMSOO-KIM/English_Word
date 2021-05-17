package mvc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.models.MyWordDTO;
import mvc.models.WordDAO;
import mvc.models.WordDTO;

@Service
public class WordServiceImpl implements WordService {

	@Autowired
	private WordDAO wordDAO;
	
	
	@Override
	public WordDTO getWord(long word_id) throws Exception {

		return wordDAO.getWord(word_id);
	}
	
	//나의 단어 제거
	@Override
	public void deleteMyWord(long word_id) throws Exception {
		
		wordDAO.deleteMyWord(word_id);
		
	}
	private WordServiceImpl() {
		
	}
	
	//단어 등록
	@Override
	public void insertWord(WordDTO wordDTO) throws Exception {

		wordDAO.insertWord(wordDTO);
	}

	@Override
	public List<WordDTO> getWordList() throws Exception {

		return wordDAO.getWordList();
		
	}

	@Override
	public void updateWord(WordDTO wordDTO) throws Exception {

		
	}

	@Override
	public void deleteWord(WordDTO wordDTO) throws Exception {

		
	}
	
	@Override
	public List<MyWordDTO> getWordList(String user_id) throws Exception {

		return wordDAO.getWordList(user_id);
	
	}
	
	@Override
	public String getWordCount() throws Exception {

		return wordDAO.getWordCount();
	}
	
	@Override
	public void insertMyWord(String user_id, WordDTO wordDTO) throws Exception {

		wordDAO.insertWord(user_id,wordDTO);
		
	}
	

}