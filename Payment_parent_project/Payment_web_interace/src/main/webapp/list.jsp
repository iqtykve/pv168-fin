<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<table border="1">
    <thead>
    <tr>
        <td>Jmeno</td>
        <td>Prijmeni</td>
        <td>Cislo uctu</td>
        <td>hotovost</td>        
    </tr>
    </thead>
    <c:forEach items="${payment}" var="payment">
        <tr>
            <td><c:out value="${payment.birthName}"/></td>
            <td><c:out value="${payment.givenName}"/></td>
            <td><c:out value="${payment.accountNumber}"/></td>
            <td><c:out value="${payment.sumAmount}"/></td>
            <td><form method="post" action="${pageContext.request.contextPath}/books/delete?id=${payment.id}"
                      style="margin-bottom: 0;"><input type="submit" value="Smazat"></form></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>