package com.feb.jdbc.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.feb.jdbc.dao.LoginDao;
import com.feb.jdbc.entity.Member;
import com.feb.jdbc.util.Sha512Encoder;

public class LoginService {

	LoginDao loginDao;
	
	public LoginService() {}
	public LoginService(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	public boolean login(HashMap<String,String> params) {
		String memberId = params.get("memberId");
		Member member = loginDao.login(memberId);
		String memberPw = member.getPasswd(); 
		
		Sha512Encoder encoder = Sha512Encoder.getInstance();
		String passwd = params.get("passwd");
		// 사용자가 입력한 값을 암호화한 거다 
		String encodeTxt = encoder.getSecurePassword(passwd);			
		
		System.out.println(member);
		return 		StringUtils.pathEquals(memberPw, encodeTxt);

		
	}
	
}
