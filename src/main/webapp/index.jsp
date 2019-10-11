<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.index.title" var="title"/>
    <fmt:message bundle="${loc}" key="local.index.mainLabel" var="mainLabel"/>
    <fmt:message bundle="${loc}" key="local.index.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.index.vacations" var="vacations"/>
    <fmt:message bundle="${loc}" key="local.index.cancel" var="cancel"/>
    <fmt:message bundle="${loc}" key="local.index.forgot" var="forgot"/>
    <fmt:message bundle="${loc}" key="local.index.forgot.password" var="Password"/>
    <fmt:message bundle="${loc}" key="local.index.incorrectInformation" var="incorrectInformation"/>

    <fmt:message bundle="${loc}" key="local.header.contact" var="contact"/>
    <fmt:message bundle="${loc}" key="local.header.about" var="about"/>
    <fmt:message bundle="${loc}" key="local.footer.allRightsReserved" var="allRightsReserved"/>
    <fmt:message bundle="${loc}" key="local.footer.lookingFor" var="lookingFor"/>

    <fmt:message bundle="${loc}" key="local.label.registration" var="registration"/>
    <fmt:message bundle="${loc}" key="local.label.username" var="username"/>
    <fmt:message bundle="${loc}" key="local.label.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.label.input.username" var="enterUsername"/>
    <fmt:message bundle="${loc}" key="local.label.input.password" var="enterPassword"/>


    <title>${title}</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/responsive.css">

    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
</head>
<body>

<section class="hero">
    <header>
        <div class="wrapper">
            <a href="#"><img src="img/logo.png" class="logo" alt="" titl=""/></a>
            <a href="#" class="hamburger"></a>
            <nav>
                <ul>
                    <li><a href="#">${about}</a></li>
                    <li><a href="#">${contact}</a></li>
                </ul>
                <div class="local_input">
                    <form action="main" method="post">
                        <input type="hidden" name="command" value="change_local"/>
                        <input type="hidden" name="local" value="ru"/>
                        <input type="hidden" name="commandForRedirect" value="${requestScope.commandForRedirect}"/>
                        <input type="submit" value="RU"/>
                    </form>

                    <form action="main" method="post">
                        <input type="hidden" name="command" value="change_local"/>
                        <input type="hidden" name="local" value="en"/>
                        <input type="hidden" name="commandForRedirect" value="${requestScope.commandForRedirect}"/>
                        <input type="submit" value="EN"/>
                    </form>
                </div>

                <!-- Button to open the modal login form -->
                <button onclick="document.getElementById('id01').style.display='block'"
                        class="login_btn">${login}</button>

                <!-- The Modal -->

                <c:choose>
                <c:when test="${requestScope.code == 1}">
                <div id="id01" class="modal" style="display: block;" >
                </c:when>

                <c:otherwise>
                <div id="id01" class="modal" >
                </c:otherwise>
                </c:choose>

                <span onclick="document.getElementById('id01').style.display='none'"
                      class="close" title="Close Modal">&times;
                    </span>

                <!-- Modal Content -->
                <div class="modal-content animate">
                    <form class="act_login_btn" action="main" method="post">
                        <div class="container">
                            <input type="hidden" name="command" value="authorization"/>

                            <label><b>${username}</b></label>
                            <input type="text" placeholder="${enterUsername}" name="login">


                            <label><b>${password}</b></label>
                            <input type="password" placeholder="${enterPassword}" name="password">

                            <c:if test="${requestScope.code == 1}">
                                <p style="font-size: 14px; color: red; padding: 5px;">
                                    ${incorrectInformation}
                                </p>
                            </c:if>

                            <input type="submit" value="${login}"/>
                        </div>
                    </form>

                    <form class="regbtn" action="main" method="get">
                        <div class="container">
                            <div class="regbtn">
                                <input type="hidden" name="command" value="to_registration_page"/>
                                <input type="submit" value="${registration}"/>
                            </div>
                        </div>
                    </form>


                    <div class="container" style="background-color:#f1f1f1">
                        <button type="button" onclick="document.getElementById('id01').style.display='none'"
                                class="cancelbtn">${cancel}
                        </button>
                        <span class="psw">${forgot} <a href="#">${Password}?</a></span>
                    </div>

                </div>
        </div>
        </nav>
        </div>
    </header><!--  end header section  -->

    <section class="caption">
        <h2 class="caption">${mainLabel}</h2>
        <h3 class="properties">${vacations}</h3>
    </section>
</section><!--  end hero section  -->

<footer>
    <div class="copyrights wrapper">
        Copyright © 2019 <a href="https://jobs.dev.by/" target="_blank" class="ph_link"
                            title="Have a nice day!">${lookingFor} </a>${allRightsReserved}
    </div>
</footer><!--  end footer  -->

</body>
</html>