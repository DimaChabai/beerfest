<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger" role="alert">
        <fmt:message key="${errorMessage}"/>
    </div>
</c:if>