<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
hello world
<h3> employee information: </h3>
<c:forEach var="emp" items="${employees}">

			<li>${emp}</li>

		</c:forEach>

