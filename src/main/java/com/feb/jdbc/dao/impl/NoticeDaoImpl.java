package com.feb.jdbc.dao.impl;

import java.util.HashMap;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.feb.jdbc.dao.NoticeDao;
import com.feb.jdbc.util.Sha512Encoder;

public class NoticeDaoImpl  extends JdbcDaoSupport implements NoticeDao {

	@Override
	public int insertNotice(HashMap<String, String> params) {
		Sha512Encoder encoder = Sha512Encoder.getInstance();
	
		String sql = "INSERT INTO lecture.notice"
				+ "(author, content, wrt_dtm) "
				+ "VALUES('" + params.get("author")
				+ "', '" + params.get("content")
				+ "', '" + params.get("wrt_dtm")+"')";
				
		return getJdbcTemplate().update(sql);
	}
	



}
