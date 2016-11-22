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
				// 파일 업로드 처리 완료.
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
	 //내경로" /resources/EH1/popup/quic_photo/uploadfile  ->여기안에 사진 저장! 사실 잘몰라요!!
	 //db에 editortest라는 파일안에 bno(시퀀스증가),title varchar2(20),editorpath(4000)있어요
	 //popup안에있는
	//다중파일업로드 파일 업로드만 담당
	@RequestMapping("/multipleUpload")
	public void multipleUpload(HttpServletRequest request, HttpServletResponse response){
	 
		try {
	         //파일정보W
	         String sFileInfo = "";
	         //파일명을 받는다 - 일반 원본파일명
	         String filename = request.getHeader("file-name");
	 
	         //파일 확장자
	         String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
	         //확장자를소문자로 변경
	         filename_ext = filename_ext.toLowerCase();
	         //파일 기본경로
	         String dftFilePath = request.getSession().getServletContext().getRealPath("/");
	         //파일 기본경로 _ 상세경로
	         String filePath = dftFilePath + "resources" + File.separator + "popup" + File.separator+"uploadfile";//사진들어가는 경로
	         File file = new File(filePath);
	         if(!file.exists()) {
	            file.mkdirs();
	         }
	         String realFileNm = "";
	         SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	         String today= formatter.format(new java.util.Date());
	         realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
	         String rlFileNm = filePath + realFileNm;
	         ///////////////// 서버에 파일쓰기 ///////////////// 
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
	         ///////////////// 서버에 파일쓰기 /////////////////
	         // 정보 출력
	         sFileInfo += "&bNewLine=true";
	         // img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
	         sFileInfo += "&sFileName="+ filename;
	         sFileInfo += "&sFileURL="+"/resources/popup/uploadfile"+realFileNm; //사진들어가는경로인데 나는 resources안에 popup안에 uploadfile있다
	         PrintWriter print = response.getWriter();
	         print.print(sFileInfo);
	         print.flush();
	         print.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@RequestMapping("/readposting")
	private String readposting(@ModelAttribute("managerbno") int managerbno,Model model){
		ManagerVo managervo=managerservice.readposting(managerbno);
		model.addAttribute("READ",managervo);
		return "readposting";
	}
	
	@RequestMapping("/deleteposting")
	private String deleteposting(@ModelAttribute("managerbno")int managerbno){
		
		managerservice.deleteposting(managerbno);
		return "postlist";
	}
	
}
