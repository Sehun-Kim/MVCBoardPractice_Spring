package com.project.mvcBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.project.mvcBoard.dao.BDao;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(Model model, SqlSession sqlSession) {
		System.out.println("BDeleteCommand");
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bId = request.getParameter("bId");
		
		BDao dao = sqlSession.getMapper(BDao.class);
		dao.delete(bId);
	}

}
