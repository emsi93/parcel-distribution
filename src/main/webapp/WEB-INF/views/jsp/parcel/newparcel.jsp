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
                    <form:form method="post" modelAttribute="parcelForm" action="/parcel/distribution/parcel/newparcel"
                               role="form">
                        <div id="step1">
                            <h4><label><spring:message code='newparcel.recipient'/></label></h4>
                            <div class="form-group">
                                <label for="name"><spring:message code='newparcel.name'/></label>
                                <form:input id="name" name="name" type="text" path="name" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="name" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="surname"><spring:message code='newparcel.surname'/></label>
                                <form:input id="surname" name="surname" type="text" path="surname"
                                            class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="surname" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="phoneNumber"><spring:message code='newparcel.phone.number'/></label>
                                <form:input id="phoneNumber" name="phoneNumber" type="text" path="phoneNumber"
                                            pattern="\d*" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="phoneNumber" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="email"><spring:message code='newparcel.email'/></label>
                                <form:input id="email" name="email" type="email" path="email" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="email" element="div"/>
                                </div>
                            </div>
                            <button class="submit btn btn-success" type="button" id="next1"><spring:message
                                    code='next'/></button>
                        </div>

                        <div id="step2" style="display: none;">
                            <h4><label><spring:message code='newparcel.address'/></label></h4>
                            <div class="form-group">
                                <label for="street"><spring:message code='newparcel.street'/></label>
                                <form:input id="street" name="street" type="text" path="street" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="street" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="streetNumber"><spring:message code='newparcel.street.number'/></label>
                                <form:input id="streetNumber" name="streetNumber" type="text" path="streetNumber"
                                            class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="streetNumber" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="flatNumber"><spring:message code='newparcel.flat.number'/></label>
                                <form:input id="flatNumber" name="flatNumber" type="text" path="flatNumber"
                                            class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="flatNumber" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="postCode"><spring:message code='newparcel.postcode'/></label>
                                <form:input id="postCode" name="postCode" type="text" path="postCode"
                                            pattern="[0-9]{2}\-[0-9]{3}" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="postCode" element="div"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="city"><spring:message code='newparcel.city'/></label>
                                <form:input id="city" name="city" type="text" path="city" class="form-control"/>
                                <div class="errors">
                                    <span class="closebtn"
                                          onclick="this.parentElement.style.display='none';">&times;</span>
                                    <form:errors path="city" element="div"/>
                                </div>
                            </div>
                            <button class="submit btn btn-success" type="button" id="return2"><spring:message
                                    code='return'/></button>
                            <button class="submit btn btn-success" type="button" id="next2"><spring:message
                                    code='next'/></button>
                        </div>

                        <div id="step3" style="display: none;">
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

                            <button class="submit btn btn-success" type="button" id="return3"><spring:message
                                    code='return'/></button>
                            <spring:message code='newparcel.finish' var="createButton"/>
                            <form:input class="submit btn btn-success" path="" type="submit"
                                        value="${createButton}" id="register"></form:input>
                        </div>

                    </form:form>

                </div>
            </div>
        </div>
        <div class="col-lg-4"></div>
    </div>
</div>
<%@include file="../includes/bottom.jsp" %>