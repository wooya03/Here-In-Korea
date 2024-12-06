import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/CourseWrite.css";
import Header from "../../global/header/Header";

function CourseWrite() {
  const navigate = useNavigate();
  const [mainImage, setMainImage] = useState(null);
  const [courseImages, setCourseImages] = useState([null, null, null]);

  const handleMainImageUpload = (e) => {
    const file = e.target.files[0];
    if (file) {
      setMainImage(URL.createObjectURL(file));
    }
  };

  const handleCourseImageUpload = (index, e) => {
    const file = e.target.files[0];
    if (file) {
      const newCourseImages = [...courseImages];
      newCourseImages[index] = URL.createObjectURL(file);
      setCourseImages(newCourseImages);
    }
  };

  //임시저장 핸들러
  const handleCourseSave = () => {
    alert("코스가 임시저장되었습니다!");
    navigate("/course/write");
  };
  
  //삭제 핸들러
  const handleCourseCancel = () => {
    if (window.confirm("정말 취소하시겠습니까? 작성한 내용이 삭제됩니다.")) {
      navigate("/course");
    }
  };

  // 작성 완료 버튼 클릭 핸들러
  const handleCourseSubmit = () => {
    const courseData = {
      mainImage,
      courseImages,
      hashtag: document.getElementById("hashtag").value,
      title: document.getElementById("title").value,
    };

    if (!courseData.title || !courseData.mainImage) {
      alert("제목과 대표 이미지는 필수 항목입니다.");
      return;
    }

    console.log("작성 완료한 데이터:", courseData);

    alert("여행 코스가 작성 완료되었습니다!");
    navigate("/course");
  };

  return (
    <div className="course-write-header">
      <Header />
    <div className="course-write-container">
      <div className="course-write-content">
        <h1 className="course-write-title">여행코스 글쓰기</h1>
        <div>
          <button className="course-save-btn" onClick={handleCourseSave}>
            임시저장
          </button>
          <button className="course-cancel-btn" onClick={handleCourseCancel}>
            취소
          </button>
        </div>
      <main>
        <section className="main-image-section">
          <label className="image-upload">
            <div className="main-image-placeholder">
              {mainImage ? (
                <img src={mainImage} alt="대표 이미지" className="uploaded-image" />
              ) : (
                <span>(이미지)</span>
              )}
            </div>
            <input
              type="file"
              accept="image/*"
              onChange={handleMainImageUpload}
              style={{ display: "none" }}
            />
          </label>
        </section>

        <div className="course-input-group">
          <label htmlFor="hashtag">해시태그</label>
          <input
            type="text"
            id="hashtag"
            placeholder="#해시태그를 입력해 주세요"
            className="review-hashtag-input"
          />
          <div className="course-content-title">
            <label htmlFor="title">제목</label>
            <input
              type="text"
              id="title"
              placeholder="제목을 입력해 주세요"
              className="review-title-input"
            />
          </div>
        </div>

        <section className="courses-section">
          {courseImages.map((image, index) => (
            <label key={index} className="image-upload">
              <div className="course-image-placeholder">
                {image ? (
                  <img src={image} alt={`코스 이미지 ${index + 1}`} className="uploaded-image" />
                ) : (
                  <span>(이미지)</span>
                )}
              </div>
              <input
                type="file"
                accept="image/*"
                onChange={(e) => handleCourseImageUpload(index, e)}
                style={{ display: "none" }}
              />
            </label>
          ))}
        </section>

        <div className="course-submit-btn-container">
          <button onClick={handleCourseSubmit} className="course-submit-btn">
            작성 완료
          </button>
        </div>
      </main>
      </div>
    </div>
    </div>
  );
}

export default CourseWrite;
