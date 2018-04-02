package com.project.mvcBoard.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface BCommand {
	// Spring에선 request를 모델 객체에 담아 보낼 수 있기 때문에 jsp와는 다르게 model 객체를 매개변수로 받는다.
	// mybatis 사용을 위해 SqlSession 객체도 함께 넘겨준다.
	public void execute(Model model, SqlSession sqlSession); 

}
