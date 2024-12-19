import React, { useState, useEffect } from "react";
import "../css/ReviewDetails.css";
import { format } from "date-fns";
import axios from "axios";
import { useParams } from "react-router-dom";
import Header from "../../global/header/Header";
import { useNavigate } from "react-router-dom";

function formatTime(dateString) {
  if (!dateString) return "날짜 정보 없음"; // dateString이 null/undefined/빈 문자열일 경우 처리
  const date = new Date(dateString);

  if (isNaN(date.getTime())) {
    // 유효하지 않은 날짜 포맷 처리
    return "잘못된 날짜";
  }

  return format(date, "yyyy-MM-dd h:mm:ss a"); // 유효한 경우 날짜 포맷팅
}


function ReviewDetails() {
    const baseUrl = "http://localhost:8080";
    const [reviewData, setreviewData] = useState(null);
    const { id } = useParams();
    const token = localStorage.getItem("token");
    const navigate = useNavigate();

    useEffect(() => {
        // 질문 데이터 가져오기
        axios.get(baseUrl + `/review/${id}`)
            .then((res) => {
                const item = res.data;
                setreviewData({
                    id: item.reviewId,
                    title: item.reviewTitle,
                    userId: item.memId,
                    date: item.createdDate,  // 날짜 변환
                    views: item.reviewViews,
                    likes: item.reviewLikes,
                    reviewContent: item.reviewContent
                });
            })
            .catch((error) => {
                console.error('질문 데이터 오류:', error);
            });
    }, [id]);

    if (!reviewData) return <p>질문 데이터를 불러오는 중...</p>;

    const deleteClick = (id) => {
        try {
            axios.delete(baseUrl + `/review/${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                  },
            });
            alert("리뷰가 삭제되었습니다.");
            navigate('/review')

          } catch (error) {
            console.error("삭제 중 오류 발생:", error);
            alert("삭제 중 오류가 발생했습니다.");
          }
      };

    return (
        <div>
            <Header />
            <div className="review-detail-container">
                <h3 className="review-detail-header">{reviewData.title}</h3>
                <div className="review-detail-h">
                    <p>작성일: {formatTime(reviewData.date)}</p> 
                    <p>조회수: {reviewData.views} &nbsp; 추천수 : {reviewData.likes}</p> 
                </div>
                <div className="review-profile">
                    <p>{reviewData.userId}</p>
                </div>
                <hr />
                <div className="review-detail-content">
                    {reviewData.reviewContent}
                </div>
                <div className="review-detail-button">
                    <button onClick={() => deleteClick(reviewData.id)}>삭제</button>
                </div>
            </div>
        </div>
    )
}

export default ReviewDetails;
