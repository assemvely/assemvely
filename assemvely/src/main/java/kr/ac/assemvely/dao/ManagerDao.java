package kr.ac.assemvely.dao;

import java.util.List;

import kr.ac.assemvely.vo.ManagerVo;

public interface ManagerDao {

	public void insertposting(ManagerVo managervo);
	public ManagerVo readposting(int managerbno);
	public List<ManagerVo> postlist();
	public void deleteposting(int managerbno);
}
