package com.project.mvcBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.project.mvcBoard.dao.BDao;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(Model model, SqlSession sqlSession) {
		System.out.println("BWriteCommand");
		
		Map<String, Object> map = model.asMap(); // model을 Map형태로 변환
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		BDao dao = sqlSession.getMapper(BDao.class);
		dao.write(bName, bTitle, bContent);
		
	}

}
