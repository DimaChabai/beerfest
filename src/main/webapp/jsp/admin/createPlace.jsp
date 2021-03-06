<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title><fmt:message key="page.content.title"/></title>
    <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
</head>
<body class="d-flex flex-column h-100">
<jsp:include page="../part/main_header.jsp"/>
<jsp:include page="../part/error_message.jsp"/>
<div class="container">
    <form method="post">
        <input type="text" name="command" value="create_place" hidden>
        <div class="form-group">
            <label for="placeType"><fmt:message key="page.content.type"/></label>
            <select class="form-control " name="placeType" id="placeType" onchange="setInputMax()">
                <option class="selectOpt" value="SMALL"><fmt:message key="page.content.small_place"/></option>
                <option class="selectOpt" value="MEDIUM"><fmt:message key="page.content.medium_place"/></option>
                <option class="selectOpt" value="LARGE"><fmt:message key="page.content.large_place"/></option>
            </select>
        </div>
        <div class="form-group">
            <label for="seats"><fmt:message key="page.content.seats_number"/></label>
            <input class="form-control" type="number" max="10" name="seats" id="seats">
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="page.content.create_label"/></button>
    </form>
</div>
<jsp:include page="../part/footer.jsp"/>
<script>
    let seats = document.getElementById("seats");
    let select = document.getElementById("placeType");

    function setInputMax() {
        if (select.value === "SMALL") {
            seats.max = 10;
        } else if (select.value === "MEDIUM") {
            seats.max = 15;
        } else {
            seats.max = 20;
        }
    }
</script>
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
