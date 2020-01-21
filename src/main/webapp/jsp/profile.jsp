<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title" /></title>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/css/style.css'>
    <link rel='stylesheet' href='webjars/bootstrap/4.3.1/css/bootstrap.min.css'>
    <script type="text/javascript"
            src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="main_header.jsp"/>
    <div class="container justify-content-center">
        <div class="container">
            <form method="post" class="needs-validation" enctype="multipart/form-data"
                  action="${pageContext.request.contextPath}/profile" novalidate>
                <div class="form-group">
                    <label for="file"><fmt:message key="avatar_button"/></label>
                    <input class="form-control-file" id="file" type="file" name="file">
                </div>
                <div class="form-group">
                    <label for="phone_number"><fmt:message key="phone_number_input"/></label>
                    <input class="form-control" id="phone_number" name="phone_number" required
                           pattern="\+375\d{2} \d{7}" value="${phone_number}">
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
                <button type="submit" class="btn btn-primary btn-block"><fmt:message key="change_button"/></button>
            </form>
        </div>
    </div>
<jsp:include page="footer.jsp"/>
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
