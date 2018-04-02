package com.project.mvcBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.project.mvcBoard.dao.BDao;
import com.project.mvcBoard.dto.BDto;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(Model model, SqlSession sqlSession) {
		System.out.println("BReplyViewCommand");
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");
		
		BDao dao = sqlSession.getMapper(BDao.class);
		BDto dto = dao.contentFind(bId); // bId로 row를 찾아 BDto 객체를 반환하는 메소드를 재활용 한다.
		
		model.addAttribute("reply_view", dto);
	}

}
