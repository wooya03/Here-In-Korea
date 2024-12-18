import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const AdminLogout = () => {
    const token = localStorage.getItem("token");
    console.log(token);
    const baseUrl = "http://localhost:8080";
    const navigate = useNavigate();

    useEffect(() => {
        putSpringData();
    }, []);

    async function putSpringData() {
        if (!token) {
            alert("로그인이 필요합니다.");
            navigate("/admin/login");
            return;
        }
    
        try {
            const response = await fetch(
                `${baseUrl}/admin/logout`, 
                {
                    method: 'POST',
                    headers: {
                        Authorization: `Bearer ${token}`,
                    }, 
                }
            );
    
            if (response.status === 200) {
                localStorage.removeItem("token");
                alert("로그아웃 되었습니다.");
                navigate("/admin/login");
            } else {
                alert("로그아웃 처리 중 문제가 발생했습니다.");
            }
        } catch (err) {
            console.error("로그아웃 실패:", err.response?.data || err.message);
            alert("로그아웃 처리 중 오류가 발생했습니다.");
        }
    }   
};

export default AdminLogout;
