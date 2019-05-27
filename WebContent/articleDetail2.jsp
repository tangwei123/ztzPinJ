<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<p>有图片上传的表单提交<p>
	<form action="<%=request.getContextPath() %>/article?action=doEditArticle2&id=${requestScope.articleInfo.getId()}" method="post" enctype="multipart/form-data">
		文章标题：<input type="text" name="title" value="${requestScope.articleInfo.getTitle()}" /><br />
		文章描述：<textarea name="des">${requestScope.articleInfo.getDes()}</textarea><br />
		文章图片:<input type="file" name="myfile" /><br />
		文章内容：<textarea name="content">${requestScope.articleInfo.getContent()}</textarea><br />
		文章排序：<input type="text" name="sort" value="${requestScope.articleInfo.getSort()}"/><br />
		<input type="submit" value="提交">
	</form>
</body>
</html>