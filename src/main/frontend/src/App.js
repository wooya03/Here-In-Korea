import {Routes, Route, BrowserRouter } from 'react-router-dom';
import AdminLogin from './admin/js/AdminLogin.js';
import AdminMain from './admin/js/AdminMain.js';
import MemInfo from './admin/js/MemInfo.js';
import Main from './Main.js';
import LoginMain from "./user/js/LoginMain";
import Layout from "./layout/Layout";
import Header from "./layout/Header";
import React from "react";
import Routers from "./Routers";

function App() {
  return (
      <main>
          <Routes>
              <Route path="/" element={<><Header/><Main/></>}/>

              {/* 어드민 페이지 */}
              <Route path="/admin" element={<><AdminHeader/><AdminMain/></>}/>
              <Route path="/admin/login" element={<><AdminHeader/><AdminLogin/></>}/>
              <Route path="/admin/meminfo" element={<><AdminHeader/><MemInfo/></>}/>

              {/* Page Login */}
              <Route path="/user/js/LoginMain" element={<><Header/><LoginMain/></>}/>
          </Routes>
      </main>
  )
}

export default App;
