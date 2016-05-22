<%@page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<html>
<body>
<div>
    <h1>Uživatelé v systému: </h1>
    <table>
        <tr>
            <td>id</td>
            <td>Jmeno</td>
            <td>Prijmeni</td>
            <td>Cislo uctu</td>
            <td>hotovost</td> 
            <td>smazan ucet</td>
            <td>akce</td>
        </tr>
        <c:forEach items="${account}" var="account">
            <tr>
                <td><c:out value="${account.id}"/></td>
                <td><c:out value="${account.birthName}"/></td>
                <td><c:out value="${account.givenName}"/></td>
                <td><c:out value="${account.accountNumber}"/></td>
                <td><c:out value="${account.sumAmount}"/></td>
                <td><c:out value="${account.wasDeleted}"/></td>
                <td><form method="post" action="${pageContext.request.contextPath}/payment/deleteUser?id=${account.id}"
                          style="margin-bottom: 0;"><c:if test="${account.wasDeleted=='false'}"><input type="submit" class="btn" value="Smazat"></c:if></form></td>
            </tr>
        </c:forEach>
    </table>

    <h2>Pro přidání uživatele:</h2>
    <c:if test="${not empty chybaUzivatel}">
        <div style="border: solid 1px red; background-color: yellow; padding: 10px">
            <c:out value="${chybaUzivatel}"/>
        </div>
    </c:if>
    <form action="${pageContext.request.contextPath}/payment/addUser" method="post">
        <table>
            <tr>
                <th>Jméno:</th>
                <td><input type="text" name="birthName" value="<c:out value='${param.birthName}'/>"/></td>
            </tr>
            <tr>
                <th>Příjmeni</th>
                <td><input type="text" name="givenName" value="<c:out value='${param.givenName}'/>"/></td>
            </tr>
            <tr>
                <th>Číslo účtu</th>
                <td><input type="text" name="accountNumber" value="<c:out value='${param.accountNumber}'/>"/></td>
            </tr>
            <tr>
                <th>Počáteční částka</th>
                <td><input type="text" name="sumAmount" value="<c:out value='${param.sumAmount}'/>"/></td>
            </tr>
            <tr><td></td><td><input type="Submit" class="btn" value="Přidat zákazníka" /></td></tr>
        </table>
    </form>
</div>
            
<div>
    <h1>Vytvořené platby: </h1>
    <table>
    <tr>
        <td>id</td>
        <td>odesilatel</td>
        <td>prijemce</td>
        <td>datum vytvoreni</td>
        <td>castka</td> 
        <td>zprava</td>
        <td>akce</td>
    </tr>
    <c:forEach items="${payment}" var="payment">
        <tr>
            <td><c:out value="${payment.id}"/></td>
            <td><c:out value="${payment.sender.birthName} ${payment.sender.givenName}"/></td>
            <td><c:out value="${payment.receiver.birthName} ${payment.receiver.givenName}"/></td>
            <td><c:out value="${payment.date}"/></td>
            <td><c:out value="${payment.amount}"/></td>
            <td><c:out value="${payment.message}"/></td>
            <td><form method="post" action="${pageContext.request.contextPath}/payment/sendPayment?id=${payment.id}"
                      style="margin-bottom: 0;"><c:if test="${payment.sended=='false'}"><input type="submit" class="btn" value="poslat"></c:if></form></td>
        </tr>
    </c:forEach>
    </table>
    
   <h2>Pro Zadání platby</h2>
    <c:if test="${not empty chybaPayment}">
        <div style="border: solid 1px red; background-color: yellow; padding: 10px">
            <c:out value="${chybaPayment}"/>
        </div>
    </c:if>
    <form action="${pageContext.request.contextPath}/payment/addPayment" method="post">
        <table>
            <tr>
                <th>ID odesílatele</th>
                <td><input type="text" name="sender" value="<c:out value='${param.sender}'/>"/></td>
            </tr>
            <tr>
                <th>ID příjemce</th>
                <td><input type="text" name="receiver" value="<c:out value='${param.receiver}'/>"/></td>
            </tr>
            <tr>
                <th>Částka</th>
                <td><input type="text" name="amount" value="<c:out value='${param.amount}'/>"/></td>
            </tr>
            <tr>
                <th>Zpráva</th>
                <td><input type="text" name="message" value="<c:out value='${param.message}'/>"/></td>
            </tr>
            <tr><td></td><td><input type="Submit" class="btn" value="Přidat platbu" /></td></tr>
        </table>
    </form>
</div>
</body>
</html>