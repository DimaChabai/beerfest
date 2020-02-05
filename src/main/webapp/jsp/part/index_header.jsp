<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${language}" scope="session"/>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <fmt:bundle basename="pagecontent">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/"><fmt:message key="page.content.title"/></a>
            <ul class="navbar-nav flex-row mr-auto mt-3">
                <li class="nav-item active">
                    <form class="form-inline" method="post" action="controller">
                        <input type="text" name="command" value="change_Language" hidden>
                        <input type="text" value="ru_RU" name="language" hidden>
                        <input type="text" value="/index.jsp" name="page" hidden>
                        <button class="btn btn-outline-success mx-2" type="submit">
                            <fmt:message key="page.parameter.ru_language"/>
                        </button>
                    </form>
                </li>
                <li class="nav-item active">
                    <form class="form-inline" method="post" action="controller">
                        <input type="text" name="command" value="change_Language" hidden>
                        <input type="text" value="be_BY" name="language" hidden>
                        <input type="text" value="/index.jsp" name="page" hidden>
                        <button class="btn btn-outline-success mx-2" type="submit">
                            <fmt:message key="page.parameter.be_language"/>
                        </button>
                    </form>
                </li>
                <li class="nav-item active">
                    <form class="form-inline" method="post" action="controller">
                        <input type="text" name="command" value="change_Language" hidden>
                        <input type="text" value="en_EN" name="language" hidden>
                        <input type="text" value="/index.jsp" name="page" hidden>
                        <button class="btn btn-outline-success mx-2" type="submit" name="language">
                            <fmt:message key="page.parameter.en_language"/>
                        </button>
                    </form>
                </li>

            </ul>
            <div class="row mt-3">
                <form class="form-inline col" method="post" action="controller">
                    <input type="text" name="command" value="to_Registration" hidden>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message
                            key="page.content.registration_button"/></button>
                </form>

                <form class="form-inline col" method="post" action="controller">
                    <input type="text" name="command" value="to_Login" hidden>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message
                            key="page.content.login_button"/></button>
                </form>
            </div>
        </fmt:bundle>
    </nav>
</header>
