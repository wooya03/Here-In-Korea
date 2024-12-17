import React, { useEffect, useRef } from 'react';

const GoogleMaps = () => {
  const mapRef = useRef(null); 

  useEffect(() => {
    const initializeMap = () => {
      const map = new window.google.maps.Map(mapRef.current, {
        center: { lat: 37.5665, lng: 126.978 }, 
        zoom: 14, 
      });
    };
    if (window.google) {
      initializeMap();
    } else {
      console.error('Google Maps API가 로드되지 않았습니다.');
    }
  }, []);

  return (
    <div className='wrap_maps_window'
      ref={mapRef}
      style={{ width: '100%', height: '500px' }} 
    ></div>
  );
};

export default GoogleMaps;
