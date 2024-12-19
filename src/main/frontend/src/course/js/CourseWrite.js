import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/CourseWrite.css";
import Header from "../../global/header/Header";
import axios from "axios";

function CourseWrite() {
  const navigate = useNavigate();
  const [mainImage, setMainImage] = useState(null);
  const [courseImages, setCourseImages] = useState([]);
  const [locations, setLocations] = useState([""]); // 장소 이름 리스트
  const [courseDescriptions, setCourseDescriptions] = useState([]);
  const [hashtag, setHashtag] = useState("");
  const [title, setTitle] = useState("");

  useEffect(() => {
    const savedCourse = JSON.parse(localStorage.getItem("courseDraft"));
    if (savedCourse) {
      setMainImage(savedCourse.mainImage);
      setCourseImages(savedCourse.courseImages || []);
      setLocations(savedCourse.locations || [""]);
      setCourseDescriptions(savedCourse.courseDescriptions || []);
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
    const newLocations = [...locations];
    const newCourseDescriptions = [...courseDescriptions];
    newCourseImages.splice(index, 1);
    newLocations.splice(index, 1);
    newCourseDescriptions.splice(index, 1);
    setCourseImages(newCourseImages);
    setLocations(newLocations);
    setCourseDescriptions(newCourseDescriptions);
  };

  const handleAddImage = () => {
    setCourseImages([...courseImages, null]);
    setLocations([...locations, ""]);
    setCourseDescriptions([...courseDescriptions, ""]);
  };

  const handleDescriptionChange = (index, value) => {
    const newDescriptions = [...courseDescriptions];
    newDescriptions[index] = value;
    setCourseDescriptions(newDescriptions);
  };

  const handleCourseSave = () => {
    const courseData = {
      mainImage,
      courseImages,
      locations,
      courseDescriptions,
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

  const handleCourseSubmit = async () => {
    if (!title || !mainImage) {
      alert("제목과 대표 이미지는 필수 항목입니다.");
      return;
    }
  
    const courseData = {
      title,
      mainImage,
      courseImages,
      locations,
      courseDescriptions,
      hashtag,
    };
  
    try {
      const response = await axios.post("/course/create", courseData); // 백엔드 API 경로
      console.log("작성 완료한 데이터:", response.data);
      alert("여행 코스가 작성 완료되었습니다!");
      navigate("/course");
    } catch (error) {
      console.error("Error submitting course:", error);
      alert("코스 작성 중 오류가 발생했습니다.");
    }
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
                className="course-hashtag-input"
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
            코스 입력
            <section className="courses-section">
              {courseImages.map((image, index) => (
                <div key={index} className="course-item">
                  <div className="course-images-container">
                    <label className="image-upload">
                      <div className="course-image-placeholder">
                        {image ? (
                          <div className="uploaded-image-container">
                            <img src={image} alt={`코스 이미지 ${index + 1}`} className="uploaded-image" />
                            <button
                              onClick={() => handleCourseRemoveImage(index)}
                              className="coures-image-remove-btn"
                            >
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
                  </div>
                  <div className="course-description-container">
                    <div className="vertical-line"></div>
                    
                    {/* 장소 이름 입력란 */}
                    <input
                      className="location-name-input"
                      placeholder={`장소 이름 ${index + 1}`}
                      value={locations[index] || ""}
                      onChange={(e) => {
                        const updatedLocations = [...locations];
                        updatedLocations[index] = e.target.value;
                        setLocations(updatedLocations);
                      }}
                    />

                    {/* 장소 설명 입력란 */}
                    <textarea
                      className="course-write-description"
                      placeholder={`코스 설명 ${index + 1}`}
                      value={courseDescriptions[index] || ""}
                      onChange={(e) => handleDescriptionChange(index, e.target.value)}
                    />
                  </div>
                </div>
              ))}
              <label className="image-upload">
                <div className="course-image-placeholder add-image-style" onClick={handleAddImage}>
                  <span>+</span>
                </div>
              </label>
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
