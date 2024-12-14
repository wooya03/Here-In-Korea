import React, { useState, useEffect } from "react";
import '../css/AdminMain.css'
import axios from "axios";


const AdminMain = () => {          
    const baseUrl = "http://localhost:8080";
    const [data, setData] = useState({

        memberCount: 0
    });
    
    useEffect(() => {
        putSpringData();
    }, []);
    
    async function putSpringData() {
        await axios
            .get(baseUrl + "/admin/main")
            .then((res) => {
                setData({ ...data, memberCount: res.data });
            })
            .catch((err) => {
                console.log(err);
            });
    }
     
    return (
        <div className="admin-container">
            <h1 className="admin-title">ADMINISTRATOR</h1>
            <div className="info-box">
                <p>현재 회원 수: <span className="count">{data.memberCount}</span></p>
                <p>누적 게시글 수: <span className="count">40345</span></p>
                <p>오늘 등록 게시글 수: <span className="count">4345</span></p>
            </div>
        </div>
    )
}

export default AdminMain;