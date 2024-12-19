import React, { useState, useEffect } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import "./search_page.css";

const SearchPage = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [hotels, setHotels] = useState([]);
  const [reviews, setReviews] = useState([]);
  const [festivals, setFestivals] = useState([]);
  const [courses, setCourses] = useState([]);  // 코스 정보 상태 추가
  const [searchHistory, setSearchHistory] = useState([]);

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

  const handleSearch = async (term = searchTerm) => {
    try {
      // 호텔 정보 검색
      const [hotelResponseByAddr, hotelResponseByTitle] = await Promise.all([
        axios.get(`http://localhost:8080/api/hotels/search2?addr1=${term}`),
        axios.get(`http://localhost:8080/api/hotels/search?title=${term}`)
      ]);

      const combinedHotels = [
        ...hotelResponseByAddr.data,
        ...hotelResponseByTitle.data
      ];

      const uniqueHotels = [
        ...new Map(combinedHotels.map(hotel => [hotel.contentid, hotel])).values()
      ];

      setHotels(uniqueHotels);

      // 행사 정보 검색
      const [festivalResponseByAddr, festivalResponseByTitle] = await Promise.all([
        axios.get(`http://localhost:8080/api/festivals/search4?addr1=${term}`),
        axios.get(`http://localhost:8080/api/festivals/search3?title=${term}`)
      ]);

      const combinedFestivals = [
        ...festivalResponseByAddr.data,
        ...festivalResponseByTitle.data
      ];

      const uniqueFestivals = [
        ...new Map(combinedFestivals.map(festival => [festival.contentId, festival])).values()
      ];

      setFestivals(uniqueFestivals);

      // 리뷰 정보 검색
      const reviewResponse = await axios.get(`http://localhost:8080/api/reviews/search5?reviewTitle=${term}`);
      if (reviewResponse.data && Array.isArray(reviewResponse.data)) {
        setReviews(reviewResponse.data);
      } else {
        setReviews([]);
      }

      // 코스 정보 검색 (새로운 API 추가)
      const courseResponse = await axios.get(`http://localhost:8080/api/course/search6?courseTitle=${term}`);
      if (courseResponse.data && Array.isArray(courseResponse.data)) {
        setCourses(courseResponse.data);  // 받은 코스 데이터를 상태에 저장
      } else {
        setCourses([]);  // 코스 데이터가 없으면 빈 배열로 설정
      }

      // 검색 기록 처리
      setSearchHistory((prevHistory) => {
        const updatedHistory = [term, ...prevHistory];
        return updatedHistory.length > 20 ? updatedHistory.slice(0, 20) : updatedHistory;
      });
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

  const handleFestivalClick = (id) => {
    navigate(`/festival/${id}`);
  };

  const handleReviewClick = (id) => {
    navigate(`/review/${id}`);
  };

  const handleCourseClick = (id) => {
    navigate(`/course/${id}`); // 코스를 클릭하면 해당 코스 페이지로 이동
  };

  return (
    <div className="search-page">
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
        <button className="search-page-button" onClick={() => handleSearch()}>
          검색
        </button>
      </div>

      <div className="search-content">
        <div className="section-group">
          {/* 숙박 정보 */}
          <div className="section accommodations">
            <h2>숙박 정보</h2>
            <div className="course-list">
              {hotels.length > 0 ? (
                hotels.map((hotel, index) => (
                  <div key={index} className="course-box-item" onClick={() => handleHotelClick(hotel.contentid)}>
                    <div className="course-box">
                      <img
                        src={hotel.firstimage2 || `${process.env.PUBLIC_URL}/Image/noimg.png`}
                        alt={hotel.title}
                        className="rounded-image"
                        width="100%"
                        height="100%"
                      />
                    </div>
                    <div className="course-info">
                      <div className="s_course-title">{hotel.title}</div>
                      <div className="s_course-tag">{hotel.addr1}</div>
                      <div className="s_course-date">{hotel.modifiedDate}</div>
                    </div>
                  </div>
                ))
              ) : (
                <p>숙박 정보가 없습니다.</p>
              )}
            </div>
            <div className="more-link-container">
              <a href="/hotels" className="more-link">#숙박 정보 더보기</a>
            </div>
          </div>

          {/* 행사 정보 */}
          <div className="section festivals">
            <h2>행사 정보</h2>
            <div className="more-link-container">
              <a href="/festival" className="more-link">#행사 정보 더보기</a>
            </div>
            <div className="course-list">
              {festivals.length > 0 ? (
                festivals.map((festival, index) => (
                  <div key={index} className="course-box-item" onClick={() => handleFestivalClick(festival.contentId)}>
                    <div className="course-box">
                      <img
                        src={festival.firstimage2 || `${process.env.PUBLIC_URL}/Image/noimg.png`}
                        alt={festival.title}
                        className="rounded-image"
                        width="100%"
                        height="100%"
                      />
                    </div>
                    <div className="course-info">
                      <div className="s_course-title">{festival.title}</div>
                      <div className="s_course-tag">{festival.addr1}</div>
                      <div className="s_course-date">{festival.eventStartDate} - {festival.eventEndDate}</div>
                    </div>
                  </div>
                ))
              ) : (
                <p>행사 정보가 없습니다.</p>
              )}
            </div>
          </div>

          {/* 리뷰 정보 */}
          <div className="section reviews">
            <h2>리뷰 정보</h2>
            <div className="course-list">
              {reviews && reviews.length > 0 ? (
                reviews.map((review, index) => (
                  <div key={index} className="course-box-item" onClick={() => handleReviewClick(review.reviewId)}>
                    <div className="course-box">
                    </div>
                    <div className="course-info">
                      <div className="s_course-title">{review.reviewTitle}</div>
                      <div className="s_course-tag">{review.reviewTag}</div>
                      <div className="s_course-date">
                        {review.createdDate ? new Intl.DateTimeFormat('ko-KR').format(new Date(review.createdDate)) : '날짜 정보 없음'}
                      </div>
                      <div className="s_course-likes">👍 {review.reviewLikes} likes</div>
                    </div>
                  </div>
                ))
              ) : (
                <p>리뷰 정보가 없습니다.</p>
              )}
            </div>
          </div>

          {/* 코스 정보 */}
          <div className="section courses">
            <h2>코스 정보</h2>
            <div className="course-list">
              {courses.length > 0 ? (
                courses.map((course, index) => (
                  <div key={index} className="course-box-item" onClick={() => handleCourseClick(course.contentId)}>
                    <div className="course-box">
                      <img
                        src={course.firstimage2 || `${process.env.PUBLIC_URL}/Image/noimg.png`}
                        alt={course.title}
                        className="rounded-image"
                        width="100%"
                        height="100%"
                      />
                    </div>
                    <div className="course-info">
                      <div className="s_course-title">{course.courseTitle}</div>
                      <div className="s_course-tag">{course.courseContent}</div>
                      <div className="s_course-date">{course.createdDate}</div>
                      <div className="s_course-like">{course.courseLikes}</div>
                      <div className="s_course-view">{course.courseViews}</div>
                    </div>
                  </div>
                ))
              ) : (
                <p>코스 정보가 없습니다.</p>
              )}
            </div>
            <div className="more-link-container">
              <a href="/courses" className="more-link">#코스 정보 더보기</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SearchPage;
