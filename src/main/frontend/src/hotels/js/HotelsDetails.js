import React, { useEffect, useState, useRef } from 'react';
import axios from 'axios';
import { format } from "date-fns";
import { useParams } from 'react-router-dom';
import "../css/HotelsDetails.css";

function formatTime(dateString) {
    if (!dateString) return "날짜 정보 없음"; 
    const date = new Date(dateString);
    if (isNaN(date.getTime())) {
        return "잘못된 날짜";
    }
    return format(date, "yyyy-MM-dd h:mm:ss a");
}

const HotelDetails = () => {
    const mapRef = useRef(null);
    const baseUrl = "http://localhost:8080";
    const [data, setData] = useState([]);
    const [map, setMap] = useState(null);
    const { id } = useParams();
    
    useEffect(() => {
        axios.get(baseUrl + `/hotels/${id}`)
            .then((res) => {
                const item = res.data;
                setData(item);
            })
            .catch((error) => {
                console.error('질문 데이터 오류:', error);
            });
    }, [id]);
    
    useEffect(() => {
        const initializeMap = () => {
            if (data.mapx && data.mapy) { // 좌표값이 존재할 경우에만 지도 초기화
                const mapInstance = new window.google.maps.Map(mapRef.current, {
                    center: { lat: data.mapy, lng: data.mapx },
                    zoom: 16,
                });
    
                // 마커 추가
                const marker = new window.google.maps.Marker({
                    position: { lat: data.mapy, lng: data.mapx },
                    map: mapInstance,
                    title: data.title || "호텔 위치"
                });
    
                setMap(mapInstance);
            }
        };
    
        if (window.google && data.mapx && data.mapy) { // Google Maps API가 로드되었고 좌표값이 있을 때만 실행
            initializeMap();
        } else if (!window.google) {
            console.error('Google Maps API가 로드되지 않았습니다.');
        }
    }, [data.mapx, data.mapy]);
  
    return (
        <div className="hotel-detail">
            <div className="hotel-content">
                <div className="room-info">
                    <div className="room-header">
                        <h1>{data.title}</h1>  
                        <h2>객실 정보 ___</h2>
                    </div>
                    <div>
                    {data.rooms && data.rooms.map((room, index) => (
                        <div className="hotel-room" key={index}>
                            <h3>{room.roomtitle}-</h3>
                            <div className="room">
                            <div className='room-section'>
                                <p>객실 크기(평): {room.roomsize1}</p>
                                <p>객실수: {room.roomcount}</p>
                                <p>기존 인원: {room.roombasecount}</p>
                                <p>최대 인원: {room.roommaxcount}</p>
                                <p>비수기 주중: {room.roomoffseasonminfee1}</p>
                                <p>비수기 주말: {room.roomoffseasonminfee2}</p>
                                <p>객실 소개: {room.roomintro}</p>
                            </div>
                            <div className='room-section'>
                                <p>목욕 시설: {room.roombathfacility}</p>
                                <p>욕조: {room.roombath}</p>
                                <p>홈시어터: {room.roomhometheater}</p>
                                <p>에어컨: {room.roomaircondition}</p>
                                <p>TV: {room.roomtv}</p>
                                <p>PC: {room.roompc}</p>
                                <p>케이블 설치: {room.roomcable}</p>
                            </div>
                            <div className='room-section'>
                                <p>인터넷: {room.roominternet}</p>
                                <p>냉장고: {room.roomrefrigerator}</p>
                                <p>세먼도구: {room.roomtoiletries}</p>
                                <p>소파: {room.roomsofa}</p>
                                <p>취사용품: {room.roomcook}</p>
                                <p>테이블: {room.roomtable}</p>
                                <p>드라이기: {room.roomhairdryer}</p>
                            </div>
                            </div>
                            <div className='room-card'>
                                {room.roomimg1 ? <img className='room-image' src={room.roomimg1} alt="" /> : "이미지가 존재하지 않습니다" }
                                {room.roomimg2 ? <img className='room-image' src={room.roomimg2} alt="" /> : "" }
                                {room.roomimg3 ? <img className='room-image' src={room.roomimg3} alt="" /> : "" }
                                {room.roomimg4 ? <img className='room-image' src={room.roomimg4} alt="" /> : "" }
                                {room.roomimg5 ? <img className='room-image' src={room.roomimg5} alt="" /> : "" }
                            </div>
                        </div>
                    ))}
                    </div>
                </div>
    
                <div className="hotel-info">
                    <div className="hotel-sinfo">
                        <p>최초 등록일 : {formatTime(data.createDate)} </p>
                        <p>최종 수정일 : {formatTime(data.modifiedDate)} </p> 
                        <p>연락처 : {data.tel} </p>
                        <p>주소 : {data.addr1} {data.addr2}</p>
                    </div>
                    <div className="thum-img">{data.firstimage2 ? (
                                <img src={data.firstimage2} alt="" />
                            ) : (
                                <img src={`${process.env.PUBLIC_URL}/Image/noimg.png`} alt="no_img" width="100%" height="100%" />
                            )}</div>
                
                    <div className="map">
                        <div ref={mapRef} style={{ width: '100%', height: '300px' }}></div>
                    </div>
                </div>
            </div>
        </div>
    );
  };
  
export default HotelDetails;