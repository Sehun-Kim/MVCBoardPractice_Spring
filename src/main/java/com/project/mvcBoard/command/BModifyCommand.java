package com.project.mvcBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.project.mvcBoard.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model, SqlSession sqlSession) {
		System.out.println("BModifyCommand");
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		
		BDao dao = sqlSession.getMapper(BDao.class);
		dao.modify(bId, bName, bTitle, bContent);

	}

}
