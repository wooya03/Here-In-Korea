import { Routes, Route } from 'react-router-dom';
import AdminLogin from './admin/js/AdminLogin.js';
import AdminMain from './admin/js/AdminMain.js';
import UserManagement from './admin/js/UserManagement.js';
import './App.css';
import Main from './main/Main.js';
import LoginMain from "./user/js/LoginMain";
import AdminHeader from './admin/js/AdminHeader.js';
import Header from './global/header/Header.js';
import ReviewManagement from './admin/js/ReviewManagement.js';
import QnaManagement from './admin/js/QnaManagement.js';
import AdminLogout from './admin/js/AdminLogout.js';
import Register from './user/js/Register.js';
import SearchPage from "./global/search/search_page";
import GoogleTranslate from './global/translate/Translate_api.js';
import QuestionList from './qna/js/QuestionList.js';
import QuestionAdd from './qna/js/QuestionAdd.js';
import QuestionDetails from './qna/js/QuestionDetails.js';
import AnswerAdd from './qna/js/AnswerAdd.js';
import Review from './review/js/Review.js';
import ReviewWrite from './review/js/ReviewWrite.js'
import Course from './course/js/Course.js';
import CourseWrite from "./course/js/CourseWrite.js";
import CourseManagement from "./admin/js/CourseManagement.js";
import RegisterAuth from "./user/js/RegisterAuth";
import IdFind from "./user/js/IdFind";
import PwFind from "./user/js/PwFind";
import Profile from "./user/js/Profile";
import HotelsList from './hotels/js/HotelsList.js';
import HotelDetails from './hotels/js/HotelsDetails.js';
import Festival from "./festival/js/Festival";
import GoogleMaps from "./maps/js/GoogleMaps";
import FestivalDetails from "./festival/js/FestivalDetails";
import {AuthProvider} from "./global/auth_context/AuthContext";



function App() {
  return (
    <AuthProvider>
    <main>
      <Routes>
        {/* 메인 페이지 */}
        <Route path="/" element={<><Header /><Main /></>} />
        
        {/* 검색 API */}
        <Route path="/translate" element={<GoogleTranslate />} />

<<<<<<<<< Temporary merge branch 1
            {/* 검색 페이지 */}
            <Route path="/search_page" element={<><Header /><SearchPage /></>} />
=========
        {/* 어드민 페이지 */}
        <Route path="/admin" element={<><AdminHeader /><AdminMain /></>} />
        <Route path="/admin/login" element={<><AdminHeader /><AdminLogin /></>} />
        <Route path="/admin/logout" element={<><AdminHeader /><AdminLogout /></>} />
        <Route path="/admin/user" element={<><AdminHeader /><UserManagement /></>} />
        <Route path="/admin/course" element={<><AdminHeader /><CourseManagement /></>} />
        <Route path="/admin/review" element={<><AdminHeader /><ReviewManagement /></>} />
        <Route path="/admin/qna" element={<><AdminHeader /><QnaManagement /></>} />
>>>>>>>>> Temporary merge branch 2

            {/* 어드민 페이지 */}
            <Route path="/admin" element={<><AdminHeader /><AdminMain /></>} />
            <Route path="/admin/login" element={<><AdminHeader /><AdminLogin /></>} />
            <Route path="/admin/logout" element={<><AdminHeader /><AdminLogout /></>} />
            <Route path="/admin/user" element={<><AdminHeader /><UserManagement /></>} />
            <Route path="/admin/course" element={<><AdminHeader /><CourseManagement /></>} />
            <Route path="/admin/review" element={<><AdminHeader /><ReviewManagement /></>} />
            <Route path="/admin/comment" element={<><AdminHeader /><CommentManagement /></>} />
            <Route path="/admin/qna" element={<><AdminHeader /><QnaManagement /></>} />

        {/* 로그인 페이지 */}
        <Route path="/user/login" element={<><Header /><LoginMain /></>} />

        {/* 회원가입 페이지 */}
        <Route path="/user/register" element={<><Header /><Register /></>} />
        <Route path="/user/registerauth" element={<><Header /><RegisterAuth /></>} />

        {/* 아이디/비밀번호 찾기 페이지*/}
        <Route path="/user/find/id" element={<><Header /><IdFind /></>} />
        {/* 비밀번호 찾기 경로 수정 예정*/}
        <Route path="/user/find/pw" element={<><Header /><PwFind /></>} />

        {/* 프로필 */}
        <Route path="/user/profile" element={<><Header /><Profile /></>} />


        {/* 질문 페이지 */}
        <Route path="/question" element={<><Header /><QuestionList /></>} />
        <Route path="/question/write" element={<><Header /><QuestionAdd /></>} />
        <Route path="/question/:id" element={<><Header /><QuestionDetails /></>} />
        {/* 답변 페이지 */}
        <Route path="/question/:id/answer/write" element={<><AdminHeader /><AnswerAdd /></>} />

        {/* 리뷰 페이지 */}
        <Route path="/review" element={<><Review /></>} />
        <Route path="/review/write" element={<><ReviewWrite /></>} />

        {/* 코스 페이지 */}
        <Route path="/course" element={<><Course /></>} />
        <Route path="/course/write" element={<><CourseWrite /></>} />

        {/* 지도 */}
        <Route path="/map" element={<><Header/> <GoogleMaps /></>} />

        {/* 숙소 페이지 */}
        <Route path='/hotels' element={<><Header /><HotelsList /></>} />
        <Route path='/hotels/:id' element={<><Header /><HotelDetails /></>} />

        {/* 행사 페이지 */}
        <Route path='/festival' element={<><Header /><Festival /></>} />
        <Route path='/festival/:contentId' element={<><Header /><FestivalDetails /></>} />


      </Routes>
    </main>
    </AuthProvider>
  );
}

export default App;
