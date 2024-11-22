import React, { useEffect, useState } from 'react';
import '../css/QuestionList.css';
import { useNavigate } from 'react-router-dom';
import { format } from 'date-fns';
import axios from 'axios';

function formatTime(dateString) {
  const date = new Date(dateString);
  return format(date, 'yyyy-MM-dd h:mm:ss a');
}

function QuestionList() {
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
            status: item.status,  // 상태
            answered: item.sstatus  // 질문의 답변 여부
          };
        }) : [];
        setData(transformedData);  // 변환된 데이터를 상태로 설정
      })
      .catch((err) => {
        console.log(err);
      });
  }

  const navigate = useNavigate();  // useNavigate 훅 사용

  const handleClick = () => {
    navigate('/question/write');  // 버튼 클릭 시 해당 경로로 이동
  };

  const detailsClick = (id) => {
    navigate(`/question/${id}`);
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
        {data && data.length > 0 ? data.map((datas) => (
          <div key={datas.id} className="question-card">
            <div className="question-header">
              <span className="question-id">No {datas.id} {datas.category}</span>
              <span className="question-date">{formatTime(datas.createdDate)}</span> {/* 포맷팅된 시간 표시 */}
            </div>
            <h3 className="question-title" key={datas.id} onClick={() => detailsClick(datas.id)}>{datas.title}</h3>
            <div className="question-status">
              {datas.answered ? (
                <span className="status answered">✔ 답변완료</span>
              ) : (
                <span className="status pending">✖ 답변대기중</span>
              )}
            </div>
          </div>
        )) : <p>No data available.</p>}
      </div>
    </div>
  );
}

export default QuestionList;
