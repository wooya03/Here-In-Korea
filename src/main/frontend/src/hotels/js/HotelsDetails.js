import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import "../css/HotelsDetails.css";

const HotelDetails = () => {
    const roomData = [
        {
            name: '아랫방',
            size: '5',
            bed: '2',
            type: '더블',
            price: '₩135,000',
            amenities: ['인터넷', '에어컨', 'TV'],
        },
        {
            name: '사랑방',
            size: '4',
            bed: '1',
            type: '싱글',
            price: '₩85,000',
            amenities: ['냉장고', '소파', 'PC'],
        },
        // 더 많은 객실 데이터를 여기에 추가
    ];
  
    return (
        <div className="hotel-detail">
            <div className="hotel-content">
                <div className="room-info">
                    <div className="room-header">
                        <h1>한옥1957</h1>
                        <h2>객실 정보 ___</h2>
                    </div>
                    <div>
                    {roomData.map((room, index) => (
                        <div className="room" key={index}>
                            <h4>{room.name}-</h4>
                            <div className='room-section'>
                                <p>객실 크기(평): {room.size}</p>
                                <p>침대: {room.bed}</p>
                                <p>객실 타입: {room.type}</p>
                            </div>
                            <div className='room-section'>
                                <p>최대 인원: {room.bed}</p>
                                <p>비수기 주중: {room.price}</p>
                                <p>목욕 시설: {room.amenities.join(', ')}</p>
                            </div>
                        </div>
                    ))}
                    </div>
                </div>
    
                <div className="hotel-info">
                    <div className="hotel-sinfo">
                        <p>숙소 주소, 연락처 등 상세 정보를 여기에 입력하세요.</p>
                    </div>
                    <div className="map">
                        <iframe
                            src="https://www.google.com/maps/embed?pb=..."
                            width="100%"
                            height="300"
                            style={{ border: 0 }}
                            allowFullScreen=""
                            loading="lazy"
                            referrerPolicy="no-referrer-when-downgrade"></iframe>
                    </div>
                </div>
            </div>
        </div>
    );
  };
  
export default HotelDetails;