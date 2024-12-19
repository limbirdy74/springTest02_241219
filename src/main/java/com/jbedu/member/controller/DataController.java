package com.jbedu.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jbedu.member.dto.MemberDto;

@Controller
public class DataController {
	
	@RequestMapping(value = "/data")
	public String data(Model model) {
		String name = "홍길동";
		
		model.addAttribute("memberName", name); //name 값을 model 객체에 담음 -> data.jsp 에 전달
		
		return "data";
	}
	
	
	@RequestMapping(value = "/datamodel")
	public ModelAndView datamodel(Model model) {  //ModelAndView 클래스를 이용한 데이터 전달
		//ModelAndView -> view에 전달해줄 값과 view(jsp)파일 이름을 동시에 전달해주는 객체
		int age = 27;  // 홍길동의 마이
		
		ModelAndView mv = new ModelAndView(); // ModelAndView 객체 생성
		mv.addObject("age", age);  // Model 객체에 데이터 담기
		mv.setViewName("datamodel"); // view 이름(jsp 이름)
		
		return mv;
	}
	
	@RequestMapping(value = "/login")  //login.jsp 를 실행시켜주는 역할만!!
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value = "/login2")
	public String login2() {
		
		return "login2";
	}
	
	@RequestMapping(value = "/login3")
	public String login3() {
		
		return "login3";
	}
	
	@RequestMapping(value = "/join")
	public String join() {
		
		return "join";
	}
	
	@RequestMapping(value = "/join2")
	public String join2() {
		
		return "join2";
	}

	@RequestMapping(value = "/confirmID")  // 클라이언트의 로그인 요청을 여기서 catch(파라미터 값도 함께)
	public String confirmID(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
//		if (mid.equals("tiger") && mpw.equals("12345")) {
//			model.addAttribute("idcheck", "memberOk");
//		} else {
//			model.addAttribute("idcheck", "memberNo");
//		}
		
		model.addAttribute("loginid", mid);
		model.addAttribute("loginpw", mpw);
		
		return "confirmID";
	}
	
	@RequestMapping(value = "/checkID", method = RequestMethod.POST)  // POST 로 보냈으므로 
	public String checkID(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		if (mid.equals("tiger") && mpw.equals("12345")) {
			model.addAttribute("idcheck", "memberOk");
			model.addAttribute("loginid", mid);
			model.addAttribute("loginpw", mpw);
		} else {
			model.addAttribute("idcheck", "memberNo");
		}
		
		return "checkID";
	}
	
	@RequestMapping(value = "/checkID2") 
	public String checkID2(@RequestParam("mid") String mid, @RequestParam("mpw") String mpw, Model model) { // 어노테이션을 이용해서 값 받기. 자동완성을 위에 불러와야 함
		
//		String mid = request.getParameter("mid");
//		String mpw = request.getParameter("mpw");
		
		if (mid.equals("tiger") && mpw.equals("12345")) {
			model.addAttribute("idcheck", "memberOk");
			model.addAttribute("loginid", mid);
			model.addAttribute("loginpw", mpw);
		} else {
			model.addAttribute("idcheck", "memberNo");
		}
		
		return "checkID";
	}
	
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		//MemberDto memberDto = new MemberDto(request.getParameter("mid")....); //이런 식으로도 가능
		MemberDto memberDto = new MemberDto(mid, mpw, mname, memail);
		
		model.addAttribute("memberDto", memberDto);  // model에 dto 실어보내기
		
//		model.addAttribute("mid", mid);
//		model.addAttribute("mpw", mpw);
//		model.addAttribute("mname", mname);
//		model.addAttribute("memail", memail);
		
		return "joinOk";
	}	
	
	@RequestMapping(value = "/joinOk2")  // 제일 많이 쓰는 방법. 이름을 같게 해야함
	public String joinOk2(MemberDto memberDto, Model model) {  // 예전에는 memberDto 이름으로 해야만되었지만. 지금은 아무거나 해도 잘 되는듯
		//joinOk2(@ModelAttribute("ddd") MemberDto memberDto, ... memberDto 객체의 이름을 ddd로 변경하여 model 객체에도 ddd 이름으로 memberDto객체를 실어보냄
//		model.addAttribute("memberDto", memberDto);  // 이것도 생략가능. 자동으로 실어보냄. 주석을 풀어도 되고 없어도됨. DTO 클래스의 이름이 너무 길어서 불편할 떄 이름 변경
		
		return "joinOk";
	}
	
	@RequestMapping(value = "/page/{pagevalue}")  //javascript 등으로 넘어오는 매개 변수 형식
	public String pagevalue(@PathVariable String pagevalue, Model model) {
		model.addAttribute("page", pagevalue);
		return "page";
	}
	
	@RequestMapping(value = "/redirect")
	public String redirect() {
		
		return "redirect";
	}
	
	@RequestMapping(value = "/redirectGood")
	public String redirectGood(@RequestParam("number") String number, Model model) {
		
		int number_int = Integer.parseInt(number);  // 문자 -> 정수 변환
		
		if(number_int > 10) {
			model.addAttribute("number", number);
//			return "redirect:redirectOk";  // 강제이동. 주소창에 새로운 요청으로 하는 방식. 전달된 값을 받을 수 없다
			// 새로운 요청을 넣어라. 게시판 글쓰고 새글 포함해서 리스트 받아 올 경우. 사용하면 됨
			return "redirectOk";  // jsp 파일을 실행시켜라
		} else {
			model.addAttribute("number", "0000");
			return "redirect:redirectNo";
		}
			
	}
	
	@RequestMapping(value = "/redirectOk")
	public String redirectOk(Model model) {

		model.addAttribute("number", "안녕하세요");
		return "redirectOk";
		
	}	
}
