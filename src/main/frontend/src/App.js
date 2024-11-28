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
import RegisterAuth from "./user/js/RegisterAuth";
import IdFind from "./user/js/IdFind";
import QuestionDetails from "./qna/js/QuestionDetails";
import Review from "./review/js/Review";
import ReviewWrite from "./review/js/ReviewWrite";
import Course from "./course/js/Course";
import PwFind from "./user/js/PwFind";

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
        <Route path="/login" element={<><Header /><LoginMain /></>} />

        {/* 회원가입 페이지 */}
        <Route path="/register" element={<><Header /><Register /></>} />
        <Route path="/registerauth" element={<><Header /><RegisterAuth /></>} />

        {/* 아이디/비밀번호 찾기 페이지*/}
        <Route path="/find/id" element={<><Header /><IdFind /></>} />
        {/* 비밀번호 찾기 경로 수정 예정*/}
        <Route path="/find/pw" element={<><Header /><PwFind /></>} />


        {/* 질문 페이지 */}
        <Route path="/question/list" element={<><Header /><QuestionList /></>} />
        <Route path="/question/write" element={<><Header /><QuestionAdd /></>} />
        <Route path="/question/:id" element={<><Header /><QuestionDetails /></>} />

        {/* 리뷰 페이지 */}
        <Route path="/review" element={<><Review /></>} />
        <Route path="/review/write" element={<><ReviewWrite /></>} />

        {/* 코스 페이지 */}
        <Route path="/course" element={<><Course /></>} />

      </Routes>
    </main>
  );
}

export default App;
