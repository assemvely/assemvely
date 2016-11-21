package kr.ac.assemvely.vo;

import java.sql.Date;

public class ManagerVo {

	private int managerbno;
	private String id;
	private String title;
	private String managerimg;
	private String posting;
	private Date writedate;
	
	public ManagerVo(){
		
	}
	
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public int getManagerbno() {
		return managerbno;
	}
	public void setManagerbno(int managerbno) {
		this.managerbno = managerbno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getManagerimg() {
		return managerimg;
	}
	public void setManagerimg(String managerimg) {
		this.managerimg = managerimg;
	}
	public String getPosting() {
		return posting;
	}
	public void setPosting(String posting) {
		this.posting = posting;
	}
	
}
