<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Parcel Distribution App</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/about"><spring:message code="about"/></a></li>
            <li><a href="/contact"><spring:message code="contact"/></a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/parcel/distribution/registration"><span class="glyphicon glyphicon-user"></span>
                <spring:message code="registration.title"/> </a></li>
            <li><a href="/parcel/distribution/login"><span class="glyphicon glyphicon-log-in"></span> <spring:message
                    code="login.button.send"/></a></li>
        </ul>
    </div>
</nav>
<%@include file="languages.jsp" %>