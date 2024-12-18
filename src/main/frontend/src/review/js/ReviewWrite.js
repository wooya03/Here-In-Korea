import React, { useRef, useState } from "react";
import { Editor } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import Header from "../../global/header/Header";
import "../css/ReviewWrite.css";
import { useNavigate } from "react-router-dom";
import axios from "axios"; // axios 추가

function ReviewWrite() {
  const navigate = useNavigate();
  const editorRef = useRef();
  const [title, setTitle] = useState("");
  const [hashtag, setHashtag] = useState("");

  // 작성 완료 버튼 클릭 핸들러
  const handleReviewSubmit = async () => {
    const editorInstance = editorRef.current.getInstance();
    const content = editorInstance.getMarkdown(); // 작성한 내용 가져오기

    // 백엔드로 POST 요청
    try {
      const response = await axios.post("http://localhost:8080/review", {
        reviewTitle: title,  // DTO와 필드 이름 매칭
        reviewTag: hashtag,  // DTO와 필드 이름 매칭
        reviewContent: content,
      });      
      console.log("리뷰 작성 성공:", response.data);

      alert("리뷰가 성공적으로 등록되었습니다.");
      navigate("/review"); // 리뷰 목록 페이지로 이동
    } catch (error) {
      console.error("리뷰 작성 중 오류 발생:", error);
      alert("리뷰 등록에 실패했습니다. 다시 시도해 주세요.");
    }
  };

  return (
    <div className="review-write-header">
      <Header />
      <div className="review-write-container">
        <div className="review-write-content">
          <h1 className="review-write-title">리뷰 글쓰기</h1>
          {/* 해시태그 입력 */}
          <div className="review-input-group">
            <label htmlFor="hashtag">해시태그</label>
            <input
              type="text"
              id="hashtag"
              placeholder="#해시태그를 입력해 주세요"
              className="review-hashtag-input"
              value={hashtag}
              onChange={(e) => setHashtag(e.target.value)}
            />
          </div>

          {/* 제목 입력 */}
          <div className="review-input-group">
            <label htmlFor="title">제목</label>
            <input
              type="text"
              id="title"
              placeholder="제목을 입력해 주세요"
              className="review-title-input"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />
          </div>

          {/* 내용 작성 */}
          <div className="review-editor">
            <Editor
              ref={editorRef}
              height="400px"
              initialEditType="wysiwyg"
              placeholder="내용을 입력해 주세요"
              previewStyle="vertical"
            />
          </div>

          {/* 작성 완료 버튼 */}
          <div className="review-submit-btn-container">
            <button onClick={handleReviewSubmit} className="review-submit-btn">
              작성 완료
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ReviewWrite;
