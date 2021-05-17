package mvc.service;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mvc.models.CrawlerDAO;
import mvc.models.WordDTO;

@Service
public class CrawlerServiceImpl implements CrawlerService {

	@Autowired
	private CrawlerDAO crawlerDAO;

	
	
	@Override
	public void insert(String url,String user_id) {
		
		Document doc = null;
		
		try {
		
			doc = Jsoup.connect(url).get();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("ul.lst_ul");
		
		
		Iterator<Element> ie1= element.select("em.words").iterator();
		Iterator<Element> ie2 = element.select("p.txt_ct2").iterator();

		// to-do
		while(ie1.hasNext()) {
			WordDTO wordDTO = new WordDTO();
			String s_ie1 =ie1.next().text().trim();
			String s_ie2= ie2.next().text().trim();
			
			if(s_ie1 ==null) continue;
			
			System.out.println(s_ie1);
			System.out.println(s_ie2);
			
			wordDTO.setMember_id(user_id);
			wordDTO.setQuestion(s_ie1);
			wordDTO.setAnswer(s_ie2);

			try {
				System.out.println();
				crawlerDAO.insertWord(wordDTO);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}
}
