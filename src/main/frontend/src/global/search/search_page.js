import React, { useState, useEffect } from "react";
import axios from "axios";
import "./search_page.css";

const SearchPage = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [hotels, setHotels] = useState([]); // 숙박 정보
  const [searchHistory, setSearchHistory] = useState([]); // 검색 기록

  // 검색 함수
  const handleSearch = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/hotels/search?title=${searchTerm}`);
      setHotels(response.data); // 숙박 정보는 백엔드에서 가져온 데이터로 업데이트

      // 검색어 기록 저장 (최대 20개)
      if (searchHistory.length >= 20) {
        setSearchHistory((prevHistory) => [searchTerm, ...prevHistory.slice(0, 19)]);
      } else {
        setSearchHistory((prevHistory) => [searchTerm, ...prevHistory]);
      }
    } catch (error) {
      console.error("숙박 정보 검색 오류", error);
    }
  };

  // 초기화 함수
  const handleReset = () => {
    setSearchTerm("");
    setHotels([]); // 숙박 정보 초기화
  };

  // 검색어 삭제 함수
  const handleDeleteSearchTerm = (termToDelete) => {
    setSearchHistory((prevHistory) => prevHistory.filter((term) => term !== termToDelete));
  };

  // 지역 이름 변환 함수
  const areastring = (res) => {
    switch (res) {
      case 1: return "#서울";
      case 2: return "#인천";
      case 3: return "#대전";
      case 4: return "#대구";
      case 5: return "#광주";
      case 6: return "#부산";
      case 7: return "#울산";
      case 8: return "#세종";
      case 31: return "#경기";
      case 32: return "#강원";
      case 33: return "#충북";
      case 34: return "#충남";
      case 35: return "#경북";
      case 36: return "#경남";
      case 37: return "#전북";
      case 38: return "#전남";
      case 39: return "#제주";
      default: return "#미정";
    }
  };

  return (
    <div className="search-page">
      <h1 className="search-results-title">검색결과</h1>

      {/* 검색 입력 필드 */}
      <div className="search-input-container">
        <input
          type="text"
          className="search-input"
          placeholder="검색어를 입력하세요"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <button className="search-page-button" onClick={handleSearch}>검색</button>
      </div>

      {/* 검색 기록 */}
      <div className="search-history">
        <h3>최근 검색어</h3>
        <ul>
          {searchHistory.length > 0 ? (
            searchHistory.map((term, index) => (
              <li key={index} className="search-term-item">
                <span>{term}</span>
                <button className="delete_button" onClick={() => handleDeleteSearchTerm(term)}>X</button>
              </li>
            ))
          ) : (
            <p>검색 기록이 없습니다.</p>
          )}
        </ul>
      </div>

      {/* 검색 결과 */}
      <div className="search-content">
        <div className="section-group">
          {/* 숙박 정보 섹션 */}
          <div className="section accommodations">
            <h2 style={{ display: 'inline-block' }}>숙박 정보</h2>
            <div className="more-link-container" style={{ display: 'inline-block', marginLeft: '10px' }}>
              <a href="http://localhost:3001/hotels" className="more-link">#숙박 정보 더보기</a>
            </div>
            <div className="course-list">
              {hotels.length > 0 ? (
                hotels.map((hotel, index) => (
                  <div key={index} className="course-box-item">
                    <div className="course-box">
                      {/* 이미지가 있을 경우 firstimage2 사용 */}
                      {hotel.firstimage2 ? (
                        <img
                          src={`http://localhost:8080${hotel.firstimage2}`} // 상대 경로를 절대 경로로 변환
                          alt={hotel.title}
                          className="rounded-image"
                          width="100%"
                          height="100%"
                        />
                      ) : (
                        <img
                          src={`${process.env.PUBLIC_URL}/Image/noimg.png`} // 기본 이미지
                          alt="no_img"
                          className="rounded-image"
                          width="100%"
                          height="100%"
                        />
                      )}
                    </div>
                    <div className="course-info">
                      <div className="s_course-title">{hotel.title}</div>
                      <div className="s_course-tag">{areastring(hotel.areaCode)}</div>
                      <div className="s_course-date">{hotel.modifiedDate}</div>
                    </div>
                  </div>
                ))
              ) : (
                <p>숙박 정보가 없습니다.</p>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SearchPage;