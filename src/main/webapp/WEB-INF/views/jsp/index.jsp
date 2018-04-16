<%@include file="includes/top.jsp" %>
<div class="row error-top">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        Main Page After Login
        <c:if test = "${role == 'ROLE_ADMIN'}">
            <div class="list-group">
                <a class="list-group-item" href="/parcel/distribution/admin/addcourier"><i class="fa fa-home fa-fw"></i>&nbsp; <spring:message code='addcourier.title'/></a>
                <a class="list-group-item" href="/parcel/distribution/admin/addadmin"><i class="fa fa-book fa-fw"></i>&nbsp; <spring:message code='addadmin.title'/></a>
                <a class="list-group-item" href="/parcel/distribution/admin/courierlist"><i class="fa fa-pencil fa-fw"></i>&nbsp; <spring:message code='admin.courierlist'/></a>
                <a class="list-group-item" href="#"><i class="fa fa-cog fa-fw"></i>&nbsp; Settings</a>
            </div>
        </c:if>

        <a href="/parcel/distribution/logout"><spring:message code='logout.button'/></a>
    </div>
    <div class="col-lg-4"></div>
</div>
<%@include file="includes/bottom.jsp" %>