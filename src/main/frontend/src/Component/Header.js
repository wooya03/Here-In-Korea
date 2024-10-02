import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Header.css";

function Header() {
  // 로그인 상태와 프로필 이미지 상태 관리
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [guestImage, setGuestImage] = useState(`${process.env.PUBLIC_URL}/Image/guest1.png`); // 초기 이미지를 guest1.png로 설정

  const handleLoginLogout = () => {
    if (isLoggedIn) {
      // 로그아웃 시
      setGuestImage(`${process.env.PUBLIC_URL}/Image/guest1.png`); // 게스트 이미지로 변경
      setIsLoggedIn(false); // 로그인 상태를 false로 변경
    } else {
      // 로그인 시
      setGuestImage(`${process.env.PUBLIC_URL}/Image/user1.png`); // 사용자 이미지로 변경
      setIsLoggedIn(true); // 로그인 상태를 true로 변경
    }
  };

  return (
    <header className="header">
      <div className="logoimage">
        <Link to="/">
          <img className="logo" alt="logo_1" src={`${process.env.PUBLIC_URL}/Image/logo_1.png`} />
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
          <input type="text" className="search-input" />
          <button className="search-button">검색</button>
        </div>

        <div className="login-container">
          <div className="guest-image">
            <img alt="guest" src={guestImage} style={{ width: '40px', height: '40px', borderRadius: '50%' }} /> {/* 상태에 따라 이미지 변경 */}
          </div>
          <div className="login-button">
            <button onClick={handleLoginLogout}>
              {isLoggedIn ? "로그아웃" : "로그인"} {/* 로그인 상태에 따라 버튼 텍스트 변경 */}
            </button>
          </div>
        </div>
      </div>
    </header>
  );
}

export default Header;
