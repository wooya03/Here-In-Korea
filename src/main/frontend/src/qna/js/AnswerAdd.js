import React, { useEffect, useState } from 'react';import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import '../css/AnswerAdd.css';

const AnswerAdd = () => {
    const {id} = useParams();

    const [data, setData] = useState({
      contents: '',
      questionId: id,
      createdDate: new Date().toISOString(),
      modifiedDate: new Date().toISOString()
    });

    useEffect(() => {
      setData((prev) => ({ ...prev, questionId: id }));
    }, [id]);
    
    const handleContentChange = (event) => {
      console.log('Content:', event.target.value);
      setData((prev) => ({ ...prev, contents: event.target.value }));
    };
  
    const navigate = useNavigate();

    const handleSubmit = async () => {
      if (data.contents.trim() === '') {
        alert('내용을 입력해주세요');
        return;
      }
  
      try {
        console.log(data);
        const token = localStorage.getItem("token");
        const response = await axios.post('http://localhost:8080/answer/write', data, {
          headers: {
            Authorization: `Bearer ${token}`, // Authorization 헤더로 전달
          },
        });
        if (response.status === 201) {
          alert('답변이 등록되었습니다!');
          navigate('/admin/qna');
        }
      } catch (error) {
        console.error('답변 등록 중 오류 발생:', error);
        alert('답변 등록에 실패했습니다.');
      }
    };
  
    return (
      <div className="answer-add-container">
        <div className="answer-add-header">
          <button className="submit-button" onClick={handleSubmit}>등록</button>
        </div>
        <textarea
          className="answer-content"
          value={data.contents}
          onChange={handleContentChange}
          placeholder="답변 내용을 입력하세요"
        />
      </div>
    );
  };
  
  export default AnswerAdd;
  