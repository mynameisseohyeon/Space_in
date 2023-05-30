function createMarker(position, index) {
    var marker = new kakao.maps.Marker({
        position: position,
    });

    marker.setMap(map);

    kakao.maps.event.addListener(marker, 'click', function() {
        map.setCenter(position);
    });

    return marker;
}

function displayPlaces(places) {
    var bounds = new kakao.maps.LatLngBounds();

    placesList.innerHTML = '';

    for (var i = 0; i < places.length; i++) {
        var place = places[i];
        var itemEl = getListItem(i, place);

        bounds.extend(new kakao.maps.LatLng(place.y, place.x));

        placesList.appendChild(itemEl);

        // 마커 생성 및 지도에 표시
        var markerPosition = new kakao.maps.LatLng(place.y, place.x);
        var marker = createMarker(markerPosition, i);

        // 마커 정보를 목록 아이템에 저장
        itemEl.setAttribute('data-marker-index', i);

        // 목록 아이템 클릭 시 해당 위치로 지도 이동
        itemEl.addEventListener('click', function() {
            var clickedMarkerIndex = this.getAttribute('data-marker-index');
            var clickedMarker = markers[clickedMarkerIndex];
            map.setCenter(clickedMarker.getPosition());
        });
    }

    map.setBounds(bounds);
}