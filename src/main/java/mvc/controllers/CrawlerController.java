package mvc.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import mvc.fx.ModelAndView;
import mvc.service.CrawlerService;


@Controller
public class CrawlerController {
	
	@Autowired
	CrawlerService crawlerService;
	
	@GetMapping(value="/crawler")
	public ModelAndView crawler(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String user_id = (String)session.getAttribute("user_id");
		ModelAndView mav = new ModelAndView();		
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
			
			Calendar cal = Calendar.getInstance();
			for(int i=1; i<=200; i++) {
				
				cal.add(Calendar.DATE, -1);

				String timedate= format.format(cal.getTime());
			
				crawlerService.insert("https://learn.dict.naver.com/endic/today/words.nhn?targetDate="+timedate,user_id);
				
			}

			mav.setViewName("home");
			response.sendRedirect(mav.getViewName());
			
			
		} catch (Exception e) {
			e.printStackTrace();

			
		}
		
		
		return null;
	}
}
