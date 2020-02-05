<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body class="d-flex flex-column h-100">
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<jsp:include page="../part/default_header.jsp"/>
<%--@TODO названия сообщений--%>
<fmt:message key="page.error.request_from"/> ${pageContext.errorData.requestURI} <fmt:message key="page.error.is_failed"/>
<br/>
<fmt:message key="page.error.servlet_name"/> ${pageContext.errorData.servletName}
<br/>
<fmt:message key="page.error.status_code"/> ${pageContext.errorData.statusCode}
<br/>
<fmt:message key="page.error.exception"/> ${pageContext.exception}
<br/>
<fmt:message key="page.error.message_from_exception"/> ${pageContext.exception.message}
<jsp:include page="../part/footer.jsp"/>
</body>
</html>