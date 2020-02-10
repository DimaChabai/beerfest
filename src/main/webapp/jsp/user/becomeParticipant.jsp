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
<jsp:include page="../part/main_header.jsp"/>
<jsp:include page="../part/error_message.jsp"/>
<div class="container">
    <form method="post" class="was-validated" novalidate>
        <input type="text" name="command" value="become_participant" hidden>
        <div class="form-group">
            <label for="company_name"><fmt:message key="page.content.name_label"/></label>
            <input class="form-control" type="text" id="company_name" name="company_name" pattern=".{10,}" required>
        </div>
        <div class="form-group">
            <label for="beer_type"><fmt:message key="page.content.beer_label"/></label>
            <select class="custom-select" name="beer_type" id="beer_type" required>
                <c:if test="${not empty beer_type}">
                    <c:forEach var="beer" items="${beer_type}">
                        <option>${beer}</option>
                    </c:forEach>
                </c:if>
            </select>
            <div class="invalid-feedback">
                <fmt:message key="page.content.invalid_place"/>
            </div>
        </div>
        <div class="form-group">
            <label for="place"><fmt:message key="page.content.place_label"/></label>
            <select class="custom-select" name="place" id="place" required>
                <c:if test="${not empty places}">
                    <c:forEach var="place" items="${places}">
                        <option><fmt:message key="page.content.territory"/>${place.idPlace} <fmt:message
                                key="page.content.places"/>${place.seats}</option>
                    </c:forEach>
                </c:if>
            </select>
            <div class="invalid-feedback">
                <fmt:message key="page.content.invalid_place"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="page.content.book_button"/></button>
    </form>
</div>
<jsp:include page="../part/footer.jsp"/>
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
