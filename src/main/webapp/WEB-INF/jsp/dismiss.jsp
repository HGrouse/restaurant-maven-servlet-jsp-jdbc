<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.navigation_bar.profile" var="profile"/>
    <fmt:message bundle="${loc}" key="local.navigation_bar.edit_profile" var="edit_profile"/>
    <fmt:message bundle="${loc}" key="local.navigation_bar.show_users" var="show_users"/>

    <fmt:message bundle="${loc}" key="local.label.dateOfDismissal" var="dateOfDismissal"/>
    <fmt:message bundle="${loc}" key="local.label.answer.noData" var="no_data"/>
    <fmt:message bundle="${loc}" key="local.label.answer.error" var="error"/>

    <fmt:message bundle="${loc}" key="local.dismissal.title" var="title"/>
    <fmt:message bundle="${loc}" key="local.dismissal.label_h1" var="label_h1"/>
    <fmt:message bundle="${loc}" key="local.dismissal.label_h2" var="label_h2"/>
    <fmt:message bundle="${loc}" key="local.dismissal.submit" var="submit"/>

    <meta charset="utf-8">
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<section class="hero">

    <jsp:include page="/WEB-INF/jsp/header.jsp"/>

    <section class="mainpage">
        <ul>
            <li>
                <form action="main" method="get">
                    <input type="hidden" name="command" value="to_user_information_page"/>
                    <input type="submit" value="${profile}"/>
                </form>
            <li>

            </li>
            <form action="main" method="get">
                <input type="hidden" name="command" value="to_edit_profile_page"/>
                <input type="submit" value="${edit_profile}"/>
            </form>
            </li>

            <c:if test="${sessionScope.user.role == 'ADMIN'}">
                <li>
                    <form action="main" method="get">
                        <input type="hidden" name="command" value="get_all_users"/>
                        <input class="active" type="submit" value="${show_users}"/>
                    </form>
                </li>
            </c:if>
        </ul>
        <div class="showbar">
            <div class="dismissal_page">
                <h1>${label_h1}</h1>
                <h2>${label_h2}</h2>
                <hr>

                <form action="main" method="post">
                    <input type="hidden" name="command" value="dismiss"/>
                    <input type="hidden" name="id" value="${sessionScope.id}"/>


                    <div class="edit_box">
                        <label><b>${dateOfDismissal}</b></label><br>
                        <input type="date" name="dateOfDismissal"/><br>
                    </div>

                    <hr>

                    <c:choose>
                        <c:when test="${sessionScope.code == null}"/>

                        <c:when test="${sessionScope.code == 13}">
                            <div style="color: darkorange">
                                <c:out value="${no_data}"/>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div style="color: red">
                                <c:out value="${error}"/>
                            </div>
                        </c:otherwise>
                    </c:choose>

                    <div class="edit_button">
                        <input type="submit" value="${submit}"/>
                    </div>
                </form>
            </div>

        </div>

    </section>
</section>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>
