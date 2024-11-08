import {Routes, Route } from 'react-router-dom';
import AdminLogin from './admin/js/AdminLogin.js';
import AdminMain from './admin/js/AdminMain.js';
import UserManagement from './admin/js/UserManagement.js';
import './App.css';
import Main from './main/Main.js';
import LoginMain from "./user/js/LoginMain";
import AdminHeader from './admin/js/AdminHeader.js';
import Header from './global/header/Header.js';
import CourseMenagement from './admin/js/CourseManagement.js';
import ReviewManagement from './admin/js/ReviewManagement.js';
import CommentManagement from './admin/js/CommentManagement.js';
import QnaManagement from './admin/js/QnaManagement.js';
import AdminLogout from './admin/js/AdminLogout.js';
import Register from './user/js/Register.js'
import RegisterAuth from './user/js/RegisterAuth'

function App() {
  return (
    <main>
      <Routes>
        <Route path="/" element={ <><Header/><Main /></>} />
        
        {/* 어드민 페이지 */}
        <Route path="/admin" element={<><AdminHeader/><AdminMain /></>} />
        <Route path="/admin/login" element={<><AdminHeader/><AdminLogin /></>} />
        <Route path="/admin/logout" element={<><AdminHeader/><AdminLogout /></>} />
        <Route path="/admin/user" element={<><AdminHeader/><UserManagement /></>} />
        <Route path="/admin/course" element={<><AdminHeader/><CourseMenagement /></>} />
        <Route path="/admin/review" element={<><AdminHeader/><ReviewManagement /></>} />
        <Route path="/admin/comment" element={<><AdminHeader/><CommentManagement /></>} />
        <Route path="/admin/qna" element={<><AdminHeader/><QnaManagement /></>} />

      {/* Page Login */}
          <Route path="/login" element={<><Header/><LoginMain/></>}/>
      {/*  Page register*/}
          <Route path="/register" element={<><Header/><Register/></>}/>
        <Route path="/registerAuth" element={<><Header/><RegisterAuth/></>}/>


      </Routes>
      </main>
  )
}

export default App;
