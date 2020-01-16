<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="index_header_start.jsp"/>
<fmt:bundle basename="pagecontent">
<div class="row">
        <form class="form-inline col" method="post" action="${pageContext.request.contextPath}/index">
            <input type="text" name="command" value="toRegistration" hidden>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="registration_button" /></button>
        </form>

        <form class="form-inline col" method="post" action="${pageContext.request.contextPath}/index">
            <input type="text" name="command" value="toLogin" hidden>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="login_button" /></button>
        </form>
</div>
</fmt:bundle>
<jsp:include page="index_header_end.jsp"/>
