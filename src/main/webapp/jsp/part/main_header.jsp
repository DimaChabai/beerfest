<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/controller"><fmt:message key="page.content.title"/></a>
        <div class="navbar-nav mt-3">
            <ul class="navbar-nav flex-row mr-auto">
                <c:if test="${role_name == 'USER'}">
                    <li class="nav-item active">
                        <form class="form-inline" method="post" action="controller">
                            <input type="text" name="command" value="to_Ticket" hidden>
                            <button class="btn btn-outline-success mx-2" type="submit"><fmt:message
                                    key="page.content.buy_ticket"/></button>
                        </form>
                    </li>
                    <li class="nav-item active">
                        <form class="form-inline" method="post" action="controller">
                            <input type="text" name="command" value="to_become_participant" hidden>
                            <button class="btn btn-outline-success mx-2" type="submit"><fmt:message
                                    key="page.content.participant_button"/>
                            </button>
                        </form>
                    </li>
                </c:if>
                <li class="nav-item active">
                    <form class="form-inline" method="post" action="controller">
                        <input type="text" name="command" value="to_participant_list" hidden>
                        <button class="btn btn-outline-success mx-2" type="submit"><fmt:message
                                key="page.content.participant_list"/>
                        </button>
                    </form>
                </li>
                <li class="nav-item active">
                    <form class="form-inline" method="post" action="controller">
                        <input type="text" name="command" value="change_Locale" hidden>
                        <input type="text" value="ru_RU" name="locale" hidden>
                        <input type="text" value="/jsp/main.jsp" name="page" hidden>
                        <button class="btn btn-outline-success mx-2" type="submit">
                            <fmt:message key="page.parameter.ru_locale"/>
                        </button>
                    </form>
                </li>
                <li class="nav-item active">
                    <form class="form-inline" method="post" action="controller">
                        <input type="text" name="command" value="change_Locale" hidden>
                        <input type="text" value="be_BY" name="locale" hidden>
                        <input type="text" value="/jsp/main.jsp" name="page" hidden>
                        <button class="btn btn-outline-success mx-2" type="submit">
                            <fmt:message key="page.parameter.be_locale"/>
                        </button>
                    </form>
                </li>
                <li class="nav-item active">
                    <form class="form-inline" method="post" action="controller">
                        <input type="text" name="command" value="change_Locale" hidden>
                        <input type="text" value="en_EN" name="locale" hidden>
                        <input type="text" value="/jsp/main.jsp" name="page" hidden>
                        <button class="btn btn-outline-success mx-2" type="submit" name="locale">
                            <fmt:message key="page.parameter.en_locale"/>
                        </button>
                    </form>
                </li>
            </ul>
        </div>
        <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex mt-3">
            <li class="nav-item">
                <form class="form-inline col px-1" method="post" action="controller">
                    <input type="text" name="command" value="to_Profile" hidden>
                    <button class="btn btn-outline-success " type="submit"><fmt:message
                            key="page.content.profile_button"/></button>
                </form>
            </li>
            <c:if test="${role_name == 'ADMIN'}">
                <li class="nav-item">
                    <form class="form-inline col px-1" method="post" action="controller">
                        <input type="text" name="command" value="to_Create" hidden>
                        <button class="btn btn-outline-success" type="submit"><fmt:message
                                key="page.content.create_button"/></button>
                    </form>
                </li>
                <li class="nav-item">
                    <form class="form-inline col px-1" method="post" action="controller">
                        <input type="text" name="command" value="to_Verification" hidden>
                        <button class="btn btn-outline-success " type="submit"><fmt:message
                                key="page.content.verification_button"/></button>
                    </form>
                </li>
            </c:if>
            <li class="nav-item">
                <form class="form-inline col px-1" method="post" action="controller">
                    <input type="text" name="command" value="Exit" hidden>
                    <button class="btn btn-outline-success " type="submit"><fmt:message
                            key="page.content.exit_button"/></button>
                </form>
            </li>
            <li class="nav-item mr-2">
                <ctg:user_role/>
            </li>
            <li class="nav-item">
                <span class="navbar-text">
                    ${email}
                </span>
                    <img src="${pageContext.request.contextPath}/avatars/${avatar}" width="32" height="32">
            </li>
        </ul>
    </nav>
</header>
