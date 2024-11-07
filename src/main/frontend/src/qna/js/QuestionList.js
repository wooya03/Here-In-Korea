import React, { useState } from 'react';
import '../css/QuestionList.css';
import { useNavigate } from 'react-router-dom';

const QuestionList = () => {
  const [questions] = useState([
    {
      id: 4,
      type: '숙소 문의',
      title: '나무호텔 문의 드립니다.',
      status: '문의 확인중입니다.',
      answered: false,
      date: '2024.06.13 18:31'
    },
    {
      id: 3,
      type: '행사 문의',
      title: '한강 서래섬 유채꽃 축제 주차공간',
      status: '답변 완료',
      answered: true,
      date: '2024.06.04 22:54'
    }
  ]);

  const navigate = useNavigate();  // useNavigate 훅 사용

  const handleClick = () => {
    navigate('/question/write');  // 버튼 클릭 시 해당 경로로 이동
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
        {questions.map((question) => (
          <div key={question.id} className="question-card">
            <div className="question-header">
              <span className="question-id">No {question.id}</span>
              <span className="question-date">{question.date}</span>
            </div>
            <h3 className="question-title">{question.title}</h3>
            <div className="question-status">
              {question.answered ? (
                <span className="status answered">✔ {question.status}</span>
              ) : (
                <span className="status pending">✖ {question.status}</span>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default QuestionList;
