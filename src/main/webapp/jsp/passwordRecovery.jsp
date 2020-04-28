<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title><fmt:message key="page.content.title"/></title>
    <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
    <script type="text/javascript"
            src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="part/index_header.jsp"/>
<jsp:include page="part/error_message.jsp"/>
<div class="container">
    <div class="row align-content-center">
        <form method="post" class="was-validation col-6 mt-5">
            <input type="text" value="password_recovery" name="command" hidden>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Восстановить пароль</button>
        </form>
    </div>
</div>
<jsp:include page="part/footer.jsp"/>
</body>
</html>
