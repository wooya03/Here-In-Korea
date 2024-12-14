import React, { useState, useEffect } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";  // useNavigate 추가
import "./search_page.css";

const SearchPage = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [hotels, setHotels] = useState([]);
  const [searchHistory, setSearchHistory] = useState([]);
  const [hoveredHotel, setHoveredHotel] = useState(null);  // 새 상태 추가
  const [mousePosition, setMousePosition] = useState({ x: 0, y: 0 }); // 마우스 위치 상태 추가

  const location = useLocation();
  const navigate = useNavigate();  // useNavigate 추가

  // URL에서 검색어 가져오기
  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const query = params.get("query");
    if (query) {
      setSearchTerm(query);
      handleSearch(query);
    }
  }, [location.search]);

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

  // 검색 함수
  const handleSearch = async (term = searchTerm) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/hotels/search?title=${term}`);
      setHotels(response.data);

      // 검색 기록 저장 (최대 20개)
      if (searchHistory.length >= 20) {
        setSearchHistory((prevHistory) => [term, ...prevHistory.slice(0, 19)]);
      } else {
        setSearchHistory((prevHistory) => [term, ...prevHistory]);
      }
    } catch (error) {
      console.error("숙박 정보 검색 오류", error);
    }
  };

  // 엔터 키로 검색 실행
  const handleKeyDown = (e) => {
    if (e.key === "Enter") {
      handleSearch();
    }
  };

  // 호텔 클릭 시 상세 페이지로 이동
  const handleHotelClick = (id) => {
    navigate(`/hotels/${id}`);  // 해당 호텔의 ID를 URL에 추가
  };

  // 마우스 위치 업데이트
  const handleMouseMove = (e) => {
    setMousePosition({ x: e.clientX, y: e.clientY });
  };

  return (
    <div className="search-page" onMouseMove={handleMouseMove}> {/* 마우스 이동 이벤트 추가 */}
      <h1 className="search-results-title">검색결과</h1>

      {/* 검색 입력 필드 */}
      <div className="search-input-container">
        <input
          type="text"
          className="search-input"
          placeholder="검색어를 입력하세요"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          onKeyDown={handleKeyDown}  // 엔터 키 이벤트 추가
        />
        <button className="search-page-button" onClick={() => handleSearch()}>검색</button>
      </div>

      {/* 검색 결과 */}
      <div className="search-content">
        <div className="section-group">
          <div className="section accommodations">
            <h2 style={{ display: 'inline-block' }}>숙박 정보</h2>
            <div className="more-link-container" style={{ display: 'inline-block', marginLeft: '10px' }}>
              <a href="http://localhost:3000/hotels" className="more-link">#숙박 정보 더보기</a>
            </div>
            <div className="course-list">
              {hotels.length > 0 ? (
                hotels.map((hotel, index) => (
                  <div
                    key={index}
                    className="course-box-item"
                    onClick={() => handleHotelClick(hotel.contentid)}  // 검색 결과 클릭 시 상세 페이지로 이동
                    onMouseEnter={() => setHoveredHotel(hotel.contentid)}  // 마우스가 올라가면 표시
                    onMouseLeave={() => setHoveredHotel(null)}  // 마우스가 떠나면 숨기기
                  >
                    <div className="course-box">
                      {hotel.firstimage2 ? (
                        <img
                          src={hotel.firstimage2}
                          alt={hotel.title}
                          className="rounded-image"
                          width="100%"
                          height="100%"
                        />
                      ) : (
                        <img
                          src={`${process.env.PUBLIC_URL}/Image/noimg.png`}
                          alt="no_img"
                          className="rounded-image"
                          width="100%"
                          height="100%"
                        />
                      )}
                    </div>
                    <div className="course-info">
                      <div className="s_course-title">{hotel.title}</div>
                      <div className="s_course-tag">{areastring(hotel.areacode)}</div>
                      <div className="s_course-date">{hotel.modifiedDate}</div>
                    </div>

                    {/* 마우스 오버 시 "클릭 시 해당 게시물로 이동합니다!" 문구 표시 */}
                    {hoveredHotel === hotel.contentid && (
                      <div
                        className="hover-text"
                        style={{
                          position: "absolute",
                          top: mousePosition.y + 10,  // 마우스 위치에 따라 문구 위치 설정
                          left: mousePosition.x + 10,
                          backgroundColor: "rgba(0, 0, 0, 0.7)",
                          color: "white",
                          padding: "5px",
                          borderRadius: "5px",
                          pointerEvents: "none",
                        }}
                      >
                        클릭 시 해당 게시물로 이동합니다!
                      </div>
                    )}
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
