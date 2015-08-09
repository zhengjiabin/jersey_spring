<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Strict//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试文件上传</title>
</head>
<body>
	<h1>Upload a File</h1>

	<form action="http://localhost:8080/jersey_spring/service/upload/uploadFile"
		method="post" enctype="multipart/form-data">
		username:	<input type="text" name="username"><br/>
        password:	<input type="password" name="password"><br/>
        gender:		<input type="radio" name="gender" value="man">Man 
         	       	<input type="radio" name="gender" value="feman">Feman<br/>
        hobby:		<input type="checkbox" name="hobby" value="baskedball">baskedball   
            		<input type="checkbox" name="hobby" value="football">football   
            		<input type="checkbox" name="hobby" value="computer">computer   
            		<input type="checkbox" name="hobby" value="swim">swim<br/>
        job:		<select name="job">
		                <option value="teacher" selected="selected">teacher
		                <option value="farmer" >farmer
		                <option value="worker" >worker
		                <option value="student" >student
            		</select><br/>
            <hr/>
        file:		<input type="file" name="file"><br/>

		<input type="submit" value="提交" />
	</form>

</body>
</html>