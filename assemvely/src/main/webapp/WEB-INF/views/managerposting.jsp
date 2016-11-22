<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/EH1/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript">
var oEditors = [];
$(function(){
					nhn.husky.EZCreator.createInIFrame({
						oAppRef: oEditors,
						elPlaceHolder: "smarteditor",
						//SmartEditor2Skin.html 파일이 존재하는 경로
						sSkinURI: "/resources/Editor/SmartEditor2Skin.html",	
						htParams : {
							// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseToolbar : true,				
							// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseVerticalResizer : true,		
							// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
							bUseModeChanger : true,			
							fOnBeforeUnload : function(){
								
							}
						}, 
						fOnAppLoad : function(){
							//기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
							oEditors.getById["smarteditor"].exec("PASTE_HTML", ["포스팅"]);
						},
						fCreator: "createSEditor2"
					}); 	
					

$("#save").click(function(){
	oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
	$("#frm").submit();
})
 
});
 
</script>
</head>

<body>


<form id="frm" action="./insert" method="post" enctype="multipart/form-data" >
<table width="100%">
		
	
 
		<tr>
			<td>제목</td>
			<td><input type="text" id="title"  name="title"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				 
				<textarea rows="10" cols="30" id="smarteditor" name="smarteditor" style="width:766px; height:412px;"> 
				</textarea>
			 
				
			</td>
			
			
		</tr>
		
			<tr>
			<td>베너이미지 설정</td>
			<td> <input type="file" name="imageFile"></td>
		</tr>	
		
		<tr>
			<td colspan="2">
				<input type="button" id="save" value="저장"/>
				<input type="button" value="취소"/>
			</td>
		</tr>
</table>


</form>
 
</body>
</html>