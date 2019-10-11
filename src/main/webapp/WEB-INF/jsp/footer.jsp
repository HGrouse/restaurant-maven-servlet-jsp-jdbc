<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="local" var="loc"/>

    <fmt:message bundle="${loc}" key="local.footer.allRightsReserved" var="allRightsReserved"/>
    <fmt:message bundle="${loc}" key="local.footer.lookingFor" var="lookingFor"/>

    <meta charset="utf-8">
    <title>Footer</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<footer>
    <div class="copyrights wrapper">
        Copyright © 2019 <a href="https://jobs.dev.by/" target="_blank" class="ph_link" title="Have a nice day!">${lookingFor}</a> ${allRightsReserved}
    </div>
</footer><!--  end footer  -->

</body>
</html>
