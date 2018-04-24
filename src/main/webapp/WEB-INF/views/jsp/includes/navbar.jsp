<nav class="navbar navbar-inverse" role="navigation">
    <div class="container">

        <div class="navbar-header">
            <a class="navbar-brand" href="/parcel/distribution/content/index">Parcel Distribution App</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-3">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">

                        <c:if test="${role == 'ROLE_ADMIN'}">
                            <li><a href="/parcel/distribution/admin/addcourier"><spring:message
                                    code='addcourier.title'/></a></li>
                            <li class="divider"></li>
                            <li><a href="/parcel/distribution/admin/addadmin"><spring:message
                                    code='addadmin.title'/></a></li>
                            <li class="divider"></li>
                            <li><a href="/parcel/distribution/admin/courierlist"><spring:message
                                    code='addadmin.title'/></a></li>
                        </c:if>
                        <c:if test="${role == 'ROLE_USER'}">
                            <li><a href="/parcel/distribution/parcel/newparcel"><spring:message
                                    code='newparcel.navbar.button'/></a></li>
                            <li class="divider"></li>
                            <li><a href="#">Jeszcze jeden oddzielony link</a></li>
                        </c:if>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code='account.navbar'/>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a><spring:message code="signed.as"/> <strong>${username}</strong></a></li>
                        <li class="divider"></li>
                        <c:if test="${role == 'ROLE_USER'}">
                            <li><a href="/parcel/distribution/editprofile/edit"><spring:message
                                    code='edit.profile.title'/></a></li>
                            <li class="divider"></li>
                        </c:if>
                        <li><a href="<c:url value="/parcel/distribution/logout" />"><spring:message
                                code='logout.button'/></a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<%@include file="languages.jsp" %>