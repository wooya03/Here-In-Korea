import {Routes, Route, BrowserRouter } from 'react-router-dom';
import './App.css';
import AdminLogin from './admin/js/AdminLogin.js';
import AdminMain from './admin/js/AdminMain.js';
import Main from './Main.js';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />} />

        {/* 어드민 페이지 */}
        <Route path="/admin/login" element={<AdminLogin />} />
        <Route path="/admin" element={<AdminMain />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App;
