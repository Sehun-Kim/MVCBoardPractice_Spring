package com.project.mvcBoard.dao;

import java.util.ArrayList;
import com.project.mvcBoard.dto.BDto;

public interface BDao {
	
	public ArrayList<BDto> list();

	public void write(String bName, String bTitle, String bContent);

	// DB에 있는 제목, 이름, 내용을 수정해주는 메소드.
	public void modify(String bId, String bName, String bTitle, String bContent);

	// bId로 DB에 있는 row를 찾아 삭제하는 메소드
	public void delete(final String bId);

	// 답변내용을 DB에 넣어주는 메소드
	public void reply(String bName, String bTitle, String bContent, String bGroup, int bStep, int bIndent);
	
	// bId로 DB에 있는 row를 찾아 bHit(조회수)를 증가시키는 메소드
	public void upHit(String strId);

	// bId로 DB에 있는 row를 찾아 BDto 객체를 반환해 주는 메소드. 단, upHit는 하지 않음
	public BDto contentFind(String strId);

	// 답변의 위치를 지정하기 위한 메소드
	public void replyShape(String strGroup, String strStep);

}
