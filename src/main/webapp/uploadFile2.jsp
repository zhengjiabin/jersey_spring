<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试文件上传</title>
</head>
<body>
	<h1>Upload a File2</h1>

	<form action="http://localhost:8080/jersey_spring/service/upload/uploadFile2"
		method="post" enctype="multipart/form-data">
		username:	<input type="text" name="username"><br/>
        password:	<input type="password" name="password"><br/>
        file:<input type="file" name="file"><br/>

		<input type="submit" value="提交" />
	</form>

</body>
</html>