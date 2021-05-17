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
import mvc.service.MemberService;
import mvc.service.WordService;

/*
	/myword/login=mvc.controllers.Memberlogin
	/myword/loginAction=mvc.controllers.MemberloginAction
	/myword/home=mvc.controllers.home
	/myword/register=mvc.controllers.MemberRegister
	/myword/registerAction=mvc.controllers.MemberRegisterAction
	/myword/insert=mvc.controllers.wordInsert
	/myword/insertAction=mvc.controllers.wordInsertAction
	/myword/practice=mvc.controllers.wordPractice
	/myword/ranking=mvc.controllers.wordRanking
	/myword/crawler=mvc.controllers.crawler
	/myword/idOverlap=mvc.controllers.idOverlapCheck
	/myword/exam=mvc.controllers.wordList
	/myword/myplace=mvc.controllers.myplace

 */

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	WordService wordService;
	
	
	@GetMapping(value="/login")
	public ModelAndView login() throws Exception {
		System.out.println("하이");
		return new ModelAndView("/WEB-INF/views/login.jsp");
		
	}
	
	@PostMapping(value="/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(true);
		
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		System.out.println("loginAction 중");
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUser_id(user_id);
		memberDTO.setPassword(password);
		
		
		ModelAndView mav = new ModelAndView();
		try {
			System.out.println(memberDTO.getName());
			System.out.println(memberDTO.getId());
			System.out.println(memberDTO.getUser_id());
			
			int result =memberService.loginMember(memberDTO);
			System.out.println(result);
			if (result == 1) {

				
				session.setAttribute("user_id",user_id);
				
				
				mav.setViewName("home");
				response.sendRedirect(mav.getViewName());
				//mav.addObject("msg", "success");
				
			}
			else if(result == 0) {
				
				mav.setViewName("home");
				response.sendRedirect(mav.getViewName());
				//mav.addObject("msg", "failure");
			}
			else if(result == -1) {
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();

			mav.addObject("msg", "글 등록에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
		}
		return mav;
		
	}
	
	@GetMapping(value="/register")
	public ModelAndView register() throws Exception {
		System.out.println(3);
		return new ModelAndView("/WEB-INF/views/register.jsp");
	}
	

	
	@PostMapping(value="/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
		
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUser_id(user_id);
		memberDTO.setPassword(password);
		memberDTO.setName(name);
		
		
		ModelAndView mav = new ModelAndView();
		try {
			memberService.insertMember(memberDTO);
			System.out.println("controller오냐");
			mav.setViewName("login");
			response.sendRedirect(mav.getViewName());
			mav.addObject("msg", "success");

		} catch (Exception e) {
			e.printStackTrace();

			mav.addObject("msg", "글 등록에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
		}
		
		return mav;
		//return new ModelAndView("/WEB-INF/views/login.jsp");
		//return null;
		 
		 
	}
	
	
	@GetMapping(value="/home")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		// 세션 객체 받아서 처리
			HttpSession session = request.getSession(true);
			
			MemberDTO memberDTO = new MemberDTO();
			String user_id = (String)session.getAttribute("user_id");
			System.out.println("home 왔셈");
			memberDTO.setUser_id(user_id);
			
		
			try {
				
				String result = wordService.getWordCount();
				session.setAttribute("word_result", result);
				

			} catch (Exception e) {
				e.printStackTrace();

				
			}
			
			
			return new ModelAndView("/WEB-INF/views/home.jsp");
	}
	
	/*
	@GetMapping(value="/practice")
	public ModelAndView practice() {
		return new ModelAndView("/WEB-INF/views/pracice.jsp");
	}	
		//return null;
	*/
	
	
	@RequestMapping(value="/practice")
	public ModelAndView practice(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		System.out.println("여긴 오나?");
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
			
			//dispatcher.forward(request, response);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mav;
		//return null;
		
	}
	
	@RequestMapping(value="/mywordpage")
	public ModelAndView myplace(HttpServletRequest request, HttpServletResponse response) {
		// 세션 객체 받아서 처리
		HttpSession session = request.getSession(true);
		
		
		String user_id = (String)session.getAttribute("user_id");
		ModelAndView mav = new ModelAndView();
		
		try {

			List<MyWordDTO> list = wordService.getWordList(user_id);
			System.out.println("myplace 오냐");
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
			System.out.println("성공");
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
			mav.setViewName("/WEB-INF/views/mywordpage.jsp");
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

	
}
