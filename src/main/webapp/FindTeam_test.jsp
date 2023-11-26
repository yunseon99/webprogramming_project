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
	
	Team_info[] infoa= new Team_info[2];
	infoa[0] = new Team_info();
	infoa[0].setcheck(false);
	infoa[0].setclass_name("1");
	infoa[0].setcount(0);
	infoa[0].settotal(1);
	infoa[0].setintroduction("1");
	infoa[0].setrequirement("1");
	infoa[0].setuser("1");
	infoa[1] = new Team_info();
	infoa[1].setcheck(true);
	infoa[1].setclass_name("2");
	infoa[1].setcount(0);
	infoa[1].settotal(2);
	infoa[1].setintroduction("2");
	infoa[1].setrequirement("2");
	infoa[1].setuser("2");
	request.setAttribute("search_name", "123");
	request.setAttribute("info",infoa);

%>
<jsp:forward page="FindTeam.jsp"></jsp:forward>
</body>
</html>