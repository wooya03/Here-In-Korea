import { Routes, Route } from 'react-router-dom';
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
import Register from './user/js/Register.js';
import Footer from "./global/footer/Footer.js";
import SearchPage from "./global/search/search_page";
import GoogleTranslate from './global/translate/Translate_api.js';
import QuestionList from './qna/js/QuestionList.js';
import QuestionAdd from './qna/js/QuestionAdd.js';

function App() {
  return (
    <main>
      <Routes>
        {/* 메인 페이지 */}
        <Route path="/" element={<><Header /><Main /></>} />
        
        {/* 검색 API */}
        <Route path="/" element={<><GoogleTranslate /></>}/>
        
        {/* 검색 페이지 */}
        <Route path="/search_page" element={<><Header /><SearchPage /></>} />

        {/* 어드민 페이지 */}
        <Route path="/admin" element={<><AdminHeader /><AdminMain /></>} />
        <Route path="/admin/login" element={<><AdminHeader /><AdminLogin /></>} />
        <Route path="/admin/logout" element={<><AdminHeader /><AdminLogout /></>} />
        <Route path="/admin/user" element={<><AdminHeader /><UserManagement /></>} />
        <Route path="/admin/course" element={<><AdminHeader /><CourseMenagement /></>} />
        <Route path="/admin/review" element={<><AdminHeader /><ReviewManagement /></>} />
        <Route path="/admin/comment" element={<><AdminHeader /><CommentManagement /></>} />
        <Route path="/admin/qna" element={<><AdminHeader /><QnaManagement /></>} />

        {/* 로그인 페이지 */}
        <Route path="/LoginMain" element={<><Header /><LoginMain /></>} />

        {/* 회원가입 페이지 */}
        <Route path="/register" element={<><Header /><Register /></>} />

        {/* 질문 페이지 */}
        <Route path="/question/list" element={<><Header /><QuestionList /></>} />
        <Route path="/question/write" element={<><Header /><QuestionAdd /></>} />
      </Routes>
    </main>
  );
}

export default App;
