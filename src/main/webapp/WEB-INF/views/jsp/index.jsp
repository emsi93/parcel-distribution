<%@include file="includes/top.jsp" %>
<div class="row error-top">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        Main Page After Login
        <div class="list-group">
            <a class="list-group-item" href="#"><i class="fa fa-home fa-fw"></i>&nbsp; Home</a>
            <a class="list-group-item" href="#"><i class="fa fa-book fa-fw"></i>&nbsp; Library</a>
            <a class="list-group-item" href="#"><i class="fa fa-pencil fa-fw"></i>&nbsp; Applications</a>
            <a class="list-group-item" href="#"><i class="fa fa-cog fa-fw"></i>&nbsp; Settings</a>
        </div>
        <a href="/parcel/distribution/logout"><spring:message code='logout.button'/></a>
    </div>
    <div class="col-lg-4"></div>
</div>
<%@include file="includes/bottom.jsp" %>