import React, { Component } from "react";
import Header from "../../global/header/Header"; // Header 컴포넌트 불러오기
import { Link } from "react-router-dom"; 
import "../css/Review.css"; // Review 컴포넌트 스타일

class Review extends Component {
    constructor(props) {
        super(props);
        this.state = {
            searchText: "",
            currentPage: 1,           // 현재 페이지 번호
            itemsPerPage: 5,          // 페이지당 아이템 수
            filteredDate: [
                { id: 1, title: "대전이 보유한 최고의 놀이터 심성당 리뷰", userId: "ync2024", date: "2024-04-21 15:44", views: 410230, likes: 21404 },
                { id: 2, title: "전라도 맛집 추천", userId: "ync2024", date: "2024-04-21 15:44", views: 130, likes: 42 },
                { id: 3, title: "애니 오타쿠를 위한 덕질 투어", userId: "ync2024", date: "2024-04-21 15:44", views: 180, likes: 32 },
                { id: 4, title: "도미는 여기가 맛있어요", userId: "test012", date: "2024-04-21 15:44", views: 1230, likes: 242 },
                { id: 5, title: "서울에서 가까운 꽃 축제 탐방", userId: "test789", date: "2024-04-21 15:44", views: 302, likes: 30 },
                // 더 많은 데이터 추가 가능
            ]
        };
    }

    // 페이지 변경 함수
    handlePageChange = (pageNumber) => {
        this.setState({ currentPage: pageNumber });
    }

    render() {
        const { currentPage, itemsPerPage, filteredDate } = this.state;

        // 페이지네이션 로직
        const indexOfLastItem = currentPage * itemsPerPage;
        const indexOfFirstItem = indexOfLastItem - itemsPerPage;
        const currentItems = filteredDate.slice(indexOfFirstItem, indexOfLastItem);

        return (
            <div className="review-app-container">
                <Header /> {/* 헤더 컴포넌트 추가 */}
                <div className="review-content">
                    <h1 className="review-title">
                        리뷰 게시판
                    </h1>
                    <Link to="/review/write">
                        <button className="review-create-btn">등록</button>
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
                            {currentItems.map((item, index) => (
                                <tr key={item.id}>
                                    <td>{indexOfFirstItem + index + 1}</td>
                                    <td>{item.title}</td>
                                    <td>{item.userId}</td>
                                    <td>{item.date}</td>
                                    <td>{item.views}</td>
                                    <td>{item.likes}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>

                    {/* 페이지네이션 버튼 */}
                    <div className="pagination">
                        {[...Array(Math.ceil(filteredDate.length / itemsPerPage)).keys()].map(num => (
                            <button
                                key={num + 1}
                                onClick={() => this.handlePageChange(num + 1)}
                                className={currentPage === num + 1 ? "active" : ""}
                            >
                                {num + 1}
                            </button>
                        ))}
                    </div>
                </div>
            </div>
        );
    }
}

export default Review;