<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="pagecontent"/>
<jsp:include page="main_header_start.jsp"/>
<div class="navbar-nav">
    <ul class="navbar-nav flex-row mr-auto">
        <c:if test="${role_name == 'USER'}">
            <li class="nav-item active">
                <form class="form-inline" method="post" action="${pageContext.request.contextPath}/index">
                    <input type="text" name="command" value="to_Ticket" hidden>
                    <button class="btn btn-outline-success mx-2" type="submit"><fmt:message
                            key="buy_ticket"/></button>
                </form>
            </li>
            <li class="nav-item active">
                <form class="form-inline" method="post" action="${pageContext.request.contextPath}/index">
                    <input type="text" name="command" value="to_Participant" hidden>
                    <button class="btn btn-outline-success mx-2" type="submit"><fmt:message
                            key="participant_button"/>
                    </button>
                </form>
            </li>
        </c:if>
        <li class="nav-item active">
            <form class="form-inline" method="post" action="${pageContext.request.contextPath}/index">
                <input type="text" name="command" value="change_Language" hidden>
                <input type="text" value="ru_RU" name="language" hidden>
                <button class="btn btn-outline-success mx-2" type="submit"><fmt:message
                        key="ru_language"/>
                </button>
            </form>
        </li>
        <li class="nav-item active">
            <form class="form-inline" method="post" action="${pageContext.request.contextPath}/index">
                <input type="text" name="command" value="change_Language" hidden>
                <input type="text" value="be_BY" name="language" hidden>
                <button class="btn btn-outline-success mx-2" type="submit"><fmt:message
                        key="be_language"/>
                </button>
            </form>
        </li>
        <li class="nav-item active">
            <form class="form-inline" method="post" action="${pageContext.request.contextPath}/index">
                <input type="text" name="command" value="change_Language" hidden>
                <input type="text" value="en_EN" name="language" hidden>
                <button class="btn btn-outline-success mx-2" type="submit" name="language"><fmt:message
                        key="en_language"/>
                </button>
            </form>
        </li>
    </ul>
</div>
<ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
    <li class="nav-item">
        <form class="form-inline col px-1" method="post" action="${pageContext.request.contextPath}/index">
            <input type="text" name="command" value="to_Profile" hidden>
            <button class="btn btn-outline-success " type="submit"><fmt:message key="profile_button"/></button>
        </form>
    </li>
    <c:if test="${role_name == 'ADMIN'}">
        <li class="nav-item">
            <form class="form-inline col px-1" method="post" action="${pageContext.request.contextPath}/index">
                <input type="text" name="command" value="to_Create" hidden>
                <button class="btn btn-outline-success" type="submit"><fmt:message key="create_button"/></button>
            </form>
        </li>
        <li class="nav-item">
            <form class="form-inline col px-1" method="post" action="${pageContext.request.contextPath}/index">
                <input type="text" name="command" value="to_Verification" hidden>
                <button class="btn btn-outline-success " type="submit"><fmt:message
                        key="verification_button"/></button>
            </form>
        </li>
    </c:if>
    <li class="nav-item">
        <form class="form-inline col px-1" method="post" action="${pageContext.request.contextPath}/index">
            <input type="text" name="command" value="Exit" hidden>
            <button class="btn btn-outline-success " type="submit"><fmt:message key="exit_button"/></button>
        </form>
    </li>
    <li class="nav-item">
        <a class="nav-link col" data-toggle="dropdown">
                <span class="navbar-text">
                    ${email}
                </span>
            <img src="${pageContext.request.contextPath}/avatars/${avatar}" width="32" height="32">
        </a>
    </li>
</ul>

<jsp:include page="index_header_end.jsp"/>
