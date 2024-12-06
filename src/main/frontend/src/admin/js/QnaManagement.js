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
  const [data, setData] = useState([]);  // 빈 배열로 초기화

  useEffect(() => {
    putSpringData();
  }, []);

  async function putSpringData() {
    await axios.get(baseUrl + "/question/list")
      .then((res) => {
        // dtoList에서 데이터를 가져오도록 수정
        const transformedData = res.data.dtoList ? res.data.dtoList.map(item => {
          return {
            id: item.id,  // 질문 ID
            title: item.title,  // 질문 제목
            category: item.category,  // 문의 구분
            createdDate: item.createdDate,  // 생성일
            answered: item.qstatus,  // 질문의 답변 여부
            memId : item.memId
          };
        }) : [];
        setData(transformedData);  // 변환된 데이터를 상태로 설정
      })
      .catch((err) => {
        console.log(err);
      });
  }

  const [selectedIds, setSelectedIds] = useState([]);

  function handleCheckboxChange(event, id) {
    if (event.target.checked) {
      setSelectedIds([...selectedIds, id]);
    } else {
      setSelectedIds(selectedIds.filter((selectedId) => selectedId !== id));
    }
  }

  const navigate = useNavigate();

  const handleClick = (id) => {
    navigate(`/question/${id}/answer/write`); // 버튼 클릭 시 해당 경로로 이동
  };
  

  async function handleDelete() {
    if (selectedIds.length === 0) {
      alert("삭제할 게시글을 선택해주세요.");
      return;
    }
  
    try {
      const response = await axios.delete(baseUrl + `/question/delete/${selectedIds}`);
      if (response.status === 200) {
        alert("삭제가 완료되었습니다.");
      }
    } catch (error) {
      console.error("삭제 중 오류 발생:", error);
      alert("삭제 중 오류가 발생했습니다.");
    }
  }

  return (
      <div className="app-container">
        <h1>QNA</h1>
        <div className="search-qna">
          <input type="text"placeholder="문의 제목" />
          <input type="text" placeholder="아이디 검색" />
          <select>
            <option value="">문의구분</option>
            <option value="숙소문의">숙소문의</option>
            <option value="행사문의">행사문의</option>
            <option value="코스문의">코스문의</option>
            <option value="기타문의">기타문의</option>
          </select>
          <p>답변대기</p><input type="checkbox" value="answer"/>
          <input type="date" />~<input type="date" />
          <button>조회</button>
        </div>

        <button className="delete-button" onClick={handleDelete}>DELETE</button>

        <table>
          <thead>
            <tr>
              <th></th>
              <th>제목</th>
              <th>아이디</th>
              <th>문의구분</th>
              <th>작성일</th>
              <th>답변상태</th>
            </tr>
          </thead>
          <tbody>
          {data && data.length > 0 ? data.map((datas) => (
              <tr key={datas.id}>
                <td><input type="checkbox" onChange={(e) => handleCheckboxChange(e, datas.id)} /></td>
                <td>{datas.title}</td>
                <td>{datas.memId}</td>
                <td>{datas.category}</td>
                <td>{formatTime(datas.createdDate)}</td>
                {datas.answered ? (
                <td>✔ 답변완료</td>
              ) : (
                <td><button onClick={() => handleClick(datas.id)}>답변 등록</button></td>
              )}
              </tr>
            )) : <tr><td>No data available.</td></tr>}
          </tbody>
        </table>
      </div>
    )
}

export default QnaManagement;