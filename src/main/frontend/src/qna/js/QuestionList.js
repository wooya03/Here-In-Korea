import React, { useEffect, useState } from "react";
import "../css/QuestionList.css";
import { useNavigate } from "react-router-dom";
import { format } from "date-fns";
import axios from "axios";

function formatTime(dateString) {
  const date = new Date(dateString);
  return format(date, "yyyy-MM-dd h:mm:ss a");
}

function QuestionList() {
  const baseUrl = "http://localhost:8080";
  const [data, setData] = useState([]); // 전체 데이터
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
  const [itemsPerPage] = useState(5); // 페이지당 항목 수

  useEffect(() => {
    putSpringData();
  }, []);

  async function putSpringData() {
    await axios
      .get(baseUrl + "/question/list")
      .then((res) => {
        const transformedData = res.data.dtoList
          ? res.data.dtoList.map((item) => {
              return {
                id: item.id,
                title: item.title,
                category: item.category,
                createdDate: item.createdDate,
                status: item.status,
                answered: item.sstatus,
              };
            })
          : [];
        setData(transformedData); // 변환된 데이터를 상태로 설정
      })
      .catch((err) => {
        console.log(err);
      });
  }

  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/question/write"); // 버튼 클릭 시 해당 경로로 이동
  };

  const detailsClick = (id) => {
    navigate(`/question/${id}`);
  };

  // 페이지네이션 처리
  const indexOfLastItem = currentPage * itemsPerPage;
  const indexOfFirstItem = indexOfLastItem - itemsPerPage;
  const currentItems = data.slice(indexOfFirstItem, indexOfLastItem);

  const totalPages = Math.ceil(data.length / itemsPerPage);

  const handlePageChange = (page) => {
    setCurrentPage(page);
  };

  const renderPagination = () => {
    const pageNumbers = Array.from({ length: totalPages }, (_, i) => i + 1);

    return (
      <div className="pagination">
        {pageNumbers.map((page) => (
          <span
            key={page}
            className={page === currentPage ? "active" : ""}
            onClick={() => handlePageChange(page)}
          >
            {page}
          </span>
        ))}
      </div>
    );
  };

  return (
    <div className="container">
      <div className="search-bar">
        <div className="search-dropdown">
          <select>
            <option>문의 구분</option>
            <option value="숙소 문의">숙소 문의</option>
            <option value="행사 문의">행사 문의</option>
            <option value="리뷰 문의">리뷰 문의</option>
            <option value="코스 문의">코스 문의</option>
            <option value="기타 문의">기타 문의</option>
          </select>
        </div>
        <div className="ask-button">
          <button onClick={handleClick}>질문하기</button>
        </div>
      </div>

      <div className="question-list">
        {currentItems && currentItems.length > 0 ? (
          currentItems.map((datas) => (
            <div key={datas.id} className="question-card">
              <div className="question-header">
                <span className="question-id">
                  No {datas.id} {datas.category}
                </span>
                <span className="question-date">
                  {formatTime(datas.createdDate)}
                </span>
              </div>
              <h3
                className="question-title"
                onClick={() => detailsClick(datas.id)}
              >
                {datas.title}
              </h3>
              <div className="question-status">
                {datas.answered ? (
                  <span className="status answered">✔ 답변완료</span>
                ) : (
                  <span className="status pending">✖ 답변대기중</span>
                )}
              </div>
            </div>
          ))
        ) : (
          <p>No data available.</p>
        )}
      </div>

      {renderPagination()}
    </div>
  );
}

export default QuestionList;
