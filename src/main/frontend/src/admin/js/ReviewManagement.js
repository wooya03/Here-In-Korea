import React, { useEffect, useState } from "react";
import "../css/ReviewManagement.css";
import axios from 'axios';
import { format } from 'date-fns';
import { useNavigate } from "react-router-dom";

function formatTime(dateString) {
  if (!dateString) return "날짜 정보 없음"; 
  const date = new Date(dateString);

  if (isNaN(date.getTime())) {
    return "잘못된 날짜";
  }

  return format(date, "yyyy-MM-dd h:mm:ss a"); 
}

const ReviewManagement = () => {
  const baseUrl = "http://localhost:8080";
  const navigate = useNavigate();
  const [data, setData] = useState([]); // 현재 페이지 데이터
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
  const [totalPages, setTotalPages] = useState(0); // 총 페이지 수
  const [itemsPerPage] = useState(10); // 페이지당 항목 수
  const [title, setTitle] = useState(""); // 검색어
  const [memId, setmemId] = useState(""); // 아이디
  const [selectedItems, setSelectedItems] = useState([]); // 선택된 항목들
  const token = localStorage.getItem("token");

  useEffect(() => {
    putSpringData(currentPage, title, memId); // 컴포넌트 로드 시 데이터 요청
  }, [currentPage, title, memId]);

  useEffect(() => {
    // 검색어가 변경될 때마다 첫 페이지로 이동
    if (title !== "" || memId !== "") {
      setCurrentPage(1);
      putSpringData(1, title, memId);
    }
  }, [title, memId]);

  async function putSpringData(pageNumber) {
    try {
      const params = {
        page: pageNumber,
        size: itemsPerPage,
        reviewTitle: title,
        memId: memId
      };

      const response = await axios.get(baseUrl + "/admin/review", {
        params,
        headers: {
          Authorization: `Bearer ${token}`, // 헤더에 토큰 추가
        },
      });
      const transformedData = response.data.dtoList
        ? response.data.dtoList.map(item => ({
            id: item.reviewId,
            title: item.reviewTitle,
            userId: item.memId,
            date: item.createdDate,
            views: item.reviewViews,
            likes: item.reviewLikes
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

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const handleTitleChange = (e) => {
    setTitle(e.target.value); 
  };

  const handleIdChange = (e) => {
    setmemId(e.target.value); 
  };

  const handleSearch = () => {
    setCurrentPage(1); 
    putSpringData(1, title, memId);
  };

  const handleSelectItem = (id) => {
    setSelectedItems(prevSelected => 
      prevSelected.includes(id) ? prevSelected.filter(item => item !== id) : [...prevSelected, id]
    );
  };

  const handleDelete = async () => {
    if (selectedItems.length === 0) {
      alert("삭제할 항목을 선택하세요.");
      return;
    }
  
    if (window.confirm("정말 삭제하겠습니까?")) {
      try {
        await axios({
          method: "delete",
          url: baseUrl + "/admin/review",
          headers: {
            Authorization: `Bearer ${token}`,
          },
          data: selectedItems, // 삭제할 리뷰 ID들
        });
        alert("선택된 리뷰가 삭제되었습니다.");
        setSelectedItems([]); // 삭제 후 선택 항목 초기화
        putSpringData(currentPage, title, memId); // 데이터 갱신
      } catch (err) {
        console.error(err);
        alert("삭제에 실패했습니다.");
      }
    }
  };  

  return (
    <div className="app-container">
      <h1>리뷰게시판관리</h1>
      <div className="search-review">
        <input
          type="text"
          placeholder="게시글 이름"
          value={title}
          onChange={handleTitleChange}
        />
        <input type="text" placeholder="아이디 검색" value={memId} onChange={handleIdChange}/>
        <button onClick={handleSearch}>조회</button>
      </div>

      <button className="delete-button" onClick={handleDelete}>DELETE</button>

      <table>
        <thead>
          <tr>
            <th></th>
            <th>제목</th>
            <th>아이디</th>
            <th>작성일</th>
            <th>조회수</th>
            <th>추천</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item) => (
            <tr key={item.id}>
              <td>
                <input
                  type="checkbox"
                  checked={selectedItems.includes(item.id)}
                  onChange={() => handleSelectItem(item.id)}
                />
              </td>
              <td>{item.title}</td>
              <td>{item.userId}</td>
              <td>{formatTime(item.date)}</td>
              <td>{item.views}</td>
              <td>{item.likes}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <div className="pagination">
        {Array.from({ length: totalPages }, (_, index) => (
          <span
            key={index}
            className={currentPage === index + 1 ? "active" : ""}
            onClick={() => handlePageChange(index + 1)}
          >
            {index + 1}
          </span>
        ))}
      </div>
    </div>
  );
}

export default ReviewManagement;
