import React, { useRef, useState, useEffect } from "react";
import { Editor } from "@toast-ui/react-editor";
import "@toast-ui/editor/dist/toastui-editor.css";
import Header from "../../global/header/Header";
import "../css/ReviewModify.css";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";

function ReviewModify() {
    const baseUrl = "http://localhost:8080";
    const navigate = useNavigate();
    const editorRef = useRef();
    const [data, setData] = useState(null); // 초기값을 null로 설정
    const [title, setTitle] = useState("");
    const [hashtag, setHashtag] = useState("");
    const { id } = useParams();

    useEffect(() => {
        // 리뷰 데이터 가져오기
        axios.get(baseUrl + `/review/${id}`)
            .then((res) => {
                const reviewData = res.data;
                setData(reviewData); // 전체 데이터 저장
                setTitle(reviewData.reviewTitle); // 제목 초기값 설정
                setHashtag(reviewData.reviewTag); // 해시태그 초기값 설정
                if (editorRef.current) {
                    editorRef.current.getInstance().setHTML(reviewData.reviewContent); // 에디터 내용 초기값 설정
                }
            })
            .catch((err) => {
                console.log(err);
            });
    }, [id]);

    const handleReviewSubmit = async () => {
        const editorInstance = editorRef.current.getInstance();
        const content = editorInstance.getHTML();
        const token = localStorage.getItem("token");
        const modifyData = {
            memId: data.memId,
            reviewTitle: title,
            reviewTag: hashtag,
            reviewContent: content,
            likeCount: data.reviewLikes,
            viewCount: data.reviewViews,
        };
    
        try {
            await axios.put(baseUrl + `/review/${id}`, modifyData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            alert("리뷰가 성공적으로 수정되었습니다.");
            navigate("/review"); // 리뷰 목록 페이지로 이동
        } catch (err) {
            console.log(err);
            alert("리뷰 수정에 실패했습니다.");
        }
    };

    return (
        <div className="review-write-header">
            <Header />
            <div className="review-write-container">
                <div className="review-write-content">
                    <h1 className="review-write-title">리뷰 수정하기</h1>
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
                            previewStyle="vertical"
                        />
                    </div>

                    {/* 작성 완료 버튼 */}
                    <button onClick={handleReviewSubmit} className="review-submit-btn">작성 완료</button>
                </div>
            </div>
        </div>
    );
}

export default ReviewModify;
