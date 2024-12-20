import React from "react";
import { Link, useNavigate } from "react-router-dom";
import '../css/AdminHeader.css';
import image from '../../img/logo.png';
import { useAuth } from "../../global/auth_context/AuthContext";

const AdminHeader = () => {
  const { isLoggedIn, checkLoginStatus } = useAuth();
  const navigate = useNavigate();
  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("refreshToken");
    alert("로그아웃 되었습니다!!!");
    checkLoginStatus();
    navigate(0);
  };

        return (
            <header className="header">
      <div className="logoimage">
        <Link to="/admin">
          <img  src={image} className="logo" alt="logo_1" />
        </Link>

        <div className="menu">
          <nav className="nav">
            <ul className="menu">
              <li className="menu-item first-item"><Link to="/admin/user">회원정보조회</Link></li>
              <li className="menu-item"><Link to="/admin/course">코스게시판관리</Link></li>
              <li className="menu-item"><Link to="/admin/review">리뷰게시판관리</Link></li>
              <li className="menu-item"><Link to="/admin/qna">Q&A</Link></li>
              <li className="menu-item last-item"><Link onClick={handleLogout}>LOGOUT</Link></li>
            </ul>
          </nav>
        </div>
      </div>
    </header>
  )
}


export default AdminHeader;