package com.feb.jdbc.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.feb.jdbc.entity.Member;
import com.feb.jdbc.entity.Notice;


@Repository
public interface NoticeDao {

//	public HashMap<String, Object> findNotice(int noticeIdx);
//	public Notice findNotice2(int noticeIdx);
//	
//	public ArrayList<Member> NoticeList(HashMap<String, Object> params);
//
//	public int join(HashMap<String, String> params);
//	
//	public int checkId(HashMap<String, String> params);
//	
//	public HashMap<String, Object> getMemberById(HashMap<String, String> params);
//
	
	public int  insertNotice(HashMap<String, String> params);
}
