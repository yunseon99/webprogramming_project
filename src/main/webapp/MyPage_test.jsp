<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="java.util.*" %>
<%@ page import="com.findteam.*" %>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
	
	MyPage_info[] infoa= new MyPage_info[3];
	infoa[0] = new MyPage_info();
	infoa[0].setapplicant("1");
	infoa[0].setclass_name("1");
	infoa[0].setphonenumber("1111111111111111111111111111111");
	
	infoa[1] = new MyPage_info();
	infoa[1].setapplicant("2");
	infoa[1].setclass_name("2");
	infoa[1].setphonenumber("2");

	infoa[2] = new MyPage_info();
	infoa[2].setapplicant("2");
	infoa[2].setclass_name("2");
	infoa[2].setphonenumber("2");
	request.setAttribute("info",infoa);

%>
<jsp:forward page="MyPage.jsp"></jsp:forward>
</body>
</html>