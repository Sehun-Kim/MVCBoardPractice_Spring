package com.project.mvcBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.project.mvcBoard.dto.BDto;
import com.project.mvcBoard.util.Constant;

public class BDao {

	JdbcTemplate template; // JdbcTemplate 접근용

	public BDao() {
		template = Constant.template; // 
	}

	public ArrayList<BDto> list(){
		System.out.println("BDao.list");
		
		// JdbcTemplate를 이용한 접근 방식
		String query = "select * from mvc_board order by bGroup desc, bStep asc";
		// JdbcTemplate는 .query() 메소드를 사용해서 쿼리를 작성하면 해당 쿼리를 실행해서 커맨드객체에 값을 담아 반환한다.
		return (ArrayList<BDto>)this.template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}

	public void write(final String bName, final String bTitle, final String bContent){
		System.out.println("BDao.write");

		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0 )";
				// data 삽입을 위한 쿼리문 생성 bId는 시퀀스를 사용해서 삽입시 하나씩 증가하도록, bGroup은 id값과 동일한 값을 적용한다. bName, bTitle, bContent를 제외한 모든 값은 0으로 초기화해준다.

				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				
				return preparedStatement;
			}
		});
	}

	public BDto contentView(String strId){
		System.out.println("BDao.contentView");

		upHit(strId);
		
		// JdbcTemaplate
		String query = "select * from mvc_board where bId = " + strId;
		return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class)); // BDto 객체 하나		
	}

	// DB에 있는 제목, 이름, 내용을 수정해주는 메소드.
	public void modify(final String bId, final String bName, final String bTitle, final String bContent){
		System.out.println("BDao.modify");
		
		String query = "update mvc_board set bName = ?, bTitle = ?, bContent = ? where bId = ? ";	
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				preparedStatement.setInt(4, Integer.parseInt(bId));
			}
		});	
	}

	// bId로 DB에 있는 row를 찾아 삭제하는 메소드
	public void delete(final String bId){
		System.out.println("BDao.delete");
		
		String query = "delete from mvc_board where bId = ?";
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setInt(1, Integer.parseInt(bId));	
			}
		});
	}

	// 답변내용을 DB에 넣어주는 메소드
	public void reply(final String bId, final String bName, final String bTitle, final String bContent, final String bGroup, final String bStep, final String bIndent){
		System.out.println("BDao.reply");

		replyShape(bGroup, bStep);
		
		String query = "insert into mvc_board (bId, bName, bTitle, bContent, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, ?, ?, ?)";

		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setString(1, bName);
				preparedStatement.setString(2, bTitle);
				preparedStatement.setString(3, bContent);
				preparedStatement.setInt(4, Integer.parseInt(bGroup)); // 답변을 달 bGroup
				preparedStatement.setInt(5, Integer.parseInt(bStep) + 1); // 답변을 달 bStep보다 1 증가
				preparedStatement.setInt(6, Integer.parseInt(bIndent) + 1); // 답변을 달 bIndent 보다 1 증가
			}
		});
	}

	// bId로 DB에 있는 row를 찾아 bHit(조회수)를 증가시키는 메소드
	public void upHit(final String strId){ // 익명 클래스에서 값을 사용할 것이기 때문에 final로 변경되지 않게 선언해줌
		System.out.println("BDao.upHit");		
		
		String query = "update mvc_board set bHit = bHit + 1 where bId = ?";
		this.template.update(query, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement prepareStatement) throws SQLException {
				prepareStatement.setInt(1, Integer.parseInt(strId));
			}
		}); // 익명클래스로 구현
	}

	// bId로 DB에 있는 row를 찾아 BDto 객체를 반환해 주는 메소드. 단, upHit는 하지 않음
	public BDto contentFind(String strId){
		System.out.println("BDao.contentFind");
		
		String query = "select * from mvc_board where bId = " + strId;
		return this.template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));
	}

	// 답변의 위치를 지정하기 위한 메소드
	public void replyShape(final String strGroup, final String strStep){
		System.out.println("BDao.replyShape");

		// DB table의 같은 bGroup을 가진 파라미터로 받은 strStep(원 게시물의 bStep 값)보다 큰 row의 bStep을 1씩 증가 시킴
		// 즉 답변이 게시판에 추가될 하나의 공간을 마련
		String query = "update mvc_board set bStep = bStep + 1 where bGroup = ? and bStep > ?";

		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement preparedStatement) throws SQLException {
				preparedStatement.setInt(1, Integer.parseInt(strGroup));
				preparedStatement.setInt(2, Integer.parseInt(strStep));	
			}
		});
	}

}
