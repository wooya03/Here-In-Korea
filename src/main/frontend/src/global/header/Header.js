import React from "react";
import { Link } from "react-router-dom"; // Link 추가
import "./Header.css";
import image from '../../img/logo.png';

function Header() {
  return (
    <header className="header">
      <div className="logoimage">
        <Link to="/"> {/* 로고 클릭 시 메인 화면으로 이동 */}
        <img  src={image} className="logo" alt="logo_1" />
        </Link>

        <div className="menu">
          <nav className="nav">
            <ul className="menu">
              <li className="menu-item first-item"><Link to="#main">메인</Link></li>
              <li className="menu-item"><Link to="#festival">행사</Link></li>
              <li className="menu-item"><Link to="#review">리뷰</Link></li>
              <li className="menu-item"><Link to="#corse">여행코스</Link></li>
              <li className="menu-item"><Link to="#accommodation">숙박</Link></li>
              <li className="menu-item"><Link to="#map">지도</Link></li>
              <li className="menu-item last-item"><Link to="#question">Q&A</Link></li>
            </ul>
          </nav>
        </div>

          <div className="search-container">
          <i className="fas fa-search search-icon"></i>
          <input type="text" className="search-input" />
        </div>
      </div>
    </header>
  );
}

export default Header;
