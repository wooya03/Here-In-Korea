import React from "react";
import { Link } from "react-router-dom";
import '../css/AdminHeader.css';
import image from '../../img/logo.png';

class AdminHeader extends React.Component {
    render (){
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
              <li className="menu-item"><Link to="#">리뷰게시판관리</Link></li>
              <li className="menu-item"><Link to="#">댓글관리</Link></li>
              <li className="menu-item"><Link to="#">Q&A</Link></li>
              <li className="menu-item last-item"><Link to="#">LOGOUT</Link></li>
            </ul>
          </nav>
        </div>
      </div>
    </header>
        )
    }
}


export default AdminHeader;