package mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mvc.models.MemberDTO;
import mvc.service.MemberService;

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
	
	@PostMapping(value="/registerAction")
	public ModelAndView registerAction(MemberDTO memberDTO) throws Exception {
		ModelAndView mav = new ModelAndView();
		try {
			memberService.insertMember(memberDTO);
			mav.setViewName("redirect:login");
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("result");
			mav.addObject("msg", "글 등록에 실패하였습니다.");
			mav.addObject("url", "javascript:history.back();");
		}
		return mav;
	}
	@GetMapping(value="/insert")
	public ModelAndView insert() throws Exception {
		return new ModelAndView("insert");
	}
	
	@RequestMapping(value="/mvc/myword/register")
	public ModelAndView register() throws Exception {
		System.out.println(3);
		return new ModelAndView("register");
	}
	
	
	/*
	MemberService memberService = MemberServiceImpl.getInstance();
	
	@Override
	public ModelAndView handleRquestInternal(HttpServletRequest request, HttpServletResponse response) {
		
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
			mav.setViewName("redirect:login");
			mav.addObject("msg", "success");

		} catch (Exception e) {
			e.printStackTrace();

			mav.addObject("msg", "湲� �벑濡앹뿉 �떎�뙣�븯���뒿�땲�떎.");
			mav.addObject("url", "javascript:history.back();");
		}
		return mav;
	}
	 */
	
	

}
