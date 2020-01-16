<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/css/style.css'>
    <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
</head>
<body>
<jsp:include page="main_header.jsp"/>
<fmt:bundle basename="pagecontent">
    <div class="container">
        <form method="post">
            <input type="text" name="command" value="participant" hidden>
            <div class="form-group">
                <label for="name"><fmt:message key="name_label"/></label>
                <input class="form-control" type="text" id="name" name="name">
            </div>
            <div class="form-group">
                <label for="place"><fmt:message key="place_label"/></label>
                <select class="form-control " name="place" id="place">
                    <c:if test="${not empty places}">
                        <c:forEach var="place" items="${places}">
                            <option>${place}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="book_button"/></button>
        </form>
    </div>
</fmt:bundle>
<jsp:include page="footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

</body>
</html>
