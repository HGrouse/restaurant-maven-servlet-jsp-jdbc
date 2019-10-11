<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<fmt:setLocale value="${sessionScope.local}"/>
	<fmt:setBundle basename="local" var="loc"/>

	<fmt:message bundle="${loc}" key="local.header.contact" var="contact"/>
	<fmt:message bundle="${loc}" key="local.header.about" var="about"/>

	<fmt:message bundle="${loc}" key="local.label.registration" var="registration"/>
	<fmt:message bundle="${loc}" key="local.label.username" var="username"/>
	<fmt:message bundle="${loc}" key="local.label.password" var="password"/>
	<fmt:message bundle="${loc}" key="local.label.email" var="email"/>
	<fmt:message bundle="${loc}" key="local.label.name" var="name"/>
	<fmt:message bundle="${loc}" key="local.label.surname" var="surname"/>
	<fmt:message bundle="${loc}" key="local.label.middleName" var="middleName"/>
	<fmt:message bundle="${loc}" key="local.label.sex" var="sex"/>
	<fmt:message bundle="${loc}" key="local.label.sex.male" var="sexMale"/>
	<fmt:message bundle="${loc}" key="local.label.sex.female" var="sexFemale"/>
	<fmt:message bundle="${loc}" key="local.label.dateOfBirth" var="dateOfBirth"/>
	<fmt:message bundle="${loc}" key="local.label.phoneNumber" var="phoneNumber"/>

	<fmt:message bundle="${loc}" key="local.label.input.username" var="enterUsername"/>
	<fmt:message bundle="${loc}" key="local.label.input.password" var="enterPassword"/>
	<fmt:message bundle="${loc}" key="local.label.input.email" var="enterEmail"/>
	<fmt:message bundle="${loc}" key="local.label.input.name" var="enterName"/>
	<fmt:message bundle="${loc}" key="local.label.input.surname" var="enterSurname"/>
	<fmt:message bundle="${loc}" key="local.label.input.middleName" var="enterMiddleName"/>
	<fmt:message bundle="${loc}" key="local.label.input.phoneNumber" var="enterPhoneNumber"/>

	<fmt:message bundle="${loc}" key="local.label.incorrect.login" var="incorrectLogin"/>
	<fmt:message bundle="${loc}" key="local.label.incorrect.exist.login" var="existLogin"/>
	<fmt:message bundle="${loc}" key="local.label.incorrect.password" var="incorrectPassword"/>
	<fmt:message bundle="${loc}" key="local.label.incorrect.email" var="incorrectEmail"/>
	<fmt:message bundle="${loc}" key="local.label.incorrect.exist.email" var="existEmail"/>
	<fmt:message bundle="${loc}" key="local.label.incorrect.name" var="incorrectName"/>
	<fmt:message bundle="${loc}" key="local.label.incorrect.surname" var="incorrectSurname"/>
	<fmt:message bundle="${loc}" key="local.label.incorrect.middleName" var="incorrectMiddleName"/>
	<fmt:message bundle="${loc}" key="local.label.incorrect.sex" var="incorrectSex"/>
	<fmt:message bundle="${loc}" key="local.label.incorrect.dateOfBirth" var="incorrectDateOfBirth"/>
	<fmt:message bundle="${loc}" key="local.label.incorrect.phoneNumber" var="incorrectPhoneNumber"/>

	<fmt:message bundle="${loc}" key="local.registration.title" var="title"/>
	<fmt:message bundle="${loc}" key="local.registration.label.description" var="description"/>
	<fmt:message bundle="${loc}" key="local.registration.toMainPage" var="toMainPage"/>
	<fmt:message bundle="${loc}" key="local.registration.submit" var="submit"/>
	<fmt:message bundle="${loc}" key="local.registration.politic.part1" var="politicPart1"/>
	<fmt:message bundle="${loc}" key="local.registration.politic.part2" var="politicPart2"/>


    <meta charset="utf-8">
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
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

                <a class="login_btn" href="index.jsp">${toMainPage}</a>
            </nav>
        </div>
    </header><!--  end header section  -->

    <section class="registration">

        <form action="main" method="post">
		        <h1>${registration}</h1>
		        <h6>${description}</h6>
		        <hr>

		        <input type="hidden" name="command" value="registration"/>

				<label><b>${username}</b></label>
				<input type="text" name="login" placeholder="${enterUsername}" required/>
			    <c:if test="${requestScope.code == 1}">
			        <p>${incorrectLogin}</p>
			    </c:if>
			    <c:if test="${requestScope.code == 10}">
			        <p>${existLogin}</p>
			    </c:if>

			    <label><b>${password}</b></label>
			    <input type="password" name="password" placeholder="${enterPassword}" required/><br>
			    <c:if test="${requestScope.code == 2}">
			        <p>${incorrectPassword}</p>
			    </c:if>

			    <label><b>${email}</b></label>
			    <input type="text" name="email" placeholder="${enterEmail}" required/><br>
			    <c:if test="${requestScope.code == 3}">
			        <p>${incorrectEmail}</p>
			    </c:if>
			    <c:if test="${requestScope.code == 11}">
			        <p>${existEmail}</p>
			    </c:if>

			    <label><b>${name}</b></label>
			    <input type="text" name="name" placeholder="${enterName}" required/><br>
			    <c:if test="${requestScope.code == 4}">
			        <p>${incorrectName}</p>
			    </c:if>

			    <label><b>${surname}</b></label>
			    <input type="text" name="surname" placeholder="${enterSurname}" required/><br>
			    <c:if test="${requestScope.code == 5}">
			        <p>${incorrectSurname}</p>
			    </c:if>

			    <label><b>${middleName}</b></label>
			    <input type="text" name="middleName" placeholder="${enterMiddleName}"/><br>
			    <c:if test="${requestScope.code == 6}">
			        <p>${incorrectMiddleName}</p>
			    </c:if>

			    <label><b>${sex}</b></label>
                <select name="sex">
                    <option value="male">${sexMale}</option>
                    <option value="female">${sexFemale}</option>
                </select>
			    <c:if test="${requestScope.code == 7}">
			        <p>${incorrectSex}</p>
			    </c:if>

			    <label><b>${dateOfBirth}</b></label>
			    <input type="date" name="dateOfBirth" required/><br>
			    <c:if test="${requestScope.code == 8}">
			        <p>${incorrectDateOfBirth}</p>
			    </c:if>

			    <label><b>${phoneNumber}</b></label>
			    <input type="text" name="phoneNumber" placeholder="${enterPhoneNumber}" required/><br>
			    <c:if test="${requestScope.code == 9}">
			        <p>${incorrectPhoneNumber}</p>
			    </c:if>

		        <hr>
		        <h6>${politicPart1} <a href="#">${politicPart2}</a>.</h6>
		        <input type="submit" class="registerbtn" value="${submit}"/>
		    </div>
		</form>

    </section>
</section><!--  end hero section  -->

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>