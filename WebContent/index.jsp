<%--
@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    --%>
<%--
Hopper hopper = new Hopper();
String fileName = request.getParameter("filename");
if(fileName == null) fileName = "null.txt";    
System.out.println("index.jsp:" + fileName);
hopper.twitch();
--%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <!--  form action="Uploading3" method="post" -->
 <form action="Uploading3" enctype = "multipart/form-data" method="post">
  <input name="filename" type="file" />
  <input type="submit" />
 </form>
 <div>
  <p>22:54</p>
 </div>
</body>
</html>