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
  const [itemsPerPage] = useState(5);  // 한 페이지에 표시할 아이템 수
  const coursesPerPage = 6;
  const navigate = useNavigate();


    useEffect(() => {
      putSpringData();
    }, []); // category 값이 변경될 때마다 데이터 재조회
  
    async function putSpringData() {
      await axios
        .get("http://localhost:8080/course")
        .then((res) => {
          const transformedData = res.data.dtoList
            ? res.data.dtoList.map((item) => ({
              id: item.courseId,
              userId: item.memId,
              title: item.courseTitle,
              tag: item.courseTag,
              createdDate: item.createdDate,
              views: item.courseViews,
              likes: item.courseLikes,
              courseImgUrl: item.courseImageUrl
              }))
            : [];
          setCourseData(transformedData);
          setLoading(false);
        })
        .catch((err) => {
          console.log(err);
        });
    }  

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
          <div key={course.id} className="course-card">
            {course.courseImgUrl && course.courseImgUrl !== "null" ? (
                                    <img src={course.courseImgUrl} alt="thumbnail" width="50%" height="50%" />
                                ) : (
                                    <img src={`${process.env.PUBLIC_URL}/Image/noimg.png`} alt="no_img" width="50%" height="50%" />
            )}
            <div className="course-details">
              <h2 className="course-name">{course.title}</h2>
              <p className="course-tag">{course.tag}</p>
              <p className="course-date">수정 {course.createdDate}</p>
              <p className="course-stats">
                조회 {course.views} 좋아요 {course.likes}
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
};

export default Course;
