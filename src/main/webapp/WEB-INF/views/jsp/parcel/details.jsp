<%@include file="../includes/top.jsp" %>
<%@include file="../includes/navbar.jsp" %>
<div class="container">
<div class="row">
    <div class="col-lg-3">
        <h4><label><spring:message code='parcel.detais'/> ${parcel.id}</label></h4>
        <spring:message code="parcel.name.surname"/>: ${parcel.recipient.name} ${parcel.recipient.surname}<br>
        <spring:message code="newparcel.phone.number"/> ${parcel.recipient.phoneNumber}<br>
        <spring:message code="newparcel.email"/>  ${parcel.recipient.email}<br><br>

        <strong><spring:message code="parcel.address"/></strong><br>
        ${parcel.recipient.street} ${parcel.recipient.streetNumber}<br>
        ${parcel.recipient.postCode}, ${parcel.recipient.city}<br><br>

        <strong><spring:message code="parcel.courier"/></strong><br>
        <spring:message code="parcel.name.surname"/>: ${parcel.courier.name} ${parcel.courier.surname}<br>
        <spring:message code="newparcel.phone.number"/> ${parcel.courier.phoneNumber}<br>
        <spring:message code="newparcel.email"/>  ${parcel.courier.email}<br><br>

        <strong><spring:message code="parcel"/></strong><br>
        <spring:message code="parcel.description"/>: ${parcel.description}<br>
        <spring:message code="parcel.dimensions"/>: ${parcel.parcelInfo.x} x ${parcel.parcelInfo.y} x ${parcel.parcelInfo.z}<br>
        <spring:message code="newparcel.weight"/> ${parcel.parcelInfo.weight}<br>
        <spring:message code="parcel.price"/>: ${parcel.parcelInfo.price}

    </div>
    <div class="col-lg-9" id="map"></div>
</div>
</div>
<script>
    function initMap() {
        var directionsDisplay = new google.maps.DirectionsRenderer;
        var directionsService = new google.maps.DirectionsService;
        var myLatLng = {lat: Number(${parcel.courier.lat}), lng: Number(${parcel.courier.lng})};

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom : 8,
            center : myLatLng
        });
        var marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            title: '<spring:message code="parcel.position"/>'
        });
        var trafficLayer = new google.maps.TrafficLayer();
        trafficLayer.setMap(map);
        directionsDisplay.setMap(map);
        calculateAndDisplayRoute(directionsService, directionsDisplay);
    }

    function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        directionsService.route({
            origin : "Polska, ${parcel.user.address.city }, ${parcel.user.address.street }",
            destination : "Polska, ${parcel.recipient.city }, ${parcel.recipient.street }",
            travelMode : google.maps.TravelMode.DRIVING
        }, function(response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
            }
        });
    }
</script>
<script
        src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">

</script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBQAR809YkzO5lbIQ_dht4OlSFEaznt2T4&callback=initMap">

</script>
<style>
    #map {
        height: 600px;
        width: 75%;
    }
</style>
<%@include file="../includes/bottom.jsp" %>