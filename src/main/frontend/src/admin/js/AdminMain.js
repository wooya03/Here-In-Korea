import React from "react";
import '../css/AdminMain.css'

class AdminMain extends React.Component {
    render() {
        return (
            <div className="admin-container">
            <h1 className="admin-title">ADMINISTRATOR</h1>
            <div className="info-box">
                <p>현재 회원 수: <span className="count">103042</span></p>
                <p>누적 게시글 수: <span className="count">40345</span></p>
                <p>오늘 등록 게시글 수: <span className="count">4345</span></p>
            </div>
        </div>
        )
    }
}

export default AdminMain;