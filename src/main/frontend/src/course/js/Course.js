import React, { Component } from "react";
import Header from "../../global/header/Header";
import "../css/Course.css";
import { Link } from "react-router-dom";

class Course extends Component {
  constructor(props) {
    super(props);
    this.state = {
      courseData: [ 
        { id: 1, title: "서울 예술 투어", description: "서울의 전통과 예술을 체험해요", date: "2024-06-16", participants: 4 },
        { id: 2, title: "충남 문화 투어", description: "충남의 역사와 문화를 배워봐요", date: "2024-03-23", participants: 14 },
        { id: 3, title: "성심당 투어", description: "대전의 인기 있는 소문난 빵집!", date: "2024-05-28", participants: 23 },
        { id: 4, title: "대전 어린이 체험 투어", description: "대전의 국립중앙과학관에서 배우는 즐거움", date: "2024-05-08", participants: 5 },
        { id: 5, title: "대전 여름 나들이", description: "여름의 대전을 즐겨요", date: "2024-06-07", participants: 14 },
        { id: 6, title: "빛나는 밤하늘 투어", description: "경기도 별빛과 함께하는 밤하늘 여행", date: "2024-06-24", participants: 14 }
      ],
      currentPage: 1,
      coursesPerPage: 6, // 한 페이지에 표시할 코스 수
    };
  }

  // 페이지 변경 핸들러
  handlePageChange = (page) => {
    this.setState({ currentPage: page });
  };

  render() {
    const { courseData, currentPage, coursesPerPage } = this.state;

    // 현재 페이지에 해당하는 코스 데이터 계산
    const indexOfLastCourse = currentPage * coursesPerPage;
    const indexOfFirstCourse = indexOfLastCourse - coursesPerPage;
    const currentCourses = courseData.slice(indexOfFirstCourse, indexOfLastCourse);

    // 전체 페이지 수 계산
    const totalPages = Math.ceil(courseData.length / coursesPerPage);
    const pages = Array.from({ length: totalPages }, (_, i) => i + 1);

    return (
      <div className="course-container">
        <Header />
        <div className="course-content">
          <h1 className="course-title">추천 여행 코스</h1>
          
          <Link to="/course/write">
          <button className="course-create-btn">등록</button>
          </Link>

          {/* 코스 목록 */}
          <div className="course-list">
            {currentCourses.map((course) => (
              <div key={course.id} className="course-item">
                <div className="course-image-placeholder" /> {/* 이미지 대신 회색 사각형 */}
                <div className="course-details">
                  <h2 className="course-name">{course.title}</h2>
                  <p className="course-description">{course.description}</p>
                  <p className="course-info">
                    <span>{course.date}</span> · <span>조회수 {course.participants}</span>
                  </p>
                </div>
              </div>
            ))}
          </div>

          {/* 페이지 네이션 */}
          <div className="pagination">
            {pages.map((page) => (
              <button
                key={page}
                className={`pagination-btn ${currentPage === page ? "active" : ""}`}
                onClick={() => this.handlePageChange(page)}
              >
                {page}
              </button>
            ))}
          </div>
        </div>
      </div>
    );
  }
}

export default Course;
