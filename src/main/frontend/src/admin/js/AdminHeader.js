import React from "react";
import { Link } from "react-router-dom"; // Link 추가
import '../css/AdminHeader.css';

class AdminHeader extends React.Component {
    render (){
        return (
            <header className="header">
      <div className="logoimage">
        <Link to="/"> {/* 로고 클릭 시 메인 화면으로 이동 */}
          <img className="logo" alt="logo_1" src="logo.png" />
        </Link>

        <div className="menu">
          <nav className="nav">
            <ul className="menu">
              <li className="menu-item first-item"><Link to="/admin">메인</Link></li>
              <li className="menu-item"><Link to="/admin/meminfo">회원정보조회</Link></li>
              <li className="menu-item"><Link to="#">코스게시판관리</Link></li>
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