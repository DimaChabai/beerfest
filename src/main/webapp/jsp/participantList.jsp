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
<jsp:include page="part/main_header.jsp"/>
<jsp:include page="part/message.jsp"/>
<jsp:include page="part/error_message.jsp"/>
<nav>
    <ul class="pagination">
        <li>
            <form method="post">
                <input type="text" value="to_participant_list" name="command" hidden>
                <input type="number" name="page" value="${page-1}" hidden>
                <button type="submit"><fmt:message key="page.content.previous"/></button>
            </form>
        </li>
        <c:if test="${participants.size()==6}">
            <li>
                <form method="post">
                    <input type="text" value="to_participant_list" name="command" hidden>
                    <input type="number" name="page" value="${page+1}" hidden>
                    <button type="submit"><fmt:message key="page.content.next"/></button>
                </form>
            </li>
        </c:if>
    </ul>
</nav>
<div class="album py-5 bg-light">
    <div class="container">
        <div class="row">
            <c:if test="${not empty participants}">
                <c:forEach var="participant" items="${participants}">
                    <div class="col-md-auto">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title"><c:out value="${participant.name}"/></h5>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">Территория №${participant.place.idPlace} Мест = ${participant.place.seats}</li>
                                </ul>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">${participant.beerType}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="part/footer.jsp"/>
</body>
</html>
