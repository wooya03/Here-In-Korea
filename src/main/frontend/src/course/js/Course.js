import React, { useState, useEffect } from "react";
import Header from "../../global/header/Header";
import "../css/Course.css";
import { Link } from "react-router-dom";

function Course() {
  const [courseData, setCourseData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const coursesPerPage = 6;

  useEffect(() => {
    fetch("/courses") // 백엔드 API 호출
      .then((response) => response.json())
      .then((data) => {
        setCourseData(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching course data:", error);
        setLoading(false);
      });
  }, []);

  const handlePageChange = (page) => setCurrentPage(page);

  const indexOfLastCourse = currentPage * coursesPerPage;
  const indexOfFirstCourse = indexOfLastCourse - coursesPerPage;
  const currentCourses = courseData.slice(indexOfFirstCourse, indexOfLastCourse);

  const totalPages = Math.ceil(courseData.length / coursesPerPage);
  const pages = Array.from({ length: totalPages }, (_, i) => i + 1);

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
          {currentCourses.map((course) => (
            <div key={course.id} className="course-item">
              <img src={course.mainImage || "/placeholder.jpg"} alt={course.title} className="course-image" />
              <div className="course-details">
                <h2 className="course-name">{course.title}</h2>
                <p className="course-description">{course.description}</p>
                <p className="course-info">
                  <span>{course.date}</span> · <span>조회수 {course.views}</span>
                </p>
              </div>
            </div>
          ))}
        </div>
        <div className="pagination">
          {pages.map((page) => (
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
}

export default Course;