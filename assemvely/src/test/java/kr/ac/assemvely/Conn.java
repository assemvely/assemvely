package kr.ac.assemvely;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.ac.assemvely.service.ManagerService;
import kr.ac.assemvely.vo.ManagerVo;

 
 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class Conn {

	 @Inject
	  ManagerService service;
	 
	 
	 @Test
	 public void testInsert() throws Exception{
		 ManagerVo vo=new ManagerVo();
		 vo.setManagerbno(3);
		 
		 service.readposting(3);
		 
		 
	 }
	 
}
