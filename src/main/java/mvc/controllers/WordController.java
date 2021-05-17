package mvc.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.fx.ModelAndView;
import mvc.models.MemberDTO;
import mvc.models.MyWordDTO;
import mvc.models.WordDTO;
import mvc.service.WordService;

@Controller
public class WordController {

	@Autowired
	private WordService wordService; 
	
	@GetMapping(value="/home")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		// 세션 객체 받아서 처리
			HttpSession session = request.getSession(true);
			
			MemberDTO memberDTO = new MemberDTO();
			String user_id = (String)session.getAttribute("user_id");
			
			memberDTO.setUser_id(user_id);
			
		
			try {
				
				String result = wordService.getWordCount();
				session.setAttribute("word_result", result);
				

			} catch (Exception e) {
				e.printStackTrace();

				
			}
			
			
			return new ModelAndView("/WEB-INF/views/home.jsp");
	}
	
	
	@RequestMapping(value="/practice")
	public ModelAndView practice(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		ModelAndView mav = new ModelAndView();
		
		try {
			List<WordDTO> list = wordService.getWordList();
			
			mav.setViewName("practice");
			mav.addObject("list", list);

			String viewName = mav.getViewName();
			Map<String, Object> model = mav.getModel();
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;

		
	}
	
	@RequestMapping(value="/mywordpage")
	public ModelAndView myplace(HttpServletRequest request, HttpServletResponse response) {
		// 세션 객체 받아서 처리
		HttpSession session = request.getSession(true);
		
		
		String user_id = (String)session.getAttribute("user_id");
		ModelAndView mav = new ModelAndView();
		
		try {

			List<MyWordDTO> list = wordService.getWordList(user_id);
			
			mav.setViewName("/WEB-INF/views/mywordpage.jsp");
			mav.addObject("list", list);
			String viewName = mav.getViewName();
			Map<String, Object> model = mav.getModel();
			for(String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;

	}
	
	@PostMapping(value="/myplaceInsertAction")
	public ModelAndView myplaceInsertAction(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);

		String user_id= (String)session.getAttribute("user_id");
		String word_id =request.getParameter("id");
		
		try {
			
			WordDTO wordDTO = wordService.getWord(Integer.parseInt(word_id));
			
			wordService.insertMyWord(user_id, wordDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping(value="/myplaceDeleteAction")
	public ModelAndView myplaceDeleteAction(HttpServletRequest request, HttpServletResponse response) {


		HttpSession session = request.getSession(true);
		ModelAndView mav = new ModelAndView();
		String word_id =request.getParameter("id");
		String user_id = (String)session.getAttribute("user_id");
		
		try {
				
			wordService.deleteMyWord(Long.parseLong(word_id));
			mav.setViewName("mywordpage");
			response.sendRedirect(mav.getViewName());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
}
