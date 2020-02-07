<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${locale}" scope="session"/>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <fmt:bundle basename="pagecontent">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><fmt:message key="page.content.title"/></a>
        </fmt:bundle>
    </nav>
</header>
