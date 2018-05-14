<%@include file="../includes/top.jsp" %>
<%@include file="../includes/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
            <div class="panel panel-warning box">
                <div class="panel-heading">
                    <h4><label><spring:message code='newparcel.title'/></label></h4>
                </div>
                <div class="panel-body">
                    <form:form method="post" modelAttribute="descriptionForm" action="/parcel/distribution/parcel/newparcel/${idRecipient}"
                               role="form">

                            <h4><label><spring:message code='newparcel.parcelinfo'/></label></h4>
                            <div class="form-group">
                                <label for="description"><spring:message code='newparcel.description'/></label>
                                <form:input id="description" name="description" type="text" path="description"
                                            class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="description" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="x">x</label>
                                <form:input id="x" name="x" type="number" path="x" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="x" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="y">y</label>
                                <form:input id="y" name="y" type="number" path="y" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="y" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="z">z</label>
                                <form:input id="z" name="z" type="number" path="z" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="z" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="weight"><spring:message code='newparcel.weight'/></label>
                                <form:input id="weight" name="weight" type="number" path="weight" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="weight" element="div"/>
                                </div>
                            </div>

                            <button class="submit btn btn-success" type="button" id="return2"><spring:message
                                    code='return'/></button>
                            <spring:message code='newparcel.finish' var="createButton"/>
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