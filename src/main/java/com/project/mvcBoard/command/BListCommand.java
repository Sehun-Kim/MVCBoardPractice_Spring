package com.project.mvcBoard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.project.mvcBoard.dao.BDao;
import com.project.mvcBoard.dto.BDto;

// DB의 data를 가져와서 dto 객체에 넣고 그것을 jsp 페이지에 보내주는 커맨드
public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		
		model.addAttribute("list", dtos); // view에서 받아 쓰기 위해 model에 넣어줌
	}

}
