package com.project.mvcBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.mvcBoard.command.BModifyViewCommand;
import com.project.mvcBoard.command.BCommand;
import com.project.mvcBoard.command.BContentCommand;
import com.project.mvcBoard.command.BDeleteCommand;
import com.project.mvcBoard.command.BListCommand;
import com.project.mvcBoard.command.BModifyCommand;
import com.project.mvcBoard.command.BReplyCommand;
import com.project.mvcBoard.command.BReplyViewCommand;
import com.project.mvcBoard.command.BWriteCommand;

// Controller 역할을 하기위한 어노테이션
// jsp에서는 uri의 확장자를 통해 servlet mapping으로 요청을 처리했다
// dispatcher가 요청을 찾아 오토스캔을 통해 컨트롤러를 찾아 @RequestMapping 어노테이션을 찾아 메소드를 실행한다.
@Controller
@RequestMapping("/board")
public class BController {
	
	BCommand command; // command 객체를 다른 메소드들에서 사용하기 위해 선언만 해둠 
	
	@RequestMapping("/list")
	public String list(Model model){
		System.out.println("BController.list");
		
		command = new BListCommand(); // 게시물들을 가져오는 커맨드
		command.execute(model);
		
		return "board/list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model){ // 작성하는 화면일 뿐이기에 커맨드 실행 x 
		System.out.println("BController.wirte_view");
		
		return "board/write_view";
	}
	
	// request객체를 파라미터로 받는 이유는 작성된 form의 데이터를 받기 위함
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model){ 
		System.out.println("BController.write");
		
		model.addAttribute("request", request); // 커맨드에서 request객체에 닮긴 값을 처리하기위해 model에 request 객체를 담아줌 
		
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list"; // controller에서 다른 페이지로 다시 갈 때 쓰는 키워드
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model){
		System.out.println("BController.content_view");
		
		model.addAttribute("request", request);
		
		command = new BContentCommand();
		command.execute(model);
		
		return "board/content_view";
		
	}
	
	@RequestMapping("/modify_form")
	public String modify_form(HttpServletRequest request, Model model){
		System.out.println("BController.modify_view");
		
		model.addAttribute("request", request);
		
		command = new BModifyViewCommand();
		command.execute(model);
		
		return "board/modify_form";
	}
	
	// modify의 경우 수정한 form data를 전송해야 하기 때문에 전송방식을 post 방식으로 하였다. (default는 get)
	@RequestMapping(method=RequestMethod.POST, value="/modify") 
	public String modify(HttpServletRequest request, Model model){
		System.out.println("BController.modify");
		
		model.addAttribute("request", request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model){
		System.out.println("BController.reply_view");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "board/reply_view";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/reply")
	public String reply(HttpServletRequest request, Model model){
		System.out.println("BController.reply");

		model.addAttribute("request", request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model){
		System.out.println("BController.delete");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}

}
