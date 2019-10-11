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

    <fmt:message bundle="${loc}" key="local.label.username" var="username"/>
    <fmt:message bundle="${loc}" key="local.label.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.label.input.new_password" var="new_password"/>
    <fmt:message bundle="${loc}" key="local.label.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.label.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.label.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="local.label.middleName" var="middleName"/>
    <fmt:message bundle="${loc}" key="local.label.sex" var="sex"/>
    <fmt:message bundle="${loc}" key="local.label.sex.male" var="sexMale"/>
    <fmt:message bundle="${loc}" key="local.label.sex.female" var="sexFemale"/>
    <fmt:message bundle="${loc}" key="local.label.dateOfBirth" var="dateOfBirth"/>
    <fmt:message bundle="${loc}" key="local.label.phoneNumber" var="phoneNumber"/>
    <fmt:message bundle="${loc}" key="local.label.role" var="role"/>
    <fmt:message bundle="${loc}" key="local.label.role.admin" var="administrator"/>
    <fmt:message bundle="${loc}" key="local.label.role.candidate" var="candidate"/>
    <fmt:message bundle="${loc}" key="local.label.role.waiter" var="waiter"/>
    <fmt:message bundle="${loc}" key="local.label.passport" var="passport"/>
    <fmt:message bundle="${loc}" key="local.label.workBegan" var="workBegan"/>
    <fmt:message bundle="${loc}" key="local.label.answer.success" var="success"/>
    <fmt:message bundle="${loc}" key="local.label.answer.noData" var="no_data"/>
    <fmt:message bundle="${loc}" key="local.label.answer.error" var="error"/>

    <fmt:message bundle="${loc}" key="local.edit_profile.title" var="title"/>
    <fmt:message bundle="${loc}" key="local.edit_profile.label_h1" var="label_h1"/>
    <fmt:message bundle="${loc}" key="local.edit_profile.label_h2" var="label_h2"/>
    <fmt:message bundle="${loc}" key="local.edit_profile.submit" var="submit"/>

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
                    <input type="submit" value="${profile}"/>
                </form>
            <li>

            </li>
            <form action="main" method="get">
                <input type="hidden" name="command" value="to_edit_profile_page"/>
                <input class="active" type="submit" value="${edit_profile}"/>
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
            <h1>${label_h1}</h1>
            <h2>${label_h2}</h2>
            <hr>

            <form action="main" method="post">
                <input type="hidden" name="command" value="edit_profile"/>
                <input type="hidden" name="id" value="${requestScope.info.id}"/>

                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                    <div class="edit_box">
                        <label><b>${username}</b></label><br>
                        <input type="text" name="login" placeholder="${requestScope.info.login}"/>
                    </div>
                </c:if>

                <div class="edit_box">
                    <label><b>${password}</b></label><br>
                    <input type="password" name="password" placeholder="${new_password}"/><br>
                </div>

                <div class="edit_box">
                    <label><b>${email}</b></label><br>
                    <input type="text" name="email" placeholder="${requestScope.info.email}"/><br>
                </div>

                <div class="edit_box">
                    <label><b>${name}</b></label><br>
                    <input type="text" name="name" placeholder="${requestScope.info.name}"/><br>
                </div>

                <div class="edit_box">
                    <label><b>${surname}</b></label><br>
                    <input type="text" name="surname" placeholder="${requestScope.info.surname}"/><br>
                </div>

                <div class="edit_box">
                    <label><b>${middleName}</b></label><br>
                    <input type="text" name="middleName" placeholder="${requestScope.info.middleName}"/><br>
                </div>

                <div class="edit_box">
                    <label><b>${sex}</b></label><br>
                    <select name="sex">
                        <c:choose>
                            <c:when test = "${requestScope.info.sex == 'male'}">
                                <option value="">${sexMale}</option>
                            </c:when>
                            <c:when test = "${requestScope.info.sex == 'female'}">
                                <option value="">${sexFemale}</option>
                            </c:when>
                        </c:choose>
                        <option value="male">${sexMale}</option>
                        <option value="female">${sexFemale}</option>
                    </select>
                </div>

                <div class="edit_box">
                    <label><b>${dateOfBirth}</b> -- [${requestScope.info.dateOfBirth}]</label><br>
                    <input type="date" name="dateOfBirth"/><br>
                </div>

                <div class="edit_box">
                    <label><b>${phoneNumber}</b></label><br>
                    <input type="text" name="phoneNumber" placeholder="${requestScope.info.phoneNumber}"/><br>
                </div>

                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                    <div class="edit_box">
                        <label><b>${role}</b></label><br>
                        <select name="role">
                            <c:choose>
                                <c:when test = "${requestScope.info.role == 'ADMIN'}">
                                    <option value="">${administrator}</option>
                                </c:when>
                                <c:when test = "${requestScope.info.role == 'WAITER'}">
                                    <option value="">${waiter}</option>
                                </c:when>
                                <c:when test = "${requestScope.info.role == 'ADMIN'}">
                                    <option value="">${candidate}</option>
                                </c:when>
                            </c:choose>

                            <option value="CANDIDATE">${candidate}</option>
                            <option value="WAITER">${waiter}</option>
                            <option value="ADMIN">${administrator}</option>
                        </select>
                    </div>
                </c:if>

                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                    <div class="edit_box">
                        <label><b>${passport}</b></label><br>
                        <input type="text" name="passport" placeholder="${requestScope.info.passport}"/>
                    </div>
                </c:if>

                <c:if test="${sessionScope.user.role == 'ADMIN'}">
                    <div class="edit_box">
                        <label><b>${workBegan}</b> -- [${requestScope.info.beganWork}]</label><br>
                        <input type="date" name="beganWork"/><br>
                    </div>
                </c:if>

                <hr>

                <c:choose>
                    <c:when test="${sessionScope.code == null}"/>

                    <c:when test="${sessionScope.code == 0}">
                        <div style="color: green">
                            <c:out value="${success}"/>
                        </div>

                    </c:when>

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


    </section>
</section><!--  end hero section  -->

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>
