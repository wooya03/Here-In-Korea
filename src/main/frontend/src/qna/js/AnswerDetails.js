import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';


function AnswerDetails() {
    // const baseUrl = "http://localhost:8080";
    // const [data, setData] = useState();
    // const {id} = useParams();
  
    // useEffect(() => {
    //     axios.get(baseUrl + `/answer/${id}`)
    //     .then((res) => {
    //         setData(res.data);
    //     })
    //     .catch((error) => {
    //         console.error(error);
    //     });
    // }, [id]);

    // if (!data) return <p>No data available.</p>;

    // return (
    //     <div className="answer-container">
    //         <div className="answer-text">
    //             {data.contents}
    //         </div>
    //     </div>
    // )
}

export default AnswerDetails;