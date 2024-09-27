import {Routes, Route } from 'react-router-dom';
import AdminLogin from './admin/js/AdminLogin.js';
import AdminMain from './admin/js/AdminMain.js';
import MemInfo from './admin/js/UserManagement.js';
import './App.css';
import Main from './main/Main.js';
import LoginMain from "./user/js/LoginMain";
import AdminHeader from './admin/js/AdminHeader.js';
import Header from './global/header/Header.js';

function App() {
  return (
    <main>
      <Routes>
        <Route path="/" element={ <><Header/><Main /></>} />

        {/* 어드민 페이지 */}
        <Route path="/admin" element={<><AdminHeader/><AdminMain /></>} />
        <Route path="/admin/login" element={<><AdminHeader/><AdminLogin /></>} />
        <Route path="/admin/meminfo" element={<><AdminHeader/><MemInfo /></>} />

      {/* Page Login */}
          <Route path="/login" element={<><Header/><LoginMain/></>}/>
      </Routes>
      </main>
  )
}

export default App;
