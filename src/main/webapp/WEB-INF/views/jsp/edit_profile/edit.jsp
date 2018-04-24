<%@include file="../includes/top.jsp" %>
<%@include file="../includes/navbar.jsp" %>
<div class="container">
<div class="row">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <div class="panel panel-warning box">
            <div class="panel-heading">
                <h4><label><spring:message code='edit.profile.title'/></label></h4>
            </div>
            <div class="panel-body">
                <form:form method="post" modelAttribute="firstForm" action="/parcel/distribution/editprofile/edit" role="form">
                    <div class="form-group">
                        <label for="name"><spring:message code='edit.profile.name'/></label>
                        <form:input id="name" name = "name" type="text" path="name" class="form-control"/>
                        <div class="errors">
                            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                            <form:errors path="name" element="div" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="surname"><spring:message code='edit.profile.surname'/></label>
                        <form:input id="surname" name = "surname" type="text" path="surname" class="form-control"/>
                        <div class="errors">
                            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                            <form:errors path="surname" element="div" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber"><spring:message code='edit.profile.phone.number'/></label>
                        <form:input id="phoneNumber" name = "phoneNumber" type="text" path="phoneNumber" pattern="\d*" class="form-control"/>
                        <div class="errors">
                            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                            <form:errors path="phoneNumber" element="div" />
                        </div>
                    </div>
                    <spring:message code='edit.profile.next' var="nextPasswordButton"/>
                    </br>
                    <form:input class="submit btn btn-success" path="" value="${nextPasswordButton}" type="submit"></form:input>
                </form:form>
            </div>
        </div>
    </div>
    <div class="col-lg-4"></div>
</div>
</div>
<%@include file="../includes/bottom.jsp" %>