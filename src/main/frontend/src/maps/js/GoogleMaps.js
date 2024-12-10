import React, {useEffect, useRef} from "react";
import "../css/GoogleMaps.css"

const GoogleMaps = () => {
    const mapRef = useRef(null); // 지도를 렌더링할 DOM 요소를 참조

    useEffect(() => {
        // Google Maps 초기화
        const initMap = () => {
            const mapOptions = {
                center: { lat: 35.871435, lng: 128.601445 }, // 위도와 경도 설정
                zoom: 12, // 지도 확대/축소 레벨
            };

            // mapRef.current에 Google Map 객체 생성
            new window.google.maps.Map(mapRef.current, mapOptions);
        };

        // Google Maps API 로드
        if (!window.google) {
            const script = document.createElement("script");
            script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyDPGmF6ZJbdsPCv8JZxlq6kruAmNJWz5H8';
            script.async = true;
            script.defer = true;
            document.body.appendChild(script);

            script.onload = () => initMap();
        } else {
            initMap();
        }
    }, []);
    return (
        <div className="wrap_content">
            <div className="wrap_maps_window" ref={mapRef}></div>
        </div>
    )

}

export default GoogleMaps