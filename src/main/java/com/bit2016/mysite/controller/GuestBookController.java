package com.bit2016.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bit2016.mysite.service.GuestBookService;
import com.bit2016.mysite.vo.GuestBookVo;

@Controller
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping("/guestbook")
	public String list(Model model){
		List<GuestBookVo> list = guestBookService.getList();
		model.addAttribute("list",list);
		return "guestbook/list";
	}
	
	@RequestMapping("/insert")
	public String insert(@ModelAttribute GuestBookVo vo){
		guestBookService.insert(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping("guestbook/deleteform/{no}")
	public String deleteForm(@PathVariable("no")Long no, Model model){
		model.addAttribute("no",no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping("guestbook/delete")
	public String delete(@RequestParam(value= "no", required=true, defaultValue = "")Long no,
						 @RequestParam(value="password", required=true, defaultValue="")String password){
		
		guestBookService.delete(no, password);
		
		return "redirect:/guestbook";	
	}
}
