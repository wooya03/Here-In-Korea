import React, { useEffect, useRef, useState } from 'react';

const GoogleMaps = () => {
  const mapRef = useRef(null);
  const [places, setPlaces] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');
  const [map, setMap] = useState(null);
  const [markers, setMarkers] = useState([]); // 마커를 관리하는 상태 추가

  useEffect(() => {
    const initializeMap = () => {
      const mapInstance = new window.google.maps.Map(mapRef.current, {
        center: { lat: 37.5665, lng: 126.978 },
        zoom: 14,
      });

      setMap(mapInstance);
    };

    if (window.google) {
      initializeMap();
    } else {
      console.error('Google Maps API가 로드되지 않았습니다.');
    }
  }, []);

  // 검색어 입력 처리
  const handleSearchInputChange = (event) => {
    setSearchQuery(event.target.value);
  };

  // 검색 버튼 클릭 처리
  const handleSearchSubmit = () => {
    if (searchQuery.trim()) {
      if (map) {
        // 기존 마커 초기화
        markers.forEach((marker) => marker.setMap(null)); // 기존 마커 제거
        setMarkers([]); // 마커 상태 초기화

        const service = new window.google.maps.places.PlacesService(map);
        const request = {
          query: searchQuery,
          fields: ['name', 'geometry'],
        };

        service.textSearch(request, (results, status) => {
          if (status === window.google.maps.places.PlacesServiceStatus.OK) {
            setPlaces(results);

            // 검색된 장소에 마커 추가 및 지도 이동
            results.forEach((place) => {
              const marker = new window.google.maps.Marker({
                map,
                position: place.geometry.location,
                title: place.name,
              });
              setMarkers((prevMarkers) => [...prevMarkers, marker]);

              // 지도 중심을 검색된 장소로 이동
              map.setCenter(place.geometry.location);
              map.setZoom(14); // 지도 줌 설정
            });
          } else {
            console.error('장소 검색에 실패했습니다:', status);
          }
        });
      }
    }
  };

  return (
    <div>
      <div>
        <input
          type="text"
          value={searchQuery}
          onChange={handleSearchInputChange}
          placeholder="장소를 검색하세요"
          style={{ width: '100%', padding: '10px', marginBottom: '10px' }}
        />
        <button onClick={handleSearchSubmit} style={{ width: '100%' }}>
          검색
        </button>
      </div>
      <div className="wrap_maps_window" ref={mapRef} style={{ width: '100%', height: '400px' }}></div>
    </div>
  );
};

export default GoogleMaps;