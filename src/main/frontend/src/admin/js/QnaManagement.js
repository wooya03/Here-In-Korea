import React, { useEffect, useState } from "react";
import "../css/QnaManagement.css";
import "../css/Common.css";
import { format } from 'date-fns';
import axios from 'axios';
import { useNavigate } from "react-router-dom";

function formatTime(dateString) {
  const date = new Date(dateString);
  return format(date, 'yyyy-MM-dd h:mm:ss a');
}

function QnaManagement() {
  const baseUrl = "http://localhost:8080";
  const [data, setData] = useState([]); // 현재 페이지 데이터
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
  const [totalPages, setTotalPages] = useState(0); // 총 페이지 수
  const [itemsPerPage] = useState(10); // 페이지당 항목 수
  const [title, setTitle] = useState(""); // 제목 필터 상태
  const [category, setCategory] = useState(""); // 카테고리 필터 상태
  const [selectedItems, setSelectedItems] = useState([]); // 선택된 항목들
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  useEffect(() => {
    putSpringData(currentPage, title, category);
  }, [currentPage, title, category]);

  async function putSpringData(pageNumber, titleFilter, categoryFilter) {
    try {
      const params = {
        page: pageNumber,
        size: itemsPerPage
      };
      if (titleFilter) params.title = titleFilter;
      if (categoryFilter) params.category = categoryFilter;

      const response = await axios.get(baseUrl + "/admin/question", {params,
        headers: {
            Authorization: `Bearer ${token}`, // 헤더에 토큰 추가
        },
    });
      const transformedData = response.data.dtoList
        ? response.data.dtoList.map(item => ({
            id: item.id,
            title: item.title,
            category: item.category,
            createdDate: item.createdDate,
            contents: item.contents,
            memId: item.memId,
            answerContents: item.answerContents
          }))
        : [];
      setData(transformedData);
      setTotalPages(response.data.totalPage);
    } catch (err) {
      if (err.response && (err.response.status === 401 || err.response.status === 403)) {
        alert('권한이 필요합니다.');
        navigate("/admin/login");
      } else {
        console.log(err);
      }
    }
  }

  const handleSelectItem = (id) => {
    setSelectedItems(prevSelected => 
      prevSelected.includes(id) ? prevSelected.filter(item => item !== id) : [...prevSelected, id]
    );
  };

  const handleSearch = () => {
    setCurrentPage(1);
    putSpringData(1, title, category);
  };

  const handleClick = (id) => {
    navigate(`/question/${id}/answer/write`);
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  async function handleDelete(selectedIds) {
    if (selectedIds.length === 0) {
      alert("삭제할 게시글을 선택해주세요.");
      return;
    }
    try {
      await axios({
        method: "delete",
        url: baseUrl + "/admin/question",
        headers: {
          Authorization: `Bearer ${token}`,
        },
        data: selectedItems,
      });
      alert("선택된 문의가 삭제되었습니다.");
      setSelectedItems([]); // 삭제 후 선택 항목 초기화
      putSpringData(currentPage, title, category); // 데이터 갱신
    } catch (error) {
      console.error("삭제 중 오류 발생:", error);
      alert("삭제 중 오류가 발생했습니다.");
    }
  }

  return (
    <div className="app-container">
      <h1>QNA</h1>
      <div className="search-qna">
        <input 
          type="text" 
          placeholder="문의 제목" 
          value={title} 
          onChange={(e) => setTitle(e.target.value)}
        />
        <select 
          value={category} 
          onChange={(e) => setCategory(e.target.value)}
        >
          <option value="">문의구분</option>
          <option value="숙소문의">숙소문의</option>
          <option value="행사문의">행사문의</option>
          <option value="코스문의">코스문의</option>
          <option value="기타문의">기타문의</option>
        </select>
        <button onClick={handleSearch}>조회</button>
      </div>
      <div>
        <button className="delete-button" onClick={handleDelete}>DELETE</button>
      </div>

      <table>
        <thead>
          <tr>
            <th></th>
            <th>제목</th>
            <th>아이디</th>
            <th>내용</th>
            <th>문의구분</th>
            <th>작성일</th>
            <th>답변상태</th>
          </tr>
        </thead>
        <tbody>
          {data.length > 0 ? data.map((item) => (
            <tr key={item.id}>
              <td><input
                  type="checkbox"
                  checked={selectedItems.includes(item.id)}
                  onChange={() => handleSelectItem(item.id)}
                /></td>
              <td>{item.title}</td>
              <td>{item.memId}</td>
              <td>{item.contents}</td>
              <td>{item.category}</td>
              <td>{formatTime(item.createdDate)}</td>
              <td>
                {item.answerContents ? (
                  <span>✔ 답변완료</span>
                ) : (
                  <button className="answer-button" onClick={() => handleClick(item.id)}>답변 등록</button>
                )}
              </td>
            </tr>
          )) : <tr><td colSpan="6">No data available.</td></tr>}
        </tbody>
      </table>

      <div className="pagination">
        {Array.from({ length: totalPages }, (_, index) => (
          <span
            key={index}
            className={currentPage === index + 1 ? "active" : ""}
            onClick={() => handlePageChange(index + 1)}>
            {index + 1}
          </span>
        ))}
      </div>
    </div>
  );
}

export default QnaManagement;
