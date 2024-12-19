import React, { useState, useEffect } from "react";
import Header from "../../global/header/Header";
import {Link, useNavigate} from "react-router-dom";
import "../css/Review.css";
import axios from "axios";
import {useAuth} from "../../global/auth_context/AuthContext";

const Review = () => {
  const { isLoggedIn } = useAuth(); // 로그인 상태 확인
  const [searchText, setSearchText] = useState("");  // 검색 텍스트 상태
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지 상태
  const [itemsPerPage] = useState(5);  // 한 페이지에 표시할 아이템 수
  const [reviews, setReviews] = useState([]);  // 리뷰 목록 상태
  const [totalPages, setTotalPages] = useState(0);  // 총 페이지 수 상태
  const navigate = useNavigate(); //여기도 Link 보다는 navigate사용 하죠

  // 리뷰 데이터를 백엔드에서 가져오는 함수
  const fetchReviews = async () => {
    try {
      const response = await axios.get("http://localhost:8080/review", {
        params: {
          page: currentPage - 1,   // 페이지 번호는 0부터 시작
          size: itemsPerPage,      // 페이지 크기
          sortBy: 'createdDate'    // 정렬 기준
        }
      });

      const transformedData = response.data.dtoList
        ? response.data.dtoList.map((item) => ({
            id: item.reviewId,
            title: item.reviewTitle,
            userId: item.memId,
            date: new Date(item.createdDate).toLocaleString(),  // 날짜 변환
            views: item.reviewViews,
            likes: item.reviewLikes,
        }))
        : [];

      setReviews(transformedData);
      setTotalPages(response.data.totalPages); // totalPages 값을 설정
    } catch (error) {
      console.error("리뷰 데이터 가져오기 실패:", error);
      // 서버 오류 또는 요청 오류에 대한 처리
    }
  };

  // 컴포넌트가 마운트되면 리뷰 데이터를 가져옵니다.
  useEffect(() => {
    fetchReviews();
  }, [currentPage]); // currentPage가 변경될 때마다 fetchReviews 함수가 호출되도록 설정

  // 페이지 변경 함수
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const handleCreateReview = () => {
    if (!isLoggedIn) {
      // 로그인되지 않았다면 로그인 페이지로 리디렉션
      alert("로그인이 필요합니다!");
      navigate("/user/login");
    } else {
      // 로그인된 상태라면 리뷰 등록 페이지로 이동
      navigate("/review/write");
    }
  };

  const detailsClick = (id) => {
    navigate(`/review/${id}`);
  };

  return (
    <div className="review-app-container">
      <Header />
      <div className="review-content">
        <h1 className="review-title">리뷰 게시판</h1>
        <Link to="/review/write">
          <button className="review-create-btn" onClick={handleCreateReview}>등록</button>
        </Link>

        {/* 게시글 테이블 */}
        <table className="review-table">
          <thead>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>회원 아이디</th>
              <th>작성일자</th>
              <th>조회수</th>
              <th>추천수</th>
            </tr>
          </thead>
          <tbody>
            {reviews.map((item, index) => (
              <tr key={item.id}>
                <td>{(currentPage - 1) * itemsPerPage + index + 1}</td>
                <td onClick={() => detailsClick(item.id)}>{item.title}</td>
                <td onClick>{item.userId}</td>
                <td>{item.date}</td>
                <td>{item.views}</td>
                <td>{item.likes}</td>
              </tr>
            ))}
          </tbody>
        </table>

        {/* 페이지네이션 버튼 */}
        <div className="pagination">
          {[...Array(totalPages).keys()].map((num) => (
            <button
              key={num + 1}
              onClick={() => handlePageChange(num + 1)}
              className={currentPage === num + 1 ? "active" : ""}
            >
              {num + 1}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Review;
