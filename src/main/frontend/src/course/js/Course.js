import React, { useState, useEffect } from "react";
import Header from "../../global/header/Header";
import "../css/Course.css";
import {Link, useNavigate} from "react-router-dom";
import { useAuth } from "../../global/auth_context/AuthContext";

function Course() {
  const { isLoggedIn } = useAuth(); //로그인 상태 확인
  const [courseData, setCourseData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const coursesPerPage = 6;
  const navigate = useNavigate(); //Link 보다 이거 이용하는게 편할 거에요

  useEffect(() => {
    fetchCourses();
  }, [currentPage]); // currentPage가 변경될 때마다 fetchCourses 함수가 호출되도록 설정

  // 페이지 변경 함수
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const handleCreateCourse = () => {
    if (!isLoggedIn) {
      // 로그인되지 않았다면 로그인 페이지로 리디렉션
      alert("로그인이 필요합니다.")
      navigate("/user/login");
    } else {
      // 로그인된 상태라면 course 등록 페이지로 이동
      navigate("/course/write");
    }
  };

  if (loading) return <div>Loading...</div>;

  return (
    <div className="course-container">
      <Header />
      <div className="course-content">
        <h1 className="course-title">추천 여행 코스</h1>
          <button className="course-create-btn" onClick={handleCreateCourse}>등록</button>
        <div className="course-list">
          {courseData.map((course) => (
            <div key={course.courseId} className="course-item">
              {/* 첫 번째 이미지 사용, 없으면 placeholder 이미지 사용 */}
              <img
                src={course.images.length > 0 ? course.images[0].courseImageUrl : "/placeholder.jpg"}
                alt={course.courseTitle}
                className="course-image"
              />
              <div className="course-details">
                <h2 className="course-name">{course.courseTitle}</h2>
                <p className="course-description">{course.courseContent}</p>
                <p className="course-info">
                  <span>{course.createdDate}</span> ·{" "}
                  <span>조회수 {course.courseViews}</span>
                </p>
              </div>
            </div>
          ))}
        </div>
        <div className="pagination">
          {Array.from({ length: totalPages }, (_, i) => i + 1).map((page) => (
            <button
              key={page}
              className={`pagination-btn ${currentPage === page ? "active" : ""}`}
              onClick={() => handlePageChange(page)}
            >
              {page}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Course;
