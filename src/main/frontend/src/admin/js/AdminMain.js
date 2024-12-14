import React, { useState, useEffect } from "react";
import '../css/AdminMain.css';
import axios from "axios";

const AdminMain = () => {          
    const baseUrl = "http://localhost:8080";
    const [data, setData] = useState({
        memberCount: 0,
    });
    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        putSpringData();
    }, []);
    
    async function putSpringData() {
        const token = localStorage.getItem("token"); // 예: 토큰을 localStorage에서 가져옴
        if (!token) {
            setErrorMessage("권한이 필요합니다");
            return;
        }

        try {
            const res = await axios.get(baseUrl + "/admin/main", {
                headers: {
                    Authorization: `Bearer ${token}`, // 헤더에 토큰 추가
                },
            });
            setData({ ...data, memberCount: res.data });
        } catch (err) {
            if (err.response && err.response.status === 401) {
                setErrorMessage("권한이 필요합니다");
            } else {
                console.log(err);
            }
        }
    }
     
    return (
        <div className="admin-container">
            <h1 className="admin-title">ADMINISTRATOR</h1>
            {errorMessage ? (
                <p className="error-message">{errorMessage}</p>
            ) : (
                <div className="info-box">
                    <p>현재 회원 수: <span className="count">{data.memberCount}</span></p>
                    <p>누적 게시글 수: <span className="count">40345</span></p>
                    <p>오늘 등록 게시글 수: <span className="count">4345</span></p>
                </div>
            )}
        </div>
    );
}

export default AdminMain;
