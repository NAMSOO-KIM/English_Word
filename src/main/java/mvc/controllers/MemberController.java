package mvc.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import mvc.fx.ModelAndView;
import mvc.models.MemberDTO;
import mvc.service.MemberService;
import mvc.service.WordService;


@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	WordService wordService;
	
	
	@GetMapping(value="/login")
	public ModelAndView login() throws Exception {
		return new ModelAndView("/WEB-INF/views/login.jsp");
		
	}
	
	@PostMapping(value="/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(true);
		
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setUser_id(user_id);
		memberDTO.setPassword(password);
		
		
		ModelAndView mav = new ModelAndView();
		try {

			
			int result =memberService.loginMember(memberDTO);
			
			if (result == 1) {

				
				session.setAttribute("user_id",user_id);
				
				System.out.println("로그인 성공");
				mav.setViewName("home");
				response.sendRedirect(mav.getViewName());
			
				
			}
			else if(result == 0) {
				
				System.out.println("로그인 실패");
				mav.setViewName("login");
				response.sendRedirect(mav.getViewName());
				
			}
			else if(result == -1) {
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();

			mav.addObject("msg", "글 등록에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
		}
		return null;
		
	}
	
	@GetMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(true);
		session.invalidate();
		ModelAndView mav = new ModelAndView();
		try {
			System.out.println("로그아웃 성공");
			mav.setViewName("login");
			response.sendRedirect(mav.getViewName());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	
	
	@GetMapping(value="/register")
	public ModelAndView register() throws Exception {
		
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
			System.out.println("회원가입 성공");
			mav.setViewName("login");
			response.sendRedirect(mav.getViewName());
			//mav.addObject("msg", "success");

		} catch (Exception e) {
			e.printStackTrace();

			//mav.addObject("msg", "글 등록에 실패하였습니다.");
			//mav.addObject("url", "javascript:history.back();");
		}
		
		return null;
		
		 
		 
	}
	
	

	
}
