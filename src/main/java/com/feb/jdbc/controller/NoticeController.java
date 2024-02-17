package com.feb.jdbc.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feb.jdbc.service.MemberService;
import com.feb.jdbc.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;

	@PostMapping("/notice.do")
	public ModelAndView join(@RequestParam HashMap<String, String> params,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int result = noticeService.insertNotice(params);
		
		mv.addObject("resultCode", result);
		if (result == 1) {
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("성공 성공 대성공 ");
			System.out.println("성공 성공 대성공 ");
			System.out.println("성공 성공 대성공 ");
			System.out.println("-----------------------------------------------------------------------");

			mv.addObject("resultMsg", "게시글 작 성공");
		} else {
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("실패 실 대 실패  ");
			System.out.println("실패 실 대 실패  ");
			System.out.println("실패 실 대 실패  ");
			System.out.println("-----------------------------------------------------------------------");


			mv.addObject("resultMsg", "게시글 작 실패");
		}
		mv.setViewName("hello");
		return mv;
	}
	

}
