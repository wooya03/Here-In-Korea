import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // React Router 사용
import Header from "../../global/header/Header"; // 헤더 컴포넌트 추가
import "../css/ReviewWrite.css";

const ReviewWrite = () => {
  const [hashtags, setHashtags] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [images, setImages] = useState([]);
  const navigate = useNavigate(); // 페이지 이동을 위한 React Router 훅

  // 해시태그 변경 핸들러
  const handleHashtagChange = (e) => {
    setHashtags(e.target.value);
  };

  // 제목 변경 핸들러
  const handleTitleChange = (e) => {
    setTitle(e.target.value);
  };

  // 내용 변경 핸들러
  const handleContentChange = (e) => {
    setContent(e.target.value);
  };

  // 이미지 업로드 핸들러
  const handleImageUpload = (e) => {
    const files = Array.from(e.target.files);
    setImages((prevImages) => [...prevImages, ...files]);
  };

  // 폼 제출 핸들러
  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("hashtags", hashtags);
    formData.append("title", title);
    formData.append("content", content);
    images.forEach((image, index) => {
      formData.append(`image_${index}`, image);
    });

    // API 전송 예제 (미리 준비된 API로 데이터 전송)
    fetch("/api/reviews", {
      method: "POST",
      body: formData,
    })
      .then((response) => {
        if (response.ok) {
          alert("리뷰가 성공적으로 등록되었습니다!");
          navigate("/review"); // 리뷰 페이지로 이동
        } else {
          alert("등록에 실패했습니다. 다시 시도해주세요.");
        }
      })
      .catch((error) => {
        console.error("Error submitting review:", error);
        alert("서버 오류가 발생했습니다.");
      });
  };

  return (
    <div className="review-write-container">
      <Header /> {/* 헤더 추가 */}
      <h1 className="review-write-title">리뷰 글쓰기</h1>
      <form onSubmit={handleSubmit} className="review-write-form">
        {/* 해시태그 입력 */}
        <div className="input-group">
          <label htmlFor="hashtags"># 해시태그</label>
          <input
            type="text"
            id="hashtags"
            value={hashtags}
            onChange={handleHashtagChange}
            placeholder="해시태그를 입력하세요 (예: #맛집 #여행)"
          />
        </div>

        {/* 제목 입력 */}
        <div className="input-group">
          <label htmlFor="title">제목</label>
          <input
            type="text"
            id="title"
            value={title}
            onChange={handleTitleChange}
            placeholder="제목을 입력하세요"
          />
        </div>

        {/* 내용 입력 */}
        <div className="input-group">
          <label htmlFor="content">내용</label>
          <textarea
            id="content"
            value={content}
            onChange={handleContentChange}
            placeholder="내용을 입력하세요"
          ></textarea>
        </div>

        {/* 사진 업로드 */}
        <div className="input-group">
          <label htmlFor="images">사진 첨부</label>
          <input
            type="file"
            id="images"
            multiple
            accept="image/*"
            onChange={handleImageUpload}
          />
          <div className="uploaded-images">
            {images.map((image, index) => (
              <div key={index} className="image-preview">
                {image.name}
              </div>
            ))}
          </div>
        </div>

        {/* 등록 버튼 */}
        <button type="submit" className="submit-btn">
          등록
        </button>
      </form>
    </div>
  );
};

export default ReviewWrite;
