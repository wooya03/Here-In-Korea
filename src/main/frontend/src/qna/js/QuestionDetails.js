import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import '../css/QuestionDetails.css';
import { format } from "date-fns";
import axios from "axios";

function formatTime(dateString) {
  if (!dateString) return "날짜 정보 없음"; // dateString이 null/undefined/빈 문자열일 경우 처리
  const date = new Date(dateString);

  if (isNaN(date.getTime())) {
    // 유효하지 않은 날짜 포맷 처리
    return "잘못된 날짜";
  }

  return format(date, "yyyy-MM-dd h:mm:ss a"); // 유효한 경우 날짜 포맷팅
}

function QuestionDetails() {
    const baseUrl = "http://localhost:8080";
    const [questionData, setQuestionData] = useState(null);
    const { id } = useParams();

    useEffect(() => {
        // 질문 데이터 가져오기
        axios.get(baseUrl + `/question/${id}`)
            .then((res) => {
                const question = res.data;
                setQuestionData({
                    id: question.id,
                    title: question.title,
                    category: question.category,
                    contents: question.contents,
                    createdDate: question.createdDate,
                    memName: question.memName,
                    answerContents: question.answerContents
                });
            })
            .catch((error) => {
                console.error('질문 데이터 오류:', error);
            });
    }, [id]);

    if (!questionData) return <p>질문 데이터를 불러오는 중...</p>;

    return (
        <div className="question-container">
            <div className="question-title">
                <h1>{questionData.title}</h1>
            </div>
            <div className="question-header">
                <div className="info">
                    <span>작성자: {questionData.memName}</span>
                    <span>문의구분: {questionData.category}</span>
                    <span>작성일: {formatTime(questionData.createdDate)}</span>
                </div>
            </div>
            <div className="question-style">
                <div className="question-text">
                    {questionData.contents}
                </div>
            </div>
            <div className="answer">
                <h2>답변 내용</h2>
                 {questionData.answerContents ? (
                    <p>{questionData.answerContents}</p>
                ) : (
                    <p>등록된 답변이 없습니다.</p>
                )}
             </div>
        </div>
    );
}

export default QuestionDetails;
