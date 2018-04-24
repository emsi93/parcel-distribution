<%@include file="../includes/top.jsp" %>
<%@include file="../includes/navbar.jsp" %>
<div class="container">
<div class="row">
    <div class="col-lg-4"></div>
    <div class="col-lg-4">
        <div class="panel panel-warning box">
            <div class="panel-heading">
                <h4><label><spring:message code='edit.profile.address.title'/></label></h4>
            </div>
            <div class="panel-body">
                <form:form method="post" modelAttribute="addressForm" action="/parcel/distribution/editprofile/edit_address" role="form">
                     <div class="form-group">
                         <label for="street"><spring:message code='edit.profile.street'/></label>
                         <form:input id="street" name = "street" type="text" path="street" class="form-control"/>
                         <div class="errors">
                             <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                             <form:errors path="street" element="div" />
                         </div>
                     </div>
                     <div class="form-group">
                         <label for="streetNumber"><spring:message code='edit.profile.street.number'/></label>
                         <form:input id="streetNumber" name="streetNumber" type="text" path="streetNumber" class="form-control"/>
                         <div class="errors">
                             <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                             <form:errors path="streetNumber" element="div" />
                         </div>
                     </div>
                     <div class="form-group">
                         <label for="flatNumber"><spring:message code='edit.profile.flat.number'/></label>
                         <form:input id="flatNumber" name="flatNumber" type="text" path="flatNumber" class="form-control"/>
                         <div class="errors">
                             <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                             <form:errors path="flatNumber" element="div" />
                         </div>
                     </div>
                     <div class="form-group">
                         <label for="postCode"><spring:message code='edit.profile.postcode'/></label>
                         <form:input id="postCode" name="postCode" type="text" path="postCode" pattern="[0-9]{2}\-[0-9]{3}" class="form-control"/>
                         <div class="errors">
                             <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                             <form:errors path="postCode" element="div" />
                         </div>
                     </div>
                     <div class="form-group">
                         <label for="city"><spring:message code='edit.profile.city'/></label>
                         <form:input id="city" name="city" type="text" path="city" class="form-control" />
                         <div class="errors">
                             <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                             <form:errors path="city" element="div" />
                         </div>
                     </div>
                    </br>
                    <spring:message code='edit.profile.save' var="savePasswordButton"/>
                    <form:input class="submit btn btn-success" path="" value="${savePasswordButton}" type="submit" id="register"></form:input>
                </form:form>
            </div>
        </div>
    </div>
    <div class="col-lg-4"></div>
</div>
</div>
<%@include file="../includes/bottom.jsp" %>