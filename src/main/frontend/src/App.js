import {Routes, Route, BrowserRouter } from 'react-router-dom';
import AdminLogin from './admin/js/AdminLogin.js';
import AdminMain from './admin/js/AdminMain.js';
import MemInfo from './admin/js/MemInfo.js';
import Main from './Main.js';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={ <>
                            <Main />
                        </>} />

        {/* 어드민 페이지 */}
        <Route path="/admin/login" 
          element={<>
            <AdminLogin />
          </>} />
        <Route 
          path="/admin" 
          element={<>
            <AdminMain />
          </>} />
          <Route 
          path="/admin/meminfo" 
          element={<>
            <MemInfo />
          </>} />
      </Routes>
    </BrowserRouter>
  )
}

export default App;
