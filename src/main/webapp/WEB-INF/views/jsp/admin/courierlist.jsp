<%@include file="../includes/top.jsp" %>
<%@include file="../includes/navbar.jsp" %>
<div class="container">
<div class="row">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <h4><label><spring:message code='admin.courierlist'/></label></h4>
        <table class="table">
            <thead>
            <tr>
                <th>LP.</th>
                <th><spring:message code='admin.courierlist.name'/></th>
                <th><spring:message code='admin.courierlist.surname'/></th>
                <th><spring:message code='admin.courierlist.email'/></th>
                <th><spring:message code='admin.courierlist.phoneNumber'/></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${courierList }" var="courier" varStatus="loop">
            <tr>
                <td>${courier.id}</td>
                <td>${courier.name}</td>
                <td>${courier.surname}</td>
                <td>${courier.email}</td>
                <td>${courier.phoneNumber}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-lg-4"></div>
</div>
</div>
<%@include file="../includes/bottom.jsp" %>