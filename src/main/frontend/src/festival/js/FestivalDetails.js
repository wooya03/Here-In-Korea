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
                // API 요청: contentId에 해당하는 축제 상세 정보를 가져옴
                const response = await axios.get(`http://localhost:8080/festival/${contentId}`);
                setFestival(response.data); // 상태 업데이트
            } catch (error) {
                console.error("Error fetching festival details:", error);
            }
        };

        fetchFestivalDetail();
    }, [contentId]);

    return (
        <div className="festival-detail-container">
            {/* 제목 */}
            <h1 className="festival-title">{festival ? festival.title : '축제 제목 로딩 중...'}</h1>

            {/* 공통 정보 섹션 */}
            <section className="common-info">
                <h2>공통 정보</h2>
                <div className="info-content">
                    {/* 사진 영역 */}
                    <div className="info-image">
                        {festival ? (
                            festival.firstimage2 ? (
                                <img src={festival.firstimage2} alt={festival.title} />
                            ) : (
                                <img src={`${process.env.PUBLIC_URL}/Image/noimg.png`} alt="No Image" />
                            )
                        ) : (
                            <img src={`${process.env.PUBLIC_URL}/Image/noimg.png`} alt="No Image" />
                        )}
                    </div>

                    {/* 텍스트 정보 영역 */}
                    <div className="info-text">
                        <ul>
                            <li><span className="info-label">주소:</span> {festival ? `${festival.addr1} ${festival.addr2}` : '정보 로딩 중...'}</li>
                            <li><span className="info-label">전화번호:</span> {festival ? festival.tel || '정보 없음' : '정보 로딩 중...'}</li>
                            <li><span className="info-label">등록일:</span> {festival ? festival.createDate || '정보 없음' : '정보 로딩 중...'}</li>
                            <li><span className="info-label">수정일:</span> {festival ? festival.modifiedDate || '정보 없음' : '정보 로딩 중...'}</li>
                            <li><span className="info-label">축제 개요:</span> {festival ? festival.overview || '정보 없음' : '정보 로딩 중...'}</li>
                        </ul>
                    </div>
                </div>
            </section>

            {/* 소개 정보 섹션 */}
            <section className="intro-info">
                <h2>소개 정보</h2>
                <ul>
                    <li><span className="info-label">주최자:</span> {festival ? festival.sponsor1 || '정보 없음' : '정보 로딩 중...'}</li>
                    <li><span className="info-label">주최자 연락처:</span> {festival ? festival.sponsor1tel || '정보 없음' : '정보 로딩 중...'}</li>
                    <li><span className="info-label">행사 시작일:</span> {festival ? festival.eventstartdate || '정보 없음' : '정보 로딩 중...'}</li>
                    <li><span className="info-label">행사 종료일:</span> {festival ? festival.eventenddate || '정보 없음' : '정보 로딩 중...'}</li>
                    <li><span className="info-label">공연 시간:</span> {festival ? festival.playtime || '정보 없음' : '정보 로딩 중...'}</li>
                    <li><span className="info-label">행사 장소:</span> {festival ? festival.eventplace || '정보 없음' : '정보 로딩 중...'}</li>
                    <li><span className="info-label">이용 요금:</span> {festival ? festival.usetimefestival || '정보 없음' : '정보 로딩 중...'}</li>
                </ul>
            </section>

        </div>
    );
};

export default FestivalDetail;
