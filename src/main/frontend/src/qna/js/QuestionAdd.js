import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../css/QuestionAdd.css';

const QuestionAdd = () => {
  const [questions, setQuestions] = useState({
    title: '',
    category: '', 
    contents: '',
    status: false,
    memId: 'user002',
    memName: '함황여',
    createdDate: new Date().toISOString(),
    modifiedDate: new Date().toISOString()
  });

  const navigate = useNavigate();


  const handleTitleChange = (event) => {
    setQuestions((prev) => ({ ...prev, title: event.target.value }));
  };

  const handleCategoryChange = (event) => {
    console.log('Selected Category:', event.target.value); // 카테고리 값 확인
    setQuestions((prev) => ({ ...prev, category: event.target.value }));
  };
  
  const handleContentChange = (event) => {
    console.log('Content:', event.target.value); // 내용 값 확인
    setQuestions((prev) => ({ ...prev, contents: event.target.value }));
  };

  const handleSubmit = async () => {
    if (questions.contents.trim() === '') {
      alert('문의 유형을 선택해주세요');
      return;
    }
    if (questions.title.trim() === '') {
      alert('제목을 입력해주세요');
      return;
    }
    
    if (questions.contents.trim() === '') {
      alert('내용을 입력해주세요');
      return;
    }

    try {
      console.log(questions);
      // Spring Boot로 데이터 전송
      const response = await axios.post('http://localhost:8080/question/write', questions);
      if (response.status === 201) {
        alert('질문이 등록되었습니다!');
        navigate('/question/list');
      }
    } catch (error) {
      console.error('질문 등록 중 오류 발생:', error);
      alert('질문 등록에 실패했습니다.');
    }
  };

  return (
    <div className="question-add-container">
      <div className="question-add-header">
        <input
          type="text"
          value={questions.title}
          onChange={handleTitleChange}
          placeholder="제목을 입력하세요"
          className="question-title-input"
        />
        <button className="submit-button" onClick={handleSubmit}>등록</button>
      </div>
      <div className="question-type">
        <select value={questions.category} onChange={handleCategoryChange}>
          <option value="">문의 유형 선택</option>
          <option value="숙소문의">숙소문의</option>
          <option value="행사문의">행사문의</option>
          <option value="리뷰문의">리뷰문의</option>
          <option value="코스문의">코스문의</option>
          <option value="기타문의">기타문의</option>
        </select>
      </div>
      <textarea
        className="question-content"
        value={questions.contents}
        onChange={handleContentChange}
        placeholder="질문 내용을 입력하세요"
      />
    </div>
  );
};

export default QuestionAdd;
