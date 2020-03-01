<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page="part/main_header.jsp"/>
<jsp:include page="part/message.jsp"/>
<jsp:include page="part/error_message.jsp"/>
<div class="container justify-content-center">
    <form method="post" class="was-validated" enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/profile">
        <div class="form-group">
            <label for="file"><fmt:message key="page.content.avatar_button"/></label>
            <input class="form-control-file" id="file" type="file" name="file">
        </div>
        <div class="form-group">
            <label for="phone_number"><fmt:message key="page.content.new_phone_number_input"/></label>
            <input class="form-control" id="phone_number" name="phone_number"
                   pattern="\+375((29)|(44)|(33)|(25)) ?\d{7}" value="${phone_number}">
            <div class="invalid-feedback">
                <fmt:message key="page.content.invalid_phone_text"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary btn-block"><fmt:message
                key="page.content.change_button"/>
        </button>
    </form>
</div>
<jsp:include page="part/footer.jsp"/>
<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            var forms = document.getElementsByClassName('needs-validation');
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
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
