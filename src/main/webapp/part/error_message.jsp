<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger" role="alert">
        <fmt:message key="${errorMessage}"/>
    </div>
</c:if>