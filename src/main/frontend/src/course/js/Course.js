import React, { useState, useEffect } from "react";
import Header from "../../global/header/Header";
import "../css/Course.css";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../global/auth_context/AuthContext";
import axios from "axios";

function Course() {
  const { isLoggedIn } = useAuth(); // 로그인 상태 확인
  const [courseData, setCourseData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const coursesPerPage = 6;
  const navigate = useNavigate(); // Link 보다 navigate 이용

  useEffect(() => {
    // 백엔드 API 호출
    axios
      .get("/courses")
      .then((response) => {
        setCourseData(response.data);
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

  const handleCreateCourse = () => {
    if (!isLoggedIn) {
      // 로그인되지 않았다면 로그인 페이지로 리디렉션
      alert("로그인이 필요합니다.");
      navigate("/user/login");
    } else {
      // 로그인된 상태라면 course 등록 페이지로 이동
      navigate("/course/write");
    }
  };

  const fetchCourseImages = async (courseId) => {
    try {
      const response = await axios.get(`/api/course-images/${courseId}`);
      return response.data;
    } catch (error) {
      console.error(`Error fetching images for course ${courseId}:`, error);
      return [];
    }
  };

  if (loading) return <div>Loading...</div>;

  return (
    <div className="course-container">
      <Header />
      <div className="course-content">
        <h1 className="course-title">추천 여행 코스</h1>
        <button className="course-create-btn" onClick={handleCreateCourse}>
          등록
        </button>
        <div className="course-list">
          {currentCourses.map((course) => (
            <CourseItem key={course.id} course={course} fetchCourseImages={fetchCourseImages} />
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

function CourseItem({ course, fetchCourseImages }) {
  const [images, setImages] = useState([]);

  useEffect(() => {
    fetchCourseImages(course.id).then(setImages);
  }, [course.id, fetchCourseImages]);

  return (
    <div className="course-item">
      <img
        src={images.length > 0 ? images[0].url : "/placeholder.jpg"}
        alt={course.title}
        className="course-image"
      />
      <div className="course-details">
        <h2 className="course-name">{course.title}</h2>
        <p className="course-description">{course.description}</p>
        <p className="course-info">
          <span>{course.date}</span> · <span>조회수 {course.views}</span>
        </p>
      </div>
    </div>
  );
}

export default Course;
