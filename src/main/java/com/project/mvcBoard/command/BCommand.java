package com.project.mvcBoard.command;

import org.springframework.ui.Model;

public interface BCommand {
	
	public void execute(Model model); // Spring에선 request를 모델 객체에 담아 보낼 수 있기 때문에 jsp와는 다르게 model 객체를 매개변수로 받는다.

}
