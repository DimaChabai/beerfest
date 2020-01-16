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
<body class="d-flex flex-column h-100">
<jsp:include page="index_header.jsp"/>
<c:if test="${not empty message}">
    <div class="alert alert-primary" role="alert">
            ${message}
    </div>
</c:if>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger" role="alert">
            ${errorMessage}
    </div>
</c:if>
<fmt:bundle basename="pagecontent">
    <div class="container">
        <form method="post" class="needs-validation" novalidate>
            <input type="text" name="command" value="registration" hidden>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" required>
                <small id="emailHelp" class="form-text text-muted"><fmt:message key="email_help_text"/></small>
                <div class="invalid-feedback">
                    <fmt:message key="invalid_email_text"/>
                </div>
            </div>
            <div class="form-group">
                <label for="phone_number"><fmt:message key="phone_number_input"/></label>
                <input class="form-control" id="phone_number" name="phone_number" pattern="\+375\d{2} \d{7}" required>
                <div class="invalid-feedback">
                    <fmt:message key="invalid_phone_text"/>
                </div>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="password_label"/></label>
                <input type="password" class="form-control" id="password" name="password" required>
                <div class="invalid-feedback">
                    <fmt:message key="invalid_password_text"/>
                </div>
            </div>
            <button type="submit" class="btn btn-primary btn-block"><fmt:message key="invalid_password_text"/></button>
        </form>
    </div>
</fmt:bundle>
<jsp:include page="footer.jsp"/>
</body>
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
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
</html>
