package kr.ac.assemvely.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.ac.assemvely.dao.ManagerDao;
import kr.ac.assemvely.vo.ManagerVo;

@Repository
public class ManagerDaoImpl implements ManagerDao {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="kr.ac.assemvely.mappers.ManagerMapper";

	@Override
	public void insertposting(ManagerVo managervo) {
	  sqlSession.insert(namespace+".insert",managervo);
		
	}

	@Override
	public ManagerVo readposting(int managerbno) {
		 
		return sqlSession.selectOne(namespace+".selectone",managerbno);
	}
	
	@Override
	public List<ManagerVo> postlist(){
		return sqlSession.selectList(namespace+".selectlist");
	}
	
}
