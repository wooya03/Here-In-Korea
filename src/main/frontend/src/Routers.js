import React from "react";
import {BrowserRouter,Route,Routes} from "react-router-dom";
import Main from "./Main";
import AdminLogin from "./admin/js/AdminLogin";
import AdminMain from "./admin/js/AdminMain";
import MemInfo from "./admin/js/MemInfo";
import LoginMain from "./user/js/LoginMain";
import Layout from "./layout/Layout";

const Routers = () =>{
    return(
        <BrowserRouter>
            <Routes>
                <Route element={<Layout/>}>
                    <Route path="/" element={ <><Main /></>} />

                    {/* 어드민 페이지 */}
                    <Route path="/admin/login" element={<><AdminLogin /></>} />
                    <Route path="/admin" element={<><AdminMain /></>} />
                    <Route path="/admin/meminfo" element={<><MemInfo /></>} />
                    <Route path="/admin/login" element={<AdminLogin />} />
                    <Route path="/admin" element={<AdminMain />} />

                    {/* 로그인 페이지 */}
                    <Route path={"/login"} element={<LoginMain/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
    )
}
export default Routers