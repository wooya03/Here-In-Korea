import React, { useState } from "react";
import { Link } from "react-router-dom"; // Link 추가
import "./Header.css";
import {useNavigate} from "react-router-dom";

function Header() {
  const [isLoggedIn, setIsLoggedIn] = useState(false); // 로그인 상태
  const [guestImage, setGuestImage] = useState(`${process.env.PUBLIC_URL}/Image/guest1.png`); // 초기 이미지를 guest1.png로 설정
  const navigate = useNavigate(); // 라우터 네비게이트 이동을 위한 생성

  const handleLoginLogout = () => {
    if (isLoggedIn) {
      setGuestImage(`${process.env.PUBLIC_URL}/Image/guest1.png`); // 게스트 이미지로 변경
      setIsLoggedIn(false); // 로그인 상태를 false로 변경
      navigate("/");  //로그아웃이후 메인페이지로 이동

    } else {
      setGuestImage(`${process.env.PUBLIC_URL}/Image/user1.png`); // 사용자 이미지로 변경
      setIsLoggedIn(true); // 로그인 상태를 true로 변경
      navigate("/login"); //로그인 페이지로 이동
    }
  };

  return (
    <header className="header">
      <div className="logo-container">
        <Link to="/">
          <img className="logo" alt="logo_1" src={`${process.env.PUBLIC_URL}/Image/logo_1.png`} />
        </Link>
      </div>

      <div className="menu">
        <nav className="nav">
          <ul className="menu">
            <li className="menu-item first-item"><Link to="/">메인</Link></li>
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
        <input type="text" className="search-input" placeholder="검색어를 입력하세요." />
        <button className="search-button">검색</button>
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
