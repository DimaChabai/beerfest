<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
    <script type="text/javascript"
            src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="index_header.jsp"/>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger" role="alert">
            ${errorMessage}
    </div>
</c:if>
<fmt:bundle basename="pagecontent">
<div class="container">
    <form method="post" class="needs-validation" novalidate>
        <input type="text" value="login"  name="command" hidden>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" required>
            <small id="emailHelp" class="form-text text-muted"><fmt:message key="email_help_text" /></small>
            <div class="invalid-feedback">
                <fmt:message key="invalid_email_text" />
            </div>
        </div>
        <div class="form-group">
            <label for="password"><fmt:message key="password_label"/></label>
            <input type="password" class="form-control" id="password" name="password" value="" required>
            <div class="invalid-feedback">
                <fmt:message key="invalid_password_text"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary btn-block"> <fmt:message key="accept_button"/></button>
    </form>
</div>
</fmt:bundle>
<jsp:include page="footer.jsp"/>
</body>
</html>
