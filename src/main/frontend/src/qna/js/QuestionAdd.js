import React, { useState } from 'react';
import '../css/QuestionAdd.css';
import { useNavigate } from 'react-router-dom';

const QuestionAdd = () => {
  const [questions, setQuestions] = useState({
    questionType: '',
    questionTitle: '',
    questionContent: ''
  });

  const navigate = useNavigate();

  const handleTypeChange = (event) => {
    setQuestions((prev) => ({ ...prev, questionType: event.target.value }));
  };

  const handleTitleChange = (event) => {
    setQuestions((prev) => ({ ...prev, questionTitle: event.target.value }));
  };

  const handleContentChange = (event) => {
    setQuestions((prev) => ({ ...prev, questionContent: event.target.value }));
  };

  const handleSubmit = () => {
    if (questions.questionTitle.trim() === '') {
      alert('제목을 입력해주세요');
      return;
    }
    if (questions.questionContent.trim() === '') {
      alert('내용을 입력해주세요');
      return;
    }

    alert('질문이 등록되었습니다!');
    navigate('/question/list');
  };

  return (
    <div className="question-add-container">
      <div className="question-add-header">
        <input
          type="text"
          value={questions.questionTitle}
          onChange={handleTitleChange}
          placeholder="제목을 입력하세요"
          className="question-title-input"
        />
        <button className="submit-button" onClick={handleSubmit}>
          등록
        </button>
      </div>
      <div className="question-type">
        <select value={questions.questionType} onChange={handleTypeChange}>
        <option value="숙소 문의">숙소 문의</option>
            <option value="행사 문의">행사 문의</option>
            <option value="리뷰 문의">리뷰 문의</option>
            <option value="코스 문의">코스 문의</option>
            <option value="기타 문의">기타 문의</option>
        </select>
      </div>
      <textarea
        className="question-content"
        value={questions.questionContent}
        onChange={handleContentChange}
        placeholder="질문 내용을 입력하세요"
      />
    </div>
  );
};

export default QuestionAdd;
