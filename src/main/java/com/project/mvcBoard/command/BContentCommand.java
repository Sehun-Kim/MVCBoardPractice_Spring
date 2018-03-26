package com.project.mvcBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.project.mvcBoard.dao.BDao;
import com.project.mvcBoard.dto.BDto;

// 글 내용을 찾는 커맨드
public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap(); // model 객체의 asMap() 메소드로 Map 객체에 model에 들어있는 값들을 키와 값으로 넣어준다.
		HttpServletRequest request = (HttpServletRequest) map.get("request"); // request를 키를 갖는 map객체의 값을 request 객체에 대입한다.
		String bId = request.getParameter("bId"); // dao 객체에서 id로 값을 찾을 id
		
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId);
		
		model.addAttribute("content_view", dto); // model 객체에 content_view 라는 이름으로 dto를 담는다.
	}

}
