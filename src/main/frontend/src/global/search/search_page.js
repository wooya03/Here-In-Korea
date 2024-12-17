import React, { useState, useEffect } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import "./search_page.css";

const SearchPage = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [hotels, setHotels] = useState([]);
  const [reviews, setReviews] = useState([]);
  const [events, setEvents] = useState([]);
  const [courses, setCourses] = useState([]); // 코스 정보 상태 추가
  const [searchHistory, setSearchHistory] = useState([]);
  const [hoveredHotel, setHoveredHotel] = useState(null);
  const [mousePosition, setMousePosition] = useState({ x: 0, y: 0 });

  const location = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const query = params.get("query");
    if (query) {
      setSearchTerm(query);
      handleSearch(query);
    }
  }, [location.search]);

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

  const handleSearch = async (term = searchTerm) => {
    try {
      // 두 요청을 병렬로 처리
      const [hotelResponseByAddr, hotelResponseByTitle] = await Promise.all([
        axios.get(`http://localhost:8080/api/hotels/search2?addr1=${term}`),
        axios.get(`http://localhost:8080/api/hotels/search?title=${term}`)
      ]);

      // 두 응답 데이터를 합쳐서 상태에 저장
      const combinedHotels = [
        ...hotelResponseByAddr.data,
        ...hotelResponseByTitle.data
      ];

      // 중복된 호텔을 제거 (contentid 기준으로)
      const uniqueHotels = [
        ...new Map(combinedHotels.map(hotel => [hotel.contentid, hotel])).values()
      ];

      setHotels(uniqueHotels);

      // 나머지 검색 요청은 기존대로 처리
      const reviewResponse = await axios.get(`http://localhost:8080/api/reviews/search?title=${term}`);
      setReviews(reviewResponse.data);

      const eventResponse = await axios.get(`http://localhost:8080/api/events/search?title=${term}`);
      setEvents(eventResponse.data);

      const courseResponse = await axios.get(`http://localhost:8080/api/courses/search?title=${term}`);
      setCourses(courseResponse.data);

      // 검색 기록 관리
      if (searchHistory.length >= 20) {
        setSearchHistory((prevHistory) => [term, ...prevHistory.slice(0, 19)]);
      } else {
        setSearchHistory((prevHistory) => [term, ...prevHistory]);
      }
    } catch (error) {
      console.error("검색 오류", error);
    }
  };

  const handleKeyDown = (e) => {
    if (e.key === "Enter") {
      handleSearch();
    }
  };

  const handleHotelClick = (id) => {
    navigate(`/hotels/${id}`);
  };

  const handleMouseMove = (e) => {
    setMousePosition({ x: e.clientX, y: e.clientY });
  };

  return (
    <div className="search-page" onMouseMove={handleMouseMove}>
      <h1 className="search-results-title">검색결과</h1>

      <div className="search-input-container">
        <input
          type="text"
          className="search-input"
          placeholder="검색어를 입력하세요"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          onKeyDown={handleKeyDown}
        />
        <button className="search-page-button" onClick={() => handleSearch()}>검색</button>
      </div>

      <div className="search-content">
        <div className="section-group">
          {/* 숙박 정보 */}
          <div className="section accommodations">
            <h2 style={{ display: 'inline-block' }}>숙박 정보</h2>
            <div className="more-link-container" style={{ display: 'inline-block', marginLeft: '10px' }}>
              <a href="/hotels" className="more-link">#숙박 정보 더보기</a>
            </div>
            <div className="course-list">
              {hotels.length > 0 ? (
                hotels.map((hotel, index) => (
                  <div
                    key={index}
                    className="course-box-item"
                    onClick={() => handleHotelClick(hotel.contentid)}
                    onMouseEnter={() => setHoveredHotel(hotel.contentid)}
                    onMouseLeave={() => setHoveredHotel(null)}
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
                      <div className="s_course-tag">{hotel.addr1}</div>
                      <div className="s_course-date">{hotel.modifiedDate}</div>
                    </div>
                    {hoveredHotel === hotel.contentid && (
                      <div
                        className="hover-text"
                        style={{
                          position: "fixed",
                          top: mousePosition.y + 10,
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

          {/* 리뷰 정보 */}
          <div className="section reviews">
            <h2 style={{ display: 'inline-block' }}>리뷰 정보</h2>
            <div className="more-link-container" style={{ display: 'inline-block', marginLeft: '10px' }}>
              <a href="/review" className="more-link">#리뷰 정보 더보기</a>
            </div>
            <div className="course-list">
              {reviews.length > 0 ? (
                reviews.map((review, index) => (
                  <div
                    key={index}
                    className="course-box-item"
                    onClick={() => handleHotelClick(review.hotelId)}
                    onMouseEnter={() => setHoveredHotel(review.hotelId)}
                    onMouseLeave={() => setHoveredHotel(null)}
                  >
                    <div className="course-box">
                      <img
                        src={review.hotelImage || `${process.env.PUBLIC_URL}/Image/noimg.png`}
                        alt={review.title}
                        className="rounded-image"
                        width="100%"
                        height="100%"
                      />
                    </div>
                    <div className="course-info">
                      <div className="s_course-title">{review.title}</div>
                      <div className="s_course-tag">{areastring(review.areacode)}</div>
                      <div className="s_course-date">{review.date}</div>
                    </div>
                    {hoveredHotel === review.hotelId && (
                      <div
                        className="hover-text"
                        style={{
                          position: "fixed",
                          top: mousePosition.y + 10,
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
                <p>리뷰 정보가 없습니다.</p>
              )}
            </div>
          </div>

          {/* 행사 정보 */}
          <div className="section events">
            <h2 style={{ display: 'inline-block' }}>행사 정보</h2>
            <div className="more-link-container" style={{ display: 'inline-block', marginLeft: '10px' }}>
              <a href="/festival" className="more-link">#행사 정보 더보기</a>
            </div>
            <div className="course-list">
              {events.length > 0 ? (
                events.map((event, index) => (
                  <div
                    key={index}
                    className="course-box-item"
                    onClick={() => navigate(`/events/${event.id}`)}
                    onMouseEnter={() => setHoveredHotel(event.id)}
                    onMouseLeave={() => setHoveredHotel(null)}
                  >
                    <div className="course-box">
                      <img
                        src={event.image || `${process.env.PUBLIC_URL}/Image/noimg.png`}
                        alt={event.title}
                        className="rounded-image"
                        width="100%"
                        height="100%"
                      />
                    </div>
                    <div className="course-info">
                      <div className="s_course-title">{event.title}</div>
                      <div className="s_course-tag">{areastring(event.areacode)}</div>
                      <div className="s_course-date">{event.date}</div>
                    </div>
                    {hoveredHotel === event.id && (
                      <div
                        className="hover-text"
                        style={{
                          position: "fixed",
                          top: mousePosition.y + 10,
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
                <p>행사 정보가 없습니다.</p>
              )}
            </div>
          </div>

          {/* 여행 코스 정보 */}
          <div className="section courses">
            <h2 style={{ display: 'inline-block' }}>여행 코스 정보</h2>
            <div className="more-link-container" style={{ display: 'inline-block', marginLeft: '10px' }}>
              <a href="/courses" className="more-link">#여행 코스 더보기</a>
            </div>
            <div className="course-list">
              {courses.length > 0 ? (
                courses.map((course, index) => (
                  <div
                    key={index}
                    className="course-box-item"
                    onClick={() => navigate(`/courses/${course.id}`)}
                    onMouseEnter={() => setHoveredHotel(course.id)}
                    onMouseLeave={() => setHoveredHotel(null)}
                  >
                    <div className="course-box">
                      <img
                        src={course.image || `${process.env.PUBLIC_URL}/Image/noimg.png`}
                        alt={course.title}
                        className="rounded-image"
                        width="100%"
                        height="100%"
                      />
                    </div>
                    <div className="course-info">
                      <div className="s_course-title">{course.title}</div>
                      <div className="s_course-tag">{areastring(course.areacode)}</div>
                      <div className="s_course-date">{course.date}</div>
                    </div>
                    {hoveredHotel === course.id && (
                      <div
                        className="hover-text"
                        style={{
                          position: "fixed",
                          top: mousePosition.y + 10,
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
                <p>여행 코스 정보가 없습니다.</p>
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SearchPage;
