<div class="container">
    <div align="right">
        <c:if test="${role == 'ROLE_GUEST'}">
                <input type="text" id="input" placeholder="ID">
                <a href='' onclick="this.href='/location/'+document.getElementById('input').value" class="btn btn-success btn-sm" role="button"><spring:message
                        code="parcel.find"/></a>
        </c:if>
        <c:if test="${role == 'ROLE_EMPLO'}">
            <input type="text" id="input" placeholder="ID">
            <a href='' onclick="this.href='/warehouse/'+document.getElementById('input').value" class="btn btn-success btn-sm" role="button"><spring:message
                    code="warehouse.button"/></a>
        </c:if>
        <h6><span><label><spring:message code='languages'/></label>
                <a href="" onclick="changeLanguage('en')"><spring:message code='english'/></a>
                <a href="" onclick="changeLanguage('pl')"><spring:message code='polish'/></a></span></h6>
    </div>
</div>