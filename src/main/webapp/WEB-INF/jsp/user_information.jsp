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

    <fmt:message bundle="${loc}" key="local.user_information.title" var="title"/>
    <fmt:message bundle="${loc}" key="local.user_information.label_h1" var="label_h1"/>
    <fmt:message bundle="${loc}" key="local.user_information.label_h2" var="label_h2"/>
    <fmt:message bundle="${loc}" key="local.user_information.table" var="table"/>

    <meta charset="utf-8">
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<!--  -->

<section class="hero">

    <jsp:include page="/WEB-INF/jsp/header.jsp"/>

    <section class="mainpage">
        <ul>
            <li>
                <form action="main" method="get">
                    <input type="hidden" name="command" value="to_user_information_page"/>
                    <input class="active" type="submit" value="${profile}"/>
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
                        <input type="submit" value="${show_users}"/>
                    </form>
                </li>
            </c:if>
        </ul>

        <div class="showbar">
            <h1>${label_h1} <c:out value="${sessionScope.user.name}"/>! </h1>
            <h2>${label_h2}</h2>

            <div style="border: 4px double black; width: 700px; height: 450px; padding: 30px;">
                <h4>${table}</h4>
            </div>

        </div>


    </section>
</section><!--  end hero section  -->

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>
