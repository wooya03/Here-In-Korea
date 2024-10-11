import React, { useState } from "react";
import "./search_page.css";

const SearchPage = () => {
  // 상태 관리: 검색어, 선택된 위치, 선택된 카테고리
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedLocation, setSelectedLocation] = useState("");
  const [selectedCategory, setSelectedCategory] = useState("");

  // 초기화 함수
  const handleReset = () => {
    setSearchTerm("");
    setSelectedLocation("");
    setSelectedCategory("");
    console.log("초기화 버튼 클릭됨");
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
        <button className="search-button">검색</button>
      </div>

      {/* 최근 검색어 표시 */}
      <div className="recent-search-container">
        <h2 className="recent-search-label">최근 검색어:</h2>
        <div className="recent-search-list">
          <span>부산 x</span>
          <span>대전 x</span>
          <span>대구 x</span>
          <span>빵 x</span>
          <span>덕질 x</span>
          <span>애니메이션 x</span>
        </div>
      </div>

      {/* 드롭다운 및 초기화 버튼 */}
      <div className="dropdown-container">
        <select
          className="dropdown"
          value={selectedLocation}
          onChange={(e) => setSelectedLocation(e.target.value)}
        >
          <option value="">서울</option>
          <option value="인천">인천</option>
          <option value="대구">대구</option>
          <option value="대전">대전</option>
          <option value="광주">광주</option>
          <option value="부산">부산</option>
          <option value="울산">울산</option>
          <option value="경기도">경기도</option>
          <option value="경상도">경상도</option>
          <option value="전라도">전라도</option>
          <option value="충청도">충청도</option>
          <option value="제주도">제주도</option>
        </select>
        <select
          className="dropdown"
          value={selectedCategory}
          onChange={(e) => setSelectedCategory(e.target.value)}
        >
          <option value="">여행스팟</option>
          <option value="문화시설">문화시설</option>
          <option value="레저">레저</option>
          <option value="숙박">숙박</option>
          <option value="쇼핑">쇼핑</option>
          <option value="음식">음식</option>
          <option value="여행코스">여행코스</option>
        </select>
        <button className="reset-button" onClick={handleReset}>
          초기화
        </button>
      </div>

      {/* 검색 결과 */}
      <div className="search-content">
        <div className="section-group">
          {/* 여행 코스 섹션 */}
          <div className="section travel-course">
            <h2 style={{ display: 'inline-block' }}>대전 여행 코스</h2>
            <div className="more-link-container" style={{ display: 'inline-block', marginLeft: '10px' }}>
              <a href="#more-courses" className="more-link">#여행 코스 더보기</a>
            </div>
            <div className="course-list">
              {/* 개별 코스 항목 */}
              <div className="course-box-item">
                <div className="course-box">
                  <img
                    src="/Image/daejun_course1.png"
                    alt="코스 1"
                    className="rounded-image"
                  />
                </div>
                <div className="course-info">
                  <div className="course-title">성심당 투어</div>
                  <div className="course-tag">#대전 #먹거리 #명소 #오로지_빵만</div>
                  <div className="course-date">24-05-28 조회23</div>
                </div>
              </div>
              {/* 추가 코스 항목들 */}
              <div className="course-box-item">
                <div className="course-box">
                  <img
                    src="/Image/daejun_course2.png"
                    alt="코스 2"
                    className="rounded-image"
                  />
                </div>
                <div className="course-info">
                  <div className="course-title">대전 여름 나들이</div>
                  <div className="course-tag">#대전 #수목원 #나들이</div>
                  <div className="course-date">24-05-07 조회14</div>
                </div>
              </div>
              <div className="course-box-item">
                <div className="course-box">
                  <img
                    src="/Image/daejun_course3.png"
                    alt="코스 3"
                    className="rounded-image"
                  />
                </div>
                <div className="course-info">
                  <div className="course-title">대전 어린이 체험 투어</div>
                  <div className="course-tag">#대전 #가족나들이 #체험 #국립중앙과학관</div>
                  <div className="course-date">24-05-08 조회5</div>
                </div>
              </div>
            </div>
          </div>

          {/* 주요 행사 섹션 */}
          <div className="section events">
            <h2 style={{ display: 'inline-block' }}>대전 주요 행사</h2>
            <div className="more-link-container" style={{ display: 'inline-block', marginLeft: '10px' }}>
              <a href="#more-events" className="more-link">#주요 행사 더보기</a>
            </div>
            <div className="course-list">
              <div className="course-box-item">
                <div className="course-box rounded-box"></div>
                <div className="course-info">
                  <div className="course-title">행사 제목 1</div>
                  <div className="course-tag">#행사 #상세정보</div>
                  <div className="course-date">24-05-28 조회23</div>
                </div>
              </div>
              <div className="course-box-item">
                <div className="course-box rounded-box"></div>
                <div className="course-info">
                  <div className="course-title">행사 제목 2</div>
                  <div className="course-tag">#행사 #상세정보</div>
                  <div className="course-date">24-05-07 조회14</div>
                </div>
              </div>
              <div className="course-box-item">
                <div className="course-box rounded-box"></div>
                <div className="course-info">
                  <div className="course-title">행사 제목 3</div>
                  <div className="course-tag">#행사 #상세정보</div>
                  <div className="course-date">24-05-08 조회5</div>
                </div>
              </div>
            </div>
          </div>

          {/* 리뷰 섹션 */}
          <div className="section reviews">
            <h2 style={{ display: 'inline-block' }}>대전 리뷰</h2>
            <div className="more-link-container" style={{ display: 'inline-block', marginLeft: '10px' }}>
              <a href="#more-reviews" className="more-link">#리뷰 더보기</a>
            </div>
            <div className="course-list">
              <div className="course-box-item">
                <div className="course-box rounded-box"></div>
                <div className="course-info">
                  <div className="course-title">리뷰 제목 1</div>
                  <div className="course-tag">#리뷰 #상세정보</div>
                  <div className="course-date">24-05-28 조회23</div>
                </div>
              </div>
              <div className="course-box-item">
                <div className="course-box rounded-box"></div>
                <div className="course-info">
                  <div className="course-title">리뷰 제목 2</div>
                  <div className="course-tag">#리뷰 #상세정보</div>
                  <div className="course-date">24-05-07 조회14</div>
                </div>
              </div>
              <div className="course-box-item">
                <div className="course-box rounded-box"></div>
                <div className="course-info">
                  <div className="course-title">리뷰 제목 3</div>
                  <div className="course-tag">#리뷰 #상세정보</div>
                  <div className="course-date">24-05-08 조회5</div>
                </div>
              </div>
            </div>
          </div>

          {/* 숙박 정보 섹션 */}
          <div className="section accommodations">
            <h2 style={{ display: 'inline-block' }}>대전 숙박 정보</h2>
            <div className="more-link-container" style={{ display: 'inline-block', marginLeft: '10px' }}>
              <a href="#more-accommodations" className="more-link">#숙박 정보 더보기</a>
            </div>
            <div className="course-list">
              <div className="course-box-item">
                <div className="course-box rounded-box"></div>
                <div className="course-info">
                  <div className="course-title">숙박 제목 1</div>
                  <div className="course-tag">#숙박 #상세정보</div>
                  <div className="course-date">24-05-28 조회23</div>
                </div>
              </div>
              <div className="course-box-item">
                <div className="course-box rounded-box"></div>
                <div className="course-info">
                  <div className="course-title">숙박 제목 2</div>
                  <div className="course-tag">#숙박 #상세정보</div>
                  <div className="course-date">24-05-07 조회14</div>
                </div>
              </div>
              <div className="course-box-item">
                <div className="course-box rounded-box"></div>
                <div className="course-info">
                  <div className="course-title">숙박 제목 3</div>
                  <div className="course-tag">#숙박 #상세정보</div>
                  <div className="course-date">24-05-08 조회5</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SearchPage;
