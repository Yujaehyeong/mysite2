package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	
	
	public List<GuestbookVo> getGuestbookList() {
		
		
		return guestbookDao.getList();
	}
	
	public Boolean createGuestbook(GuestbookVo guestbookVo) {

		return guestbookDao.insertGuestbook(guestbookVo);
	}
	
	public Boolean deleteGuestbook(GuestbookVo guestbookVo) {

		return guestbookDao.deleteGuestbook(guestbookVo);
	}
}
