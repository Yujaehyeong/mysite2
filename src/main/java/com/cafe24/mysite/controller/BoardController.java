package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService BoardService;

	@RequestMapping("list")
	public String list(Model model) {

		List<BoardVo> list = BoardService.getBoardList();
		model.addAttribute("list", list);
		
		return "board/list";
	}

	@RequestMapping("modify")
	public String modify(
			Model model,
			@RequestParam(value="no", required=true, defaultValue="0") Long boardNo) {
				
				BoardVo boardVo = BoardService.getBoardByNo(boardNo);
				model.addAttribute("vo", boardVo);
				
		return "board/modify";
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modify( @ModelAttribute BoardVo boardVo) {
				
		BoardService.modify(boardVo);
		
		return "redirect:/board/view?no="+boardVo.getNo();
	}
	
	@RequestMapping("view")
	public String view(
			Model model,
			@RequestParam(value="no", required=true, defaultValue="0") Long boardNo) {
		
		BoardVo boardVo = BoardService.getBoardByNo(boardNo);
		model.addAttribute("vo", boardVo);
		
		return "board/view";
	}
	
	@RequestMapping("write")
	public String write(
			Model model,
			@RequestParam(value = "groupNo", required = true, defaultValue = "-1") int groupNo,
			@RequestParam(value = "orderNo", required = true, defaultValue = "0") int orderNo,
			@RequestParam(value = "depth", required = true, defaultValue = "0") int depth
			) {
		
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("depth", depth);
		
		return "board/write";
	}
	
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String write(
			Model model, 
			@ModelAttribute BoardVo boardVo,
			HttpSession session) {
			
		Long userNo = ((UserVo) session.getAttribute("authUser")).getNo();
		if(boardVo.getGroupNo() == -1) {
			boardVo.setUserNo(userNo);
			BoardService.createBoard(boardVo);
		}else {
			BoardService.createReplyBoard(boardVo);
		}
		
		return "redirect:/board/list";
		
	}
	
//	@RequestMapping("delete")
//	public String delete(
//			@RequestParam(value="no", required=true, defaultValue="0") Long boardNo) {
//		
//		BoardVo boardVo = BoardService.deleteBoard(boardNo);
//		model.addAttribute("vo", boardVo);
//		
//		return "redirect:/board/list";
//	}
}
