import React, { useState, useEffect } from "react";
import "../css/UserManagement.css";
import "../css/Common.css";
import { format } from "date-fns";
import axios from "axios";
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

const UserManagement = () => {
  const baseUrl = "http://localhost:8080";
  const navigate = useNavigate();
  const [data, setData] = useState([]); // 현재 페이지 데이터
  const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
  const [totalPages, setTotalPages] = useState(0); // 총 페이지 수
  const [itemsPerPage] = useState(10); // 페이지당 항목 수
  const [searchKeyword, setSearchKeyword] = useState(""); // 검색어
  const [gender, setGender] = useState(""); // 성별 필터
  const token = localStorage.getItem("token");

  useEffect(() => {
    putSpringData(currentPage, searchKeyword, gender); // 컴포넌트 로드 시 데이터 요청
  }, [currentPage, gender]);

  useEffect(() => {
    // 검색어가 변경될 때마다 첫 페이지로 이동
    if (searchKeyword !== "" || gender) {
      setCurrentPage(1);
      putSpringData(1, searchKeyword, gender);
    }
  }, [searchKeyword, gender]);

  async function putSpringData(pageNumber, keyword, selectedGender) {
    try {
      const params = {
        page: pageNumber,
        memName: keyword,
        gender: selectedGender,
        size: itemsPerPage
      };
      const response = await axios.get(
        `${baseUrl}/admin/member`, {params,
          headers: {
              Authorization: `Bearer ${token}`, // 헤더에 토큰 추가
          },
      });
      const transformedData = response.data.dtoList
        ? response.data.dtoList.map((item) => {
            return {
              memid: item.memId,
              memname: item.memName,
              loginDate: item.loginDate,
              signDate: item.signDate,
              gender: item.gender,
              birth: item.birth,
              email: item.email
            };
          })
        : [];
      setData(transformedData); // 현재 페이지 데이터 설정
      setTotalPages(response.data.totalPage); // 총 페이지 수 설정
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
    setCurrentPage(pageNumber); // 페이지 상태 업데이트
  };

  const handleSearchChange = (e) => {
    setSearchKeyword(e.target.value); // 검색어 상태 업데이트
  };

  const handleGenderChange = (e) => {
    setGender(e.target.value); // 성별 필터 상태 업데이트
  };

  const handleSearch = () => {
    setCurrentPage(1); // 검색 후 첫 페이지로 이동
    putSpringData(1, searchKeyword, gender); // 검색어 및 성별로 데이터 요청
  };

  return (
    <div className="app-container">
      <h1>회원정보조회</h1>

      {/* 검색 입력 및 버튼 */}
      <form className="search-user" onSubmit={(e) => e.preventDefault()}>
        <input
          type="text"
          value={searchKeyword}
          onChange={handleSearchChange}
          placeholder="회원 이름으로 검색"
        />
        
        {/* 성별 필터 */}
        <div className="gender-selection">
          <label>
            <input
              type="radio"
              value="M"
              checked={gender === "M"}
              onChange={handleGenderChange}
            />
            남
          </label>
          <label>
            <input
              type="radio"
              value="F"
              checked={gender === "F"}
              onChange={handleGenderChange}
            />
            <span>여</span>
          </label>
          <label>
            <input
              type="radio"
              value=""
              checked={gender === ""}
              onChange={handleGenderChange}
            />
            전체
          </label>
          
          <button onClick={handleSearch}>검색</button>
        </div>
      </form>

      <table>
        <thead>
          <tr>
            <th></th>
            <th>회원 아이디</th>
            <th>회원 이름</th>
            <th>가입일자</th>
            <th>로그인일자</th>
            <th>성별</th>
            <th>생년월일</th>
            <th>이메일</th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              <td>
                <input type="checkbox" />
              </td>
              <td>{item.memid}</td>
              <td>{item.memname}</td>
              <td>{formatTime(item.signDate)}</td>
              <td>{formatTime(item.loginDate)}</td>
              <td>{item.gender}</td>
              <td>{item.birth}</td>
              <td>{item.email}</td>
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
};

export default UserManagement;
