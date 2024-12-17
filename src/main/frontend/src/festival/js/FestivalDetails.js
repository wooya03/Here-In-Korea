import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import "../css/FestivalDetails.css";

const FestivalDetail = () => {
    const { contentId } = useParams(); // URL에서 contentId를 추출
    const [festival, setFestival] = useState(null);

    useEffect(() => {
        const fetchFestivalDetail = async () => {
            try {
                // 상세 정보를 가져오는 API 요청
                const response = await axios.get(`http://localhost:8080/festival/${contentId}`);
                setFestival(response.data); // 상태 업데이트
            } catch (error) {
                console.error("Error fetching festival details:", error);
            }
        };

        fetchFestivalDetail();
    }, [contentId]);

    if (!festival) return <div className="loading">Loading...</div>;

    return (
        <div className="festival-detail-container">
            {/* 제목 */}
            <h1 className="festival-title">{festival.title}</h1>

            {/* 공통 정보 섹션 */}
            <section className="common-info">
                <h2>공통 정보</h2>
                <ul>
                    <li><strong>주소:</strong> {festival.addr1} {festival.addr2}</li>
                    <li><strong>전화번호:</strong> {festival.tel || '정보 없음'}</li>
                    <li><strong>위치:</strong> X: {festival.mapx}, Y: {festival.mapy}</li>
                    <li><strong>등록일:</strong> {festival.createDate}</li>
                    <li><strong>수정일:</strong> {festival.modifiedDate}</li>
                </ul>
            </section>

            {/* 소개 정보 섹션 */}
            <section className="intro-info">
                <h2>소개 정보</h2>
                <p>{festival.description || '소개 정보가 없습니다.'}</p>
            </section>

            {/* 이미지 섹션 */}
            <section className="festival-image">
                {festival.firstimage2 ? (
                    <img src={festival.firstimage2} alt={festival.title} />
                ) : (
                    <img src={`${process.env.PUBLIC_URL}/Image/noimg.png`} alt="No Image" />
                )}
            </section>
        </div>
    );
};

export default FestivalDetail;