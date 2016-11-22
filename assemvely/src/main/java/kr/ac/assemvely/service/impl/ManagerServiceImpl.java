package kr.ac.assemvely.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.ac.assemvely.dao.ManagerDao;
import kr.ac.assemvely.service.ManagerService;
import kr.ac.assemvely.vo.ManagerVo;


 @Service
public class ManagerServiceImpl  implements ManagerService {
	
	@Inject
	private ManagerDao managerdao;

	@Override
	public void insertposting(ManagerVo managervo) {
		managerdao.insertposting(managervo);
		
	}

	@Override
	public ManagerVo readposting(int managerbno) {
		 
		return managerdao.readposting(managerbno);
	}

	@Override
	public List<ManagerVo> postlist() {
		
		return managerdao.postlist();
	}
	
	
	



}
