<%@include file="../includes/top.jsp" %>
<%@include file="../includes/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-lg-2"></div>
        <div class="col-lg-8">
            <h4><label><spring:message code='contact.list'/></label></h4>
            <table class="table table-striped" style="width:100%">
                <thead>
                <tr>
                    <th>LP.</th>
                    <th><spring:message code='contact.name'/></th>
                    <th><spring:message code='contact.surname'/></th>
                    <th><spring:message code='contact.email'/></th>
                    <th><spring:message code='contact.phoneNumber'/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${recipientList }" var="recipient" varStatus="loop">
                    <tr>
                        <td>${recipient.id}</td>
                        <td>${recipient.name}</td>
                        <td>${recipient.surname}</td>
                        <td>${recipient.email}</td>
                        <td>${recipient.phoneNumber}</td>
                        <td><a href="<c:url value='/parcel/distribution/parcel/newparcel/${recipient.id}' />" class="btn btn-success btn-sm" role="button"><spring:message code='contact.new.package'/></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-lg-2"></div>
    </div>
</div>
<%@include file="../includes/bottom.jsp" %>