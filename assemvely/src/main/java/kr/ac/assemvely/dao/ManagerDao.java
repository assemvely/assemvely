package kr.ac.assemvely.dao;

import java.util.List;

import kr.ac.assemvely.vo.ManagerVo;

public interface ManagerDao {

	void insertposting(ManagerVo managervo);
	ManagerVo readposting(int managerbno);
	public List<ManagerVo> postlist();
}
