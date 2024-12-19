import React, { useState, useEffect } from "react";
import Header from "../../global/header/Header";
import "../css/Course.css";
import {Link, useNavigate} from "react-router-dom";
import { useAuth } from "../../global/auth_context/AuthContext";
import axios from "axios";

function Course() {
  const { isLoggedIn } = useAuth(); //로그인 상태 확인
  const [courseData, setCourseData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const coursesPerPage = 6;
  const [totalPages, setTotalPages] = useState(0);  // 총 페이지 수 상태
  const navigate = useNavigate(); //Link 보다 이거 이용하는게 편할 거에요

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const response = await axios.get("http://localhost:8080/course", {
          params: {
            page: currentPage - 1,   // 페이지 번호는 0부터 시작
            size: coursesPerPage,    // 페이지 크기
            sortBy: 'createdDate'
          }
        });
        console.log(response.data);  // 데이터를 확인하기 위해 콘솔 로그 추가
    
        const transformedData = response.data.dtoList 
          ? response.data.dtoList.map((item) => ({
              courseId: item.courseId,
              courseTitle: item.courseTitle,
              courseContent: item.courseContent,
              courseTag: item.courseTag,
              createdDate: new Date(item.createdDate).toLocaleString(), // 날짜 변환
              courseViews: item.courseViews,
              courseLikes: item.courseLikes,
              courseName: item.courseName,
              images: item.images, // CourseImageDTO에 해당하는 이미지 리스트
          }))
          : [];
    
        setCourseData(transformedData); // 상태 업데이트
        setTotalPages(response.data.totalPages); // totalPages 값 설정
        setLoading(false);
      } catch (error) {
        console.error("코스 데이터 가져오기 실패:", error);
        setLoading(false);
      }
    };
    
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
