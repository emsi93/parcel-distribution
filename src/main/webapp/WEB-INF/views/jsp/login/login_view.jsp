<%@include file="../includes/top.jsp" %>
<%@include file="../includes/navbar2.jsp" %>
<div class="container">
<div class="row">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <center><img src="/resources/images/user.png"></center>

        <div class="panel panel-warning box">
            <div class="panel-heading">
                <h4><label><spring:message code='login.title'/></label></h4>
            </div>
            <div class="panel-body">
                <form action="/parcel/distribution/login" method="post">
                    <label for="login"><spring:message code='login.username'/></label>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input id="login" name="login" type="text" class="form-control">
                    </div>
                    </br>
                    <label for="password"><spring:message code='login.password'/></label>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        <input id="password" name="password" type="password" class="form-control">
                    </div>
                    </br>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-success"><spring:message code='login.button.send'/></button>
                    <span style="float:right"><a href="/password/emailForm"><spring:message
                            code='login.forgot.password'/></a></span>
                </form>
            </div>
            <div class="panel-footer">
                <spring:message code='login.new.user'/> <a href="/parcel/distribution/registration"><spring:message
                    code='login.create.account'/></a>
            </div>
        </div>
    </div>
    <div class="col-lg-4"></div>
</div>
</div>
<%@include file="../includes/bottom.jsp" %>