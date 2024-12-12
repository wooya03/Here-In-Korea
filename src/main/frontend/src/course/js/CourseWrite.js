import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/CourseWrite.css";
import Header from "../../global/header/Header";

function CourseWrite() {
  const navigate = useNavigate();
  const [mainImage, setMainImage] = useState(null);
  const [courseImages, setCourseImages] = useState([]);
  const [hashtag, setHashtag] = useState("");
  const [title, setTitle] = useState("");

  useEffect(() => {
    const savedCourse = JSON.parse(localStorage.getItem("courseDraft"));
    if (savedCourse) {
      setMainImage(savedCourse.mainImage);
      setCourseImages(savedCourse.courseImages || []);
      setHashtag(savedCourse.hashtag || "");
      setTitle(savedCourse.title || "");
    }
  }, []);

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

  const handleCourseRemoveImage = (index) => {
    const newCourseImages = [...courseImages];
    newCourseImages.splice(index, 1); // 해당 이미지를 삭제
    setCourseImages(newCourseImages);
  };

  const handleAddImage = () => {
    setCourseImages([...courseImages, null]); // 새 이미지 추가
  };

  const handleCourseSave = () => {
    const courseData = {
      mainImage,
      courseImages,
      hashtag,
      title,
    };

    localStorage.setItem("courseDraft", JSON.stringify(courseData));
    alert("코스가 임시 저장되었습니다!");
  };

  const handleCourseCancel = () => {
    if (window.confirm("정말 취소하시겠습니까? 작성한 내용이 삭제됩니다.")) {
      localStorage.removeItem("courseDraft");
      navigate("/course");
    }
  };

  const handleCourseSubmit = () => {
    if (!title || !mainImage) {
      alert("제목과 대표 이미지는 필수 항목입니다.");
      return;
    }

    const courseData = {
      mainImage,
      courseImages,
      hashtag,
      title,
    };

    console.log("작성 완료한 데이터:", courseData);
    localStorage.removeItem("courseDraft");
    alert("여행 코스가 작성 완료되었습니다!");
    navigate("/course");
  };

  return (
    <div className="course-write-header">
      <Header />
      <div className="course-write-container">
        <div className="course-write-content">
          <h1 className="course-write-title">여행코스 글쓰기</h1>
          <div className="button-group">
            <button className="course-save-btn" onClick={handleCourseSave}>
              임시 저장
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
                    <div className="uploaded-image-container">
                      <img src={mainImage} alt="대표 이미지" className="uploaded-image" />
                      <button onClick={() => setMainImage(null)} className="remove-btn">
                        X
                      </button>
                    </div>
                  ) : (
                    <span>대표 이미지 업로드</span>
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
                value={hashtag}
                onChange={(e) => setHashtag(e.target.value)}
                placeholder="#해시태그를 입력해 주세요"
                className="review-hashtag-input"
              />
              <label htmlFor="title">제목</label>
              <input
                type="text"
                id="title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                placeholder="제목을 입력해 주세요"
                className="review-title-input"
              />
            </div>

            <section className="courses-section">
              <div className="course-images-container">
                {courseImages.map((image, index) => (
                  <label key={index} className="image-upload">
                    <div className="course-image-placeholder">
                      {image ? (
                        <div className="uploaded-image-container">
                          <img src={image} alt={`코스 이미지 ${index + 1}`} className="uploaded-image" />
                          <button onClick={() => handleCourseRemoveImage(index)} className="coures-image-remove-btn">
                            X
                          </button>
                        </div>
                      ) : (
                        <span>이미지 {index + 1}</span>
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
                {/* 이미지 추가 버튼 */}
                <label className="image-upload">
                  <div className="course-image-placeholder add-image-style" onClick={handleAddImage}>
                    <span>+</span>
                  </div>
                </label>
              </div>
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
