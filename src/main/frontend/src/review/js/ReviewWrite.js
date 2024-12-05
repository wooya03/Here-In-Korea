import React, { useRef } from "react";
import { Editor } from "@toast-ui/react-editor"; // Toast UI Editor 가져오기
import "@toast-ui/editor/dist/toastui-editor.css"; // Toast UI 기본 CSS
import Header from "../../global/header/Header"; // Header 컴포넌트 불러오기
import "../css/ReviewWrite.css"; // 스타일 적용
import { useNavigate } from "react-router-dom"; // React Router를 통해 페이지 이동

function ReviewWrite() {
  const navigate = useNavigate(); // 페이지 이동을 위한 Hook
  const editorRef = useRef(); // 에디터 접근을 위한 Ref

  //임시저장 핸들러
  const handleReviewSave = () => {
    alert("코스가 임시저장되었습니다!");
    navigate("/review/write");
  };
  
  //삭제 핸들러
  const handleReviewCancel = () => {
    if (window.confirm("정말 취소하시겠습니까? 작성한 내용이 삭제됩니다.")) {
      navigate("/review");
    }
  };

  // 작성 완료 버튼 클릭 핸들러
  const handleReviewSubmit = () => {
    const editorInstance = editorRef.current.getInstance(); // 에디터 인스턴스 가져오기
    const content = editorInstance.getMarkdown(); // 작성한 내용을 Markdown 형식으로 가져오기

    console.log("작성된 내용:", content);

    // 작성 완료 후 리뷰 목록 페이지로 이동
    navigate("/review"); // useNavigate를 사용하여 경로 이동
  };

  return (
    <div className="review-write-container">
      <Header />
      <div className="review-write-content">
        <h1 className="review-write-title">리뷰 글쓰기</h1>
        <div>
          <button className="review-save-btn" onClick={handleReviewSave}>
            임시저장
          </button>
          <button className="review-cancel-btn" onClick={handleReviewCancel}>
            취소
          </button>
        </div>
        {/* 해시태그 입력 */}
        <div className="review-input-group">
          <label htmlFor="hashtag">해시태그</label>
          <input
            type="text"
            id="hashtag"
            placeholder="#해시태그를 입력해 주세요"
            className="review-hashtag-input"
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
          />
        </div>

        {/* 내용 작성 */}
        <div className="review-editor">
          <Editor
            ref={editorRef} // 에디터에 Ref 연결
            height="400px"
            initialEditType="wysiwyg" // 초기 에디터 타입 (Markdown, WYSIWYG)
            placeholder="내용을 입력해 주세요"
            previewStyle="vertical" // 미리보기 스타일 (vertical, tab)
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
  );
}

export default ReviewWrite;
