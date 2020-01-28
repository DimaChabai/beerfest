<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="page.content.title"/></title>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/css/style.css'>
    <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
</head>
<body>
<jsp:include page="../../part/main_header.jsp"/>
<jsp:include page="/part/error_message.jsp"/>
<div class="album py-5 bg-light">
    <div class="container">
        <div class="row">
            <c:if test="${not empty participants}">
                <c:forEach var="participant" items="${participants}">
                    <div class="col-md-auto">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title"><c:out value=" ${participant.name}"/></h5>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">${participant.place}</li>
                                </ul>
                                <div class="card-footer">
                                    <form class="form" method="post">
                                        <input type="text" value="${participant.id}" name="id" hidden>
                                        <input type="text" value="accept_Verification" name="command" hidden>
                                        <button type="submit" class="btn btn-primary"><fmt:message
                                                key="page.content.accept_button"/></button>
                                    </form>
                                    <form class="form" method="post">
                                        <input type="text" value="${participant.id}" name="id" hidden>
                                        <input type="text" value="decline_Verification" name="command" hidden>
                                        <button type="submit" class="btn btn-danger"><fmt:message
                                                key="page.content.decline_button"/></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>
<jsp:include page="../../part/footer.jsp"/>
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
