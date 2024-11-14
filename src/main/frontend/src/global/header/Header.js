import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Header.css";
import TranslateApi from '../translate/Translate_api';  // 수정된 경로와 파일명

function Header() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [guestImage, setGuestImage] = useState(`${process.env.PUBLIC_URL}/Image/guest1.png`);
  const navigate = useNavigate();

  const handleLoginLogout = () => {
    if (isLoggedIn) {
      setGuestImage(`${process.env.PUBLIC_URL}/Image/guest1.png`);
      setIsLoggedIn(false);
      navigate("/");
    } else {
      setGuestImage(`${process.env.PUBLIC_URL}/Image/user1.png`);
      setIsLoggedIn(true);
      navigate("/loginMain");
    }
  };

  const handleSearchClick = () => {
    console.log("Search button clicked"); // 클릭 여부 확인
    navigate("/search_page");
  };

  return (
    <header className="header">
      {/* Google 번역 위젯을 헤더 상단에 추가 */}
      <TranslateApi />

      <div className="logo-container">
        <Link to="/">
          <img className="logo" alt="logo_1" src={`${process.env.PUBLIC_URL}/Image/logo_1.png`} />
        </Link>
      </div>

      <div className="menu">
        <nav className="nav">
          <ul className="menu">
            <li className="menu-item first-item"><Link to="/">메인</Link></li>
            <li className="menu-item"><Link to="/festival">행사</Link></li>
            <li className="menu-item"><Link to="/review">리뷰</Link></li>
            <li className="menu-item"><Link to="/corse">여행코스</Link></li>
            <li className="menu-item"><Link to="/accommodation">숙박</Link></li>
            <li className="menu-item"><Link to="/map">지도</Link></li>
            <li className="menu-item last-item"><Link to="/question">Q&A</Link></li>
          </ul>
        </nav>
      </div>

      <div className="search-container">
        <input type="text" className="search-input" placeholder="검색어를 입력하세요." />
        <button className="search-button" onClick={handleSearchClick}>검색</button>
      </div>

      <div className="login-container">
        <div className="guest-image">
          <img alt="guest" src={guestImage} style={{ width: '40px', height: '40px', borderRadius: '50%' }} />
        </div>
        <div className="login-button">
          <button onClick={handleLoginLogout}>
            {isLoggedIn ? "로그아웃" : "로그인"}
          </button>
        </div>
      </div>
    </header>
  );
}

export default Header;
