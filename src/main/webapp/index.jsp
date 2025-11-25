<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    java.util.Enumeration<String> attrNames = session.getAttributeNames();

    if (!attrNames.hasMoreElements()) {
%>
        <p>Session null</p>
<%
    } else {
        while (attrNames.hasMoreElements()) {
            String name = attrNames.nextElement();
            Object value = session.getAttribute(name);
%>
            <p><b><%= name %></b> : <%= value %></p>
<%
        }
    }
%>


</body>
</html>