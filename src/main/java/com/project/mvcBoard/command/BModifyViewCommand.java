package com.project.mvcBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.project.mvcBoard.dao.BDao;
import com.project.mvcBoard.dto.BDto;


public class BModifyViewCommand implements BCommand {

	@Override
	public void execute(Model model, SqlSession sqlSession) {
		System.out.println("BModifyViewCommand");
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId"); // bId값
		BDao dao = sqlSession.getMapper(BDao.class);
		BDto dto = dao.contentFind(bId);
		
		model.addAttribute("modify_content", dto);

	}

}
