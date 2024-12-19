import React, { useState, useEffect } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import "./Header.css";
import TranslateApi from "../translate/Translate_api";
import { useAuth } from "../auth_context/AuthContext";

function Header() {
  const { isLoggedIn, checkLoginStatus } = useAuth();
  const [guestImage, setGuestImage] = useState(`${process.env.PUBLIC_URL}/Image/guest1.png`);
  const [searchTerm, setSearchTerm] = useState(""); // 검색어 상태 추가
  const navigate = useNavigate();
  const location = useLocation(); // useLocation 추가
  const token = localStorage.getItem("token");


  useEffect(() => {
    if (isLoggedIn) {
      setGuestImage(`${process.env.PUBLIC_URL}/Image/user1.png`);
    } else {
      setGuestImage(`${process.env.PUBLIC_URL}/Image/guest1.png`);
    }
  }, [isLoggedIn]);

  useEffect(() => {
    checkLoginStatus();  // 컴포넌트가 마운트될 때마다 로그인 상태 검사
  }, [location]);

  // 검색어 처리
  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const query = params.get("query");

    if (query) {
      setSearchTerm(query); // URL에 있는 검색어로 상태 설정
    } else {
      setSearchTerm(""); // 검색어가 없으면 빈 값으로 설정
    }
  }, [location.search]); // location.search가 변경될 때마다 실행

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("refreshToken");
    checkLoginStatus();
  };

  const handleLoginLogout = () => {
    if (isLoggedIn) {
      handleLogout();
    } else {
      navigate("/user/login");
    }
  };

  const handleSearchClick = () => {
    if (!searchTerm.trim()) {
      alert("검색어를 입력해 주세요!"); // 검색어가 비어있을 때 경고창 띄움
      return;
    }
    navigate(`/search_page?query=${encodeURIComponent(searchTerm)}`); // 검색어를 URL 파라미터로 전달
  };

  const handleKeyDown = (e) => {
    if (e.key === "Enter") {
      handleSearchClick(); // 엔터키가 눌리면 검색 실행
    }
  };

  const handleMoveProfile = () => {
    if (isLoggedIn) {
      navigate("/user/profile"); // 로그인 상태일 때만 프로필로 이동
    }
  };

  return (
      <header className="header">
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
              <li className="menu-item"><Link to="/course">여행코스</Link></li>
              <li className="menu-item"><Link to="/hotels">숙박</Link></li>
              <li className="menu-item"><Link to="/map">지도</Link></li>
              <li className="menu-item last-item"><Link to="/question">Q&A</Link></li>
            </ul>
          </nav>
        </div>

        <div className="search-container">
          <input
              type="text"
              className="search-input"
              placeholder="검색어를 입력하세요."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)} // 검색어 입력 상태 업데이트
              onKeyDown={handleKeyDown} // 엔터키 이벤트 처리
          />
          <button className="search-button" onClick={handleSearchClick}>검색</button>
        </div>

        <div className="login-container">
          <div className="guest-image">
            <img
                alt="guest"
                src={guestImage}
                style={{ width: '40px', height: '40px', borderRadius: '50%' }}
                onClick={handleMoveProfile}
            />
          </div>
          <div className="login-button">
            <button onClick={handleLoginLogout}>
              {token ? "로그아웃" : "로그인"}
            </button>
          </div>
        </div>
      </header>
  );
}

export default Header;
