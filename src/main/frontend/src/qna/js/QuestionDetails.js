import React, { useEffect, useState } from 'react';
import { format } from 'date-fns';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import '../css/QuestionDetails.css';

function QuestionDetails() {
    const baseUrl = "http://localhost:8080";
    const [data, setData] = useState();
    const {id} = useParams();
  
    useEffect(() => {
        axios.get(baseUrl + `/question/${id}`)
        .then((res) => {
            setData(res.data);
        })
        .catch((error) => {
            console.error(error);
        });
    }, [id]);

    if (!data) return <p>No data available.</p>;

    return (
        <div className="question-container">
            <div className="question-title">
                <h1>{data.title}</h1>
            </div>
            <div className="question-header">
                <div className='info'>
                    <span>작성자 : {data.memName}</span>
                    <span>문의구분 : {data.category}</span>
                    <span>작성일 : {data.createdDate}</span>
                </div>
            </div>
            <div className="question-style">
                <div className="question-text">
                    {data.contents}
                </div>
            </div>
            <div class="answer">
                답변내용
            </div>
        </div>
    )
}

export default QuestionDetails;