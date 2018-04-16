<%@include file="../includes/top.jsp" %>
<div class="row error-top">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <div class="panel panel-warning box">
            <div class="panel-heading">
                <h4><label><spring:message code='addadmin.title'/></label></h4>
            </div>
            <div class="panel-body">
                <form:form method="post" modelAttribute="adminForm" action="/parcel/distribution/admin/addadmin" role="form">
                    <div class="form-group">
                        <label for="login"><spring:message code='addadmin.username'/></label>
                        <form:input id="login" name = "login" type="text" path="login" class="form-control"/>
                        <div class="errors">
                            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                            <form:errors path="login" element="div" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email"><spring:message code='addadmin.email'/></label>
                        <form:input id="email" name = "email" type="email" path="email" class="form-control"/>
                        <div class="errors">
                            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                            <form:errors path="email" element="div" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password"><spring:message code='addadmin.password'/></label>
                        <form:input id="password" name="password" type="password" path="password" class="form-control"/>
                        <div class="errors">
                            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                            <form:errors path="password" element="div" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="passwordConfirm"><spring:message code='addadmin.confirm.password'/></label>
                        <form:input id="passwordConfirm" name="passwordConfirm" type="password" path="passwordConfirm" class="form-control"/>
                        <div class="errors">
                            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                            <form:errors path="passwordConfirm" element="div" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email"><spring:message code='addadmin.name'/></label>
                        <form:input id="name" name = "name" type="text" path="name" class="form-control"/>
                        <div class="errors">
                            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                            <form:errors path="name" element="div" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email"><spring:message code='addadmin.surname'/></label>
                        <form:input id="surname" name = "surname" type="text" path="surname" class="form-control"/>
                        <div class="errors">
                            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                            <form:errors path="surname" element="div" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email"><spring:message code='addadmin.phoneNumber'/></label>
                        <form:input id="phoneNumber" name = "phoneNumber" type="text" path="phoneNumber" pattern="\d*" class="form-control"/>
                        <div class="errors">
                            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                            <form:errors path="phoneNumber" element="div" />
                        </div>
                    </div>
                    </br>
                    <spring:message code='addadmin.create.an.account.button' var="createButton"/>
                    <form:input class="submit btn btn-success" path="" type="submit"
                                value="${createButton}" id="register"></form:input>
                </form:form>
            </div>
        </div>
    </div>
    <div class="col-lg-4"></div>
</div>
<%@include file="../includes/bottom.jsp" %>