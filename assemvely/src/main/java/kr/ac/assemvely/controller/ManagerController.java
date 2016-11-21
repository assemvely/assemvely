package kr.ac.assemvely.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import kr.ac.assemvely.service.ManagerService;
import kr.ac.assemvely.vo.ManagerVo;
 
@Controller
@RequestMapping("/manager")
public class ManagerController {

	
	@Inject
	private ManagerService managerservice;
	
	@RequestMapping(value="/posting")
	public String posting(Locale locale){
		
		return "managerposting";
	}
	
	 @RequestMapping(value="/insert",method=RequestMethod.POST)
	 public String save(@ModelAttribute("title") String title, @RequestParam MultipartFile imageFile,HttpServletRequest request,Model model) throws IllegalStateException, IOException{
	  
		 
		 MultipartHttpServletRequest imgrequest=(MultipartHttpServletRequest)request;
			Map<String, MultipartFile> files = ((MultipartRequest) imgrequest).getFileMap();
			CommonsMultipartFile cmf = (CommonsMultipartFile) files.get("imageFile");
			String savePath =request.getServletContext().getRealPath("/resources/managerimg");  
			 String random=UUID.randomUUID().toString();
			String realfilename=cmf.getOriginalFilename()+random;
			 String realPath=savePath+"/"+realfilename;
		  
			 File file = new File(realPath);
				// ���� ���ε� ó�� �Ϸ�.
				cmf.transferTo(file);
		
	
		 ManagerVo managervo=new ManagerVo();
		 managervo.setId("aaa");
		 managervo.setTitle(title);
		 managervo.setManagerimg(realfilename);
		 managervo.setPosting(request.getParameter("smarteditor"));
		 managerservice.insertposting(managervo);
		 List<ManagerVo> managerlist=managerservice.postlist();
		 model.addAttribute("LIST",managerlist);
		 return "postlist";
	 }
	 //�����" /resources/EH1/popup/quic_photo/uploadfile  ->����ȿ� ���� ����! ��� �߸����!!
	 //db�� editortest��� ���Ͼȿ� bno(����������),title varchar2(20),editorpath(4000)�־��
	 //popup�ȿ��ִ�
	//�������Ͼ��ε� ���� ���ε常 ���
	@RequestMapping("/multipleUpload")
	public void multipleUpload(HttpServletRequest request, HttpServletResponse response){
	 
		try {
	         //��������W
	         String sFileInfo = "";
	         //���ϸ��� �޴´� - �Ϲ� �������ϸ�
	         String filename = request.getHeader("file-name");
	 
	         //���� Ȯ����
	         String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
	         //Ȯ���ڸ��ҹ��ڷ� ����
	         filename_ext = filename_ext.toLowerCase();
	         //���� �⺻���
	         String dftFilePath = request.getSession().getServletContext().getRealPath("/");
	         //���� �⺻��� _ �󼼰��
	         String filePath = dftFilePath + "resources" + File.separator + "popup" + File.separator+"uploadfile";//�������� ���
	         File file = new File(filePath);
	         if(!file.exists()) {
	            file.mkdirs();
	         }
	         String realFileNm = "";
	         SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	         String today= formatter.format(new java.util.Date());
	         realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
	         String rlFileNm = filePath + realFileNm;
	         ///////////////// ������ ���Ͼ��� ///////////////// 
	         InputStream is = request.getInputStream();
	         OutputStream os=new FileOutputStream(rlFileNm);
	         int numRead;
	         byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
	         while((numRead = is.read(b,0,b.length)) != -1){
	            os.write(b,0,numRead);
	         }
	         if(is != null) {
	            is.close();
	         }
	         os.flush();
	         os.close();
	         ///////////////// ������ ���Ͼ��� /////////////////
	         // ���� ���
	         sFileInfo += "&bNewLine=true";
	         // img �±��� title �Ӽ��� �������ϸ����� ��������ֱ� ����
	         sFileInfo += "&sFileName="+ filename;
	         sFileInfo += "&sFileURL="+"/resources/popup/uploadfile"+realFileNm; //�������°���ε� ���� resources�ȿ� popup�ȿ� uploadfile�ִ�
	         PrintWriter print = response.getWriter();
	         print.print(sFileInfo);
	         print.flush();
	         print.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@RequestMapping("/readposting")
	private String readpoasting(@ModelAttribute("managerbno") int managerbno,Model model){
		System.out.println("����");
		ManagerVo managervo=managerservice.readposting(managerbno);
		model.addAttribute("READ",managervo);
		return "readposting";
	}
	
}