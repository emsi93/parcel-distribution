<%@include file="../includes/top.jsp" %>
<%@include file="../includes/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <div class="panel panel-warning box">
                <div class="panel-heading">
                    <h4><label><spring:message code='edit.profile.change.password.title'/></label></h4>
                </div>
                <div class="panel-body">
                    <form:form method="post" modelAttribute="changePasswordForm"
                               action="/parcel/distribution/editprofile/changePassword" role="form">

                            <div class="form-group">
                                <label for="oldPassword"><spring:message code='edit.profile.change.password.oldPassword'/></label>
                                <form:input id="oldPassword" name="oldPassword" type="password" path="oldPassword" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="oldPassword" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="newPassword"><spring:message code='edit.profile.change.password.newPassword'/></label>
                                <form:input id="newPassword" name="newPassword" type="password" path="newPassword"
                                            class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="newPassword" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword"><spring:message code='edit.profile.change.password.confirmPassword'/></label>
                                <form:input id="confirmPassword" name="confirmPassword" type="password" path="confirmPassword" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="confirmPassword" element="div"/>
                                </div>
                            </div>
                            <spring:message code='edit.profile.save' var="savePasswordButton"/>
                            <form:input class="submit btn btn-success" path="" value="${savePasswordButton}"
                                        type="submit" id="register"></form:input>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="col-lg-4"></div>
    </div>
</div>
<%@include file="../includes/bottom.jsp" %>