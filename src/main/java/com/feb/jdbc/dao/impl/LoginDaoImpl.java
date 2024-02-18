package com.feb.jdbc.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import com.feb.jdbc.entity.*;
import com.feb.jdbc.dao.LoginDao;


public class LoginDaoImpl extends JdbcDaoSupport implements LoginDao{
	
	
	
	@Override
	public Member login(String memberId) {
		String sql = "select * from lecture.member where member_id = ?";
		Member member = null; 
		try {
		member = getJdbcTemplate().queryForObject(sql, new Object[] {memberId}, new MemberRowMapper());
		}catch(EmptyResultDataAccessException e) {                                    
			throw new EmptyResultDataAccessException("예상하는 값이랑 달라서 에러가 발생했죠 ",1,e);
		}
		return member;
	}

}
