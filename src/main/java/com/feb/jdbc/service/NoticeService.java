package com.feb.jdbc.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.feb.jdbc.dao.NoticeDao;

@Service
public class NoticeService {
	
	@Autowired
	 NoticeDao noticeDao;
	
	
	

	public int insertNotice(HashMap<String, String> params) {
		return noticeDao.insertNotice(params);
	}


}
