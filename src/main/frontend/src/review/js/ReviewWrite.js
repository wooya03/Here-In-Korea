import React, { useRef, useState } from "react";
import { Editor } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import Header from "../../global/header/Header";
import "../css/ReviewWrite.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function ReviewWrite() {
  const navigate = useNavigate();
  const editorRef = useRef();
  const [title, setTitle] = useState("");
  const [hashtag, setHashtag] = useState("");

  // 작성 완료 버튼 클릭 핸들러
  const handleReviewSubmit = async () => {
    const editorInstance = editorRef.current.getInstance();
    const content = editorInstance.getHTML(); // 작성한 내용을 HTML로 가져오기
    const baseUrl = "http://localhost:8080";

    // localStorage에서 토큰과 회원 ID를 가져옵니다
    const token = localStorage.getItem("token");
    const memId = localStorage.getItem("memId"); 

    if(!token || !memId){
      alert("로그인 되어있지 않습니다.");
      console.log("검증 실패:",{token, memId});
      return;
    }

    if (!title || !hashtag || !content ) {
      alert("모든 항목을 입력해 주세요.");
      console.log("검증 실패:", { title, hashtag, content });
      return;
    }

    const reviewData = {
      memId: memId, // 서버에서 기대하는 memId
      reviewTitle: title,
      reviewTag: hashtag,
      reviewContent: content,
      likeCount: 0, // 좋아요 초기값
      viewCount: 0, // 조회수 초기값
    };

    try {
      const response = await axios.post(baseUrl + `/review/create`, reviewData);

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
