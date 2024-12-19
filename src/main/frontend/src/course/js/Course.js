import React, { useState, useEffect } from "react";
import Header from "../../global/header/Header";
import "../css/Course.css";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../global/auth_context/AuthContext";
import axios from "axios";

const Course = () => {
  const { isLoggedIn } = useAuth(); // 로그인 상태 확인
  const [courseData, setCourseData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(1);
  const coursesPerPage = 6;
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const response = await axios.get("/course"); // 백엔드 엔드포인트 경로 확인
        setCourseData(response.data); // 서버 응답에 따라 key를 수정
        setLoading(false);
      } catch (error) {
        console.error("Error fetching course data:", error);
        setLoading(false);
      }
    };

    fetchCourses();
  }, []);

  const handlePageChange = (page) => setCurrentPage(page);

  const indexOfLastCourse = currentPage * coursesPerPage;
  const indexOfFirstCourse = indexOfLastCourse - coursesPerPage;
  const currentCourses = courseData.slice(indexOfFirstCourse, indexOfLastCourse);

  const totalPages = Math.ceil(courseData.length / coursesPerPage);
  const pages = Array.from({ length: totalPages }, (_, i) => i + 1);

  const handleCreateCourse = () => {
    if (!isLoggedIn) {
      alert("로그인이 필요합니다.");
      navigate("/user/login");
    } else {
      navigate("/course/write");
    }
  };

  const fetchCourseImages = async (courseId) => {
    try {
      const response = await axios.get(`/course/images/${courseId}`);
      return response.data; // 서버 응답에 따라 key를 수정
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
            <CourseItem key={course.courseId} course={course} fetchCourseImages={fetchCourseImages} />
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
};

const CourseItem = ({ course, fetchCourseImages }) => {
  const [images, setImages] = useState([]);

  useEffect(() => {
    const fetchImages = async () => {
      const courseImages = await fetchCourseImages(course.courseId);
      setImages(courseImages);
    };

    fetchImages();
  }, [course.courseId, fetchCourseImages]);

  return (
    <div className="course-item">
      <img
        src={images.length > 0 ? images[0].courseImageUrl : "/placeholder.jpg"}
        alt={course.courseTitle}
        className="course-image"
      />
      <div className="course-details">
        <h2 className="course-name">{course.courseTitle}</h2>
        <p className="course-description">{course.courseContent}</p>
        <p className="course-info">
          <span>{new Date(course.createdDate).toLocaleDateString()}</span> · <span>조회수 {course.courseViews}</span>
        </p>
      </div>
    </div>
  );
};

export default Course;
