<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.header.contact" var="contact"/>
    <fmt:message bundle="${loc}" key="local.header.about" var="about"/>
    <fmt:message bundle="${loc}" key="local.header.logout" var="logout"/>


    <meta charset="utf-8">
    <title>Header</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
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

            <div class="exitbutton">
                <form action="main" method="post">
                    <input type="hidden" name="command" value="exit"/>
                    <input type="submit" value="${logout}">
                </form>
            </div>


            <!-- <a class="login_btn" href="index.jsp">Main Page</a> -->
        </nav>
    </div>
</header><!--  end header section  -->

</body>
</html>
