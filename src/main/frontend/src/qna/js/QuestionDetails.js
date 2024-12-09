import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import '../css/QuestionDetails.css';

function QuestionDetails() {
    const baseUrl = "http://localhost:8080";
    const [questionData, setQuestionData] = useState(null); // 질문 데이터
    const [answerData, setAnswerData] = useState([]); // 답변 데이터
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
                    answered: question.qstatus // 답변 상태
                });
            })
            .catch((error) => {
                console.error('질문 데이터 오류:', error);
            });

        // 답변 데이터 가져오기
        axios.get(baseUrl + `/answer/${id}`)
            .then((res) => {
                setAnswerData(res.data); // 답변 데이터를 상태에 저장
            })
            .catch((error) => {
                console.error('답변 데이터 오류:', error);
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
                    <span>작성일: {questionData.createdDate}</span>
                </div>
            </div>
            <div className="question-style">
                <div className="question-text">
                    {questionData.contents}
                </div>
            </div>
            <div className="answer">
                <h2>답변 내용</h2>
                 {/* {answerData.length > 0 ? (
                    answerData.map((answer, index) => (
                        <div key={index} className="answer-item">
                            <p>{answer.contents}</p>
                            <span>작성자: {answer.memName}</span>
                            <span>작성일: {answer.createdDate}</span>
                        </div>
                    ))
                ) : (
                    <p>등록된 답변이 없습니다.</p>
                )} */}
             </div>
        </div>
    );
}

export default QuestionDetails;
