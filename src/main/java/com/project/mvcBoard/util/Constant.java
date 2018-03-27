package com.project.mvcBoard.util;

import org.springframework.jdbc.core.JdbcTemplate;

public class Constant {
	// 어디서든 JdbcTemplate를 쓸 수 있도록 static 필드로 선언함
	public static JdbcTemplate template;
}
