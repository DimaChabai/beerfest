<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:bundle basename="resource.pagecontent">
<jsp:include page="main_header_start.jsp"/>
<div class="navbar-nav">

    <ul class="navbar-nav flex-row mr-auto">
        <c:if test="${role_name == 'USER'}">
            <li class="nav-item active">
                <form class="form-inline" method="post" action="${pageContext.request.contextPath}/index">
                    <input type="text" name="command" value="toTicket" hidden>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="buy_ticket" /></button>
                </form>
            </li>
            <li class="nav-item active">
                <form class="form-inline" method="post" action="${pageContext.request.contextPath}/index">
                    <input type="text" name="command" value="toParticipant" hidden>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="participant_button" />
                    </button>
                </form>
            </li>
        </c:if>
    </ul>
</div>
<ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
    <li class="nav-item">
        <form class="form-inline col px-0" method="post" action="${pageContext.request.contextPath}/index">
            <input type="text" name="command" value="toProfile" hidden>
            <button class="btn btn-outline-success " type="submit"><fmt:message key="profile_button" /></button>
        </form>
    </li>
    <c:if test="${role_name == 'ADMIN'}">
        <li class="nav-item">
            <form class="form-inline col px-0" method="post" action="${pageContext.request.contextPath}/index">
                <input type="text" name="command" value="toCreate" hidden>
                <button class="btn btn-outline-success" type="submit"><fmt:message key="create_button" /></button>
            </form>
        </li>
        <li class="nav-item">
            <form class="form-inline col px-0" method="post" action="${pageContext.request.contextPath}/index">
                <input type="text" name="command" value="toVerification" hidden>
                <button class="btn btn-outline-success " type="submit"><fmt:message key="verification_button" />
                </button>
            </form>
        </li>
    </c:if>
    <li class="nav-item">
        <form class="form-inline col px-0" method="post" action="${pageContext.request.contextPath}/index">
            <input type="text" name="command" value="toExit" hidden>
            <button class="btn btn-outline-success " type="submit"><fmt:message key="exit_button"/></button>
        </form>
    </li>
    <li class="nav-item"><a class="nav-link col" data-toggle="dropdown">
            <span class="navbar-text">
                ${email}
            </span>
        <img src="${pageContext.request.contextPath}/avatars/${avatar}" width="32" height="32">
    </a></li>


</ul>
<%--    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">--%>
<%--        <a class="dropdown-item" href="${pageContext.request.contextPath}/index?command=profile">Профиль</a>--%>
<%--        <a class="dropdown-item" href="${pageContext.request.contextPath}/index?command=create">Создать места</a>--%>
<%--        <a class="dropdown-item" href="${pageContext.request.contextPath}/index?command=verification">Подтвердить--%>
<%--            участников</a>--%>
<%--        <div class="dropdown-divider"></div>--%>
<%--        <a class="dropdown-item" href="${pageContext.request.contextPath}/exit">Выйти</a>--%>
<%--    </div>--%>
</div>


<jsp:include page="index_header_end.jsp"/>
