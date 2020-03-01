<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmt:message key="page.content.title"/></title>
    <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="../part/main_header.jsp"/>
<jsp:include page="../part/message.jsp"/>
<jsp:include page="../part/error_message.jsp"/>
<div class="container ml-1">
    <div class="row">
        <a class="col-2" href="${pageContext.request.contextPath}/info">
            <button type="submit" class="btn btn-primary">Экспорт xml</button>
        </a>
    </div>
    <div class="row">
        <form class="col-4" action="${pageContext.request.contextPath}/info" method="post"
              enctype="multipart/form-data">
            <div class="form-group">
                <label for="file">XML</label>
                <input class="form-control-file" id="file" type="file" name="file">
            </div>
            <button type="submit" class="btn btn-primary btn-block">
                Импортировать
            </button>
        </form>
    </div>
    <jsp:include page="../part/footer.jsp"/>
</body>
</html>
