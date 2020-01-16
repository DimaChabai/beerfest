<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:bundle basename="pagecontent">
    <html xmlns="http://www.w3.org/1999/html">
    <head>
        <title>Title</title>
        <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
        <script type="text/javascript"
                src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body class="d-flex flex-column h-100">
    <jsp:include page="jsp/index_header.jsp"/>
    <fmt:message key="email_help_text"/>
    <jsp:include page="jsp/footer.jsp"/>
    </body>
    </html>
</fmt:bundle>