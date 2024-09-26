import {Routes, Route, BrowserRouter } from 'react-router-dom';
import './App.css';
import AdminLogin from './admin/js/AdminLogin.js';
import Main from './Main.js';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/admin" element={<AdminLogin />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App;
