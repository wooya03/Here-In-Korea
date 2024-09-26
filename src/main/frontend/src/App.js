import {Routes, Route } from 'react-router-dom';
import AdminLogin from './admin/js/AdminLogin.js';
import AdminMain from './admin/js/AdminMain.js';
import MemInfo from './admin/js/MemInfo.js';
import './App.css';
import Main from './Main.js';
import LoginMain from "./user/js/LoginMain";

function App() {
  return (
    <main>
      <Routes>
        <Route path="/" element={ <><Main /></>} />

        {/* 어드민 페이지 */}
        <Route path="/admin/login" element={<><AdminLogin /></>} />
        <Route 
          path="/admin" element={<><AdminMain /></>} />
          <Route 
          path="/admin/meminfo" element={<><MemInfo /></>} />

        <Route path="/admin/login" element={<AdminLogin />} />
        <Route path="/admin" element={<AdminMain />} />

      {/* Page Login */}
          <Route path="/user/js/LoginMain" element={<LoginMain/>}/>
      </Routes>
      </main>
  )
}

export default App;
