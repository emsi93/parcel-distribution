<%@include file="../includes/top.jsp" %>
<%@include file="../includes/navbar.jsp" %>
<div class="container">
<div class="row">
    <div class="col-lg-2"></div>
    <div class="col-lg-8">
        <h4><label><spring:message code='admin.userlist'/></label></h4>
        <table class="table table-striped" style="width:100%">
            <thead>
            <tr>
                <th>LP.</th>
                <th><spring:message code='admin.userlist.name'/></th>
                <th><spring:message code='admin.userlist.surname'/></th>
                <th><spring:message code='admin.userlist.email'/></th>
                <th><spring:message code='admin.userlist.phoneNumber'/></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${userList }" var="user" varStatus="loop">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.email}</td>
                <td>${user.phoneNumber}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-lg-2"></div>
</div>
</div>
<%@include file="../includes/bottom.jsp" %>