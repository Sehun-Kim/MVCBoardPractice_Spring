package com.project.mvcBoard.command;


import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.project.mvcBoard.dao.BDao;

// DB의 data를 가져와서 dto 객체에 넣고 그것을 jsp 페이지에 보내주는 커맨드
public class BListCommand implements BCommand {

	@Override
	public void execute(Model model, SqlSession sqlSession) {
		System.out.println("BListCommand");
		
		// SqlSession 객체의 getMapper 메소드로 BDao 인터페이스 구현
		BDao dao = sqlSession.getMapper(BDao.class);
		model.addAttribute("list", dao.list()); // view에서 받아 쓰기 위해 model에 넣어줌
	
	}

}
