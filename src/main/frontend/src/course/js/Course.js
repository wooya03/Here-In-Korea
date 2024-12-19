import React, { useState, useEffect } from "react";
import Header from "../../global/header/Header";
import "../css/Course.css";
import { Link } from "react-router-dom";
import axios from "axios";

const Course = () => {
  const [courseData, setCourseData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0); // 전체 페이지 수
  const coursesPerPage = 6; // 백엔드에서 페이지 크기를 조정하려면 이 값을 API로 전달

  // 코스 데이터를 백엔드에서 가져오는 함수
  const fetchCourses = async () => {
    try {
      const response = await axios.get("http://localhost:8080/course", {
        params: {
          page: currentPage - 1,   // 페이지 번호는 0부터 시작
          size: coursesPerPage,    // 페이지 크기
          sortBy: 'createdDate'
        }
      });

      const transformedData = response.data 
      ? response.data.map((item) => ({
        courseId: item.courseId,
        courseTitle: item.courseTitle,
        courseContent: item.courseContent,
        courseTag: item.courseTag,
        createdDate: new Date(item.createdDate).toLocaleDateString(), // 날짜 변환
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

  // 컴포넌트가 마운트되면 코스 데이터를 가져옵니다.
  useEffect(() => {
    fetchCourses();
  }, [currentPage]); // currentPage가 변경될 때마다 fetchCourses 함수가 호출되도록 설정

  // 페이지 변경 함수
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  if (loading) return <div>Loading...</div>;

  return (
    <div className="course-container">
      <Header />
      <div className="course-content">
        <h1 className="course-title">추천 여행 코스</h1>
        <Link to="/course/write">
          <button className="course-create-btn">등록</button>
        </Link>
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
