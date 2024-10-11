import React from 'react';
import { Routes, Route } from 'react-router-dom';
import AdminLogin from './admin/js/AdminLogin.js';
import AdminMain from './admin/js/AdminMain.js';
import UserManagement from './admin/js/UserManagement.js';
import './App.css';
import Main from './main/Main.js';
import LoginMain from "./user/js/LoginMain";
import AdminHeader from './admin/js/AdminHeader.js';
import Header from './global/header/Header.js';
import CourseManagement from './admin/js/CourseManagement.js';
import ReviewManagement from './admin/js/ReviewManagement.js';
import CommentManagement from './admin/js/CommentManagement.js';
import QnaManagement from './admin/js/QnaManagement.js';
import AdminLogout from './admin/js/AdminLogout.js';
import Banner from './global/banner/Banner';  // 배너 컴포넌트 추가
import SearchPage from './global/search/search_page'; // 경로 수정

function App() {
  return (
    <main>
      <Routes>
        <Route path="/" element={<><Header/><Banner/><Main /></>} />  {/* 헤더, 배너, 메인 페이지 */}

        {/* 어드민 페이지 */}
        <Route path="/admin" element={<><AdminHeader/><AdminMain /></>} />
        <Route path="/admin/login" element={<><AdminHeader/><AdminLogin /></>} />
        <Route path="/admin/logout" element={<><AdminHeader/><AdminLogout /></>} />
        <Route path="/admin/user" element={<><AdminHeader/><UserManagement /></>} />
        <Route path="/admin/course" element={<><AdminHeader/><CourseManagement /></>} />
        <Route path="/admin/review" element={<><AdminHeader/><ReviewManagement /></>} />
        <Route path="/admin/comment" element={<><AdminHeader/><CommentManagement /></>} />
        <Route path="/admin/qna" element={<><AdminHeader/><QnaManagement /></>} />

        {/* 페이지 로그인 */}
        <Route path="/user/js/LoginMain" element={<><Header/><LoginMain/></>} />
        
        {/* 검색 페이지 */}
        <Route path="/search" element={<><Header/><SearchPage/></>} />
      </Routes>
    </main>
  );
}

export default App;
