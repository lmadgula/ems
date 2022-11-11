<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3> department information: </h3>
<c:forEach var="dep" items="${departments}">

			<li>${dep}</li>

		</c:forEach>

