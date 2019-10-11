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

    <fmt:message bundle="${loc}" key="local.label.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.label.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="local.label.middleName" var="middleName"/>
    <fmt:message bundle="${loc}" key="local.label.role" var="role"/>
    <fmt:message bundle="${loc}" key="local.label.commands" var="commands"/>
    <fmt:message bundle="${loc}" key="local.label.commands.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="local.label.commands.fire" var="fire"/>

    <fmt:message bundle="${loc}" key="local.show_users.title" var="title"/>
    <fmt:message bundle="${loc}" key="local.show_users.label_h1" var="label_h1"/>
    <fmt:message bundle="${loc}" key="local.show_users.label_h2_part1" var="label_h2_part1"/>
    <fmt:message bundle="${loc}" key="local.show_users.label_h2_part2" var="label_h2_part2"/>

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

            <h1>${label_h1}</h1>
            <h2>${label_h2_part1}
                ${((requestScope.chosenPage) * requestScope.count_users_on_page) + 1}
                ${label_h2_part2}
                ${((requestScope.chosenPage) * requestScope.count_users_on_page) + requestScope.count_users_on_page}
            </h2>
            <div class="user_view">
                <table>
                    <tr>
                        <th width="120px">${role}</th>
                        <th width="150px">${name}</th>
                        <th width="150px">${surname}</th>
                        <th width="150px">${middleName}</th>
                        <th colspan="2">${commands}</th>
                    </tr>


                    <c:forEach items="${requestScope.users}" var="user">
                        <tr>
                            <td>
                                <c:out value="${user.role}"/>
                            </td>
                            <td>
                                <c:out value="${user.name}"/>
                            </td>
                            <td>
                                <c:out value="${user.surname}"/>
                            </td>
                            <td>
                                <c:out value="${user.middleName}"/>
                            </td>
                            <td class="action_edit">
                                <form action="main" method="get">
                                    <input type="hidden" name="command" value="to_edit_profile_page"/>
                                    <input type="hidden" name="id" value="${user.id}"/>
                                    <input type="submit" value="${edit}">
                                </form>
                            </td>

                            <td class="action_dismiss">
                                <form action="main" method="get">
                                    <input type="hidden" name="command" value="to_dismissal_page"/>
                                    <input type="hidden" name="id" value="${user.id}"/>
                                    <input type="submit" value="${fire}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                </table>


            </div>

            <hr>

            <div class="page_to_show">
                <c:forEach var="i" begin="" end="${requestScope.countOfPages}">

                    <c:choose>
                        <c:when test="${requestScope.chosenPage == i || (requestScope.chosenPage == null && i == 0)}">
                            <form action="main" method="get">
                                <input type="hidden" name="command" value="get_all_users"/>
                                <input type="hidden" name="chosenPage" value="${i}"/>
                                <input type="submit" class="active" value="${i+1}"/>
                            </form>
                        </c:when>

                        <c:otherwise>
                            <form action="main" method="get">
                                <input type="hidden" name="command" value="get_all_users"/>
                                <input type="hidden" name="chosenPage" value="${i}"/>
                                <input type="submit" value="${i+1}"/>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>


        </div>


    </section>
</section><!--  end hero section  -->

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
