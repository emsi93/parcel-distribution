<%@include file="../includes/top.jsp" %>
<%@include file="../includes/navbar2.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <div class="panel panel-warning box">
                <div class="panel-heading">
                    <h4><label><spring:message code='registration.title'/></label></h4>
                </div>
                <div class="panel-body">
                    <form:form method="post" modelAttribute="userForm" action="/parcel/distribution/registration"
                               role="form">
                        <div class="form-group">
                            <label for="login"><spring:message code='registration.username'/></label>
                            <form:input id="login" name="login" type="text" path="login" class="form-control"/>
                            <div class="errors">
                                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                                <form:errors path="login" element="div"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email"><spring:message code='registration.email'/></label>
                            <form:input id="email" name="email" type="email" path="email" class="form-control"/>
                            <div class="errors">
                                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                                <form:errors path="email" element="div"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password"><spring:message code='registration.password'/></label>
                            <form:input id="password" name="password" type="password" path="password"
                                        class="form-control"/>
                            <div class="errors">
                                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                                <form:errors path="password" element="div"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="passwordConfirm"><spring:message code='registration.confirm.password'/></label>
                            <form:input id="passwordConfirm" name="passwordConfirm" type="password"
                                        path="passwordConfirm" class="form-control"/>
                            <div class="errors">
                                <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                                <form:errors path="passwordConfirm" element="div"/>
                            </div>
                        </div>
                        </br>
                        <spring:message code='registration.create.an.account.button' var="createButton"/>
                        <form:input class="submit btn btn-success" path="" type="submit"
                                    value="${createButton}" id="register"></form:input>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-lg-4"></div>
    </div>
</div>
<%@include file="../includes/bottom.jsp" %>