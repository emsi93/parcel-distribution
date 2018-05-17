<%@include file="../includes/top.jsp" %>
<%@include file="../includes/navbar.jsp" %>
<div class="container">
<div class="row">
    <div class="col-lg-2"></div>
    <div class="col-lg-8">
        <h4><label><spring:message code='parcel.list'/></label></h4>
        <table class="table table-striped" style="width:100%">
            <thead>
            <tr>
                <th>LP.</th>
                <th><spring:message code='parcel.description'/></th>
                <th><spring:message code='parcel.name'/></th>
                <th><spring:message code='parcel.surname'/></th>
                <th><spring:message code='parcel.address'/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${parcelList }" var="parcel" varStatus="loop">
            <tr>
                <td>${parcel.id}</td>
                <td>${parcel.description}</td>
                <td>${parcel.recipient.name}</td>
                <td>${parcel.recipient.surname}</td>
                <td>${parcel.recipient.street}, ${parcel.recipient.city} </td>
                <td><a href="<c:url value='/parcel/distribution/parcel/details/${parcel.id}' />" class="btn btn-success btn-sm" role="button"><spring:message code="details"/></a></td>
                <td><a href="<c:url value='/parcel/distribution/download/parcel/${parcel.id}' />" class="btn btn-success btn-sm" role="button">PDF</a></td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-lg-2"></div>
</div>
</div>
<%@include file="../includes/bottom.jsp" %>