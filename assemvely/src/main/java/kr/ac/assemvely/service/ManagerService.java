package kr.ac.assemvely.service;

import java.util.List;

import kr.ac.assemvely.vo.ManagerVo;

public interface ManagerService {

	void insertposting(ManagerVo managervo);
	ManagerVo readposting(int managerbno);
	public List<ManagerVo> postlist();
}
